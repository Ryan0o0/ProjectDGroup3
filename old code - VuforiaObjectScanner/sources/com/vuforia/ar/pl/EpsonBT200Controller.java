package com.vuforia.ar.pl;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager.LayoutParams;

public class EpsonBT200Controller {
    private static final String MODULENAME = "EpsonBT200Controller";
    private boolean stereoEnabled = false;

    public EpsonBT200Controller() {
        final Activity activityFromNative = SystemTools.getActivityFromNative();
        if (activityFromNative != null) {
            activityFromNative.runOnUiThread(new Runnable() {
                public void run() {
                    Window window = activityFromNative.getWindow();
                    LayoutParams attributes = window.getAttributes();
                    attributes.flags |= Integer.MIN_VALUE;
                    window.setAttributes(attributes);
                }
            });
        }
    }

    public boolean setStereo(boolean r8) {
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
        r7 = this;
        r0 = 0;
        r1 = com.vuforia.ar.pl.SystemTools.getActivityFromNative();	 Catch:{ NoSuchMethodException -> 0x003c, IllegalAccessException -> 0x003b, InvocationTargetException -> 0x003a }
        if (r1 != 0) goto L_0x0008;	 Catch:{ NoSuchMethodException -> 0x003c, IllegalAccessException -> 0x003b, InvocationTargetException -> 0x003a }
    L_0x0007:
        return r0;	 Catch:{ NoSuchMethodException -> 0x003c, IllegalAccessException -> 0x003b, InvocationTargetException -> 0x003a }
    L_0x0008:
        r2 = "window";	 Catch:{ NoSuchMethodException -> 0x003c, IllegalAccessException -> 0x003b, InvocationTargetException -> 0x003a }
        r1 = r1.getSystemService(r2);	 Catch:{ NoSuchMethodException -> 0x003c, IllegalAccessException -> 0x003b, InvocationTargetException -> 0x003a }
        r1 = (android.view.WindowManager) r1;	 Catch:{ NoSuchMethodException -> 0x003c, IllegalAccessException -> 0x003b, InvocationTargetException -> 0x003a }
        r1 = r1.getDefaultDisplay();	 Catch:{ NoSuchMethodException -> 0x003c, IllegalAccessException -> 0x003b, InvocationTargetException -> 0x003a }
        r2 = android.view.Display.class;	 Catch:{ NoSuchMethodException -> 0x003c, IllegalAccessException -> 0x003b, InvocationTargetException -> 0x003a }
        r3 = "setDisplayMode";	 Catch:{ NoSuchMethodException -> 0x003c, IllegalAccessException -> 0x003b, InvocationTargetException -> 0x003a }
        r4 = 1;	 Catch:{ NoSuchMethodException -> 0x003c, IllegalAccessException -> 0x003b, InvocationTargetException -> 0x003a }
        r5 = new java.lang.Class[r4];	 Catch:{ NoSuchMethodException -> 0x003c, IllegalAccessException -> 0x003b, InvocationTargetException -> 0x003a }
        r6 = java.lang.Integer.TYPE;	 Catch:{ NoSuchMethodException -> 0x003c, IllegalAccessException -> 0x003b, InvocationTargetException -> 0x003a }
        r5[r0] = r6;	 Catch:{ NoSuchMethodException -> 0x003c, IllegalAccessException -> 0x003b, InvocationTargetException -> 0x003a }
        r2 = r2.getDeclaredMethod(r3, r5);	 Catch:{ NoSuchMethodException -> 0x003c, IllegalAccessException -> 0x003b, InvocationTargetException -> 0x003a }
        r3 = new java.lang.Object[r4];	 Catch:{ NoSuchMethodException -> 0x003c, IllegalAccessException -> 0x003b, InvocationTargetException -> 0x003a }
        r4 = java.lang.Integer.valueOf(r8);	 Catch:{ NoSuchMethodException -> 0x003c, IllegalAccessException -> 0x003b, InvocationTargetException -> 0x003a }
        r3[r0] = r4;	 Catch:{ NoSuchMethodException -> 0x003c, IllegalAccessException -> 0x003b, InvocationTargetException -> 0x003a }
        r1 = r2.invoke(r1, r3);	 Catch:{ NoSuchMethodException -> 0x003c, IllegalAccessException -> 0x003b, InvocationTargetException -> 0x003a }
        r1 = (java.lang.Boolean) r1;	 Catch:{ NoSuchMethodException -> 0x003c, IllegalAccessException -> 0x003b, InvocationTargetException -> 0x003a }
        r1 = r1.booleanValue();	 Catch:{ NoSuchMethodException -> 0x003c, IllegalAccessException -> 0x003b, InvocationTargetException -> 0x003a }
        if (r1 == 0) goto L_0x0039;	 Catch:{ NoSuchMethodException -> 0x003c, IllegalAccessException -> 0x003b, InvocationTargetException -> 0x003a }
    L_0x0037:
        r7.stereoEnabled = r8;	 Catch:{ NoSuchMethodException -> 0x003c, IllegalAccessException -> 0x003b, InvocationTargetException -> 0x003a }
    L_0x0039:
        return r1;
    L_0x003a:
        return r0;
    L_0x003b:
        return r0;
    L_0x003c:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.EpsonBT200Controller.setStereo(boolean):boolean");
    }

    public boolean getStereo() {
        return this.stereoEnabled;
    }
}
