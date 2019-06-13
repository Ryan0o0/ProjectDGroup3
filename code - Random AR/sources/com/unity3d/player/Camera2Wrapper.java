package com.unity3d.player;

import android.content.Context;
import android.graphics.Rect;
import android.hardware.Camera.Area;

public class Camera2Wrapper implements C0039d {
    /* renamed from: a */
    private Context f279a;
    /* renamed from: b */
    private C0033a f280b = null;
    /* renamed from: c */
    private final int f281c = 100;

    public Camera2Wrapper(Context context) {
        this.f279a = context;
        initCamera2Jni();
    }

    /* renamed from: a */
    private static int m142a(float f) {
        return (int) Math.min(Math.max((f * 2000.0f) - 0.0040893555f, -900.0f), 900.0f);
    }

    private final native void deinitCamera2Jni();

    private final native void initCamera2Jni();

    private final native void nativeFrameReady(Object obj, Object obj2, Object obj3, int i, int i2, int i3);

    private final native void nativeSurfaceTextureReady(Object obj);

    /* renamed from: a */
    public final void m143a() {
        deinitCamera2Jni();
        closeCamera2();
    }

    /* renamed from: a */
    public final void mo1a(Object obj) {
        nativeSurfaceTextureReady(obj);
    }

    /* renamed from: a */
    public final void mo2a(Object obj, Object obj2, Object obj3, int i, int i2, int i3) {
        nativeFrameReady(obj, obj2, obj3, i, i2, i3);
    }

    protected void closeCamera2() {
        if (this.f280b != null) {
            this.f280b.m72b();
        }
        this.f280b = null;
    }

    protected int getCamera2Count() {
        return C0044j.f176b ? C0033a.m35a(this.f279a) : 0;
    }

    protected int[] getCamera2Resolutions(int i) {
        return C0044j.f176b ? C0033a.m57d(this.f279a, i) : null;
    }

    protected int getCamera2SensorOrientation(int i) {
        return C0044j.f176b ? C0033a.m36a(this.f279a, i) : 0;
    }

    protected Object getCameraFocusArea(float f, float f2) {
        int a = m142a(f);
        int a2 = m142a(1.0f - f2);
        return new Area(new Rect(a - 100, a2 - 100, a + 100, a2 + 100), 1000);
    }

    protected Rect getFrameSizeCamera2() {
        return this.f280b != null ? this.f280b.m69a() : new Rect();
    }

    protected boolean initializeCamera2(int i, int i2, int i3, int i4, int i5) {
        if (!C0044j.f176b || this.f280b != null || UnityPlayer.currentActivity == null) {
            return false;
        }
        this.f280b = new C0033a(this);
        return this.f280b.m71a(this.f279a, i, i2, i3, i4, i5);
    }

    protected boolean isCamera2AutoFocusPointSupported(int i) {
        return C0044j.f176b ? C0033a.m54c(this.f279a, i) : false;
    }

    protected boolean isCamera2FrontFacing(int i) {
        return C0044j.f176b ? C0033a.m52b(this.f279a, i) : false;
    }

    protected void pauseCamera2() {
        if (this.f280b != null) {
            this.f280b.m74d();
        }
    }

    protected boolean setAutoFocusPoint(float f, float f2) {
        return (!C0044j.f176b || this.f280b == null) ? false : this.f280b.m70a(f, f2);
    }

    protected void startCamera2() {
        if (this.f280b != null) {
            this.f280b.m73c();
        }
    }

    protected void stopCamera2() {
        if (this.f280b != null) {
            this.f280b.m75e();
        }
    }
}
