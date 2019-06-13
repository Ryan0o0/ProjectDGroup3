package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/* renamed from: com.unity3d.player.c */
class C0038c {
    /* renamed from: a */
    protected C0055o f169a = null;
    /* renamed from: b */
    protected C0041f f170b = null;
    /* renamed from: c */
    protected Context f171c = null;
    /* renamed from: d */
    protected String f172d = null;
    /* renamed from: e */
    protected String f173e = "";

    C0038c(String str, C0041f c0041f) {
        this.f173e = str;
        this.f170b = c0041f;
    }

    protected void reportError(String str) {
        if (this.f170b != null) {
            C0041f c0041f = this.f170b;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.f173e);
            stringBuilder.append(" Error [");
            stringBuilder.append(this.f172d);
            stringBuilder.append("]");
            c0041f.reportError(stringBuilder.toString(), str);
            return;
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append(this.f173e);
        stringBuilder.append(" Error [");
        stringBuilder.append(this.f172d);
        stringBuilder.append("]: ");
        stringBuilder.append(str);
        C0042g.Log(6, stringBuilder.toString());
    }

    protected void runOnUiThread(Runnable runnable) {
        if (this.f171c instanceof Activity) {
            ((Activity) this.f171c).runOnUiThread(runnable);
            return;
        }
        StringBuilder stringBuilder = new StringBuilder("Not running ");
        stringBuilder.append(this.f173e);
        stringBuilder.append(" from an Activity; Ignoring execution request...");
        C0042g.Log(5, stringBuilder.toString());
    }

    protected boolean runOnUiThreadWithSync(final Runnable runnable) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            runnable.run();
            return true;
        }
        boolean z = false;
        final Semaphore semaphore = new Semaphore(0);
        runOnUiThread(new Runnable(this) {
            /* renamed from: c */
            final /* synthetic */ C0038c f168c;

            public final void run() {
                try {
                    runnable.run();
                } catch (Exception e) {
                    C0038c c0038c = this.f168c;
                    StringBuilder stringBuilder = new StringBuilder("Exception unloading Google VR on UI Thread. ");
                    stringBuilder.append(e.getLocalizedMessage());
                    c0038c.reportError(stringBuilder.toString());
                } catch (Throwable th) {
                    semaphore.release();
                }
                semaphore.release();
            }
        });
        try {
            if (semaphore.tryAcquire(4, TimeUnit.SECONDS)) {
                z = true;
            } else {
                reportError("Timeout waiting for vr state change!");
            }
        } catch (InterruptedException e) {
            StringBuilder stringBuilder = new StringBuilder("Interrupted while trying to acquire sync lock. ");
            stringBuilder.append(e.getLocalizedMessage());
            reportError(stringBuilder.toString());
        }
        return z;
    }
}
