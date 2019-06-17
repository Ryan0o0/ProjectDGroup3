/*
 * Copyright 2019 The TensorFlow Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.tensorflow.lite.examples.detection;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.media.Image;
import android.media.Image.Plane;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Size;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.ar.core.Anchor;
import com.google.ar.core.Frame;
import com.google.ar.core.HitResult;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.ArSceneView;
import com.google.ar.sceneform.FrameTime;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ViewRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import org.tensorflow.lite.examples.detection.env.ImageUtils;
import org.tensorflow.lite.examples.detection.env.Logger;
import org.tensorflow.lite.examples.detection.tflite.Classifier;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

//import com.google.ar.core.Plane;

public abstract class CameraActivity extends AppCompatActivity {
  private static final Logger LOGGER = new Logger();

  protected int   previewWidth  = 0;
  protected int   previewHeight = 0;
  private boolean debug = false;
  private Handler handler;
  private HandlerThread handlerThread;
  private boolean   useCamera2API;
  private boolean   isProcessingFrame = false;
  private byte[][]  yuvBytes = new byte[3][];
  private int[]     rgbBytes = null;

  public static int screenWidth;
  public static int screenHeight;


  //Thread Communication
  ///////////////////////////////////////////////////////////////////////////////////////////////
  public static List<Classifier.Recognition> mappedRecognitionsAvailable = new LinkedList<Classifier.Recognition>();
  ///////////////////////////////////////////////////////////////////////////////////////////////


  private int yRowStride;
  private Runnable postInferenceCallback;
  private Runnable imageConverter;
  protected TextView frameValueTextView, cropValueTextView, inferenceTimeTextView;



  // AR REQUIRED
  ///////////////////////////////////////////////////////////////////////////////////////////////
  private static final String TAG = CameraActivity.class.getSimpleName();
  private static final double MIN_OPENGL_VERSION = 3.0;

  private ArFragment arFragment;
  private ArSceneView sceneView;
  private ModelRenderable andyRenderable;
  private ViewRenderable markerName;

  private byte[] data = null;
  ///////////////////////////////////////////////////////////////////////////////////////////////


  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    LOGGER.d("onCreate " + this);
    super.onCreate(null);

    DisplayMetrics displayMetrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
    screenHeight = displayMetrics.heightPixels;
    screenWidth = displayMetrics.widthPixels;

    // AR REQUIRED
    ///////////////////////////////////////////////////////////////////////////////////////////////
    if (!checkIsSupportedDeviceOrFinish(this)) {
      return;
    }

    setContentView(R.layout.activity_camera);
    arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ux_fragment);
    sceneView = arFragment.getArSceneView();

    // When you build a Renderable, Sceneform loads its resources in the background while returning
    // a CompletableFuture. Call thenAccept(), handle(), or check isDone() before calling get().
    ModelRenderable.builder()
            .setSource(this, Uri.parse("marker.sfb"))
            .build()
            .thenAccept(renderable -> {andyRenderable = renderable;
              renderable.setShadowCaster(false);
              renderable.setShadowReceiver(false);})
            .exceptionally(
                    throwable -> {
                      Toast toast =
                              Toast.makeText(this, "Unable to load andy renderable", Toast.LENGTH_LONG);
                      toast.setGravity(Gravity.CENTER, 0, 0);
                      toast.show();
                      return null;
                    });

    sceneView.getScene().addOnUpdateListener(this::onSceneUpdate);

    arFragment.setOnTapArPlaneListener(
            (HitResult hitResult, com.google.ar.core.Plane plane, MotionEvent motionEvent) -> {
              if (andyRenderable == null) {
                return;
              }

              float x = motionEvent.getRawX();
              float y = motionEvent.getRawY();
              List<Classifier.Recognition> mappedRecognitionsDetected = mappedRecognitionsAvailable;

              for (Classifier.Recognition result : mappedRecognitionsDetected){
                if(x > result.getLocation().left && x < result.getLocation().right){
                  if(y > result.getLocation().top && y < result.getLocation().bottom){
                    Anchor anchor = hitResult.createAnchor();
                    AnchorNode anchorNode = new AnchorNode(anchor);
                    anchorNode.setParent(arFragment.getArSceneView().getScene());

                    // Create the transformable andy and add it to the anchor.
                    TransformableNode andy = new TransformableNode(arFragment.getTransformationSystem());
                    andy.setParent(anchorNode);
                    andy.setRenderable(andyRenderable);
                    andy.select();

                    addName(anchorNode, andy, result.getTitle().substring(0, 1).toUpperCase() + result.getTitle().substring(1));
                    break;
                  }
                }
              }
            });
    ///////////////////////////////////////////////////////////////////////////////////////////////
  }

  private void addName(AnchorNode anchorNode, TransformableNode model, String name){
    ViewRenderable.builder()
            .setView(this, R.layout.name_marker)
            .build()
            .thenAccept( viewRenderable -> {

              TransformableNode nameView = new TransformableNode(arFragment.getTransformationSystem());
              nameView.setLocalPosition(new Vector3(0f, model.getLocalPosition().y+0.2f, 0));
              nameView.setParent(anchorNode);
              nameView.setRenderable(viewRenderable);
              viewRenderable.setShadowCaster(false);
              viewRenderable.setShadowReceiver(false);

              //Setting text
              TextView marker_text = (TextView) viewRenderable.getView();
              marker_text.setText(name);

              //Click on textview to remove marker
              marker_text.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                  anchorNode.setParent(null);
                }
              });
            });
  }

  protected int[] getRgbBytes() {
    imageConverter.run();
    return rgbBytes;
  }

  protected int getLuminanceStride() {
    return yRowStride;
  }

  protected byte[] getLuminance() {
    return yuvBytes[0];
  }

  public void onPreviewFrame(final byte[] bytes, final int height, final int width) {
    if (isProcessingFrame) {
      LOGGER.w("Dropping frame!");
      return;
    }

    LOGGER.i("Communicated data: " + mappedRecognitionsAvailable);

    try {
      // Initialize the storage bitmaps once when the resolution is known.
      if (rgbBytes == null) {
        //Camera.Size previewSize = camera.getParameters().getPreviewSize();
        previewHeight = height;
        previewWidth = width;
        rgbBytes = new int[previewWidth * previewHeight];
        onPreviewSizeChosen(new Size(width, height), 90);
      }
    } catch (final Exception e) {
      LOGGER.e(e, "Exception!");
      return;
    }

    isProcessingFrame = true;
    yuvBytes[0] = bytes;
    yRowStride = previewWidth;

    imageConverter =
        new Runnable() {
          @Override
          public void run() {
            ImageUtils.convertYUV420SPToARGB8888(bytes, previewWidth, previewHeight, rgbBytes);
          }
        };

    postInferenceCallback =
        new Runnable() {
          @Override
          public void run() {
            isProcessingFrame = false;
          }
        };
    processImage();
  }

  @Override
  public synchronized void onStart() {
    LOGGER.d("onStart " + this);
    super.onStart();
  }

  @Override
  public synchronized void onResume() {
    LOGGER.d("onResume " + this);
    super.onResume();

    handlerThread = new HandlerThread("inference");
    handlerThread.start();
    handler = new Handler(handlerThread.getLooper());
  }

  @Override
  public synchronized void onPause() {
    LOGGER.d("onPause " + this);

    handlerThread.quitSafely();
    try {
      handlerThread.join();
      handlerThread = null;
      handler = null;
    } catch (final InterruptedException e) {
      LOGGER.e(e, "Exception!");
    }

    super.onPause();
  }

  @Override
  public synchronized void onStop() {
    LOGGER.d("onStop " + this);
    super.onStop();
  }

  @Override
  public synchronized void onDestroy() {
    LOGGER.d("onDestroy " + this);
    super.onDestroy();
  }

  protected synchronized void runInBackground(final Runnable r) {
    if (handler != null) {
      handler.post(r);
    }
  }


  protected void fillBytes(final Plane[] planes, final byte[][] yuvBytes) {
    // Because of the variable row stride it's not possible to know in
    // advance the actual necessary dimensions of the yuv planes.
    for (int i = 0; i < planes.length; ++i) {
      final ByteBuffer buffer = planes[i].getBuffer();
      if (yuvBytes[i] == null) {
        LOGGER.d("Initializing buffer %d at size %d", i, buffer.capacity());
        yuvBytes[i] = new byte[buffer.capacity()];
      }
      buffer.get(yuvBytes[i]);
    }
  }

  public boolean isDebug() {
    return debug;
  }

  protected void readyForNextImage() {
    if (postInferenceCallback != null) {
      postInferenceCallback.run();
    }
  }

  protected int getScreenOrientation() {
    switch (getWindowManager().getDefaultDisplay().getRotation()) {
      case Surface.ROTATION_270:
        return 270;
      case Surface.ROTATION_180:
        return 180;
      case Surface.ROTATION_90:
        return 90;
      default:
        return 0;
    }
  }

  protected void showFrameInfo(String frameInfo) {
    frameValueTextView.setText(frameInfo);
  }

  protected void showCropInfo(String cropInfo) {
    cropValueTextView.setText(cropInfo);
  }

  protected void showInference(String inferenceTime) {
    inferenceTimeTextView.setText(inferenceTime);
  }

  protected abstract void processImage();

  protected abstract void onPreviewSizeChosen(final Size size, final int rotation);

  protected abstract int getLayoutId();

  protected abstract Size getDesiredPreviewFrameSize();

  protected abstract void setNumThreads(int numThreads);

  protected abstract void setUseNNAPI(boolean isChecked);

  // AR REQUIRED
  ///////////////////////////////////////////////////////////////////////////////////////////////
  public static boolean checkIsSupportedDeviceOrFinish(final Activity activity) {
    if (Build.VERSION.SDK_INT < VERSION_CODES.N) {
      Log.e(TAG, "Sceneform requires Android N or later");
      Toast.makeText(activity, "Sceneform requires Android N or later", Toast.LENGTH_LONG).show();
      activity.finish();
      return false;
    }
    String openGlVersionString =
            ((ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE))
                    .getDeviceConfigurationInfo()
                    .getGlEsVersion();
    if (Double.parseDouble(openGlVersionString) < MIN_OPENGL_VERSION) {
      Log.e(TAG, "Sceneform requires OpenGL ES 3.0 later");
      Toast.makeText(activity, "Sceneform requires OpenGL ES 3.0 or later", Toast.LENGTH_LONG)
              .show();
      activity.finish();
      return false;
    }
    return true;
  }

  public void onSceneUpdate(FrameTime frameTime) {
    Frame frame = sceneView.getArFrame();
    int height = 0;
    int width = 0;
    boolean hasRightFormat = false;

    if (frame == null) {
      return;
    }

    try (Image image = frame.acquireCameraImage()) {
      if (image.getFormat() != ImageFormat.YUV_420_888) {
        throw new IllegalArgumentException(
                "Expected image in YUV_420_888 format, got format " + image.getFormat());
      }
      width = image.getWidth();
      height =  image.getHeight();

      data = YUV_420_888toYUV420(image);

      hasRightFormat = true;

    } catch (Exception e) {
      Log.e(TAG, "Exception copying image", e);
    }

    if(hasRightFormat && (height != 0)) {
      onPreviewFrame(data, height, width);
      hasRightFormat = false;
    }
  }

  private static byte[] YUV_420_888toYUV420(Image image){
    Plane Y = image.getPlanes()[0];
    Plane U = image.getPlanes()[1];
    Plane V = image.getPlanes()[2];

    int uPixelStride = U.getPixelStride();

    ByteBuffer YBuffer = Y.getBuffer();
    ByteBuffer UBuffer = U.getBuffer();
    ByteBuffer VBuffer = V.getBuffer();

    YBuffer.rewind();
    UBuffer.rewind();
    VBuffer.rewind();

    int yb, ub, vb;
    yb = YBuffer.capacity();
    ub = UBuffer.capacity();
    vb = VBuffer.capacity();

    byte[] ybb, ubb,vbb;
    ybb = new byte[yb];
    ubb = new byte[ub];
    vbb = new byte[vb];

    YBuffer.get(ybb);
    UBuffer.get(ubb);
    VBuffer.get(vbb);

    byte[] yData = ybb;
    byte[] uData = new byte[yb/4];
    byte[] vData = new byte[yb/4];

    int index = 0;
    for(int i = 0; (i < (640 * 480 * 0.5)) && (index < (640 * 480 * 0.25)); i += uPixelStride){
      uData[index] = ubb[i];
      vData[index] = vbb[i];
      index++;
    }

    byte[] yuv420sp = new byte[(int)(640 * 480 * 1.5)];

    // Y DATA FIRST

    for (int i = 0 ; i < 640 * 480; i++)
      yuv420sp[i] = yData[i];

    //UV INTERLEAVED DATA NEXT

    index = 640 * 480;
    for (int i = 0; i < (640 * 480 * 0.25); i++) {
      yuv420sp[index] = uData[i];
      index++;
      yuv420sp[index] = vData[i];
      index++;
    }

    return yuv420sp;
  }
  ///////////////////////////////////////////////////////////////////////////////////////////////
}