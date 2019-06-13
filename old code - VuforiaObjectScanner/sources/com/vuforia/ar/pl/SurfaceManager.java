package com.vuforia.ar.pl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.View;
import android.view.ViewGroup;
import com.vuforia.ar.pl.Camera1_Preview.CameraCacheInfo;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SurfaceManager {
    private static final String MODULENAME = "SurfaceManager";
    Lock addSurfaceLock = new ReentrantLock();
    View cameraSurfaceParentView = null;
    CameraCacheInfo cciForSurface;
    GLSurfaceView glSurfaceView = null;
    int glSurfaceViewChildPosition = 0;
    boolean renderWhenDirtyEnabled = false;
    Lock viewLock = new ReentrantLock();

    /* renamed from: com.vuforia.ar.pl.SurfaceManager$1 */
    class C00071 implements Runnable {
        C00071() {
        }

        public void run() {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1630521067.run(Unknown Source)
*/
            /*
            r5 = this;
            r0 = com.vuforia.ar.pl.SurfaceManager.this;
            r0 = r0.addSurfaceLock;
            r0.lock();
            r0 = com.vuforia.ar.pl.SurfaceManager.this;
            r0.retrieveGLSurfaceView();
            r0 = com.vuforia.ar.pl.SurfaceManager.this;	 Catch:{ Exception -> 0x0044, all -> 0x003b }
            r1 = com.vuforia.ar.pl.SurfaceManager.this;	 Catch:{ Exception -> 0x0044, all -> 0x003b }
            r1 = r1.cciForSurface;	 Catch:{ Exception -> 0x0044, all -> 0x003b }
            r0.setupCameraSurface(r1);	 Catch:{ Exception -> 0x0044, all -> 0x003b }
            r0 = com.vuforia.ar.pl.SurfaceManager.this;	 Catch:{ Exception -> 0x0044, all -> 0x003b }
            r0 = r0.cameraSurfaceParentView;	 Catch:{ Exception -> 0x0044, all -> 0x003b }
            r0 = (android.view.ViewGroup) r0;	 Catch:{ Exception -> 0x0044, all -> 0x003b }
            r1 = com.vuforia.ar.pl.SurfaceManager.this;	 Catch:{ Exception -> 0x0044, all -> 0x003b }
            r1 = r1.cciForSurface;	 Catch:{ Exception -> 0x0044, all -> 0x003b }
            r1 = r1.surface;	 Catch:{ Exception -> 0x0044, all -> 0x003b }
            r2 = com.vuforia.ar.pl.SurfaceManager.this;	 Catch:{ Exception -> 0x0044, all -> 0x003b }
            r2 = r2.glSurfaceViewChildPosition;	 Catch:{ Exception -> 0x0044, all -> 0x003b }
            r2 = r2 + 1;	 Catch:{ Exception -> 0x0044, all -> 0x003b }
            r3 = new android.widget.FrameLayout$LayoutParams;	 Catch:{ Exception -> 0x0044, all -> 0x003b }
            r4 = -1;	 Catch:{ Exception -> 0x0044, all -> 0x003b }
            r3.<init>(r4, r4);	 Catch:{ Exception -> 0x0044, all -> 0x003b }
            r0.addView(r1, r2, r3);	 Catch:{ Exception -> 0x0044, all -> 0x003b }
            r0 = com.vuforia.ar.pl.SurfaceManager.this;	 Catch:{ Exception -> 0x0044, all -> 0x003b }
            r0 = r0.cciForSurface;	 Catch:{ Exception -> 0x0044, all -> 0x003b }
            r0 = r0.surface;	 Catch:{ Exception -> 0x0044, all -> 0x003b }
            r1 = 0;	 Catch:{ Exception -> 0x0044, all -> 0x003b }
            r0.setVisibility(r1);	 Catch:{ Exception -> 0x0044, all -> 0x003b }
            goto L_0x0044;
        L_0x003b:
            r0 = move-exception;
            r1 = com.vuforia.ar.pl.SurfaceManager.this;
            r1 = r1.addSurfaceLock;
            r1.unlock();
            throw r0;
        L_0x0044:
            r0 = com.vuforia.ar.pl.SurfaceManager.this;
            r0 = r0.addSurfaceLock;
            r0.unlock();
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.SurfaceManager.1.run():void");
        }
    }

    private android.opengl.GLSurfaceView searchForGLSurfaceView(android.view.View r7) {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1630521067.run(Unknown Source)
*/
        /*
        r6 = this;
        r0 = 0;
        r6.glSurfaceViewChildPosition = r0;
        r1 = 0;
        r7 = (android.view.ViewGroup) r7;	 Catch:{ Exception -> 0x002a }
        r2 = r7.getChildCount();	 Catch:{ Exception -> 0x002a }
        r3 = r1;	 Catch:{ Exception -> 0x002a }
    L_0x000b:
        if (r0 >= r2) goto L_0x0029;	 Catch:{ Exception -> 0x002a }
    L_0x000d:
        r4 = r7.getChildAt(r0);	 Catch:{ Exception -> 0x002a }
        r5 = r4 instanceof android.opengl.GLSurfaceView;	 Catch:{ Exception -> 0x002a }
        if (r5 == 0) goto L_0x001b;	 Catch:{ Exception -> 0x002a }
    L_0x0015:
        r3 = r4;	 Catch:{ Exception -> 0x002a }
        r3 = (android.opengl.GLSurfaceView) r3;	 Catch:{ Exception -> 0x002a }
        r6.glSurfaceViewChildPosition = r0;	 Catch:{ Exception -> 0x002a }
        goto L_0x0029;	 Catch:{ Exception -> 0x002a }
    L_0x001b:
        r5 = r4 instanceof android.view.ViewGroup;	 Catch:{ Exception -> 0x002a }
        if (r5 == 0) goto L_0x0026;	 Catch:{ Exception -> 0x002a }
    L_0x001f:
        r3 = r6.searchForGLSurfaceView(r4);	 Catch:{ Exception -> 0x002a }
        if (r3 == 0) goto L_0x0026;
    L_0x0025:
        goto L_0x0029;
    L_0x0026:
        r0 = r0 + 1;
        goto L_0x000b;
    L_0x0029:
        return r3;
    L_0x002a:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.SurfaceManager.searchForGLSurfaceView(android.view.View):android.opengl.GLSurfaceView");
    }

    private boolean applyRenderWhenDirty() {
        if (this.glSurfaceView == null) {
            return false;
        }
        this.glSurfaceView.setRenderMode(this.renderWhenDirtyEnabled ^ 1);
        return true;
    }

    private void setupCameraSurface(CameraCacheInfo cameraCacheInfo) {
        if (cameraCacheInfo.surface == null) {
            Context activityFromNative = SystemTools.getActivityFromNative();
            if (activityFromNative != null) {
                cameraCacheInfo.surface = new CameraSurface(activityFromNative);
            } else {
                return;
            }
        } else if (cameraCacheInfo.surface.getParent() != null && ViewGroup.class.isInstance(cameraCacheInfo.surface.getParent())) {
            ((ViewGroup) cameraCacheInfo.surface.getParent()).removeView(cameraCacheInfo.surface);
        }
        cameraCacheInfo.surface.setCamera(cameraCacheInfo.camera);
    }

    public boolean retrieveGLSurfaceView() {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1630521067.run(Unknown Source)
*/
        /*
        r3 = this;
        r0 = 0;
        r1 = com.vuforia.ar.pl.SystemTools.getActivityFromNative();	 Catch:{ Exception -> 0x002d }
        if (r1 != 0) goto L_0x0008;	 Catch:{ Exception -> 0x002d }
    L_0x0007:
        return r0;	 Catch:{ Exception -> 0x002d }
    L_0x0008:
        r1 = r1.getWindow();	 Catch:{ Exception -> 0x002d }
        r1 = r1.getDecorView();	 Catch:{ Exception -> 0x002d }
        r2 = r3.searchForGLSurfaceView(r1);	 Catch:{ Exception -> 0x002d }
        r3.glSurfaceView = r2;	 Catch:{ Exception -> 0x002d }
        r2 = r3.glSurfaceView;	 Catch:{ Exception -> 0x002d }
        if (r2 != 0) goto L_0x001d;	 Catch:{ Exception -> 0x002d }
    L_0x001a:
        r3.cameraSurfaceParentView = r1;	 Catch:{ Exception -> 0x002d }
        goto L_0x0027;	 Catch:{ Exception -> 0x002d }
    L_0x001d:
        r1 = r3.glSurfaceView;	 Catch:{ Exception -> 0x002d }
        r1 = r1.getParent();	 Catch:{ Exception -> 0x002d }
        r1 = (android.view.View) r1;	 Catch:{ Exception -> 0x002d }
        r3.cameraSurfaceParentView = r1;	 Catch:{ Exception -> 0x002d }
    L_0x0027:
        r1 = r3.glSurfaceView;
        if (r1 == 0) goto L_0x002c;
    L_0x002b:
        r0 = 1;
    L_0x002c:
        return r0;
    L_0x002d:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.SurfaceManager.retrieveGLSurfaceView():boolean");
    }

    public boolean setEnableRenderWhenDirty(boolean z) {
        this.renderWhenDirtyEnabled = z;
        return applyRenderWhenDirty();
    }

    public void requestRender() {
        if (this.glSurfaceView != null) {
            this.glSurfaceView.requestRender();
        }
    }

    public boolean addCameraSurface(com.vuforia.ar.pl.Camera1_Preview.CameraCacheInfo r4) {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1630521067.run(Unknown Source)
*/
        /*
        r3 = this;
        r0 = com.vuforia.ar.pl.SystemTools.getActivityFromNative();
        r1 = 0;
        if (r0 != 0) goto L_0x0008;
    L_0x0007:
        return r1;
    L_0x0008:
        r3.cciForSurface = r4;
        r4 = r3.viewLock;
        r4.lock();
        r4 = 1;
        r2 = new com.vuforia.ar.pl.SurfaceManager$1;	 Catch:{ Exception -> 0x0025, all -> 0x001e }
        r2.<init>();	 Catch:{ Exception -> 0x0025, all -> 0x001e }
        r0.runOnUiThread(r2);	 Catch:{ Exception -> 0x0025, all -> 0x001e }
        r0 = r3.viewLock;
        r0.unlock();
        goto L_0x002b;
    L_0x001e:
        r4 = move-exception;
        r0 = r3.viewLock;
        r0.unlock();
        throw r4;
    L_0x0025:
        r0 = r3.viewLock;
        r0.unlock();
        r1 = 1;
    L_0x002b:
        r4 = r4 ^ r1;
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.SurfaceManager.addCameraSurface(com.vuforia.ar.pl.Camera1_Preview$CameraCacheInfo):boolean");
    }
}
