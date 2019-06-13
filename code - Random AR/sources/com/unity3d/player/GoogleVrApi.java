package com.unity3d.player;

import java.util.concurrent.atomic.AtomicReference;

public class GoogleVrApi {
    /* renamed from: a */
    private static AtomicReference f3a = new AtomicReference();

    private GoogleVrApi() {
    }

    /* renamed from: a */
    static void m2a() {
        f3a.set(null);
    }

    /* renamed from: a */
    static void m3a(C0041f c0041f) {
        f3a.compareAndSet(null, new GoogleVrProxy(c0041f));
    }

    /* renamed from: b */
    static GoogleVrProxy m4b() {
        return (GoogleVrProxy) f3a.get();
    }

    public static GoogleVrVideo getGoogleVrVideo() {
        return (GoogleVrVideo) f3a.get();
    }
}
