package com.vuforia;

import android.app.Activity;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Vuforia {
    public static final int GL_20 = 1;
    public static final int GL_30 = 8;
    public static final int INIT_DEVICE_NOT_SUPPORTED = -2;
    public static final int INIT_ERROR = -1;
    public static final int INIT_EXTERNAL_DEVICE_NOT_DETECTED = -10;
    public static final int INIT_LICENSE_ERROR_CANCELED_KEY = -8;
    public static final int INIT_LICENSE_ERROR_INVALID_KEY = -5;
    public static final int INIT_LICENSE_ERROR_MISSING_KEY = -4;
    public static final int INIT_LICENSE_ERROR_NO_NETWORK_PERMANENT = -6;
    public static final int INIT_LICENSE_ERROR_NO_NETWORK_TRANSIENT = -7;
    public static final int INIT_LICENSE_ERROR_PRODUCT_TYPE_MISMATCH = -9;
    public static final int INIT_NO_CAMERA_ACCESS = -3;
    private static boolean initializedJava = false;
    private static UpdateCallback sUpdateCallback = null;
    private static UpdateCallbackInterface sUpdateCallbackInterface = null;
    protected static Map<Integer, Object> sUserDataMap = new ConcurrentHashMap(16, 0.75f, 4);

    public interface UpdateCallbackInterface {
        void Vuforia_onUpdate(State state);
    }

    /* renamed from: com.vuforia.Vuforia$1 */
    static class C00531 extends UpdateCallback {
        C00531() {
        }

        public void Vuforia_onUpdate(State state) {
            Vuforia.sUpdateCallbackInterface.Vuforia_onUpdate(state);
            state.delete();
        }
    }

    private static native void privateSetInitParameters(Activity activity, int i, String str);

    protected static short[] convertStringToShortArray(String str) {
        if (str == null) {
            return null;
        }
        short[] sArr = new short[(str.codePointCount(0, str.length()) + 1)];
        int length = str.length();
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3;
            int codePointAt = str.codePointAt(i);
            if (codePointAt > 65535) {
                i3 = i2 + 1;
                sArr[i2] = (short) (codePointAt >> 16);
                i2 = i3;
            }
            i3 = i2 + 1;
            sArr[i2] = (short) codePointAt;
            i += Character.charCount(codePointAt);
            i2 = i3;
        }
        sArr[sArr.length - 1] = (short) 0;
        return sArr;
    }

    protected static boolean wasInitializedJava() {
        return initializedJava;
    }

    protected static void setHint() {
        setHint(-858996736, 2796202);
    }

    public static void setInitParameters(Activity activity, int i, String str) {
        if (!initializedJava) {
            setHint();
            initializedJava = true;
        }
        privateSetInitParameters(activity, i, str);
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1630521067.run(Unknown Source)
*/
        /*
        java.lang.System.loadLibrary(r3);	 Catch:{ UnsatisfiedLinkError -> 0x003c, SecurityException -> 0x0020 }
        r0 = java.lang.System.out;	 Catch:{ UnsatisfiedLinkError -> 0x003c, SecurityException -> 0x0020 }
        r1 = new java.lang.StringBuilder;	 Catch:{ UnsatisfiedLinkError -> 0x003c, SecurityException -> 0x0020 }
        r1.<init>();	 Catch:{ UnsatisfiedLinkError -> 0x003c, SecurityException -> 0x0020 }
        r2 = "Native library lib";	 Catch:{ UnsatisfiedLinkError -> 0x003c, SecurityException -> 0x0020 }
        r1.append(r2);	 Catch:{ UnsatisfiedLinkError -> 0x003c, SecurityException -> 0x0020 }
        r1.append(r3);	 Catch:{ UnsatisfiedLinkError -> 0x003c, SecurityException -> 0x0020 }
        r2 = ".so loaded";	 Catch:{ UnsatisfiedLinkError -> 0x003c, SecurityException -> 0x0020 }
        r1.append(r2);	 Catch:{ UnsatisfiedLinkError -> 0x003c, SecurityException -> 0x0020 }
        r1 = r1.toString();	 Catch:{ UnsatisfiedLinkError -> 0x003c, SecurityException -> 0x0020 }
        r0.println(r1);	 Catch:{ UnsatisfiedLinkError -> 0x003c, SecurityException -> 0x0020 }
        r3 = 1;
        return r3;
    L_0x0020:
        r0 = java.lang.System.err;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "The library lib";
        r1.append(r2);
        r1.append(r3);
        r3 = ".so was not allowed to be loaded";
        r1.append(r3);
        r3 = r1.toString();
        r0.println(r3);
        goto L_0x0057;
    L_0x003c:
        r0 = java.lang.System.err;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "The library lib";
        r1.append(r2);
        r1.append(r3);
        r3 = ".so could not be loaded";
        r1.append(r3);
        r3 = r1.toString();
        r0.println(r3);
    L_0x0057:
        r3 = 0;
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.Vuforia.loadLibrary(java.lang.String):boolean");
    }

    static {
        if (!loadLibrary("Vuforia")) {
            System.exit(1);
        }
    }

    protected static UpdateCallback registerLocalReference(UpdateCallbackInterface updateCallbackInterface) {
        if (updateCallbackInterface == null) {
            sUpdateCallback = null;
            sUpdateCallbackInterface = null;
            return null;
        }
        sUpdateCallbackInterface = updateCallbackInterface;
        sUpdateCallback = new C00531();
        return sUpdateCallback;
    }

    protected static boolean updateUserDataMap(Integer num, Object obj) {
        if (num == null) {
            return null;
        }
        if (obj == null) {
            sUserDataMap.remove(num);
        } else {
            sUserDataMap.put(num, obj);
        }
        return true;
    }

    protected static Object retrieveFromUserDataMap(Integer num) {
        if (sUserDataMap.containsKey(num)) {
            return sUserDataMap.get(num);
        }
        return null;
    }

    public static boolean setDriverLibrary(String str) {
        return VuforiaJNI.setDriverLibrary(str);
    }

    public static int init() {
        return VuforiaJNI.init();
    }

    public static boolean isInitialized() {
        return VuforiaJNI.isInitialized();
    }

    public static void deinit() {
        VuforiaJNI.deinit();
    }

    public static int setAllowedFusionProviders(int i) {
        return VuforiaJNI.setAllowedFusionProviders(i);
    }

    public static int getActiveFusionProvider() {
        return VuforiaJNI.getActiveFusionProvider();
    }

    public static boolean setHint(long j, int i) {
        return VuforiaJNI.setHint(j, i);
    }

    public static void registerCallback(UpdateCallbackInterface updateCallbackInterface) {
        VuforiaJNI.registerCallback(UpdateCallback.getCPtr(registerLocalReference(updateCallbackInterface)), sUpdateCallback);
    }

    public static boolean setFrameFormat(int i, boolean z) {
        return VuforiaJNI.setFrameFormat(i, z);
    }

    public static int getBitsPerPixel(int i) {
        return VuforiaJNI.getBitsPerPixel(i);
    }

    public static boolean requiresAlpha() {
        return VuforiaJNI.requiresAlpha();
    }

    public static int getBufferSize(int i, int i2, int i3) {
        return VuforiaJNI.getBufferSize(i, i2, i3);
    }

    public static void onResume() {
        VuforiaJNI.onResume();
    }

    public static void onPause() {
        VuforiaJNI.onPause();
    }

    public static void onSurfaceCreated() {
        VuforiaJNI.onSurfaceCreated();
    }

    public static void onSurfaceChanged(int i, int i2) {
        VuforiaJNI.onSurfaceChanged(i, i2);
    }

    public static String getLibraryVersion() {
        return VuforiaJNI.getLibraryVersion();
    }
}
