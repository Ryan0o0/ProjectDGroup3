package com.vuforia.ar.pl;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

class CameraSurface extends SurfaceView implements Callback {
    private static final String MODULENAME = "CameraSurface";
    Camera camera = null;
    SurfaceHolder surfaceHolder = getHolder();

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    public CameraSurface(Context context) {
        super(context);
        this.surfaceHolder.addCallback(this);
        this.surfaceHolder.setType(3);
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public void surfaceCreated(android.view.SurfaceHolder r2) {
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
	at jadx.api.JadxDecompiler$$Lambda$8/1630521067.run(Unknown Source)
*/
        /*
        r1 = this;
        r0 = r1.camera;	 Catch:{ IOException -> 0x000a }
        if (r0 == 0) goto L_0x000d;	 Catch:{ IOException -> 0x000a }
    L_0x0004:
        r0 = r1.camera;	 Catch:{ IOException -> 0x000a }
        r0.setPreviewDisplay(r2);	 Catch:{ IOException -> 0x000a }
        goto L_0x000d;
    L_0x000a:
        r2 = 0;
        r1.camera = r2;
    L_0x000d:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.CameraSurface.surfaceCreated(android.view.SurfaceHolder):void");
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.camera = null;
    }
}
