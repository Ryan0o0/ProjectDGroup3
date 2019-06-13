package com.unity3d.player;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

/* renamed from: com.unity3d.player.h */
public final class C0080h implements C0040e {
    /* renamed from: a */
    private static boolean m214a(android.content.pm.PackageItemInfo r1) {
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
        r1 = r1.metaData;	 Catch:{ Exception -> 0x0009 }
        r0 = "unityplayer.SkipPermissionsDialog";	 Catch:{ Exception -> 0x0009 }
        r1 = r1.getBoolean(r0);	 Catch:{ Exception -> 0x0009 }
        return r1;
    L_0x0009:
        r1 = 0;
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.h.a(android.content.pm.PackageItemInfo):boolean");
    }

    /* renamed from: a */
    public final void mo12a(Activity activity, String str) {
        if (activity == null) {
            return;
        }
        if (str != null) {
            FragmentManager fragmentManager = activity.getFragmentManager();
            String str2 = "96489";
            if (fragmentManager.findFragmentByTag(str2) == null) {
                Fragment c0043i = new C0043i();
                Bundle bundle = new Bundle();
                bundle.putString("PermissionNames", str);
                c0043i.setArguments(bundle);
                FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
                beginTransaction.add(0, c0043i, str2);
                beginTransaction.commit();
            }
        }
    }

    /* renamed from: a */
    public final boolean mo13a(android.app.Activity r4) {
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
        r3 = this;
        r0 = r4.getPackageManager();	 Catch:{ Exception -> 0x0024 }
        r1 = r4.getComponentName();	 Catch:{ Exception -> 0x0024 }
        r2 = 128; // 0x80 float:1.794E-43 double:6.32E-322;	 Catch:{ Exception -> 0x0024 }
        r1 = r0.getActivityInfo(r1, r2);	 Catch:{ Exception -> 0x0024 }
        r4 = r4.getPackageName();	 Catch:{ Exception -> 0x0024 }
        r4 = r0.getApplicationInfo(r4, r2);	 Catch:{ Exception -> 0x0024 }
        r0 = com.unity3d.player.C0080h.m214a(r1);	 Catch:{ Exception -> 0x0024 }
        if (r0 != 0) goto L_0x0022;	 Catch:{ Exception -> 0x0024 }
    L_0x001c:
        r4 = com.unity3d.player.C0080h.m214a(r4);	 Catch:{ Exception -> 0x0024 }
        if (r4 == 0) goto L_0x0024;
    L_0x0022:
        r4 = 1;
        return r4;
    L_0x0024:
        r4 = 0;
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.h.a(android.app.Activity):boolean");
    }
}
