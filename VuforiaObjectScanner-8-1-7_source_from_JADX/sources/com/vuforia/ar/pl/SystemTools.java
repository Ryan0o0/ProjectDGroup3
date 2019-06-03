package com.vuforia.ar.pl;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class SystemTools {
    public static final int AR_DEVICE_ORIENTATION_0 = 268455954;
    public static final int AR_DEVICE_ORIENTATION_180 = 268455955;
    public static final int AR_DEVICE_ORIENTATION_270 = 268455957;
    public static final int AR_DEVICE_ORIENTATION_90 = 268455956;
    public static final int AR_DEVICE_ORIENTATION_UNKNOWN = 268455952;
    public static final int AR_ERROR_INVALID_ENUM = 3;
    public static final int AR_ERROR_INVALID_HANDLE = 4;
    public static final int AR_ERROR_INVALID_OPERATION = 5;
    public static final int AR_ERROR_INVALID_VALUE = 2;
    public static final int AR_ERROR_NONE = 0;
    public static final int AR_ERROR_OPERATION_CANCELED = 7;
    public static final int AR_ERROR_OPERATION_FAILED = 6;
    public static final int AR_ERROR_OPERATION_TIMEOUT = 8;
    public static final int AR_ERROR_UNKNOWN = 1;
    public static final int AR_RENDERING_TEXTURE_ROTATION_AUTO = 268455953;
    public static final int AR_RENDERING_TEXTURE_ROTATION_LANDSCAPE_LEFT = 268455956;
    public static final int AR_RENDERING_TEXTURE_ROTATION_LANDSCAPE_RIGHT = 268455957;
    public static final int AR_RENDERING_TEXTURE_ROTATION_PORTRAIT = 268455954;
    public static final int AR_RENDERING_TEXTURE_ROTATION_PORTRAIT_UPSIDEDOWN = 268455955;
    public static final int AR_VIDEOTEXTURE_ROTATION_UNKNOWN = 268455952;
    private static final String MODULENAME = "SystemTools";

    public static native Activity getActivityFromNative();

    public static native void logSystemError(String str);

    public static native void setSystemErrorCode(int i);

    public static boolean checkMinimumApiLevel(int i) {
        return VERSION.SDK_INT >= i;
    }

    public static void sendKillSignal(final int i) {
        final Activity activityFromNative = getActivityFromNative();
        if (activityFromNative != null) {
            activityFromNative.runOnUiThread(new Runnable() {
                public void run() {
                    activityFromNative.setResult(i);
                    activityFromNative.finish();
                }
            });
        }
    }

    public static java.lang.reflect.Method retrieveClassMethod(java.lang.Class<?> r0, java.lang.String r1, java.lang.Class<?>... r2) {
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
        r0 = r0.getMethod(r1, r2);	 Catch:{ Exception -> 0x0005 }
        goto L_0x0006;
    L_0x0005:
        r0 = 0;
    L_0x0006:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.SystemTools.retrieveClassMethod(java.lang.Class, java.lang.String, java.lang.Class[]):java.lang.reflect.Method");
    }

    public static int getDeviceOrientation(Activity activity) {
        int i = 268455952;
        if (activity == null) {
            return 268455952;
        }
        activity.getResources().getConfiguration();
        activity = ((WindowManager) activity.getSystemService("window")).getDefaultDisplay();
        if (checkMinimumApiLevel(8)) {
            activity = activity.getRotation();
        } else {
            activity = activity.getOrientation();
        }
        if (activity == null) {
            i = 268455954;
        } else if (activity == 1) {
            i = 268455956;
        } else if (activity == 2) {
            i = 268455955;
        } else if (activity == 3) {
            i = 268455957;
        }
        return i;
    }

    public static int getActivityOrientation(Activity activity) {
        int i = 268455952;
        if (activity == null) {
            return 268455952;
        }
        Configuration configuration = activity.getResources().getConfiguration();
        activity = ((WindowManager) activity.getSystemService("window")).getDefaultDisplay();
        if (checkMinimumApiLevel(8)) {
            activity = activity.getRotation();
        } else {
            activity = activity.getOrientation();
        }
        switch (configuration.orientation) {
            case 1:
            case 3:
                if (activity != null) {
                    if (activity != 3) {
                        i = 268455955;
                        break;
                    }
                }
                i = 268455954;
                break;
            case 2:
                if (activity != null) {
                    if (activity != 1) {
                        i = 268455957;
                        break;
                    }
                }
                i = 268455956;
                break;
            default:
                break;
        }
        return i;
    }

    public static java.lang.String getNativeLibraryPath(android.app.Activity r3) {
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
        r0 = 0;
        if (r3 != 0) goto L_0x0004;
    L_0x0003:
        return r0;
    L_0x0004:
        r1 = r3.getApplicationInfo();	 Catch:{ Exception -> 0x0057 }
        if (r1 == 0) goto L_0x0055;	 Catch:{ Exception -> 0x0057 }
    L_0x000a:
        r2 = 9;	 Catch:{ Exception -> 0x0057 }
        r2 = checkMinimumApiLevel(r2);	 Catch:{ Exception -> 0x0057 }
        if (r2 == 0) goto L_0x003a;	 Catch:{ Exception -> 0x0057 }
    L_0x0012:
        r3 = r1.nativeLibraryDir;	 Catch:{ Exception -> 0x0057 }
        if (r3 == 0) goto L_0x0056;	 Catch:{ Exception -> 0x0057 }
    L_0x0016:
        r1 = r3.length();	 Catch:{ Exception -> 0x0057 }
        if (r1 <= 0) goto L_0x0056;	 Catch:{ Exception -> 0x0057 }
    L_0x001c:
        r1 = r3.length();	 Catch:{ Exception -> 0x0057 }
        r1 = r1 + -1;	 Catch:{ Exception -> 0x0057 }
        r1 = r3.charAt(r1);	 Catch:{ Exception -> 0x0057 }
        r2 = 47;	 Catch:{ Exception -> 0x0057 }
        if (r1 == r2) goto L_0x0056;	 Catch:{ Exception -> 0x0057 }
    L_0x002a:
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0057 }
        r1.<init>();	 Catch:{ Exception -> 0x0057 }
        r1.append(r3);	 Catch:{ Exception -> 0x0057 }
        r1.append(r2);	 Catch:{ Exception -> 0x0057 }
        r3 = r1.toString();	 Catch:{ Exception -> 0x0057 }
        goto L_0x0056;	 Catch:{ Exception -> 0x0057 }
    L_0x003a:
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0057 }
        r1.<init>();	 Catch:{ Exception -> 0x0057 }
        r2 = "/data/data/";	 Catch:{ Exception -> 0x0057 }
        r1.append(r2);	 Catch:{ Exception -> 0x0057 }
        r3 = r3.getPackageName();	 Catch:{ Exception -> 0x0057 }
        r1.append(r3);	 Catch:{ Exception -> 0x0057 }
        r3 = "/lib/";	 Catch:{ Exception -> 0x0057 }
        r1.append(r3);	 Catch:{ Exception -> 0x0057 }
        r3 = r1.toString();	 Catch:{ Exception -> 0x0057 }
        goto L_0x0056;
    L_0x0055:
        r3 = r0;
    L_0x0056:
        return r3;
    L_0x0057:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.SystemTools.getNativeLibraryPath(android.app.Activity):java.lang.String");
    }

    public static int[] getActivitySize(Activity activity) {
        if (activity == null) {
            return null;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        activity = displayMetrics.widthPixels;
        int i = displayMetrics.heightPixels;
        if (activity <= null || i <= 0) {
            return null;
        }
        return new int[]{activity, i};
    }

    public static float[] getDisplayDpi(Activity activity) {
        if (activity == null) {
            return null;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (checkMinimumApiLevel(17)) {
            activity.getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        } else {
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        }
        activity = displayMetrics.xdpi;
        float f = displayMetrics.ydpi;
        if (activity <= null || f <= 0.0f) {
            return null;
        }
        return new float[]{activity, f};
    }

    public static int[] getDisplaySize(android.app.Activity r6) {
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
        r0 = 0;
        if (r6 != 0) goto L_0x0004;
    L_0x0003:
        return r0;
    L_0x0004:
        r1 = new android.graphics.Point;
        r1.<init>();
        r6 = r6.getWindowManager();	 Catch:{ NoSuchMethodError -> 0x0039 }
        r6 = r6.getDefaultDisplay();	 Catch:{ NoSuchMethodError -> 0x0039 }
        r6.getRealSize(r1);	 Catch:{ NoSuchMethodError -> 0x0039 }
        r6 = r1.x;	 Catch:{ NoSuchMethodError -> 0x0039 }
        if (r6 <= 0) goto L_0x0040;	 Catch:{ NoSuchMethodError -> 0x0039 }
    L_0x0018:
        r6 = r1.y;	 Catch:{ NoSuchMethodError -> 0x0039 }
        if (r6 <= 0) goto L_0x0040;	 Catch:{ NoSuchMethodError -> 0x0039 }
    L_0x001c:
        r6 = 2;	 Catch:{ NoSuchMethodError -> 0x0039 }
        r6 = new int[r6];	 Catch:{ NoSuchMethodError -> 0x0039 }
        r2 = r1.y;	 Catch:{ NoSuchMethodError -> 0x0039 }
        r3 = r1.x;	 Catch:{ NoSuchMethodError -> 0x0039 }
        r4 = 1;	 Catch:{ NoSuchMethodError -> 0x0039 }
        r5 = 0;	 Catch:{ NoSuchMethodError -> 0x0039 }
        if (r2 <= r3) goto L_0x0030;	 Catch:{ NoSuchMethodError -> 0x0039 }
    L_0x0027:
        r2 = r1.y;	 Catch:{ NoSuchMethodError -> 0x0039 }
        r6[r5] = r2;	 Catch:{ NoSuchMethodError -> 0x0039 }
        r1 = r1.x;	 Catch:{ NoSuchMethodError -> 0x0039 }
        r6[r4] = r1;	 Catch:{ NoSuchMethodError -> 0x0039 }
        goto L_0x0038;	 Catch:{ NoSuchMethodError -> 0x0039 }
    L_0x0030:
        r2 = r1.x;	 Catch:{ NoSuchMethodError -> 0x0039 }
        r6[r5] = r2;	 Catch:{ NoSuchMethodError -> 0x0039 }
        r1 = r1.y;	 Catch:{ NoSuchMethodError -> 0x0039 }
        r6[r4] = r1;	 Catch:{ NoSuchMethodError -> 0x0039 }
    L_0x0038:
        return r6;
    L_0x0039:
        r6 = "SystemTools";
        r1 = "Display.getRealSize is not supported on this platform";
        com.vuforia.ar.pl.DebugLog.LOGE(r6, r1);
    L_0x0040:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.SystemTools.getDisplaySize(android.app.Activity):int[]");
    }
}
