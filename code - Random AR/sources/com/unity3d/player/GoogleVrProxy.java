package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.View;
import com.unity3d.player.GoogleVrVideo.GoogleVrVideoCallbacks;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicLong;

class GoogleVrProxy extends C0038c implements GoogleVrVideo {
    /* renamed from: f */
    private boolean f282f = false;
    /* renamed from: g */
    private boolean f283g = false;
    /* renamed from: h */
    private Runnable f284h = null;
    /* renamed from: i */
    private Vector f285i = new Vector();
    /* renamed from: j */
    private SurfaceView f286j = null;
    /* renamed from: k */
    private C0006a f287k = new C0006a(this);
    /* renamed from: l */
    private Thread f288l = null;
    /* renamed from: m */
    private Handler f289m = new Handler(this, Looper.getMainLooper()) {
        /* renamed from: a */
        final /* synthetic */ GoogleVrProxy f4a;

        public final void handleMessage(Message message) {
            if (message.what != 135711) {
                super.handleMessage(message);
                return;
            }
            switch (message.arg1) {
                case 2147483645:
                    Iterator it = this.f4a.f285i.iterator();
                    while (it.hasNext()) {
                        ((GoogleVrVideoCallbacks) it.next()).onFrameAvailable();
                    }
                    return;
                case 2147483646:
                    Surface surface = (Surface) message.obj;
                    Iterator it2 = this.f4a.f285i.iterator();
                    while (it2.hasNext()) {
                        ((GoogleVrVideoCallbacks) it2.next()).onSurfaceAvailable(surface);
                    }
                    return;
                default:
                    super.handleMessage(message);
                    return;
            }
        }
    };

    /* renamed from: com.unity3d.player.GoogleVrProxy$4 */
    class C00054 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ GoogleVrProxy f14a;

        C00054(GoogleVrProxy googleVrProxy) {
            this.f14a = googleVrProxy;
        }

        public final void run() {
            try {
                if (this.f14a.a != null) {
                    this.f14a.a.m111a("unload", new Object[0]);
                    this.f14a.a.m111a("deinitialize", new Object[0]);
                    this.f14a.a = null;
                }
                this.f14a.f287k.f16b = false;
            } catch (Exception e) {
                GoogleVrProxy googleVrProxy = this.f14a;
                StringBuilder stringBuilder = new StringBuilder("Exception unloading Google VR on UI Thread. ");
                stringBuilder.append(e.getLocalizedMessage());
                googleVrProxy.reportError(stringBuilder.toString());
            }
        }
    }

    /* renamed from: com.unity3d.player.GoogleVrProxy$a */
    class C0006a {
        /* renamed from: a */
        public boolean f15a = false;
        /* renamed from: b */
        public boolean f16b = false;
        /* renamed from: c */
        public boolean f17c = false;
        /* renamed from: d */
        public boolean f18d = false;
        /* renamed from: e */
        public boolean f19e = true;
        /* renamed from: f */
        public boolean f20f = false;
        /* renamed from: g */
        final /* synthetic */ GoogleVrProxy f21g;

        C0006a(GoogleVrProxy googleVrProxy) {
            this.f21g = googleVrProxy;
        }

        /* renamed from: a */
        public final boolean m5a() {
            return this.f15a && this.f16b;
        }

        /* renamed from: b */
        public final void m6b() {
            this.f15a = false;
            this.f16b = false;
            this.f18d = false;
            this.f19e = true;
            this.f20f = false;
        }
    }

    public GoogleVrProxy(C0041f c0041f) {
        super("Google VR", c0041f);
        initVrJni();
    }

    /* renamed from: a */
    private void m148a(boolean z) {
        this.f287k.f18d = z;
    }

    /* renamed from: a */
    private static boolean m149a(int i) {
        return VERSION.SDK_INT >= i;
    }

    /* renamed from: a */
    private boolean m150a(ClassLoader classLoader) {
        try {
            Class loadClass = classLoader.loadClass("com.unity3d.unitygvr.GoogleVR");
            C0055o c0055o = new C0055o(loadClass, loadClass.getConstructor(new Class[0]).newInstance(new Object[0]));
            c0055o.m112a("initialize", new Class[]{Activity.class, Context.class, SurfaceView.class, Boolean.TYPE, Handler.class});
            c0055o.m112a("deinitialize", new Class[0]);
            c0055o.m112a("load", new Class[]{Boolean.TYPE, Boolean.TYPE, Boolean.TYPE, Boolean.TYPE, Boolean.TYPE, Runnable.class});
            c0055o.m112a("enable", new Class[]{Boolean.TYPE});
            c0055o.m112a("unload", new Class[0]);
            c0055o.m112a("pause", new Class[0]);
            c0055o.m112a("resume", new Class[0]);
            c0055o.m112a("getGvrLayout", new Class[0]);
            c0055o.m112a("getVideoSurfaceId", new Class[0]);
            c0055o.m112a("getVideoSurface", new Class[0]);
            this.a = c0055o;
            return true;
        } catch (Exception e) {
            StringBuilder stringBuilder = new StringBuilder("Exception initializing GoogleVR from Unity library. ");
            stringBuilder.append(e.getLocalizedMessage());
            reportError(stringBuilder.toString());
            return false;
        }
    }

    /* renamed from: d */
    private boolean m153d() {
        return this.f287k.f18d;
    }

    /* renamed from: e */
    private void m155e() {
        Activity activity = (Activity) this.c;
        if (this.f283g && !this.f287k.f20f && activity != null) {
            this.f287k.f20f = true;
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.HOME");
            intent.setFlags(268435456);
            activity.startActivity(intent);
        }
    }

    private final native void initVrJni();

    private final native boolean isQuiting();

    private final native void setVrVideoTransform(float[][] fArr);

    /* renamed from: a */
    public final void m156a(Intent intent) {
        if (intent != null && intent.getBooleanExtra("android.intent.extra.VR_LAUNCH", false)) {
            this.f283g = true;
        }
    }

    /* renamed from: a */
    public final boolean m157a() {
        return this.f287k.f15a;
    }

    /* renamed from: a */
    public final boolean m158a(Activity activity, Context context, SurfaceView surfaceView, Runnable runnable) {
        String str;
        if (!(activity == null || context == null || surfaceView == null)) {
            if (runnable != null) {
                this.f287k.m6b();
                this.c = context;
                this.f284h = runnable;
                if (!m149a(19)) {
                    str = "Google VR requires a device that supports an api version of 19 (KitKat) or better.";
                } else if (this.f283g && !m149a(24)) {
                    str = "Daydream requires a device that supports an api version of 24 (Nougat) or better.";
                } else if (!m150a(UnityPlayer.class.getClassLoader())) {
                    return false;
                } else {
                    boolean booleanValue;
                    try {
                        booleanValue = ((Boolean) this.a.m111a("initialize", activity, context, surfaceView, Boolean.valueOf(this.f283g), this.f289m)).booleanValue();
                    } catch (Exception e) {
                        StringBuilder stringBuilder = new StringBuilder("Exception while trying to intialize Unity Google VR Library. ");
                        stringBuilder.append(e.getLocalizedMessage());
                        reportError(stringBuilder.toString());
                        booleanValue = false;
                    }
                    if (booleanValue) {
                        this.f286j = surfaceView;
                        this.f287k.f15a = true;
                        this.d = "";
                        return true;
                    }
                    str = "Unable to initialize GoogleVR library.";
                }
                reportError(str);
                return false;
            }
        }
        str = "Invalid parameters passed to Google VR initiialization.";
        reportError(str);
        return false;
    }

    /* renamed from: b */
    public final void m159b() {
        resumeGvrLayout();
    }

    /* renamed from: c */
    public final void m160c() {
        if (this.f286j != null) {
            this.f286j.getHolder().setSizeFromLayout();
        }
    }

    public void deregisterGoogleVrVideoListener(GoogleVrVideoCallbacks googleVrVideoCallbacks) {
        if (this.f285i.contains(googleVrVideoCallbacks)) {
            googleVrVideoCallbacks.onSurfaceUnavailable();
            this.f285i.remove(googleVrVideoCallbacks);
        }
    }

    protected Object getVideoSurface() {
        if (m153d()) {
            if (!this.f287k.f19e) {
                try {
                    return this.a.m111a("getVideoSurface", new Object[0]);
                } catch (Exception e) {
                    StringBuilder stringBuilder = new StringBuilder("Exception caught while Getting GoogleVR Video Surface. ");
                    stringBuilder.append(e.getLocalizedMessage());
                    reportError(stringBuilder.toString());
                }
            }
        }
        return null;
    }

    protected int getVideoSurfaceId() {
        if (m153d()) {
            if (!this.f287k.f19e) {
                try {
                    return ((Integer) this.a.m111a("getVideoSurfaceId", new Object[0])).intValue();
                } catch (Exception e) {
                    StringBuilder stringBuilder = new StringBuilder("Exception caught while getting Video Surface ID from GoogleVR. ");
                    stringBuilder.append(e.getLocalizedMessage());
                    reportError(stringBuilder.toString());
                }
            }
        }
        return -1;
    }

    protected long loadGoogleVr(boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        if (!this.f287k.f15a) {
            return 0;
        }
        String str;
        final AtomicLong atomicLong;
        final boolean z6;
        final boolean z7;
        final boolean z8;
        final boolean z9;
        final boolean z10;
        AtomicLong atomicLong2 = new AtomicLong(0);
        if (!z) {
            if (!z2) {
                str = "Cardboard";
                r8.d = str;
                atomicLong = atomicLong2;
                z6 = z;
                z7 = z2;
                z8 = z3;
                z9 = z4;
                z10 = z5;
                if (!runOnUiThreadWithSync(new Runnable(this) {
                    /* renamed from: g */
                    final /* synthetic */ GoogleVrProxy f11g;

                    public final void run() {
                        try {
                            atomicLong.set(((Long) this.f11g.a.m111a("load", Boolean.valueOf(z6), Boolean.valueOf(z7), Boolean.valueOf(z8), Boolean.valueOf(z9), Boolean.valueOf(z10), this.f11g.f284h)).longValue());
                            this.f11g.f287k.f16b = true;
                        } catch (Exception e) {
                            GoogleVrProxy googleVrProxy = this.f11g;
                            StringBuilder stringBuilder = new StringBuilder("Exception caught while loading GoogleVR. ");
                            stringBuilder.append(e.getLocalizedMessage());
                            googleVrProxy.reportError(stringBuilder.toString());
                            atomicLong.set(0);
                        }
                    }
                }) || atomicLong2.longValue() == 0) {
                    reportError("Google VR had a fatal issue while loading. VR will not be available.");
                }
                return atomicLong2.longValue();
            }
        }
        str = "Daydream";
        r8.d = str;
        atomicLong = atomicLong2;
        z6 = z;
        z7 = z2;
        z8 = z3;
        z9 = z4;
        z10 = z5;
        reportError("Google VR had a fatal issue while loading. VR will not be available.");
        return atomicLong2.longValue();
    }

    protected void pauseGvrLayout() {
        if (this.f287k.m5a() && !this.f287k.f19e) {
            if (m153d()) {
                Iterator it = this.f285i.iterator();
                while (it.hasNext()) {
                    ((GoogleVrVideoCallbacks) it.next()).onSurfaceUnavailable();
                }
            }
            if (this.a != null) {
                this.a.m111a("pause", new Object[0]);
            }
            this.f287k.f19e = true;
        }
    }

    public void registerGoogleVrVideoListener(GoogleVrVideoCallbacks googleVrVideoCallbacks) {
        if (!this.f285i.contains(googleVrVideoCallbacks)) {
            this.f285i.add(googleVrVideoCallbacks);
            Surface surface = (Surface) getVideoSurface();
            if (surface != null) {
                googleVrVideoCallbacks.onSurfaceAvailable(surface);
            }
        }
    }

    protected void resumeGvrLayout() {
        if (this.f287k.m5a() && this.f287k.f19e) {
            if (this.a != null) {
                this.a.m111a("resume", new Object[0]);
            }
            this.f287k.f19e = false;
        }
    }

    protected void setGoogleVrModeEnabled(final boolean z) {
        if (this.f287k.m5a() && this.b != null) {
            if (this.c != null) {
                if (!z && isQuiting()) {
                    m155e();
                }
                runOnUiThread(new Runnable(this) {
                    /* renamed from: b */
                    final /* synthetic */ GoogleVrProxy f13b;

                    public final void run() {
                        if (z != this.f13b.m153d()) {
                            try {
                                if (!z || this.f13b.m153d()) {
                                    if (!z && this.f13b.m153d()) {
                                        this.f13b.m148a(false);
                                        if (this.f13b.a != null) {
                                            this.f13b.a.m111a("enable", Boolean.valueOf(false));
                                        }
                                        if (!(this.f13b.a == null || this.f13b.b == null)) {
                                            this.f13b.b.removeViewFromPlayer((View) this.f13b.a.m111a("getGvrLayout", new Object[0]));
                                        }
                                    }
                                } else if (this.f13b.a == null || this.f13b.b == null || this.f13b.b.addViewToPlayer((View) this.f13b.a.m111a("getGvrLayout", new Object[0]), true)) {
                                    if (this.f13b.a != null) {
                                        this.f13b.a.m111a("enable", Boolean.valueOf(true));
                                    }
                                    this.f13b.m148a(true);
                                } else {
                                    this.f13b.reportError("Unable to add Google VR to view hierarchy.");
                                }
                            } catch (Exception e) {
                                GoogleVrProxy googleVrProxy = this.f13b;
                                StringBuilder stringBuilder = new StringBuilder("Exception enabling Google VR on UI Thread. ");
                                stringBuilder.append(e.getLocalizedMessage());
                                googleVrProxy.reportError(stringBuilder.toString());
                            }
                        }
                    }
                });
            }
        }
    }

    public void setVideoLocationTransform(float[] fArr) {
        float[][] fArr2 = (float[][]) Array.newInstance(float.class, new int[]{4, 4});
        for (int i = 0; i < 4; i++) {
            for (int i2 = 0; i2 < 4; i2++) {
                fArr2[i][i2] = fArr[(i * 4) + i2];
            }
        }
        setVrVideoTransform(fArr2);
    }

    protected void unloadGoogleVr() {
        if (this.f287k.f18d) {
            setGoogleVrModeEnabled(false);
        }
        if (this.f287k.f17c) {
            this.f287k.f17c = false;
        }
        this.f286j = null;
        runOnUiThread(new C00054(this));
    }
}
