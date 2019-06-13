package org.fmod;

import android.media.AudioRecord;
import android.util.Log;
import java.nio.ByteBuffer;

/* renamed from: org.fmod.a */
final class C0077a implements Runnable {
    /* renamed from: a */
    private final FMODAudioDevice f270a;
    /* renamed from: b */
    private final ByteBuffer f271b;
    /* renamed from: c */
    private final int f272c;
    /* renamed from: d */
    private final int f273d;
    /* renamed from: e */
    private final int f274e = 2;
    /* renamed from: f */
    private volatile Thread f275f;
    /* renamed from: g */
    private volatile boolean f276g;
    /* renamed from: h */
    private AudioRecord f277h;
    /* renamed from: i */
    private boolean f278i;

    C0077a(FMODAudioDevice fMODAudioDevice, int i, int i2) {
        this.f270a = fMODAudioDevice;
        this.f272c = i;
        this.f273d = i2;
        this.f271b = ByteBuffer.allocateDirect(AudioRecord.getMinBufferSize(i, i2, 2));
    }

    /* renamed from: d */
    private void m138d() {
        if (this.f277h != null) {
            if (this.f277h.getState() == 1) {
                this.f277h.stop();
            }
            this.f277h.release();
            this.f277h = null;
        }
        this.f271b.position(0);
        this.f278i = false;
    }

    /* renamed from: a */
    public final int m139a() {
        return this.f271b.capacity();
    }

    /* renamed from: b */
    public final void m140b() {
        if (this.f275f != null) {
            m141c();
        }
        this.f276g = true;
        this.f275f = new Thread(this);
        this.f275f.start();
    }

    /* renamed from: c */
    public final void m141c() {
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
        r1 = this;
    L_0x0000:
        r0 = r1.f275f;
        if (r0 == 0) goto L_0x0010;
    L_0x0004:
        r0 = 0;
        r1.f276g = r0;
        r0 = r1.f275f;	 Catch:{ InterruptedException -> 0x0000 }
        r0.join();	 Catch:{ InterruptedException -> 0x0000 }
        r0 = 0;	 Catch:{ InterruptedException -> 0x0000 }
        r1.f275f = r0;	 Catch:{ InterruptedException -> 0x0000 }
        goto L_0x0000;
    L_0x0010:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.fmod.a.c():void");
    }

    public final void run() {
        int i = 3;
        while (this.f276g) {
            if (!this.f278i && i > 0) {
                m138d();
                this.f277h = new AudioRecord(1, this.f272c, this.f273d, this.f274e, this.f271b.capacity());
                boolean z = true;
                if (this.f277h.getState() != 1) {
                    z = false;
                }
                this.f278i = z;
                if (this.f278i) {
                    this.f271b.position(0);
                    this.f277h.startRecording();
                    i = 3;
                } else {
                    StringBuilder stringBuilder = new StringBuilder("AudioRecord failed to initialize (status ");
                    stringBuilder.append(this.f277h.getState());
                    stringBuilder.append(")");
                    Log.e("FMOD", stringBuilder.toString());
                    i--;
                    m138d();
                }
            }
            if (this.f278i && this.f277h.getRecordingState() == 3) {
                this.f270a.fmodProcessMicData(this.f271b, this.f277h.read(this.f271b, this.f271b.capacity()));
                this.f271b.position(0);
            }
        }
        m138d();
    }
}
