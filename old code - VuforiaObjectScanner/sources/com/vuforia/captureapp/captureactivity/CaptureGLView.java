package com.vuforia.captureapp.captureactivity;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.EGLConfigChooser;
import android.opengl.GLSurfaceView.EGLContextFactory;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;

public class CaptureGLView extends GLSurfaceView {
    private static boolean mUseOpenGLES2 = true;

    private static class ConfigChooser implements EGLConfigChooser {
        protected int mAlphaSize;
        protected int mBlueSize;
        protected int mDepthSize;
        protected int mGreenSize;
        protected int mRedSize;
        protected int mStencilSize;
        private int[] mValue = new int[1];

        public ConfigChooser(int i, int i2, int i3, int i4, int i5, int i6) {
            this.mRedSize = i;
            this.mGreenSize = i2;
            this.mBlueSize = i3;
            this.mAlphaSize = i4;
            this.mDepthSize = i5;
            this.mStencilSize = i6;
        }

        private EGLConfig getMatchingConfig(EGL10 egl10, EGLDisplay eGLDisplay, int[] iArr) {
            int[] iArr2 = new int[1];
            egl10.eglChooseConfig(eGLDisplay, iArr, null, 0, iArr2);
            int i = iArr2[0];
            if (i > 0) {
                EGLConfig[] eGLConfigArr = new EGLConfig[i];
                egl10.eglChooseConfig(eGLDisplay, iArr, eGLConfigArr, i, iArr2);
                return chooseConfig(egl10, eGLDisplay, eGLConfigArr);
            }
            throw new IllegalArgumentException("No matching EGL configs");
        }

        public EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay) {
            if (CaptureGLView.mUseOpenGLES2) {
                return getMatchingConfig(egl10, eGLDisplay, new int[]{12324, 4, 12323, 4, 12322, 4, 12352, 4, 12344});
            }
            return getMatchingConfig(egl10, eGLDisplay, new int[]{12324, 5, 12323, 6, 12322, 5, 12352, 1, 12344});
        }

        public EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr) {
            for (EGLConfig eGLConfig : eGLConfigArr) {
                EGL10 egl102 = egl10;
                EGLDisplay eGLDisplay2 = eGLDisplay;
                EGLConfig eGLConfig2 = eGLConfig;
                int findConfigAttrib = findConfigAttrib(egl102, eGLDisplay2, eGLConfig2, 12325, 0);
                int findConfigAttrib2 = findConfigAttrib(egl102, eGLDisplay2, eGLConfig2, 12326, 0);
                if (findConfigAttrib >= this.mDepthSize) {
                    if (findConfigAttrib2 >= this.mStencilSize) {
                        egl102 = egl10;
                        eGLDisplay2 = eGLDisplay;
                        eGLConfig2 = eGLConfig;
                        findConfigAttrib = findConfigAttrib(egl102, eGLDisplay2, eGLConfig2, 12324, 0);
                        int findConfigAttrib3 = findConfigAttrib(egl102, eGLDisplay2, eGLConfig2, 12323, 0);
                        int findConfigAttrib4 = findConfigAttrib(egl102, eGLDisplay2, eGLConfig2, 12322, 0);
                        findConfigAttrib2 = findConfigAttrib(egl102, eGLDisplay2, eGLConfig2, 12321, 0);
                        if (findConfigAttrib == this.mRedSize && findConfigAttrib3 == this.mGreenSize && findConfigAttrib4 == this.mBlueSize && findConfigAttrib2 == this.mAlphaSize) {
                            return eGLConfig;
                        }
                    }
                }
            }
            return null;
        }

        private int findConfigAttrib(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, int i, int i2) {
            return egl10.eglGetConfigAttrib(eGLDisplay, eGLConfig, i, this.mValue) != null ? this.mValue[null] : i2;
        }
    }

    private static class ContextFactory implements EGLContextFactory {
        private static int EGL_CONTEXT_CLIENT_VERSION = 12440;

        private ContextFactory() {
        }

        public EGLContext createContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig) {
            if (CaptureGLView.mUseOpenGLES2) {
                DebugLog.LOGI("Creating OpenGL ES 2.0 context");
                CaptureGLView.checkEglError("Before eglCreateContext", egl10);
                eGLDisplay = egl10.eglCreateContext(eGLDisplay, eGLConfig, EGL10.EGL_NO_CONTEXT, new int[]{EGL_CONTEXT_CLIENT_VERSION, 2, 12344});
            } else {
                DebugLog.LOGI("Creating OpenGL ES 1.x context");
                CaptureGLView.checkEglError("Before eglCreateContext", egl10);
                eGLDisplay = egl10.eglCreateContext(eGLDisplay, eGLConfig, EGL10.EGL_NO_CONTEXT, new int[]{EGL_CONTEXT_CLIENT_VERSION, 1, 12344});
            }
            CaptureGLView.checkEglError("After eglCreateContext", egl10);
            return eGLDisplay;
        }

        public void destroyContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLContext eGLContext) {
            egl10.eglDestroyContext(eGLDisplay, eGLContext);
        }
    }

    public CaptureGLView(Context context) {
        super(context);
    }

    public void init(int i, boolean z, int i2, int i3) {
        boolean z2 = true;
        if ((i & 1) == 0) {
            z2 = false;
        }
        mUseOpenGLES2 = z2;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Using OpenGL ES ");
        stringBuilder.append(mUseOpenGLES2 ? "2.0" : "1.x");
        DebugLog.LOGI(stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("Using ");
        stringBuilder.append(z ? "translucent" : "opaque");
        stringBuilder.append(" GLView, depth buffer size: ");
        stringBuilder.append(i2);
        stringBuilder.append(", stencil size: ");
        stringBuilder.append(i3);
        DebugLog.LOGI(stringBuilder.toString());
        if (z) {
            getHolder().setFormat(-3);
        }
        setEGLContextFactory(new ContextFactory());
        EGLConfigChooser configChooser;
        if (z) {
            configChooser = new ConfigChooser(8, 8, 8, 8, i2, i3);
        } else {
            configChooser = new ConfigChooser(5, 6, 5, 0, i2, i3);
        }
        setEGLConfigChooser(r0);
    }

    private static void checkEglError(String str, EGL10 egl10) {
        while (egl10.eglGetError() != 12288) {
            DebugLog.LOGE(String.format("%s: EGL error: 0x%x", new Object[]{str, Integer.valueOf(egl10.eglGetError())}));
        }
    }
}
