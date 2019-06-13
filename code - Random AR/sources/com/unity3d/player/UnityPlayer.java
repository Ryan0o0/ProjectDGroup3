package com.unity3d.player;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.TypedValue;
import android.view.InputEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import com.unity3d.player.C0051l.C0050a;
import com.unity3d.player.C0065q.C0064a;
import com.vuforia.PIXEL_FORMAT;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class UnityPlayer extends FrameLayout implements C0041f {
    public static Activity currentActivity;
    /* renamed from: t */
    private static boolean f300t;
    /* renamed from: a */
    C0024e f301a = new C0024e();
    /* renamed from: b */
    C0048k f302b = null;
    /* renamed from: c */
    private int f303c = -1;
    /* renamed from: d */
    private boolean f304d = false;
    /* renamed from: e */
    private boolean f305e = true;
    /* renamed from: f */
    private C0053n f306f = new C0053n();
    /* renamed from: g */
    private final ConcurrentLinkedQueue f307g = new ConcurrentLinkedQueue();
    /* renamed from: h */
    private BroadcastReceiver f308h = null;
    /* renamed from: i */
    private boolean f309i = false;
    /* renamed from: j */
    private C0021c f310j = new C0021c();
    /* renamed from: k */
    private TelephonyManager f311k;
    /* renamed from: l */
    private ClipboardManager f312l;
    /* renamed from: m */
    private C0051l f313m;
    /* renamed from: n */
    private GoogleARCoreApi f314n = null;
    /* renamed from: o */
    private C0019a f315o = new C0019a(this);
    /* renamed from: p */
    private Camera2Wrapper f316p = null;
    /* renamed from: q */
    private HFPStatus f317q = null;
    /* renamed from: r */
    private Context f318r;
    /* renamed from: s */
    private SurfaceView f319s;
    /* renamed from: u */
    private boolean f320u;
    /* renamed from: v */
    private boolean f321v = false;
    /* renamed from: w */
    private C0065q f322w;

    /* renamed from: com.unity3d.player.UnityPlayer$1 */
    class C00111 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ UnityPlayer f55a;

        C00111(UnityPlayer unityPlayer) {
            this.f55a = unityPlayer;
        }

        public final void onClick(DialogInterface dialogInterface, int i) {
            this.f55a.m187e();
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer$2 */
    class C00122 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ UnityPlayer f65a;

        C00122(UnityPlayer unityPlayer) {
            this.f65a = unityPlayer;
        }

        public final void run() {
            this.f65a.nativeLowMemory();
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer$3 */
    class C00133 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ UnityPlayer f66a;

        C00133(UnityPlayer unityPlayer) {
            this.f66a = unityPlayer;
        }

        public final void run() {
            this.f66a.nativeResume();
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer$5 */
    class C00155 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ UnityPlayer f78a;

        C00155(UnityPlayer unityPlayer) {
            this.f78a = unityPlayer;
        }

        public final void run() {
            if (this.f78a.f302b != null) {
                this.f78a.f302b.dismiss();
                this.f78a.f302b = null;
            }
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer$a */
    class C0019a implements SensorEventListener {
        /* renamed from: a */
        final /* synthetic */ UnityPlayer f85a;

        C0019a(UnityPlayer unityPlayer) {
            this.f85a = unityPlayer;
        }

        public final void onAccuracyChanged(Sensor sensor, int i) {
        }

        public final void onSensorChanged(SensorEvent sensorEvent) {
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer$b */
    enum C0020b {
        ;

        static {
            f89d = new int[]{f86a, f87b, f88c};
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer$c */
    private class C0021c extends PhoneStateListener {
        /* renamed from: a */
        final /* synthetic */ UnityPlayer f90a;

        private C0021c(UnityPlayer unityPlayer) {
            this.f90a = unityPlayer;
        }

        public final void onCallStateChanged(int i, String str) {
            UnityPlayer unityPlayer = this.f90a;
            boolean z = true;
            if (i != 1) {
                z = false;
            }
            unityPlayer.nativeMuteMasterAudio(z);
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer$d */
    enum C0022d {
        PAUSE,
        RESUME,
        QUIT,
        SURFACE_LOST,
        SURFACE_ACQUIRED,
        FOCUS_LOST,
        FOCUS_GAINED,
        NEXT_FRAME
    }

    /* renamed from: com.unity3d.player.UnityPlayer$e */
    private class C0024e extends Thread {
        /* renamed from: a */
        Handler f101a;
        /* renamed from: b */
        boolean f102b;
        /* renamed from: c */
        boolean f103c;
        /* renamed from: d */
        int f104d;
        /* renamed from: e */
        int f105e;
        /* renamed from: f */
        final /* synthetic */ UnityPlayer f106f;

        /* renamed from: com.unity3d.player.UnityPlayer$e$1 */
        class C00231 implements Callback {
            /* renamed from: a */
            final /* synthetic */ C0024e f100a;

            C00231(C0024e c0024e) {
                this.f100a = c0024e;
            }

            /* renamed from: a */
            private void m24a() {
                if (this.f100a.f104d == C0020b.f88c && this.f100a.f103c) {
                    this.f100a.f106f.nativeFocusChanged(true);
                    this.f100a.f104d = C0020b.f86a;
                }
            }

            public final boolean handleMessage(Message message) {
                if (message.what != 2269) {
                    return false;
                }
                C0022d c0022d = (C0022d) message.obj;
                if (c0022d == C0022d.NEXT_FRAME) {
                    this.f100a.f106f.executeGLThreadJobs();
                    if (!this.f100a.f102b || !this.f100a.f103c) {
                        return true;
                    }
                    if (this.f100a.f105e >= 0) {
                        if (this.f100a.f105e == 0 && this.f100a.f106f.m199k()) {
                            this.f100a.f106f.m165a();
                        }
                        C0024e c0024e = this.f100a;
                        c0024e.f105e--;
                    }
                    if (!(this.f100a.f106f.isFinishing() || this.f100a.f106f.nativeRender())) {
                        this.f100a.f106f.m187e();
                    }
                } else if (c0022d == C0022d.QUIT) {
                    Looper.myLooper().quit();
                } else if (c0022d == C0022d.RESUME) {
                    this.f100a.f102b = true;
                } else if (c0022d == C0022d.PAUSE) {
                    this.f100a.f102b = false;
                } else if (c0022d == C0022d.SURFACE_LOST) {
                    this.f100a.f103c = false;
                } else {
                    if (c0022d == C0022d.SURFACE_ACQUIRED) {
                        this.f100a.f103c = true;
                    } else if (c0022d == C0022d.FOCUS_LOST) {
                        if (this.f100a.f104d == C0020b.f86a) {
                            this.f100a.f106f.nativeFocusChanged(false);
                        }
                        this.f100a.f104d = C0020b.f87b;
                    } else if (c0022d == C0022d.FOCUS_GAINED) {
                        this.f100a.f104d = C0020b.f88c;
                    }
                    m24a();
                }
                if (this.f100a.f102b) {
                    Message.obtain(this.f100a.f101a, 2269, C0022d.NEXT_FRAME).sendToTarget();
                }
                return true;
            }
        }

        private C0024e(UnityPlayer unityPlayer) {
            this.f106f = unityPlayer;
            this.f102b = false;
            this.f103c = false;
            this.f104d = C0020b.f87b;
            this.f105e = 5;
        }

        /* renamed from: a */
        private void m25a(C0022d c0022d) {
            if (this.f101a != null) {
                Message.obtain(this.f101a, 2269, c0022d).sendToTarget();
            }
        }

        /* renamed from: a */
        public final void m26a() {
            m25a(C0022d.QUIT);
        }

        /* renamed from: a */
        public final void m27a(Runnable runnable) {
            if (this.f101a != null) {
                m25a(C0022d.PAUSE);
                Message.obtain(this.f101a, runnable).sendToTarget();
            }
        }

        /* renamed from: b */
        public final void m28b() {
            m25a(C0022d.RESUME);
        }

        /* renamed from: b */
        public final void m29b(Runnable runnable) {
            if (this.f101a != null) {
                m25a(C0022d.SURFACE_LOST);
                Message.obtain(this.f101a, runnable).sendToTarget();
            }
        }

        /* renamed from: c */
        public final void m30c() {
            m25a(C0022d.FOCUS_GAINED);
        }

        /* renamed from: c */
        public final void m31c(Runnable runnable) {
            if (this.f101a != null) {
                Message.obtain(this.f101a, runnable).sendToTarget();
                m25a(C0022d.SURFACE_ACQUIRED);
            }
        }

        /* renamed from: d */
        public final void m32d() {
            m25a(C0022d.FOCUS_LOST);
        }

        /* renamed from: d */
        public final void m33d(Runnable runnable) {
            if (this.f101a != null) {
                Message.obtain(this.f101a, runnable).sendToTarget();
            }
        }

        public final void run() {
            setName("UnityMain");
            Looper.prepare();
            this.f101a = new Handler(new C00231(this));
            Looper.loop();
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer$f */
    private abstract class C0025f implements Runnable {
        /* renamed from: e */
        final /* synthetic */ UnityPlayer f107e;

        private C0025f(UnityPlayer unityPlayer) {
            this.f107e = unityPlayer;
        }

        /* renamed from: a */
        public abstract void mo6a();

        public final void run() {
            if (!this.f107e.isFinishing()) {
                mo6a();
            }
        }
    }

    static {
        new C0052m().m98a();
        f300t = false;
        f300t = loadLibraryStatic("main");
    }

    public UnityPlayer(Context context) {
        super(context);
        if (context instanceof Activity) {
            currentActivity = (Activity) context;
            this.f303c = currentActivity.getRequestedOrientation();
        }
        m167a(currentActivity);
        this.f318r = context;
        if (currentActivity != null && m199k()) {
            this.f313m = new C0051l(this.f318r, C0050a.m97a()[getSplashMode()]);
            addView(this.f313m);
        }
        m168a(this.f318r.getApplicationInfo());
        if (C0053n.m101c()) {
            initJni(context);
            this.f306f.m104c(true);
            this.f319s = m182c();
            addView(this.f319s);
            bringChildToFront(this.f313m);
            this.f320u = false;
            nativeInitWebRequest(UnityWebRequest.class);
            m202m();
            this.f311k = (TelephonyManager) this.f318r.getSystemService("phone");
            this.f312l = (ClipboardManager) this.f318r.getSystemService("clipboard");
            this.f316p = new Camera2Wrapper(this.f318r);
            this.f317q = new HFPStatus(this.f318r);
            this.f301a.start();
            return;
        }
        AlertDialog create = new Builder(this.f318r).setTitle("Failure to initialize!").setPositiveButton("OK", new C00111(this)).setMessage("Your hardware does not support this application, sorry!").create();
        create.setCancelable(false);
        create.show();
    }

    public static void UnitySendMessage(String str, String str2, String str3) {
        if (C0053n.m101c()) {
            nativeUnitySendMessage(str, str2, str3);
            return;
        }
        StringBuilder stringBuilder = new StringBuilder("Native libraries not loaded - dropping message for ");
        stringBuilder.append(str);
        stringBuilder.append(".");
        stringBuilder.append(str2);
        C0042g.Log(5, stringBuilder.toString());
    }

    /* renamed from: a */
    private void m165a() {
        m213a(new Runnable(this) {
            /* renamed from: a */
            final /* synthetic */ UnityPlayer f52a;

            {
                this.f52a = r1;
            }

            public final void run() {
                this.f52a.removeView(this.f52a.f313m);
                this.f52a.f313m = null;
            }
        });
    }

    /* renamed from: a */
    private void m166a(int i, Surface surface) {
        if (!this.f304d) {
            m181b(0, surface);
        }
    }

    /* renamed from: a */
    private static void m167a(Activity activity) {
        if (activity != null && activity.getIntent().getBooleanExtra("android.intent.extra.VR_LAUNCH", false) && activity.getWindow() != null) {
            View decorView = activity.getWindow().getDecorView();
            if (decorView != null) {
                decorView.setSystemUiVisibility(7);
            }
        }
    }

    /* renamed from: a */
    private static void m168a(ApplicationInfo applicationInfo) {
        if (f300t && NativeLoader.load(applicationInfo.nativeLibraryDir)) {
            C0053n.m99a();
        }
    }

    /* renamed from: a */
    private void m169a(View view, View view2) {
        Object obj;
        if (this.f306f.m106d()) {
            obj = null;
        } else {
            pause();
            obj = 1;
        }
        if (view != null) {
            ViewParent parent = view.getParent();
            if (!((parent instanceof UnityPlayer) && ((UnityPlayer) parent) == this)) {
                if (parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(view);
                }
                addView(view);
                bringChildToFront(view);
                view.setVisibility(0);
            }
        }
        if (view2 != null && view2.getParent() == this) {
            view2.setVisibility(8);
            removeView(view2);
        }
        if (obj != null) {
            resume();
        }
    }

    /* renamed from: a */
    private void m170a(C0025f c0025f) {
        if (!isFinishing()) {
            m179b((Runnable) c0025f);
        }
    }

    /* renamed from: b */
    private void m179b(Runnable runnable) {
        if (!C0053n.m101c()) {
            return;
        }
        if (Thread.currentThread() == this.f301a) {
            runnable.run();
        } else {
            this.f307g.add(runnable);
        }
    }

    /* renamed from: b */
    private static boolean m180b() {
        if (currentActivity == null) {
            return false;
        }
        TypedValue typedValue = new TypedValue();
        return currentActivity.getTheme().resolveAttribute(16842840, typedValue, true) && typedValue.type == 18 && typedValue.data != 0;
    }

    /* renamed from: b */
    private boolean m181b(final int r4, final android.view.Surface r5) {
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
	at jadx.api.JadxDecompiler$$Lambda$8/345281752.run(Unknown Source)
*/
        /*
        r3 = this;
        r0 = com.unity3d.player.C0053n.m101c();
        r1 = 0;
        if (r0 == 0) goto L_0x0049;
    L_0x0007:
        r0 = r3.f306f;
        r0 = r0.m107e();
        if (r0 != 0) goto L_0x0010;
    L_0x000f:
        goto L_0x0049;
    L_0x0010:
        r0 = new java.util.concurrent.Semaphore;
        r0.<init>(r1);
        r1 = new com.unity3d.player.UnityPlayer$20;
        r1.<init>(r3, r4, r5, r0);
        if (r4 != 0) goto L_0x002a;
    L_0x001c:
        if (r5 != 0) goto L_0x0024;
    L_0x001e:
        r2 = r3.f301a;
        r2.m29b(r1);
        goto L_0x002d;
    L_0x0024:
        r2 = r3.f301a;
        r2.m31c(r1);
        goto L_0x002d;
    L_0x002a:
        r1.run();
    L_0x002d:
        if (r5 != 0) goto L_0x0047;
    L_0x002f:
        if (r4 != 0) goto L_0x0047;
    L_0x0031:
        r4 = 4;
        r1 = 5;
        r2 = java.util.concurrent.TimeUnit.SECONDS;	 Catch:{ InterruptedException -> 0x0042 }
        r4 = r0.tryAcquire(r4, r2);	 Catch:{ InterruptedException -> 0x0042 }
        if (r4 != 0) goto L_0x0047;	 Catch:{ InterruptedException -> 0x0042 }
    L_0x003c:
        r4 = "Timeout while trying detaching primary window.";	 Catch:{ InterruptedException -> 0x0042 }
        com.unity3d.player.C0042g.Log(r1, r4);	 Catch:{ InterruptedException -> 0x0042 }
        goto L_0x0047;
    L_0x0042:
        r4 = "UI thread got interrupted while trying to detach the primary window from the Unity Engine.";
        com.unity3d.player.C0042g.Log(r1, r4);
    L_0x0047:
        r4 = 1;
        return r4;
    L_0x0049:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.UnityPlayer.b(int, android.view.Surface):boolean");
    }

    /* renamed from: c */
    private SurfaceView m182c() {
        SurfaceView surfaceView = new SurfaceView(this.f318r);
        if (m180b()) {
            surfaceView.getHolder().setFormat(-3);
            surfaceView.setZOrderOnTop(true);
        } else {
            surfaceView.getHolder().setFormat(-1);
        }
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback(this) {
            /* renamed from: a */
            final /* synthetic */ UnityPlayer f53a;

            {
                this.f53a = r1;
            }

            public final void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
                this.f53a.m166a(0, surfaceHolder.getSurface());
                this.f53a.m184d();
            }

            public final void surfaceCreated(SurfaceHolder surfaceHolder) {
                this.f53a.m166a(0, surfaceHolder.getSurface());
            }

            public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                this.f53a.m166a(0, null);
            }
        });
        surfaceView.setFocusable(true);
        surfaceView.setFocusableInTouchMode(true);
        return surfaceView;
    }

    /* renamed from: d */
    private void m184d() {
        if (!C0053n.m101c()) {
            return;
        }
        if (this.f306f.m107e()) {
            this.f301a.m33d(new Runnable(this) {
                /* renamed from: a */
                final /* synthetic */ UnityPlayer f54a;

                {
                    this.f54a = r1;
                }

                public final void run() {
                    this.f54a.nativeSendSurfaceChangedEvent();
                }
            });
        }
    }

    /* renamed from: e */
    private void m187e() {
        if ((this.f318r instanceof Activity) && !((Activity) this.f318r).isFinishing()) {
            ((Activity) this.f318r).finish();
        }
    }

    /* renamed from: f */
    private void m189f() {
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
	at jadx.api.JadxDecompiler$$Lambda$8/345281752.run(Unknown Source)
*/
        /*
        r7 = this;
        r0 = 1;
        r1 = 0;
        r7.reportSoftInputStr(r1, r0, r0);
        r1 = r7.f306f;
        r1 = r1.m109g();
        if (r1 != 0) goto L_0x000e;
    L_0x000d:
        return;
    L_0x000e:
        r1 = com.unity3d.player.C0053n.m101c();
        r2 = 0;
        if (r1 == 0) goto L_0x004f;
    L_0x0015:
        r1 = new java.util.concurrent.Semaphore;
        r1.<init>(r2);
        r3 = r7.isFinishing();
        if (r3 == 0) goto L_0x0026;
    L_0x0020:
        r3 = new com.unity3d.player.UnityPlayer$22;
        r3.<init>(r7, r1);
        goto L_0x002b;
    L_0x0026:
        r3 = new com.unity3d.player.UnityPlayer$23;
        r3.<init>(r7, r1);
    L_0x002b:
        r4 = r7.f301a;
        r4.m27a(r3);
        r3 = 4;
        r5 = 5;
        r6 = java.util.concurrent.TimeUnit.SECONDS;	 Catch:{ InterruptedException -> 0x0041 }
        r3 = r1.tryAcquire(r3, r6);	 Catch:{ InterruptedException -> 0x0041 }
        if (r3 != 0) goto L_0x0046;	 Catch:{ InterruptedException -> 0x0041 }
    L_0x003b:
        r3 = "Timeout while trying to pause the Unity Engine.";	 Catch:{ InterruptedException -> 0x0041 }
        com.unity3d.player.C0042g.Log(r5, r3);	 Catch:{ InterruptedException -> 0x0041 }
        goto L_0x0046;
    L_0x0041:
        r3 = "UI thread got interrupted while trying to pause the Unity Engine.";
        com.unity3d.player.C0042g.Log(r5, r3);
    L_0x0046:
        r1 = r1.drainPermits();
        if (r1 <= 0) goto L_0x004f;
    L_0x004c:
        r7.destroy();
    L_0x004f:
        r1 = r7.f306f;
        r1.m105d(r2);
        r1 = r7.f306f;
        r1.m103b(r0);
        r0 = r7.f309i;
        if (r0 == 0) goto L_0x0064;
    L_0x005d:
        r0 = r7.f311k;
        r1 = r7.f310j;
        r0.listen(r1, r2);
    L_0x0064:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.UnityPlayer.f():void");
    }

    /* renamed from: g */
    private void m190g() {
        this.f321v = true;
        nativeDone();
        this.f306f.m104c(false);
    }

    /* renamed from: h */
    private void m192h() {
        if (this.f306f.m108f()) {
            this.f306f.m105d(true);
            m179b(new C00133(this));
            this.f301a.m28b();
        }
    }

    /* renamed from: i */
    private static void m194i() {
        if (!C0053n.m101c()) {
            return;
        }
        if (NativeLoader.unload()) {
            C0053n.m100b();
            return;
        }
        throw new UnsatisfiedLinkError("Unable to unload libraries from libmain.so");
    }

    private final native void initJni(Context context);

    /* renamed from: j */
    private ApplicationInfo m196j() {
        return this.f318r.getPackageManager().getApplicationInfo(this.f318r.getPackageName(), PIXEL_FORMAT.NV12);
    }

    /* renamed from: k */
    private boolean m199k() {
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
	at jadx.api.JadxDecompiler$$Lambda$8/345281752.run(Unknown Source)
*/
        /*
        r2 = this;
        r0 = r2.m196j();	 Catch:{ Exception -> 0x000d }
        r0 = r0.metaData;	 Catch:{ Exception -> 0x000d }
        r1 = "unity.splash-enable";	 Catch:{ Exception -> 0x000d }
        r0 = r0.getBoolean(r1);	 Catch:{ Exception -> 0x000d }
        return r0;
    L_0x000d:
        r0 = 0;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.UnityPlayer.k():boolean");
    }

    /* renamed from: l */
    private boolean m200l() {
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
	at jadx.api.JadxDecompiler$$Lambda$8/345281752.run(Unknown Source)
*/
        /*
        r2 = this;
        r0 = r2.m196j();	 Catch:{ Exception -> 0x000d }
        r0 = r0.metaData;	 Catch:{ Exception -> 0x000d }
        r1 = "unity.tango-enable";	 Catch:{ Exception -> 0x000d }
        r0 = r0.getBoolean(r1);	 Catch:{ Exception -> 0x000d }
        return r0;
    L_0x000d:
        r0 = 0;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.UnityPlayer.l():boolean");
    }

    protected static boolean loadLibraryStatic(java.lang.String r4) {
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
	at jadx.api.JadxDecompiler$$Lambda$8/345281752.run(Unknown Source)
*/
        /*
        r0 = 0;
        r1 = 6;
        java.lang.System.loadLibrary(r4);	 Catch:{ UnsatisfiedLinkError -> 0x001a, Exception -> 0x0007 }
        r4 = 1;
        return r4;
    L_0x0007:
        r4 = move-exception;
        r2 = new java.lang.StringBuilder;
        r3 = "Unknown error ";
        r2.<init>(r3);
        r2.append(r4);
    L_0x0012:
        r4 = r2.toString();
        com.unity3d.player.C0042g.Log(r1, r4);
        return r0;
    L_0x001a:
        r2 = new java.lang.StringBuilder;
        r3 = "Unable to find ";
        r2.<init>(r3);
        r2.append(r4);
        goto L_0x0012;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.UnityPlayer.loadLibraryStatic(java.lang.String):boolean");
    }

    /* renamed from: m */
    private void m202m() {
        if (this.f318r instanceof Activity) {
            ((Activity) this.f318r).getWindow().setFlags(PIXEL_FORMAT.YUYV, PIXEL_FORMAT.YUYV);
        }
    }

    private final native void nativeDone();

    private final native void nativeFocusChanged(boolean z);

    private final native void nativeInitWebRequest(Class cls);

    private final native boolean nativeInjectEvent(InputEvent inputEvent);

    private final native boolean nativeIsAutorotationOn();

    private final native void nativeLowMemory();

    private final native void nativeMuteMasterAudio(boolean z);

    private final native boolean nativePause();

    private final native void nativeRecreateGfxState(int i, Surface surface);

    private final native boolean nativeRender();

    private final native void nativeRestartActivityIndicator();

    private final native void nativeResume();

    private final native void nativeSendSurfaceChangedEvent();

    private final native void nativeSetInputSelection(int i, int i2);

    private final native void nativeSetInputString(String str);

    private final native void nativeSoftInputCanceled();

    private final native void nativeSoftInputClosed();

    private final native void nativeSoftInputLostFocus();

    private static native void nativeUnitySendMessage(String str, String str2, String str3);

    /* renamed from: a */
    final void m213a(Runnable runnable) {
        if (this.f318r instanceof Activity) {
            ((Activity) this.f318r).runOnUiThread(runnable);
        } else {
            C0042g.Log(5, "Not running Unity from an Activity; ignored...");
        }
    }

    protected void addPhoneCallListener() {
        this.f309i = true;
        this.f311k.listen(this.f310j, 32);
    }

    public boolean addViewToPlayer(View view, boolean z) {
        m169a(view, z ? this.f319s : null);
        boolean z2 = false;
        Object obj = view.getParent() == this ? 1 : null;
        Object obj2 = (z && this.f319s.getParent() == null) ? 1 : null;
        Object obj3 = this.f319s.getParent() == this ? 1 : null;
        if (!(obj == null || (obj2 == null && obj3 == null))) {
            z2 = true;
        }
        if (!z2) {
            if (obj == null) {
                C0042g.Log(6, "addViewToPlayer: Failure adding view to hierarchy");
            }
            if (obj2 == null && obj3 == null) {
                C0042g.Log(6, "addViewToPlayer: Failure removing old view from hierarchy");
            }
        }
        return z2;
    }

    public void configurationChanged(Configuration configuration) {
        if (this.f319s instanceof SurfaceView) {
            this.f319s.getHolder().setSizeFromLayout();
        }
        if (this.f322w != null) {
            this.f322w.m137c();
        }
        GoogleVrProxy b = GoogleVrApi.m4b();
        if (b != null) {
            b.m160c();
        }
    }

    public void destroy() {
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
	at jadx.api.JadxDecompiler$$Lambda$8/345281752.run(Unknown Source)
*/
        /*
        r4 = this;
        r0 = com.unity3d.player.GoogleVrApi.m4b();
        if (r0 == 0) goto L_0x0009;
    L_0x0006:
        com.unity3d.player.GoogleVrApi.m2a();
    L_0x0009:
        r0 = r4.f316p;
        r1 = 0;
        if (r0 == 0) goto L_0x0015;
    L_0x000e:
        r0 = r4.f316p;
        r0.m143a();
        r4.f316p = r1;
    L_0x0015:
        r0 = r4.f317q;
        if (r0 == 0) goto L_0x0020;
    L_0x0019:
        r0 = r4.f317q;
        r0.m12a();
        r4.f317q = r1;
    L_0x0020:
        r0 = 1;
        r4.f320u = r0;
        r0 = r4.f306f;
        r0 = r0.m106d();
        if (r0 != 0) goto L_0x002e;
    L_0x002b:
        r4.pause();
    L_0x002e:
        r0 = r4.f301a;
        r0.m26a();
        r0 = r4.f301a;	 Catch:{ InterruptedException -> 0x003b }
        r2 = 4000; // 0xfa0 float:5.605E-42 double:1.9763E-320;	 Catch:{ InterruptedException -> 0x003b }
        r0.join(r2);	 Catch:{ InterruptedException -> 0x003b }
        goto L_0x0040;
    L_0x003b:
        r0 = r4.f301a;
        r0.interrupt();
    L_0x0040:
        r0 = r4.f308h;
        if (r0 == 0) goto L_0x004b;
    L_0x0044:
        r0 = r4.f318r;
        r2 = r4.f308h;
        r0.unregisterReceiver(r2);
    L_0x004b:
        r4.f308h = r1;
        r0 = com.unity3d.player.C0053n.m101c();
        if (r0 == 0) goto L_0x0056;
    L_0x0053:
        r4.removeAllViews();
    L_0x0056:
        r0 = r4.f321v;
        if (r0 != 0) goto L_0x005d;
    L_0x005a:
        r4.kill();
    L_0x005d:
        m194i();
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.UnityPlayer.destroy():void");
    }

    protected void disableLogger() {
        C0042g.f174a = true;
    }

    public boolean displayChanged(int i, Surface surface) {
        if (i == 0) {
            this.f304d = surface != null;
            m213a(new Runnable(this) {
                /* renamed from: a */
                final /* synthetic */ UnityPlayer f60a;

                {
                    this.f60a = r1;
                }

                public final void run() {
                    if (this.f60a.f304d) {
                        this.f60a.removeView(this.f60a.f319s);
                    } else {
                        this.f60a.addView(this.f60a.f319s);
                    }
                }
            });
        }
        return m181b(i, surface);
    }

    protected void executeGLThreadJobs() {
        while (true) {
            Runnable runnable = (Runnable) this.f307g.poll();
            if (runnable != null) {
                runnable.run();
            } else {
                return;
            }
        }
    }

    protected String getClipboardText() {
        String str = "";
        ClipData primaryClip = this.f312l.getPrimaryClip();
        return primaryClip != null ? primaryClip.getItemAt(0).coerceToText(this.f318r).toString() : str;
    }

    public Bundle getSettings() {
        return Bundle.EMPTY;
    }

    protected int getSplashMode() {
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
	at jadx.api.JadxDecompiler$$Lambda$8/345281752.run(Unknown Source)
*/
        /*
        r2 = this;
        r0 = r2.m196j();	 Catch:{ Exception -> 0x000d }
        r0 = r0.metaData;	 Catch:{ Exception -> 0x000d }
        r1 = "unity.splash-mode";	 Catch:{ Exception -> 0x000d }
        r0 = r0.getInt(r1);	 Catch:{ Exception -> 0x000d }
        return r0;
    L_0x000d:
        r0 = 0;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.UnityPlayer.getSplashMode():int");
    }

    public View getView() {
        return this;
    }

    protected void hideSoftInput() {
        final Runnable c00155 = new C00155(this);
        if (C0044j.f176b) {
            m170a(new C0025f(this) {
                /* renamed from: b */
                final /* synthetic */ UnityPlayer f299b;

                /* renamed from: a */
                public final void mo6a() {
                    this.f299b.m213a(c00155);
                }
            });
        } else {
            m213a(c00155);
        }
    }

    public void init(int i, boolean z) {
    }

    protected boolean initializeGoogleAr() {
        if (this.f314n == null && currentActivity != null && m200l()) {
            this.f314n = new GoogleARCoreApi();
            this.f314n.initializeARCore(currentActivity);
            if (!this.f306f.m106d()) {
                this.f314n.resumeARCore();
            }
        }
        return false;
    }

    protected boolean initializeGoogleVr() {
        GoogleVrProxy b = GoogleVrApi.m4b();
        if (b == null) {
            GoogleVrApi.m3a(this);
            b = GoogleVrApi.m4b();
            if (b == null) {
                C0042g.Log(6, "Unable to create Google VR subsystem.");
                return false;
            }
        }
        final Semaphore semaphore = new Semaphore(0);
        final Runnable anonymousClass13 = new Runnable(this) {
            /* renamed from: a */
            final /* synthetic */ UnityPlayer f46a;

            {
                this.f46a = r1;
            }

            public final void run() {
                this.f46a.injectEvent(new KeyEvent(0, 4));
                this.f46a.injectEvent(new KeyEvent(1, 4));
            }
        };
        m213a(new Runnable(this) {
            /* renamed from: d */
            final /* synthetic */ UnityPlayer f50d;

            public final void run() {
                if (!b.m158a(UnityPlayer.currentActivity, this.f50d.f318r, this.f50d.m182c(), anonymousClass13)) {
                    C0042g.Log(6, "Unable to initialize Google VR subsystem.");
                }
                if (UnityPlayer.currentActivity != null) {
                    b.m156a(UnityPlayer.currentActivity.getIntent());
                }
                semaphore.release();
            }
        });
        try {
            if (semaphore.tryAcquire(4, TimeUnit.SECONDS)) {
                return b.m157a();
            }
            C0042g.Log(5, "Timeout while trying to initialize Google VR.");
            return false;
        } catch (InterruptedException e) {
            StringBuilder stringBuilder = new StringBuilder("UI thread was interrupted while initializing Google VR. ");
            stringBuilder.append(e.getLocalizedMessage());
            C0042g.Log(5, stringBuilder.toString());
            return false;
        }
    }

    public boolean injectEvent(InputEvent inputEvent) {
        return !C0053n.m101c() ? false : nativeInjectEvent(inputEvent);
    }

    protected boolean isFinishing() {
        if (!this.f320u) {
            boolean z = (this.f318r instanceof Activity) && ((Activity) this.f318r).isFinishing();
            this.f320u = z;
            if (!z) {
                return false;
            }
        }
        return true;
    }

    protected void kill() {
        Process.killProcess(Process.myPid());
    }

    protected boolean loadLibrary(String str) {
        return loadLibraryStatic(str);
    }

    public void lowMemory() {
        if (C0053n.m101c()) {
            m179b(new C00122(this));
        }
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        return injectEvent(motionEvent);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return injectEvent(keyEvent);
    }

    public boolean onKeyLongPress(int i, KeyEvent keyEvent) {
        return injectEvent(keyEvent);
    }

    public boolean onKeyMultiple(int i, int i2, KeyEvent keyEvent) {
        return injectEvent(keyEvent);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return injectEvent(keyEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return injectEvent(motionEvent);
    }

    public void pause() {
        if (this.f314n != null) {
            this.f314n.pauseARCore();
        }
        if (this.f322w != null) {
            this.f322w.m134a();
        }
        GoogleVrProxy b = GoogleVrApi.m4b();
        if (b != null) {
            b.pauseGvrLayout();
        }
        m189f();
    }

    public void quit() {
        destroy();
    }

    public void removeViewFromPlayer(View view) {
        m169a(this.f319s, view);
        Object obj = null;
        Object obj2 = view.getParent() == null ? 1 : null;
        Object obj3 = this.f319s.getParent() == this ? 1 : null;
        if (!(obj2 == null || obj3 == null)) {
            obj = 1;
        }
        if (obj == null) {
            if (obj2 == null) {
                C0042g.Log(6, "removeViewFromPlayer: Failure removing view from hierarchy");
            }
            if (obj3 == null) {
                C0042g.Log(6, "removeVireFromPlayer: Failure agging old view to hierarchy");
            }
        }
    }

    public void reportError(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(": ");
        stringBuilder.append(str2);
        C0042g.Log(6, stringBuilder.toString());
    }

    protected void reportSoftInputSelection(final int i, final int i2) {
        m170a(new C0025f(this) {
            /* renamed from: c */
            final /* synthetic */ UnityPlayer f296c;

            /* renamed from: a */
            public final void mo6a() {
                this.f296c.nativeSetInputSelection(i, i2);
            }
        });
    }

    protected void reportSoftInputStr(final String str, final int i, final boolean z) {
        if (i == 1) {
            hideSoftInput();
        }
        m170a(new C0025f(this) {
            /* renamed from: d */
            final /* synthetic */ UnityPlayer f293d;

            /* renamed from: a */
            public final void mo6a() {
                if (z) {
                    this.f293d.nativeSoftInputCanceled();
                } else if (str != null) {
                    this.f293d.nativeSetInputString(str);
                }
                if (i == 1) {
                    this.f293d.nativeSoftInputClosed();
                }
            }
        });
    }

    protected void requestUserAuthorization(String str) {
        if (C0044j.f177c && str != null && !str.isEmpty() && currentActivity != null) {
            C0044j.f178d.mo12a(currentActivity, str);
        }
    }

    public void resume() {
        if (this.f314n != null) {
            this.f314n.resumeARCore();
        }
        this.f306f.m103b(false);
        if (this.f322w != null) {
            this.f322w.m136b();
        }
        m192h();
        nativeRestartActivityIndicator();
        GoogleVrProxy b = GoogleVrApi.m4b();
        if (b != null) {
            b.m159b();
        }
    }

    protected void setCharacterLimit(final int i) {
        m213a(new Runnable(this) {
            /* renamed from: b */
            final /* synthetic */ UnityPlayer f82b;

            public final void run() {
                if (this.f82b.f302b != null) {
                    this.f82b.f302b.m93a(i);
                }
            }
        });
    }

    protected void setClipboardText(String str) {
        this.f312l.setPrimaryClip(ClipData.newPlainText("Text", str));
    }

    protected void setHideInputField(final boolean z) {
        m213a(new Runnable(this) {
            /* renamed from: b */
            final /* synthetic */ UnityPlayer f84b;

            public final void run() {
                if (this.f84b.f302b != null) {
                    this.f84b.f302b.m96a(z);
                }
            }
        });
    }

    protected void setSelection(final int i, final int i2) {
        m213a(new Runnable(this) {
            /* renamed from: c */
            final /* synthetic */ UnityPlayer f45c;

            public final void run() {
                if (this.f45c.f302b != null) {
                    this.f45c.f302b.m94a(i, i2);
                }
            }
        });
    }

    protected void setSoftInputStr(final String str) {
        m213a(new Runnable(this) {
            /* renamed from: b */
            final /* synthetic */ UnityPlayer f80b;

            public final void run() {
                if (this.f80b.f302b != null && str != null) {
                    this.f80b.f302b.m95a(str);
                }
            }
        });
    }

    protected void showSoftInput(String str, int i, boolean z, boolean z2, boolean z3, boolean z4, String str2, int i2, boolean z5) {
        final UnityPlayer unityPlayer = this;
        final String str3 = str;
        final int i3 = i;
        final boolean z6 = z;
        final boolean z7 = z2;
        final boolean z8 = z3;
        final boolean z9 = z4;
        final String str4 = str2;
        final int i4 = i2;
        final boolean z10 = z5;
        Runnable c00144 = new Runnable(this) {
            /* renamed from: k */
            final /* synthetic */ UnityPlayer f77k;

            public final void run() {
                this.f77k.f302b = new C0048k(this.f77k.f318r, unityPlayer, str3, i3, z6, z7, z8, str4, i4, z10);
                this.f77k.f302b.show();
            }
        };
        UnityPlayer unityPlayer2 = this;
        m213a(c00144);
    }

    protected boolean showVideoPlayer(String str, int i, int i2, int i3, boolean z, int i4, int i5) {
        if (this.f322w == null) {
            r0.f322w = new C0065q(this);
        }
        boolean a = r0.f322w.m135a(r0.f318r, str, i, i2, i3, z, (long) i4, (long) i5, new C0064a(this) {
            /* renamed from: a */
            final /* synthetic */ UnityPlayer f297a;

            {
                this.f297a = r1;
            }

            /* renamed from: a */
            public final void mo7a() {
                this.f297a.f322w = null;
            }
        });
        if (a) {
            m213a(new Runnable(this) {
                /* renamed from: a */
                final /* synthetic */ UnityPlayer f51a;

                {
                    this.f51a = r1;
                }

                public final void run() {
                    if (this.f51a.nativeIsAutorotationOn() && (this.f51a.f318r instanceof Activity)) {
                        ((Activity) this.f51a.f318r).setRequestedOrientation(this.f51a.f303c);
                    }
                }
            });
        }
        return a;
    }

    protected boolean skipPermissionsDialog() {
        return (!C0044j.f177c || currentActivity == null) ? false : C0044j.f178d.mo13a(currentActivity);
    }

    public void start() {
    }

    public void stop() {
    }

    protected void toggleGyroscopeSensor(boolean z) {
        SensorManager sensorManager = (SensorManager) this.f318r.getSystemService("sensor");
        Sensor defaultSensor = sensorManager.getDefaultSensor(11);
        if (z) {
            sensorManager.registerListener(this.f315o, defaultSensor, 1);
        } else {
            sensorManager.unregisterListener(this.f315o);
        }
    }

    public void windowFocusChanged(boolean z) {
        this.f306f.m102a(z);
        if (this.f306f.m107e()) {
            if (z && this.f302b != null) {
                nativeSoftInputLostFocus();
                reportSoftInputStr(null, 1, false);
            }
            if (z) {
                this.f301a.m30c();
            } else {
                this.f301a.m32d();
            }
            m192h();
        }
    }
}
