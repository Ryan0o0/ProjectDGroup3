package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.MediaController.MediaPlayerControl;

/* renamed from: com.unity3d.player.p */
public final class C0058p extends FrameLayout implements OnBufferingUpdateListener, OnCompletionListener, OnPreparedListener, OnVideoSizeChangedListener, Callback, MediaPlayerControl {
    /* renamed from: a */
    private static boolean f212a = false;
    /* renamed from: b */
    private final Context f213b;
    /* renamed from: c */
    private final SurfaceView f214c;
    /* renamed from: d */
    private final SurfaceHolder f215d;
    /* renamed from: e */
    private final String f216e;
    /* renamed from: f */
    private final int f217f;
    /* renamed from: g */
    private final int f218g;
    /* renamed from: h */
    private final boolean f219h;
    /* renamed from: i */
    private final long f220i;
    /* renamed from: j */
    private final long f221j;
    /* renamed from: k */
    private final FrameLayout f222k;
    /* renamed from: l */
    private final Display f223l;
    /* renamed from: m */
    private int f224m;
    /* renamed from: n */
    private int f225n;
    /* renamed from: o */
    private int f226o;
    /* renamed from: p */
    private int f227p;
    /* renamed from: q */
    private MediaPlayer f228q;
    /* renamed from: r */
    private MediaController f229r;
    /* renamed from: s */
    private boolean f230s = false;
    /* renamed from: t */
    private boolean f231t = false;
    /* renamed from: u */
    private int f232u = 0;
    /* renamed from: v */
    private boolean f233v = false;
    /* renamed from: w */
    private boolean f234w = false;
    /* renamed from: x */
    private C0056a f235x;
    /* renamed from: y */
    private C0057b f236y;
    /* renamed from: z */
    private volatile int f237z = 0;

    /* renamed from: com.unity3d.player.p$a */
    public interface C0056a {
        /* renamed from: a */
        void mo14a(int i);
    }

    /* renamed from: com.unity3d.player.p$b */
    public class C0057b implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C0058p f209a;
        /* renamed from: b */
        private C0058p f210b;
        /* renamed from: c */
        private boolean f211c = false;

        public C0057b(C0058p c0058p, C0058p c0058p2) {
            this.f209a = c0058p;
            this.f210b = c0058p2;
        }

        /* renamed from: a */
        public final void m114a() {
            this.f211c = true;
        }

        public final void run() {
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
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/345281752.run(Unknown Source)
*/
            /*
            r2 = this;
            r0 = 5000; // 0x1388 float:7.006E-42 double:2.4703E-320;
            java.lang.Thread.sleep(r0);	 Catch:{ InterruptedException -> 0x0006 }
            goto L_0x000d;
        L_0x0006:
            r0 = java.lang.Thread.currentThread();
            r0.interrupt();
        L_0x000d:
            r0 = r2.f211c;
            if (r0 != 0) goto L_0x0021;
        L_0x0011:
            r0 = com.unity3d.player.C0058p.f212a;
            if (r0 == 0) goto L_0x001c;
        L_0x0017:
            r0 = "Stopping the video player due to timeout.";
            com.unity3d.player.C0058p.m117b(r0);
        L_0x001c:
            r0 = r2.f210b;
            r0.CancelOnPrepare();
        L_0x0021:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.p.b.run():void");
        }
    }

    protected C0058p(Context context, String str, int i, int i2, int i3, boolean z, long j, long j2, C0056a c0056a) {
        super(context);
        this.f235x = c0056a;
        this.f213b = context;
        this.f222k = this;
        this.f214c = new SurfaceView(context);
        this.f215d = this.f214c.getHolder();
        this.f215d.addCallback(this);
        this.f222k.setBackgroundColor(i);
        this.f222k.addView(this.f214c);
        this.f223l = ((WindowManager) this.f213b.getSystemService("window")).getDefaultDisplay();
        this.f216e = str;
        this.f217f = i2;
        this.f218g = i3;
        this.f219h = z;
        this.f220i = j;
        this.f221j = j2;
        if (f212a) {
            StringBuilder stringBuilder = new StringBuilder("fileName: ");
            stringBuilder.append(this.f216e);
            C0058p.m117b(stringBuilder.toString());
        }
        if (f212a) {
            stringBuilder = new StringBuilder("backgroundColor: ");
            stringBuilder.append(i);
            C0058p.m117b(stringBuilder.toString());
        }
        if (f212a) {
            stringBuilder = new StringBuilder("controlMode: ");
            stringBuilder.append(this.f217f);
            C0058p.m117b(stringBuilder.toString());
        }
        if (f212a) {
            stringBuilder = new StringBuilder("scalingMode: ");
            stringBuilder.append(this.f218g);
            C0058p.m117b(stringBuilder.toString());
        }
        if (f212a) {
            stringBuilder = new StringBuilder("isURL: ");
            stringBuilder.append(this.f219h);
            C0058p.m117b(stringBuilder.toString());
        }
        if (f212a) {
            stringBuilder = new StringBuilder("videoOffset: ");
            stringBuilder.append(this.f220i);
            C0058p.m117b(stringBuilder.toString());
        }
        if (f212a) {
            stringBuilder = new StringBuilder("videoLength: ");
            stringBuilder.append(this.f221j);
            C0058p.m117b(stringBuilder.toString());
        }
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    /* renamed from: a */
    private void m115a(int i) {
        this.f237z = i;
        if (this.f235x != null) {
            this.f235x.mo14a(this.f237z);
        }
    }

    /* renamed from: b */
    private static void m117b(String str) {
        StringBuilder stringBuilder = new StringBuilder("VideoPlayer: ");
        stringBuilder.append(str);
        Log.i("Video", stringBuilder.toString());
    }

    /* renamed from: c */
    private void m119c() {
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
        r8 = this;
        r0 = r8.f228q;
        if (r0 == 0) goto L_0x001e;
    L_0x0004:
        r0 = r8.f228q;
        r1 = r8.f215d;
        r0.setDisplay(r1);
        r0 = r8.f233v;
        if (r0 != 0) goto L_0x001d;
    L_0x000f:
        r0 = f212a;
        if (r0 == 0) goto L_0x0018;
    L_0x0013:
        r0 = "Resuming playback";
        com.unity3d.player.C0058p.m117b(r0);
    L_0x0018:
        r0 = r8.f228q;
        r0.start();
    L_0x001d:
        return;
    L_0x001e:
        r0 = 0;
        r8.m115a(r0);
        r8.doCleanUp();
        r0 = new android.media.MediaPlayer;	 Catch:{ Exception -> 0x00d0 }
        r0.<init>();	 Catch:{ Exception -> 0x00d0 }
        r8.f228q = r0;	 Catch:{ Exception -> 0x00d0 }
        r0 = r8.f219h;	 Catch:{ Exception -> 0x00d0 }
        if (r0 == 0) goto L_0x003e;	 Catch:{ Exception -> 0x00d0 }
    L_0x0030:
        r0 = r8.f228q;	 Catch:{ Exception -> 0x00d0 }
        r1 = r8.f213b;	 Catch:{ Exception -> 0x00d0 }
        r2 = r8.f216e;	 Catch:{ Exception -> 0x00d0 }
        r2 = android.net.Uri.parse(r2);	 Catch:{ Exception -> 0x00d0 }
        r0.setDataSource(r1, r2);	 Catch:{ Exception -> 0x00d0 }
        goto L_0x0092;	 Catch:{ Exception -> 0x00d0 }
    L_0x003e:
        r0 = r8.f221j;	 Catch:{ Exception -> 0x00d0 }
        r2 = 0;	 Catch:{ Exception -> 0x00d0 }
        r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));	 Catch:{ Exception -> 0x00d0 }
        if (r4 == 0) goto L_0x005e;	 Catch:{ Exception -> 0x00d0 }
    L_0x0046:
        r0 = new java.io.FileInputStream;	 Catch:{ Exception -> 0x00d0 }
        r1 = r8.f216e;	 Catch:{ Exception -> 0x00d0 }
        r0.<init>(r1);	 Catch:{ Exception -> 0x00d0 }
        r2 = r8.f228q;	 Catch:{ Exception -> 0x00d0 }
        r3 = r0.getFD();	 Catch:{ Exception -> 0x00d0 }
        r4 = r8.f220i;	 Catch:{ Exception -> 0x00d0 }
        r6 = r8.f221j;	 Catch:{ Exception -> 0x00d0 }
        r2.setDataSource(r3, r4, r6);	 Catch:{ Exception -> 0x00d0 }
    L_0x005a:
        r0.close();	 Catch:{ Exception -> 0x00d0 }
        goto L_0x0092;	 Catch:{ Exception -> 0x00d0 }
    L_0x005e:
        r0 = r8.getResources();	 Catch:{ Exception -> 0x00d0 }
        r0 = r0.getAssets();	 Catch:{ Exception -> 0x00d0 }
        r1 = r8.f216e;	 Catch:{ IOException -> 0x0081 }
        r0 = r0.openFd(r1);	 Catch:{ IOException -> 0x0081 }
        r1 = r8.f228q;	 Catch:{ IOException -> 0x0081 }
        r2 = r0.getFileDescriptor();	 Catch:{ IOException -> 0x0081 }
        r3 = r0.getStartOffset();	 Catch:{ IOException -> 0x0081 }
        r5 = r0.getLength();	 Catch:{ IOException -> 0x0081 }
        r1.setDataSource(r2, r3, r5);	 Catch:{ IOException -> 0x0081 }
        r0.close();	 Catch:{ IOException -> 0x0081 }
        goto L_0x0092;
    L_0x0081:
        r0 = new java.io.FileInputStream;	 Catch:{ Exception -> 0x00d0 }
        r1 = r8.f216e;	 Catch:{ Exception -> 0x00d0 }
        r0.<init>(r1);	 Catch:{ Exception -> 0x00d0 }
        r1 = r8.f228q;	 Catch:{ Exception -> 0x00d0 }
        r2 = r0.getFD();	 Catch:{ Exception -> 0x00d0 }
        r1.setDataSource(r2);	 Catch:{ Exception -> 0x00d0 }
        goto L_0x005a;	 Catch:{ Exception -> 0x00d0 }
    L_0x0092:
        r0 = r8.f228q;	 Catch:{ Exception -> 0x00d0 }
        r1 = r8.f215d;	 Catch:{ Exception -> 0x00d0 }
        r0.setDisplay(r1);	 Catch:{ Exception -> 0x00d0 }
        r0 = r8.f228q;	 Catch:{ Exception -> 0x00d0 }
        r1 = 1;	 Catch:{ Exception -> 0x00d0 }
        r0.setScreenOnWhilePlaying(r1);	 Catch:{ Exception -> 0x00d0 }
        r0 = r8.f228q;	 Catch:{ Exception -> 0x00d0 }
        r0.setOnBufferingUpdateListener(r8);	 Catch:{ Exception -> 0x00d0 }
        r0 = r8.f228q;	 Catch:{ Exception -> 0x00d0 }
        r0.setOnCompletionListener(r8);	 Catch:{ Exception -> 0x00d0 }
        r0 = r8.f228q;	 Catch:{ Exception -> 0x00d0 }
        r0.setOnPreparedListener(r8);	 Catch:{ Exception -> 0x00d0 }
        r0 = r8.f228q;	 Catch:{ Exception -> 0x00d0 }
        r0.setOnVideoSizeChangedListener(r8);	 Catch:{ Exception -> 0x00d0 }
        r0 = r8.f228q;	 Catch:{ Exception -> 0x00d0 }
        r1 = 3;	 Catch:{ Exception -> 0x00d0 }
        r0.setAudioStreamType(r1);	 Catch:{ Exception -> 0x00d0 }
        r0 = r8.f228q;	 Catch:{ Exception -> 0x00d0 }
        r0.prepareAsync();	 Catch:{ Exception -> 0x00d0 }
        r0 = new com.unity3d.player.p$b;	 Catch:{ Exception -> 0x00d0 }
        r0.<init>(r8, r8);	 Catch:{ Exception -> 0x00d0 }
        r8.f236y = r0;	 Catch:{ Exception -> 0x00d0 }
        r0 = new java.lang.Thread;	 Catch:{ Exception -> 0x00d0 }
        r1 = r8.f236y;	 Catch:{ Exception -> 0x00d0 }
        r0.<init>(r1);	 Catch:{ Exception -> 0x00d0 }
        r0.start();	 Catch:{ Exception -> 0x00d0 }
        return;
    L_0x00d0:
        r0 = move-exception;
        r1 = f212a;
        if (r1 == 0) goto L_0x00ed;
    L_0x00d5:
        r1 = new java.lang.StringBuilder;
        r2 = "error: ";
        r1.<init>(r2);
        r2 = r0.getMessage();
        r1.append(r2);
        r1.append(r0);
        r0 = r1.toString();
        com.unity3d.player.C0058p.m117b(r0);
    L_0x00ed:
        r0 = 2;
        r8.m115a(r0);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.p.c():void");
    }

    /* renamed from: d */
    private void m120d() {
        if (!isPlaying()) {
            m115a(1);
            if (f212a) {
                C0058p.m117b("startVideoPlayback");
            }
            updateVideoLayout();
            if (!this.f233v) {
                start();
            }
        }
    }

    public final void CancelOnPrepare() {
        m115a(2);
    }

    /* renamed from: a */
    final boolean m121a() {
        return this.f233v;
    }

    public final boolean canPause() {
        return true;
    }

    public final boolean canSeekBackward() {
        return true;
    }

    public final boolean canSeekForward() {
        return true;
    }

    protected final void destroyPlayer() {
        if (f212a) {
            C0058p.m117b("destroyPlayer");
        }
        if (!this.f233v) {
            pause();
        }
        doCleanUp();
    }

    protected final void doCleanUp() {
        if (this.f236y != null) {
            this.f236y.m114a();
            this.f236y = null;
        }
        if (this.f228q != null) {
            this.f228q.release();
            this.f228q = null;
        }
        this.f226o = 0;
        this.f227p = 0;
        this.f231t = false;
        this.f230s = false;
    }

    public final int getBufferPercentage() {
        return this.f219h ? this.f232u : 100;
    }

    public final int getCurrentPosition() {
        return this.f228q == null ? 0 : this.f228q.getCurrentPosition();
    }

    public final int getDuration() {
        return this.f228q == null ? 0 : this.f228q.getDuration();
    }

    public final boolean isPlaying() {
        Object obj = (this.f231t && this.f230s) ? 1 : null;
        if (this.f228q == null) {
            return obj == null;
        } else {
            if (!this.f228q.isPlaying()) {
                if (obj != null) {
                    return false;
                }
            }
            return true;
        }
    }

    public final void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        if (f212a) {
            StringBuilder stringBuilder = new StringBuilder("onBufferingUpdate percent:");
            stringBuilder.append(i);
            C0058p.m117b(stringBuilder.toString());
        }
        this.f232u = i;
    }

    public final void onCompletion(MediaPlayer mediaPlayer) {
        if (f212a) {
            C0058p.m117b("onCompletion called");
        }
        destroyPlayer();
        m115a(3);
    }

    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            if (this.f217f != 2 || i == 0 || keyEvent.isSystem()) {
                return this.f229r != null ? this.f229r.onKeyDown(i, keyEvent) : super.onKeyDown(i, keyEvent);
            }
        }
        destroyPlayer();
        m115a(3);
        return true;
    }

    public final void onPrepared(MediaPlayer mediaPlayer) {
        if (f212a) {
            C0058p.m117b("onPrepared called");
        }
        if (this.f236y != null) {
            this.f236y.m114a();
            this.f236y = null;
        }
        if (this.f217f == 0 || this.f217f == 1) {
            this.f229r = new MediaController(this.f213b);
            this.f229r.setMediaPlayer(this);
            this.f229r.setAnchorView(this);
            this.f229r.setEnabled(true);
            if (this.f213b instanceof Activity) {
                this.f229r.setSystemUiVisibility(((Activity) this.f213b).getWindow().getDecorView().getSystemUiVisibility());
            }
            this.f229r.show();
        }
        this.f231t = true;
        if (this.f231t && this.f230s) {
            m120d();
        }
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (this.f217f != 2 || action != 0) {
            return this.f229r != null ? this.f229r.onTouchEvent(motionEvent) : super.onTouchEvent(motionEvent);
        } else {
            destroyPlayer();
            m115a(3);
            return true;
        }
    }

    public final void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        if (f212a) {
            StringBuilder stringBuilder = new StringBuilder("onVideoSizeChanged called ");
            stringBuilder.append(i);
            stringBuilder.append("x");
            stringBuilder.append(i2);
            C0058p.m117b(stringBuilder.toString());
        }
        if (i != 0) {
            if (i2 != 0) {
                this.f230s = true;
                this.f226o = i;
                this.f227p = i2;
                if (this.f231t && this.f230s) {
                    m120d();
                }
                return;
            }
        }
        if (f212a) {
            stringBuilder = new StringBuilder("invalid video width(");
            stringBuilder.append(i);
            stringBuilder.append(") or height(");
            stringBuilder.append(i2);
            stringBuilder.append(")");
            C0058p.m117b(stringBuilder.toString());
        }
    }

    public final void pause() {
        if (this.f228q != null) {
            if (this.f234w) {
                this.f228q.pause();
            }
            this.f233v = true;
        }
    }

    public final void seekTo(int i) {
        if (this.f228q != null) {
            this.f228q.seekTo(i);
        }
    }

    public final void start() {
        if (f212a) {
            C0058p.m117b("Start");
        }
        if (this.f228q != null) {
            if (this.f234w) {
                this.f228q.start();
            }
            this.f233v = false;
        }
    }

    public final void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        if (f212a) {
            StringBuilder stringBuilder = new StringBuilder("surfaceChanged called ");
            stringBuilder.append(i);
            stringBuilder.append(" ");
            stringBuilder.append(i2);
            stringBuilder.append("x");
            stringBuilder.append(i3);
            C0058p.m117b(stringBuilder.toString());
        }
        if (this.f224m != i2 || this.f225n != i3) {
            this.f224m = i2;
            this.f225n = i3;
            if (this.f234w) {
                updateVideoLayout();
            }
        }
    }

    public final void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (f212a) {
            C0058p.m117b("surfaceCreated called");
        }
        this.f234w = true;
        m119c();
    }

    public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        if (f212a) {
            C0058p.m117b("surfaceDestroyed called");
        }
        this.f234w = false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected final void updateVideoLayout() {
        /*
        r6 = this;
        r0 = f212a;
        if (r0 == 0) goto L_0x0009;
    L_0x0004:
        r0 = "updateVideoLayout";
        com.unity3d.player.C0058p.m117b(r0);
    L_0x0009:
        r0 = r6.f228q;
        if (r0 != 0) goto L_0x000e;
    L_0x000d:
        return;
    L_0x000e:
        r0 = r6.f224m;
        if (r0 == 0) goto L_0x0016;
    L_0x0012:
        r0 = r6.f225n;
        if (r0 != 0) goto L_0x0034;
    L_0x0016:
        r0 = r6.f213b;
        r1 = "window";
        r0 = r0.getSystemService(r1);
        r0 = (android.view.WindowManager) r0;
        r1 = new android.util.DisplayMetrics;
        r1.<init>();
        r0 = r0.getDefaultDisplay();
        r0.getMetrics(r1);
        r0 = r1.widthPixels;
        r6.f224m = r0;
        r0 = r1.heightPixels;
        r6.f225n = r0;
    L_0x0034:
        r0 = r6.f224m;
        r1 = r6.f225n;
        r2 = r6.f230s;
        if (r2 == 0) goto L_0x0073;
    L_0x003c:
        r2 = r6.f226o;
        r2 = (float) r2;
        r3 = r6.f227p;
        r3 = (float) r3;
        r2 = r2 / r3;
        r3 = r6.f224m;
        r3 = (float) r3;
        r4 = r6.f225n;
        r4 = (float) r4;
        r3 = r3 / r4;
        r4 = r6.f218g;
        r5 = 1;
        if (r4 != r5) goto L_0x0060;
    L_0x004f:
        r3 = (r3 > r2 ? 1 : (r3 == r2 ? 0 : -1));
        if (r3 > 0) goto L_0x0059;
    L_0x0053:
        r1 = r6.f224m;
        r1 = (float) r1;
        r1 = r1 / r2;
        r1 = (int) r1;
        goto L_0x007c;
    L_0x0059:
        r0 = r6.f225n;
        r0 = (float) r0;
        r0 = r0 * r2;
        r0 = (int) r0;
        goto L_0x007c;
    L_0x0060:
        r4 = r6.f218g;
        r5 = 2;
        if (r4 != r5) goto L_0x006a;
    L_0x0065:
        r3 = (r3 > r2 ? 1 : (r3 == r2 ? 0 : -1));
        if (r3 < 0) goto L_0x0059;
    L_0x0069:
        goto L_0x0053;
    L_0x006a:
        r2 = r6.f218g;
        if (r2 != 0) goto L_0x007c;
    L_0x006e:
        r0 = r6.f226o;
        r1 = r6.f227p;
        goto L_0x007c;
    L_0x0073:
        r2 = f212a;
        if (r2 == 0) goto L_0x007c;
    L_0x0077:
        r2 = "updateVideoLayout: Video size is not known yet";
        com.unity3d.player.C0058p.m117b(r2);
    L_0x007c:
        r2 = r6.f224m;
        if (r2 != r0) goto L_0x0084;
    L_0x0080:
        r2 = r6.f225n;
        if (r2 == r1) goto L_0x00af;
    L_0x0084:
        r2 = f212a;
        if (r2 == 0) goto L_0x00a1;
    L_0x0088:
        r2 = new java.lang.StringBuilder;
        r3 = "frameWidth = ";
        r2.<init>(r3);
        r2.append(r0);
        r3 = "; frameHeight = ";
        r2.append(r3);
        r2.append(r1);
        r2 = r2.toString();
        com.unity3d.player.C0058p.m117b(r2);
    L_0x00a1:
        r2 = new android.widget.FrameLayout$LayoutParams;
        r3 = 17;
        r2.<init>(r0, r1, r3);
        r0 = r6.f222k;
        r1 = r6.f214c;
        r0.updateViewLayout(r1, r2);
    L_0x00af:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.p.updateVideoLayout():void");
    }
}
