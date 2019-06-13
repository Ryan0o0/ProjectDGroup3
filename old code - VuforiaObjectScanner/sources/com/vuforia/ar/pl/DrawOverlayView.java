package com.vuforia.ar.pl;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import java.nio.ByteBuffer;

public class DrawOverlayView extends View {
    private static final String MODULENAME = "DrawOverlayView";
    private Drawable drawable = null;
    private double mLeft;
    private float[] mScale;
    private int[] mSize;
    private double mTop;
    private DisplayMetrics metrics;
    private Bitmap overlayBitmap;

    public DrawOverlayView(Context context) {
        super(context);
    }

    public DrawOverlayView(Context context, byte[] bArr, int i, int i2, float[] fArr, int[] iArr) {
        super(context);
        this.mLeft = (double) i;
        this.mTop = (double) i2;
        this.mScale = fArr;
        this.mSize = iArr;
        context = new byte[(bArr.length * 4)];
        for (i2 = 0; i2 < this.mSize[0] * this.mSize[1]; i2++) {
            fArr = i2 * 4;
            context[fArr] = bArr[i2];
            context[fArr + 1] = bArr[i2];
            context[fArr + 2] = bArr[i2];
            context[fArr + 3] = -1;
        }
        this.overlayBitmap = Bitmap.createBitmap(this.mSize[0], this.mSize[1], Config.ARGB_8888);
        this.overlayBitmap.copyPixelsFromBuffer(ByteBuffer.wrap(context));
        this.drawable = new BitmapDrawable(this.overlayBitmap);
        this.metrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(this.metrics);
    }

    protected void onDraw(Canvas canvas) {
        if (this.overlayBitmap == null) {
            super.dispatchDraw(canvas);
            return;
        }
        double d = (double) this.metrics.heightPixels;
        double intrinsicHeight = (double) (((float) this.drawable.getIntrinsicHeight()) * this.mScale[1]);
        Double.isNaN(d);
        Double.isNaN(intrinsicHeight);
        d -= intrinsicHeight;
        if (d < this.mTop) {
            this.mTop = d;
        }
        d = this.mLeft;
        intrinsicHeight = (double) ((((float) this.drawable.getIntrinsicWidth()) * this.metrics.density) * this.mScale[0]);
        Double.isNaN(intrinsicHeight);
        int i = (int) (d + intrinsicHeight);
        double d2 = this.mTop;
        double intrinsicHeight2 = (double) ((((float) this.drawable.getIntrinsicHeight()) * this.metrics.density) * this.mScale[1]);
        Double.isNaN(intrinsicHeight2);
        this.drawable.setBounds((int) this.mLeft, (int) this.mTop, i, (int) (d2 + intrinsicHeight2));
        this.drawable.setAlpha(100);
        this.drawable.draw(canvas);
        super.dispatchDraw(canvas);
    }

    public void addOverlay(Activity activity) {
        ((ViewGroup) activity.getWindow().getDecorView()).addView(this);
        setVisibility(null);
    }

    public void removeOverlay(android.app.Activity r1, android.view.View r2) {
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
        r1 = r1.getWindow();	 Catch:{ Exception -> 0x000d }
        r1 = r1.getDecorView();	 Catch:{ Exception -> 0x000d }
        r1 = (android.view.ViewGroup) r1;	 Catch:{ Exception -> 0x000d }
        r1.removeView(r2);	 Catch:{ Exception -> 0x000d }
    L_0x000d:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.ar.pl.DrawOverlayView.removeOverlay(android.app.Activity, android.view.View):void");
    }
}
