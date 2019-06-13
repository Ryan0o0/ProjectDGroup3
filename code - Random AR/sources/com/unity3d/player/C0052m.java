package com.unity3d.player;

import java.lang.Thread.UncaughtExceptionHandler;

/* renamed from: com.unity3d.player.m */
final class C0052m implements UncaughtExceptionHandler {
    /* renamed from: a */
    private volatile UncaughtExceptionHandler f197a;

    C0052m() {
    }

    /* renamed from: a */
    final synchronized boolean m98a() {
        boolean z;
        C0052m defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (defaultUncaughtExceptionHandler == this) {
            z = false;
        } else {
            this.f197a = defaultUncaughtExceptionHandler;
            Thread.setDefaultUncaughtExceptionHandler(this);
            z = true;
        }
        return z;
    }

    public final synchronized void uncaughtException(java.lang.Thread r8, java.lang.Throwable r9) {
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
        r7 = this;
        monitor-enter(r7);
        r0 = new java.lang.Error;	 Catch:{ Throwable -> 0x0065 }
        r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0065 }
        r1.<init>();	 Catch:{ Throwable -> 0x0065 }
        r2 = "FATAL EXCEPTION [%s]\n";	 Catch:{ Throwable -> 0x0065 }
        r3 = 1;	 Catch:{ Throwable -> 0x0065 }
        r4 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x0065 }
        r5 = r8.getName();	 Catch:{ Throwable -> 0x0065 }
        r6 = 0;	 Catch:{ Throwable -> 0x0065 }
        r4[r6] = r5;	 Catch:{ Throwable -> 0x0065 }
        r2 = java.lang.String.format(r2, r4);	 Catch:{ Throwable -> 0x0065 }
        r1.append(r2);	 Catch:{ Throwable -> 0x0065 }
        r2 = "Unity version     : %s\n";	 Catch:{ Throwable -> 0x0065 }
        r4 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x0065 }
        r5 = "2019.1.1f1";	 Catch:{ Throwable -> 0x0065 }
        r4[r6] = r5;	 Catch:{ Throwable -> 0x0065 }
        r2 = java.lang.String.format(r2, r4);	 Catch:{ Throwable -> 0x0065 }
        r1.append(r2);	 Catch:{ Throwable -> 0x0065 }
        r2 = "Device model      : %s %s\n";	 Catch:{ Throwable -> 0x0065 }
        r4 = 2;	 Catch:{ Throwable -> 0x0065 }
        r4 = new java.lang.Object[r4];	 Catch:{ Throwable -> 0x0065 }
        r5 = android.os.Build.MANUFACTURER;	 Catch:{ Throwable -> 0x0065 }
        r4[r6] = r5;	 Catch:{ Throwable -> 0x0065 }
        r5 = android.os.Build.MODEL;	 Catch:{ Throwable -> 0x0065 }
        r4[r3] = r5;	 Catch:{ Throwable -> 0x0065 }
        r2 = java.lang.String.format(r2, r4);	 Catch:{ Throwable -> 0x0065 }
        r1.append(r2);	 Catch:{ Throwable -> 0x0065 }
        r2 = "Device fingerprint: %s\n";	 Catch:{ Throwable -> 0x0065 }
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x0065 }
        r4 = android.os.Build.FINGERPRINT;	 Catch:{ Throwable -> 0x0065 }
        r3[r6] = r4;	 Catch:{ Throwable -> 0x0065 }
        r2 = java.lang.String.format(r2, r3);	 Catch:{ Throwable -> 0x0065 }
        r1.append(r2);	 Catch:{ Throwable -> 0x0065 }
        r1 = r1.toString();	 Catch:{ Throwable -> 0x0065 }
        r0.<init>(r1);	 Catch:{ Throwable -> 0x0065 }
        r1 = new java.lang.StackTraceElement[r6];	 Catch:{ Throwable -> 0x0065 }
        r0.setStackTrace(r1);	 Catch:{ Throwable -> 0x0065 }
        r0.initCause(r9);	 Catch:{ Throwable -> 0x0065 }
        r1 = r7.f197a;	 Catch:{ Throwable -> 0x0065 }
        r1.uncaughtException(r8, r0);	 Catch:{ Throwable -> 0x0065 }
        monitor-exit(r7);
        return;
    L_0x0063:
        r8 = move-exception;
        goto L_0x006c;
    L_0x0065:
        r0 = r7.f197a;	 Catch:{ all -> 0x0063 }
        r0.uncaughtException(r8, r9);	 Catch:{ all -> 0x0063 }
        monitor-exit(r7);
        return;
    L_0x006c:
        monitor-exit(r7);
        throw r8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.m.uncaughtException(java.lang.Thread, java.lang.Throwable):void");
    }
}
