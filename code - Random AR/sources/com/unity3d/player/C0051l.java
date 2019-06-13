package com.unity3d.player;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;

/* renamed from: com.unity3d.player.l */
public final class C0051l extends View {
    /* renamed from: a */
    final int f193a;
    /* renamed from: b */
    final int f194b = getResources().getIdentifier("unity_static_splash", "drawable", getContext().getPackageName());
    /* renamed from: c */
    Bitmap f195c;
    /* renamed from: d */
    Bitmap f196d;

    /* renamed from: com.unity3d.player.l$1 */
    static /* synthetic */ class C00491 {
        /* renamed from: a */
        static final /* synthetic */ int[] f188a = new int[C0050a.m97a().length];

        static {
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
            r0 = com.unity3d.player.C0051l.C0050a.m97a();
            r0 = r0.length;
            r0 = new int[r0];
            f188a = r0;
            r0 = 1;
            r1 = f188a;	 Catch:{ NoSuchFieldError -> 0x0011 }
            r2 = com.unity3d.player.C0051l.C0050a.f189a;	 Catch:{ NoSuchFieldError -> 0x0011 }
            r2 = r2 - r0;	 Catch:{ NoSuchFieldError -> 0x0011 }
            r1[r2] = r0;	 Catch:{ NoSuchFieldError -> 0x0011 }
        L_0x0011:
            r1 = f188a;	 Catch:{ NoSuchFieldError -> 0x0019 }
            r2 = com.unity3d.player.C0051l.C0050a.f190b;	 Catch:{ NoSuchFieldError -> 0x0019 }
            r2 = r2 - r0;	 Catch:{ NoSuchFieldError -> 0x0019 }
            r3 = 2;	 Catch:{ NoSuchFieldError -> 0x0019 }
            r1[r2] = r3;	 Catch:{ NoSuchFieldError -> 0x0019 }
        L_0x0019:
            r1 = f188a;	 Catch:{ NoSuchFieldError -> 0x0021 }
            r2 = com.unity3d.player.C0051l.C0050a.f191c;	 Catch:{ NoSuchFieldError -> 0x0021 }
            r2 = r2 - r0;	 Catch:{ NoSuchFieldError -> 0x0021 }
            r0 = 3;	 Catch:{ NoSuchFieldError -> 0x0021 }
            r1[r2] = r0;	 Catch:{ NoSuchFieldError -> 0x0021 }
        L_0x0021:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.l.1.<clinit>():void");
        }
    }

    /* renamed from: com.unity3d.player.l$a */
    enum C0050a {
        ;

        static {
            f192d = new int[]{f189a, f190b, f191c};
        }

        /* renamed from: a */
        public static int[] m97a() {
            return (int[]) f192d.clone();
        }
    }

    public C0051l(Context context, int i) {
        super(context);
        this.f193a = i;
        if (this.f194b != 0) {
            forceLayout();
        }
    }

    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.f195c != null) {
            this.f195c.recycle();
            this.f195c = null;
        }
        if (this.f196d != null) {
            this.f196d.recycle();
            this.f196d = null;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onLayout(boolean r8, int r9, int r10, int r11, int r12) {
        /*
        r7 = this;
        r8 = r7.f194b;
        if (r8 != 0) goto L_0x0005;
    L_0x0004:
        return;
    L_0x0005:
        r8 = r7.f195c;
        r9 = 0;
        if (r8 != 0) goto L_0x001d;
    L_0x000a:
        r8 = new android.graphics.BitmapFactory$Options;
        r8.<init>();
        r8.inScaled = r9;
        r10 = r7.getResources();
        r11 = r7.f194b;
        r8 = android.graphics.BitmapFactory.decodeResource(r10, r11, r8);
        r7.f195c = r8;
    L_0x001d:
        r8 = r7.f195c;
        r8 = r8.getWidth();
        r10 = r7.f195c;
        r10 = r10.getHeight();
        r11 = r7.getWidth();
        r12 = r7.getHeight();
        if (r11 == 0) goto L_0x00cc;
    L_0x0033:
        if (r12 != 0) goto L_0x0037;
    L_0x0035:
        goto L_0x00cc;
    L_0x0037:
        r0 = (float) r8;
        r1 = (float) r10;
        r0 = r0 / r1;
        r1 = (float) r11;
        r2 = (float) r12;
        r3 = r1 / r2;
        r3 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1));
        r4 = 1;
        if (r3 > 0) goto L_0x0045;
    L_0x0043:
        r3 = 1;
        goto L_0x0046;
    L_0x0045:
        r3 = 0;
    L_0x0046:
        r5 = com.unity3d.player.C0051l.C00491.f188a;
        r6 = r7.f193a;
        r6 = r6 - r4;
        r5 = r5[r6];
        switch(r5) {
            case 1: goto L_0x0061;
            case 2: goto L_0x0051;
            case 3: goto L_0x0051;
            default: goto L_0x0050;
        };
    L_0x0050:
        goto L_0x006c;
    L_0x0051:
        r8 = r7.f193a;
        r10 = com.unity3d.player.C0051l.C0050a.f191c;
        if (r8 != r10) goto L_0x0059;
    L_0x0057:
        r8 = 1;
        goto L_0x005a;
    L_0x0059:
        r8 = 0;
    L_0x005a:
        r8 = r8 ^ r3;
        if (r8 == 0) goto L_0x0068;
    L_0x005d:
        r1 = r1 / r0;
        r10 = (int) r1;
        r8 = r11;
        goto L_0x006c;
    L_0x0061:
        if (r11 >= r8) goto L_0x0066;
    L_0x0063:
        r1 = r1 / r0;
        r10 = (int) r1;
        r8 = r11;
    L_0x0066:
        if (r12 >= r10) goto L_0x006c;
    L_0x0068:
        r2 = r2 * r0;
        r8 = (int) r2;
        r10 = r12;
    L_0x006c:
        r11 = r7.f196d;
        if (r11 == 0) goto L_0x008f;
    L_0x0070:
        r11 = r7.f196d;
        r11 = r11.getWidth();
        if (r11 != r8) goto L_0x0081;
    L_0x0078:
        r11 = r7.f196d;
        r11 = r11.getHeight();
        if (r11 != r10) goto L_0x0081;
    L_0x0080:
        return;
    L_0x0081:
        r11 = r7.f196d;
        r12 = r7.f195c;
        if (r11 == r12) goto L_0x008f;
    L_0x0087:
        r11 = r7.f196d;
        r11.recycle();
        r11 = 0;
        r7.f196d = r11;
    L_0x008f:
        r11 = r7.f195c;
        r8 = android.graphics.Bitmap.createScaledBitmap(r11, r8, r10, r4);
        r7.f196d = r8;
        r8 = r7.f196d;
        r10 = r7.getResources();
        r10 = r10.getDisplayMetrics();
        r10 = r10.densityDpi;
        r8.setDensity(r10);
        r8 = new android.graphics.drawable.ColorDrawable;
        r10 = -16777216; // 0xffffffffff000000 float:-1.7014118E38 double:NaN;
        r8.<init>(r10);
        r10 = new android.graphics.drawable.BitmapDrawable;
        r11 = r7.getResources();
        r12 = r7.f196d;
        r10.<init>(r11, r12);
        r11 = 17;
        r10.setGravity(r11);
        r11 = new android.graphics.drawable.LayerDrawable;
        r12 = 2;
        r12 = new android.graphics.drawable.Drawable[r12];
        r12[r9] = r8;
        r12[r4] = r10;
        r11.<init>(r12);
        r7.setBackground(r11);
    L_0x00cc:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.l.onLayout(boolean, int, int, int, int):void");
    }
}
