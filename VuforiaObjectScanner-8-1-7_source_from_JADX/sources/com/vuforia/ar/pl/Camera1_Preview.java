package com.vuforia.ar.pl;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import com.vuforia.PIXEL_FORMAT;
import com.vuforia.captureapp.BuildConfig;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

public class Camera1_Preview implements PreviewCallback {
    private static final int AR_CAMERA_DIRECTION_BACK = 268443665;
    private static final int AR_CAMERA_DIRECTION_FRONT = 268443666;
    private static final int AR_CAMERA_DIRECTION_UNKNOWN = 268443664;
    private static final int AR_CAMERA_EXPOSUREMODE_AUTO = 805314560;
    private static final int AR_CAMERA_EXPOSUREMODE_CONTINUOUSAUTO = 805322752;
    private static final int AR_CAMERA_EXPOSUREMODE_LOCKED = 805310464;
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
    private static final int[] CAMERA_IMAGE_FORMAT_CONVERSIONTABLE = new int[]{16, AR_CAMERA_IMAGE_FORMAT_NV16, 17, AR_CAMERA_IMAGE_FORMAT_NV21, 4, AR_CAMERA_IMAGE_FORMAT_RGB565, 842094169, AR_CAMERA_IMAGE_FORMAT_YV12};
    private static boolean CONVERT_FORMAT_TO_ANDROID = false;
    private static boolean CONVERT_FORMAT_TO_PL = true;
    private static final String FOCUS_MODE_NORMAL = "normal";
    private static final String MODULENAME = "Camera1_Preview";
    private static final int NUM_CAPTURE_BUFFERS = 2;
    private static final int NUM_CAPTURE_BUFFERS_TO_ADD = 2;
    private static final int NUM_MAX_CAMERAOPEN_RETRY = 10;
    private static final String SAMSUNG_PARAM_FAST_FPS_MODE = "fast-fps-mode";
    private static final String SAMSUNG_PARAM_VRMODE = "vrmode";
    private static final String SAMSUNG_PARAM_VRMODE_SUPPORTED = "vrmode-supported";
    private static final int TIME_CAMERAOPEN_RETRY_DELAY_MS = 250;
    private static final int _NUM_CAMERA_CAPSINFO_VALUE_ = 6;
    private static final int _NUM_CAMERA_CAPTUREINFO_VALUE_ = 5;
    private Vector<CameraCacheInfo> cameraCacheInfo = null;
    private HashMap<Camera, Integer> cameraCacheInfoIndexCache = null;
    private SurfaceManager surfaceManager = null;

    /* renamed from: com.vuforia.ar.pl.Camera1_Preview$1 */
    class C00001 implements AutoFocusCallback {
        C00001() {
        }

        public void onAutoFocus(boolean z, Camera camera) {
            z = Camera1_Preview.this.cameraCacheInfoIndexCache.get(camera);
            if (z) {
                z = Camera1_Preview.this.getCameraCacheInfo(((Integer) z).intValue());
                if (z) {
                    z.isAutoFocusing = null;
                }
            }
        }
    }

    public class CameraCacheInfo {
        byte[][] buffer;
        int bufferFormatPL;
        int bufferHeight;
        int bufferSize;
        int bufferWidth;
        Camera camera;
        int[] caps;
        long deviceHandle;
        int deviceID;
        boolean isAutoFocusing;
        int overrideFormatAndroid;
        int overrideHeight;
        int overrideWidth;
        int requestFormatAndroid;
        int requestHeight;
        int requestWidth;
        int status;
        CameraSurface surface;
        SurfaceTexture surfaceTexture;
    }

    private native void newFrameAvailable(long j, int i, int i2, int i3, int i4, byte[] bArr, long j2);

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
	at jadx.api.JadxDecompiler$$Lambda$8/1630521067.run(Unknown Source)
*/
        /*
        r4 = this;
        r0 = 0;
        r1 = com.vuforia.ar.pl.SystemTools.getActivityFromNative();	 Catch:{ Exception -> 0x001a }
        if (r1 != 0) goto L_0x0008;	 Catch:{ Exception -> 0x001a }
    L_0x0007:
        return r0;	 Catch:{ Exception -> 0x001a }
    L_0x0008:
        r2 = r1.getPackageManager();	 Catch:{ Exception -> 0x001a }
        r3 = "android.permission.CAMERA";	 Catch:{ Exception -> 0x001a }
        r1 = r1.getPackageName();	 Catch:{ Exception -> 0x001a }
        r1 = r2.checkPermission(r3, r1);	 Catch:{ Exception -> 0x001a }
        if (r1 != 0) goto L_0x001a;
    L_0x0018:
        r0 = 1;
        return r0;
    L_0x001a:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.Camera1_Preview.checkPermission():boolean");
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
	at jadx.api.JadxDecompiler$$Lambda$8/1630521067.run(Unknown Source)
*/
        /*
        r4 = this;
        r6 = 9;
        r6 = com.vuforia.ar.pl.SystemTools.checkMinimumApiLevel(r6);
        r0 = 1;
        r1 = 0;
        r2 = 2;
        r3 = -1;
        if (r6 == 0) goto L_0x0037;
    L_0x000c:
        switch(r7) {
            case 268443664: goto L_0x0015;
            case 268443665: goto L_0x0013;
            case 268443666: goto L_0x0016;
            default: goto L_0x000f;
        };
    L_0x000f:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r2);
        return r3;
    L_0x0013:
        r0 = 0;
        goto L_0x0016;
    L_0x0015:
        r0 = -1;
    L_0x0016:
        r6 = android.hardware.Camera.getNumberOfCameras();
    L_0x001a:
        if (r1 >= r6) goto L_0x0032;
    L_0x001c:
        r7 = new android.hardware.Camera$CameraInfo;
        r7.<init>();
        android.hardware.Camera.getCameraInfo(r1, r7);	 Catch:{ Exception -> 0x002f }
        if (r0 < 0) goto L_0x002a;
    L_0x0026:
        r7 = r7.facing;
        if (r0 != r7) goto L_0x002f;
    L_0x002a:
        if (r5 < 0) goto L_0x002e;
    L_0x002c:
        if (r5 != r1) goto L_0x002f;
    L_0x002e:
        return r1;
    L_0x002f:
        r1 = r1 + 1;
        goto L_0x001a;
    L_0x0032:
        r5 = 6;
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r5);
        return r3;
    L_0x0037:
        r6 = 268443666; // 0x10002012 float:2.5268255E-29 double:1.32628793E-315;
        if (r7 != r6) goto L_0x0040;
    L_0x003c:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r2);
        return r3;
    L_0x0040:
        if (r5 < r0) goto L_0x0046;
    L_0x0042:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r2);
        return r3;
    L_0x0046:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.Camera1_Preview.getCameraDeviceIndex(int, int, int):int");
    }

    private android.hardware.Camera.Parameters getCameraParameters(android.hardware.Camera r1) {
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
        r0 = this;
        r1 = r1.getParameters();	 Catch:{ Exception -> 0x0005 }
        goto L_0x0006;
    L_0x0005:
        r1 = 0;
    L_0x0006:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.Camera1_Preview.getCameraParameters(android.hardware.Camera):android.hardware.Camera$Parameters");
    }

    private CameraCacheInfo getCameraCacheInfo(int i) {
        if (i >= 0) {
            if (i < this.cameraCacheInfo.size()) {
                return (CameraCacheInfo) this.cameraCacheInfo.get(i);
            }
        }
        return 0;
    }

    private boolean setCustomCameraParams(android.hardware.Camera.Parameters r7, java.lang.String r8) {
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
        r6 = this;
        r0 = 0;
        r1 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x003e }
        r1.<init>(r8);	 Catch:{ JSONException -> 0x003e }
        r8 = r1.keys();
    L_0x000a:
        r2 = r8.hasNext();
        if (r2 == 0) goto L_0x003c;
    L_0x0010:
        r2 = r8.next();
        r2 = (java.lang.String) r2;
        r3 = r1.get(r2);	 Catch:{ JSONException -> 0x003b }
        r4 = r3.getClass();
        r5 = java.lang.String.class;
        if (r4 != r5) goto L_0x0028;
    L_0x0022:
        r3 = (java.lang.String) r3;
        r7.set(r2, r3);
        goto L_0x000a;
    L_0x0028:
        r4 = r3.getClass();
        r5 = java.lang.Integer.class;
        if (r4 != r5) goto L_0x003a;
    L_0x0030:
        r3 = (java.lang.Integer) r3;
        r3 = r3.intValue();
        r7.set(r2, r3);
        goto L_0x000a;
    L_0x003a:
        return r0;
    L_0x003b:
        return r0;
    L_0x003c:
        r7 = 1;
        return r7;
    L_0x003e:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.Camera1_Preview.setCustomCameraParams(android.hardware.Camera$Parameters, java.lang.String):boolean");
    }

    private boolean setCameraPreviewFps(int i, Parameters parameters) {
        int[] iArr;
        List<int[]> supportedPreviewFpsRange = parameters.getSupportedPreviewFpsRange();
        int i2 = i * 1000;
        if ((i == 60 || i == 120) && "true".equalsIgnoreCase(parameters.get(SAMSUNG_PARAM_VRMODE_SUPPORTED))) {
            int[] iArr2 = new int[2];
            parameters.set(SAMSUNG_PARAM_VRMODE, 1);
            parameters.setRecordingHint(true);
            parameters.set("focus-mode", "continuous-video");
            if (i == 60) {
                parameters.set(SAMSUNG_PARAM_FAST_FPS_MODE, 1);
                iArr2[0] = 60000;
                iArr2[1] = 60000;
            }
            if (i == 120) {
                parameters.set(SAMSUNG_PARAM_FAST_FPS_MODE, 2);
                iArr2[0] = 120000;
                iArr2[1] = 120000;
            }
            iArr = iArr2;
        } else {
            if (!("true".equalsIgnoreCase(parameters.get(SAMSUNG_PARAM_VRMODE_SUPPORTED)) == 0 || parameters.get(SAMSUNG_PARAM_FAST_FPS_MODE) == 0 || parameters.getInt(SAMSUNG_PARAM_FAST_FPS_MODE) == 0)) {
                parameters.set(SAMSUNG_PARAM_VRMODE, 0);
                parameters.set(SAMSUNG_PARAM_FAST_FPS_MODE, 0);
            }
            iArr = null;
            for (int[] iArr3 : supportedPreviewFpsRange) {
                if (iArr3[0] == i2 && iArr3[1] - iArr3[0] < Integer.MAX_VALUE) {
                    iArr = iArr3;
                }
            }
        }
        if (iArr == null) {
            return false;
        }
        parameters.setPreviewFpsRange(iArr[0], iArr[1]);
        return true;
    }

    private boolean setCameraCaptureParams(com.vuforia.ar.pl.Camera1_Preview.CameraCacheInfo r5, android.hardware.Camera.Parameters r6, int[] r7, int[] r8) {
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
        r4 = this;
        r0 = 2;
        r1 = 1;
        r2 = 0;
        if (r7 != 0) goto L_0x0007;
    L_0x0005:
        if (r8 == 0) goto L_0x0028;
    L_0x0007:
        if (r8 == 0) goto L_0x000c;
    L_0x0009:
        r3 = r8[r2];
        goto L_0x000e;
    L_0x000c:
        r3 = r7[r2];
    L_0x000e:
        r5.overrideWidth = r3;
        if (r8 == 0) goto L_0x0015;
    L_0x0012:
        r3 = r8[r1];
        goto L_0x0017;
    L_0x0015:
        r3 = r7[r1];
    L_0x0017:
        r5.overrideHeight = r3;
        if (r8 == 0) goto L_0x001e;
    L_0x001b:
        r8 = r8[r0];
        goto L_0x0020;
    L_0x001e:
        r8 = r7[r0];
    L_0x0020:
        r3 = CONVERT_FORMAT_TO_ANDROID;
        r8 = r4.translateImageFormat(r8, r3);
        r5.overrideFormatAndroid = r8;
    L_0x0028:
        if (r7 != 0) goto L_0x002b;
    L_0x002a:
        return r1;
    L_0x002b:
        r8 = r7[r2];
        r5.requestWidth = r8;
        r8 = r7[r1];
        r5.requestHeight = r8;
        r8 = r7[r0];
        r0 = CONVERT_FORMAT_TO_ANDROID;
        r8 = r4.translateImageFormat(r8, r0);
        r5.requestFormatAndroid = r8;
        r8 = 3;
        r8 = r7[r8];
        r0 = r5.requestWidth;	 Catch:{ Exception -> 0x00a1 }
        if (r0 <= 0) goto L_0x004f;	 Catch:{ Exception -> 0x00a1 }
    L_0x0044:
        r0 = r5.requestHeight;	 Catch:{ Exception -> 0x00a1 }
        if (r0 <= 0) goto L_0x004f;	 Catch:{ Exception -> 0x00a1 }
    L_0x0048:
        r0 = r5.requestWidth;	 Catch:{ Exception -> 0x00a1 }
        r3 = r5.requestHeight;	 Catch:{ Exception -> 0x00a1 }
        r6.setPreviewSize(r0, r3);	 Catch:{ Exception -> 0x00a1 }
    L_0x004f:
        if (r8 <= 0) goto L_0x0066;	 Catch:{ Exception -> 0x00a1 }
    L_0x0051:
        r0 = 8;	 Catch:{ Exception -> 0x00a1 }
        r0 = com.vuforia.ar.pl.SystemTools.checkMinimumApiLevel(r0);	 Catch:{ Exception -> 0x00a1 }
        if (r0 == 0) goto L_0x0063;	 Catch:{ Exception -> 0x00a1 }
    L_0x0059:
        r0 = r4.setCameraPreviewFps(r8, r6);	 Catch:{ Exception -> 0x00a1 }
        if (r0 != 0) goto L_0x0066;	 Catch:{ Exception -> 0x00a1 }
    L_0x005f:
        r6.setPreviewFrameRate(r8);	 Catch:{ Exception -> 0x00a1 }
        goto L_0x0066;	 Catch:{ Exception -> 0x00a1 }
    L_0x0063:
        r6.setPreviewFrameRate(r8);	 Catch:{ Exception -> 0x00a1 }
    L_0x0066:
        r8 = r5.requestFormatAndroid;	 Catch:{ Exception -> 0x00a1 }
        if (r8 == 0) goto L_0x006f;	 Catch:{ Exception -> 0x00a1 }
    L_0x006a:
        r8 = r5.requestFormatAndroid;	 Catch:{ Exception -> 0x00a1 }
        r6.setPreviewFormat(r8);	 Catch:{ Exception -> 0x00a1 }
    L_0x006f:
        r6 = 4;
        r6 = r7[r6];
        if (r6 <= 0) goto L_0x0076;
    L_0x0074:
        r6 = 1;
        goto L_0x0077;
    L_0x0076:
        r6 = 0;
    L_0x0077:
        if (r6 == 0) goto L_0x00a0;
    L_0x0079:
        r6 = 11;
        r6 = com.vuforia.ar.pl.SystemTools.checkMinimumApiLevel(r6);
        if (r6 == 0) goto L_0x0092;
    L_0x0081:
        r6 = -1;
        r7 = new android.graphics.SurfaceTexture;	 Catch:{ Exception -> 0x0091 }
        r7.<init>(r6);	 Catch:{ Exception -> 0x0091 }
        r5.surfaceTexture = r7;	 Catch:{ Exception -> 0x0091 }
        r6 = r5.camera;	 Catch:{ Exception -> 0x00a0 }
        r5 = r5.surfaceTexture;	 Catch:{ Exception -> 0x00a0 }
        r6.setPreviewTexture(r5);	 Catch:{ Exception -> 0x00a0 }
        goto L_0x00a0;
    L_0x0091:
        return r2;
    L_0x0092:
        r6 = r4.surfaceManager;
        if (r6 == 0) goto L_0x009f;
    L_0x0096:
        r6 = r4.surfaceManager;
        r5 = r6.addCameraSurface(r5);
        if (r5 != 0) goto L_0x00a0;
    L_0x009e:
        return r2;
    L_0x009f:
        return r2;
    L_0x00a0:
        return r1;
    L_0x00a1:
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.Camera1_Preview.setCameraCaptureParams(com.vuforia.ar.pl.Camera1_Preview$CameraCacheInfo, android.hardware.Camera$Parameters, int[], int[]):boolean");
    }

    private boolean checkSamsungHighFPS(com.vuforia.ar.pl.Camera1_Preview.CameraCacheInfo r7) {
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
        r6 = this;
        r0 = r7.camera;
        r0 = r6.getCameraParameters(r0);
        r1 = 6;
        r2 = 0;
        if (r0 != 0) goto L_0x000e;
    L_0x000a:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r1);
        return r2;
    L_0x000e:
        r3 = "true";
        r4 = "vrmode-supported";
        r4 = r0.get(r4);
        r3 = r3.equalsIgnoreCase(r4);
        if (r3 == 0) goto L_0x0102;
    L_0x001c:
        r3 = r7.requestWidth;
        if (r3 <= 0) goto L_0x0102;
    L_0x0020:
        r3 = r7.requestHeight;
        if (r3 <= 0) goto L_0x0102;
    L_0x0024:
        r3 = "fast-fps-mode";
        r3 = r0.get(r3);
        if (r3 == 0) goto L_0x0102;
    L_0x002c:
        r3 = "fast-fps-mode";
        r3 = r0.getInt(r3);
        if (r3 == 0) goto L_0x0102;
    L_0x0034:
        r3 = r7.requestWidth;
        r4 = r0.getPreviewSize();
        r4 = r4.width;
        if (r3 != r4) goto L_0x0048;
    L_0x003e:
        r3 = r7.requestHeight;
        r4 = r0.getPreviewSize();
        r4 = r4.height;
        if (r3 == r4) goto L_0x0102;
    L_0x0048:
        r3 = "Camera1_Preview";
        r4 = "Detected Samsung high fps camera driver bug.";
        com.vuforia.ar.pl.DebugLog.LOGW(r3, r4);
        r3 = "Camera1_Preview";
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "Preview size doesn't match request; width ";
        r4.append(r5);
        r5 = r7.requestWidth;
        r4.append(r5);
        r5 = "!=";
        r4.append(r5);
        r5 = r0.getPreviewSize();
        r5 = r5.width;
        r4.append(r5);
        r5 = " or height ";
        r4.append(r5);
        r5 = r7.requestHeight;
        r4.append(r5);
        r5 = "!=";
        r4.append(r5);
        r5 = r0.getPreviewSize();
        r5 = r5.height;
        r4.append(r5);
        r4 = r4.toString();
        com.vuforia.ar.pl.DebugLog.LOGW(r3, r4);
        r3 = 30;
        r6.setCameraPreviewFps(r3, r0);
        r3 = r7.requestWidth;
        r4 = r7.requestHeight;
        r0.setPreviewSize(r3, r4);
        r3 = r7.camera;	 Catch:{ Exception -> 0x00fe }
        r3.setParameters(r0);	 Catch:{ Exception -> 0x00fe }
        r0 = r7.camera;
        r0 = r6.getCameraParameters(r0);
        r1 = r7.requestWidth;
        r3 = r0.getPreviewSize();
        r3 = r3.width;
        if (r1 != r3) goto L_0x00b8;
    L_0x00ae:
        r1 = r7.requestHeight;
        r3 = r0.getPreviewSize();
        r3 = r3.height;
        if (r1 == r3) goto L_0x0102;
    L_0x00b8:
        r1 = "Camera1_Preview";
        r3 = "Unable to workaround Samsung high fps camera driver bug.";
        com.vuforia.ar.pl.DebugLog.LOGE(r1, r3);
        r1 = "Camera1_Preview";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "Preview size doesn't match request; width ";
        r3.append(r4);
        r4 = r7.requestWidth;
        r3.append(r4);
        r4 = "!=";
        r3.append(r4);
        r4 = r0.getPreviewSize();
        r4 = r4.width;
        r3.append(r4);
        r4 = " or height ";
        r3.append(r4);
        r7 = r7.requestHeight;
        r3.append(r7);
        r7 = "!=";
        r3.append(r7);
        r7 = r0.getPreviewSize();
        r7 = r7.height;
        r3.append(r7);
        r7 = r3.toString();
        com.vuforia.ar.pl.DebugLog.LOGE(r1, r7);
        return r2;
    L_0x00fe:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r1);
        return r2;
    L_0x0102:
        r7 = 1;
        return r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.Camera1_Preview.checkSamsungHighFPS(com.vuforia.ar.pl.Camera1_Preview$CameraCacheInfo):boolean");
    }

    private boolean setupPreviewBuffer(com.vuforia.ar.pl.Camera1_Preview.CameraCacheInfo r7) {
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
        r6 = this;
        r0 = r7.camera;
        r0 = r6.getCameraParameters(r0);
        r1 = 0;
        if (r0 != 0) goto L_0x000a;
    L_0x0009:
        return r1;
    L_0x000a:
        r2 = r7.requestWidth;	 Catch:{ Exception -> 0x0091 }
        r3 = r7.overrideWidth;	 Catch:{ Exception -> 0x0091 }
        if (r2 != r3) goto L_0x0017;	 Catch:{ Exception -> 0x0091 }
    L_0x0010:
        r2 = r0.getPreviewSize();	 Catch:{ Exception -> 0x0091 }
        r2 = r2.width;	 Catch:{ Exception -> 0x0091 }
        goto L_0x0019;	 Catch:{ Exception -> 0x0091 }
    L_0x0017:
        r2 = r7.overrideWidth;	 Catch:{ Exception -> 0x0091 }
    L_0x0019:
        r7.bufferWidth = r2;	 Catch:{ Exception -> 0x0091 }
        r2 = r7.requestHeight;	 Catch:{ Exception -> 0x0091 }
        r3 = r7.overrideHeight;	 Catch:{ Exception -> 0x0091 }
        if (r2 != r3) goto L_0x0028;	 Catch:{ Exception -> 0x0091 }
    L_0x0021:
        r2 = r0.getPreviewSize();	 Catch:{ Exception -> 0x0091 }
        r2 = r2.height;	 Catch:{ Exception -> 0x0091 }
        goto L_0x002a;	 Catch:{ Exception -> 0x0091 }
    L_0x0028:
        r2 = r7.overrideHeight;	 Catch:{ Exception -> 0x0091 }
    L_0x002a:
        r7.bufferHeight = r2;	 Catch:{ Exception -> 0x0091 }
        r2 = r7.requestFormatAndroid;	 Catch:{ Exception -> 0x0091 }
        r3 = r7.overrideFormatAndroid;	 Catch:{ Exception -> 0x0091 }
        if (r2 != r3) goto L_0x0037;	 Catch:{ Exception -> 0x0091 }
    L_0x0032:
        r0 = r0.getPreviewFormat();	 Catch:{ Exception -> 0x0091 }
        goto L_0x0039;	 Catch:{ Exception -> 0x0091 }
    L_0x0037:
        r0 = r7.overrideFormatAndroid;	 Catch:{ Exception -> 0x0091 }
    L_0x0039:
        r2 = CONVERT_FORMAT_TO_PL;	 Catch:{ Exception -> 0x0091 }
        r2 = r6.translateImageFormat(r0, r2);	 Catch:{ Exception -> 0x0091 }
        r7.bufferFormatPL = r2;	 Catch:{ Exception -> 0x0091 }
        r2 = new android.graphics.PixelFormat;	 Catch:{ Exception -> 0x004c }
        r2.<init>();	 Catch:{ Exception -> 0x004c }
        android.graphics.PixelFormat.getPixelFormatInfo(r0, r2);	 Catch:{ Exception -> 0x004c }
        r2 = r2.bitsPerPixel;	 Catch:{ Exception -> 0x004c }
        goto L_0x0054;
        r2 = r6.getBitsPerPixel(r0);
        if (r2 != 0) goto L_0x0054;
    L_0x0053:
        return r1;
    L_0x0054:
        r0 = r7.bufferWidth;
        r3 = r7.bufferHeight;
        r0 = r0 * r3;
        r0 = r0 * r2;
        r0 = r0 / 8;
        r0 = r0 + 4096;
        r2 = r7.bufferSize;
        r3 = 1;
        if (r0 > r2) goto L_0x006b;
    L_0x0065:
        r7 = r7.camera;
        r7.setPreviewCallbackWithBuffer(r6);
        return r3;
    L_0x006b:
        r2 = 2;
        r4 = new byte[r2][];
        r7.buffer = r4;
    L_0x0070:
        if (r1 >= r2) goto L_0x0086;
    L_0x0072:
        r4 = r7.buffer;
        r5 = new byte[r0];
        r4[r1] = r5;
        if (r1 >= r2) goto L_0x0083;
    L_0x007a:
        r4 = r7.camera;
        r5 = r7.buffer;
        r5 = r5[r1];
        r4.addCallbackBuffer(r5);
    L_0x0083:
        r1 = r1 + 1;
        goto L_0x0070;
    L_0x0086:
        r7.bufferSize = r0;
        r7 = r7.camera;
        r7.setPreviewCallbackWithBuffer(r6);
        java.lang.System.gc();
        return r3;
    L_0x0091:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.Camera1_Preview.setupPreviewBuffer(com.vuforia.ar.pl.Camera1_Preview$CameraCacheInfo):boolean");
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

    private int translateImageFormat(int i, boolean z) {
        int i2 = 0;
        for (int i3 = 0; i3 < CAMERA_IMAGE_FORMAT_CONVERSIONTABLE.length / 2; i3++) {
            if (i == (z == CONVERT_FORMAT_TO_PL ? CAMERA_IMAGE_FORMAT_CONVERSIONTABLE[i3 * 2] : CAMERA_IMAGE_FORMAT_CONVERSIONTABLE[(i3 * 2) + 1])) {
                return z == CONVERT_FORMAT_TO_PL ? CAMERA_IMAGE_FORMAT_CONVERSIONTABLE[(i3 * 2) + 1] : CAMERA_IMAGE_FORMAT_CONVERSIONTABLE[i3 * 2];
            }
        }
        if (z == CONVERT_FORMAT_TO_PL) {
            i2 = AR_CAMERA_IMAGE_FORMAT_UNKNOWN;
        }
        return i2;
    }

    public void onPreviewFrame(byte[] bArr, Camera camera) {
        long nanoTime = System.nanoTime();
        SystemTools.checkMinimumApiLevel(18);
        Object obj = this.cameraCacheInfoIndexCache.get(camera);
        if (obj == null) {
            SystemTools.checkMinimumApiLevel(18);
            return;
        }
        int intValue = ((Integer) obj).intValue();
        CameraCacheInfo cameraCacheInfo = getCameraCacheInfo(intValue);
        if (cameraCacheInfo == null) {
            SystemTools.checkMinimumApiLevel(18);
            return;
        }
        newFrameAvailable(cameraCacheInfo.deviceHandle, intValue, cameraCacheInfo.bufferWidth, cameraCacheInfo.bufferHeight, cameraCacheInfo.bufferFormatPL, bArr, nanoTime);
        camera.addCallbackBuffer(bArr);
        SystemTools.checkMinimumApiLevel(18);
    }

    public boolean init() {
        this.cameraCacheInfo = new Vector();
        this.cameraCacheInfoIndexCache = new HashMap();
        return true;
    }

    public void setSurfaceManager(SurfaceManager surfaceManager) {
        this.surfaceManager = surfaceManager;
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
	at jadx.api.JadxDecompiler$$Lambda$8/1630521067.run(Unknown Source)
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
        r0 = 9;
        r0 = com.vuforia.ar.pl.SystemTools.checkMinimumApiLevel(r0);
        if (r0 == 0) goto L_0x001d;
    L_0x0014:
        r0 = android.hardware.Camera.getNumberOfCameras();	 Catch:{ Exception -> 0x0019 }
        return r0;
    L_0x0019:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r2);
        return r1;
    L_0x001d:
        r0 = com.vuforia.ar.pl.SystemTools.getActivityFromNative();	 Catch:{ Exception -> 0x002c }
        r0 = r0.getPackageManager();	 Catch:{ Exception -> 0x002c }
        r3 = "android.hardware.camera";	 Catch:{ Exception -> 0x002c }
        r0 = r0.hasSystemFeature(r3);	 Catch:{ Exception -> 0x002c }
        return r0;
    L_0x002c:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r2);
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.Camera1_Preview.getNumberOfCameras():int");
    }

    public int getOrientation(int r4) {
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
        r3 = this;
        r0 = r3.checkPermission();
        r1 = -1;
        r2 = 6;
        if (r0 != 0) goto L_0x000c;
    L_0x0008:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r2);
        return r1;
    L_0x000c:
        r0 = 9;
        r0 = com.vuforia.ar.pl.SystemTools.checkMinimumApiLevel(r0);
        if (r0 == 0) goto L_0x0023;
    L_0x0014:
        r0 = new android.hardware.Camera$CameraInfo;
        r0.<init>();
        android.hardware.Camera.getCameraInfo(r4, r0);	 Catch:{ Exception -> 0x001f }
        r4 = r0.orientation;
        return r4;
    L_0x001f:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r2);
        return r1;
    L_0x0023:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r2);
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.Camera1_Preview.getOrientation(int):int");
    }

    public int getDirection(int r5) {
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
        r4 = this;
        r0 = r4.checkPermission();
        r1 = -1;
        r2 = 6;
        if (r0 != 0) goto L_0x000c;
    L_0x0008:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r2);
        return r1;
    L_0x000c:
        r0 = 9;
        r0 = com.vuforia.ar.pl.SystemTools.checkMinimumApiLevel(r0);
        r3 = 268443665; // 0x10002011 float:2.5268252E-29 double:1.326287927E-315;
        if (r0 == 0) goto L_0x0031;
    L_0x0017:
        r0 = new android.hardware.Camera$CameraInfo;
        r0.<init>();
        android.hardware.Camera.getCameraInfo(r5, r0);	 Catch:{ Exception -> 0x002d }
        r5 = r0.facing;
        switch(r5) {
            case 0: goto L_0x002c;
            case 1: goto L_0x0028;
            default: goto L_0x0024;
        };
    L_0x0024:
        r5 = 268443664; // 0x10002010 float:2.526825E-29 double:1.32628792E-315;
        return r5;
    L_0x0028:
        r5 = 268443666; // 0x10002012 float:2.5268255E-29 double:1.32628793E-315;
        return r5;
    L_0x002c:
        return r3;
    L_0x002d:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r2);
        return r1;
    L_0x0031:
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.Camera1_Preview.getDirection(int):int");
    }

    public int getDeviceID(int i) {
        i = getCameraCacheInfo(i);
        if (i != 0) {
            return i.deviceID;
        }
        SystemTools.setSystemErrorCode(4);
        return -1;
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
	at jadx.api.JadxDecompiler$$Lambda$8/1630521067.run(Unknown Source)
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
        r10 = r7.getCameraDeviceIndex(r10, r11, r12);
        if (r10 >= 0) goto L_0x0013;
    L_0x0012:
        return r2;
    L_0x0013:
        r11 = r7.cameraCacheInfo;
        r11 = r11.size();
        r12 = 0;
        r0 = 0;
        r4 = r12;
        r3 = 0;
    L_0x001d:
        if (r3 >= r11) goto L_0x002f;
    L_0x001f:
        r4 = r7.cameraCacheInfo;
        r4 = r4.get(r3);
        r4 = (com.vuforia.ar.pl.Camera1_Preview.CameraCacheInfo) r4;
        r5 = r4.deviceID;
        if (r5 != r10) goto L_0x002c;
    L_0x002b:
        goto L_0x0030;
    L_0x002c:
        r3 = r3 + 1;
        goto L_0x001d;
    L_0x002f:
        r3 = -1;
    L_0x0030:
        if (r3 >= 0) goto L_0x0062;
    L_0x0032:
        r4 = new com.vuforia.ar.pl.Camera1_Preview$CameraCacheInfo;
        r4.<init>();
        r4.deviceID = r10;
        r4.deviceHandle = r8;
        r4.camera = r12;
        r4.surface = r12;
        r8 = r12;
        r8 = (byte[][]) r8;
        r4.buffer = r8;
        r4.overrideWidth = r0;
        r4.requestWidth = r0;
        r4.bufferWidth = r0;
        r4.overrideHeight = r0;
        r4.requestHeight = r0;
        r4.bufferHeight = r0;
        r8 = 268439808; // 0x10001100 float:2.5256645E-29 double:1.32626887E-315;
        r4.bufferFormatPL = r8;
        r4.overrideFormatAndroid = r0;
        r4.requestFormatAndroid = r0;
        r4.caps = r12;
        r8 = 268443649; // 0x10002001 float:2.5268204E-29 double:1.32628785E-315;
        r4.status = r8;
        r4.isAutoFocusing = r0;
    L_0x0062:
        r4.bufferSize = r0;
        r8 = 10;
        r8 = 0;
        r9 = 10;
    L_0x0069:
        r10 = 9;
        r11 = 1;
        r10 = com.vuforia.ar.pl.SystemTools.checkMinimumApiLevel(r10);	 Catch:{ Exception -> 0x008c }
        if (r10 == 0) goto L_0x007b;	 Catch:{ Exception -> 0x008c }
    L_0x0072:
        r10 = r4.deviceID;	 Catch:{ Exception -> 0x008c }
        r10 = android.hardware.Camera.open(r10);	 Catch:{ Exception -> 0x008c }
        r4.camera = r10;	 Catch:{ Exception -> 0x008c }
        goto L_0x0085;	 Catch:{ Exception -> 0x008c }
    L_0x007b:
        r10 = r4.deviceID;	 Catch:{ Exception -> 0x008c }
        if (r10 != 0) goto L_0x0085;	 Catch:{ Exception -> 0x008c }
    L_0x007f:
        r10 = android.hardware.Camera.open();	 Catch:{ Exception -> 0x008c }
        r4.camera = r10;	 Catch:{ Exception -> 0x008c }
    L_0x0085:
        r10 = r4.camera;	 Catch:{ Exception -> 0x008c }
        if (r10 == 0) goto L_0x008b;
    L_0x0089:
        r8 = 1;
        goto L_0x008c;
    L_0x008b:
        r8 = 0;
    L_0x008c:
        if (r8 != 0) goto L_0x009c;
    L_0x008e:
        if (r9 <= 0) goto L_0x009c;
    L_0x0090:
        monitor-enter(r7);	 Catch:{ Exception -> 0x009b }
        r5 = 250; // 0xfa float:3.5E-43 double:1.235E-321;
        r7.wait(r5);	 Catch:{ all -> 0x0098 }
        monitor-exit(r7);	 Catch:{ all -> 0x0098 }
        goto L_0x009c;	 Catch:{ all -> 0x0098 }
    L_0x0098:
        r10 = move-exception;	 Catch:{ all -> 0x0098 }
        monitor-exit(r7);	 Catch:{ all -> 0x0098 }
        throw r10;	 Catch:{ Exception -> 0x009b }
    L_0x009c:
        if (r8 != 0) goto L_0x00a5;
    L_0x009e:
        r10 = r9 + -1;
        if (r9 > 0) goto L_0x00a3;
    L_0x00a2:
        goto L_0x00a5;
    L_0x00a3:
        r9 = r10;
        goto L_0x0069;
    L_0x00a5:
        r8 = r4.camera;
        if (r8 != 0) goto L_0x00ad;
    L_0x00a9:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r1);
        return r2;
    L_0x00ad:
        if (r14 == 0) goto L_0x00b2;
    L_0x00af:
        r8 = r14.length;
        if (r8 > 0) goto L_0x00b7;
    L_0x00b2:
        if (r15 == 0) goto L_0x00b9;
    L_0x00b4:
        r8 = r15.length;
        if (r8 <= 0) goto L_0x00b9;
    L_0x00b7:
        r8 = 1;
        goto L_0x00ba;
    L_0x00b9:
        r8 = 0;
    L_0x00ba:
        if (r13 == 0) goto L_0x00c3;
    L_0x00bc:
        r9 = r13.length();
        if (r9 <= 0) goto L_0x00c3;
    L_0x00c2:
        r0 = 1;
    L_0x00c3:
        if (r8 != 0) goto L_0x00c7;
    L_0x00c5:
        if (r0 == 0) goto L_0x0102;
    L_0x00c7:
        r9 = r4.camera;
        r9 = r7.getCameraParameters(r9);
        if (r9 != 0) goto L_0x00d3;
    L_0x00cf:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r1);
        return r2;
    L_0x00d3:
        r10 = 2;
        if (r8 == 0) goto L_0x00ea;
    L_0x00d6:
        if (r14 == 0) goto L_0x00e0;
    L_0x00d8:
        r8 = r14.length;
        r12 = 5;
        if (r8 == r12) goto L_0x00e0;
    L_0x00dc:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10);
        return r2;
    L_0x00e0:
        r8 = r7.setCameraCaptureParams(r4, r9, r14, r15);
        if (r8 != 0) goto L_0x00ea;
    L_0x00e6:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r1);
        return r2;
    L_0x00ea:
        if (r0 == 0) goto L_0x00f6;
    L_0x00ec:
        r8 = r7.setCustomCameraParams(r9, r13);
        if (r8 != 0) goto L_0x00f6;
    L_0x00f2:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r10);
        return r2;
    L_0x00f6:
        r8 = r4.camera;	 Catch:{ Exception -> 0x0122 }
        r8.setParameters(r9);	 Catch:{ Exception -> 0x0122 }
        r8 = r7.checkSamsungHighFPS(r4);
        if (r8 != 0) goto L_0x0102;
    L_0x0101:
        return r2;
    L_0x0102:
        r8 = 268443650; // 0x10002002 float:2.5268207E-29 double:1.326287853E-315;
        r4.status = r8;
        if (r3 >= 0) goto L_0x0116;
    L_0x0109:
        r8 = r7.cameraCacheInfo;
        r8.add(r4);
        r8 = r7.cameraCacheInfo;
        r8 = r8.size();
        r3 = r8 + -1;
    L_0x0116:
        r8 = r7.cameraCacheInfoIndexCache;
        r9 = r4.camera;
        r10 = java.lang.Integer.valueOf(r3);
        r8.put(r9, r10);
        return r3;
    L_0x0122:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r1);
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.Camera1_Preview.open(long, int, int, int, java.lang.String, int[], int[]):int");
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
	at jadx.api.JadxDecompiler$$Lambda$8/1630521067.run(Unknown Source)
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
        r1 = r3.cameraCacheInfoIndexCache;
        r2 = r4.camera;
        r1.remove(r2);
        r1 = r4.camera;	 Catch:{ Exception -> 0x0019 }
        r1.release();	 Catch:{ Exception -> 0x0019 }
        r0 = 1;
    L_0x0019:
        r1 = 0;
        r4.camera = r1;
        r1 = (byte[][]) r1;
        r4.buffer = r1;
        r1 = 268443649; // 0x10002001 float:2.5268204E-29 double:1.32628785E-315;
        r4.status = r1;
        java.lang.System.gc();
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.Camera1_Preview.close(int):boolean");
    }

    public int[] getCameraCapabilities(int i) {
        Camera1_Preview camera1_Preview = this;
        CameraCacheInfo cameraCacheInfo = getCameraCacheInfo(i);
        if (cameraCacheInfo == null) {
            SystemTools.setSystemErrorCode(4);
            return null;
        } else if (cameraCacheInfo.caps != null) {
            return cameraCacheInfo.caps;
        } else {
            Parameters cameraParameters = getCameraParameters(cameraCacheInfo.camera);
            if (cameraParameters == null) {
                SystemTools.setSystemErrorCode(6);
                return null;
            }
            int i2;
            ListIterator listIterator;
            int i3;
            List supportedPreviewSizes = cameraParameters.getSupportedPreviewSizes();
            List supportedPreviewFrameRates = cameraParameters.getSupportedPreviewFrameRates();
            List supportedPreviewFormats = cameraParameters.getSupportedPreviewFormats();
            List supportedFlashModes = cameraParameters.getSupportedFlashModes();
            List supportedFocusModes = cameraParameters.getSupportedFocusModes();
            boolean z = false;
            int size = supportedPreviewSizes != null ? supportedPreviewSizes.size() : 0;
            int size2 = supportedPreviewFrameRates != null ? supportedPreviewFrameRates.size() : 0;
            int size3 = supportedPreviewFormats != null ? supportedPreviewFormats.size() : 0;
            cameraCacheInfo.caps = new int[((((size * 2) + 6) + size2) + size3)];
            cameraCacheInfo.caps[0] = AR_CAMERA_PARAMTYPE_BASE;
            boolean z2 = supportedFlashModes != null && (supportedFlashModes.contains("torch") || supportedFlashModes.contains("on"));
            setCameraCapsBit(cameraCacheInfo, 0, AR_CAMERA_PARAMTYPE_TORCHMODE, z2);
            setCameraCapsBit(cameraCacheInfo, 0, AR_CAMERA_PARAMTYPE_FOCUSMODE, true);
            setCameraCapsBit(cameraCacheInfo, 0, AR_CAMERA_PARAMTYPE_FOCUSVALUE, SystemTools.checkMinimumApiLevel(8));
            setCameraCapsBit(cameraCacheInfo, 0, AR_CAMERA_PARAMTYPE_FOCUSREGION, SystemTools.checkMinimumApiLevel(14));
            setCameraCapsBit(cameraCacheInfo, 0, AR_CAMERA_PARAMTYPE_EXPOSUREVALUE, SystemTools.checkMinimumApiLevel(8));
            setCameraCapsBit(cameraCacheInfo, 0, AR_CAMERA_PARAMTYPE_EXPOSUREVALUERANGE, SystemTools.checkMinimumApiLevel(8));
            boolean z3 = SystemTools.checkMinimumApiLevel(8) && cameraParameters.isZoomSupported();
            setCameraCapsBit(cameraCacheInfo, 0, AR_CAMERA_PARAMTYPE_ZOOMVALUE, z3);
            z2 = SystemTools.checkMinimumApiLevel(8) && cameraParameters.isZoomSupported();
            setCameraCapsBit(cameraCacheInfo, 0, AR_CAMERA_PARAMTYPE_ZOOMRANGE, z2);
            setCameraCapsBit(cameraCacheInfo, 0, AR_CAMERA_PARAMTYPE_VIDEO_STABILIZATION, SystemTools.checkMinimumApiLevel(15));
            cameraCacheInfo.caps[1] = AR_CAMERA_PARAMTYPE_BASE;
            z3 = supportedFlashModes != null && (supportedFlashModes.contains("torch") || supportedFlashModes.contains("on"));
            setCameraCapsBit(cameraCacheInfo, 1, AR_CAMERA_PARAMTYPE_TORCHMODE, z3);
            setCameraCapsBit(cameraCacheInfo, 1, AR_CAMERA_PARAMTYPE_FOCUSMODE, true);
            setCameraCapsBit(cameraCacheInfo, 1, AR_CAMERA_PARAMTYPE_FOCUSREGION, SystemTools.checkMinimumApiLevel(14));
            setCameraCapsBit(cameraCacheInfo, 1, AR_CAMERA_PARAMTYPE_EXPOSUREVALUE, SystemTools.checkMinimumApiLevel(8));
            if (SystemTools.checkMinimumApiLevel(8) && cameraParameters.isZoomSupported()) {
                i2 = AR_CAMERA_PARAMTYPE_ZOOMVALUE;
                z = true;
            } else {
                i2 = AR_CAMERA_PARAMTYPE_ZOOMVALUE;
            }
            setCameraCapsBit(cameraCacheInfo, 1, i2, z);
            setCameraCapsBit(cameraCacheInfo, 1, AR_CAMERA_PARAMTYPE_VIDEO_STABILIZATION, SystemTools.checkMinimumApiLevel(15));
            cameraCacheInfo.caps[2] = AR_CAMERA_PARAMVALUE_BASE;
            if (supportedFlashModes != null && (supportedFlashModes.contains("torch") || supportedFlashModes.contains("on"))) {
                setCameraCapsBit(cameraCacheInfo, 2, AR_CAMERA_TORCHMODE_OFF, true);
                setCameraCapsBit(cameraCacheInfo, 2, AR_CAMERA_TORCHMODE_ON, true);
            }
            if (supportedFocusModes != null) {
                setCameraCapsBit(cameraCacheInfo, 2, AR_CAMERA_FOCUSMODE_NORMAL, true);
                setCameraCapsBit(cameraCacheInfo, 2, AR_CAMERA_FOCUSMODE_AUTO, supportedFocusModes.contains("auto"));
                setCameraCapsBit(cameraCacheInfo, 2, AR_CAMERA_FOCUSMODE_CONTINUOUSAUTO, supportedFocusModes.contains("continuous-video"));
                setCameraCapsBit(cameraCacheInfo, 2, AR_CAMERA_FOCUSMODE_MACRO, supportedFocusModes.contains("macro"));
                setCameraCapsBit(cameraCacheInfo, 2, AR_CAMERA_FOCUSMODE_INFINITY, supportedFocusModes.contains("infinity"));
                setCameraCapsBit(cameraCacheInfo, 2, AR_CAMERA_FOCUSMODE_FIXED, supportedFocusModes.contains("fixed"));
            }
            cameraCacheInfo.caps[3] = size;
            cameraCacheInfo.caps[4] = size2;
            cameraCacheInfo.caps[5] = size3;
            if (size > 0) {
                listIterator = supportedPreviewSizes.listIterator();
                int i4 = 6;
                while (listIterator.hasNext()) {
                    Size size4 = (Size) listIterator.next();
                    cameraCacheInfo.caps[i4] = size4.width;
                    cameraCacheInfo.caps[i4 + 1] = size4.height;
                    i4 += 2;
                }
                i3 = i4;
            } else {
                i3 = 6;
            }
            if (size2 > 0) {
                listIterator = supportedPreviewFrameRates.listIterator();
                while (listIterator.hasNext()) {
                    cameraCacheInfo.caps[i3] = ((Integer) listIterator.next()).intValue();
                    i3++;
                }
            }
            if (size3 > 0) {
                listIterator = supportedPreviewFormats.listIterator();
                while (listIterator.hasNext()) {
                    cameraCacheInfo.caps[i3] = translateImageFormat(((Integer) listIterator.next()).intValue(), true);
                    i3++;
                }
            }
            return cameraCacheInfo.caps;
        }
    }

    public boolean setCaptureInfo(int r4, int[] r5, int[] r6) {
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
        r3 = this;
        r4 = r3.getCameraCacheInfo(r4);
        r0 = 0;
        if (r4 != 0) goto L_0x000c;
    L_0x0007:
        r4 = 4;
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r4);
        return r0;
    L_0x000c:
        r1 = r5.length;
        r2 = 5;
        if (r1 == r2) goto L_0x0015;
    L_0x0010:
        r4 = 2;
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r4);
        return r0;
    L_0x0015:
        r1 = r4.camera;
        r1 = r3.getCameraParameters(r1);
        r2 = 6;
        if (r1 != 0) goto L_0x0022;
    L_0x001e:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r2);
        return r0;
    L_0x0022:
        r5 = r3.setCameraCaptureParams(r4, r1, r5, r6);
        if (r5 != 0) goto L_0x002c;
    L_0x0028:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r2);
        return r0;
    L_0x002c:
        r5 = r4.camera;	 Catch:{ Exception -> 0x003a }
        r5.setParameters(r1);	 Catch:{ Exception -> 0x003a }
        r4 = r3.checkSamsungHighFPS(r4);
        if (r4 != 0) goto L_0x0038;
    L_0x0037:
        return r0;
    L_0x0038:
        r4 = 1;
        return r4;
    L_0x003a:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r2);
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.Camera1_Preview.setCaptureInfo(int, int[], int[]):boolean");
    }

    public int[] getCaptureInfo(int r11) {
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
        r10 = this;
        r11 = r10.getCameraCacheInfo(r11);
        r0 = 4;
        r1 = 0;
        if (r11 != 0) goto L_0x000c;
    L_0x0008:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r0);
        return r1;
    L_0x000c:
        r2 = r11.camera;
        r2 = r10.getCameraParameters(r2);
        r3 = 6;
        if (r2 != 0) goto L_0x0019;
    L_0x0015:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r3);
        return r1;
    L_0x0019:
        r4 = 5;
        r4 = new int[r4];	 Catch:{ Exception -> 0x004e }
        r5 = r2.getPreviewSize();	 Catch:{ Exception -> 0x004e }
        r5 = r5.width;	 Catch:{ Exception -> 0x004e }
        r6 = 0;	 Catch:{ Exception -> 0x004e }
        r4[r6] = r5;	 Catch:{ Exception -> 0x004e }
        r5 = r2.getPreviewSize();	 Catch:{ Exception -> 0x004e }
        r5 = r5.height;	 Catch:{ Exception -> 0x004e }
        r7 = 1;	 Catch:{ Exception -> 0x004e }
        r4[r7] = r5;	 Catch:{ Exception -> 0x004e }
        r5 = 2;	 Catch:{ Exception -> 0x004e }
        r8 = r2.getPreviewFormat();	 Catch:{ Exception -> 0x004e }
        r9 = CONVERT_FORMAT_TO_PL;	 Catch:{ Exception -> 0x004e }
        r8 = r10.translateImageFormat(r8, r9);	 Catch:{ Exception -> 0x004e }
        r4[r5] = r8;	 Catch:{ Exception -> 0x004e }
        r5 = 3;	 Catch:{ Exception -> 0x004e }
        r2 = r2.getPreviewFrameRate();	 Catch:{ Exception -> 0x004e }
        r4[r5] = r2;	 Catch:{ Exception -> 0x004e }
        r2 = r11.surface;	 Catch:{ Exception -> 0x004e }
        if (r2 != 0) goto L_0x004a;	 Catch:{ Exception -> 0x004e }
    L_0x0046:
        r11 = r11.surfaceTexture;	 Catch:{ Exception -> 0x004e }
        if (r11 == 0) goto L_0x004b;	 Catch:{ Exception -> 0x004e }
    L_0x004a:
        r6 = 1;	 Catch:{ Exception -> 0x004e }
    L_0x004b:
        r4[r0] = r6;	 Catch:{ Exception -> 0x004e }
        return r4;
    L_0x004e:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r3);
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.Camera1_Preview.getCaptureInfo(int):int[]");
    }

    public boolean start(int r4) {
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
        r3 = this;
        r4 = r3.getCameraCacheInfo(r4);
        r0 = 0;
        if (r4 != 0) goto L_0x000c;
    L_0x0007:
        r4 = 4;
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r4);
        return r0;
    L_0x000c:
        r1 = r3.setupPreviewBuffer(r4);
        r2 = 6;
        if (r1 != 0) goto L_0x0017;
    L_0x0013:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r2);
        return r0;
    L_0x0017:
        r1 = r4.camera;	 Catch:{ Exception -> 0x0023 }
        r1.startPreview();	 Catch:{ Exception -> 0x0023 }
        r1 = 268443651; // 0x10002003 float:2.526821E-29 double:1.32628786E-315;	 Catch:{ Exception -> 0x0023 }
        r4.status = r1;	 Catch:{ Exception -> 0x0023 }
        r4 = 1;
        return r4;
    L_0x0023:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r2);
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.Camera1_Preview.start(int):boolean");
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
	at jadx.api.JadxDecompiler$$Lambda$8/1630521067.run(Unknown Source)
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
        r1 = r3.camera;	 Catch:{ Exception -> 0x0018 }
        r1.stopPreview();	 Catch:{ Exception -> 0x0018 }
        r1 = 268443650; // 0x10002002 float:2.5268207E-29 double:1.326287853E-315;	 Catch:{ Exception -> 0x0018 }
        r3.status = r1;	 Catch:{ Exception -> 0x0018 }
        r3 = 1;
        return r3;
    L_0x0018:
        r3 = 6;
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r3);
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.Camera1_Preview.stop(int):boolean");
    }

    public boolean setBatchParameters(int i, String str) {
        if (str == null) {
            return false;
        }
        i = getCameraCacheInfo(i);
        if (i != 0) {
            if (i.camera != null) {
                Parameters cameraParameters = getCameraParameters(i.camera);
                if (cameraParameters == null) {
                    SystemTools.setSystemErrorCode(6);
                    return false;
                } else if (setCustomCameraParams(cameraParameters, str) == null) {
                    return false;
                } else {
                    i.camera.setParameters(cameraParameters);
                    return true;
                }
            }
        }
        SystemTools.setSystemErrorCode(4);
        return false;
    }

    boolean setUntypedCameraParameter(int r4, java.lang.String r5, java.lang.String r6) {
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
        r3 = this;
        r4 = r3.getCameraCacheInfo(r4);
        r0 = 0;
        if (r4 == 0) goto L_0x0027;
    L_0x0007:
        r1 = r4.camera;
        if (r1 != 0) goto L_0x000c;
    L_0x000b:
        goto L_0x0027;
    L_0x000c:
        r1 = r4.camera;
        r1 = r3.getCameraParameters(r1);
        r2 = 6;
        if (r1 != 0) goto L_0x0019;
    L_0x0015:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r2);
        return r0;
    L_0x0019:
        r1.set(r5, r6);	 Catch:{ Exception -> 0x0023 }
        r4 = r4.camera;	 Catch:{ Exception -> 0x0023 }
        r4.setParameters(r1);	 Catch:{ Exception -> 0x0023 }
        r4 = 1;
        return r4;
    L_0x0023:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r2);
        return r0;
    L_0x0027:
        r4 = 4;
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r4);
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.Camera1_Preview.setUntypedCameraParameter(int, java.lang.String, java.lang.String):boolean");
    }

    String getUntypedCameraParameter(int i, String str) {
        i = getCameraCacheInfo(i);
        if (i != 0) {
            if (i.camera != null) {
                i = getCameraParameters(i.camera);
                if (i == 0) {
                    SystemTools.setSystemErrorCode(6);
                    return null;
                }
                i = i.get(str);
                if (i == 0) {
                    SystemTools.setSystemErrorCode(6);
                }
                return i;
            }
        }
        SystemTools.setSystemErrorCode(4);
        return null;
    }

    String getFlattenedParameters(int i) {
        i = getCameraCacheInfo(i);
        if (i != 0) {
            if (i.camera != null) {
                i = getCameraParameters(i.camera);
                if (i != 0) {
                    return i.flatten();
                }
                SystemTools.setSystemErrorCode(6);
                return BuildConfig.FLAVOR;
            }
        }
        SystemTools.setSystemErrorCode(4);
        return BuildConfig.FLAVOR;
    }

    boolean setTypedCameraParameter(int r18, int r19, java.lang.Object r20) {
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
        r17 = this;
        r0 = r17;
        r1 = r19;
        r3 = r17.getCameraCacheInfo(r18);
        r4 = 4;
        r5 = 0;
        if (r3 == 0) goto L_0x0305;
    L_0x000c:
        r6 = r3.camera;
        if (r6 != 0) goto L_0x0012;
    L_0x0010:
        goto L_0x0305;
    L_0x0012:
        r6 = r3.camera;
        r6 = r0.getCameraParameters(r6);
        r7 = 6;
        if (r6 != 0) goto L_0x001f;
    L_0x001b:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r7);
        return r5;
    L_0x001f:
        r8 = 8;
        r9 = 14;
        r10 = 0;
        r11 = 3;
        r12 = 1;
        switch(r1) {
            case 536870913: goto L_0x02a4;
            case 536870914: goto L_0x0242;
            case 536870916: goto L_0x023d;
            case 536870920: goto L_0x0238;
            case 536870928: goto L_0x0176;
            case 536870944: goto L_0x014a;
            case 536870976: goto L_0x0110;
            case 536871936: goto L_0x00e9;
            case 536872960: goto L_0x00e5;
            case 536875008: goto L_0x00a2;
            case 536879104: goto L_0x009e;
            case 536887296: goto L_0x009a;
            case 536903680: goto L_0x007d;
            case 536936448: goto L_0x0079;
            case 537001984: goto L_0x0075;
            case 537133056: goto L_0x0071;
            case 537395200: goto L_0x006d;
            case 537919488: goto L_0x0069;
            case 538968064: goto L_0x0065;
            case 541065216: goto L_0x003f;
            case 553648128: goto L_0x002b;
            default: goto L_0x0029;
        };
    L_0x0029:
        r1 = 0;
        return r1;
    L_0x002b:
        r2 = r20;	 Catch:{ Exception -> 0x0300 }
        r2 = (java.lang.Boolean) r2;	 Catch:{ Exception -> 0x0300 }
        r2 = r2.booleanValue();	 Catch:{ Exception -> 0x0300 }
        if (r2 == 0) goto L_0x003a;	 Catch:{ Exception -> 0x0300 }
    L_0x0035:
        r6.setVideoStabilization(r12);	 Catch:{ Exception -> 0x0300 }
        goto L_0x0173;	 Catch:{ Exception -> 0x0300 }
    L_0x003a:
        r6.setVideoStabilization(r5);	 Catch:{ Exception -> 0x0300 }
        goto L_0x0173;	 Catch:{ Exception -> 0x0300 }
    L_0x003f:
        r2 = r20;	 Catch:{ Exception -> 0x0300 }
        r2 = (java.lang.Number) r2;	 Catch:{ Exception -> 0x0300 }
        r2 = r2.intValue();	 Catch:{ Exception -> 0x0300 }
        r4 = com.vuforia.ar.pl.SystemTools.checkMinimumApiLevel(r9);	 Catch:{ Exception -> 0x0300 }
        if (r4 == 0) goto L_0x0057;	 Catch:{ Exception -> 0x0300 }
    L_0x004d:
        if (r2 == 0) goto L_0x0051;	 Catch:{ Exception -> 0x0300 }
    L_0x004f:
        r2 = 1;	 Catch:{ Exception -> 0x0300 }
        goto L_0x0052;	 Catch:{ Exception -> 0x0300 }
    L_0x0051:
        r2 = 0;	 Catch:{ Exception -> 0x0300 }
    L_0x0052:
        r6.setRecordingHint(r2);	 Catch:{ Exception -> 0x0300 }
        goto L_0x0173;	 Catch:{ Exception -> 0x0300 }
    L_0x0057:
        r4 = "recording-hint";	 Catch:{ Exception -> 0x0300 }
        if (r2 == 0) goto L_0x005e;	 Catch:{ Exception -> 0x0300 }
    L_0x005b:
        r2 = "true";	 Catch:{ Exception -> 0x0300 }
        goto L_0x0060;	 Catch:{ Exception -> 0x0300 }
    L_0x005e:
        r2 = "false";	 Catch:{ Exception -> 0x0300 }
    L_0x0060:
        r6.set(r4, r2);	 Catch:{ Exception -> 0x0300 }
        goto L_0x0173;	 Catch:{ Exception -> 0x0300 }
    L_0x0065:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r7);	 Catch:{ Exception -> 0x0300 }
        return r5;	 Catch:{ Exception -> 0x0300 }
    L_0x0069:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r7);	 Catch:{ Exception -> 0x0300 }
        return r5;	 Catch:{ Exception -> 0x0300 }
    L_0x006d:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r7);	 Catch:{ Exception -> 0x0300 }
        return r5;	 Catch:{ Exception -> 0x0300 }
    L_0x0071:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r7);	 Catch:{ Exception -> 0x0300 }
        return r5;	 Catch:{ Exception -> 0x0300 }
    L_0x0075:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r7);	 Catch:{ Exception -> 0x0300 }
        return r5;	 Catch:{ Exception -> 0x0300 }
    L_0x0079:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r7);	 Catch:{ Exception -> 0x0300 }
        return r5;	 Catch:{ Exception -> 0x0300 }
    L_0x007d:
        r4 = com.vuforia.ar.pl.SystemTools.checkMinimumApiLevel(r8);	 Catch:{ Exception -> 0x0300 }
        if (r4 == 0) goto L_0x0096;	 Catch:{ Exception -> 0x0300 }
    L_0x0083:
        r4 = r6.isZoomSupported();	 Catch:{ Exception -> 0x0300 }
        if (r4 == 0) goto L_0x0096;	 Catch:{ Exception -> 0x0300 }
    L_0x0089:
        r2 = r20;	 Catch:{ Exception -> 0x0300 }
        r2 = (java.lang.Number) r2;	 Catch:{ Exception -> 0x0300 }
        r2 = r2.intValue();	 Catch:{ Exception -> 0x0300 }
        r6.setZoom(r2);	 Catch:{ Exception -> 0x0300 }
        goto L_0x0173;	 Catch:{ Exception -> 0x0300 }
    L_0x0096:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r7);	 Catch:{ Exception -> 0x0300 }
        return r5;	 Catch:{ Exception -> 0x0300 }
    L_0x009a:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r7);	 Catch:{ Exception -> 0x0300 }
        return r5;	 Catch:{ Exception -> 0x0300 }
    L_0x009e:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r7);	 Catch:{ Exception -> 0x0300 }
        return r5;	 Catch:{ Exception -> 0x0300 }
    L_0x00a2:
        r2 = r20;	 Catch:{ Exception -> 0x0300 }
        r2 = (java.lang.Number) r2;	 Catch:{ Exception -> 0x0300 }
        r2 = r2.intValue();	 Catch:{ Exception -> 0x0300 }
        r4 = 806354944; // 0x30100000 float:5.2386895E-10 double:3.98392276E-315;	 Catch:{ Exception -> 0x0300 }
        if (r2 == r4) goto L_0x00da;	 Catch:{ Exception -> 0x0300 }
    L_0x00ae:
        r4 = 809500672; // 0x30400000 float:6.9849193E-10 double:3.999464723E-315;	 Catch:{ Exception -> 0x0300 }
        if (r2 == r4) goto L_0x00cf;	 Catch:{ Exception -> 0x0300 }
    L_0x00b2:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r11);	 Catch:{ Exception -> 0x0300 }
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0300 }
        r1.<init>();	 Catch:{ Exception -> 0x0300 }
        r3 = "Cannot set unknown white balance mode (";	 Catch:{ Exception -> 0x0300 }
        r1.append(r3);	 Catch:{ Exception -> 0x0300 }
        r1.append(r2);	 Catch:{ Exception -> 0x0300 }
        r2 = ")";	 Catch:{ Exception -> 0x0300 }
        r1.append(r2);	 Catch:{ Exception -> 0x0300 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x0300 }
        com.vuforia.ar.pl.SystemTools.logSystemError(r1);	 Catch:{ Exception -> 0x0300 }
        return r5;	 Catch:{ Exception -> 0x0300 }
    L_0x00cf:
        r2 = r6.isAutoWhiteBalanceLockSupported();	 Catch:{ Exception -> 0x0300 }
        if (r2 == 0) goto L_0x0173;	 Catch:{ Exception -> 0x0300 }
    L_0x00d5:
        r6.setAutoWhiteBalanceLock(r5);	 Catch:{ Exception -> 0x0300 }
        goto L_0x0173;	 Catch:{ Exception -> 0x0300 }
    L_0x00da:
        r2 = r6.isAutoWhiteBalanceLockSupported();	 Catch:{ Exception -> 0x0300 }
        if (r2 == 0) goto L_0x0173;	 Catch:{ Exception -> 0x0300 }
    L_0x00e0:
        r6.setAutoWhiteBalanceLock(r12);	 Catch:{ Exception -> 0x0300 }
        goto L_0x0173;	 Catch:{ Exception -> 0x0300 }
    L_0x00e5:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r7);	 Catch:{ Exception -> 0x0300 }
        return r5;	 Catch:{ Exception -> 0x0300 }
    L_0x00e9:
        r4 = com.vuforia.ar.pl.SystemTools.checkMinimumApiLevel(r8);	 Catch:{ Exception -> 0x0300 }
        if (r4 == 0) goto L_0x010c;	 Catch:{ Exception -> 0x0300 }
    L_0x00ef:
        r2 = r20;	 Catch:{ Exception -> 0x0300 }
        r2 = (java.lang.Number) r2;	 Catch:{ Exception -> 0x0300 }
        r2 = r2.floatValue();	 Catch:{ Exception -> 0x0300 }
        r4 = r6.getExposureCompensationStep();	 Catch:{ Exception -> 0x0300 }
        r8 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1));	 Catch:{ Exception -> 0x0300 }
        if (r8 != 0) goto L_0x0103;	 Catch:{ Exception -> 0x0300 }
    L_0x00ff:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r7);	 Catch:{ Exception -> 0x0300 }
        return r5;	 Catch:{ Exception -> 0x0300 }
    L_0x0103:
        r2 = r2 / r4;	 Catch:{ Exception -> 0x0300 }
        r2 = java.lang.Math.round(r2);	 Catch:{ Exception -> 0x0300 }
        r6.setExposureCompensation(r2);	 Catch:{ Exception -> 0x0300 }
        goto L_0x0173;	 Catch:{ Exception -> 0x0300 }
    L_0x010c:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r7);	 Catch:{ Exception -> 0x0300 }
        return r5;	 Catch:{ Exception -> 0x0300 }
    L_0x0110:
        r2 = r20;	 Catch:{ Exception -> 0x0300 }
        r2 = (java.lang.Number) r2;	 Catch:{ Exception -> 0x0300 }
        r2 = r2.intValue();	 Catch:{ Exception -> 0x0300 }
        r2 = java.lang.Integer.toString(r2);	 Catch:{ Exception -> 0x0300 }
        r4 = "iso-values";	 Catch:{ Exception -> 0x0300 }
        r4 = r6.get(r4);	 Catch:{ Exception -> 0x0300 }
        if (r4 == 0) goto L_0x0144;	 Catch:{ Exception -> 0x0300 }
    L_0x0124:
        r8 = ",";	 Catch:{ Exception -> 0x0300 }
        r4 = r4.split(r8);	 Catch:{ Exception -> 0x0300 }
        r8 = 0;	 Catch:{ Exception -> 0x0300 }
    L_0x012b:
        r9 = r4.length;	 Catch:{ Exception -> 0x0300 }
        if (r8 >= r9) goto L_0x0144;	 Catch:{ Exception -> 0x0300 }
    L_0x012e:
        r9 = r4[r8];	 Catch:{ Exception -> 0x0300 }
        r9 = r9.toLowerCase();	 Catch:{ Exception -> 0x0300 }
        r10 = r2.toLowerCase();	 Catch:{ Exception -> 0x0300 }
        r9 = r9.contains(r10);	 Catch:{ Exception -> 0x0300 }
        if (r9 == 0) goto L_0x0141;	 Catch:{ Exception -> 0x0300 }
    L_0x013e:
        r2 = r4[r8];	 Catch:{ Exception -> 0x0300 }
        goto L_0x0144;	 Catch:{ Exception -> 0x0300 }
    L_0x0141:
        r8 = r8 + 1;	 Catch:{ Exception -> 0x0300 }
        goto L_0x012b;	 Catch:{ Exception -> 0x0300 }
    L_0x0144:
        r4 = "iso";	 Catch:{ Exception -> 0x0300 }
        r6.set(r4, r2);	 Catch:{ Exception -> 0x0300 }
        goto L_0x0173;	 Catch:{ Exception -> 0x0300 }
    L_0x014a:
        r2 = r20;	 Catch:{ Exception -> 0x0300 }
        r2 = (java.lang.Number) r2;	 Catch:{ Exception -> 0x0300 }
        r2 = r2.intValue();	 Catch:{ Exception -> 0x0300 }
        r4 = 805310464; // 0x30001000 float:4.6588866E-10 double:3.978762345E-315;	 Catch:{ Exception -> 0x0300 }
        if (r2 == r4) goto L_0x016a;	 Catch:{ Exception -> 0x0300 }
    L_0x0157:
        r4 = 805322752; // 0x30004000 float:4.665708E-10 double:3.978823056E-315;	 Catch:{ Exception -> 0x0300 }
        if (r2 == r4) goto L_0x0160;	 Catch:{ Exception -> 0x0300 }
    L_0x015c:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r11);	 Catch:{ Exception -> 0x0300 }
        return r5;	 Catch:{ Exception -> 0x0300 }
    L_0x0160:
        r2 = r6.isAutoExposureLockSupported();	 Catch:{ Exception -> 0x0300 }
        if (r2 == 0) goto L_0x0173;	 Catch:{ Exception -> 0x0300 }
    L_0x0166:
        r6.setAutoExposureLock(r5);	 Catch:{ Exception -> 0x0300 }
        goto L_0x0173;	 Catch:{ Exception -> 0x0300 }
    L_0x016a:
        r2 = r6.isAutoExposureLockSupported();	 Catch:{ Exception -> 0x0300 }
        if (r2 == 0) goto L_0x0173;	 Catch:{ Exception -> 0x0300 }
    L_0x0170:
        r6.setAutoExposureLock(r12);	 Catch:{ Exception -> 0x0300 }
    L_0x0173:
        r4 = r6;	 Catch:{ Exception -> 0x0300 }
        goto L_0x02d7;	 Catch:{ Exception -> 0x0300 }
    L_0x0176:
        r8 = com.vuforia.ar.pl.SystemTools.checkMinimumApiLevel(r9);	 Catch:{ Exception -> 0x0300 }
        if (r8 == 0) goto L_0x0233;	 Catch:{ Exception -> 0x0300 }
    L_0x017c:
        r2 = r20;	 Catch:{ Exception -> 0x0300 }
        r2 = (float[]) r2;	 Catch:{ Exception -> 0x0300 }
        r2 = (float[]) r2;	 Catch:{ Exception -> 0x0300 }
        r8 = r2.length;	 Catch:{ Exception -> 0x0300 }
        r9 = 5;	 Catch:{ Exception -> 0x0300 }
        r13 = 2;	 Catch:{ Exception -> 0x0300 }
        if (r8 == r9) goto L_0x018b;	 Catch:{ Exception -> 0x0300 }
    L_0x0187:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r13);	 Catch:{ Exception -> 0x0300 }
        return r5;	 Catch:{ Exception -> 0x0300 }
    L_0x018b:
        r8 = r2[r5];	 Catch:{ Exception -> 0x0300 }
        r8 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1));	 Catch:{ Exception -> 0x0300 }
        if (r8 < 0) goto L_0x022e;	 Catch:{ Exception -> 0x0300 }
    L_0x0191:
        r8 = r2[r5];	 Catch:{ Exception -> 0x0300 }
        r9 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;	 Catch:{ Exception -> 0x0300 }
        r8 = (r8 > r9 ? 1 : (r8 == r9 ? 0 : -1));	 Catch:{ Exception -> 0x0300 }
        if (r8 > 0) goto L_0x022e;	 Catch:{ Exception -> 0x0300 }
    L_0x0199:
        r8 = r2[r12];	 Catch:{ Exception -> 0x0300 }
        r8 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1));	 Catch:{ Exception -> 0x0300 }
        if (r8 < 0) goto L_0x022e;	 Catch:{ Exception -> 0x0300 }
    L_0x019f:
        r8 = r2[r12];	 Catch:{ Exception -> 0x0300 }
        r8 = (r8 > r9 ? 1 : (r8 == r9 ? 0 : -1));	 Catch:{ Exception -> 0x0300 }
        if (r8 > 0) goto L_0x022e;	 Catch:{ Exception -> 0x0300 }
    L_0x01a5:
        r8 = r2[r13];	 Catch:{ Exception -> 0x0300 }
        r8 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1));	 Catch:{ Exception -> 0x0300 }
        if (r8 < 0) goto L_0x022e;	 Catch:{ Exception -> 0x0300 }
    L_0x01ab:
        r8 = r2[r13];	 Catch:{ Exception -> 0x0300 }
        r8 = (r8 > r9 ? 1 : (r8 == r9 ? 0 : -1));	 Catch:{ Exception -> 0x0300 }
        if (r8 > 0) goto L_0x022e;	 Catch:{ Exception -> 0x0300 }
    L_0x01b1:
        r8 = r2[r11];	 Catch:{ Exception -> 0x0300 }
        r8 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1));	 Catch:{ Exception -> 0x0300 }
        if (r8 < 0) goto L_0x022e;	 Catch:{ Exception -> 0x0300 }
    L_0x01b7:
        r8 = r2[r11];	 Catch:{ Exception -> 0x0300 }
        r8 = (r8 > r9 ? 1 : (r8 == r9 ? 0 : -1));	 Catch:{ Exception -> 0x0300 }
        if (r8 > 0) goto L_0x022e;	 Catch:{ Exception -> 0x0300 }
    L_0x01bd:
        r8 = r2[r4];	 Catch:{ Exception -> 0x0300 }
        r8 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1));	 Catch:{ Exception -> 0x0300 }
        if (r8 < 0) goto L_0x022e;	 Catch:{ Exception -> 0x0300 }
    L_0x01c3:
        r8 = r2[r4];	 Catch:{ Exception -> 0x0300 }
        r8 = (r8 > r9 ? 1 : (r8 == r9 ? 0 : -1));	 Catch:{ Exception -> 0x0300 }
        if (r8 <= 0) goto L_0x01ca;	 Catch:{ Exception -> 0x0300 }
    L_0x01c9:
        goto L_0x022e;	 Catch:{ Exception -> 0x0300 }
    L_0x01ca:
        r8 = new android.graphics.Rect;	 Catch:{ Exception -> 0x0300 }
        r9 = r2[r5];	 Catch:{ Exception -> 0x0300 }
        r9 = (double) r9;
        r14 = 4656510908468559872; // 0x409f400000000000 float:0.0 double:2000.0;
        java.lang.Double.isNaN(r9);
        r9 = r9 * r14;
        r9 = (int) r9;
        r9 = r9 + -1000;
        r10 = r2[r12];	 Catch:{ Exception -> 0x0300 }
        r16 = r6;
        r5 = (double) r10;
        java.lang.Double.isNaN(r5);
        r5 = r5 * r14;
        r5 = (int) r5;
        r5 = r5 + -1000;
        r6 = r2[r13];	 Catch:{ Exception -> 0x0300 }
        r12 = (double) r6;
        java.lang.Double.isNaN(r12);
        r12 = r12 * r14;
        r6 = (int) r12;
        r6 = r6 + -1000;
        r10 = r2[r11];	 Catch:{ Exception -> 0x0300 }
        r10 = (double) r10;
        java.lang.Double.isNaN(r10);
        r10 = r10 * r14;
        r10 = (int) r10;
        r10 = r10 + -1000;
        r8.<init>(r9, r5, r6, r10);	 Catch:{ Exception -> 0x0300 }
        r5 = new java.util.ArrayList;	 Catch:{ Exception -> 0x0300 }
        r5.<init>();	 Catch:{ Exception -> 0x0300 }
        r6 = new android.hardware.Camera$Area;	 Catch:{ Exception -> 0x0300 }
        r2 = r2[r4];	 Catch:{ Exception -> 0x0300 }
        r9 = (double) r2;
        r11 = 4652007308841189376; // 0x408f400000000000 float:0.0 double:1000.0;
        java.lang.Double.isNaN(r9);
        r9 = r9 * r11;
        r2 = (int) r9;
        r6.<init>(r8, r2);	 Catch:{ Exception -> 0x0300 }
        r5.add(r6);	 Catch:{ Exception -> 0x0300 }
        r2 = r16.getMaxNumFocusAreas();	 Catch:{ Exception -> 0x0300 }
        if (r2 <= 0) goto L_0x022a;	 Catch:{ Exception -> 0x0300 }
    L_0x0223:
        r4 = r16;	 Catch:{ Exception -> 0x0300 }
        r4.setFocusAreas(r5);	 Catch:{ Exception -> 0x0300 }
        goto L_0x02d7;	 Catch:{ Exception -> 0x0300 }
    L_0x022a:
        r4 = r16;	 Catch:{ Exception -> 0x0300 }
        goto L_0x02d7;	 Catch:{ Exception -> 0x0300 }
    L_0x022e:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r13);	 Catch:{ Exception -> 0x0300 }
        r1 = 0;
        return r1;
    L_0x0233:
        r1 = 0;
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r7);	 Catch:{ Exception -> 0x0301 }
        return r1;	 Catch:{ Exception -> 0x0301 }
    L_0x0238:
        r1 = 0;	 Catch:{ Exception -> 0x0301 }
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r7);	 Catch:{ Exception -> 0x0301 }
        return r1;	 Catch:{ Exception -> 0x0301 }
    L_0x023d:
        r1 = 0;	 Catch:{ Exception -> 0x0301 }
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r7);	 Catch:{ Exception -> 0x0301 }
        return r1;
    L_0x0242:
        r4 = r6;
        r5 = r3.camera;	 Catch:{ Exception -> 0x0300 }
        r5.cancelAutoFocus();	 Catch:{ Exception -> 0x0300 }
        r2 = r20;	 Catch:{ Exception -> 0x0300 }
        r2 = (java.lang.Number) r2;	 Catch:{ Exception -> 0x0300 }
        r2 = r2.intValue();	 Catch:{ Exception -> 0x0300 }
        switch(r2) {
            case 805306384: goto L_0x028b;
            case 805306400: goto L_0x0284;
            case 805306432: goto L_0x026d;
            case 805306496: goto L_0x0266;
            case 805306624: goto L_0x025f;
            case 805306880: goto L_0x0258;
            default: goto L_0x0253;
        };	 Catch:{ Exception -> 0x0300 }
    L_0x0253:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r11);	 Catch:{ Exception -> 0x0300 }
        r1 = 0;	 Catch:{ Exception -> 0x0300 }
        goto L_0x02a3;	 Catch:{ Exception -> 0x0300 }
    L_0x0258:
        r2 = "fixed";	 Catch:{ Exception -> 0x0300 }
        r4.setFocusMode(r2);	 Catch:{ Exception -> 0x0300 }
        goto L_0x02d7;	 Catch:{ Exception -> 0x0300 }
    L_0x025f:
        r2 = "infinity";	 Catch:{ Exception -> 0x0300 }
        r4.setFocusMode(r2);	 Catch:{ Exception -> 0x0300 }
        goto L_0x02d7;	 Catch:{ Exception -> 0x0300 }
    L_0x0266:
        r2 = "macro";	 Catch:{ Exception -> 0x0300 }
        r4.setFocusMode(r2);	 Catch:{ Exception -> 0x0300 }
        goto L_0x02d7;	 Catch:{ Exception -> 0x0300 }
    L_0x026d:
        r2 = r4.getSupportedFocusModes();	 Catch:{ Exception -> 0x0300 }
        r5 = "continuous-video";	 Catch:{ Exception -> 0x0300 }
        r2 = r2.contains(r5);	 Catch:{ Exception -> 0x0300 }
        if (r2 == 0) goto L_0x027f;	 Catch:{ Exception -> 0x0300 }
    L_0x0279:
        r2 = "continuous-video";	 Catch:{ Exception -> 0x0300 }
        r4.setFocusMode(r2);	 Catch:{ Exception -> 0x0300 }
        goto L_0x02d7;	 Catch:{ Exception -> 0x0300 }
    L_0x027f:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r7);	 Catch:{ Exception -> 0x0300 }
        r1 = 0;	 Catch:{ Exception -> 0x0300 }
        return r1;	 Catch:{ Exception -> 0x0300 }
    L_0x0284:
        r2 = "auto";	 Catch:{ Exception -> 0x0300 }
        r4.setFocusMode(r2);	 Catch:{ Exception -> 0x0300 }
    L_0x0289:
        r2 = 1;	 Catch:{ Exception -> 0x0300 }
        goto L_0x02d8;	 Catch:{ Exception -> 0x0300 }
    L_0x028b:
        r2 = r4.getSupportedFocusModes();	 Catch:{ Exception -> 0x0300 }
        r5 = "normal";	 Catch:{ Exception -> 0x0300 }
        r2 = r2.contains(r5);	 Catch:{ Exception -> 0x0300 }
        if (r2 == 0) goto L_0x029d;	 Catch:{ Exception -> 0x0300 }
    L_0x0297:
        r2 = "normal";	 Catch:{ Exception -> 0x0300 }
        r4.setFocusMode(r2);	 Catch:{ Exception -> 0x0300 }
        goto L_0x02d7;	 Catch:{ Exception -> 0x0300 }
    L_0x029d:
        r2 = "auto";	 Catch:{ Exception -> 0x0300 }
        r4.setFocusMode(r2);	 Catch:{ Exception -> 0x0300 }
        goto L_0x0289;	 Catch:{ Exception -> 0x0300 }
    L_0x02a3:
        return r1;	 Catch:{ Exception -> 0x0300 }
    L_0x02a4:
        r4 = r6;	 Catch:{ Exception -> 0x0300 }
        r2 = r20;	 Catch:{ Exception -> 0x0300 }
        r2 = (java.lang.Number) r2;	 Catch:{ Exception -> 0x0300 }
        r2 = r2.intValue();	 Catch:{ Exception -> 0x0300 }
        switch(r2) {
            case 805306369: goto L_0x02d2;
            case 805306370: goto L_0x02ba;
            case 805306371: goto L_0x02b0;
            case 805306372: goto L_0x02b5;
            default: goto L_0x02b0;
        };
    L_0x02b0:
        r1 = 0;
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r11);	 Catch:{ Exception -> 0x0301 }
        goto L_0x02ff;
    L_0x02b5:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r11);	 Catch:{ Exception -> 0x0300 }
        r1 = 0;	 Catch:{ Exception -> 0x0300 }
        return r1;	 Catch:{ Exception -> 0x0300 }
    L_0x02ba:
        r2 = r4.getSupportedFlashModes();	 Catch:{ Exception -> 0x0300 }
        r5 = "torch";	 Catch:{ Exception -> 0x0300 }
        r2 = r2.contains(r5);	 Catch:{ Exception -> 0x0300 }
        if (r2 == 0) goto L_0x02cc;	 Catch:{ Exception -> 0x0300 }
    L_0x02c6:
        r2 = "torch";	 Catch:{ Exception -> 0x0300 }
        r4.setFlashMode(r2);	 Catch:{ Exception -> 0x0300 }
        goto L_0x02d7;	 Catch:{ Exception -> 0x0300 }
    L_0x02cc:
        r2 = "on";	 Catch:{ Exception -> 0x0300 }
        r4.setFlashMode(r2);	 Catch:{ Exception -> 0x0300 }
        goto L_0x02d7;	 Catch:{ Exception -> 0x0300 }
    L_0x02d2:
        r2 = "off";	 Catch:{ Exception -> 0x0300 }
        r4.setFlashMode(r2);	 Catch:{ Exception -> 0x0300 }
    L_0x02d7:
        r2 = 0;
    L_0x02d8:
        r5 = r3.camera;	 Catch:{ Exception -> 0x02fa }
        r5.setParameters(r4);	 Catch:{ Exception -> 0x02fa }
        if (r2 == 0) goto L_0x02f8;
    L_0x02df:
        r2 = 536870914; // 0x20000002 float:1.0842024E-19 double:2.65249475E-315;
        if (r1 == r2) goto L_0x02e5;
    L_0x02e4:
        goto L_0x02f8;
    L_0x02e5:
        r1 = 1;
        r3.isAutoFocusing = r1;	 Catch:{ Exception -> 0x02f3 }
        r1 = r3.camera;	 Catch:{ Exception -> 0x02f3 }
        r2 = new com.vuforia.ar.pl.Camera1_Preview$1;	 Catch:{ Exception -> 0x02f3 }
        r2.<init>();	 Catch:{ Exception -> 0x02f3 }
        r1.autoFocus(r2);	 Catch:{ Exception -> 0x02f3 }
        goto L_0x02f8;
    L_0x02f3:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r7);
        r1 = 0;
        return r1;
    L_0x02f8:
        r1 = 1;
        return r1;
    L_0x02fa:
        r1 = 0;
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r7);
        return r1;
    L_0x02ff:
        return r1;
    L_0x0300:
        r1 = 0;
    L_0x0301:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r7);
        return r1;
    L_0x0305:
        r1 = 0;
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r4);
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.Camera1_Preview.setTypedCameraParameter(int, int, java.lang.Object):boolean");
    }

    java.lang.Object getTypedCameraParameter(int r10, int r11) {
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
        r9 = this;
        r10 = r9.getCameraCacheInfo(r10);
        r0 = 4;
        r1 = 0;
        if (r10 == 0) goto L_0x01d9;
    L_0x0008:
        r2 = r10.camera;
        if (r2 != 0) goto L_0x000e;
    L_0x000c:
        goto L_0x01d9;
    L_0x000e:
        r2 = r10.camera;
        r2 = r9.getCameraParameters(r2);
        r3 = 6;
        if (r2 != 0) goto L_0x001b;
    L_0x0017:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r3);
        return r1;
    L_0x001b:
        r4 = 3;
        r5 = 1;
        r6 = 2;
        r7 = 8;
        r8 = 0;
        switch(r11) {
            case 536870913: goto L_0x01a4;
            case 536870914: goto L_0x0144;
            case 536870916: goto L_0x0131;
            case 536870920: goto L_0x0115;
            case 536870928: goto L_0x00ce;
            case 536870944: goto L_0x00ca;
            case 536871936: goto L_0x00b0;
            case 536872960: goto L_0x0089;
            case 536875008: goto L_0x0085;
            case 536879104: goto L_0x0081;
            case 536887296: goto L_0x007d;
            case 536903680: goto L_0x0064;
            case 536936448: goto L_0x0049;
            case 537001984: goto L_0x0045;
            case 537133056: goto L_0x0041;
            case 537395200: goto L_0x003d;
            case 537919488: goto L_0x0039;
            case 538968064: goto L_0x0035;
            case 553648128: goto L_0x0025;
            default: goto L_0x0024;
        };
    L_0x0024:
        return r1;
    L_0x0025:
        r10 = r2.getVideoStabilization();	 Catch:{ Exception -> 0x01d5 }
        if (r10 == 0) goto L_0x0030;	 Catch:{ Exception -> 0x01d5 }
    L_0x002b:
        r10 = java.lang.Boolean.valueOf(r5);	 Catch:{ Exception -> 0x01d5 }
        return r10;	 Catch:{ Exception -> 0x01d5 }
    L_0x0030:
        r10 = java.lang.Boolean.valueOf(r8);	 Catch:{ Exception -> 0x01d5 }
        return r10;	 Catch:{ Exception -> 0x01d5 }
    L_0x0035:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r3);	 Catch:{ Exception -> 0x01d5 }
        return r1;	 Catch:{ Exception -> 0x01d5 }
    L_0x0039:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r3);	 Catch:{ Exception -> 0x01d5 }
        return r1;	 Catch:{ Exception -> 0x01d5 }
    L_0x003d:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r3);	 Catch:{ Exception -> 0x01d5 }
        return r1;	 Catch:{ Exception -> 0x01d5 }
    L_0x0041:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r3);	 Catch:{ Exception -> 0x01d5 }
        return r1;	 Catch:{ Exception -> 0x01d5 }
    L_0x0045:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r3);	 Catch:{ Exception -> 0x01d5 }
        return r1;	 Catch:{ Exception -> 0x01d5 }
    L_0x0049:
        r10 = com.vuforia.ar.pl.SystemTools.checkMinimumApiLevel(r7);	 Catch:{ Exception -> 0x01d5 }
        if (r10 == 0) goto L_0x0060;	 Catch:{ Exception -> 0x01d5 }
    L_0x004f:
        r10 = r2.isZoomSupported();	 Catch:{ Exception -> 0x01d5 }
        if (r10 == 0) goto L_0x0060;	 Catch:{ Exception -> 0x01d5 }
    L_0x0055:
        r10 = new int[r6];	 Catch:{ Exception -> 0x01d5 }
        r10[r8] = r8;	 Catch:{ Exception -> 0x01d5 }
        r11 = r2.getMaxZoom();	 Catch:{ Exception -> 0x01d5 }
        r10[r5] = r11;	 Catch:{ Exception -> 0x01d5 }
        return r10;	 Catch:{ Exception -> 0x01d5 }
    L_0x0060:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r3);	 Catch:{ Exception -> 0x01d5 }
        return r1;	 Catch:{ Exception -> 0x01d5 }
    L_0x0064:
        r10 = com.vuforia.ar.pl.SystemTools.checkMinimumApiLevel(r7);	 Catch:{ Exception -> 0x01d5 }
        if (r10 == 0) goto L_0x0079;	 Catch:{ Exception -> 0x01d5 }
    L_0x006a:
        r10 = r2.isZoomSupported();	 Catch:{ Exception -> 0x01d5 }
        if (r10 == 0) goto L_0x0079;	 Catch:{ Exception -> 0x01d5 }
    L_0x0070:
        r10 = r2.getZoom();	 Catch:{ Exception -> 0x01d5 }
        r10 = java.lang.Integer.valueOf(r10);	 Catch:{ Exception -> 0x01d5 }
        return r10;	 Catch:{ Exception -> 0x01d5 }
    L_0x0079:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r3);	 Catch:{ Exception -> 0x01d5 }
        return r1;	 Catch:{ Exception -> 0x01d5 }
    L_0x007d:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r3);	 Catch:{ Exception -> 0x01d5 }
        return r1;	 Catch:{ Exception -> 0x01d5 }
    L_0x0081:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r3);	 Catch:{ Exception -> 0x01d5 }
        return r1;	 Catch:{ Exception -> 0x01d5 }
    L_0x0085:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r3);	 Catch:{ Exception -> 0x01d5 }
        return r1;	 Catch:{ Exception -> 0x01d5 }
    L_0x0089:
        r10 = com.vuforia.ar.pl.SystemTools.checkMinimumApiLevel(r7);	 Catch:{ Exception -> 0x01d5 }
        if (r10 == 0) goto L_0x00ac;	 Catch:{ Exception -> 0x01d5 }
    L_0x008f:
        r10 = new float[r6];	 Catch:{ Exception -> 0x01d5 }
        r11 = r2.getExposureCompensationStep();	 Catch:{ Exception -> 0x01d5 }
        r0 = r2.getMinExposureCompensation();	 Catch:{ Exception -> 0x01d5 }
        r0 = (float) r0;	 Catch:{ Exception -> 0x01d5 }
        r11 = r11 * r0;	 Catch:{ Exception -> 0x01d5 }
        r10[r8] = r11;	 Catch:{ Exception -> 0x01d5 }
        r11 = r2.getExposureCompensationStep();	 Catch:{ Exception -> 0x01d5 }
        r0 = r2.getMaxExposureCompensation();	 Catch:{ Exception -> 0x01d5 }
        r0 = (float) r0;	 Catch:{ Exception -> 0x01d5 }
        r11 = r11 * r0;	 Catch:{ Exception -> 0x01d5 }
        r10[r5] = r11;	 Catch:{ Exception -> 0x01d5 }
        return r10;	 Catch:{ Exception -> 0x01d5 }
    L_0x00ac:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r3);	 Catch:{ Exception -> 0x01d5 }
        return r1;	 Catch:{ Exception -> 0x01d5 }
    L_0x00b0:
        r10 = com.vuforia.ar.pl.SystemTools.checkMinimumApiLevel(r7);	 Catch:{ Exception -> 0x01d5 }
        if (r10 == 0) goto L_0x00c6;	 Catch:{ Exception -> 0x01d5 }
    L_0x00b6:
        r10 = r2.getExposureCompensationStep();	 Catch:{ Exception -> 0x01d5 }
        r11 = r2.getExposureCompensation();	 Catch:{ Exception -> 0x01d5 }
        r11 = (float) r11;	 Catch:{ Exception -> 0x01d5 }
        r10 = r10 * r11;	 Catch:{ Exception -> 0x01d5 }
        r10 = java.lang.Float.valueOf(r10);	 Catch:{ Exception -> 0x01d5 }
        return r10;	 Catch:{ Exception -> 0x01d5 }
    L_0x00c6:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r3);	 Catch:{ Exception -> 0x01d5 }
        return r1;	 Catch:{ Exception -> 0x01d5 }
    L_0x00ca:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r3);	 Catch:{ Exception -> 0x01d5 }
        return r1;	 Catch:{ Exception -> 0x01d5 }
    L_0x00ce:
        r10 = 14;	 Catch:{ Exception -> 0x01d5 }
        r10 = com.vuforia.ar.pl.SystemTools.checkMinimumApiLevel(r10);	 Catch:{ Exception -> 0x01d5 }
        if (r10 == 0) goto L_0x0111;	 Catch:{ Exception -> 0x01d5 }
    L_0x00d6:
        r10 = r2.getMaxNumFocusAreas();	 Catch:{ Exception -> 0x01d5 }
        if (r10 <= 0) goto L_0x0111;	 Catch:{ Exception -> 0x01d5 }
    L_0x00dc:
        r10 = r2.getFocusAreas();	 Catch:{ Exception -> 0x01d5 }
        r11 = r10.size();	 Catch:{ Exception -> 0x01d5 }
        if (r11 <= 0) goto L_0x0111;	 Catch:{ Exception -> 0x01d5 }
    L_0x00e6:
        r10 = r10.get(r8);	 Catch:{ Exception -> 0x01d5 }
        r10 = (android.hardware.Camera.Area) r10;	 Catch:{ Exception -> 0x01d5 }
        r11 = 5;	 Catch:{ Exception -> 0x01d5 }
        r11 = new float[r11];	 Catch:{ Exception -> 0x01d5 }
        r2 = r10.rect;	 Catch:{ Exception -> 0x01d5 }
        r2 = r2.left;	 Catch:{ Exception -> 0x01d5 }
        r2 = (float) r2;	 Catch:{ Exception -> 0x01d5 }
        r11[r8] = r2;	 Catch:{ Exception -> 0x01d5 }
        r2 = r10.rect;	 Catch:{ Exception -> 0x01d5 }
        r2 = r2.top;	 Catch:{ Exception -> 0x01d5 }
        r2 = (float) r2;	 Catch:{ Exception -> 0x01d5 }
        r11[r5] = r2;	 Catch:{ Exception -> 0x01d5 }
        r2 = r10.rect;	 Catch:{ Exception -> 0x01d5 }
        r2 = r2.right;	 Catch:{ Exception -> 0x01d5 }
        r2 = (float) r2;	 Catch:{ Exception -> 0x01d5 }
        r11[r6] = r2;	 Catch:{ Exception -> 0x01d5 }
        r2 = r10.rect;	 Catch:{ Exception -> 0x01d5 }
        r2 = r2.bottom;	 Catch:{ Exception -> 0x01d5 }
        r2 = (float) r2;	 Catch:{ Exception -> 0x01d5 }
        r11[r4] = r2;	 Catch:{ Exception -> 0x01d5 }
        r10 = r10.weight;	 Catch:{ Exception -> 0x01d5 }
        r10 = (float) r10;	 Catch:{ Exception -> 0x01d5 }
        r11[r0] = r10;	 Catch:{ Exception -> 0x01d5 }
        return r11;	 Catch:{ Exception -> 0x01d5 }
    L_0x0111:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r3);	 Catch:{ Exception -> 0x01d5 }
        return r1;	 Catch:{ Exception -> 0x01d5 }
    L_0x0115:
        r10 = 9;	 Catch:{ Exception -> 0x01d5 }
        r10 = com.vuforia.ar.pl.SystemTools.checkMinimumApiLevel(r10);	 Catch:{ Exception -> 0x01d5 }
        if (r10 == 0) goto L_0x012d;	 Catch:{ Exception -> 0x01d5 }
    L_0x011d:
        r10 = new float[r4];	 Catch:{ Exception -> 0x01d5 }
        r2.getFocusDistances(r10);	 Catch:{ Exception -> 0x01d5 }
        r11 = new float[r6];	 Catch:{ Exception -> 0x01d5 }
        r0 = r10[r8];	 Catch:{ Exception -> 0x01d5 }
        r11[r8] = r0;	 Catch:{ Exception -> 0x01d5 }
        r10 = r10[r6];	 Catch:{ Exception -> 0x01d5 }
        r11[r5] = r10;	 Catch:{ Exception -> 0x01d5 }
        return r11;	 Catch:{ Exception -> 0x01d5 }
    L_0x012d:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r3);	 Catch:{ Exception -> 0x01d5 }
        return r1;	 Catch:{ Exception -> 0x01d5 }
    L_0x0131:
        r10 = com.vuforia.ar.pl.SystemTools.checkMinimumApiLevel(r7);	 Catch:{ Exception -> 0x01d5 }
        if (r10 == 0) goto L_0x0140;	 Catch:{ Exception -> 0x01d5 }
    L_0x0137:
        r10 = r2.getFocalLength();	 Catch:{ Exception -> 0x01d5 }
        r10 = java.lang.Float.valueOf(r10);	 Catch:{ Exception -> 0x01d5 }
        return r10;	 Catch:{ Exception -> 0x01d5 }
    L_0x0140:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r3);	 Catch:{ Exception -> 0x01d5 }
        return r1;	 Catch:{ Exception -> 0x01d5 }
    L_0x0144:
        r11 = r2.getFocusMode();	 Catch:{ Exception -> 0x01d5 }
        r0 = "auto";	 Catch:{ Exception -> 0x01d5 }
        r0 = r11.equals(r0);	 Catch:{ Exception -> 0x01d5 }
        if (r0 == 0) goto L_0x0160;	 Catch:{ Exception -> 0x01d5 }
    L_0x0150:
        r10 = r10.isAutoFocusing;	 Catch:{ Exception -> 0x01d5 }
        if (r10 == 0) goto L_0x0158;	 Catch:{ Exception -> 0x01d5 }
    L_0x0154:
        r10 = 805306400; // 0x30000020 float:4.6566306E-10 double:3.978742266E-315;	 Catch:{ Exception -> 0x01d5 }
        goto L_0x015b;	 Catch:{ Exception -> 0x01d5 }
    L_0x0158:
        r10 = 805306384; // 0x30000010 float:4.656622E-10 double:3.978742187E-315;	 Catch:{ Exception -> 0x01d5 }
    L_0x015b:
        r10 = java.lang.Integer.valueOf(r10);	 Catch:{ Exception -> 0x01d5 }
        return r10;	 Catch:{ Exception -> 0x01d5 }
    L_0x0160:
        r10 = "continuous-video";	 Catch:{ Exception -> 0x01d5 }
        r10 = r11.equals(r10);	 Catch:{ Exception -> 0x01d5 }
        if (r10 == 0) goto L_0x0170;	 Catch:{ Exception -> 0x01d5 }
    L_0x0168:
        r10 = 805306432; // 0x30000040 float:4.6566484E-10 double:3.978742424E-315;	 Catch:{ Exception -> 0x01d5 }
        r10 = java.lang.Integer.valueOf(r10);	 Catch:{ Exception -> 0x01d5 }
        return r10;	 Catch:{ Exception -> 0x01d5 }
    L_0x0170:
        r10 = "infinity";	 Catch:{ Exception -> 0x01d5 }
        r10 = r11.equals(r10);	 Catch:{ Exception -> 0x01d5 }
        if (r10 == 0) goto L_0x0180;	 Catch:{ Exception -> 0x01d5 }
    L_0x0178:
        r10 = 805306624; // 0x30000100 float:4.656755E-10 double:3.978743373E-315;	 Catch:{ Exception -> 0x01d5 }
        r10 = java.lang.Integer.valueOf(r10);	 Catch:{ Exception -> 0x01d5 }
        return r10;	 Catch:{ Exception -> 0x01d5 }
    L_0x0180:
        r10 = "macro";	 Catch:{ Exception -> 0x01d5 }
        r10 = r11.equals(r10);	 Catch:{ Exception -> 0x01d5 }
        if (r10 == 0) goto L_0x0190;	 Catch:{ Exception -> 0x01d5 }
    L_0x0188:
        r10 = 805306496; // 0x30000080 float:4.656684E-10 double:3.97874274E-315;	 Catch:{ Exception -> 0x01d5 }
        r10 = java.lang.Integer.valueOf(r10);	 Catch:{ Exception -> 0x01d5 }
        return r10;	 Catch:{ Exception -> 0x01d5 }
    L_0x0190:
        r10 = "fixed";	 Catch:{ Exception -> 0x01d5 }
        r10 = r11.equals(r10);	 Catch:{ Exception -> 0x01d5 }
        if (r10 == 0) goto L_0x01a0;	 Catch:{ Exception -> 0x01d5 }
    L_0x0198:
        r10 = 805306880; // 0x30000200 float:4.656897E-10 double:3.97874464E-315;	 Catch:{ Exception -> 0x01d5 }
        r10 = java.lang.Integer.valueOf(r10);	 Catch:{ Exception -> 0x01d5 }
        return r10;	 Catch:{ Exception -> 0x01d5 }
    L_0x01a0:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r3);	 Catch:{ Exception -> 0x01d5 }
        return r1;	 Catch:{ Exception -> 0x01d5 }
    L_0x01a4:
        r10 = r2.getFlashMode();	 Catch:{ Exception -> 0x01d5 }
        r11 = "torch";	 Catch:{ Exception -> 0x01d5 }
        r11 = r10.equals(r11);	 Catch:{ Exception -> 0x01d5 }
        if (r11 != 0) goto L_0x01cd;	 Catch:{ Exception -> 0x01d5 }
    L_0x01b0:
        r11 = "on";	 Catch:{ Exception -> 0x01d5 }
        r11 = r10.equals(r11);	 Catch:{ Exception -> 0x01d5 }
        if (r11 == 0) goto L_0x01b9;	 Catch:{ Exception -> 0x01d5 }
    L_0x01b8:
        goto L_0x01cd;	 Catch:{ Exception -> 0x01d5 }
    L_0x01b9:
        r11 = "off";	 Catch:{ Exception -> 0x01d5 }
        r10 = r10.equals(r11);	 Catch:{ Exception -> 0x01d5 }
        if (r10 == 0) goto L_0x01c9;	 Catch:{ Exception -> 0x01d5 }
    L_0x01c1:
        r10 = 805306369; // 0x30000001 float:4.6566134E-10 double:3.978742113E-315;	 Catch:{ Exception -> 0x01d5 }
        r10 = java.lang.Integer.valueOf(r10);	 Catch:{ Exception -> 0x01d5 }
        return r10;	 Catch:{ Exception -> 0x01d5 }
    L_0x01c9:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r3);	 Catch:{ Exception -> 0x01d5 }
        return r1;	 Catch:{ Exception -> 0x01d5 }
    L_0x01cd:
        r10 = 805306370; // 0x30000002 float:4.656614E-10 double:3.97874212E-315;	 Catch:{ Exception -> 0x01d5 }
        r10 = java.lang.Integer.valueOf(r10);	 Catch:{ Exception -> 0x01d5 }
        return r10;
    L_0x01d5:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r3);
        return r1;
    L_0x01d9:
        com.vuforia.ar.pl.SystemTools.setSystemErrorCode(r0);
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.Camera1_Preview.getTypedCameraParameter(int, int):java.lang.Object");
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
