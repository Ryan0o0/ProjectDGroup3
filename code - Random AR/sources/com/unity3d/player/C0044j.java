package com.unity3d.player;

import android.os.Build.VERSION;

/* renamed from: com.unity3d.player.j */
public final class C0044j {
    /* renamed from: a */
    static final boolean f175a = (VERSION.SDK_INT >= 19);
    /* renamed from: b */
    static final boolean f176b = (VERSION.SDK_INT >= 21);
    /* renamed from: c */
    static final boolean f177c;
    /* renamed from: d */
    static final C0040e f178d;

    static {
        boolean z = false;
        if (VERSION.SDK_INT >= 23) {
            z = true;
        }
        f177c = z;
        f178d = z ? new C0080h() : null;
    }
}
