package com.vuforia.ar.pl;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCaptureSession.CaptureCallback;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraDevice.StateCallback;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureRequest.Builder;
import android.hardware.camera2.CaptureRequest.Key;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.Image.Plane;
import android.media.ImageReader;
import android.media.ImageReader.OnImageAvailableListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Trace;
import android.util.Range;
import android.util.Size;
import android.view.Surface;
import com.vuforia.PIXEL_FORMAT;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;

@TargetApi(21)
public class Camera2_Preview {
    private static final int AR_CAMERA_DIRECTION_BACK = 268443665;
    private static final int AR_CAMERA_DIRECTION_FRONT = 268443666;
    private static final int AR_CAMERA_DIRECTION_UNKNOWN = 268443664;
    private static final int AR_CAMERA_EXPOSUREMODE_AUTO = 805314560;
    private static final int AR_CAMERA_EXPOSUREMODE_CONTINUOUSAUTO = 805322752;
    private static final int AR_CAMERA_EXPOSUREMODE_LOCKED = 805310464;
    private static final int AR_CAMERA_EXPOSUREMODE_MANUAL = 805339136;
    private static final int AR_CAMERA_EXPOSUREMODE_SHUTTER_PRIORITY = 805371904;
    private static final int AR_CAMERA_FOCUSMODE_AUTO = 805306400;
    private static final int AR_CAMERA_FOCUSMODE_CONTINUOUSAUTO = 805306432;
    private static final int AR_CAMERA_FOCUSMODE_FIXED = 805306880;
    private static final int AR_CAMERA_FOCUSMODE_INFINITY = 805306624;
    private static final int AR_CAMERA_FOCUSMODE_MACRO = 805306496;
    private static final int AR_CAMERA_FOCUSMODE_NORMAL = 805306384;
    private static final int AR_CAMERA_IMAGE_FORMAT_ARGB32 = 268439813;
    private static final int AR_CAMERA_IMAGE_FORMAT_ARGB8888 = 268439813;
    private static final int AR_CAMERA_IMAGE_FORMAT_BGR24 = 268439822;
    private static final int AR_CAMERA_IMAGE_FORMAT_BGR888 = 268439822;
    private static final int AR_CAMERA_IMAGE_FORMAT_BGRA32 = 268439814;
    private static final int AR_CAMERA_IMAGE_FORMAT_BGRA8888 = 268439814;
    private static final int AR_CAMERA_IMAGE_FORMAT_LUM = 268439809;
    private static final int AR_CAMERA_IMAGE_FORMAT_NV12 = 268439815;
    private static final int AR_CAMERA_IMAGE_FORMAT_NV16 = 268439816;
    private static final int AR_CAMERA_IMAGE_FORMAT_NV21 = 268439817;
    private static final int AR_CAMERA_IMAGE_FORMAT_RGB24 = 268439811;
    private static final int AR_CAMERA_IMAGE_FORMAT_RGB565 = 268439810;
    private static final int AR_CAMERA_IMAGE_FORMAT_RGB888 = 268439811;
    private static final int AR_CAMERA_IMAGE_FORMAT_RGBA32 = 268439812;
    private static final int AR_CAMERA_IMAGE_FORMAT_RGBA4444 = 268439821;
    private static final int AR_CAMERA_IMAGE_FORMAT_RGBA5551 = 268439820;
    private static final int AR_CAMERA_IMAGE_FORMAT_RGBA8888 = 268439812;
    private static final int AR_CAMERA_IMAGE_FORMAT_UNKNOWN = 268439808;
    private static final int AR_CAMERA_IMAGE_FORMAT_YUV420P = 268439828;
    private static final int AR_CAMERA_IMAGE_FORMAT_YV12 = 268439818;
    private static final int AR_CAMERA_IMAGE_FORMAT_YV16 = 268439819;
    private static final int AR_CAMERA_PARAMTYPE_BASE = 536870912;
    private static final int AR_CAMERA_PARAMTYPE_BRIGHTNESSRANGE = 537133056;
    private static final int AR_CAMERA_PARAMTYPE_BRIGHTNESSVALUE = 537001984;
    private static final int AR_CAMERA_PARAMTYPE_CONTRASTRANGE = 537919488;
    private static final int AR_CAMERA_PARAMTYPE_CONTRASTVALUE = 537395200;
    private static final int AR_CAMERA_PARAMTYPE_EXPOSUREMODE = 536870944;
    private static final int AR_CAMERA_PARAMTYPE_EXPOSURETIME = 536871168;
    private static final int AR_CAMERA_PARAMTYPE_EXPOSURETIMERANGE = 536871424;
    private static final int AR_CAMERA_PARAMTYPE_EXPOSUREVALUE = 536871936;
    private static final int AR_CAMERA_PARAMTYPE_EXPOSUREVALUERANGE = 536872960;
    private static final int AR_CAMERA_PARAMTYPE_FOCUSMODE = 536870914;
    private static final int AR_CAMERA_PARAMTYPE_FOCUSRANGE = 536870920;
    private static final int AR_CAMERA_PARAMTYPE_FOCUSREGION = 536870928;
    private static final int AR_CAMERA_PARAMTYPE_FOCUSVALUE = 536870916;
    private static final int AR_CAMERA_PARAMTYPE_ISO = 536870976;
    private static final int AR_CAMERA_PARAMTYPE_ISORANGE = 536871040;
    private static final int AR_CAMERA_PARAMTYPE_LENS_IS_ADJUSTING = 545259520;
    private static final int AR_CAMERA_PARAMTYPE_RECORDING_HINT = 541065216;
    private static final int AR_CAMERA_PARAMTYPE_ROTATION = 538968064;
    private static final int AR_CAMERA_PARAMTYPE_TORCHMODE = 536870913;
    private static final int AR_CAMERA_PARAMTYPE_VIDEO_STABILIZATION = 553648128;
    private static final int AR_CAMERA_PARAMTYPE_WHITEBALANCEMODE = 536875008;
    private static final int AR_CAMERA_PARAMTYPE_WHITEBALANCERANGE = 536887296;
    private static final int AR_CAMERA_PARAMTYPE_WHITEBALANCEVALUE = 536879104;
    private static final int AR_CAMERA_PARAMTYPE_ZOOMRANGE = 536936448;
    private static final int AR_CAMERA_PARAMTYPE_ZOOMVALUE = 536903680;
    private static final int AR_CAMERA_PARAMVALUE_BASE = 805306368;
    private static final int AR_CAMERA_STATUS_CAPTURE_RUNNING = 268443651;
    private static final int AR_CAMERA_STATUS_OPENED = 268443650;
    private static final int AR_CAMERA_STATUS_UNINITIALIZED = 268443649;
    private static final int AR_CAMERA_STATUS_UNKNOWN = 268443648;
    private static final int AR_CAMERA_TORCHMODE_AUTO = 805306372;
    private static final int AR_CAMERA_TORCHMODE_CONTINUOUSAUTO = 805306376;
    private static final int AR_CAMERA_TORCHMODE_OFF = 805306369;
    private static final int AR_CAMERA_TORCHMODE_ON = 805306370;
    private static final int AR_CAMERA_TYPE_MONO = 268447761;
    private static final int AR_CAMERA_TYPE_STEREO = 268447762;
    private static final int AR_CAMERA_TYPE_UNKNOWN = 268447760;
    private static final int AR_CAMERA_WHITEBALANCEMODE_AUTO = 807403520;
    private static final int AR_CAMERA_WHITEBALANCEMODE_CONTINUOUSAUTO = 809500672;
    private static final int AR_CAMERA_WHITEBALANCEMODE_LOCKED = 806354944;
    private static final int CAMERA_CAPSINFO_VALUE_NUM_SUPPORTED_FRAMERATES = 4;
    private static final int CAMERA_CAPSINFO_VALUE_NUM_SUPPORTED_IMAGEFORMATS = 5;
    private static final int CAMERA_CAPSINFO_VALUE_NUM_SUPPORTED_IMAGESIZES = 3;
    private static final int CAMERA_CAPSINFO_VALUE_SUPPORTED_PARAMVALUES = 2;
    private static final int CAMERA_CAPSINFO_VALUE_SUPPORTED_QUERYABLE_PARAMS = 0;
    private static final int CAMERA_CAPSINFO_VALUE_SUPPORTED_SETTABLE_PARAMS = 1;
    private static final int CAMERA_CAPTUREINFO_VALUE_FORMAT = 2;
    private static final int CAMERA_CAPTUREINFO_VALUE_FRAMERATE = 3;
    private static final int CAMERA_CAPTUREINFO_VALUE_HEIGHT = 1;
    private static final int CAMERA_CAPTUREINFO_VALUE_PREVIEWSURFACEENABLED = 4;
    private static final int CAMERA_CAPTUREINFO_VALUE_WIDTH = 0;
    private static final int[] CAMERA_VALID_IMAGE_FORMAT_PL = new int[]{AR_CAMERA_IMAGE_FORMAT_NV21, AR_CAMERA_IMAGE_FORMAT_NV12, AR_CAMERA_IMAGE_FORMAT_YV12, AR_CAMERA_IMAGE_FORMAT_YUV420P};
    private static final Range<Integer> EMPTY_RANGE = new Range(Integer.valueOf(0), Integer.valueOf(0));
    private static final String FOCUS_MODE_NORMAL = "normal";
    private static final int MAX_HIGHEST_FPS_ALLOWED = 300;
    private static final int MAX_LOWEST_FPS_ALLOWED = 150;
    private static final String MODULENAME = "Camera2_Preview";
    private static final int NUM_CAPTURE_BUFFERS = 2;
    private static final int NUM_MAX_CAMERAOPEN_RETRY = 10;
    private static final int TIME_CAMERAOPEN_RETRY_DELAY_MS = 250;
    private static final int _NUM_CAMERA_CAPSINFO_VALUE_ = 6;
    private static final int _NUM_CAMERA_CAPTUREINFO_VALUE_ = 5;
    private HashMap<ImageReader, Integer> mCameraCacheInfoIndexCache = null;
    private Vector<CameraCacheInfo> mCameraCacheInfos = null;
    private Vector<CameraCacheInfo> mCameraCacheInfosInProgress = null;
    private CameraManager mCameraManager;
    private Context mContext;
    private int mIsPermissionGranted = -1;
    private Semaphore mOpenCloseSemaphore = new Semaphore(1);

    /* renamed from: com.vuforia.ar.pl.Camera2_Preview$1 */
    class C00671 extends StateCallback {
        C00671() {
        }

        public void onOpened(android.hardware.camera2.CameraDevice r7) {
            /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/345281752.run(Unknown Source)
*/
            /*
            r6 = this;
            r0 = 0;
            r1 = com.vuforia.ar.pl.Camera2_Preview.this;	 Catch:{ CameraAccessException -> 0x0043, IllegalArgumentException -> 0x003d, IllegalStateException -> 0x0037 }
            r1 = r1.mCameraCacheInfosInProgress;	 Catch:{ CameraAccessException -> 0x0043, IllegalArgumentException -> 0x003d, IllegalStateException -> 0x0037 }
            r1 = r1.iterator();	 Catch:{ CameraAccessException -> 0x0043, IllegalArgumentException -> 0x003d, IllegalStateException -> 0x0037 }
            r2 = r0;
        L_0x000c:
            r3 = r1.hasNext();	 Catch:{ CameraAccessException -> 0x0044, IllegalArgumentException -> 0x003e, IllegalStateException -> 0x0038 }
            if (r3 == 0) goto L_0x0048;	 Catch:{ CameraAccessException -> 0x0044, IllegalArgumentException -> 0x003e, IllegalStateException -> 0x0038 }
        L_0x0012:
            r3 = r1.next();	 Catch:{ CameraAccessException -> 0x0044, IllegalArgumentException -> 0x003e, IllegalStateException -> 0x0038 }
            r3 = (com.vuforia.ar.pl.Camera2_Preview.CameraCacheInfo) r3;	 Catch:{ CameraAccessException -> 0x0044, IllegalArgumentException -> 0x003e, IllegalStateException -> 0x0038 }
            r4 = r3.deviceIDString;	 Catch:{ CameraAccessException -> 0x0044, IllegalArgumentException -> 0x003e, IllegalStateException -> 0x0038 }
            r5 = r7.getId();	 Catch:{ CameraAccessException -> 0x0044, IllegalArgumentException -> 0x003e, IllegalStateException -> 0x0038 }
            r4 = r4.equals(r5);	 Catch:{ CameraAccessException -> 0x0044, IllegalArgumentException -> 0x003e, IllegalStateException -> 0x0038 }
            if (r4 == 0) goto L_0x000c;
        L_0x0024:
            r3.device = r7;	 Catch:{ CameraAccessException -> 0x0033, IllegalArgumentException -> 0x0031, IllegalStateException -> 0x002f }
            r2 = 1;	 Catch:{ CameraAccessException -> 0x0033, IllegalArgumentException -> 0x0031, IllegalStateException -> 0x002f }
            r2 = r7.createCaptureRequest(r2);	 Catch:{ CameraAccessException -> 0x0033, IllegalArgumentException -> 0x0031, IllegalStateException -> 0x002f }
            r3.builder = r2;	 Catch:{ CameraAccessException -> 0x0033, IllegalArgumentException -> 0x0031, IllegalStateException -> 0x002f }
            r2 = r3;
            goto L_0x000c;
        L_0x002f:
            r2 = r3;
            goto L_0x0038;
        L_0x0031:
            r2 = r3;
            goto L_0x003e;
        L_0x0033:
            r2 = r3;
            goto L_0x0044;
        L_0x0035:
            r7 = move-exception;
            goto L_0x0052;
        L_0x0037:
            r2 = r0;
        L_0x0038:
            r2.builder = r0;	 Catch:{ all -> 0x0035 }
            r2.device = r0;	 Catch:{ all -> 0x0035 }
            goto L_0x0048;	 Catch:{ all -> 0x0035 }
        L_0x003d:
            r2 = r0;	 Catch:{ all -> 0x0035 }
        L_0x003e:
            r2.builder = r0;	 Catch:{ all -> 0x0035 }
            r2.device = r0;	 Catch:{ all -> 0x0035 }
            goto L_0x0048;	 Catch:{ all -> 0x0035 }
        L_0x0043:
            r2 = r0;	 Catch:{ all -> 0x0035 }
        L_0x0044:
            r2.builder = r0;	 Catch:{ all -> 0x0035 }
            r2.device = r0;	 Catch:{ all -> 0x0035 }
        L_0x0048:
            r7 = com.vuforia.ar.pl.Camera2_Preview.this;
            r7 = r7.mOpenCloseSemaphore;
            r7.release();
            return;
        L_0x0052:
            r0 = com.vuforia.ar.pl.Camera2_Preview.this;
            r0 = r0.mOpenCloseSemaphore;
            r0.release();
            throw r7;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.Camera2_Preview.1.onOpened(android.hardware.camera2.CameraDevice):void");
        }

        public void onError(CameraDevice cameraDevice, int i) {
            cameraDevice.close();
            Camera2_Preview.this.mOpenCloseSemaphore.release();
        }

        public void onDisconnected(CameraDevice cameraDevice) {
            cameraDevice.close();
            Camera2_Preview.this.mOpenCloseSemaphore.release();
        }
    }

    /* renamed from: com.vuforia.ar.pl.Camera2_Preview$2 */
    class C00682 extends CameraCaptureSession.StateCallback {
        C00682() {
        }

        public void onConfigured(CameraCaptureSession cameraCaptureSession) {
            CameraCacheInfo cameraCacheInfo;
            Iterator it = Camera2_Preview.this.mCameraCacheInfos.iterator();
            while (it.hasNext()) {
                cameraCacheInfo = (CameraCacheInfo) it.next();
                if (cameraCacheInfo.deviceIDString.equals(cameraCaptureSession.getDevice().getId())) {
                    break;
                }
            }
            cameraCacheInfo = null;
            cameraCacheInfo.session = cameraCaptureSession;
            for (Surface addTarget : cameraCacheInfo.surfaces) {
                cameraCacheInfo.builder.addTarget(addTarget);
            }
            Camera2_Preview.this.mOpenCloseSemaphore.release();
        }

        public void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
            cameraCaptureSession.close();
            Camera2_Preview.this.mOpenCloseSemaphore.release();
        }
    }

    private class AutofocusRunner extends CaptureCallback {
        private CameraCacheInfo mCCI;
        private CaptureRequest mCancelRequest = null;
        private CaptureRequest mFocusRequest = null;

        public AutofocusRunner(CameraCacheInfo cameraCacheInfo) {
            this.mCCI = cameraCacheInfo;
        }

        public boolean triggerAutofocus() throws CameraAccessException {
            if (!(this.mCCI == null || this.mCCI.builder == null)) {
                if (this.mCCI.session != null) {
                    Integer num = (Integer) this.mCCI.builder.get(CaptureRequest.CONTROL_AF_MODE);
                    if (CaptureRequest.CONTROL_AF_MODE != null) {
                        if (num != null) {
                            if (num.intValue() != 1 && num.intValue() != 2) {
                                return false;
                            }
                            this.mCCI.isAutoFocusing = true;
                            this.mCCI.builder.set(CaptureRequest.CONTROL_AF_TRIGGER, Integer.valueOf(2));
                            this.mCancelRequest = this.mCCI.builder.build();
                            this.mCCI.builder.set(CaptureRequest.CONTROL_AF_TRIGGER, Integer.valueOf(0));
                            this.mCCI.builder.set(CaptureRequest.CONTROL_AF_TRIGGER, Integer.valueOf(1));
                            this.mFocusRequest = this.mCCI.builder.build();
                            this.mCCI.builder.set(CaptureRequest.CONTROL_AF_TRIGGER, Integer.valueOf(0));
                            this.mCCI.session.capture(this.mCancelRequest, this, this.mCCI.handler);
                            return true;
                        }
                    }
                    return false;
                }
            }
            return false;
        }

        public void onCaptureCompleted(android.hardware.camera2.CameraCaptureSession r2, android.hardware.camera2.CaptureRequest r3, android.hardware.camera2.TotalCaptureResult r4) {
            /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/345281752.run(Unknown Source)
*/
            /*
            r1 = this;
            super.onCaptureCompleted(r2, r3, r4);
            r0 = android.hardware.camera2.CaptureResult.CONTROL_AF_STATE;
            r4 = r4.get(r0);
            r4 = (java.lang.Integer) r4;
            r0 = r1.mCancelRequest;
            r0 = r3.equals(r0);
            if (r0 == 0) goto L_0x0023;
        L_0x0013:
            r0 = r4.intValue();
            if (r0 != 0) goto L_0x0023;
        L_0x0019:
            r3 = r1.mFocusRequest;	 Catch:{ CameraAccessException -> 0x003e, CameraAccessException -> 0x003e, CameraAccessException -> 0x003e }
            r4 = r1.mCCI;	 Catch:{ CameraAccessException -> 0x003e, CameraAccessException -> 0x003e, CameraAccessException -> 0x003e }
            r4 = r4.handler;	 Catch:{ CameraAccessException -> 0x003e, CameraAccessException -> 0x003e, CameraAccessException -> 0x003e }
            r2.capture(r3, r1, r4);	 Catch:{ CameraAccessException -> 0x003e, CameraAccessException -> 0x003e, CameraAccessException -> 0x003e }
            goto L_0x003e;
        L_0x0023:
            r2 = r1.mFocusRequest;
            r2 = r3.equals(r2);
            if (r2 == 0) goto L_0x003e;
        L_0x002b:
            r2 = r4.intValue();
            r3 = 4;
            if (r2 == r3) goto L_0x0039;
        L_0x0032:
            r2 = r4.intValue();
            r3 = 5;
            if (r2 != r3) goto L_0x003e;
        L_0x0039:
            r2 = r1.mCCI;
            r3 = 0;
            r2.isAutoFocusing = r3;
        L_0x003e:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.Camera2_Preview.AutofocusRunner.onCaptureCompleted(android.hardware.camera2.CameraCaptureSession, android.hardware.camera2.CaptureRequest, android.hardware.camera2.TotalCaptureResult):void");
        }
    }

    public class CameraCacheInfo {
        int bufferFormatPL;
        int bufferHeight;
        int bufferWidth;
        Builder builder;
        int[] caps;
        CameraCharacteristics characteristics;
        CameraDevice device;
        long deviceHandle;
        int deviceID;
        String deviceIDString;
        Handler handler;
        Semaphore imageSemaphore;
        Image[] images;
        boolean isAutoFocusing;
        CaptureResult lastResult;
        int overrideFormatAndroid;
        int overrideFormatPL;
        int overrideHeight;
        int overrideWidth;
        ImageReader reader;
        int requestFormatAndroid;
        int requestFormatPL;
        int requestFramerate;
        int requestHeight;
        int requestWidth;
        CameraCaptureSession session;
        int status;
        List<Surface> surfaces;
        HandlerThread thread;
    }

    public class FrameInfo {
        long exposureTime;
        int iso;
        long timestamp;
    }

    private class OnCameraDataAvailable implements OnImageAvailableListener {
        private int[] actualBufferSize;
        private int actualCaptureFormat;
        private long prevTimestamp;

        private OnCameraDataAvailable() {
            this.actualCaptureFormat = Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_UNKNOWN;
            this.actualBufferSize = null;
            this.prevTimestamp = -1;
        }

        public void onImageAvailable(ImageReader imageReader) {
            Trace.beginSection("onImageAvailable (java)");
            Integer num = (Integer) Camera2_Preview.this.mCameraCacheInfoIndexCache.get(imageReader);
            if (num == null) {
                Trace.endSection();
                return;
            }
            CameraCacheInfo cameraCacheInfo = (CameraCacheInfo) Camera2_Preview.this.mCameraCacheInfos.get(num.intValue());
            if (cameraCacheInfo == null) {
                Trace.endSection();
            } else if (cameraCacheInfo.imageSemaphore.tryAcquire()) {
                if (imageReader.getMaxImages() > 0) {
                    imageReader = imageReader.acquireLatestImage();
                    if (imageReader != null) {
                        FrameInfo frameInfo = new FrameInfo();
                        frameInfo.timestamp = imageReader.getTimestamp();
                        CaptureResult captureResult = cameraCacheInfo.lastResult;
                        if (captureResult != null) {
                            Long l = (Long) captureResult.get(CaptureResult.SENSOR_EXPOSURE_TIME);
                            if (l != null) {
                                frameInfo.exposureTime = l.longValue();
                                frameInfo.timestamp += l.longValue();
                            }
                            Integer num2 = (Integer) captureResult.get(CaptureResult.SENSOR_SENSITIVITY);
                            if (num2 != null) {
                                frameInfo.iso = num2.intValue();
                            }
                        }
                        if (frameInfo.timestamp > this.prevTimestamp) {
                            this.prevTimestamp = frameInfo.timestamp;
                            if (this.actualCaptureFormat == Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_UNKNOWN) {
                                this.actualCaptureFormat = getImageFormat(imageReader);
                            }
                            Camera2_Preview.this.newFrameAvailable(cameraCacheInfo.deviceHandle, num.intValue(), cameraCacheInfo.bufferWidth, cameraCacheInfo.bufferHeight, this.actualBufferSize, this.actualCaptureFormat, imageReader.getPlanes()[0].getBuffer(), frameInfo);
                        }
                        imageReader.close();
                    }
                }
                cameraCacheInfo.imageSemaphore.release();
                Trace.endSection();
            } else {
                DebugLog.LOGE(Camera2_Preview.MODULENAME, "Unable to aquire image semaphore, need to free some buffers!!");
                Trace.endSection();
            }
        }

        private int getImageFormat(Image image) {
            OnCameraDataAvailable onCameraDataAvailable = this;
            if (image != null && image.getPlanes().length == 3) {
                if (image.getFormat() == 35) {
                    Plane plane = image.getPlanes()[0];
                    Plane plane2 = image.getPlanes()[1];
                    Plane plane3 = image.getPlanes()[2];
                    if (plane.getPixelStride() == 1 && plane2.getPixelStride() == plane3.getPixelStride()) {
                        if (plane2.getRowStride() == plane3.getRowStride()) {
                            long[] jArr = new long[]{Camera2_Preview.this.getBufferAddress(plane.getBuffer()), Camera2_Preview.this.getBufferAddress(plane2.getBuffer()), Camera2_Preview.this.getBufferAddress(plane3.getBuffer())};
                            if (!(jArr[0] == 0 || jArr[1] == 0)) {
                                if (jArr[2] != 0) {
                                    if (plane2.getPixelStride() == 2) {
                                        if (jArr[1] + 1 == jArr[2]) {
                                            onCameraDataAvailable.actualBufferSize = calculateActualBufferSize(jArr[0], jArr[1], jArr[2], plane.getRowStride(), plane2.getRowStride(), image.getWidth(), image.getHeight(), Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_NV12);
                                            return Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_NV12;
                                        } else if (jArr[2] + 1 == jArr[1]) {
                                            onCameraDataAvailable.actualBufferSize = calculateActualBufferSize(jArr[0], jArr[2], jArr[1], plane.getRowStride(), plane2.getRowStride(), image.getWidth(), image.getHeight(), Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_NV21);
                                            return Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_NV21;
                                        } else {
                                            DebugLog.LOGE(Camera2_Preview.MODULENAME, "Detected an image with chroma pixel stride of two, but not NV12 or NV21. Unknown image format");
                                        }
                                    }
                                    if (plane2.getPixelStride() != 1) {
                                        DebugLog.LOGE(Camera2_Preview.MODULENAME, "Unable to detect a supported camera image format, Unknown image format");
                                        return Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_UNKNOWN;
                                    } else if (jArr[1] > jArr[2]) {
                                        onCameraDataAvailable.actualBufferSize = calculateActualBufferSize(jArr[0], jArr[2], jArr[1], plane.getRowStride(), plane2.getRowStride(), image.getWidth(), image.getHeight(), Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_YV12);
                                        return Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_YV12;
                                    } else {
                                        onCameraDataAvailable.actualBufferSize = calculateActualBufferSize(jArr[0], jArr[1], jArr[2], plane.getRowStride(), plane2.getRowStride(), image.getWidth(), image.getHeight(), Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_YUV420P);
                                        return Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_YUV420P;
                                    }
                                }
                            }
                            return Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_UNKNOWN;
                        }
                    }
                    return Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_UNKNOWN;
                }
            }
            return Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_UNKNOWN;
        }

        private int[] calculateActualBufferSize(long j, long j2, long j3, int i, int i2, int i3, int i4, int i5) {
            Object obj;
            int[] iArr;
            int i6 = i;
            int i7 = i2;
            int i8 = i5;
            if (!(i8 == Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_NV12 || i8 == Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_NV21) || j + ((long) (i4 * i3)) == j2) {
                if (i8 == Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_YV12 || i8 == Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_YUV420P) {
                    if (j + ((long) (i4 * i3)) == j2) {
                        if (j2 + ((long) ((i4 / 2) * (i3 / 2))) != j3) {
                        }
                    }
                }
                obj = null;
                if (obj != null) {
                    return null;
                }
                iArr = new int[6];
                iArr[0] = i6;
                long j4 = j2 - j;
                iArr[1] = (int) (j4 / ((long) i6));
                iArr[4] = (int) (j4 - ((long) (iArr[1] * i6)));
                iArr[2] = i7;
                if (i8 != Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_NV12) {
                    if (i8 == Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_NV21) {
                        if (i8 == Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_YV12 && i8 != Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_YUV420P) {
                            return iArr;
                        }
                        long j5 = j3 - j2;
                        iArr[3] = (int) (j5 / ((long) i7));
                        iArr[5] = (int) (j5 - ((long) (iArr[3] * i7)));
                        return iArr;
                    }
                }
                iArr[3] = i4 / 2;
                iArr[5] = 0;
                return iArr;
            }
            obj = 1;
            if (obj != null) {
                return null;
            }
            iArr = new int[6];
            iArr[0] = i6;
            long j42 = j2 - j;
            iArr[1] = (int) (j42 / ((long) i6));
            iArr[4] = (int) (j42 - ((long) (iArr[1] * i6)));
            iArr[2] = i7;
            if (i8 != Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_NV12) {
                if (i8 == Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_NV21) {
                    if (i8 == Camera2_Preview.AR_CAMERA_IMAGE_FORMAT_YV12) {
                    }
                    long j52 = j3 - j2;
                    iArr[3] = (int) (j52 / ((long) i7));
                    iArr[5] = (int) (j52 - ((long) (iArr[3] * i7)));
                    return iArr;
                }
            }
            iArr[3] = i4 / 2;
            iArr[5] = 0;
            return iArr;
        }
    }

    private class OnFrameCapturedCallback extends CaptureCallback {
        CameraCacheInfo mCCI;

        public OnFrameCapturedCallback(CameraCacheInfo cameraCacheInfo) {
            this.mCCI = cameraCacheInfo;
        }

        public void onCaptureCompleted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, TotalCaptureResult totalCaptureResult) {
            if (this.mCCI != null) {
                this.mCCI.lastResult = totalCaptureResult;
            }
        }
    }

    private static int compareHardwareSupportLevel(int i, int i2) {
        if (i == i2) {
            return 0;
        }
        int i3 = 1;
        if (i == 2) {
            if (i2 >= 0) {
                i3 = -1;
            }
            return i3;
        } else if (i2 != 2) {
            return i - i2;
        } else {
            if (i < 0) {
                i3 = -1;
            }
            return i3;
        }
    }

    private native long getBufferAddress(ByteBuffer byteBuffer);

    private native void newFrameAvailable(long j, int i, int i2, int i3, int[] iArr, int i4, ByteBuffer byteBuffer, Object obj);

    int getBitsPerPixel(int i) {
        if (i == 4) {
            return 16;
        }
        if (i == 842094169) {
            return 12;
        }
        switch (i) {
            case PIXEL_FORMAT.RGBA8888 /*16*/:
                return 16;
            case 17:
                return 12;
            default:
                return 0;
        }
    }

    private boolean checkPermission() {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/345281752.run(Unknown Source)
*/
        /*
        r4 = this;
        r0 = r4.mIsPermissionGranted;
        r1 = 1;
        if (r0 != 0) goto L_0x0006;
    L_0x0005:
        return r1;
    L_0x0006:
        r0 = com.vuforia.ar.pl.SystemTools.getActivityFromNative();	 Catch:{ Exception -> 0x001f }
        r2 = r0.getPackageManager();	 Catch:{ Exception -> 0x001f }
        r3 = "android.permission.CAMERA";	 Catch:{ Exception -> 0x001f }
        r0 = r0.getPackageName();	 Catch:{ Exception -> 0x001f }
        r0 = r2.checkPermission(r3, r0);	 Catch:{ Exception -> 0x001f }
        r4.mIsPermissionGranted = r0;	 Catch:{ Exception -> 0x001f }
        r0 = r4.mIsPermissionGranted;	 Catch:{ Exception -> 0x001f }
        if (r0 != 0) goto L_0x001f;
    L_0x001e:
        return r1;
    L_0x001f:
        r0 = 0;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.Camera2_Preview.checkPermission():boolean");
    }

    private int getCameraDeviceIndex(int r5, int r6, int r7) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/345281752.run(Unknown Source)
*/
        /*
        r4 = this;
        r6 = 0;
        r0 = -1;
        switch(r7) {
            case 268443664: goto L_0x000e;
            case 268443665: goto L_0x000c;
            case 268443666: goto L_0x000a;
            default: goto L_0x0005;
        };
    L_0x0005:
        r5 = 2;
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r5);
        return r0;
    L_0x000a:
        r7 = 0;
        goto L_0x000f;
    L_0x000c:
        r7 = 1;
        goto L_0x000f;
    L_0x000e:
        r7 = -1;
    L_0x000f:
        r1 = r4.mCameraManager;	 Catch:{ CameraAccessException -> 0x0039, CameraAccessException -> 0x0039 }
        r1 = r1.getCameraIdList();	 Catch:{ CameraAccessException -> 0x0039, CameraAccessException -> 0x0039 }
    L_0x0015:
        r2 = r1.length;	 Catch:{ CameraAccessException -> 0x0039, CameraAccessException -> 0x0039 }
        if (r6 >= r2) goto L_0x0039;	 Catch:{ CameraAccessException -> 0x0039, CameraAccessException -> 0x0039 }
    L_0x0018:
        r2 = r4.mCameraManager;	 Catch:{ CameraAccessException -> 0x0039, CameraAccessException -> 0x0039 }
        r3 = r1[r6];	 Catch:{ CameraAccessException -> 0x0039, CameraAccessException -> 0x0039 }
        r2 = r2.getCameraCharacteristics(r3);	 Catch:{ CameraAccessException -> 0x0039, CameraAccessException -> 0x0039 }
        if (r7 < 0) goto L_0x0030;	 Catch:{ CameraAccessException -> 0x0039, CameraAccessException -> 0x0039 }
    L_0x0022:
        r3 = android.hardware.camera2.CameraCharacteristics.LENS_FACING;	 Catch:{ CameraAccessException -> 0x0039, CameraAccessException -> 0x0039 }
        r2 = r2.get(r3);	 Catch:{ CameraAccessException -> 0x0039, CameraAccessException -> 0x0039 }
        r2 = (java.lang.Integer) r2;	 Catch:{ CameraAccessException -> 0x0039, CameraAccessException -> 0x0039 }
        r2 = r2.intValue();	 Catch:{ CameraAccessException -> 0x0039, CameraAccessException -> 0x0039 }
        if (r7 != r2) goto L_0x0035;
    L_0x0030:
        if (r5 < 0) goto L_0x0038;
    L_0x0032:
        if (r5 != r6) goto L_0x0035;
    L_0x0034:
        goto L_0x0038;
    L_0x0035:
        r6 = r6 + 1;
        goto L_0x0015;
    L_0x0038:
        return r6;
    L_0x0039:
        r5 = 6;
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r5);
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.Camera2_Preview.getCameraDeviceIndex(int, int, int):int");
    }

    private CameraCacheInfo getCameraCacheInfo(int i) {
        if (i >= 0) {
            if (i < this.mCameraCacheInfos.size()) {
                return (CameraCacheInfo) this.mCameraCacheInfos.get(i);
            }
        }
        return 0;
    }

    private boolean setCustomCameraParams(com.vuforia.ar.pl.Camera2_Preview.CameraCacheInfo r7, java.lang.String r8) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/345281752.run(Unknown Source)
*/
        /*
        r6 = this;
        r0 = 0;
        if (r7 == 0) goto L_0x004c;
    L_0x0003:
        r1 = r7.builder;
        if (r1 == 0) goto L_0x004c;
    L_0x0007:
        r1 = r7.characteristics;
        if (r1 != 0) goto L_0x000c;
    L_0x000b:
        goto L_0x004c;
    L_0x000c:
        r1 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x004b }
        r1.<init>(r8);	 Catch:{ JSONException -> 0x004b }
        r8 = r1.keys();
    L_0x0015:
        r2 = r8.hasNext();
        if (r2 == 0) goto L_0x0049;
    L_0x001b:
        r2 = r8.next();
        r2 = (java.lang.String) r2;
        r3 = r1.get(r2);	 Catch:{ JSONException -> 0x0048 }
        r4 = r3.getClass();
        r5 = java.lang.String.class;
        if (r4 == r5) goto L_0x0033;
    L_0x002d:
        r5 = java.lang.Integer.class;
        if (r4 != r5) goto L_0x0032;
    L_0x0031:
        goto L_0x0033;
    L_0x0032:
        return r0;
    L_0x0033:
        r4 = r7.characteristics;
        r4 = r6.mapStringToKey(r2, r4, r3);
        if (r4 == 0) goto L_0x0047;
    L_0x003b:
        r4 = r7.builder;
        r5 = r7.characteristics;
        r2 = r6.mapStringToKey(r2, r5, r3);
        r4.set(r2, r3);
        goto L_0x0015;
    L_0x0047:
        return r0;
    L_0x0048:
        return r0;
    L_0x0049:
        r7 = 1;
        return r7;
    L_0x004b:
        return r0;
    L_0x004c:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.Camera2_Preview.setCustomCameraParams(com.vuforia.ar.pl.Camera2_Preview$CameraCacheInfo, java.lang.String):boolean");
    }

    private <T> Key<T> mapStringToKey(String str, CameraCharacteristics cameraCharacteristics, T t) {
        for (Key key : cameraCharacteristics.getAvailableCaptureRequestKeys()) {
            if (key.getName().equals(str)) {
                return key;
            }
        }
        return null;
    }

    private boolean setCameraCaptureParams(CameraCacheInfo cameraCacheInfo, int[] iArr, int[] iArr2) {
        if (!(iArr == null && iArr2 == null)) {
            cameraCacheInfo.overrideWidth = iArr2 != null ? iArr2[0] : iArr[0];
            cameraCacheInfo.overrideHeight = iArr2 != null ? iArr2[1] : iArr[1];
            cameraCacheInfo.overrideFormatPL = iArr2 != null ? iArr2[2] : iArr[2];
            cameraCacheInfo.overrideFormatAndroid = translateImageFormatPLToAndroid(iArr2 != null ? iArr2[2] : iArr[2]);
        }
        if (iArr == null) {
            return true;
        }
        if (!(cameraCacheInfo == null || cameraCacheInfo.builder == null)) {
            if (cameraCacheInfo.characteristics != null) {
                cameraCacheInfo.requestWidth = iArr[0];
                cameraCacheInfo.requestHeight = iArr[1];
                cameraCacheInfo.requestFormatPL = iArr[2];
                cameraCacheInfo.requestFormatAndroid = translateImageFormatPLToAndroid(iArr[2]);
                cameraCacheInfo.requestFramerate = iArr[3];
                iArr = ((StreamConfigurationMap) cameraCacheInfo.characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)).getOutputSizes(cameraCacheInfo.requestFormatAndroid);
                if (iArr == null) {
                    return false;
                }
                boolean z = false;
                for (Size equals : iArr) {
                    z = equals.equals(new Size(cameraCacheInfo.requestWidth, cameraCacheInfo.requestHeight));
                    if (z) {
                        break;
                    }
                }
                if (!z) {
                    return false;
                }
                Range[] rangeArr = (Range[]) cameraCacheInfo.characteristics.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES);
                int length = rangeArr.length;
                Object obj = null;
                iArr2 = null;
                int i = Integer.MAX_VALUE;
                while (iArr2 < length) {
                    Range range = rangeArr[iArr2];
                    if (((Integer) range.getLower()).intValue() < MAX_LOWEST_FPS_ALLOWED) {
                        if (((Integer) range.getUpper()).intValue() < MAX_HIGHEST_FPS_ALLOWED) {
                            if (range.contains(Integer.valueOf(cameraCacheInfo.requestFramerate))) {
                                int intValue = ((Integer) range.getUpper()).intValue() - ((Integer) range.getLower()).intValue();
                                if (intValue < i) {
                                    obj = range;
                                    i = intValue;
                                }
                            }
                            iArr2++;
                        }
                    }
                    DebugLog.LOGW(MODULENAME, String.format("Detected odd fps values from Camera2 API: low=%d, high=%d.  Using first fps range as default instead of searching for perfect fit.", new Object[]{range.getLower(), range.getUpper()}));
                    obj = rangeArr[0];
                    break;
                }
                if (obj == null) {
                    return false;
                }
                cameraCacheInfo.builder.set(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE, obj);
                return true;
            }
        }
        return false;
    }

    private boolean setupPreviewBuffer(CameraCacheInfo cameraCacheInfo) {
        cameraCacheInfo.reader = ImageReader.newInstance(cameraCacheInfo.requestWidth, cameraCacheInfo.requestHeight, cameraCacheInfo.requestFormatAndroid, 2);
        cameraCacheInfo.imageSemaphore = new Semaphore(2);
        cameraCacheInfo.images = new Image[2];
        cameraCacheInfo.bufferWidth = cameraCacheInfo.requestWidth == cameraCacheInfo.overrideWidth ? cameraCacheInfo.reader.getWidth() : cameraCacheInfo.overrideWidth;
        cameraCacheInfo.bufferHeight = cameraCacheInfo.requestHeight == cameraCacheInfo.overrideHeight ? cameraCacheInfo.reader.getHeight() : cameraCacheInfo.overrideHeight;
        if (cameraCacheInfo.requestFormatAndroid == cameraCacheInfo.overrideFormatAndroid) {
            cameraCacheInfo.reader.getImageFormat();
        } else {
            int i = cameraCacheInfo.overrideFormatAndroid;
        }
        cameraCacheInfo.bufferFormatPL = cameraCacheInfo.requestFormatPL == cameraCacheInfo.overrideFormatPL ? cameraCacheInfo.requestFormatPL : cameraCacheInfo.overrideFormatPL;
        cameraCacheInfo.reader.setOnImageAvailableListener(new OnCameraDataAvailable(), cameraCacheInfo.handler);
        if (cameraCacheInfo.surfaces == null) {
            cameraCacheInfo.surfaces = new LinkedList();
        }
        cameraCacheInfo.surfaces.clear();
        cameraCacheInfo.surfaces.add(cameraCacheInfo.reader.getSurface());
        return true;
    }

    private void setCameraCapsBit(CameraCacheInfo cameraCacheInfo, int i, int i2, boolean z) {
        int i3;
        switch (i) {
            case 0:
            case 1:
                i3 = AR_CAMERA_PARAMTYPE_BASE;
                break;
            case 2:
                i3 = AR_CAMERA_PARAMVALUE_BASE;
                break;
            default:
                return;
        }
        i2 = (int) (Math.log((double) (i2 & (i3 ^ -1))) / Math.log(2.0d));
        if (z) {
            cameraCacheInfo = cameraCacheInfo.caps;
            cameraCacheInfo[i] = (1 << i2) | cameraCacheInfo[i];
        } else {
            cameraCacheInfo = cameraCacheInfo.caps;
            cameraCacheInfo[i] = ((1 << i2) ^ -1) & cameraCacheInfo[i];
        }
    }

    private int translateImageFormatPLToAndroid(int i) {
        for (int i2 : CAMERA_VALID_IMAGE_FORMAT_PL) {
            if (i == i2) {
                return 35;
            }
        }
        return 0;
    }

    private List<Integer> getSupportedPreviewFrameRates(CameraCharacteristics cameraCharacteristics) {
        Range[] rangeArr = (Range[]) cameraCharacteristics.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES);
        int i = Integer.MAX_VALUE;
        int i2 = Integer.MIN_VALUE;
        for (Range range : rangeArr) {
            i = Math.min(i, ((Integer) range.getLower()).intValue());
            i2 = Math.max(i2, ((Integer) range.getUpper()).intValue());
        }
        List<Integer> linkedList = new LinkedList();
        if (i >= 0 && i < MAX_LOWEST_FPS_ALLOWED && i2 >= 0) {
            if (i2 < MAX_HIGHEST_FPS_ALLOWED) {
                while (i <= i2) {
                    for (Range contains : rangeArr) {
                        if (contains.contains(Integer.valueOf(i))) {
                            linkedList.add(Integer.valueOf(i));
                            break;
                        }
                    }
                    i++;
                }
                return linkedList;
            }
        }
        DebugLog.LOGW(MODULENAME, String.format("Detected odd fps values from Camera2 API: low=%d, high=%d.  Using saner defaults instead.", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
        linkedList.add(Integer.valueOf(30));
        return linkedList;
    }

    private boolean checkCameraManager() {
        if (this.mCameraManager != null) {
            return true;
        }
        Activity activityFromNative = SystemTools.getActivityFromNative();
        if (activityFromNative == null) {
            return false;
        }
        Context application = activityFromNative.getApplication();
        if (application == null) {
            return false;
        }
        this.mCameraManager = (CameraManager) application.getSystemService("camera");
        if (this.mCameraManager == null) {
            return false;
        }
        return true;
    }

    private void cleanupHandlerThread(CameraCacheInfo cameraCacheInfo) {
        cameraCacheInfo.handler = null;
        cameraCacheInfo.thread.quitSafely();
        cameraCacheInfo.thread = null;
    }

    public boolean init() {
        this.mCameraCacheInfos = new Vector();
        this.mCameraCacheInfosInProgress = new Vector();
        this.mCameraCacheInfoIndexCache = new HashMap();
        return true;
    }

    public int getNumberOfCameras() {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/345281752.run(Unknown Source)
*/
        /*
        r3 = this;
        r0 = r3.checkPermission();
        r1 = -1;
        r2 = 6;
        if (r0 != 0) goto L_0x000c;
    L_0x0008:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r2);
        return r1;
    L_0x000c:
        r0 = r3.checkCameraManager();
        if (r0 != 0) goto L_0x0016;
    L_0x0012:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r2);
        return r1;
    L_0x0016:
        r0 = 21;
        r0 = com.vuforia.ar.pl.SystemTools.checkMinimumApiLevel(r0);
        if (r0 == 0) goto L_0x0026;
    L_0x001e:
        r0 = r3.mCameraManager;	 Catch:{ Exception -> 0x0026 }
        r0 = r0.getCameraIdList();	 Catch:{ Exception -> 0x0026 }
        r0 = r0.length;	 Catch:{ Exception -> 0x0026 }
        return r0;
    L_0x0026:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r2);
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.Camera2_Preview.getNumberOfCameras():int");
    }

    public int getOrientation(int r5) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/345281752.run(Unknown Source)
*/
        /*
        r4 = this;
        r0 = r4.checkPermission();
        r1 = -1;
        r2 = 6;
        if (r0 != 0) goto L_0x000c;
    L_0x0008:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r2);
        return r1;
    L_0x000c:
        r0 = r4.checkCameraManager();
        if (r0 != 0) goto L_0x0016;
    L_0x0012:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r2);
        return r1;
    L_0x0016:
        r0 = 21;
        r0 = com.vuforia.ar.pl.SystemTools.checkMinimumApiLevel(r0);
        if (r0 == 0) goto L_0x003c;
    L_0x001e:
        r0 = r4.mCameraManager;	 Catch:{ Exception -> 0x003c }
        r0 = r0.getCameraIdList();	 Catch:{ Exception -> 0x003c }
        r3 = r0.length;	 Catch:{ Exception -> 0x003c }
        if (r5 >= r3) goto L_0x003c;	 Catch:{ Exception -> 0x003c }
    L_0x0027:
        r3 = r4.mCameraManager;	 Catch:{ Exception -> 0x003c }
        r5 = r0[r5];	 Catch:{ Exception -> 0x003c }
        r5 = r3.getCameraCharacteristics(r5);	 Catch:{ Exception -> 0x003c }
        r0 = android.hardware.camera2.CameraCharacteristics.SENSOR_ORIENTATION;	 Catch:{ Exception -> 0x003c }
        r5 = r5.get(r0);	 Catch:{ Exception -> 0x003c }
        r5 = (java.lang.Integer) r5;	 Catch:{ Exception -> 0x003c }
        r5 = r5.intValue();	 Catch:{ Exception -> 0x003c }
        return r5;
    L_0x003c:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r2);
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.Camera2_Preview.getOrientation(int):int");
    }

    public int getDirection(int r6) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/345281752.run(Unknown Source)
*/
        /*
        r5 = this;
        r0 = r5.checkPermission();
        r1 = -1;
        r2 = 6;
        if (r0 != 0) goto L_0x000c;
    L_0x0008:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r2);
        return r1;
    L_0x000c:
        r0 = r5.checkCameraManager();
        if (r0 != 0) goto L_0x0016;
    L_0x0012:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r2);
        return r1;
    L_0x0016:
        r0 = 21;
        r0 = com.vuforia.ar.pl.SystemTools.checkMinimumApiLevel(r0);
        r3 = 268443665; // 0x10002011 float:2.5268252E-29 double:1.326287927E-315;
        if (r0 == 0) goto L_0x004e;
    L_0x0021:
        r0 = r5.mCameraManager;	 Catch:{ Exception -> 0x004a }
        r0 = r0.getCameraIdList();	 Catch:{ Exception -> 0x004a }
        r4 = r0.length;	 Catch:{ Exception -> 0x004a }
        if (r6 >= r4) goto L_0x004e;	 Catch:{ Exception -> 0x004a }
    L_0x002a:
        r4 = r5.mCameraManager;	 Catch:{ Exception -> 0x004a }
        r6 = r0[r6];	 Catch:{ Exception -> 0x004a }
        r6 = r4.getCameraCharacteristics(r6);	 Catch:{ Exception -> 0x004a }
        r0 = android.hardware.camera2.CameraCharacteristics.LENS_FACING;	 Catch:{ Exception -> 0x004a }
        r6 = r6.get(r0);	 Catch:{ Exception -> 0x004a }
        r6 = (java.lang.Integer) r6;	 Catch:{ Exception -> 0x004a }
        r6 = r6.intValue();	 Catch:{ Exception -> 0x004a }
        switch(r6) {
            case 0: goto L_0x0046;
            case 1: goto L_0x0045;
            default: goto L_0x0041;
        };
    L_0x0041:
        r6 = 268443664; // 0x10002010 float:2.526825E-29 double:1.32628792E-315;
        return r6;
    L_0x0045:
        return r3;
    L_0x0046:
        r6 = 268443666; // 0x10002012 float:2.5268255E-29 double:1.32628793E-315;
        return r6;
    L_0x004a:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r2);
        return r1;
    L_0x004e:
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.Camera2_Preview.getDirection(int):int");
    }

    public int getDeviceID(int i) {
        i = getCameraCacheInfo(i);
        if (i != 0) {
            return i.deviceID;
        }
        SystemTools.setSystemErrorCode(4);
        return -1;
    }

    public static boolean checkMinimumHardwareSupportLevel(int r7, int r8) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/345281752.run(Unknown Source)
*/
        /*
        r0 = 1;
        r1 = 0;
        r2 = 268443665; // 0x10002011 float:2.5268252E-29 double:1.326287927E-315;
        if (r7 != r2) goto L_0x0009;
    L_0x0007:
        r7 = 1;
        goto L_0x000f;
    L_0x0009:
        r2 = 268443666; // 0x10002012 float:2.5268255E-29 double:1.32628793E-315;
        if (r7 != r2) goto L_0x0055;
    L_0x000e:
        r7 = 0;
    L_0x000f:
        r2 = com.vuforia.ar.pl.SystemTools.getActivityFromNative();	 Catch:{ Exception -> 0x0050 }
        r3 = "camera";	 Catch:{ Exception -> 0x0050 }
        r2 = r2.getSystemService(r3);	 Catch:{ Exception -> 0x0050 }
        r2 = (android.hardware.camera2.CameraManager) r2;	 Catch:{ Exception -> 0x0050 }
        r3 = r2.getCameraIdList();	 Catch:{ Exception -> 0x0050 }
        r4 = 0;	 Catch:{ Exception -> 0x0050 }
    L_0x0020:
        r5 = r3.length;	 Catch:{ Exception -> 0x0050 }
        if (r4 >= r5) goto L_0x004f;	 Catch:{ Exception -> 0x0050 }
    L_0x0023:
        r5 = r3[r4];	 Catch:{ Exception -> 0x0050 }
        r5 = r2.getCameraCharacteristics(r5);	 Catch:{ Exception -> 0x0050 }
        r6 = android.hardware.camera2.CameraCharacteristics.LENS_FACING;	 Catch:{ Exception -> 0x0050 }
        r6 = r5.get(r6);	 Catch:{ Exception -> 0x0050 }
        r6 = (java.lang.Integer) r6;	 Catch:{ Exception -> 0x0050 }
        r6 = r6.intValue();	 Catch:{ Exception -> 0x0050 }
        if (r6 != r7) goto L_0x004c;	 Catch:{ Exception -> 0x0050 }
    L_0x0037:
        r7 = android.hardware.camera2.CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL;	 Catch:{ Exception -> 0x0050 }
        r7 = r5.get(r7);	 Catch:{ Exception -> 0x0050 }
        r7 = (java.lang.Integer) r7;	 Catch:{ Exception -> 0x0050 }
        r7 = r7.intValue();	 Catch:{ Exception -> 0x0050 }
        r7 = compareHardwareSupportLevel(r7, r8);	 Catch:{ Exception -> 0x0050 }
        if (r7 < 0) goto L_0x004a;
    L_0x0049:
        goto L_0x004b;
    L_0x004a:
        r0 = 0;
    L_0x004b:
        return r0;
    L_0x004c:
        r4 = r4 + 1;
        goto L_0x0020;
    L_0x004f:
        return r1;
    L_0x0050:
        r7 = 6;
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r7);
        return r1;
    L_0x0055:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.Camera2_Preview.checkMinimumHardwareSupportLevel(int, int):boolean");
    }

    public int open(long r8, int r10, int r11, int r12, java.lang.String r13, int[] r14, int[] r15) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/345281752.run(Unknown Source)
*/
        /*
        r7 = this;
        r0 = r7.checkPermission();
        r1 = 6;
        r2 = -1;
        if (r0 != 0) goto L_0x000c;
    L_0x0008:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r1);
        return r2;
    L_0x000c:
        r0 = r7.checkCameraManager();
        if (r0 != 0) goto L_0x0016;
    L_0x0012:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r1);
        return r2;
    L_0x0016:
        r10 = r7.getCameraDeviceIndex(r10, r11, r12);
        if (r10 >= 0) goto L_0x001d;
    L_0x001c:
        return r2;
    L_0x001d:
        r11 = r7.mCameraCacheInfos;
        r11 = r11.size();
        r12 = 0;
        r0 = 0;
        r4 = r12;
        r3 = 0;
    L_0x0027:
        if (r3 >= r11) goto L_0x0039;
    L_0x0029:
        r4 = r7.mCameraCacheInfos;
        r4 = r4.get(r3);
        r4 = (com.vuforia.ar.pl.Camera2_Preview.CameraCacheInfo) r4;
        r5 = r4.deviceID;
        if (r5 != r10) goto L_0x0036;
    L_0x0035:
        goto L_0x003a;
    L_0x0036:
        r3 = r3 + 1;
        goto L_0x0027;
    L_0x0039:
        r3 = -1;
    L_0x003a:
        if (r3 >= 0) goto L_0x00c3;
    L_0x003c:
        r4 = new com.vuforia.ar.pl.Camera2_Preview$CameraCacheInfo;	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
        r4.<init>();	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
        r4.deviceID = r10;	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
        r4.deviceHandle = r8;	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
        r8 = r7.mCameraManager;	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
        r8 = r8.getCameraIdList();	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
        r9 = r4.deviceID;	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
        r8 = r8[r9];	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
        r4.deviceIDString = r8;	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
        r8 = r7.mCameraManager;	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
        r9 = r4.deviceIDString;	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
        r8 = r8.getCameraCharacteristics(r9);	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
        r4.characteristics = r8;	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
        r4.device = r12;	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
        r4.session = r12;	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
        r4.builder = r12;	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
        r4.surfaces = r12;	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
        r4.reader = r12;	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
        r4.images = r12;	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
        r4.imageSemaphore = r12;	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
        r4.overrideWidth = r0;	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
        r4.bufferWidth = r0;	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
        r4.overrideHeight = r0;	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
        r4.bufferHeight = r0;	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
        r8 = 268439808; // 0x10001100 float:2.5256645E-29 double:1.32626887E-315;	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
        r4.bufferFormatPL = r8;	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
        r4.overrideFormatPL = r8;	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
        r4.overrideFormatAndroid = r0;	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
        r4.caps = r12;	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
        r8 = 268443649; // 0x10002001 float:2.5268204E-29 double:1.32628785E-315;	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
        r4.status = r8;	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
        r4.isAutoFocusing = r0;	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
        r8 = 268439817; // 0x10001109 float:2.5256672E-29 double:1.326268916E-315;	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
        r4.requestFormatPL = r8;	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
        r8 = 35;	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
        r4.requestFormatAndroid = r8;	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
        r8 = r4.characteristics;	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
        r9 = android.hardware.camera2.CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP;	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
        r8 = r8.get(r9);	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
        r8 = (android.hardware.camera2.params.StreamConfigurationMap) r8;	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
        r9 = r4.requestFormatAndroid;	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
        r8 = r8.getOutputSizes(r9);	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
        if (r8 == 0) goto L_0x00a8;	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
    L_0x009e:
        r9 = r8.length;	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
        if (r9 <= 0) goto L_0x00a8;	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
    L_0x00a1:
        r9 = r8[r0];	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
        r9 = r9.getWidth();	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
        goto L_0x00a9;	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
    L_0x00a8:
        r9 = 0;	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
    L_0x00a9:
        r4.requestWidth = r9;	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
        if (r8 == 0) goto L_0x00b7;	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
    L_0x00ad:
        r9 = r8.length;	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
        if (r9 <= 0) goto L_0x00b7;	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
    L_0x00b0:
        r8 = r8[r0];	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
        r8 = r8.getHeight();	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
        goto L_0x00b8;	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
    L_0x00b7:
        r8 = 0;	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
    L_0x00b8:
        r4.requestHeight = r8;	 Catch:{ CameraAccessException -> 0x00bf, IllegalArgumentException -> 0x00bb }
        goto L_0x00c3;
    L_0x00bb:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r1);
        return r2;
    L_0x00bf:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r1);
        return r2;
    L_0x00c3:
        r8 = 10;
        r9 = new android.os.HandlerThread;
        r10 = new java.lang.StringBuilder;
        r10.<init>();
        r11 = r4.deviceIDString;
        r10.append(r11);
        r11 = "_camera_thread";
        r10.append(r11);
        r10 = r10.toString();
        r9.<init>(r10);
        r4.thread = r9;
        r9 = r4.thread;
        r9.start();
        r9 = new android.os.Handler;
        r10 = r4.thread;
        r10 = r10.getLooper();
        r9.<init>(r10);
        r4.handler = r9;
        r9 = 0;
    L_0x00f2:
        r10 = 1;
        r11 = r7.mOpenCloseSemaphore;	 Catch:{ Exception -> 0x0125 }
        r11.acquire();	 Catch:{ Exception -> 0x0125 }
        r11 = r7.mCameraCacheInfosInProgress;	 Catch:{ Exception -> 0x0125 }
        r11.add(r4);	 Catch:{ Exception -> 0x0125 }
        r11 = r7.mCameraManager;	 Catch:{ Exception -> 0x0125 }
        r12 = r4.deviceIDString;	 Catch:{ Exception -> 0x0125 }
        r5 = new com.vuforia.ar.pl.Camera2_Preview$1;	 Catch:{ Exception -> 0x0125 }
        r5.<init>();	 Catch:{ Exception -> 0x0125 }
        r6 = r4.handler;	 Catch:{ Exception -> 0x0125 }
        r11.openCamera(r12, r5, r6);	 Catch:{ Exception -> 0x0125 }
        r11 = r7.mOpenCloseSemaphore;	 Catch:{ Exception -> 0x0125 }
        r11.acquire();	 Catch:{ Exception -> 0x0125 }
        r11 = r7.mCameraCacheInfosInProgress;	 Catch:{ Exception -> 0x0125 }
        r11.remove(r4);	 Catch:{ Exception -> 0x0125 }
        r11 = r7.mOpenCloseSemaphore;	 Catch:{ Exception -> 0x0125 }
        r11.release();	 Catch:{ Exception -> 0x0125 }
        r11 = r4.device;	 Catch:{ Exception -> 0x0125 }
        if (r11 == 0) goto L_0x0124;	 Catch:{ Exception -> 0x0125 }
    L_0x011e:
        r11 = r4.builder;	 Catch:{ Exception -> 0x0125 }
        if (r11 == 0) goto L_0x0124;
    L_0x0122:
        r9 = 1;
        goto L_0x0125;
    L_0x0124:
        r9 = 0;
    L_0x0125:
        if (r9 != 0) goto L_0x0134;
    L_0x0127:
        if (r8 <= 0) goto L_0x0134;
    L_0x0129:
        monitor-enter(r7);	 Catch:{ Exception -> 0x0134 }
        r11 = 250; // 0xfa float:3.5E-43 double:1.235E-321;
        r7.wait(r11);	 Catch:{ all -> 0x0131 }
        monitor-exit(r7);	 Catch:{ all -> 0x0131 }
        goto L_0x0134;	 Catch:{ all -> 0x0131 }
    L_0x0131:
        r11 = move-exception;	 Catch:{ all -> 0x0131 }
        monitor-exit(r7);	 Catch:{ all -> 0x0131 }
        throw r11;	 Catch:{ Exception -> 0x0134 }
    L_0x0134:
        if (r9 != 0) goto L_0x013d;
    L_0x0136:
        r11 = r8 + -1;
        if (r8 > 0) goto L_0x013b;
    L_0x013a:
        goto L_0x013d;
    L_0x013b:
        r8 = r11;
        goto L_0x00f2;
    L_0x013d:
        r8 = r4.device;
        if (r8 == 0) goto L_0x01a1;
    L_0x0141:
        r8 = r4.builder;
        if (r8 != 0) goto L_0x0146;
    L_0x0145:
        goto L_0x01a1;
    L_0x0146:
        if (r14 == 0) goto L_0x014b;
    L_0x0148:
        r8 = r14.length;
        if (r8 > 0) goto L_0x0150;
    L_0x014b:
        if (r15 == 0) goto L_0x0152;
    L_0x014d:
        r8 = r15.length;
        if (r8 <= 0) goto L_0x0152;
    L_0x0150:
        r8 = 1;
        goto L_0x0153;
    L_0x0152:
        r8 = 0;
    L_0x0153:
        if (r13 == 0) goto L_0x015c;
    L_0x0155:
        r9 = r13.length();
        if (r9 <= 0) goto L_0x015c;
    L_0x015b:
        r0 = 1;
    L_0x015c:
        if (r8 != 0) goto L_0x0160;
    L_0x015e:
        if (r0 == 0) goto L_0x018c;
    L_0x0160:
        r9 = 2;
        if (r8 == 0) goto L_0x017d;
    L_0x0163:
        if (r14 == 0) goto L_0x0170;
    L_0x0165:
        r8 = r14.length;
        r11 = 5;
        if (r8 == r11) goto L_0x0170;
    L_0x0169:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9);
        r7.cleanupHandlerThread(r4);
        return r2;
    L_0x0170:
        r8 = r7.setCameraCaptureParams(r4, r14, r15);
        if (r8 != 0) goto L_0x017d;
    L_0x0176:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r1);
        r7.cleanupHandlerThread(r4);
        return r2;
    L_0x017d:
        if (r0 == 0) goto L_0x018c;
    L_0x017f:
        r8 = r7.setCustomCameraParams(r4, r13);
        if (r8 != 0) goto L_0x018c;
    L_0x0185:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9);
        r7.cleanupHandlerThread(r4);
        return r2;
    L_0x018c:
        r8 = 268443650; // 0x10002002 float:2.5268207E-29 double:1.326287853E-315;
        r4.status = r8;
        if (r3 >= 0) goto L_0x01a0;
    L_0x0193:
        r8 = r7.mCameraCacheInfos;
        r8.add(r4);
        r8 = r7.mCameraCacheInfos;
        r8 = r8.size();
        r3 = r8 + -1;
    L_0x01a0:
        return r3;
    L_0x01a1:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r1);
        r7.cleanupHandlerThread(r4);
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.Camera2_Preview.open(long, int, int, int, java.lang.String, int[], int[]):int");
    }

    public boolean close(int r4) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/345281752.run(Unknown Source)
*/
        /*
        r3 = this;
        r4 = r3.getCameraCacheInfo(r4);
        r0 = 0;
        if (r4 != 0) goto L_0x000c;
    L_0x0007:
        r4 = 4;
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r4);
        return r0;
    L_0x000c:
        r1 = r3.mCameraCacheInfoIndexCache;
        r2 = r4.reader;
        r1.remove(r2);
        r1 = r4.session;	 Catch:{ Exception -> 0x002f }
        if (r1 == 0) goto L_0x001c;	 Catch:{ Exception -> 0x002f }
    L_0x0017:
        r1 = r4.session;	 Catch:{ Exception -> 0x002f }
        r1.close();	 Catch:{ Exception -> 0x002f }
    L_0x001c:
        r1 = r4.device;	 Catch:{ Exception -> 0x002f }
        if (r1 == 0) goto L_0x0025;	 Catch:{ Exception -> 0x002f }
    L_0x0020:
        r1 = r4.device;	 Catch:{ Exception -> 0x002f }
        r1.close();	 Catch:{ Exception -> 0x002f }
    L_0x0025:
        r1 = r4.reader;	 Catch:{ Exception -> 0x002f }
        if (r1 == 0) goto L_0x002e;	 Catch:{ Exception -> 0x002f }
    L_0x0029:
        r1 = r4.reader;	 Catch:{ Exception -> 0x002f }
        r1.close();	 Catch:{ Exception -> 0x002f }
    L_0x002e:
        r0 = 1;
    L_0x002f:
        r1 = 0;
        r4.session = r1;
        r4.reader = r1;
        r4.images = r1;
        r1 = 268443649; // 0x10002001 float:2.5268204E-29 double:1.32628785E-315;
        r4.status = r1;
        r3.cleanupHandlerThread(r4);
        java.lang.System.gc();
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.Camera2_Preview.close(int):boolean");
    }

    public int[] getCameraCapabilities(int r22) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/345281752.run(Unknown Source)
*/
        /*
        r21 = this;
        r0 = r21;
        r1 = r21.checkCameraManager();
        r2 = 0;
        r3 = 6;
        if (r1 != 0) goto L_0x000e;
    L_0x000a:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r3);
        return r2;
    L_0x000e:
        r1 = r21.getCameraCacheInfo(r22);
        r4 = 4;
        if (r1 != 0) goto L_0x0019;
    L_0x0015:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r4);
        return r2;
    L_0x0019:
        r5 = r1.caps;
        if (r5 == 0) goto L_0x0020;
    L_0x001d:
        r1 = r1.caps;
        return r1;
    L_0x0020:
        r5 = r0.mCameraManager;	 Catch:{ CameraAccessException -> 0x02f6, IllegalArgumentException -> 0x02f1 }
        r5 = r5.getCameraIdList();	 Catch:{ CameraAccessException -> 0x02f6, IllegalArgumentException -> 0x02f1 }
        r6 = r1.deviceID;	 Catch:{ CameraAccessException -> 0x02f6, IllegalArgumentException -> 0x02f1 }
        r5 = r5[r6];	 Catch:{ CameraAccessException -> 0x02f6, IllegalArgumentException -> 0x02f1 }
        r6 = r0.mCameraManager;	 Catch:{ CameraAccessException -> 0x02f6, IllegalArgumentException -> 0x02f1 }
        r5 = r6.getCameraCharacteristics(r5);	 Catch:{ CameraAccessException -> 0x02f6, IllegalArgumentException -> 0x02f1 }
        r6 = android.hardware.camera2.CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP;	 Catch:{ CameraAccessException -> 0x02f6, IllegalArgumentException -> 0x02f1 }
        r5 = r5.get(r6);	 Catch:{ CameraAccessException -> 0x02f6, IllegalArgumentException -> 0x02f1 }
        r5 = (android.hardware.camera2.params.StreamConfigurationMap) r5;	 Catch:{ CameraAccessException -> 0x02f6, IllegalArgumentException -> 0x02f1 }
        r2 = 35;
        r2 = r5.getOutputSizes(r2);
        r5 = r1.characteristics;
        r5 = r0.getSupportedPreviewFrameRates(r5);
        r6 = r1.characteristics;
        r7 = android.hardware.camera2.CameraCharacteristics.CONTROL_AF_AVAILABLE_MODES;
        r6 = r6.get(r7);
        r6 = (int[]) r6;
        java.util.Arrays.sort(r6);
        r7 = r1.characteristics;
        r8 = android.hardware.camera2.CameraCharacteristics.CONTROL_AE_AVAILABLE_MODES;
        r7 = r7.get(r8);
        r7 = (int[]) r7;
        java.util.Arrays.sort(r7);
        r8 = 0;
        if (r2 == 0) goto L_0x0063;
    L_0x0061:
        r9 = r2.length;
        goto L_0x0064;
    L_0x0063:
        r9 = 0;
    L_0x0064:
        if (r5 == 0) goto L_0x006b;
    L_0x0066:
        r10 = r5.size();
        goto L_0x006c;
    L_0x006b:
        r10 = 0;
    L_0x006c:
        r11 = CAMERA_VALID_IMAGE_FORMAT_PL;
        r11 = r11.length;
        r12 = r1.characteristics;
        r13 = android.hardware.camera2.CameraCharacteristics.LENS_INFO_AVAILABLE_FOCAL_LENGTHS;
        r12 = r12.get(r13);
        r12 = (float[]) r12;
        if (r12 == 0) goto L_0x0080;
    L_0x007b:
        r12 = r12.length;
        if (r12 <= 0) goto L_0x0080;
    L_0x007e:
        r12 = 1;
        goto L_0x0081;
    L_0x0080:
        r12 = 0;
    L_0x0081:
        r14 = r1.characteristics;
        r15 = android.hardware.camera2.CameraCharacteristics.FLASH_INFO_AVAILABLE;
        r14 = r14.get(r15);
        r14 = (java.lang.Boolean) r14;
        if (r14 != 0) goto L_0x0091;
    L_0x008d:
        r14 = java.lang.Boolean.valueOf(r8);
    L_0x0091:
        r15 = r1.characteristics;
        r4 = android.hardware.camera2.CameraCharacteristics.CONTROL_MAX_REGIONS_AF;
        r4 = r15.get(r4);
        r4 = (java.lang.Integer) r4;
        if (r4 == 0) goto L_0x00a5;
    L_0x009d:
        r4 = r4.intValue();
        if (r4 <= 0) goto L_0x00a5;
    L_0x00a3:
        r4 = 1;
        goto L_0x00a6;
    L_0x00a5:
        r4 = 0;
    L_0x00a6:
        r15 = r1.characteristics;
        r8 = android.hardware.camera2.CameraCharacteristics.CONTROL_AE_COMPENSATION_RANGE;
        r8 = r15.get(r8);
        r8 = (android.util.Range) r8;
        if (r8 == 0) goto L_0x00bc;
    L_0x00b2:
        r15 = EMPTY_RANGE;
        r8 = r15.equals(r8);
        if (r8 != 0) goto L_0x00bc;
    L_0x00ba:
        r8 = 1;
        goto L_0x00bd;
    L_0x00bc:
        r8 = 0;
    L_0x00bd:
        r15 = r1.characteristics;
        r3 = android.hardware.camera2.CameraCharacteristics.SENSOR_INFO_SENSITIVITY_RANGE;
        r3 = r15.get(r3);
        r3 = (android.util.Range) r3;
        if (r3 == 0) goto L_0x00d3;
    L_0x00c9:
        r15 = EMPTY_RANGE;
        r15 = r15.equals(r3);
        if (r15 != 0) goto L_0x00d3;
    L_0x00d1:
        r15 = 1;
        goto L_0x00d4;
    L_0x00d3:
        r15 = 0;
    L_0x00d4:
        r13 = r1.characteristics;
        r18 = r5;
        r5 = android.hardware.camera2.CameraCharacteristics.SENSOR_INFO_EXPOSURE_TIME_RANGE;
        r5 = r13.get(r5);
        r5 = (android.util.Range) r5;
        if (r3 == 0) goto L_0x00ec;
    L_0x00e2:
        r3 = EMPTY_RANGE;
        r3 = r3.equals(r5);
        if (r3 != 0) goto L_0x00ec;
    L_0x00ea:
        r3 = 1;
        goto L_0x00ed;
    L_0x00ec:
        r3 = 0;
    L_0x00ed:
        r5 = r1.characteristics;
        r13 = android.hardware.camera2.CameraCharacteristics.CONTROL_AVAILABLE_VIDEO_STABILIZATION_MODES;
        r5 = r5.get(r13);
        r5 = (int[]) r5;
        r13 = r1.characteristics;
        r19 = r2;
        r2 = android.hardware.camera2.CameraCharacteristics.LENS_INFO_AVAILABLE_OPTICAL_STABILIZATION;
        r2 = r13.get(r2);
        r2 = (int[]) r2;
        if (r5 == 0) goto L_0x010a;
    L_0x0105:
        r5 = r5.length;
        r13 = 1;
        if (r5 > r13) goto L_0x0110;
    L_0x0109:
        goto L_0x010b;
    L_0x010a:
        r13 = 1;
    L_0x010b:
        if (r2 == 0) goto L_0x0112;
    L_0x010d:
        r2 = r2.length;
        if (r2 <= r13) goto L_0x0112;
    L_0x0110:
        r2 = 1;
        goto L_0x0113;
    L_0x0112:
        r2 = 0;
    L_0x0113:
        r5 = r9 * 2;
        r13 = 6;
        r5 = r5 + r13;
        r5 = r5 + r10;
        r5 = r5 + r11;
        r5 = new int[r5];
        r1.caps = r5;
        r5 = r1.caps;
        r13 = 536870912; // 0x20000000 float:1.0842022E-19 double:2.652494739E-315;
        r20 = r11;
        r11 = 0;
        r5[r11] = r13;
        r5 = r14.booleanValue();
        r13 = 536870913; // 0x20000001 float:1.0842023E-19 double:2.652494744E-315;
        r0.setCameraCapsBit(r1, r11, r13, r5);
        r5 = r6.length;
        if (r5 <= 0) goto L_0x0135;
    L_0x0133:
        r5 = 1;
        goto L_0x0136;
    L_0x0135:
        r5 = 0;
    L_0x0136:
        r13 = 536870914; // 0x20000002 float:1.0842024E-19 double:2.65249475E-315;
        r0.setCameraCapsBit(r1, r11, r13, r5);
        r5 = 536870916; // 0x20000004 float:1.0842027E-19 double:2.65249476E-315;
        r0.setCameraCapsBit(r1, r11, r5, r12);
        r5 = 536870928; // 0x20000010 float:1.0842042E-19 double:2.65249482E-315;
        r0.setCameraCapsBit(r1, r11, r5, r4);
        r5 = r7.length;
        if (r5 <= 0) goto L_0x014d;
    L_0x014b:
        r5 = 1;
        goto L_0x014e;
    L_0x014d:
        r5 = 0;
    L_0x014e:
        r13 = 536870944; // 0x20000020 float:1.0842063E-19 double:2.652494897E-315;
        r0.setCameraCapsBit(r1, r11, r13, r5);
        r5 = 536871936; // 0x20000400 float:1.0843345E-19 double:2.6524998E-315;
        r0.setCameraCapsBit(r1, r11, r5, r8);
        r5 = 536872960; // 0x20000800 float:1.0844669E-19 double:2.652504857E-315;
        r0.setCameraCapsBit(r1, r11, r5, r8);
        r5 = 536870976; // 0x20000040 float:1.0842104E-19 double:2.652495055E-315;
        r0.setCameraCapsBit(r1, r11, r5, r15);
        r5 = 536871040; // 0x20000080 float:1.0842187E-19 double:2.65249537E-315;
        r0.setCameraCapsBit(r1, r11, r5, r15);
        r5 = 536871168; // 0x20000100 float:1.0842353E-19 double:2.652496004E-315;
        r0.setCameraCapsBit(r1, r11, r5, r3);
        r5 = 536871424; // 0x20000200 float:1.0842683E-19 double:2.65249727E-315;
        r0.setCameraCapsBit(r1, r11, r5, r3);
        r5 = 536903680; // 0x20008000 float:1.0884373E-19 double:2.652656634E-315;
        r0.setCameraCapsBit(r1, r11, r5, r12);
        r5 = 536936448; // 0x20010000 float:1.0926725E-19 double:2.65281853E-315;
        r0.setCameraCapsBit(r1, r11, r5, r12);
        r5 = 553648128; // 0x21000000 float:4.3368087E-19 double:2.7353852E-315;
        r0.setCameraCapsBit(r1, r11, r5, r2);
        r5 = r1.caps;
        r11 = 1;
        r17 = 536870912; // 0x20000000 float:1.0842022E-19 double:2.652494739E-315;
        r5[r11] = r17;
        r5 = r14.booleanValue();
        r13 = 536870913; // 0x20000001 float:1.0842023E-19 double:2.652494744E-315;
        r0.setCameraCapsBit(r1, r11, r13, r5);
        r5 = r6.length;
        if (r5 <= 0) goto L_0x01a1;
    L_0x019c:
        r5 = 1;
    L_0x019d:
        r13 = 536870914; // 0x20000002 float:1.0842024E-19 double:2.65249475E-315;
        goto L_0x01a3;
    L_0x01a1:
        r5 = 0;
        goto L_0x019d;
    L_0x01a3:
        r0.setCameraCapsBit(r1, r11, r13, r5);
        r5 = 536870928; // 0x20000010 float:1.0842042E-19 double:2.65249482E-315;
        r0.setCameraCapsBit(r1, r11, r5, r4);
        r4 = r7.length;
        if (r4 <= 0) goto L_0x01b4;
    L_0x01af:
        r4 = 1;
    L_0x01b0:
        r5 = 536870944; // 0x20000020 float:1.0842063E-19 double:2.652494897E-315;
        goto L_0x01b6;
    L_0x01b4:
        r4 = 0;
        goto L_0x01b0;
    L_0x01b6:
        r0.setCameraCapsBit(r1, r11, r5, r4);
        r4 = 536871936; // 0x20000400 float:1.0843345E-19 double:2.6524998E-315;
        r0.setCameraCapsBit(r1, r11, r4, r8);
        r4 = 536870976; // 0x20000040 float:1.0842104E-19 double:2.652495055E-315;
        r0.setCameraCapsBit(r1, r11, r4, r15);
        r4 = 536871168; // 0x20000100 float:1.0842353E-19 double:2.652496004E-315;
        r0.setCameraCapsBit(r1, r11, r4, r3);
        r3 = 536903680; // 0x20008000 float:1.0884373E-19 double:2.652656634E-315;
        r0.setCameraCapsBit(r1, r11, r3, r12);
        r3 = 553648128; // 0x21000000 float:4.3368087E-19 double:2.7353852E-315;
        r0.setCameraCapsBit(r1, r11, r3, r2);
        r2 = r1.caps;
        r3 = 805306368; // 0x30000000 float:4.656613E-10 double:3.97874211E-315;
        r4 = 2;
        r2[r4] = r3;
        r2 = r14.booleanValue();
        if (r2 == 0) goto L_0x01ef;
    L_0x01e3:
        r2 = 805306369; // 0x30000001 float:4.6566134E-10 double:3.978742113E-315;
        r0.setCameraCapsBit(r1, r4, r2, r11);
        r2 = 805306370; // 0x30000002 float:4.656614E-10 double:3.97874212E-315;
        r0.setCameraCapsBit(r1, r4, r2, r11);
    L_0x01ef:
        r2 = -1;
        if (r6 == 0) goto L_0x0253;
    L_0x01f2:
        r3 = 805306384; // 0x30000010 float:4.656622E-10 double:3.978742187E-315;
        r5 = java.util.Arrays.binarySearch(r6, r11);
        if (r5 == r2) goto L_0x01fd;
    L_0x01fb:
        r5 = 1;
        goto L_0x01fe;
    L_0x01fd:
        r5 = 0;
    L_0x01fe:
        r0.setCameraCapsBit(r1, r4, r3, r5);
        r3 = 805306400; // 0x30000020 float:4.6566306E-10 double:3.978742266E-315;
        r5 = java.util.Arrays.binarySearch(r6, r11);
        if (r5 == r2) goto L_0x020c;
    L_0x020a:
        r5 = 1;
        goto L_0x020d;
    L_0x020c:
        r5 = 0;
    L_0x020d:
        r0.setCameraCapsBit(r1, r4, r3, r5);
        r3 = 805306432; // 0x30000040 float:4.6566484E-10 double:3.978742424E-315;
        r5 = 3;
        r5 = java.util.Arrays.binarySearch(r6, r5);
        if (r5 == r2) goto L_0x021c;
    L_0x021a:
        r5 = 1;
        goto L_0x021d;
    L_0x021c:
        r5 = 0;
    L_0x021d:
        r0.setCameraCapsBit(r1, r4, r3, r5);
        r3 = 805306496; // 0x30000080 float:4.656684E-10 double:3.97874274E-315;
        r5 = java.util.Arrays.binarySearch(r6, r4);
        if (r5 == r2) goto L_0x022b;
    L_0x0229:
        r5 = 1;
        goto L_0x022c;
    L_0x022b:
        r5 = 0;
    L_0x022c:
        r0.setCameraCapsBit(r1, r4, r3, r5);
        r3 = 805306624; // 0x30000100 float:4.656755E-10 double:3.978743373E-315;
        r5 = 0;
        r8 = java.util.Arrays.binarySearch(r6, r5);
        if (r8 == r2) goto L_0x023f;
    L_0x0239:
        r8 = android.hardware.camera2.CaptureRequest.LENS_FOCUS_DISTANCE;
        if (r8 == 0) goto L_0x023f;
    L_0x023d:
        r8 = 1;
        goto L_0x0240;
    L_0x023f:
        r8 = 0;
    L_0x0240:
        r0.setCameraCapsBit(r1, r4, r3, r8);
        r3 = 805306880; // 0x30000200 float:4.656897E-10 double:3.97874464E-315;
        r6 = java.util.Arrays.binarySearch(r6, r5);
        if (r6 == r2) goto L_0x024e;
    L_0x024c:
        r6 = 1;
        goto L_0x024f;
    L_0x024e:
        r6 = 0;
    L_0x024f:
        r0.setCameraCapsBit(r1, r4, r3, r6);
        goto L_0x0254;
    L_0x0253:
        r5 = 0;
    L_0x0254:
        if (r7 == 0) goto L_0x0284;
    L_0x0256:
        r3 = 805310464; // 0x30001000 float:4.6588866E-10 double:3.978762345E-315;
        r6 = java.util.Arrays.binarySearch(r7, r5);
        if (r6 == r2) goto L_0x0261;
    L_0x025f:
        r6 = 1;
        goto L_0x0262;
    L_0x0261:
        r6 = 0;
    L_0x0262:
        r0.setCameraCapsBit(r1, r4, r3, r6);
        r3 = 805339136; // 0x30008000 float:4.674803E-10 double:3.978904003E-315;
        r6 = java.util.Arrays.binarySearch(r7, r5);
        if (r6 == r2) goto L_0x0270;
    L_0x026e:
        r6 = 1;
        goto L_0x0271;
    L_0x0270:
        r6 = 0;
    L_0x0271:
        r0.setCameraCapsBit(r1, r4, r3, r6);
        r3 = 805322752; // 0x30004000 float:4.665708E-10 double:3.978823056E-315;
        r6 = 1;
        r7 = java.util.Arrays.binarySearch(r7, r6);
        if (r7 == r2) goto L_0x0280;
    L_0x027e:
        r2 = 1;
        goto L_0x0281;
    L_0x0280:
        r2 = 0;
    L_0x0281:
        r0.setCameraCapsBit(r1, r4, r3, r2);
    L_0x0284:
        r2 = r1.caps;
        r3 = 3;
        r2[r3] = r9;
        r2 = r1.caps;
        r3 = 4;
        r2[r3] = r10;
        r2 = r1.caps;
        r3 = 5;
        r2[r3] = r20;
        if (r9 <= 0) goto L_0x02b6;
    L_0x0295:
        r2 = r19;
        r3 = r2.length;
        r4 = 0;
        r16 = 6;
    L_0x029b:
        if (r4 >= r3) goto L_0x02b8;
    L_0x029d:
        r6 = r2[r4];
        r7 = r1.caps;
        r8 = r6.getWidth();
        r7[r16] = r8;
        r7 = r1.caps;
        r8 = r16 + 1;
        r6 = r6.getHeight();
        r7[r8] = r6;
        r16 = r16 + 2;
        r4 = r4 + 1;
        goto L_0x029b;
    L_0x02b6:
        r16 = 6;
    L_0x02b8:
        if (r10 <= 0) goto L_0x02d5;
    L_0x02ba:
        r2 = r18.iterator();
    L_0x02be:
        r3 = r2.hasNext();
        if (r3 == 0) goto L_0x02d5;
    L_0x02c4:
        r3 = r2.next();
        r3 = (java.lang.Integer) r3;
        r4 = r1.caps;
        r3 = r3.intValue();
        r4[r16] = r3;
        r16 = r16 + 1;
        goto L_0x02be;
    L_0x02d5:
        r2 = CAMERA_VALID_IMAGE_FORMAT_PL;
        r3 = r2.length;
    L_0x02d8:
        if (r5 >= r3) goto L_0x02ee;
    L_0x02da:
        r4 = r2[r5];
        r4 = java.lang.Integer.valueOf(r4);
        r6 = r1.caps;
        r4 = r4.intValue();
        r6[r16] = r4;
        r4 = 1;
        r16 = r16 + 1;
        r5 = r5 + 1;
        goto L_0x02d8;
    L_0x02ee:
        r1 = r1.caps;
        return r1;
    L_0x02f1:
        r1 = 6;
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r1);
        return r2;
    L_0x02f6:
        r1 = 6;
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r1);
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.Camera2_Preview.getCameraCapabilities(int):int[]");
    }

    public boolean setCaptureInfo(int i, int[] iArr, int[] iArr2) {
        i = getCameraCacheInfo(i);
        if (i == 0) {
            SystemTools.setSystemErrorCode(4);
            return false;
        } else if (iArr.length != 5) {
            SystemTools.setSystemErrorCode(2);
            return false;
        } else if (setCameraCaptureParams(i, iArr, iArr2) != 0) {
            return true;
        } else {
            SystemTools.setSystemErrorCode(6);
            return false;
        }
    }

    public int[] getCaptureInfo(int r7) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/345281752.run(Unknown Source)
*/
        /*
        r6 = this;
        r7 = r6.getCameraCacheInfo(r7);
        r0 = 0;
        r1 = 4;
        if (r7 != 0) goto L_0x000c;
    L_0x0008:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r1);
        return r0;
    L_0x000c:
        r2 = 5;
        r2 = new int[r2];	 Catch:{ Exception -> 0x0056 }
        r3 = r7.reader;	 Catch:{ Exception -> 0x0056 }
        r4 = 0;	 Catch:{ Exception -> 0x0056 }
        r5 = 1;	 Catch:{ Exception -> 0x0056 }
        if (r3 == 0) goto L_0x0026;	 Catch:{ Exception -> 0x0056 }
    L_0x0015:
        r3 = r7.reader;	 Catch:{ Exception -> 0x0056 }
        r3 = r3.getWidth();	 Catch:{ Exception -> 0x0056 }
        r2[r4] = r3;	 Catch:{ Exception -> 0x0056 }
        r3 = r7.reader;	 Catch:{ Exception -> 0x0056 }
        r3 = r3.getHeight();	 Catch:{ Exception -> 0x0056 }
        r2[r5] = r3;	 Catch:{ Exception -> 0x0056 }
        goto L_0x002e;	 Catch:{ Exception -> 0x0056 }
    L_0x0026:
        r3 = r7.requestWidth;	 Catch:{ Exception -> 0x0056 }
        r2[r4] = r3;	 Catch:{ Exception -> 0x0056 }
        r3 = r7.requestHeight;	 Catch:{ Exception -> 0x0056 }
        r2[r5] = r3;	 Catch:{ Exception -> 0x0056 }
    L_0x002e:
        r3 = 2;	 Catch:{ Exception -> 0x0056 }
        r4 = r7.requestFormatPL;	 Catch:{ Exception -> 0x0056 }
        r2[r3] = r4;	 Catch:{ Exception -> 0x0056 }
        r3 = r7.builder;	 Catch:{ Exception -> 0x0056 }
        r4 = 3;	 Catch:{ Exception -> 0x0056 }
        if (r3 == 0) goto L_0x004f;	 Catch:{ Exception -> 0x0056 }
    L_0x0038:
        r7 = r7.builder;	 Catch:{ Exception -> 0x0056 }
        r3 = android.hardware.camera2.CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE;	 Catch:{ Exception -> 0x0056 }
        r7 = r7.get(r3);	 Catch:{ Exception -> 0x0056 }
        r7 = (android.util.Range) r7;	 Catch:{ Exception -> 0x0056 }
        r7 = r7.getUpper();	 Catch:{ Exception -> 0x0056 }
        r7 = (java.lang.Integer) r7;	 Catch:{ Exception -> 0x0056 }
        r7 = r7.intValue();	 Catch:{ Exception -> 0x0056 }
        r2[r4] = r7;	 Catch:{ Exception -> 0x0056 }
        goto L_0x0053;	 Catch:{ Exception -> 0x0056 }
    L_0x004f:
        r7 = r7.requestFramerate;	 Catch:{ Exception -> 0x0056 }
        r2[r4] = r7;	 Catch:{ Exception -> 0x0056 }
    L_0x0053:
        r2[r1] = r5;	 Catch:{ Exception -> 0x0056 }
        return r2;
    L_0x0056:
        r7 = 6;
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r7);
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.Camera2_Preview.getCaptureInfo(int):int[]");
    }

    public boolean start(int r8) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/345281752.run(Unknown Source)
*/
        /*
        r7 = this;
        r0 = r7.getCameraCacheInfo(r8);
        r1 = 0;
        if (r0 != 0) goto L_0x000c;
    L_0x0007:
        r8 = 4;
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r8);
        return r1;
    L_0x000c:
        r2 = r7.setupPreviewBuffer(r0);
        r3 = 6;
        if (r2 != 0) goto L_0x0017;
    L_0x0013:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r3);
        return r1;
    L_0x0017:
        r2 = r0.session;	 Catch:{ Exception -> 0x0064 }
        if (r2 != 0) goto L_0x0040;	 Catch:{ Exception -> 0x0064 }
    L_0x001b:
        r2 = r7.mOpenCloseSemaphore;	 Catch:{ Exception -> 0x0064 }
        r2.acquire();	 Catch:{ Exception -> 0x0064 }
        r2 = r0.device;	 Catch:{ Exception -> 0x0064 }
        r4 = r0.surfaces;	 Catch:{ Exception -> 0x0064 }
        r5 = new com.vuforia.ar.pl.Camera2_Preview$2;	 Catch:{ Exception -> 0x0064 }
        r5.<init>();	 Catch:{ Exception -> 0x0064 }
        r6 = r0.handler;	 Catch:{ Exception -> 0x0064 }
        r2.createCaptureSession(r4, r5, r6);	 Catch:{ Exception -> 0x0064 }
        r2 = r7.mOpenCloseSemaphore;	 Catch:{ Exception -> 0x0064 }
        r2.acquire();	 Catch:{ Exception -> 0x0064 }
        r2 = r7.mOpenCloseSemaphore;	 Catch:{ Exception -> 0x0064 }
        r2.release();	 Catch:{ Exception -> 0x0064 }
        r2 = r0.session;	 Catch:{ Exception -> 0x0064 }
        if (r2 != 0) goto L_0x0040;	 Catch:{ Exception -> 0x0064 }
    L_0x003c:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r3);	 Catch:{ Exception -> 0x0064 }
        return r1;	 Catch:{ Exception -> 0x0064 }
    L_0x0040:
        r2 = r0.session;	 Catch:{ Exception -> 0x0064 }
        r4 = r0.builder;	 Catch:{ Exception -> 0x0064 }
        r4 = r4.build();	 Catch:{ Exception -> 0x0064 }
        r5 = new com.vuforia.ar.pl.Camera2_Preview$OnFrameCapturedCallback;	 Catch:{ Exception -> 0x0064 }
        r5.<init>(r0);	 Catch:{ Exception -> 0x0064 }
        r6 = r0.handler;	 Catch:{ Exception -> 0x0064 }
        r2.setRepeatingRequest(r4, r5, r6);	 Catch:{ Exception -> 0x0064 }
        r2 = 268443651; // 0x10002003 float:2.526821E-29 double:1.32628786E-315;	 Catch:{ Exception -> 0x0064 }
        r0.status = r2;	 Catch:{ Exception -> 0x0064 }
        r2 = r7.mCameraCacheInfoIndexCache;	 Catch:{ Exception -> 0x0064 }
        r0 = r0.reader;	 Catch:{ Exception -> 0x0064 }
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ Exception -> 0x0064 }
        r2.put(r0, r8);	 Catch:{ Exception -> 0x0064 }
        r8 = 1;
        return r8;
    L_0x0064:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r3);
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.Camera2_Preview.start(int):boolean");
    }

    public boolean stop(int r3) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/345281752.run(Unknown Source)
*/
        /*
        r2 = this;
        r3 = r2.getCameraCacheInfo(r3);
        r0 = 0;
        if (r3 != 0) goto L_0x000c;
    L_0x0007:
        r3 = 4;
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r3);
        return r0;
    L_0x000c:
        r1 = r3.session;	 Catch:{ Exception -> 0x0018 }
        r1.abortCaptures();	 Catch:{ Exception -> 0x0018 }
        r1 = 268443650; // 0x10002002 float:2.5268207E-29 double:1.326287853E-315;	 Catch:{ Exception -> 0x0018 }
        r3.status = r1;	 Catch:{ Exception -> 0x0018 }
        r3 = 1;
        return r3;
    L_0x0018:
        r3 = 6;
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r3);
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.Camera2_Preview.stop(int):boolean");
    }

    public boolean setBatchParameters(int i, String str) {
        if (str == null) {
            return false;
        }
        i = getCameraCacheInfo(i);
        if (i != 0) {
            if (i.builder != null) {
                if (setCustomCameraParams(i, str) == 0) {
                    return false;
                }
                return true;
            }
        }
        SystemTools.setSystemErrorCode(4);
        return false;
    }

    boolean setUntypedCameraParameter(int r7, java.lang.String r8, java.lang.Object r9) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/345281752.run(Unknown Source)
*/
        /*
        r6 = this;
        r7 = r6.getCameraCacheInfo(r7);
        r0 = 0;
        if (r7 == 0) goto L_0x00ab;
    L_0x0007:
        r1 = r7.builder;
        if (r1 == 0) goto L_0x00ab;
    L_0x000b:
        r1 = r7.characteristics;
        if (r1 == 0) goto L_0x00ab;
    L_0x000f:
        if (r8 == 0) goto L_0x00ab;
    L_0x0011:
        if (r9 != 0) goto L_0x0015;
    L_0x0013:
        goto L_0x00ab;
    L_0x0015:
        r1 = r7.characteristics;
        r1 = r1.getAvailableCaptureRequestKeys();
        r2 = 0;
    L_0x001c:
        r3 = r1.size();
        r4 = 6;
        if (r2 >= r3) goto L_0x00a7;
    L_0x0023:
        r3 = r1.get(r2);
        r3 = (android.hardware.camera2.CaptureRequest.Key) r3;
        r5 = r3.getName();
        r5 = r5.equals(r8);
        if (r5 != 0) goto L_0x0036;
    L_0x0033:
        r2 = r2 + 1;
        goto L_0x001c;
    L_0x0036:
        r8 = r7.builder;
        r8 = r8.get(r3);
        r1 = r8 instanceof java.lang.Integer;
        if (r1 != 0) goto L_0x0051;
    L_0x0040:
        r2 = r8 instanceof java.lang.Float;
        if (r2 != 0) goto L_0x0051;
    L_0x0044:
        r2 = r8 instanceof java.lang.Boolean;
        if (r2 != 0) goto L_0x0051;
    L_0x0048:
        r2 = r8 instanceof java.lang.Byte;
        if (r2 != 0) goto L_0x0051;
    L_0x004c:
        r2 = r8 instanceof java.lang.Long;
        if (r2 != 0) goto L_0x0051;
    L_0x0050:
        return r0;
    L_0x0051:
        r2 = r8 instanceof java.lang.Byte;
        if (r2 == 0) goto L_0x0065;
    L_0x0055:
        r2 = r9 instanceof java.lang.Long;
        if (r2 == 0) goto L_0x0065;
    L_0x0059:
        r2 = new java.lang.Byte;
        r9 = (java.lang.Long) r9;
        r9 = r9.byteValue();
        r2.<init>(r9);
        r9 = r2;
    L_0x0065:
        if (r1 == 0) goto L_0x0077;
    L_0x0067:
        r1 = r9 instanceof java.lang.Long;
        if (r1 == 0) goto L_0x0077;
    L_0x006b:
        r1 = new java.lang.Integer;
        r9 = (java.lang.Long) r9;
        r9 = r9.intValue();
        r1.<init>(r9);
        r9 = r1;
    L_0x0077:
        r8 = r8.getClass();
        r1 = r9.getClass();
        r8 = r8.equals(r1);
        if (r8 != 0) goto L_0x0086;
    L_0x0085:
        return r0;
    L_0x0086:
        r8 = r7.builder;	 Catch:{ Exception -> 0x00a3 }
        r8.set(r3, r9);	 Catch:{ Exception -> 0x00a3 }
        r8 = r7.session;	 Catch:{ Exception -> 0x00a3 }
        if (r8 == 0) goto L_0x00a1;	 Catch:{ Exception -> 0x00a3 }
    L_0x008f:
        r8 = r7.session;	 Catch:{ Exception -> 0x00a3 }
        r9 = r7.builder;	 Catch:{ Exception -> 0x00a3 }
        r9 = r9.build();	 Catch:{ Exception -> 0x00a3 }
        r1 = new com.vuforia.ar.pl.Camera2_Preview$OnFrameCapturedCallback;	 Catch:{ Exception -> 0x00a3 }
        r1.<init>(r7);	 Catch:{ Exception -> 0x00a3 }
        r7 = r7.handler;	 Catch:{ Exception -> 0x00a3 }
        r8.setRepeatingRequest(r9, r1, r7);	 Catch:{ Exception -> 0x00a3 }
    L_0x00a1:
        r7 = 1;
        return r7;
    L_0x00a3:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r4);
        return r0;
    L_0x00a7:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r4);
        return r0;
    L_0x00ab:
        r7 = 4;
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r7);
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.Camera2_Preview.setUntypedCameraParameter(int, java.lang.String, java.lang.Object):boolean");
    }

    Object getUntypedCameraParameter(int i, String str) {
        i = getCameraCacheInfo(i);
        if (!(i == 0 || i.builder == null || i.characteristics == null)) {
            if (str != null) {
                Object obj;
                List availableCaptureRequestKeys = i.characteristics.getAvailableCaptureRequestKeys();
                for (int i2 = 0; i2 < availableCaptureRequestKeys.size(); i2++) {
                    Key key = (Key) availableCaptureRequestKeys.get(i2);
                    if (key.getName().equals(str)) {
                        obj = i.builder.get(key);
                        break;
                    }
                }
                obj = null;
                List keys = i.characteristics.getKeys();
                for (int i3 = 0; i3 < keys.size(); i3++) {
                    CameraCharacteristics.Key key2 = (CameraCharacteristics.Key) keys.get(i3);
                    if (key2.getName().equals(str)) {
                        obj = i.characteristics.get(key2);
                        break;
                    }
                }
                if (obj == null) {
                    SystemTools.setSystemErrorCode(6);
                    return null;
                }
                if ((obj instanceof Long) == null && (obj instanceof Float) == null && (obj instanceof Boolean) == null) {
                    if ((obj instanceof String) == null) {
                        if ((obj instanceof Integer) != null) {
                            return new Long(((Integer) obj).longValue());
                        }
                        if ((obj instanceof Byte) != null) {
                            return new Long(((Byte) obj).longValue());
                        }
                        if ((obj instanceof Range) != null) {
                            Range range = (Range) obj;
                            str = range.getLower();
                            Comparable upper = range.getUpper();
                            if (str instanceof Integer) {
                                return new long[]{((Integer) str).longValue(), ((Integer) upper).longValue()};
                            } else if (str instanceof Long) {
                                return new long[]{((Long) str).longValue(), ((Long) upper).longValue()};
                            } else {
                                SystemTools.setSystemErrorCode(6);
                                return null;
                            }
                        }
                        SystemTools.setSystemErrorCode(6);
                        return null;
                    }
                }
                return obj;
            }
        }
        SystemTools.setSystemErrorCode(4);
        return null;
    }

    int getUntypedCameraParameterType(int i, String str) {
        i = getCameraCacheInfo(i);
        if (!(i == 0 || i.builder == null || i.characteristics == null)) {
            if (str != null) {
                Object obj;
                Object obj2 = null;
                List availableCaptureRequestKeys = i.characteristics.getAvailableCaptureRequestKeys();
                for (int i2 = 0; i2 < availableCaptureRequestKeys.size(); i2++) {
                    Key key = (Key) availableCaptureRequestKeys.get(i2);
                    if (key.getName().equals(str)) {
                        obj2 = i.builder.get(key);
                        obj = 1;
                        break;
                    }
                }
                obj = null;
                List keys = i.characteristics.getKeys();
                for (int i3 = 0; i3 < keys.size(); i3++) {
                    CameraCharacteristics.Key key2 = (CameraCharacteristics.Key) keys.get(i3);
                    if (key2.getName().equals(str)) {
                        obj2 = i.characteristics.get(key2);
                        obj = 1;
                        break;
                    }
                }
                if (obj == null) {
                    SystemTools.setSystemErrorCode(6);
                    return -1;
                } else if (obj2 == null) {
                    return -1;
                } else {
                    if ((obj2 instanceof Integer) != 0 || (obj2 instanceof Byte) != 0 || (obj2 instanceof Long) != 0) {
                        return 1;
                    }
                    if ((obj2 instanceof Float) != 0) {
                        return 2;
                    }
                    if ((obj2 instanceof Boolean) != 0) {
                        return 3;
                    }
                    if ((obj2 instanceof String) != 0) {
                        return 0;
                    }
                    if ((obj2 instanceof Range) != 0) {
                        i = ((Range) obj2).getLower();
                        return ((i instanceof Integer) == null && (i instanceof Long) == 0) ? -1 : 4;
                    }
                }
            }
        }
        SystemTools.setSystemErrorCode(4);
        return -1;
    }

    int getNamedParameterCount(int i) {
        i = getCameraCacheInfo(i);
        if (!(i == 0 || i.builder == null)) {
            if (i.characteristics != null) {
                return i.characteristics.getAvailableCaptureRequestKeys().size() + i.characteristics.getKeys().size();
            }
        }
        SystemTools.setSystemErrorCode(4);
        return -1;
    }

    String getNamedParameter(int i, int i2) {
        i = getCameraCacheInfo(i);
        if (!(i == 0 || i.builder == null)) {
            if (i.characteristics != null) {
                List availableCaptureRequestKeys = i.characteristics.getAvailableCaptureRequestKeys();
                i = i.characteristics.getKeys();
                if (i2 < availableCaptureRequestKeys.size()) {
                    Key key = (Key) availableCaptureRequestKeys.get(i2);
                    if (key == null) {
                        return null;
                    }
                    return key.getName();
                } else if (i2 - availableCaptureRequestKeys.size() < i.size()) {
                    CameraCharacteristics.Key key2 = (CameraCharacteristics.Key) i.get(i2 - availableCaptureRequestKeys.size());
                    if (key2 == null) {
                        return null;
                    }
                    return key2.getName();
                } else {
                    SystemTools.setSystemErrorCode(6);
                    return null;
                }
            }
        }
        SystemTools.setSystemErrorCode(4);
        return null;
    }

    boolean setTypedCameraParameter(int r17, int r18, java.lang.Object r19) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/345281752.run(Unknown Source)
*/
        /*
        r16 = this;
        r0 = r16;
        r1 = r18;
        r3 = r16.getCameraCacheInfo(r17);
        r4 = 4;
        r5 = 0;
        if (r3 == 0) goto L_0x0579;
    L_0x000c:
        r6 = r3.builder;
        if (r6 == 0) goto L_0x0579;
    L_0x0010:
        r6 = r3.characteristics;
        if (r6 != 0) goto L_0x0016;
    L_0x0014:
        goto L_0x0579;
    L_0x0016:
        r6 = 0;
        r7 = 2;
        r8 = 3;
        r9 = 1;
        r10 = 6;
        switch(r1) {
            case 536870913: goto L_0x049f;
            case 536870914: goto L_0x03e2;
            case 536870916: goto L_0x03c3;
            case 536870920: goto L_0x03bd;
            case 536870928: goto L_0x02d0;
            case 536870944: goto L_0x0256;
            case 536870976: goto L_0x021e;
            case 536871040: goto L_0x021a;
            case 536871168: goto L_0x01d7;
            case 536871424: goto L_0x01d3;
            case 536871936: goto L_0x017c;
            case 536872960: goto L_0x0178;
            case 536875008: goto L_0x011f;
            case 536879104: goto L_0x011b;
            case 536887296: goto L_0x0117;
            case 536903680: goto L_0x00cf;
            case 536936448: goto L_0x00cb;
            case 537001984: goto L_0x00c7;
            case 537133056: goto L_0x00c3;
            case 537395200: goto L_0x00bf;
            case 537919488: goto L_0x00bb;
            case 538968064: goto L_0x00b7;
            case 541065216: goto L_0x0096;
            case 553648128: goto L_0x0020;
            default: goto L_0x001e;
        };
    L_0x001e:
        r2 = 0;
        return r2;
    L_0x0020:
        r4 = r3.characteristics;	 Catch:{ Exception -> 0x049b }
        r6 = android.hardware.camera2.CameraCharacteristics.LENS_INFO_AVAILABLE_OPTICAL_STABILIZATION;	 Catch:{ Exception -> 0x049b }
        r4 = r4.get(r6);	 Catch:{ Exception -> 0x049b }
        r4 = (int[]) r4;	 Catch:{ Exception -> 0x049b }
        if (r4 == 0) goto L_0x0035;	 Catch:{ Exception -> 0x049b }
    L_0x002c:
        r4 = r4.length;	 Catch:{ Exception -> 0x049b }
        if (r4 <= r9) goto L_0x0035;	 Catch:{ Exception -> 0x049b }
    L_0x002f:
        r4 = android.hardware.camera2.CaptureRequest.LENS_OPTICAL_STABILIZATION_MODE;	 Catch:{ Exception -> 0x049b }
        if (r4 == 0) goto L_0x0035;	 Catch:{ Exception -> 0x049b }
    L_0x0033:
        r4 = 1;	 Catch:{ Exception -> 0x049b }
        goto L_0x0036;	 Catch:{ Exception -> 0x049b }
    L_0x0035:
        r4 = 0;	 Catch:{ Exception -> 0x049b }
    L_0x0036:
        r6 = r3.characteristics;	 Catch:{ Exception -> 0x049b }
        r7 = android.hardware.camera2.CameraCharacteristics.CONTROL_AVAILABLE_VIDEO_STABILIZATION_MODES;	 Catch:{ Exception -> 0x049b }
        r6 = r6.get(r7);	 Catch:{ Exception -> 0x049b }
        r6 = (int[]) r6;	 Catch:{ Exception -> 0x049b }
        if (r6 == 0) goto L_0x004b;	 Catch:{ Exception -> 0x049b }
    L_0x0042:
        r6 = r6.length;	 Catch:{ Exception -> 0x049b }
        if (r6 <= r9) goto L_0x004b;	 Catch:{ Exception -> 0x049b }
    L_0x0045:
        r6 = android.hardware.camera2.CaptureRequest.CONTROL_VIDEO_STABILIZATION_MODE;	 Catch:{ Exception -> 0x049b }
        if (r6 == 0) goto L_0x004b;	 Catch:{ Exception -> 0x049b }
    L_0x0049:
        r6 = 1;	 Catch:{ Exception -> 0x049b }
        goto L_0x004c;	 Catch:{ Exception -> 0x049b }
    L_0x004b:
        r6 = 0;	 Catch:{ Exception -> 0x049b }
    L_0x004c:
        if (r4 != 0) goto L_0x0054;	 Catch:{ Exception -> 0x049b }
    L_0x004e:
        if (r6 != 0) goto L_0x0054;	 Catch:{ Exception -> 0x049b }
    L_0x0050:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10);	 Catch:{ Exception -> 0x049b }
        return r5;	 Catch:{ Exception -> 0x049b }
    L_0x0054:
        r2 = r19;	 Catch:{ Exception -> 0x049b }
        r2 = (java.lang.Boolean) r2;	 Catch:{ Exception -> 0x049b }
        r2 = r2.booleanValue();	 Catch:{ Exception -> 0x049b }
        if (r4 == 0) goto L_0x0069;	 Catch:{ Exception -> 0x049b }
    L_0x005e:
        r7 = r3.builder;	 Catch:{ Exception -> 0x049b }
        r8 = android.hardware.camera2.CaptureRequest.LENS_OPTICAL_STABILIZATION_MODE;	 Catch:{ Exception -> 0x049b }
        r11 = java.lang.Integer.valueOf(r5);	 Catch:{ Exception -> 0x049b }
        r7.set(r8, r11);	 Catch:{ Exception -> 0x049b }
    L_0x0069:
        if (r6 == 0) goto L_0x0076;	 Catch:{ Exception -> 0x049b }
    L_0x006b:
        r7 = r3.builder;	 Catch:{ Exception -> 0x049b }
        r8 = android.hardware.camera2.CaptureRequest.CONTROL_VIDEO_STABILIZATION_MODE;	 Catch:{ Exception -> 0x049b }
        r11 = java.lang.Integer.valueOf(r5);	 Catch:{ Exception -> 0x049b }
        r7.set(r8, r11);	 Catch:{ Exception -> 0x049b }
    L_0x0076:
        if (r2 == 0) goto L_0x047f;	 Catch:{ Exception -> 0x049b }
    L_0x0078:
        if (r4 == 0) goto L_0x0087;	 Catch:{ Exception -> 0x049b }
    L_0x007a:
        r2 = r3.builder;	 Catch:{ Exception -> 0x049b }
        r4 = android.hardware.camera2.CaptureRequest.LENS_OPTICAL_STABILIZATION_MODE;	 Catch:{ Exception -> 0x049b }
        r6 = java.lang.Integer.valueOf(r9);	 Catch:{ Exception -> 0x049b }
        r2.set(r4, r6);	 Catch:{ Exception -> 0x049b }
        goto L_0x047f;	 Catch:{ Exception -> 0x049b }
    L_0x0087:
        if (r6 == 0) goto L_0x047f;	 Catch:{ Exception -> 0x049b }
    L_0x0089:
        r2 = r3.builder;	 Catch:{ Exception -> 0x049b }
        r4 = android.hardware.camera2.CaptureRequest.CONTROL_VIDEO_STABILIZATION_MODE;	 Catch:{ Exception -> 0x049b }
        r6 = java.lang.Integer.valueOf(r9);	 Catch:{ Exception -> 0x049b }
        r2.set(r4, r6);	 Catch:{ Exception -> 0x049b }
        goto L_0x047f;	 Catch:{ Exception -> 0x049b }
    L_0x0096:
        r4 = android.hardware.camera2.CaptureRequest.CONTROL_CAPTURE_INTENT;	 Catch:{ Exception -> 0x049b }
        if (r4 != 0) goto L_0x009e;	 Catch:{ Exception -> 0x049b }
    L_0x009a:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10);	 Catch:{ Exception -> 0x049b }
        return r5;	 Catch:{ Exception -> 0x049b }
    L_0x009e:
        r2 = r19;	 Catch:{ Exception -> 0x049b }
        r2 = (java.lang.Number) r2;	 Catch:{ Exception -> 0x049b }
        r2 = r2.intValue();	 Catch:{ Exception -> 0x049b }
        r4 = r3.builder;	 Catch:{ Exception -> 0x049b }
        r6 = android.hardware.camera2.CaptureRequest.CONTROL_CAPTURE_INTENT;	 Catch:{ Exception -> 0x049b }
        if (r2 == 0) goto L_0x00ad;	 Catch:{ Exception -> 0x049b }
    L_0x00ac:
        goto L_0x00ae;	 Catch:{ Exception -> 0x049b }
    L_0x00ad:
        r8 = 1;	 Catch:{ Exception -> 0x049b }
    L_0x00ae:
        r2 = java.lang.Integer.valueOf(r8);	 Catch:{ Exception -> 0x049b }
        r4.set(r6, r2);	 Catch:{ Exception -> 0x049b }
        goto L_0x047f;	 Catch:{ Exception -> 0x049b }
    L_0x00b7:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10);	 Catch:{ Exception -> 0x049b }
        return r5;	 Catch:{ Exception -> 0x049b }
    L_0x00bb:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10);	 Catch:{ Exception -> 0x049b }
        return r5;	 Catch:{ Exception -> 0x049b }
    L_0x00bf:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10);	 Catch:{ Exception -> 0x049b }
        return r5;	 Catch:{ Exception -> 0x049b }
    L_0x00c3:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10);	 Catch:{ Exception -> 0x049b }
        return r5;	 Catch:{ Exception -> 0x049b }
    L_0x00c7:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10);	 Catch:{ Exception -> 0x049b }
        return r5;	 Catch:{ Exception -> 0x049b }
    L_0x00cb:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10);	 Catch:{ Exception -> 0x049b }
        return r5;	 Catch:{ Exception -> 0x049b }
    L_0x00cf:
        r2 = r19;	 Catch:{ Exception -> 0x049b }
        r2 = (java.lang.Number) r2;	 Catch:{ Exception -> 0x049b }
        r2 = r2.intValue();	 Catch:{ Exception -> 0x049b }
        r4 = r3.characteristics;	 Catch:{ Exception -> 0x049b }
        r6 = android.hardware.camera2.CameraCharacteristics.LENS_INFO_AVAILABLE_FOCAL_LENGTHS;	 Catch:{ Exception -> 0x049b }
        r4 = r4.get(r6);	 Catch:{ Exception -> 0x049b }
        r4 = (float[]) r4;	 Catch:{ Exception -> 0x049b }
        if (r4 == 0) goto L_0x0113;	 Catch:{ Exception -> 0x049b }
    L_0x00e3:
        r6 = android.hardware.camera2.CaptureRequest.LENS_FOCAL_LENGTH;	 Catch:{ Exception -> 0x049b }
        if (r6 != 0) goto L_0x00e8;	 Catch:{ Exception -> 0x049b }
    L_0x00e7:
        goto L_0x0113;	 Catch:{ Exception -> 0x049b }
    L_0x00e8:
        r6 = r4.length;	 Catch:{ Exception -> 0x049b }
        r8 = 0;	 Catch:{ Exception -> 0x049b }
    L_0x00ea:
        if (r8 >= r6) goto L_0x010c;	 Catch:{ Exception -> 0x049b }
    L_0x00ec:
        r11 = r4[r8];	 Catch:{ Exception -> 0x049b }
        r12 = (float) r2;	 Catch:{ Exception -> 0x049b }
        r12 = r11 - r12;	 Catch:{ Exception -> 0x049b }
        r12 = java.lang.Math.abs(r12);	 Catch:{ Exception -> 0x049b }
        r13 = 1008981770; // 0x3c23d70a float:0.01 double:4.9850323E-315;	 Catch:{ Exception -> 0x049b }
        r12 = (r12 > r13 ? 1 : (r12 == r13 ? 0 : -1));	 Catch:{ Exception -> 0x049b }
        if (r12 >= 0) goto L_0x0109;	 Catch:{ Exception -> 0x049b }
    L_0x00fc:
        r2 = r3.builder;	 Catch:{ Exception -> 0x049b }
        r4 = android.hardware.camera2.CaptureRequest.LENS_FOCAL_LENGTH;	 Catch:{ Exception -> 0x049b }
        r6 = java.lang.Float.valueOf(r11);	 Catch:{ Exception -> 0x049b }
        r2.set(r4, r6);	 Catch:{ Exception -> 0x049b }
        r2 = 1;	 Catch:{ Exception -> 0x049b }
        goto L_0x010d;	 Catch:{ Exception -> 0x049b }
    L_0x0109:
        r8 = r8 + 1;	 Catch:{ Exception -> 0x049b }
        goto L_0x00ea;	 Catch:{ Exception -> 0x049b }
    L_0x010c:
        r2 = 0;	 Catch:{ Exception -> 0x049b }
    L_0x010d:
        if (r2 != 0) goto L_0x047f;	 Catch:{ Exception -> 0x049b }
    L_0x010f:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r7);	 Catch:{ Exception -> 0x049b }
        return r5;	 Catch:{ Exception -> 0x049b }
    L_0x0113:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10);	 Catch:{ Exception -> 0x049b }
        return r5;	 Catch:{ Exception -> 0x049b }
    L_0x0117:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10);	 Catch:{ Exception -> 0x049b }
        return r5;	 Catch:{ Exception -> 0x049b }
    L_0x011b:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10);	 Catch:{ Exception -> 0x049b }
        return r5;	 Catch:{ Exception -> 0x049b }
    L_0x011f:
        r2 = r19;	 Catch:{ Exception -> 0x049b }
        r2 = (java.lang.Number) r2;	 Catch:{ Exception -> 0x049b }
        r2 = r2.intValue();	 Catch:{ Exception -> 0x049b }
        r4 = 806354944; // 0x30100000 float:5.2386895E-10 double:3.98392276E-315;	 Catch:{ Exception -> 0x049b }
        if (r2 == r4) goto L_0x0163;	 Catch:{ Exception -> 0x049b }
    L_0x012b:
        r4 = 809500672; // 0x30400000 float:6.9849193E-10 double:3.999464723E-315;	 Catch:{ Exception -> 0x049b }
        if (r2 == r4) goto L_0x0133;	 Catch:{ Exception -> 0x049b }
    L_0x012f:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r8);	 Catch:{ Exception -> 0x049b }
        return r5;	 Catch:{ Exception -> 0x049b }
    L_0x0133:
        r2 = android.hardware.camera2.CaptureRequest.CONTROL_AWB_LOCK;	 Catch:{ Exception -> 0x049b }
        if (r2 == 0) goto L_0x0152;	 Catch:{ Exception -> 0x049b }
    L_0x0137:
        r2 = r3.builder;	 Catch:{ Exception -> 0x049b }
        r4 = android.hardware.camera2.CaptureRequest.CONTROL_AWB_LOCK;	 Catch:{ Exception -> 0x049b }
        r2 = r2.get(r4);	 Catch:{ Exception -> 0x049b }
        r2 = (java.lang.Boolean) r2;	 Catch:{ Exception -> 0x049b }
        r2 = r2.booleanValue();	 Catch:{ Exception -> 0x049b }
        if (r2 == 0) goto L_0x0152;	 Catch:{ Exception -> 0x049b }
    L_0x0147:
        r2 = r3.builder;	 Catch:{ Exception -> 0x049b }
        r4 = android.hardware.camera2.CaptureRequest.CONTROL_AWB_LOCK;	 Catch:{ Exception -> 0x049b }
        r6 = java.lang.Boolean.valueOf(r5);	 Catch:{ Exception -> 0x049b }
        r2.set(r4, r6);	 Catch:{ Exception -> 0x049b }
    L_0x0152:
        r2 = android.hardware.camera2.CaptureRequest.CONTROL_AWB_MODE;	 Catch:{ Exception -> 0x049b }
        if (r2 == 0) goto L_0x047f;	 Catch:{ Exception -> 0x049b }
    L_0x0156:
        r2 = r3.builder;	 Catch:{ Exception -> 0x049b }
        r4 = android.hardware.camera2.CaptureRequest.CONTROL_AWB_MODE;	 Catch:{ Exception -> 0x049b }
        r6 = java.lang.Integer.valueOf(r9);	 Catch:{ Exception -> 0x049b }
        r2.set(r4, r6);	 Catch:{ Exception -> 0x049b }
        goto L_0x047f;	 Catch:{ Exception -> 0x049b }
    L_0x0163:
        r2 = android.hardware.camera2.CaptureRequest.CONTROL_AWB_LOCK;	 Catch:{ Exception -> 0x049b }
        if (r2 != 0) goto L_0x016b;	 Catch:{ Exception -> 0x049b }
    L_0x0167:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10);	 Catch:{ Exception -> 0x049b }
        return r5;	 Catch:{ Exception -> 0x049b }
    L_0x016b:
        r2 = r3.builder;	 Catch:{ Exception -> 0x049b }
        r4 = android.hardware.camera2.CaptureRequest.CONTROL_AWB_LOCK;	 Catch:{ Exception -> 0x049b }
        r6 = java.lang.Boolean.valueOf(r9);	 Catch:{ Exception -> 0x049b }
        r2.set(r4, r6);	 Catch:{ Exception -> 0x049b }
        goto L_0x047f;	 Catch:{ Exception -> 0x049b }
    L_0x0178:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10);	 Catch:{ Exception -> 0x049b }
        return r5;	 Catch:{ Exception -> 0x049b }
    L_0x017c:
        r2 = r19;	 Catch:{ Exception -> 0x049b }
        r2 = (java.lang.Number) r2;	 Catch:{ Exception -> 0x049b }
        r2 = r2.floatValue();	 Catch:{ Exception -> 0x049b }
        r4 = r3.characteristics;	 Catch:{ Exception -> 0x049b }
        r6 = android.hardware.camera2.CameraCharacteristics.CONTROL_AE_COMPENSATION_RANGE;	 Catch:{ Exception -> 0x049b }
        r4 = r4.get(r6);	 Catch:{ Exception -> 0x049b }
        r4 = (android.util.Range) r4;	 Catch:{ Exception -> 0x049b }
        r6 = r3.characteristics;	 Catch:{ Exception -> 0x049b }
        r7 = android.hardware.camera2.CameraCharacteristics.CONTROL_AE_COMPENSATION_STEP;	 Catch:{ Exception -> 0x049b }
        r6 = r6.get(r7);	 Catch:{ Exception -> 0x049b }
        r6 = (android.util.Rational) r6;	 Catch:{ Exception -> 0x049b }
        r7 = EMPTY_RANGE;	 Catch:{ Exception -> 0x049b }
        r7 = r7.equals(r4);	 Catch:{ Exception -> 0x049b }
        if (r7 != 0) goto L_0x01cf;	 Catch:{ Exception -> 0x049b }
    L_0x01a0:
        r7 = android.hardware.camera2.CaptureRequest.CONTROL_AE_EXPOSURE_COMPENSATION;	 Catch:{ Exception -> 0x049b }
        if (r7 == 0) goto L_0x01cf;	 Catch:{ Exception -> 0x049b }
    L_0x01a4:
        if (r6 == 0) goto L_0x01cf;	 Catch:{ Exception -> 0x049b }
    L_0x01a6:
        r7 = android.hardware.camera2.CameraCharacteristics.CONTROL_AE_COMPENSATION_STEP;	 Catch:{ Exception -> 0x049b }
        if (r7 != 0) goto L_0x01ab;	 Catch:{ Exception -> 0x049b }
    L_0x01aa:
        goto L_0x01cf;	 Catch:{ Exception -> 0x049b }
    L_0x01ab:
        r6 = r6.floatValue();	 Catch:{ Exception -> 0x049b }
        r2 = r2 / r6;	 Catch:{ Exception -> 0x049b }
        r2 = java.lang.Math.round(r2);	 Catch:{ Exception -> 0x049b }
        r6 = java.lang.Integer.valueOf(r2);	 Catch:{ Exception -> 0x049b }
        r4 = r4.contains(r6);	 Catch:{ Exception -> 0x049b }
        if (r4 == 0) goto L_0x01cb;	 Catch:{ Exception -> 0x049b }
    L_0x01be:
        r4 = r3.builder;	 Catch:{ Exception -> 0x049b }
        r6 = android.hardware.camera2.CaptureRequest.CONTROL_AE_EXPOSURE_COMPENSATION;	 Catch:{ Exception -> 0x049b }
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ Exception -> 0x049b }
        r4.set(r6, r2);	 Catch:{ Exception -> 0x049b }
        goto L_0x047f;	 Catch:{ Exception -> 0x049b }
    L_0x01cb:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10);	 Catch:{ Exception -> 0x049b }
        return r5;	 Catch:{ Exception -> 0x049b }
    L_0x01cf:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10);	 Catch:{ Exception -> 0x049b }
        return r5;	 Catch:{ Exception -> 0x049b }
    L_0x01d3:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10);	 Catch:{ Exception -> 0x049b }
        return r5;	 Catch:{ Exception -> 0x049b }
    L_0x01d7:
        r4 = r3.characteristics;	 Catch:{ Exception -> 0x049b }
        r6 = android.hardware.camera2.CameraCharacteristics.SENSOR_INFO_EXPOSURE_TIME_RANGE;	 Catch:{ Exception -> 0x049b }
        r4 = r4.get(r6);	 Catch:{ Exception -> 0x049b }
        r4 = (android.util.Range) r4;	 Catch:{ Exception -> 0x049b }
        if (r4 == 0) goto L_0x0216;	 Catch:{ Exception -> 0x049b }
    L_0x01e3:
        r6 = android.hardware.camera2.CaptureRequest.SENSOR_EXPOSURE_TIME;	 Catch:{ Exception -> 0x049b }
        if (r6 != 0) goto L_0x01e8;	 Catch:{ Exception -> 0x049b }
    L_0x01e7:
        goto L_0x0216;	 Catch:{ Exception -> 0x049b }
    L_0x01e8:
        r2 = r19;	 Catch:{ Exception -> 0x049b }
        r2 = (java.lang.Number) r2;	 Catch:{ Exception -> 0x049b }
        r2 = r2.floatValue();	 Catch:{ Exception -> 0x049b }
        r6 = (double) r2;
        r11 = 4741671816366391296; // 0x41cdcd6500000000 float:0.0 double:1.0E9;
        java.lang.Double.isNaN(r6);
        r6 = r6 * r11;
        r6 = java.lang.Math.round(r6);	 Catch:{ Exception -> 0x049b }
        r2 = java.lang.Long.valueOf(r6);	 Catch:{ Exception -> 0x049b }
        r4 = r4.contains(r2);	 Catch:{ Exception -> 0x049b }
        if (r4 == 0) goto L_0x0212;	 Catch:{ Exception -> 0x049b }
    L_0x0209:
        r4 = r3.builder;	 Catch:{ Exception -> 0x049b }
        r6 = android.hardware.camera2.CaptureRequest.SENSOR_EXPOSURE_TIME;	 Catch:{ Exception -> 0x049b }
        r4.set(r6, r2);	 Catch:{ Exception -> 0x049b }
        goto L_0x047f;	 Catch:{ Exception -> 0x049b }
    L_0x0212:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10);	 Catch:{ Exception -> 0x049b }
        return r5;	 Catch:{ Exception -> 0x049b }
    L_0x0216:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10);	 Catch:{ Exception -> 0x049b }
        return r5;	 Catch:{ Exception -> 0x049b }
    L_0x021a:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10);	 Catch:{ Exception -> 0x049b }
        return r5;	 Catch:{ Exception -> 0x049b }
    L_0x021e:
        r4 = r3.characteristics;	 Catch:{ Exception -> 0x049b }
        r6 = android.hardware.camera2.CameraCharacteristics.SENSOR_INFO_SENSITIVITY_RANGE;	 Catch:{ Exception -> 0x049b }
        r4 = r4.get(r6);	 Catch:{ Exception -> 0x049b }
        r4 = (android.util.Range) r4;	 Catch:{ Exception -> 0x049b }
        if (r4 == 0) goto L_0x0252;	 Catch:{ Exception -> 0x049b }
    L_0x022a:
        r6 = android.hardware.camera2.CaptureRequest.SENSOR_SENSITIVITY;	 Catch:{ Exception -> 0x049b }
        if (r6 != 0) goto L_0x022f;	 Catch:{ Exception -> 0x049b }
    L_0x022e:
        goto L_0x0252;	 Catch:{ Exception -> 0x049b }
    L_0x022f:
        r2 = r19;	 Catch:{ Exception -> 0x049b }
        r2 = (java.lang.Number) r2;	 Catch:{ Exception -> 0x049b }
        r2 = r2.intValue();	 Catch:{ Exception -> 0x049b }
        r6 = java.lang.Integer.valueOf(r2);	 Catch:{ Exception -> 0x049b }
        r4 = r4.contains(r6);	 Catch:{ Exception -> 0x049b }
        if (r4 == 0) goto L_0x024e;	 Catch:{ Exception -> 0x049b }
    L_0x0241:
        r4 = r3.builder;	 Catch:{ Exception -> 0x049b }
        r6 = android.hardware.camera2.CaptureRequest.SENSOR_SENSITIVITY;	 Catch:{ Exception -> 0x049b }
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ Exception -> 0x049b }
        r4.set(r6, r2);	 Catch:{ Exception -> 0x049b }
        goto L_0x047f;	 Catch:{ Exception -> 0x049b }
    L_0x024e:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10);	 Catch:{ Exception -> 0x049b }
        return r5;	 Catch:{ Exception -> 0x049b }
    L_0x0252:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10);	 Catch:{ Exception -> 0x049b }
        return r5;	 Catch:{ Exception -> 0x049b }
    L_0x0256:
        r2 = r19;	 Catch:{ Exception -> 0x049b }
        r2 = (java.lang.Number) r2;	 Catch:{ Exception -> 0x049b }
        r2 = r2.intValue();	 Catch:{ Exception -> 0x049b }
        r4 = r3.characteristics;	 Catch:{ Exception -> 0x049b }
        r6 = android.hardware.camera2.CameraCharacteristics.CONTROL_AE_AVAILABLE_MODES;	 Catch:{ Exception -> 0x049b }
        r4 = r4.get(r6);	 Catch:{ Exception -> 0x049b }
        r4 = (int[]) r4;	 Catch:{ Exception -> 0x049b }
        if (r4 == 0) goto L_0x02cc;	 Catch:{ Exception -> 0x049b }
    L_0x026a:
        r6 = android.hardware.camera2.CaptureRequest.CONTROL_AE_MODE;	 Catch:{ Exception -> 0x049b }
        if (r6 != 0) goto L_0x026f;	 Catch:{ Exception -> 0x049b }
    L_0x026e:
        goto L_0x02cc;	 Catch:{ Exception -> 0x049b }
    L_0x026f:
        r6 = 805310464; // 0x30001000 float:4.6588866E-10 double:3.978762345E-315;	 Catch:{ Exception -> 0x049b }
        if (r2 == r6) goto L_0x02a7;	 Catch:{ Exception -> 0x049b }
    L_0x0274:
        r6 = 805322752; // 0x30004000 float:4.665708E-10 double:3.978823056E-315;	 Catch:{ Exception -> 0x049b }
        if (r2 == r6) goto L_0x0282;	 Catch:{ Exception -> 0x049b }
    L_0x0279:
        r6 = 805339136; // 0x30008000 float:4.674803E-10 double:3.978904003E-315;	 Catch:{ Exception -> 0x049b }
        if (r2 == r6) goto L_0x02a7;	 Catch:{ Exception -> 0x049b }
    L_0x027e:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r8);	 Catch:{ Exception -> 0x049b }
        return r5;	 Catch:{ Exception -> 0x049b }
    L_0x0282:
        r2 = r4.length;	 Catch:{ Exception -> 0x049b }
        r6 = 0;	 Catch:{ Exception -> 0x049b }
        r7 = 0;	 Catch:{ Exception -> 0x049b }
    L_0x0285:
        if (r6 >= r2) goto L_0x0294;	 Catch:{ Exception -> 0x049b }
    L_0x0287:
        r7 = r4[r6];	 Catch:{ Exception -> 0x049b }
        if (r7 != r9) goto L_0x028d;	 Catch:{ Exception -> 0x049b }
    L_0x028b:
        r7 = 1;	 Catch:{ Exception -> 0x049b }
        goto L_0x028e;	 Catch:{ Exception -> 0x049b }
    L_0x028d:
        r7 = 0;	 Catch:{ Exception -> 0x049b }
    L_0x028e:
        if (r7 == 0) goto L_0x0291;	 Catch:{ Exception -> 0x049b }
    L_0x0290:
        goto L_0x0294;	 Catch:{ Exception -> 0x049b }
    L_0x0291:
        r6 = r6 + 1;	 Catch:{ Exception -> 0x049b }
        goto L_0x0285;	 Catch:{ Exception -> 0x049b }
    L_0x0294:
        if (r7 != 0) goto L_0x029a;	 Catch:{ Exception -> 0x049b }
    L_0x0296:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10);	 Catch:{ Exception -> 0x049b }
        return r5;	 Catch:{ Exception -> 0x049b }
    L_0x029a:
        r2 = r3.builder;	 Catch:{ Exception -> 0x049b }
        r4 = android.hardware.camera2.CaptureRequest.CONTROL_AE_MODE;	 Catch:{ Exception -> 0x049b }
        r6 = java.lang.Integer.valueOf(r9);	 Catch:{ Exception -> 0x049b }
        r2.set(r4, r6);	 Catch:{ Exception -> 0x049b }
        goto L_0x047f;	 Catch:{ Exception -> 0x049b }
    L_0x02a7:
        r2 = r4.length;	 Catch:{ Exception -> 0x049b }
        r6 = 0;	 Catch:{ Exception -> 0x049b }
        r7 = 0;	 Catch:{ Exception -> 0x049b }
    L_0x02aa:
        if (r6 >= r2) goto L_0x02b9;	 Catch:{ Exception -> 0x049b }
    L_0x02ac:
        r7 = r4[r6];	 Catch:{ Exception -> 0x049b }
        if (r7 != 0) goto L_0x02b2;	 Catch:{ Exception -> 0x049b }
    L_0x02b0:
        r7 = 1;	 Catch:{ Exception -> 0x049b }
        goto L_0x02b3;	 Catch:{ Exception -> 0x049b }
    L_0x02b2:
        r7 = 0;	 Catch:{ Exception -> 0x049b }
    L_0x02b3:
        if (r7 == 0) goto L_0x02b6;	 Catch:{ Exception -> 0x049b }
    L_0x02b5:
        goto L_0x02b9;	 Catch:{ Exception -> 0x049b }
    L_0x02b6:
        r6 = r6 + 1;	 Catch:{ Exception -> 0x049b }
        goto L_0x02aa;	 Catch:{ Exception -> 0x049b }
    L_0x02b9:
        if (r7 != 0) goto L_0x02bf;	 Catch:{ Exception -> 0x049b }
    L_0x02bb:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10);	 Catch:{ Exception -> 0x049b }
        return r5;	 Catch:{ Exception -> 0x049b }
    L_0x02bf:
        r2 = r3.builder;	 Catch:{ Exception -> 0x049b }
        r4 = android.hardware.camera2.CaptureRequest.CONTROL_AE_MODE;	 Catch:{ Exception -> 0x049b }
        r6 = java.lang.Integer.valueOf(r5);	 Catch:{ Exception -> 0x049b }
        r2.set(r4, r6);	 Catch:{ Exception -> 0x049b }
        goto L_0x047f;	 Catch:{ Exception -> 0x049b }
    L_0x02cc:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10);	 Catch:{ Exception -> 0x049b }
        return r5;	 Catch:{ Exception -> 0x049b }
    L_0x02d0:
        r2 = r19;	 Catch:{ Exception -> 0x049b }
        r2 = (float[]) r2;	 Catch:{ Exception -> 0x049b }
        r2 = (float[]) r2;	 Catch:{ Exception -> 0x049b }
        r11 = r2.length;	 Catch:{ Exception -> 0x049b }
        r12 = 5;	 Catch:{ Exception -> 0x049b }
        if (r11 == r12) goto L_0x02de;	 Catch:{ Exception -> 0x049b }
    L_0x02da:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r7);	 Catch:{ Exception -> 0x049b }
        return r5;	 Catch:{ Exception -> 0x049b }
    L_0x02de:
        r11 = r2[r5];	 Catch:{ Exception -> 0x049b }
        r11 = (r11 > r6 ? 1 : (r11 == r6 ? 0 : -1));	 Catch:{ Exception -> 0x049b }
        if (r11 < 0) goto L_0x03b8;	 Catch:{ Exception -> 0x049b }
    L_0x02e4:
        r11 = r2[r5];	 Catch:{ Exception -> 0x049b }
        r12 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;	 Catch:{ Exception -> 0x049b }
        r11 = (r11 > r12 ? 1 : (r11 == r12 ? 0 : -1));	 Catch:{ Exception -> 0x049b }
        if (r11 > 0) goto L_0x03b8;	 Catch:{ Exception -> 0x049b }
    L_0x02ec:
        r11 = r2[r9];	 Catch:{ Exception -> 0x049b }
        r11 = (r11 > r6 ? 1 : (r11 == r6 ? 0 : -1));	 Catch:{ Exception -> 0x049b }
        if (r11 < 0) goto L_0x03b8;	 Catch:{ Exception -> 0x049b }
    L_0x02f2:
        r11 = r2[r9];	 Catch:{ Exception -> 0x049b }
        r11 = (r11 > r12 ? 1 : (r11 == r12 ? 0 : -1));	 Catch:{ Exception -> 0x049b }
        if (r11 > 0) goto L_0x03b8;	 Catch:{ Exception -> 0x049b }
    L_0x02f8:
        r11 = r2[r7];	 Catch:{ Exception -> 0x049b }
        r11 = (r11 > r6 ? 1 : (r11 == r6 ? 0 : -1));	 Catch:{ Exception -> 0x049b }
        if (r11 < 0) goto L_0x03b8;	 Catch:{ Exception -> 0x049b }
    L_0x02fe:
        r11 = r2[r7];	 Catch:{ Exception -> 0x049b }
        r11 = (r11 > r12 ? 1 : (r11 == r12 ? 0 : -1));	 Catch:{ Exception -> 0x049b }
        if (r11 > 0) goto L_0x03b8;	 Catch:{ Exception -> 0x049b }
    L_0x0304:
        r11 = r2[r8];	 Catch:{ Exception -> 0x049b }
        r11 = (r11 > r6 ? 1 : (r11 == r6 ? 0 : -1));	 Catch:{ Exception -> 0x049b }
        if (r11 < 0) goto L_0x03b8;	 Catch:{ Exception -> 0x049b }
    L_0x030a:
        r11 = r2[r8];	 Catch:{ Exception -> 0x049b }
        r11 = (r11 > r12 ? 1 : (r11 == r12 ? 0 : -1));	 Catch:{ Exception -> 0x049b }
        if (r11 > 0) goto L_0x03b8;	 Catch:{ Exception -> 0x049b }
    L_0x0310:
        r11 = r2[r4];	 Catch:{ Exception -> 0x049b }
        r11 = (r11 > r6 ? 1 : (r11 == r6 ? 0 : -1));	 Catch:{ Exception -> 0x049b }
        if (r11 < 0) goto L_0x03b8;	 Catch:{ Exception -> 0x049b }
    L_0x0316:
        r11 = r2[r4];	 Catch:{ Exception -> 0x049b }
        r11 = (r11 > r12 ? 1 : (r11 == r12 ? 0 : -1));	 Catch:{ Exception -> 0x049b }
        if (r11 <= 0) goto L_0x031e;	 Catch:{ Exception -> 0x049b }
    L_0x031c:
        goto L_0x03b8;	 Catch:{ Exception -> 0x049b }
    L_0x031e:
        r11 = r3.characteristics;	 Catch:{ Exception -> 0x049b }
        r12 = android.hardware.camera2.CameraCharacteristics.CONTROL_MAX_REGIONS_AF;	 Catch:{ Exception -> 0x049b }
        r11 = r11.get(r12);	 Catch:{ Exception -> 0x049b }
        r11 = (java.lang.Integer) r11;	 Catch:{ Exception -> 0x049b }
        r12 = android.hardware.camera2.CaptureRequest.CONTROL_AF_REGIONS;	 Catch:{ Exception -> 0x049b }
        if (r12 == 0) goto L_0x03b2;	 Catch:{ Exception -> 0x049b }
    L_0x032c:
        if (r11 == 0) goto L_0x03b2;	 Catch:{ Exception -> 0x049b }
    L_0x032e:
        r11 = r11.intValue();	 Catch:{ Exception -> 0x049b }
        if (r11 != 0) goto L_0x0336;	 Catch:{ Exception -> 0x049b }
    L_0x0334:
        goto L_0x03b2;	 Catch:{ Exception -> 0x049b }
    L_0x0336:
        r11 = r3.characteristics;	 Catch:{ Exception -> 0x049b }
        r12 = android.hardware.camera2.CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE;	 Catch:{ Exception -> 0x049b }
        r11 = r11.get(r12);	 Catch:{ Exception -> 0x049b }
        r11 = (android.graphics.Rect) r11;	 Catch:{ Exception -> 0x049b }
        if (r11 != 0) goto L_0x0346;	 Catch:{ Exception -> 0x049b }
    L_0x0342:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10);	 Catch:{ Exception -> 0x049b }
        return r5;	 Catch:{ Exception -> 0x049b }
    L_0x0346:
        r12 = new android.hardware.camera2.params.MeteringRectangle[r9];	 Catch:{ Exception -> 0x049b }
        r13 = new android.hardware.camera2.params.MeteringRectangle;	 Catch:{ Exception -> 0x049b }
        r14 = new android.graphics.Rect;	 Catch:{ Exception -> 0x049b }
        r15 = r2[r5];	 Catch:{ Exception -> 0x049b }
        r10 = r11.width();	 Catch:{ Exception -> 0x049b }
        r10 = (float) r10;	 Catch:{ Exception -> 0x049b }
        r15 = r15 * r10;	 Catch:{ Exception -> 0x049b }
        r10 = (int) r15;	 Catch:{ Exception -> 0x049b }
        r15 = r11.width();	 Catch:{ Exception -> 0x049b }
        r15 = r15 - r9;	 Catch:{ Exception -> 0x049b }
        r10 = java.lang.Math.min(r10, r15);	 Catch:{ Exception -> 0x049b }
        r15 = r2[r9];	 Catch:{ Exception -> 0x049b }
        r5 = r11.height();	 Catch:{ Exception -> 0x049b }
        r5 = (float) r5;	 Catch:{ Exception -> 0x049b }
        r15 = r15 * r5;	 Catch:{ Exception -> 0x049b }
        r5 = (int) r15;	 Catch:{ Exception -> 0x049b }
        r15 = r11.height();	 Catch:{ Exception -> 0x049b }
        r15 = r15 - r9;	 Catch:{ Exception -> 0x049b }
        r5 = java.lang.Math.min(r5, r15);	 Catch:{ Exception -> 0x049b }
        r7 = r2[r7];	 Catch:{ Exception -> 0x049b }
        r15 = r11.width();	 Catch:{ Exception -> 0x049b }
        r15 = (float) r15;	 Catch:{ Exception -> 0x049b }
        r7 = r7 * r15;	 Catch:{ Exception -> 0x049b }
        r7 = (int) r7;	 Catch:{ Exception -> 0x049b }
        r15 = r11.width();	 Catch:{ Exception -> 0x049b }
        r15 = r15 - r9;	 Catch:{ Exception -> 0x049b }
        r7 = java.lang.Math.min(r7, r15);	 Catch:{ Exception -> 0x049b }
        r8 = r2[r8];	 Catch:{ Exception -> 0x049b }
        r15 = r11.height();	 Catch:{ Exception -> 0x049b }
        r15 = (float) r15;	 Catch:{ Exception -> 0x049b }
        r8 = r8 * r15;	 Catch:{ Exception -> 0x049b }
        r8 = (int) r8;	 Catch:{ Exception -> 0x049b }
        r11 = r11.height();	 Catch:{ Exception -> 0x049b }
        r11 = r11 - r9;	 Catch:{ Exception -> 0x049b }
        r8 = java.lang.Math.min(r8, r11);	 Catch:{ Exception -> 0x049b }
        r14.<init>(r10, r5, r7, r8);	 Catch:{ Exception -> 0x049b }
        r2 = r2[r4];	 Catch:{ Exception -> 0x049b }
        r4 = 1148846080; // 0x447a0000 float:1000.0 double:5.676053805E-315;	 Catch:{ Exception -> 0x049b }
        r2 = r2 * r4;	 Catch:{ Exception -> 0x049b }
        r2 = r2 + r6;	 Catch:{ Exception -> 0x049b }
        r2 = (int) r2;	 Catch:{ Exception -> 0x049b }
        r13.<init>(r14, r2);	 Catch:{ Exception -> 0x049b }
        r2 = 0;
        r12[r2] = r13;	 Catch:{ Exception -> 0x0574 }
        r2 = r3.builder;	 Catch:{ Exception -> 0x049b }
        r4 = android.hardware.camera2.CaptureRequest.CONTROL_AF_REGIONS;	 Catch:{ Exception -> 0x049b }
        r2.set(r4, r12);	 Catch:{ Exception -> 0x049b }
        goto L_0x0498;
    L_0x03b2:
        r1 = 6;
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r1);	 Catch:{ Exception -> 0x049c }
        r1 = 0;
        return r1;
    L_0x03b8:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r7);	 Catch:{ Exception -> 0x049b }
        r4 = 0;	 Catch:{ Exception -> 0x049b }
        return r4;	 Catch:{ Exception -> 0x049b }
    L_0x03bd:
        r4 = 0;	 Catch:{ Exception -> 0x049b }
        r5 = 6;	 Catch:{ Exception -> 0x049b }
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r5);	 Catch:{ Exception -> 0x049b }
        return r4;	 Catch:{ Exception -> 0x049b }
    L_0x03c3:
        r4 = 0;	 Catch:{ Exception -> 0x049b }
        r5 = 6;	 Catch:{ Exception -> 0x049b }
        r6 = android.hardware.camera2.CaptureRequest.LENS_FOCUS_DISTANCE;	 Catch:{ Exception -> 0x049b }
        if (r6 != 0) goto L_0x03cd;	 Catch:{ Exception -> 0x049b }
    L_0x03c9:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r5);	 Catch:{ Exception -> 0x049b }
        return r4;	 Catch:{ Exception -> 0x049b }
    L_0x03cd:
        r2 = r19;	 Catch:{ Exception -> 0x049b }
        r2 = (java.lang.Number) r2;	 Catch:{ Exception -> 0x049b }
        r2 = r2.floatValue();	 Catch:{ Exception -> 0x049b }
        r2 = java.lang.Float.valueOf(r2);	 Catch:{ Exception -> 0x049b }
        r4 = r3.builder;	 Catch:{ Exception -> 0x049b }
        r5 = android.hardware.camera2.CaptureRequest.LENS_FOCUS_DISTANCE;	 Catch:{ Exception -> 0x049b }
        r4.set(r5, r2);	 Catch:{ Exception -> 0x049b }
        goto L_0x047f;	 Catch:{ Exception -> 0x049b }
    L_0x03e2:
        r4 = android.hardware.camera2.CaptureRequest.CONTROL_AF_MODE;	 Catch:{ Exception -> 0x049b }
        if (r4 != 0) goto L_0x03ec;	 Catch:{ Exception -> 0x049b }
    L_0x03e6:
        r4 = 6;	 Catch:{ Exception -> 0x049b }
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r4);	 Catch:{ Exception -> 0x049b }
        r1 = 0;	 Catch:{ Exception -> 0x049b }
        return r1;	 Catch:{ Exception -> 0x049b }
    L_0x03ec:
        r4 = r3.characteristics;	 Catch:{ Exception -> 0x049b }
        r5 = android.hardware.camera2.CameraCharacteristics.CONTROL_AF_AVAILABLE_MODES;	 Catch:{ Exception -> 0x049b }
        r4 = r4.get(r5);	 Catch:{ Exception -> 0x049b }
        r4 = (int[]) r4;	 Catch:{ Exception -> 0x049b }
        java.util.Arrays.sort(r4);	 Catch:{ Exception -> 0x049b }
        r2 = r19;	 Catch:{ Exception -> 0x049b }
        r2 = (java.lang.Number) r2;	 Catch:{ Exception -> 0x049b }
        r2 = r2.intValue();	 Catch:{ Exception -> 0x049b }
        r5 = -1;	 Catch:{ Exception -> 0x049b }
        switch(r2) {
            case 805306384: goto L_0x0481;
            case 805306400: goto L_0x0481;
            case 805306432: goto L_0x0468;
            case 805306496: goto L_0x0450;
            case 805306624: goto L_0x0423;
            case 805306880: goto L_0x040b;
            default: goto L_0x0405;
        };	 Catch:{ Exception -> 0x049b }
    L_0x0405:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r8);	 Catch:{ Exception -> 0x049b }
        r1 = 0;
        goto L_0x049a;
    L_0x040b:
        r2 = 0;
        r4 = java.util.Arrays.binarySearch(r4, r2);	 Catch:{ Exception -> 0x0574 }
        if (r4 != r5) goto L_0x0417;	 Catch:{ Exception -> 0x0574 }
    L_0x0412:
        r4 = 6;	 Catch:{ Exception -> 0x0574 }
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r4);	 Catch:{ Exception -> 0x0574 }
        return r2;	 Catch:{ Exception -> 0x0574 }
    L_0x0417:
        r4 = r3.builder;	 Catch:{ Exception -> 0x0574 }
        r5 = android.hardware.camera2.CaptureRequest.CONTROL_AF_MODE;	 Catch:{ Exception -> 0x0574 }
        r6 = java.lang.Integer.valueOf(r2);	 Catch:{ Exception -> 0x0574 }
        r4.set(r5, r6);	 Catch:{ Exception -> 0x049b }
        goto L_0x047f;	 Catch:{ Exception -> 0x049b }
    L_0x0423:
        r2 = android.hardware.camera2.CaptureRequest.LENS_FOCUS_DISTANCE;	 Catch:{ Exception -> 0x049b }
        if (r2 != 0) goto L_0x042d;	 Catch:{ Exception -> 0x049b }
    L_0x0427:
        r2 = 6;	 Catch:{ Exception -> 0x049b }
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r2);	 Catch:{ Exception -> 0x049b }
        r7 = 0;	 Catch:{ Exception -> 0x049b }
        return r7;	 Catch:{ Exception -> 0x049b }
    L_0x042d:
        r2 = 6;	 Catch:{ Exception -> 0x049b }
        r7 = 0;	 Catch:{ Exception -> 0x049b }
        r4 = java.util.Arrays.binarySearch(r4, r7);	 Catch:{ Exception -> 0x049b }
        if (r4 != r5) goto L_0x0439;	 Catch:{ Exception -> 0x049b }
    L_0x0435:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r2);	 Catch:{ Exception -> 0x049b }
        return r7;	 Catch:{ Exception -> 0x049b }
    L_0x0439:
        r2 = r3.builder;	 Catch:{ Exception -> 0x049b }
        r4 = android.hardware.camera2.CaptureRequest.CONTROL_AF_MODE;	 Catch:{ Exception -> 0x049b }
        r5 = java.lang.Integer.valueOf(r7);	 Catch:{ Exception -> 0x049b }
        r2.set(r4, r5);	 Catch:{ Exception -> 0x049b }
        r2 = r3.builder;	 Catch:{ Exception -> 0x049b }
        r4 = android.hardware.camera2.CaptureRequest.LENS_FOCUS_DISTANCE;	 Catch:{ Exception -> 0x049b }
        r5 = java.lang.Float.valueOf(r6);	 Catch:{ Exception -> 0x049b }
        r2.set(r4, r5);	 Catch:{ Exception -> 0x049b }
        goto L_0x047f;	 Catch:{ Exception -> 0x049b }
    L_0x0450:
        r2 = java.util.Arrays.binarySearch(r4, r7);	 Catch:{ Exception -> 0x049b }
        if (r2 != r5) goto L_0x045c;	 Catch:{ Exception -> 0x049b }
    L_0x0456:
        r2 = 6;	 Catch:{ Exception -> 0x049b }
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r2);	 Catch:{ Exception -> 0x049b }
        r1 = 0;	 Catch:{ Exception -> 0x049b }
        return r1;	 Catch:{ Exception -> 0x049b }
    L_0x045c:
        r2 = r3.builder;	 Catch:{ Exception -> 0x049b }
        r4 = android.hardware.camera2.CaptureRequest.CONTROL_AF_MODE;	 Catch:{ Exception -> 0x049b }
        r5 = java.lang.Integer.valueOf(r7);	 Catch:{ Exception -> 0x049b }
        r2.set(r4, r5);	 Catch:{ Exception -> 0x049b }
        goto L_0x0498;	 Catch:{ Exception -> 0x049b }
    L_0x0468:
        r2 = java.util.Arrays.binarySearch(r4, r8);	 Catch:{ Exception -> 0x049b }
        if (r2 != r5) goto L_0x0474;	 Catch:{ Exception -> 0x049b }
    L_0x046e:
        r2 = 6;	 Catch:{ Exception -> 0x049b }
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r2);	 Catch:{ Exception -> 0x049b }
        r1 = 0;	 Catch:{ Exception -> 0x049b }
        return r1;	 Catch:{ Exception -> 0x049b }
    L_0x0474:
        r2 = r3.builder;	 Catch:{ Exception -> 0x049b }
        r4 = android.hardware.camera2.CaptureRequest.CONTROL_AF_MODE;	 Catch:{ Exception -> 0x049b }
        r5 = java.lang.Integer.valueOf(r8);	 Catch:{ Exception -> 0x049b }
        r2.set(r4, r5);	 Catch:{ Exception -> 0x049b }
    L_0x047f:
        r5 = 0;	 Catch:{ Exception -> 0x049b }
        goto L_0x04d8;	 Catch:{ Exception -> 0x049b }
    L_0x0481:
        r2 = java.util.Arrays.binarySearch(r4, r9);	 Catch:{ Exception -> 0x049b }
        if (r2 != r5) goto L_0x048d;	 Catch:{ Exception -> 0x049b }
    L_0x0487:
        r2 = 6;	 Catch:{ Exception -> 0x049b }
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r2);	 Catch:{ Exception -> 0x049b }
        r1 = 0;	 Catch:{ Exception -> 0x049b }
        return r1;	 Catch:{ Exception -> 0x049b }
    L_0x048d:
        r2 = r3.builder;	 Catch:{ Exception -> 0x049b }
        r4 = android.hardware.camera2.CaptureRequest.CONTROL_AF_MODE;	 Catch:{ Exception -> 0x049b }
        r5 = java.lang.Integer.valueOf(r9);	 Catch:{ Exception -> 0x049b }
        r2.set(r4, r5);	 Catch:{ Exception -> 0x049b }
    L_0x0498:
        r5 = 1;
        goto L_0x04d8;
    L_0x049a:
        return r1;
    L_0x049b:
        r1 = 6;
    L_0x049c:
        r2 = 0;
        goto L_0x0575;
    L_0x049f:
        r4 = android.hardware.camera2.CaptureRequest.FLASH_MODE;	 Catch:{ Exception -> 0x0573 }
        if (r4 != 0) goto L_0x04a9;
    L_0x04a3:
        r4 = 6;
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r4);	 Catch:{ Exception -> 0x049b }
        r4 = 0;
        return r4;
    L_0x04a9:
        r4 = 0;
        r2 = r19;	 Catch:{ Exception -> 0x0573 }
        r2 = (java.lang.Number) r2;	 Catch:{ Exception -> 0x0573 }
        r2 = r2.intValue();	 Catch:{ Exception -> 0x0573 }
        switch(r2) {
            case 805306369: goto L_0x04cb;
            case 805306370: goto L_0x04bf;
            case 805306371: goto L_0x04b5;
            case 805306372: goto L_0x04bb;
            default: goto L_0x04b5;
        };
    L_0x04b5:
        r2 = 0;
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r8);	 Catch:{ Exception -> 0x0574 }
        goto L_0x0572;
    L_0x04bb:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r8);	 Catch:{ Exception -> 0x049b }
        return r4;	 Catch:{ Exception -> 0x049b }
    L_0x04bf:
        r2 = r3.builder;	 Catch:{ Exception -> 0x049b }
        r4 = android.hardware.camera2.CaptureRequest.FLASH_MODE;	 Catch:{ Exception -> 0x049b }
        r5 = java.lang.Integer.valueOf(r7);	 Catch:{ Exception -> 0x049b }
        r2.set(r4, r5);	 Catch:{ Exception -> 0x049b }
        goto L_0x047f;
    L_0x04cb:
        r2 = r3.builder;	 Catch:{ Exception -> 0x0573 }
        r4 = android.hardware.camera2.CaptureRequest.FLASH_MODE;	 Catch:{ Exception -> 0x0573 }
        r5 = 0;	 Catch:{ Exception -> 0x0573 }
        r6 = java.lang.Integer.valueOf(r5);	 Catch:{ Exception -> 0x0573 }
        r2.set(r4, r6);	 Catch:{ Exception -> 0x0573 }
        goto L_0x047f;
    L_0x04d8:
        r2 = r3.session;
        if (r2 == 0) goto L_0x0571;
    L_0x04dc:
        r2 = r3.session;	 Catch:{ CameraAccessException -> 0x056b, IllegalArgumentException -> 0x0565, IllegalStateException -> 0x055f }
        r4 = r3.builder;	 Catch:{ CameraAccessException -> 0x056b, IllegalArgumentException -> 0x0565, IllegalStateException -> 0x055f }
        r4 = r4.build();	 Catch:{ CameraAccessException -> 0x056b, IllegalArgumentException -> 0x0565, IllegalStateException -> 0x055f }
        r6 = new com.vuforia.ar.pl.Camera2_Preview$OnFrameCapturedCallback;	 Catch:{ CameraAccessException -> 0x056b, IllegalArgumentException -> 0x0565, IllegalStateException -> 0x055f }
        r6.<init>(r3);	 Catch:{ CameraAccessException -> 0x056b, IllegalArgumentException -> 0x0565, IllegalStateException -> 0x055f }
        r7 = r3.handler;	 Catch:{ CameraAccessException -> 0x056b, IllegalArgumentException -> 0x0565, IllegalStateException -> 0x055f }
        r2.setRepeatingRequest(r4, r6, r7);	 Catch:{ CameraAccessException -> 0x056b, IllegalArgumentException -> 0x0565, IllegalStateException -> 0x055f }
        if (r5 == 0) goto L_0x0571;
    L_0x04f0:
        r2 = 536870914; // 0x20000002 float:1.0842024E-19 double:2.65249475E-315;
        if (r1 == r2) goto L_0x04fc;
    L_0x04f5:
        r2 = 536870928; // 0x20000010 float:1.0842042E-19 double:2.65249482E-315;
        if (r1 == r2) goto L_0x0548;
    L_0x04fa:
        goto L_0x0571;
    L_0x04fc:
        r1 = r3.characteristics;	 Catch:{ Exception -> 0x0559 }
        r2 = android.hardware.camera2.CameraCharacteristics.CONTROL_MAX_REGIONS_AF;	 Catch:{ Exception -> 0x0559 }
        r1 = r1.get(r2);	 Catch:{ Exception -> 0x0559 }
        r1 = (java.lang.Integer) r1;	 Catch:{ Exception -> 0x0559 }
        if (r1 == 0) goto L_0x0548;	 Catch:{ Exception -> 0x0559 }
    L_0x0508:
        r2 = android.hardware.camera2.CameraCharacteristics.CONTROL_MAX_REGIONS_AF;	 Catch:{ Exception -> 0x0559 }
        if (r2 == 0) goto L_0x0548;	 Catch:{ Exception -> 0x0559 }
    L_0x050c:
        r1 = r1.intValue();	 Catch:{ Exception -> 0x0559 }
        if (r1 <= 0) goto L_0x0548;	 Catch:{ Exception -> 0x0559 }
    L_0x0512:
        r1 = android.hardware.camera2.CaptureRequest.CONTROL_AF_REGIONS;	 Catch:{ Exception -> 0x0559 }
        if (r1 == 0) goto L_0x0548;	 Catch:{ Exception -> 0x0559 }
    L_0x0516:
        r1 = r3.builder;	 Catch:{ Exception -> 0x0559 }
        r2 = android.hardware.camera2.CaptureRequest.CONTROL_AF_REGIONS;	 Catch:{ Exception -> 0x0559 }
        r1 = r1.get(r2);	 Catch:{ Exception -> 0x0559 }
        r1 = (android.hardware.camera2.params.MeteringRectangle[]) r1;	 Catch:{ Exception -> 0x0559 }
        if (r1 == 0) goto L_0x0548;	 Catch:{ Exception -> 0x0559 }
    L_0x0522:
        r2 = r1.length;	 Catch:{ Exception -> 0x0559 }
        if (r2 <= 0) goto L_0x0548;	 Catch:{ Exception -> 0x0559 }
    L_0x0525:
        r2 = r1.length;	 Catch:{ Exception -> 0x0559 }
        r2 = new android.hardware.camera2.params.MeteringRectangle[r2];	 Catch:{ Exception -> 0x0559 }
        r4 = r1.length;	 Catch:{ Exception -> 0x0559 }
        r5 = 0;	 Catch:{ Exception -> 0x0559 }
        r6 = 0;	 Catch:{ Exception -> 0x0559 }
    L_0x052b:
        if (r5 >= r4) goto L_0x0541;	 Catch:{ Exception -> 0x0559 }
    L_0x052d:
        r7 = r1[r5];	 Catch:{ Exception -> 0x0559 }
        r8 = r6 + 1;	 Catch:{ Exception -> 0x0559 }
        r10 = new android.hardware.camera2.params.MeteringRectangle;	 Catch:{ Exception -> 0x0559 }
        r7 = r7.getRect();	 Catch:{ Exception -> 0x0559 }
        r11 = 0;	 Catch:{ Exception -> 0x0559 }
        r10.<init>(r7, r11);	 Catch:{ Exception -> 0x0559 }
        r2[r6] = r10;	 Catch:{ Exception -> 0x0559 }
        r5 = r5 + 1;	 Catch:{ Exception -> 0x0559 }
        r6 = r8;	 Catch:{ Exception -> 0x0559 }
        goto L_0x052b;	 Catch:{ Exception -> 0x0559 }
    L_0x0541:
        r1 = r3.builder;	 Catch:{ Exception -> 0x0559 }
        r4 = android.hardware.camera2.CaptureRequest.CONTROL_AF_REGIONS;	 Catch:{ Exception -> 0x0559 }
        r1.set(r4, r2);	 Catch:{ Exception -> 0x0559 }
    L_0x0548:
        r1 = new com.vuforia.ar.pl.Camera2_Preview$AutofocusRunner;	 Catch:{ Exception -> 0x0559 }
        r1.<init>(r3);	 Catch:{ Exception -> 0x0559 }
        r1 = r1.triggerAutofocus();	 Catch:{ Exception -> 0x0559 }
        if (r1 != 0) goto L_0x0571;
    L_0x0553:
        r1 = 6;
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r1);	 Catch:{ Exception -> 0x055a }
        r1 = 0;
        return r1;
    L_0x0559:
        r1 = 6;
    L_0x055a:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r1);
        r1 = 0;
        return r1;
    L_0x055f:
        r1 = 6;
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r1);
        r2 = 0;
        return r2;
    L_0x0565:
        r1 = 6;
        r2 = 0;
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r1);
        return r2;
    L_0x056b:
        r1 = 6;
        r2 = 0;
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r1);
        return r2;
    L_0x0571:
        return r9;
    L_0x0572:
        return r2;
    L_0x0573:
        r2 = 0;
    L_0x0574:
        r1 = 6;
    L_0x0575:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r1);
        return r2;
    L_0x0579:
        r2 = 0;
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r4);
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.Camera2_Preview.setTypedCameraParameter(int, int, java.lang.Object):boolean");
    }

    java.lang.Object getTypedCameraParameter(int r12, int r13) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/345281752.run(Unknown Source)
*/
        /*
        r11 = this;
        r12 = r11.getCameraCacheInfo(r12);
        r0 = 4;
        r1 = 0;
        if (r12 == 0) goto L_0x03c0;
    L_0x0008:
        r2 = r12.characteristics;
        if (r2 != 0) goto L_0x000e;
    L_0x000c:
        goto L_0x03c0;
    L_0x000e:
        r2 = r12.lastResult;
        r3 = 3;
        r4 = 4741671816366391296; // 0x41cdcd6500000000 float:0.0 double:1.0E9;
        r6 = 2;
        r7 = 0;
        r8 = 1;
        r9 = 6;
        switch(r13) {
            case 536870913: goto L_0x037b;
            case 536870914: goto L_0x02eb;
            case 536870916: goto L_0x02d1;
            case 536870920: goto L_0x02a2;
            case 536870928: goto L_0x0219;
            case 536870944: goto L_0x01d8;
            case 536870976: goto L_0x01b7;
            case 536871040: goto L_0x0187;
            case 536871168: goto L_0x0164;
            case 536871424: goto L_0x0130;
            case 536871936: goto L_0x00f8;
            case 536872960: goto L_0x00aa;
            case 536875008: goto L_0x00a6;
            case 536879104: goto L_0x00a2;
            case 536887296: goto L_0x009e;
            case 536903680: goto L_0x0084;
            case 536936448: goto L_0x007d;
            case 537001984: goto L_0x0080;
            case 537133056: goto L_0x0079;
            case 537395200: goto L_0x0075;
            case 537919488: goto L_0x0071;
            case 538968064: goto L_0x006d;
            case 553648128: goto L_0x001e;
            default: goto L_0x001d;
        };
    L_0x001d:
        return r1;
    L_0x001e:
        if (r2 != 0) goto L_0x0024;
    L_0x0020:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9);	 Catch:{ Exception -> 0x03bc }
        return r1;	 Catch:{ Exception -> 0x03bc }
    L_0x0024:
        r12 = android.hardware.camera2.CaptureResult.CONTROL_VIDEO_STABILIZATION_MODE;	 Catch:{ Exception -> 0x03bc }
        r12 = r2.get(r12);	 Catch:{ Exception -> 0x03bc }
        r12 = (java.lang.Integer) r12;	 Catch:{ Exception -> 0x03bc }
        if (r12 == 0) goto L_0x0043;	 Catch:{ Exception -> 0x03bc }
    L_0x002e:
        r13 = android.hardware.camera2.CaptureResult.CONTROL_VIDEO_STABILIZATION_MODE;	 Catch:{ Exception -> 0x03bc }
        if (r13 == 0) goto L_0x0043;	 Catch:{ Exception -> 0x03bc }
    L_0x0032:
        r13 = java.lang.Integer.valueOf(r8);	 Catch:{ Exception -> 0x03bc }
        r12 = r12.equals(r13);	 Catch:{ Exception -> 0x03bc }
        if (r12 == 0) goto L_0x0041;	 Catch:{ Exception -> 0x03bc }
    L_0x003c:
        r12 = java.lang.Boolean.valueOf(r8);	 Catch:{ Exception -> 0x03bc }
        return r12;	 Catch:{ Exception -> 0x03bc }
    L_0x0041:
        r12 = 1;	 Catch:{ Exception -> 0x03bc }
        goto L_0x0044;	 Catch:{ Exception -> 0x03bc }
    L_0x0043:
        r12 = 0;	 Catch:{ Exception -> 0x03bc }
    L_0x0044:
        r13 = android.hardware.camera2.CaptureResult.LENS_OPTICAL_STABILIZATION_MODE;	 Catch:{ Exception -> 0x03bc }
        r13 = r2.get(r13);	 Catch:{ Exception -> 0x03bc }
        r13 = (java.lang.Integer) r13;	 Catch:{ Exception -> 0x03bc }
        if (r13 == 0) goto L_0x0062;	 Catch:{ Exception -> 0x03bc }
    L_0x004e:
        r0 = android.hardware.camera2.CaptureResult.LENS_OPTICAL_STABILIZATION_MODE;	 Catch:{ Exception -> 0x03bc }
        if (r0 == 0) goto L_0x0062;	 Catch:{ Exception -> 0x03bc }
    L_0x0052:
        r12 = java.lang.Integer.valueOf(r8);	 Catch:{ Exception -> 0x03bc }
        r12 = r13.equals(r12);	 Catch:{ Exception -> 0x03bc }
        if (r12 == 0) goto L_0x0061;	 Catch:{ Exception -> 0x03bc }
    L_0x005c:
        r12 = java.lang.Boolean.valueOf(r8);	 Catch:{ Exception -> 0x03bc }
        return r12;	 Catch:{ Exception -> 0x03bc }
    L_0x0061:
        r12 = 1;	 Catch:{ Exception -> 0x03bc }
    L_0x0062:
        if (r12 != 0) goto L_0x0068;	 Catch:{ Exception -> 0x03bc }
    L_0x0064:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9);	 Catch:{ Exception -> 0x03bc }
        return r1;	 Catch:{ Exception -> 0x03bc }
    L_0x0068:
        r12 = java.lang.Boolean.valueOf(r7);	 Catch:{ Exception -> 0x03bc }
        return r12;	 Catch:{ Exception -> 0x03bc }
    L_0x006d:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9);	 Catch:{ Exception -> 0x03bc }
        return r1;	 Catch:{ Exception -> 0x03bc }
    L_0x0071:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9);	 Catch:{ Exception -> 0x03bc }
        return r1;	 Catch:{ Exception -> 0x03bc }
    L_0x0075:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9);	 Catch:{ Exception -> 0x03bc }
        return r1;	 Catch:{ Exception -> 0x03bc }
    L_0x0079:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9);	 Catch:{ Exception -> 0x03bc }
        return r1;	 Catch:{ Exception -> 0x03bc }
    L_0x007d:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9);	 Catch:{ Exception -> 0x03bc }
    L_0x0080:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9);	 Catch:{ Exception -> 0x03bc }
        return r1;	 Catch:{ Exception -> 0x03bc }
    L_0x0084:
        if (r2 != 0) goto L_0x008a;	 Catch:{ Exception -> 0x03bc }
    L_0x0086:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9);	 Catch:{ Exception -> 0x03bc }
        return r1;	 Catch:{ Exception -> 0x03bc }
    L_0x008a:
        r12 = android.hardware.camera2.CaptureResult.LENS_FOCAL_LENGTH;	 Catch:{ Exception -> 0x03bc }
        r12 = r2.get(r12);	 Catch:{ Exception -> 0x03bc }
        r12 = (java.lang.Float) r12;	 Catch:{ Exception -> 0x03bc }
        if (r12 == 0) goto L_0x009a;	 Catch:{ Exception -> 0x03bc }
    L_0x0094:
        r13 = android.hardware.camera2.CaptureResult.LENS_FOCAL_LENGTH;	 Catch:{ Exception -> 0x03bc }
        if (r13 != 0) goto L_0x0099;	 Catch:{ Exception -> 0x03bc }
    L_0x0098:
        goto L_0x009a;	 Catch:{ Exception -> 0x03bc }
    L_0x0099:
        return r12;	 Catch:{ Exception -> 0x03bc }
    L_0x009a:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9);	 Catch:{ Exception -> 0x03bc }
        return r1;	 Catch:{ Exception -> 0x03bc }
    L_0x009e:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9);	 Catch:{ Exception -> 0x03bc }
        return r1;	 Catch:{ Exception -> 0x03bc }
    L_0x00a2:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9);	 Catch:{ Exception -> 0x03bc }
        return r1;	 Catch:{ Exception -> 0x03bc }
    L_0x00a6:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9);	 Catch:{ Exception -> 0x03bc }
        return r1;	 Catch:{ Exception -> 0x03bc }
    L_0x00aa:
        r13 = r12.characteristics;	 Catch:{ Exception -> 0x03bc }
        r0 = android.hardware.camera2.CameraCharacteristics.CONTROL_AE_COMPENSATION_RANGE;	 Catch:{ Exception -> 0x03bc }
        r13 = r13.get(r0);	 Catch:{ Exception -> 0x03bc }
        r13 = (android.util.Range) r13;	 Catch:{ Exception -> 0x03bc }
        r12 = r12.characteristics;	 Catch:{ Exception -> 0x03bc }
        r0 = android.hardware.camera2.CameraCharacteristics.CONTROL_AE_COMPENSATION_STEP;	 Catch:{ Exception -> 0x03bc }
        r12 = r12.get(r0);	 Catch:{ Exception -> 0x03bc }
        r12 = (android.util.Rational) r12;	 Catch:{ Exception -> 0x03bc }
        if (r13 == 0) goto L_0x00f4;	 Catch:{ Exception -> 0x03bc }
    L_0x00c0:
        r0 = android.hardware.camera2.CameraCharacteristics.CONTROL_AE_COMPENSATION_RANGE;	 Catch:{ Exception -> 0x03bc }
        if (r0 == 0) goto L_0x00f4;	 Catch:{ Exception -> 0x03bc }
    L_0x00c4:
        if (r12 == 0) goto L_0x00f4;	 Catch:{ Exception -> 0x03bc }
    L_0x00c6:
        r0 = android.hardware.camera2.CameraCharacteristics.CONTROL_AE_COMPENSATION_STEP;	 Catch:{ Exception -> 0x03bc }
        if (r0 != 0) goto L_0x00cb;	 Catch:{ Exception -> 0x03bc }
    L_0x00ca:
        goto L_0x00f4;	 Catch:{ Exception -> 0x03bc }
    L_0x00cb:
        r0 = new float[r6];	 Catch:{ Exception -> 0x03bc }
        r2 = r12.floatValue();	 Catch:{ Exception -> 0x03bc }
        r3 = r13.getLower();	 Catch:{ Exception -> 0x03bc }
        r3 = (java.lang.Integer) r3;	 Catch:{ Exception -> 0x03bc }
        r3 = r3.intValue();	 Catch:{ Exception -> 0x03bc }
        r3 = (float) r3;	 Catch:{ Exception -> 0x03bc }
        r2 = r2 * r3;	 Catch:{ Exception -> 0x03bc }
        r0[r7] = r2;	 Catch:{ Exception -> 0x03bc }
        r12 = r12.floatValue();	 Catch:{ Exception -> 0x03bc }
        r13 = r13.getUpper();	 Catch:{ Exception -> 0x03bc }
        r13 = (java.lang.Integer) r13;	 Catch:{ Exception -> 0x03bc }
        r13 = r13.intValue();	 Catch:{ Exception -> 0x03bc }
        r13 = (float) r13;	 Catch:{ Exception -> 0x03bc }
        r12 = r12 * r13;	 Catch:{ Exception -> 0x03bc }
        r0[r8] = r12;	 Catch:{ Exception -> 0x03bc }
        return r0;	 Catch:{ Exception -> 0x03bc }
    L_0x00f4:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9);	 Catch:{ Exception -> 0x03bc }
        return r1;	 Catch:{ Exception -> 0x03bc }
    L_0x00f8:
        if (r2 != 0) goto L_0x00fe;	 Catch:{ Exception -> 0x03bc }
    L_0x00fa:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9);	 Catch:{ Exception -> 0x03bc }
        return r1;	 Catch:{ Exception -> 0x03bc }
    L_0x00fe:
        r13 = android.hardware.camera2.CaptureResult.CONTROL_AE_EXPOSURE_COMPENSATION;	 Catch:{ Exception -> 0x03bc }
        r13 = r2.get(r13);	 Catch:{ Exception -> 0x03bc }
        r13 = (java.lang.Integer) r13;	 Catch:{ Exception -> 0x03bc }
        r12 = r12.characteristics;	 Catch:{ Exception -> 0x03bc }
        r0 = android.hardware.camera2.CameraCharacteristics.CONTROL_AE_COMPENSATION_STEP;	 Catch:{ Exception -> 0x03bc }
        r12 = r12.get(r0);	 Catch:{ Exception -> 0x03bc }
        r12 = (android.util.Rational) r12;	 Catch:{ Exception -> 0x03bc }
        if (r13 == 0) goto L_0x012c;	 Catch:{ Exception -> 0x03bc }
    L_0x0112:
        r0 = android.hardware.camera2.CaptureResult.CONTROL_AE_EXPOSURE_COMPENSATION;	 Catch:{ Exception -> 0x03bc }
        if (r0 == 0) goto L_0x012c;	 Catch:{ Exception -> 0x03bc }
    L_0x0116:
        if (r12 == 0) goto L_0x012c;	 Catch:{ Exception -> 0x03bc }
    L_0x0118:
        r0 = android.hardware.camera2.CameraCharacteristics.CONTROL_AE_COMPENSATION_STEP;	 Catch:{ Exception -> 0x03bc }
        if (r0 == 0) goto L_0x012c;	 Catch:{ Exception -> 0x03bc }
    L_0x011c:
        r12 = r12.floatValue();	 Catch:{ Exception -> 0x03bc }
        r13 = r13.intValue();	 Catch:{ Exception -> 0x03bc }
        r13 = (float) r13;	 Catch:{ Exception -> 0x03bc }
        r12 = r12 * r13;	 Catch:{ Exception -> 0x03bc }
        r12 = java.lang.Float.valueOf(r12);	 Catch:{ Exception -> 0x03bc }
        return r12;	 Catch:{ Exception -> 0x03bc }
    L_0x012c:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9);	 Catch:{ Exception -> 0x03bc }
        return r1;	 Catch:{ Exception -> 0x03bc }
    L_0x0130:
        r12 = r12.characteristics;	 Catch:{ Exception -> 0x03bc }
        r13 = android.hardware.camera2.CameraCharacteristics.SENSOR_INFO_EXPOSURE_TIME_RANGE;	 Catch:{ Exception -> 0x03bc }
        r12 = r12.get(r13);	 Catch:{ Exception -> 0x03bc }
        r12 = (android.util.Range) r12;	 Catch:{ Exception -> 0x03bc }
        if (r12 == 0) goto L_0x0160;	 Catch:{ Exception -> 0x03bc }
    L_0x013c:
        r13 = android.hardware.camera2.CameraCharacteristics.SENSOR_INFO_EXPOSURE_TIME_RANGE;	 Catch:{ Exception -> 0x03bc }
        if (r13 != 0) goto L_0x0141;	 Catch:{ Exception -> 0x03bc }
    L_0x0140:
        goto L_0x0160;	 Catch:{ Exception -> 0x03bc }
    L_0x0141:
        r13 = new float[r6];	 Catch:{ Exception -> 0x03bc }
        r0 = r12.getLower();	 Catch:{ Exception -> 0x03bc }
        r0 = (java.lang.Long) r0;	 Catch:{ Exception -> 0x03bc }
        r2 = r0.doubleValue();	 Catch:{ Exception -> 0x03bc }
        r2 = r2 / r4;	 Catch:{ Exception -> 0x03bc }
        r0 = (float) r2;	 Catch:{ Exception -> 0x03bc }
        r13[r7] = r0;	 Catch:{ Exception -> 0x03bc }
        r12 = r12.getUpper();	 Catch:{ Exception -> 0x03bc }
        r12 = (java.lang.Long) r12;	 Catch:{ Exception -> 0x03bc }
        r2 = r12.doubleValue();	 Catch:{ Exception -> 0x03bc }
        r2 = r2 / r4;	 Catch:{ Exception -> 0x03bc }
        r12 = (float) r2;	 Catch:{ Exception -> 0x03bc }
        r13[r8] = r12;	 Catch:{ Exception -> 0x03bc }
        return r13;	 Catch:{ Exception -> 0x03bc }
    L_0x0160:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9);	 Catch:{ Exception -> 0x03bc }
        return r1;	 Catch:{ Exception -> 0x03bc }
    L_0x0164:
        if (r2 != 0) goto L_0x016a;	 Catch:{ Exception -> 0x03bc }
    L_0x0166:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9);	 Catch:{ Exception -> 0x03bc }
        return r1;	 Catch:{ Exception -> 0x03bc }
    L_0x016a:
        r12 = android.hardware.camera2.CaptureResult.SENSOR_EXPOSURE_TIME;	 Catch:{ Exception -> 0x03bc }
        r12 = r2.get(r12);	 Catch:{ Exception -> 0x03bc }
        r12 = (java.lang.Long) r12;	 Catch:{ Exception -> 0x03bc }
        if (r12 == 0) goto L_0x0183;	 Catch:{ Exception -> 0x03bc }
    L_0x0174:
        r13 = android.hardware.camera2.CaptureResult.SENSOR_EXPOSURE_TIME;	 Catch:{ Exception -> 0x03bc }
        if (r13 == 0) goto L_0x0183;	 Catch:{ Exception -> 0x03bc }
    L_0x0178:
        r12 = r12.doubleValue();	 Catch:{ Exception -> 0x03bc }
        r12 = r12 / r4;	 Catch:{ Exception -> 0x03bc }
        r12 = (float) r12;	 Catch:{ Exception -> 0x03bc }
        r12 = java.lang.Float.valueOf(r12);	 Catch:{ Exception -> 0x03bc }
        return r12;	 Catch:{ Exception -> 0x03bc }
    L_0x0183:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9);	 Catch:{ Exception -> 0x03bc }
        return r1;	 Catch:{ Exception -> 0x03bc }
    L_0x0187:
        r12 = r12.characteristics;	 Catch:{ Exception -> 0x03bc }
        r13 = android.hardware.camera2.CameraCharacteristics.SENSOR_INFO_SENSITIVITY_RANGE;	 Catch:{ Exception -> 0x03bc }
        r12 = r12.get(r13);	 Catch:{ Exception -> 0x03bc }
        r12 = (android.util.Range) r12;	 Catch:{ Exception -> 0x03bc }
        if (r12 == 0) goto L_0x01b3;	 Catch:{ Exception -> 0x03bc }
    L_0x0193:
        r13 = android.hardware.camera2.CameraCharacteristics.SENSOR_INFO_SENSITIVITY_RANGE;	 Catch:{ Exception -> 0x03bc }
        if (r13 != 0) goto L_0x0198;	 Catch:{ Exception -> 0x03bc }
    L_0x0197:
        goto L_0x01b3;	 Catch:{ Exception -> 0x03bc }
    L_0x0198:
        r13 = new float[r6];	 Catch:{ Exception -> 0x03bc }
        r0 = r12.getLower();	 Catch:{ Exception -> 0x03bc }
        r0 = (java.lang.Integer) r0;	 Catch:{ Exception -> 0x03bc }
        r0 = r0.floatValue();	 Catch:{ Exception -> 0x03bc }
        r13[r7] = r0;	 Catch:{ Exception -> 0x03bc }
        r12 = r12.getUpper();	 Catch:{ Exception -> 0x03bc }
        r12 = (java.lang.Integer) r12;	 Catch:{ Exception -> 0x03bc }
        r12 = r12.floatValue();	 Catch:{ Exception -> 0x03bc }
        r13[r8] = r12;	 Catch:{ Exception -> 0x03bc }
        return r13;	 Catch:{ Exception -> 0x03bc }
    L_0x01b3:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9);	 Catch:{ Exception -> 0x03bc }
        return r1;	 Catch:{ Exception -> 0x03bc }
    L_0x01b7:
        if (r2 != 0) goto L_0x01bd;	 Catch:{ Exception -> 0x03bc }
    L_0x01b9:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9);	 Catch:{ Exception -> 0x03bc }
        return r1;	 Catch:{ Exception -> 0x03bc }
    L_0x01bd:
        r12 = android.hardware.camera2.CaptureResult.SENSOR_SENSITIVITY;	 Catch:{ Exception -> 0x03bc }
        r12 = r2.get(r12);	 Catch:{ Exception -> 0x03bc }
        r12 = (java.lang.Integer) r12;	 Catch:{ Exception -> 0x03bc }
        if (r12 == 0) goto L_0x01d4;	 Catch:{ Exception -> 0x03bc }
    L_0x01c7:
        r13 = android.hardware.camera2.CaptureResult.SENSOR_SENSITIVITY;	 Catch:{ Exception -> 0x03bc }
        if (r13 == 0) goto L_0x01d4;	 Catch:{ Exception -> 0x03bc }
    L_0x01cb:
        r12 = r12.floatValue();	 Catch:{ Exception -> 0x03bc }
        r12 = java.lang.Float.valueOf(r12);	 Catch:{ Exception -> 0x03bc }
        return r12;	 Catch:{ Exception -> 0x03bc }
    L_0x01d4:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9);	 Catch:{ Exception -> 0x03bc }
        return r1;	 Catch:{ Exception -> 0x03bc }
    L_0x01d8:
        if (r2 != 0) goto L_0x01de;	 Catch:{ Exception -> 0x03bc }
    L_0x01da:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9);	 Catch:{ Exception -> 0x03bc }
        return r1;	 Catch:{ Exception -> 0x03bc }
    L_0x01de:
        r12 = android.hardware.camera2.CaptureResult.CONTROL_AE_MODE;	 Catch:{ Exception -> 0x03bc }
        r12 = r2.get(r12);	 Catch:{ Exception -> 0x03bc }
        r12 = (java.lang.Integer) r12;	 Catch:{ Exception -> 0x03bc }
        if (r12 == 0) goto L_0x0215;	 Catch:{ Exception -> 0x03bc }
    L_0x01e8:
        r13 = android.hardware.camera2.CaptureResult.CONTROL_AE_MODE;	 Catch:{ Exception -> 0x03bc }
        if (r13 != 0) goto L_0x01ed;	 Catch:{ Exception -> 0x03bc }
    L_0x01ec:
        goto L_0x0215;	 Catch:{ Exception -> 0x03bc }
    L_0x01ed:
        r13 = java.lang.Integer.valueOf(r7);	 Catch:{ Exception -> 0x03bc }
        r13 = r12.equals(r13);	 Catch:{ Exception -> 0x03bc }
        if (r13 == 0) goto L_0x01ff;	 Catch:{ Exception -> 0x03bc }
    L_0x01f7:
        r12 = 805339136; // 0x30008000 float:4.674803E-10 double:3.978904003E-315;	 Catch:{ Exception -> 0x03bc }
        r12 = java.lang.Integer.valueOf(r12);	 Catch:{ Exception -> 0x03bc }
        return r12;	 Catch:{ Exception -> 0x03bc }
    L_0x01ff:
        r13 = java.lang.Integer.valueOf(r8);	 Catch:{ Exception -> 0x03bc }
        r12 = r12.equals(r13);	 Catch:{ Exception -> 0x03bc }
        if (r12 == 0) goto L_0x0211;	 Catch:{ Exception -> 0x03bc }
    L_0x0209:
        r12 = 805322752; // 0x30004000 float:4.665708E-10 double:3.978823056E-315;	 Catch:{ Exception -> 0x03bc }
        r12 = java.lang.Integer.valueOf(r12);	 Catch:{ Exception -> 0x03bc }
        return r12;	 Catch:{ Exception -> 0x03bc }
    L_0x0211:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9);	 Catch:{ Exception -> 0x03bc }
        return r1;	 Catch:{ Exception -> 0x03bc }
    L_0x0215:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9);	 Catch:{ Exception -> 0x03bc }
        return r1;	 Catch:{ Exception -> 0x03bc }
    L_0x0219:
        if (r2 != 0) goto L_0x021f;	 Catch:{ Exception -> 0x03bc }
    L_0x021b:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9);	 Catch:{ Exception -> 0x03bc }
        return r1;	 Catch:{ Exception -> 0x03bc }
    L_0x021f:
        r13 = r12.characteristics;	 Catch:{ Exception -> 0x03bc }
        r4 = android.hardware.camera2.CameraCharacteristics.CONTROL_MAX_REGIONS_AF;	 Catch:{ Exception -> 0x03bc }
        r13 = r13.get(r4);	 Catch:{ Exception -> 0x03bc }
        r13 = (java.lang.Integer) r13;	 Catch:{ Exception -> 0x03bc }
        if (r13 == 0) goto L_0x029e;	 Catch:{ Exception -> 0x03bc }
    L_0x022b:
        r4 = android.hardware.camera2.CameraCharacteristics.CONTROL_MAX_REGIONS_AF;	 Catch:{ Exception -> 0x03bc }
        if (r4 == 0) goto L_0x029e;	 Catch:{ Exception -> 0x03bc }
    L_0x022f:
        r13 = r13.intValue();	 Catch:{ Exception -> 0x03bc }
        if (r13 <= 0) goto L_0x029e;	 Catch:{ Exception -> 0x03bc }
    L_0x0235:
        r13 = android.hardware.camera2.CaptureResult.CONTROL_AF_REGIONS;	 Catch:{ Exception -> 0x03bc }
        if (r13 == 0) goto L_0x029e;	 Catch:{ Exception -> 0x03bc }
    L_0x0239:
        r13 = android.hardware.camera2.CaptureResult.CONTROL_AF_REGIONS;	 Catch:{ Exception -> 0x03bc }
        r13 = r2.get(r13);	 Catch:{ Exception -> 0x03bc }
        r13 = (android.hardware.camera2.params.MeteringRectangle[]) r13;	 Catch:{ Exception -> 0x03bc }
        if (r13 == 0) goto L_0x029d;	 Catch:{ Exception -> 0x03bc }
    L_0x0243:
        r2 = r13.length;	 Catch:{ Exception -> 0x03bc }
        if (r2 != 0) goto L_0x0247;	 Catch:{ Exception -> 0x03bc }
    L_0x0246:
        goto L_0x029d;	 Catch:{ Exception -> 0x03bc }
    L_0x0247:
        r12 = r12.characteristics;	 Catch:{ Exception -> 0x03bc }
        r2 = android.hardware.camera2.CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE;	 Catch:{ Exception -> 0x03bc }
        r12 = r12.get(r2);	 Catch:{ Exception -> 0x03bc }
        r12 = (android.graphics.Rect) r12;	 Catch:{ Exception -> 0x03bc }
        if (r12 != 0) goto L_0x025b;	 Catch:{ Exception -> 0x03bc }
    L_0x0253:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9);	 Catch:{ Exception -> 0x03bc }
        r12 = java.lang.Boolean.valueOf(r7);	 Catch:{ Exception -> 0x03bc }
        return r12;	 Catch:{ Exception -> 0x03bc }
    L_0x025b:
        r2 = r13[r7];	 Catch:{ Exception -> 0x03bc }
        r2 = r2.getRect();	 Catch:{ Exception -> 0x03bc }
        r4 = 5;	 Catch:{ Exception -> 0x03bc }
        r4 = new float[r4];	 Catch:{ Exception -> 0x03bc }
        r5 = r2.left;	 Catch:{ Exception -> 0x03bc }
        r10 = r12.width();	 Catch:{ Exception -> 0x03bc }
        r10 = r10 - r8;	 Catch:{ Exception -> 0x03bc }
        r5 = r5 / r10;	 Catch:{ Exception -> 0x03bc }
        r5 = (float) r5;	 Catch:{ Exception -> 0x03bc }
        r4[r7] = r5;	 Catch:{ Exception -> 0x03bc }
        r5 = r2.top;	 Catch:{ Exception -> 0x03bc }
        r10 = r12.height();	 Catch:{ Exception -> 0x03bc }
        r10 = r10 - r8;	 Catch:{ Exception -> 0x03bc }
        r5 = r5 / r10;	 Catch:{ Exception -> 0x03bc }
        r5 = (float) r5;	 Catch:{ Exception -> 0x03bc }
        r4[r8] = r5;	 Catch:{ Exception -> 0x03bc }
        r5 = r2.right;	 Catch:{ Exception -> 0x03bc }
        r10 = r12.width();	 Catch:{ Exception -> 0x03bc }
        r10 = r10 - r8;	 Catch:{ Exception -> 0x03bc }
        r5 = r5 / r10;	 Catch:{ Exception -> 0x03bc }
        r5 = (float) r5;	 Catch:{ Exception -> 0x03bc }
        r4[r6] = r5;	 Catch:{ Exception -> 0x03bc }
        r2 = r2.bottom;	 Catch:{ Exception -> 0x03bc }
        r12 = r12.height();	 Catch:{ Exception -> 0x03bc }
        r12 = r12 - r8;	 Catch:{ Exception -> 0x03bc }
        r2 = r2 / r12;	 Catch:{ Exception -> 0x03bc }
        r12 = (float) r2;	 Catch:{ Exception -> 0x03bc }
        r4[r3] = r12;	 Catch:{ Exception -> 0x03bc }
        r12 = r13[r7];	 Catch:{ Exception -> 0x03bc }
        r12 = r12.getMeteringWeight();	 Catch:{ Exception -> 0x03bc }
        r12 = r12 - r7;	 Catch:{ Exception -> 0x03bc }
        r12 = r12 / 1000;	 Catch:{ Exception -> 0x03bc }
        r12 = (float) r12;	 Catch:{ Exception -> 0x03bc }
        r4[r0] = r12;	 Catch:{ Exception -> 0x03bc }
        return r4;	 Catch:{ Exception -> 0x03bc }
    L_0x029d:
        return r1;	 Catch:{ Exception -> 0x03bc }
    L_0x029e:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9);	 Catch:{ Exception -> 0x03bc }
        return r1;	 Catch:{ Exception -> 0x03bc }
    L_0x02a2:
        if (r2 != 0) goto L_0x02a8;	 Catch:{ Exception -> 0x03bc }
    L_0x02a4:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9);	 Catch:{ Exception -> 0x03bc }
        return r1;	 Catch:{ Exception -> 0x03bc }
    L_0x02a8:
        r12 = android.hardware.camera2.CaptureResult.LENS_FOCUS_RANGE;	 Catch:{ Exception -> 0x03bc }
        r12 = r2.get(r12);	 Catch:{ Exception -> 0x03bc }
        r12 = (android.util.Pair) r12;	 Catch:{ Exception -> 0x03bc }
        if (r12 == 0) goto L_0x02cd;	 Catch:{ Exception -> 0x03bc }
    L_0x02b2:
        r13 = android.hardware.camera2.CaptureResult.LENS_FOCUS_RANGE;	 Catch:{ Exception -> 0x03bc }
        if (r13 == 0) goto L_0x02cd;	 Catch:{ Exception -> 0x03bc }
    L_0x02b6:
        r13 = new float[r6];	 Catch:{ Exception -> 0x03bc }
        r0 = r12.first;	 Catch:{ Exception -> 0x03bc }
        r0 = (java.lang.Float) r0;	 Catch:{ Exception -> 0x03bc }
        r0 = r0.floatValue();	 Catch:{ Exception -> 0x03bc }
        r13[r7] = r0;	 Catch:{ Exception -> 0x03bc }
        r12 = r12.second;	 Catch:{ Exception -> 0x03bc }
        r12 = (java.lang.Float) r12;	 Catch:{ Exception -> 0x03bc }
        r12 = r12.floatValue();	 Catch:{ Exception -> 0x03bc }
        r13[r8] = r12;	 Catch:{ Exception -> 0x03bc }
        return r13;	 Catch:{ Exception -> 0x03bc }
    L_0x02cd:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9);	 Catch:{ Exception -> 0x03bc }
        return r1;	 Catch:{ Exception -> 0x03bc }
    L_0x02d1:
        if (r2 != 0) goto L_0x02d7;	 Catch:{ Exception -> 0x03bc }
    L_0x02d3:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9);	 Catch:{ Exception -> 0x03bc }
        return r1;	 Catch:{ Exception -> 0x03bc }
    L_0x02d7:
        r12 = android.hardware.camera2.CaptureResult.LENS_FOCAL_LENGTH;	 Catch:{ Exception -> 0x03bc }
        r12 = r2.get(r12);	 Catch:{ Exception -> 0x03bc }
        r12 = (java.lang.Float) r12;	 Catch:{ Exception -> 0x03bc }
        if (r12 == 0) goto L_0x02e7;	 Catch:{ Exception -> 0x03bc }
    L_0x02e1:
        r13 = android.hardware.camera2.CaptureResult.LENS_FOCAL_LENGTH;	 Catch:{ Exception -> 0x03bc }
        if (r13 != 0) goto L_0x02e6;	 Catch:{ Exception -> 0x03bc }
    L_0x02e5:
        goto L_0x02e7;	 Catch:{ Exception -> 0x03bc }
    L_0x02e6:
        return r12;	 Catch:{ Exception -> 0x03bc }
    L_0x02e7:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9);	 Catch:{ Exception -> 0x03bc }
        return r1;	 Catch:{ Exception -> 0x03bc }
    L_0x02eb:
        if (r2 != 0) goto L_0x02f1;	 Catch:{ Exception -> 0x03bc }
    L_0x02ed:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9);	 Catch:{ Exception -> 0x03bc }
        return r1;	 Catch:{ Exception -> 0x03bc }
    L_0x02f1:
        r13 = android.hardware.camera2.CaptureResult.CONTROL_AF_MODE;	 Catch:{ Exception -> 0x03bc }
        r13 = r2.get(r13);	 Catch:{ Exception -> 0x03bc }
        r13 = (java.lang.Integer) r13;	 Catch:{ Exception -> 0x03bc }
        if (r13 == 0) goto L_0x0377;	 Catch:{ Exception -> 0x03bc }
    L_0x02fb:
        r0 = android.hardware.camera2.CaptureResult.CONTROL_AF_MODE;	 Catch:{ Exception -> 0x03bc }
        if (r0 != 0) goto L_0x0301;	 Catch:{ Exception -> 0x03bc }
    L_0x02ff:
        goto L_0x0377;	 Catch:{ Exception -> 0x03bc }
    L_0x0301:
        r0 = java.lang.Integer.valueOf(r8);	 Catch:{ Exception -> 0x03bc }
        r0 = r13.equals(r0);	 Catch:{ Exception -> 0x03bc }
        if (r0 == 0) goto L_0x031b;	 Catch:{ Exception -> 0x03bc }
    L_0x030b:
        r12 = r12.isAutoFocusing;	 Catch:{ Exception -> 0x03bc }
        if (r12 == 0) goto L_0x0313;	 Catch:{ Exception -> 0x03bc }
    L_0x030f:
        r12 = 805306400; // 0x30000020 float:4.6566306E-10 double:3.978742266E-315;	 Catch:{ Exception -> 0x03bc }
        goto L_0x0316;	 Catch:{ Exception -> 0x03bc }
    L_0x0313:
        r12 = 805306384; // 0x30000010 float:4.656622E-10 double:3.978742187E-315;	 Catch:{ Exception -> 0x03bc }
    L_0x0316:
        r12 = java.lang.Integer.valueOf(r12);	 Catch:{ Exception -> 0x03bc }
        return r12;	 Catch:{ Exception -> 0x03bc }
    L_0x031b:
        r12 = java.lang.Integer.valueOf(r3);	 Catch:{ Exception -> 0x03bc }
        r12 = r13.equals(r12);	 Catch:{ Exception -> 0x03bc }
        if (r12 == 0) goto L_0x032d;	 Catch:{ Exception -> 0x03bc }
    L_0x0325:
        r12 = 805306432; // 0x30000040 float:4.6566484E-10 double:3.978742424E-315;	 Catch:{ Exception -> 0x03bc }
        r12 = java.lang.Integer.valueOf(r12);	 Catch:{ Exception -> 0x03bc }
        return r12;	 Catch:{ Exception -> 0x03bc }
    L_0x032d:
        r12 = java.lang.Integer.valueOf(r7);	 Catch:{ Exception -> 0x03bc }
        r12 = r13.equals(r12);	 Catch:{ Exception -> 0x03bc }
        if (r12 == 0) goto L_0x0361;	 Catch:{ Exception -> 0x03bc }
    L_0x0337:
        r12 = android.hardware.camera2.CaptureResult.LENS_FOCUS_DISTANCE;	 Catch:{ Exception -> 0x03bc }
        r12 = r2.get(r12);	 Catch:{ Exception -> 0x03bc }
        r12 = (java.lang.Float) r12;	 Catch:{ Exception -> 0x03bc }
        if (r12 == 0) goto L_0x0359;	 Catch:{ Exception -> 0x03bc }
    L_0x0341:
        r13 = android.hardware.camera2.CaptureResult.LENS_FOCUS_DISTANCE;	 Catch:{ Exception -> 0x03bc }
        if (r13 == 0) goto L_0x0359;	 Catch:{ Exception -> 0x03bc }
    L_0x0345:
        r13 = 0;	 Catch:{ Exception -> 0x03bc }
        r13 = java.lang.Float.valueOf(r13);	 Catch:{ Exception -> 0x03bc }
        r12 = r12.equals(r13);	 Catch:{ Exception -> 0x03bc }
        if (r12 != 0) goto L_0x0351;	 Catch:{ Exception -> 0x03bc }
    L_0x0350:
        goto L_0x0359;	 Catch:{ Exception -> 0x03bc }
    L_0x0351:
        r12 = 805306624; // 0x30000100 float:4.656755E-10 double:3.978743373E-315;	 Catch:{ Exception -> 0x03bc }
        r12 = java.lang.Integer.valueOf(r12);	 Catch:{ Exception -> 0x03bc }
        return r12;	 Catch:{ Exception -> 0x03bc }
    L_0x0359:
        r12 = 805306880; // 0x30000200 float:4.656897E-10 double:3.97874464E-315;	 Catch:{ Exception -> 0x03bc }
        r12 = java.lang.Integer.valueOf(r12);	 Catch:{ Exception -> 0x03bc }
        return r12;	 Catch:{ Exception -> 0x03bc }
    L_0x0361:
        r12 = java.lang.Integer.valueOf(r6);	 Catch:{ Exception -> 0x03bc }
        r12 = r13.equals(r12);	 Catch:{ Exception -> 0x03bc }
        if (r12 == 0) goto L_0x0373;	 Catch:{ Exception -> 0x03bc }
    L_0x036b:
        r12 = 805306496; // 0x30000080 float:4.656684E-10 double:3.97874274E-315;	 Catch:{ Exception -> 0x03bc }
        r12 = java.lang.Integer.valueOf(r12);	 Catch:{ Exception -> 0x03bc }
        return r12;	 Catch:{ Exception -> 0x03bc }
    L_0x0373:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9);	 Catch:{ Exception -> 0x03bc }
        return r1;	 Catch:{ Exception -> 0x03bc }
    L_0x0377:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9);	 Catch:{ Exception -> 0x03bc }
        return r1;	 Catch:{ Exception -> 0x03bc }
    L_0x037b:
        if (r2 != 0) goto L_0x0381;	 Catch:{ Exception -> 0x03bc }
    L_0x037d:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9);	 Catch:{ Exception -> 0x03bc }
        return r1;	 Catch:{ Exception -> 0x03bc }
    L_0x0381:
        r12 = android.hardware.camera2.CaptureResult.FLASH_MODE;	 Catch:{ Exception -> 0x03bc }
        r12 = r2.get(r12);	 Catch:{ Exception -> 0x03bc }
        r12 = (java.lang.Integer) r12;	 Catch:{ Exception -> 0x03bc }
        if (r12 == 0) goto L_0x03b8;	 Catch:{ Exception -> 0x03bc }
    L_0x038b:
        r13 = android.hardware.camera2.CaptureResult.FLASH_MODE;	 Catch:{ Exception -> 0x03bc }
        if (r13 != 0) goto L_0x0390;	 Catch:{ Exception -> 0x03bc }
    L_0x038f:
        goto L_0x03b8;	 Catch:{ Exception -> 0x03bc }
    L_0x0390:
        r13 = java.lang.Integer.valueOf(r6);	 Catch:{ Exception -> 0x03bc }
        r13 = r12.equals(r13);	 Catch:{ Exception -> 0x03bc }
        if (r13 == 0) goto L_0x03a2;	 Catch:{ Exception -> 0x03bc }
    L_0x039a:
        r12 = 805306370; // 0x30000002 float:4.656614E-10 double:3.97874212E-315;	 Catch:{ Exception -> 0x03bc }
        r12 = java.lang.Integer.valueOf(r12);	 Catch:{ Exception -> 0x03bc }
        return r12;	 Catch:{ Exception -> 0x03bc }
    L_0x03a2:
        r13 = java.lang.Integer.valueOf(r7);	 Catch:{ Exception -> 0x03bc }
        r12 = r12.equals(r13);	 Catch:{ Exception -> 0x03bc }
        if (r12 == 0) goto L_0x03b4;	 Catch:{ Exception -> 0x03bc }
    L_0x03ac:
        r12 = 805306369; // 0x30000001 float:4.6566134E-10 double:3.978742113E-315;	 Catch:{ Exception -> 0x03bc }
        r12 = java.lang.Integer.valueOf(r12);	 Catch:{ Exception -> 0x03bc }
        return r12;	 Catch:{ Exception -> 0x03bc }
    L_0x03b4:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9);	 Catch:{ Exception -> 0x03bc }
        return r1;	 Catch:{ Exception -> 0x03bc }
    L_0x03b8:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9);	 Catch:{ Exception -> 0x03bc }
        return r1;
    L_0x03bc:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r9);
        return r1;
    L_0x03c0:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r0);
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.Camera2_Preview.getTypedCameraParameter(int, int):java.lang.Object");
    }

    int getStatus(int i) {
        i = getCameraCacheInfo(i);
        if (i != 0) {
            return i.status;
        }
        SystemTools.setSystemErrorCode(4);
        return AR_CAMERA_STATUS_UNKNOWN;
    }
}
