package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* renamed from: com.unity3d.player.q */
final class C0065q {
    /* renamed from: a */
    private UnityPlayer f250a = null;
    /* renamed from: b */
    private Context f251b = null;
    /* renamed from: c */
    private C0064a f252c;
    /* renamed from: d */
    private final Semaphore f253d = new Semaphore(0);
    /* renamed from: e */
    private final Lock f254e = new ReentrantLock();
    /* renamed from: f */
    private C0058p f255f = null;
    /* renamed from: g */
    private int f256g = 2;
    /* renamed from: h */
    private boolean f257h = false;
    /* renamed from: i */
    private boolean f258i = false;

    /* renamed from: com.unity3d.player.q$2 */
    class C00612 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C0065q f247a;

        C00612(C0065q c0065q) {
            this.f247a = c0065q;
        }

        public final void run() {
            this.f247a.f250a.pause();
        }
    }

    /* renamed from: com.unity3d.player.q$3 */
    class C00623 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C0065q f248a;

        C00623(C0065q c0065q) {
            this.f248a = c0065q;
        }

        public final void run() {
            if (this.f248a.f255f != null) {
                this.f248a.f250a.addViewToPlayer(this.f248a.f255f, true);
                this.f248a.f258i = true;
                this.f248a.f255f.requestFocus();
            }
        }
    }

    /* renamed from: com.unity3d.player.q$4 */
    class C00634 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C0065q f249a;

        C00634(C0065q c0065q) {
            this.f249a = c0065q;
        }

        public final void run() {
            this.f249a.m129d();
            this.f249a.f250a.resume();
        }
    }

    /* renamed from: com.unity3d.player.q$a */
    public interface C0064a {
        /* renamed from: a */
        void mo7a();
    }

    C0065q(UnityPlayer unityPlayer) {
        this.f250a = unityPlayer;
    }

    /* renamed from: d */
    private void m129d() {
        if (this.f255f != null) {
            this.f250a.removeViewFromPlayer(this.f255f);
            this.f258i = false;
            this.f255f.destroyPlayer();
            this.f255f = null;
            if (this.f252c != null) {
                this.f252c.mo7a();
            }
        }
    }

    /* renamed from: a */
    public final void m134a() {
        this.f254e.lock();
        if (this.f255f != null) {
            if (this.f256g == 0) {
                this.f255f.CancelOnPrepare();
            } else if (this.f258i) {
                this.f257h = this.f255f.m121a();
                if (!this.f257h) {
                    this.f255f.pause();
                }
            }
        }
        this.f254e.unlock();
    }

    /* renamed from: a */
    public final boolean m135a(android.content.Context r15, java.lang.String r16, int r17, int r18, int r19, boolean r20, long r21, long r23, com.unity3d.player.C0065q.C0064a r25) {
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
        r14 = this;
        r11 = r14;
        r0 = r11.f254e;
        r0.lock();
        r0 = r25;
        r11.f252c = r0;
        r0 = r15;
        r11.f251b = r0;
        r0 = r11.f253d;
        r0.drainPermits();
        r12 = 2;
        r11.f256g = r12;
        r13 = new com.unity3d.player.q$1;
        r0 = r13;
        r1 = r14;
        r2 = r16;
        r3 = r17;
        r4 = r18;
        r5 = r19;
        r6 = r20;
        r7 = r21;
        r9 = r23;
        r0.<init>(r1, r2, r3, r4, r5, r6, r7, r9);
        r14.runOnUiThread(r13);
        r0 = 0;
        r1 = r11.f254e;	 Catch:{ InterruptedException -> 0x0042 }
        r1.unlock();	 Catch:{ InterruptedException -> 0x0042 }
        r1 = r11.f253d;	 Catch:{ InterruptedException -> 0x0042 }
        r1.acquire();	 Catch:{ InterruptedException -> 0x0042 }
        r1 = r11.f254e;	 Catch:{ InterruptedException -> 0x0042 }
        r1.lock();	 Catch:{ InterruptedException -> 0x0042 }
        r1 = r11.f256g;	 Catch:{ InterruptedException -> 0x0042 }
        if (r1 == r12) goto L_0x0042;
    L_0x0041:
        r0 = 1;
    L_0x0042:
        r1 = new com.unity3d.player.q$2;
        r1.<init>(r14);
        r14.runOnUiThread(r1);
        if (r0 == 0) goto L_0x0057;
    L_0x004c:
        r1 = r11.f256g;
        r2 = 3;
        if (r1 == r2) goto L_0x0057;
    L_0x0051:
        r1 = new com.unity3d.player.q$3;
        r1.<init>(r14);
        goto L_0x005c;
    L_0x0057:
        r1 = new com.unity3d.player.q$4;
        r1.<init>(r14);
    L_0x005c:
        r14.runOnUiThread(r1);
        r1 = r11.f254e;
        r1.unlock();
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.q.a(android.content.Context, java.lang.String, int, int, int, boolean, long, long, com.unity3d.player.q$a):boolean");
    }

    /* renamed from: b */
    public final void m136b() {
        this.f254e.lock();
        if (!(this.f255f == null || !this.f258i || this.f257h)) {
            this.f255f.start();
        }
        this.f254e.unlock();
    }

    /* renamed from: c */
    public final void m137c() {
        this.f254e.lock();
        if (this.f255f != null) {
            this.f255f.updateVideoLayout();
        }
        this.f254e.unlock();
    }

    protected final void runOnUiThread(Runnable runnable) {
        if (this.f251b instanceof Activity) {
            ((Activity) this.f251b).runOnUiThread(runnable);
        } else {
            C0042g.Log(5, "Not running from an Activity; Ignoring execution request...");
        }
    }
}
