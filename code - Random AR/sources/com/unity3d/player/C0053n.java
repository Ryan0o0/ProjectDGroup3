package com.unity3d.player;

/* renamed from: com.unity3d.player.n */
final class C0053n {
    /* renamed from: a */
    private static boolean f198a = false;
    /* renamed from: b */
    private boolean f199b = false;
    /* renamed from: c */
    private boolean f200c = false;
    /* renamed from: d */
    private boolean f201d = true;
    /* renamed from: e */
    private boolean f202e = false;

    C0053n() {
    }

    /* renamed from: a */
    static void m99a() {
        f198a = true;
    }

    /* renamed from: b */
    static void m100b() {
        f198a = false;
    }

    /* renamed from: c */
    static boolean m101c() {
        return f198a;
    }

    /* renamed from: a */
    final void m102a(boolean z) {
        this.f199b = z;
    }

    /* renamed from: b */
    final void m103b(boolean z) {
        this.f201d = z;
    }

    /* renamed from: c */
    final void m104c(boolean z) {
        this.f202e = z;
    }

    /* renamed from: d */
    final void m105d(boolean z) {
        this.f200c = z;
    }

    /* renamed from: d */
    final boolean m106d() {
        return this.f201d;
    }

    /* renamed from: e */
    final boolean m107e() {
        return this.f202e;
    }

    /* renamed from: f */
    final boolean m108f() {
        return f198a && this.f199b && !this.f201d && !this.f200c;
    }

    /* renamed from: g */
    final boolean m109g() {
        return this.f200c;
    }

    public final String toString() {
        return super.toString();
    }
}
