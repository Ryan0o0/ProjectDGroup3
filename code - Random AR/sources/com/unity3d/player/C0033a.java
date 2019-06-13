package com.unity3d.player;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.graphics.SurfaceTexture.OnFrameAvailableListener;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCaptureSession.CaptureCallback;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraDevice.StateCallback;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureRequest.Builder;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.MeteringRectangle;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.Image.Plane;
import android.media.ImageReader;
import android.media.ImageReader.OnImageAvailableListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Range;
import android.util.Size;
import android.view.Surface;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/* renamed from: com.unity3d.player.a */
public final class C0033a {
    /* renamed from: b */
    private static CameraManager f128b;
    /* renamed from: c */
    private static String[] f129c;
    /* renamed from: e */
    private static Semaphore f130e = new Semaphore(1);
    /* renamed from: A */
    private CaptureCallback f131A = new C00271(this);
    /* renamed from: B */
    private final StateCallback f132B = new C00293(this);
    /* renamed from: C */
    private final OnImageAvailableListener f133C = new C00304(this);
    /* renamed from: D */
    private final OnFrameAvailableListener f134D = new C00315(this);
    /* renamed from: a */
    private C0039d f135a = null;
    /* renamed from: d */
    private CameraDevice f136d;
    /* renamed from: f */
    private HandlerThread f137f;
    /* renamed from: g */
    private Handler f138g;
    /* renamed from: h */
    private Rect f139h;
    /* renamed from: i */
    private Rect f140i;
    /* renamed from: j */
    private int f141j;
    /* renamed from: k */
    private int f142k;
    /* renamed from: l */
    private float f143l = -1.0f;
    /* renamed from: m */
    private float f144m = -1.0f;
    /* renamed from: n */
    private int f145n;
    /* renamed from: o */
    private int f146o;
    /* renamed from: p */
    private boolean f147p = false;
    /* renamed from: q */
    private Range f148q;
    /* renamed from: r */
    private ImageReader f149r = null;
    /* renamed from: s */
    private Image f150s;
    /* renamed from: t */
    private Builder f151t;
    /* renamed from: u */
    private CameraCaptureSession f152u = null;
    /* renamed from: v */
    private Object f153v = new Object();
    /* renamed from: w */
    private int f154w;
    /* renamed from: x */
    private SurfaceTexture f155x;
    /* renamed from: y */
    private Surface f156y = null;
    /* renamed from: z */
    private int f157z = C0032a.f126c;

    /* renamed from: com.unity3d.player.a$1 */
    class C00271 extends CaptureCallback {
        /* renamed from: a */
        final /* synthetic */ C0033a f119a;

        C00271(C0033a c0033a) {
            this.f119a = c0033a;
        }

        public final void onCaptureCompleted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, TotalCaptureResult totalCaptureResult) {
            this.f119a.m46a(captureRequest.getTag());
        }

        public final void onCaptureFailed(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, CaptureFailure captureFailure) {
            StringBuilder stringBuilder = new StringBuilder("Camera2: Capture session failed ");
            stringBuilder.append(captureRequest.getTag());
            stringBuilder.append(" reason ");
            stringBuilder.append(captureFailure.getReason());
            C0042g.Log(5, stringBuilder.toString());
            this.f119a.m46a(captureRequest.getTag());
        }

        public final void onCaptureSequenceAborted(CameraCaptureSession cameraCaptureSession, int i) {
        }

        public final void onCaptureSequenceCompleted(CameraCaptureSession cameraCaptureSession, int i, long j) {
        }
    }

    /* renamed from: com.unity3d.player.a$2 */
    class C00282 extends CameraCaptureSession.StateCallback {
        /* renamed from: a */
        final /* synthetic */ C0033a f120a;

        C00282(C0033a c0033a) {
            this.f120a = c0033a;
        }

        public final void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
            C0042g.Log(6, "Camera2: CaptureSession configuration failed.");
        }

        public final void onConfigured(CameraCaptureSession cameraCaptureSession) {
            if (this.f120a.f136d != null) {
                synchronized (this.f120a.f153v) {
                    this.f120a.f152u = cameraCaptureSession;
                    try {
                        this.f120a.f151t = this.f120a.f136d.createCaptureRequest(1);
                        if (this.f120a.f156y != null) {
                            this.f120a.f151t.addTarget(this.f120a.f156y);
                        }
                        this.f120a.f151t.addTarget(this.f120a.f149r.getSurface());
                        this.f120a.f151t.set(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE, this.f120a.f148q);
                        this.f120a.m67j();
                    } catch (CameraAccessException e) {
                        StringBuilder stringBuilder = new StringBuilder("Camera2: CameraAccessException ");
                        stringBuilder.append(e);
                        C0042g.Log(6, stringBuilder.toString());
                    }
                }
            }
        }
    }

    /* renamed from: com.unity3d.player.a$3 */
    class C00293 extends StateCallback {
        /* renamed from: a */
        final /* synthetic */ C0033a f121a;

        C00293(C0033a c0033a) {
            this.f121a = c0033a;
        }

        public final void onClosed(CameraDevice cameraDevice) {
            C0033a.f130e.release();
        }

        public final void onDisconnected(CameraDevice cameraDevice) {
            C0042g.Log(5, "Camera2: CameraDevice disconnected.");
            this.f121a.m44a(cameraDevice);
            C0033a.f130e.release();
        }

        public final void onError(CameraDevice cameraDevice, int i) {
            StringBuilder stringBuilder = new StringBuilder("Camera2: Error opeining CameraDevice ");
            stringBuilder.append(i);
            C0042g.Log(6, stringBuilder.toString());
            this.f121a.m44a(cameraDevice);
            C0033a.f130e.release();
        }

        public final void onOpened(CameraDevice cameraDevice) {
            this.f121a.f136d = cameraDevice;
            C0033a.f130e.release();
        }
    }

    /* renamed from: com.unity3d.player.a$4 */
    class C00304 implements OnImageAvailableListener {
        /* renamed from: a */
        final /* synthetic */ C0033a f122a;

        C00304(C0033a c0033a) {
            this.f122a = c0033a;
        }

        public final void onImageAvailable(ImageReader imageReader) {
            if (C0033a.f130e.tryAcquire()) {
                Image acquireNextImage = imageReader.acquireNextImage();
                if (acquireNextImage != null) {
                    Plane[] planes = acquireNextImage.getPlanes();
                    if (acquireNextImage.getFormat() == 35 && planes != null && planes.length == 3) {
                        C0039d h = this.f122a.f135a;
                        ByteBuffer buffer = planes[0].getBuffer();
                        ByteBuffer buffer2 = planes[1].getBuffer();
                        ByteBuffer buffer3 = planes[2].getBuffer();
                        h.mo2a(buffer, buffer2, buffer3, planes[0].getRowStride(), planes[1].getRowStride(), planes[1].getPixelStride());
                    } else {
                        C0042g.Log(6, "Camera2: Wrong image format.");
                    }
                    if (this.f122a.f150s != null) {
                        this.f122a.f150s.close();
                    }
                    this.f122a.f150s = acquireNextImage;
                }
                C0033a.f130e.release();
            }
        }
    }

    /* renamed from: com.unity3d.player.a$5 */
    class C00315 implements OnFrameAvailableListener {
        /* renamed from: a */
        final /* synthetic */ C0033a f123a;

        C00315(C0033a c0033a) {
            this.f123a = c0033a;
        }

        public final void onFrameAvailable(SurfaceTexture surfaceTexture) {
            this.f123a.f135a.mo1a(surfaceTexture);
        }
    }

    /* renamed from: com.unity3d.player.a$a */
    private enum C0032a {
        ;

        static {
            f127d = new int[]{f124a, f125b, f126c};
        }
    }

    protected C0033a(C0039d c0039d) {
        this.f135a = c0039d;
        m61g();
    }

    /* renamed from: a */
    public static int m35a(Context context) {
        return C0033a.m55c(context).length;
    }

    /* renamed from: a */
    public static int m36a(Context context, int i) {
        try {
            return ((Integer) C0033a.m48b(context).getCameraCharacteristics(C0033a.m55c(context)[i]).get(CameraCharacteristics.SENSOR_ORIENTATION)).intValue();
        } catch (CameraAccessException e) {
            StringBuilder stringBuilder = new StringBuilder("Camera2: CameraAccessException ");
            stringBuilder.append(e);
            C0042g.Log(6, stringBuilder.toString());
            return 0;
        }
    }

    /* renamed from: a */
    private static int m37a(Range[] rangeArr, int i) {
        int i2 = -1;
        double d = Double.MAX_VALUE;
        for (int i3 = 0; i3 < rangeArr.length; i3++) {
            int intValue = ((Integer) rangeArr[i3].getLower()).intValue();
            int intValue2 = ((Integer) rangeArr[i3].getUpper()).intValue();
            float f = (float) i;
            if (f + 0.1f > ((float) intValue) && f - 0.1f < ((float) intValue2)) {
                return i;
            }
            double min = (double) ((float) Math.min(Math.abs(i - intValue), Math.abs(i - intValue2)));
            if (min < d) {
                i2 = i3;
                d = min;
            }
        }
        return ((Integer) (i > ((Integer) rangeArr[i2].getUpper()).intValue() ? rangeArr[i2].getUpper() : rangeArr[i2].getLower())).intValue();
    }

    /* renamed from: a */
    private static Rect m38a(Size[] sizeArr, double d, double d2) {
        Size[] sizeArr2 = sizeArr;
        double d3 = Double.MAX_VALUE;
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < sizeArr2.length; i3++) {
            int width = sizeArr2[i3].getWidth();
            int height = sizeArr2[i3].getHeight();
            double d4 = (double) width;
            Double.isNaN(d4);
            d4 = Math.abs(Math.log(d / d4));
            double d5 = (double) height;
            Double.isNaN(d5);
            d4 += Math.abs(Math.log(d2 / d5));
            if (d4 < d3) {
                i = width;
                i2 = height;
                d3 = d4;
            }
        }
        return new Rect(0, 0, i, i2);
    }

    /* renamed from: a */
    private void m44a(CameraDevice cameraDevice) {
        synchronized (this.f153v) {
            this.f152u = null;
        }
        cameraDevice.close();
        this.f136d = null;
    }

    /* renamed from: a */
    private void m46a(Object obj) {
        if (obj == "Focus") {
            this.f147p = false;
            synchronized (this.f153v) {
                if (this.f152u != null) {
                    try {
                        this.f151t.set(CaptureRequest.CONTROL_AF_TRIGGER, Integer.valueOf(0));
                        this.f151t.setTag("Regular");
                        this.f152u.setRepeatingRequest(this.f151t.build(), this.f131A, this.f138g);
                    } catch (CameraAccessException e) {
                        StringBuilder stringBuilder = new StringBuilder("Camera2: CameraAccessException ");
                        stringBuilder.append(e);
                        C0042g.Log(6, stringBuilder.toString());
                    }
                }
            }
        } else if (obj == "Cancel focus") {
            synchronized (this.f153v) {
                if (this.f152u != null) {
                    m67j();
                }
            }
        }
    }

    /* renamed from: a */
    private static Size[] m47a(CameraCharacteristics cameraCharacteristics) {
        String str;
        StreamConfigurationMap streamConfigurationMap = (StreamConfigurationMap) cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        if (streamConfigurationMap == null) {
            str = "Camera2: configuration map is not available.";
        } else {
            Size[] outputSizes = streamConfigurationMap.getOutputSizes(35);
            if (outputSizes != null) {
                if (outputSizes.length != 0) {
                    return outputSizes;
                }
            }
            str = "Camera2: output sizes for YUV_420_888 format are not avialable.";
        }
        C0042g.Log(6, str);
        return null;
    }

    /* renamed from: b */
    private static CameraManager m48b(Context context) {
        if (f128b == null) {
            f128b = (CameraManager) context.getSystemService("camera");
        }
        return f128b;
    }

    /* renamed from: b */
    private void m50b(CameraCharacteristics cameraCharacteristics) {
        this.f142k = ((Integer) cameraCharacteristics.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AF)).intValue();
        if (this.f142k > 0) {
            this.f140i = (Rect) cameraCharacteristics.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE);
            float width = ((float) this.f139h.width()) / ((float) this.f139h.height());
            if (width > ((float) this.f140i.width()) / ((float) this.f140i.height())) {
                this.f145n = 0;
                this.f146o = (int) ((((float) this.f140i.height()) - (((float) this.f140i.width()) / width)) / 2.0f);
            } else {
                this.f146o = 0;
                this.f145n = (int) ((((float) this.f140i.width()) - (((float) this.f140i.height()) * width)) / 2.0f);
            }
            this.f141j = Math.min(this.f140i.width(), this.f140i.height()) / 20;
        }
    }

    /* renamed from: b */
    public static boolean m52b(Context context, int i) {
        try {
            return ((Integer) C0033a.m48b(context).getCameraCharacteristics(C0033a.m55c(context)[i]).get(CameraCharacteristics.LENS_FACING)).intValue() == 0;
        } catch (CameraAccessException e) {
            StringBuilder stringBuilder = new StringBuilder("Camera2: CameraAccessException ");
            stringBuilder.append(e);
            C0042g.Log(6, stringBuilder.toString());
            return false;
        }
    }

    /* renamed from: c */
    public static boolean m54c(Context context, int i) {
        try {
            return ((Integer) C0033a.m48b(context).getCameraCharacteristics(C0033a.m55c(context)[i]).get(CameraCharacteristics.CONTROL_MAX_REGIONS_AF)).intValue() > 0;
        } catch (CameraAccessException e) {
            StringBuilder stringBuilder = new StringBuilder("Camera2: CameraAccessException ");
            stringBuilder.append(e);
            C0042g.Log(6, stringBuilder.toString());
            return false;
        }
    }

    /* renamed from: c */
    private static String[] m55c(Context context) {
        if (f129c == null) {
            try {
                f129c = C0033a.m48b(context).getCameraIdList();
            } catch (CameraAccessException e) {
                StringBuilder stringBuilder = new StringBuilder("Camera2: CameraAccessException ");
                stringBuilder.append(e);
                C0042g.Log(6, stringBuilder.toString());
                f129c = new String[0];
            }
        }
        return f129c;
    }

    /* renamed from: d */
    public static int[] m57d(Context context, int i) {
        try {
            Size[] a = C0033a.m47a(C0033a.m48b(context).getCameraCharacteristics(C0033a.m55c(context)[i]));
            if (a == null) {
                return null;
            }
            int[] iArr = new int[(a.length * 2)];
            for (int i2 = 0; i2 < a.length; i2++) {
                int i3 = i2 * 2;
                iArr[i3] = a[i2].getWidth();
                iArr[i3 + 1] = a[i2].getHeight();
            }
            return iArr;
        } catch (CameraAccessException e) {
            StringBuilder stringBuilder = new StringBuilder("Camera2: CameraAccessException ");
            stringBuilder.append(e);
            C0042g.Log(6, stringBuilder.toString());
            return null;
        }
    }

    /* renamed from: g */
    private void m61g() {
        this.f137f = new HandlerThread("CameraBackground");
        this.f137f.start();
        this.f138g = new Handler(this.f137f.getLooper());
    }

    /* renamed from: h */
    private void m64h() {
        this.f137f.quit();
        try {
            this.f137f.join(4000);
            this.f137f = null;
            this.f138g = null;
        } catch (InterruptedException e) {
            this.f137f.interrupt();
            StringBuilder stringBuilder = new StringBuilder("Camera2: Interrupted while waiting for the background thread to finish ");
            stringBuilder.append(e);
            C0042g.Log(6, stringBuilder.toString());
        }
    }

    /* renamed from: i */
    private void m66i() {
        StringBuilder stringBuilder;
        try {
            if (f130e.tryAcquire(4, TimeUnit.SECONDS)) {
                this.f136d.close();
                try {
                    if (!f130e.tryAcquire(4, TimeUnit.SECONDS)) {
                        C0042g.Log(5, "Camera2: Timeout waiting to close camera.");
                    }
                } catch (InterruptedException e) {
                    stringBuilder = new StringBuilder("Camera2: Interrupted while waiting to close camera ");
                    stringBuilder.append(e);
                    C0042g.Log(6, stringBuilder.toString());
                }
                this.f136d = null;
                f130e.release();
                return;
            }
            C0042g.Log(5, "Camera2: Timeout waiting to lock camera for closing.");
        } catch (InterruptedException e2) {
            stringBuilder = new StringBuilder("Camera2: Interrupted while trying to lock camera for closing ");
            stringBuilder.append(e2);
            C0042g.Log(6, stringBuilder.toString());
        }
    }

    /* renamed from: j */
    private void m67j() {
        try {
            if (this.f142k != 0 && this.f143l >= 0.0f && this.f143l <= 1.0f && this.f144m >= 0.0f) {
                if (this.f144m <= 1.0f) {
                    this.f147p = true;
                    int width = (int) ((((float) (this.f140i.width() - (this.f145n * 2))) * this.f143l) + ((float) this.f145n));
                    double height = (double) (this.f140i.height() - (this.f146o * 2));
                    double d = (double) this.f144m;
                    Double.isNaN(d);
                    double d2 = 1.0d - d;
                    Double.isNaN(height);
                    height *= d2;
                    d2 = (double) this.f146o;
                    Double.isNaN(d2);
                    int i = (int) (height + d2);
                    width = Math.max(this.f141j + 1, Math.min(width, (this.f140i.width() - this.f141j) - 1));
                    i = Math.max(this.f141j + 1, Math.min(i, (this.f140i.height() - this.f141j) - 1));
                    this.f151t.set(CaptureRequest.CONTROL_AF_REGIONS, new MeteringRectangle[]{new MeteringRectangle(width - this.f141j, i - this.f141j, this.f141j * 2, this.f141j * 2, 999)});
                    this.f151t.set(CaptureRequest.CONTROL_AF_MODE, Integer.valueOf(1));
                    this.f151t.set(CaptureRequest.CONTROL_AF_TRIGGER, Integer.valueOf(1));
                    this.f151t.setTag("Focus");
                    this.f152u.capture(this.f151t.build(), this.f131A, this.f138g);
                }
            }
            this.f151t.set(CaptureRequest.CONTROL_AF_MODE, Integer.valueOf(4));
            this.f151t.setTag("Regular");
            if (this.f152u != null) {
                this.f152u.setRepeatingRequest(this.f151t.build(), this.f131A, this.f138g);
            }
        } catch (CameraAccessException e) {
            StringBuilder stringBuilder = new StringBuilder("Camera2: CameraAccessException ");
            stringBuilder.append(e);
            C0042g.Log(6, stringBuilder.toString());
        }
    }

    /* renamed from: k */
    private void m68k() {
        try {
            if (this.f152u != null) {
                this.f152u.stopRepeating();
                this.f151t.set(CaptureRequest.CONTROL_AF_TRIGGER, Integer.valueOf(2));
                this.f151t.set(CaptureRequest.CONTROL_AF_MODE, Integer.valueOf(0));
                this.f151t.setTag("Cancel focus");
                this.f152u.capture(this.f151t.build(), this.f131A, this.f138g);
            }
        } catch (CameraAccessException e) {
            StringBuilder stringBuilder = new StringBuilder("Camera2: CameraAccessException ");
            stringBuilder.append(e);
            C0042g.Log(6, stringBuilder.toString());
        }
    }

    /* renamed from: a */
    public final Rect m69a() {
        return this.f139h;
    }

    /* renamed from: a */
    public final boolean m70a(float f, float f2) {
        if (this.f142k > 0) {
            if (this.f147p) {
                C0042g.Log(5, "Camera2: Setting manual focus point already started.");
            } else {
                this.f143l = f;
                this.f144m = f2;
                synchronized (this.f153v) {
                    if (!(this.f152u == null || this.f157z == C0032a.f125b)) {
                        m68k();
                    }
                }
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public final boolean m71a(Context context, int i, int i2, int i3, int i4, int i5) {
        StringBuilder stringBuilder;
        try {
            CameraCharacteristics cameraCharacteristics = f128b.getCameraCharacteristics(C0033a.m55c(context)[i]);
            if (((Integer) cameraCharacteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL)).intValue() == 2) {
                C0042g.Log(5, "Camera2: only LEGACY hardware level is supported.");
                return false;
            }
            Size[] a = C0033a.m47a(cameraCharacteristics);
            if (a != null) {
                if (a.length != 0) {
                    this.f139h = C0033a.m38a(a, (double) i2, (double) i3);
                    Range[] rangeArr = (Range[]) cameraCharacteristics.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES);
                    if (rangeArr != null) {
                        if (rangeArr.length != 0) {
                            i2 = C0033a.m37a(rangeArr, i4);
                            this.f148q = new Range(Integer.valueOf(i2), Integer.valueOf(i2));
                            try {
                                if (f130e.tryAcquire(4, TimeUnit.SECONDS)) {
                                    try {
                                        f128b.openCamera(C0033a.m55c(context)[i], this.f132B, this.f138g);
                                        try {
                                            if (f130e.tryAcquire(4, TimeUnit.SECONDS)) {
                                                f130e.release();
                                                this.f154w = i5;
                                                m50b(cameraCharacteristics);
                                                return this.f136d != null;
                                            } else {
                                                C0042g.Log(5, "Camera2: Timeout waiting to open camera.");
                                                return false;
                                            }
                                        } catch (InterruptedException e) {
                                            stringBuilder = new StringBuilder("Camera2: Interrupted while waiting to open camera ");
                                            stringBuilder.append(e);
                                            C0042g.Log(6, stringBuilder.toString());
                                        }
                                    } catch (CameraAccessException e2) {
                                        stringBuilder = new StringBuilder("Camera2: CameraAccessException ");
                                        stringBuilder.append(e2);
                                        C0042g.Log(6, stringBuilder.toString());
                                        f130e.release();
                                        return false;
                                    }
                                }
                                C0042g.Log(5, "Camera2: Timeout waiting to lock camera for opening.");
                                return false;
                            } catch (InterruptedException e3) {
                                stringBuilder = new StringBuilder("Camera2: Interrupted while trying to lock camera for opening ");
                                stringBuilder.append(e3);
                                C0042g.Log(6, stringBuilder.toString());
                                return false;
                            }
                        }
                    }
                    C0042g.Log(6, "Camera2: target FPS ranges are not avialable.");
                }
            }
            return false;
        } catch (CameraAccessException e22) {
            stringBuilder = new StringBuilder("Camera2: CameraAccessException ");
            stringBuilder.append(e22);
            C0042g.Log(6, stringBuilder.toString());
            return false;
        }
    }

    /* renamed from: b */
    public final void m72b() {
        if (this.f136d != null) {
            m75e();
            m66i();
            this.f131A = null;
            this.f156y = null;
            this.f155x = null;
            if (this.f150s != null) {
                this.f150s.close();
                this.f150s = null;
            }
            if (this.f149r != null) {
                this.f149r.close();
                this.f149r = null;
            }
        }
        m64h();
    }

    /* renamed from: c */
    public final void m73c() {
        if (this.f149r == null) {
            this.f149r = ImageReader.newInstance(this.f139h.width(), this.f139h.height(), 35, 2);
            this.f149r.setOnImageAvailableListener(this.f133C, this.f138g);
            this.f150s = null;
            if (this.f154w != 0) {
                this.f155x = new SurfaceTexture(this.f154w);
                this.f155x.setDefaultBufferSize(this.f139h.width(), this.f139h.height());
                this.f155x.setOnFrameAvailableListener(this.f134D, this.f138g);
                this.f156y = new Surface(this.f155x);
            }
        }
        try {
            if (this.f152u == null) {
                this.f136d.createCaptureSession(Arrays.asList(this.f156y != null ? new Surface[]{this.f156y, this.f149r.getSurface()} : new Surface[]{this.f149r.getSurface()}), new C00282(this), this.f138g);
            } else if (this.f157z == C0032a.f125b) {
                this.f152u.setRepeatingRequest(this.f151t.build(), this.f131A, this.f138g);
            }
            this.f157z = C0032a.f124a;
        } catch (CameraAccessException e) {
            StringBuilder stringBuilder = new StringBuilder("Camera2: CameraAccessException ");
            stringBuilder.append(e);
            C0042g.Log(6, stringBuilder.toString());
        }
    }

    /* renamed from: d */
    public final void m74d() {
        synchronized (this.f153v) {
            if (this.f152u != null) {
                try {
                    this.f152u.stopRepeating();
                    this.f157z = C0032a.f125b;
                } catch (CameraAccessException e) {
                    StringBuilder stringBuilder = new StringBuilder("Camera2: CameraAccessException ");
                    stringBuilder.append(e);
                    C0042g.Log(6, stringBuilder.toString());
                }
            }
        }
    }

    /* renamed from: e */
    public final void m75e() {
        synchronized (this.f153v) {
            if (this.f152u != null) {
                try {
                    this.f152u.abortCaptures();
                } catch (CameraAccessException e) {
                    StringBuilder stringBuilder = new StringBuilder("Camera2: CameraAccessException ");
                    stringBuilder.append(e);
                    C0042g.Log(6, stringBuilder.toString());
                }
                this.f152u.close();
                this.f152u = null;
                this.f157z = C0032a.f126c;
            }
        }
    }
}
