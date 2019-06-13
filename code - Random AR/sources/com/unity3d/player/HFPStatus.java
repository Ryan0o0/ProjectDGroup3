package com.unity3d.player;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;

public class HFPStatus {
    /* renamed from: a */
    private Context f27a;
    /* renamed from: b */
    private BroadcastReceiver f28b = null;
    /* renamed from: c */
    private Intent f29c = null;
    /* renamed from: d */
    private boolean f30d = false;
    /* renamed from: e */
    private AudioManager f31e = null;
    /* renamed from: f */
    private int f32f = C0008a.f23a;

    /* renamed from: com.unity3d.player.HFPStatus$1 */
    class C00071 extends BroadcastReceiver {
        /* renamed from: a */
        final /* synthetic */ HFPStatus f22a;

        C00071(HFPStatus hFPStatus) {
            this.f22a = hFPStatus;
        }

        public void onReceive(Context context, Intent intent) {
            switch (intent.getIntExtra("android.media.extra.SCO_AUDIO_STATE", -1)) {
                case -1:
                    break;
                case 0:
                    if (this.f22a.f30d) {
                        this.f22a.f31e.setMode(0);
                    }
                    this.f22a.f30d = false;
                    break;
                case 1:
                    this.f22a.f32f = C0008a.f24b;
                    if (this.f22a.f30d) {
                        this.f22a.f31e.setMode(3);
                        return;
                    } else {
                        this.f22a.f31e.stopBluetoothSco();
                        return;
                    }
                case 2:
                    if (this.f22a.f32f != C0008a.f24b) {
                        this.f22a.f32f = C0008a.f25c;
                        break;
                    } else {
                        this.f22a.f30d = true;
                        return;
                    }
                default:
                    break;
            }
        }
    }

    /* renamed from: com.unity3d.player.HFPStatus$a */
    enum C0008a {
        ;

        static {
            f26d = new int[]{f23a, f24b, f25c};
        }
    }

    public HFPStatus(Context context) {
        this.f27a = context;
        this.f31e = (AudioManager) this.f27a.getSystemService("audio");
        initHFPStatusJni();
    }

    private final native void deinitHFPStatusJni();

    private final native void initHFPStatusJni();

    /* renamed from: a */
    public final void m12a() {
        deinitHFPStatusJni();
    }

    protected boolean getHFPStat() {
        return this.f32f == C0008a.f24b;
    }

    protected void requestHFPStat() {
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
        r4 = this;
        r0 = new com.unity3d.player.HFPStatus$1;
        r0.<init>(r4);
        r4.f28b = r0;
        r0 = r4.f27a;
        r1 = r4.f28b;
        r2 = new android.content.IntentFilter;
        r3 = "android.media.ACTION_SCO_AUDIO_STATE_UPDATED";
        r2.<init>(r3);
        r0 = r0.registerReceiver(r1, r2);
        r4.f29c = r0;
        r0 = r4.f31e;	 Catch:{ NullPointerException -> 0x001e }
        r0.startBluetoothSco();	 Catch:{ NullPointerException -> 0x001e }
        return;
    L_0x001e:
        r0 = 5;
        r1 = "startBluetoothSco() failed. no bluetooth device connected.";
        com.unity3d.player.C0042g.Log(r0, r1);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.HFPStatus.requestHFPStat():void");
    }
}
