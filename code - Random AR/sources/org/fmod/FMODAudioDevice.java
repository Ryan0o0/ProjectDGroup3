package org.fmod;

import android.media.AudioTrack;
import android.util.Log;
import java.nio.ByteBuffer;

public class FMODAudioDevice implements Runnable {
    /* renamed from: h */
    private static int f259h = 0;
    /* renamed from: i */
    private static int f260i = 1;
    /* renamed from: j */
    private static int f261j = 2;
    /* renamed from: k */
    private static int f262k = 3;
    /* renamed from: a */
    private volatile Thread f263a = null;
    /* renamed from: b */
    private volatile boolean f264b = false;
    /* renamed from: c */
    private AudioTrack f265c = null;
    /* renamed from: d */
    private boolean f266d = false;
    /* renamed from: e */
    private ByteBuffer f267e = null;
    /* renamed from: f */
    private byte[] f268f = null;
    /* renamed from: g */
    private volatile C0077a f269g;

    private native int fmodGetInfo(int i);

    private native int fmodProcess(ByteBuffer byteBuffer);

    private void releaseAudioTrack() {
        if (this.f265c != null) {
            if (this.f265c.getState() == 1) {
                this.f265c.stop();
            }
            this.f265c.release();
            this.f265c = null;
        }
        this.f267e = null;
        this.f268f = null;
        this.f266d = false;
    }

    public synchronized void close() {
        stop();
    }

    native int fmodProcessMicData(ByteBuffer byteBuffer, int i);

    public boolean isRunning() {
        return this.f263a != null && this.f263a.isAlive();
    }

    public void run() {
        int i = 3;
        while (this.f264b) {
            if (!this.f266d && i > 0) {
                releaseAudioTrack();
                int fmodGetInfo = fmodGetInfo(f259h);
                int round = Math.round(((float) AudioTrack.getMinBufferSize(fmodGetInfo, 3, 2)) * 1.1f) & -4;
                int fmodGetInfo2 = fmodGetInfo(f260i);
                int fmodGetInfo3 = (fmodGetInfo(f261j) * fmodGetInfo2) * 4;
                this.f265c = new AudioTrack(3, fmodGetInfo, 3, 2, fmodGetInfo3 > round ? fmodGetInfo3 : round, 1);
                this.f266d = this.f265c.getState() == 1;
                if (this.f266d) {
                    this.f267e = ByteBuffer.allocateDirect((fmodGetInfo2 * 2) * 2);
                    this.f268f = new byte[this.f267e.capacity()];
                    this.f265c.play();
                    i = 3;
                } else {
                    StringBuilder stringBuilder = new StringBuilder("AudioTrack failed to initialize (status ");
                    stringBuilder.append(this.f265c.getState());
                    stringBuilder.append(")");
                    Log.e("FMOD", stringBuilder.toString());
                    releaseAudioTrack();
                    i--;
                }
            }
            if (this.f266d) {
                if (fmodGetInfo(f262k) == 1) {
                    fmodProcess(this.f267e);
                    this.f267e.get(this.f268f, 0, this.f267e.capacity());
                    this.f265c.write(this.f268f, 0, this.f267e.capacity());
                    this.f267e.position(0);
                } else {
                    releaseAudioTrack();
                }
            }
        }
        releaseAudioTrack();
    }

    public synchronized void start() {
        if (this.f263a != null) {
            stop();
        }
        this.f263a = new Thread(this, "FMODAudioDevice");
        this.f263a.setPriority(10);
        this.f264b = true;
        this.f263a.start();
        if (this.f269g != null) {
            this.f269g.m140b();
        }
    }

    public synchronized int startAudioRecord(int i, int i2, int i3) {
        if (this.f269g == null) {
            this.f269g = new C0077a(this, i, i2);
            this.f269g.m140b();
        }
        return this.f269g.m139a();
    }

    public synchronized void stop() {
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
        r1 = this;
        monitor-enter(r1);
    L_0x0001:
        r0 = r1.f263a;	 Catch:{ all -> 0x001c }
        if (r0 == 0) goto L_0x0011;	 Catch:{ all -> 0x001c }
    L_0x0005:
        r0 = 0;	 Catch:{ all -> 0x001c }
        r1.f264b = r0;	 Catch:{ all -> 0x001c }
        r0 = r1.f263a;	 Catch:{ InterruptedException -> 0x0001 }
        r0.join();	 Catch:{ InterruptedException -> 0x0001 }
        r0 = 0;	 Catch:{ InterruptedException -> 0x0001 }
        r1.f263a = r0;	 Catch:{ InterruptedException -> 0x0001 }
        goto L_0x0001;
    L_0x0011:
        r0 = r1.f269g;	 Catch:{ all -> 0x001c }
        if (r0 == 0) goto L_0x001a;	 Catch:{ all -> 0x001c }
    L_0x0015:
        r0 = r1.f269g;	 Catch:{ all -> 0x001c }
        r0.m141c();	 Catch:{ all -> 0x001c }
    L_0x001a:
        monitor-exit(r1);
        return;
    L_0x001c:
        r0 = move-exception;
        monitor-exit(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.fmod.FMODAudioDevice.stop():void");
    }

    public synchronized void stopAudioRecord() {
        if (this.f269g != null) {
            this.f269g.m141c();
            this.f269g = null;
        }
    }
}
