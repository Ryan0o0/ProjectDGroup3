package com.vuforia.captureapp.captureactivity;

import android.opengl.GLSurfaceView.Renderer;
import android.os.Handler;
import android.os.Message;
import com.vuforia.Vuforia;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class CaptureGLRenderer implements Renderer {
    public static Handler mainActivityHandler;
    public CaptureActivity mActivity;
    public boolean mIsActive = false;

    public native void initRendering();

    public native void renderFrame();

    public native void updateRendering(int i, int i2);

    public void displayMessage(String str) {
        Message message = new Message();
        message.obj = str;
        mainActivityHandler.sendMessage(message);
    }

    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        DebugLog.LOGD("GLRenderer::onSurfaceCreated");
        initRendering();
        Vuforia.onSurfaceCreated();
    }

    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        DebugLog.LOGD("GLRenderer::onSurfaceChanged");
        updateRendering(i, i2);
        Vuforia.onSurfaceChanged(i, i2);
    }

    public void onDrawFrame(GL10 gl10) {
        if (this.mIsActive != null) {
            this.mActivity.updateRenderView();
            renderFrame();
        }
    }
}
