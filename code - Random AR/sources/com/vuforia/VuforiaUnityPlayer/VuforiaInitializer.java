package com.vuforia.VuforiaUnityPlayer;

import android.app.Activity;
import com.vuforia.Vuforia;

public class VuforiaInitializer {
    private static final String NATIVE_LIB_UNITYPLAYER = "VuforiaUnityPlayer";
    private static final String NATIVE_LIB_VUFORIA = "Vuforia";
    private static final String NATIVE_LIB_VUFORIAWRAPPER = "VuforiaWrapper";

    private static native void initPlatformNative();

    public static void loadNativeLibraries() {
        loadLibrary(NATIVE_LIB_VUFORIA);
        loadLibrary(NATIVE_LIB_VUFORIAWRAPPER);
        loadLibrary(NATIVE_LIB_UNITYPLAYER);
    }

    public static void initPlatform() {
        initPlatformNative();
    }

    public static int initVuforia(Activity activity, int i, String str) {
        DebugLog.LOGD("Initializing Vuforia...");
        Vuforia.setInitParameters(activity, i, str);
        Vuforia.setHint(-858996736, 1747626);
        do {
            activity = Vuforia.init();
            if (activity < null) {
                break;
            }
        } while (activity < 100);
        if (activity >= null) {
            return null;
        }
        DebugLog.LOGE("Vuforia initialization failed");
        return activity;
    }

    private static boolean loadLibrary(java.lang.String r3) {
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
        java.lang.System.loadLibrary(r3);	 Catch:{ UnsatisfiedLinkError -> 0x001f, SecurityException -> 0x0005 }
        r3 = 1;
        return r3;
    L_0x0005:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "The library lib";
        r0.append(r1);
        r0.append(r3);
        r3 = ".so was not allowed to be loaded";
        r0.append(r3);
        r3 = r0.toString();
        com.vuforia.VuforiaUnityPlayer.DebugLog.LOGE(r3);
        goto L_0x0040;
    L_0x001f:
        r0 = move-exception;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "The library lib";
        r1.append(r2);
        r1.append(r3);
        r3 = ".so could not be loaded: ";
        r1.append(r3);
        r3 = r0.toString();
        r1.append(r3);
        r3 = r1.toString();
        com.vuforia.VuforiaUnityPlayer.DebugLog.LOGE(r3);
    L_0x0040:
        r3 = 0;
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.VuforiaUnityPlayer.VuforiaInitializer.loadLibrary(java.lang.String):boolean");
    }
}
