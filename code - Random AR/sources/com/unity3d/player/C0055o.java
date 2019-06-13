package com.unity3d.player;

import java.lang.reflect.Method;
import java.util.HashMap;

/* renamed from: com.unity3d.player.o */
final class C0055o {
    /* renamed from: a */
    private HashMap f206a = new HashMap();
    /* renamed from: b */
    private Class f207b = null;
    /* renamed from: c */
    private Object f208c = null;

    /* renamed from: com.unity3d.player.o$a */
    class C0054a {
        /* renamed from: a */
        public Class[] f203a;
        /* renamed from: b */
        public Method f204b = null;
        /* renamed from: c */
        final /* synthetic */ C0055o f205c;

        public C0054a(C0055o c0055o, Class[] clsArr) {
            this.f205c = c0055o;
            this.f203a = clsArr;
        }
    }

    public C0055o(Class cls, Object obj) {
        this.f207b = cls;
        this.f208c = obj;
    }

    /* renamed from: a */
    private void m110a(String str, C0054a c0054a) {
        try {
            c0054a.f204b = this.f207b.getMethod(str, c0054a.f203a);
        } catch (Exception e) {
            StringBuilder stringBuilder = new StringBuilder("Exception while trying to get method ");
            stringBuilder.append(str);
            stringBuilder.append(". ");
            stringBuilder.append(e.getLocalizedMessage());
            C0042g.Log(6, stringBuilder.toString());
            c0054a.f204b = null;
        }
    }

    /* renamed from: a */
    public final Object m111a(String str, Object... objArr) {
        StringBuilder stringBuilder;
        if (this.f206a.containsKey(str)) {
            C0054a c0054a = (C0054a) this.f206a.get(str);
            if (c0054a.f204b == null) {
                m110a(str, c0054a);
            }
            if (c0054a.f204b == null) {
                stringBuilder = new StringBuilder("Unable to create method: ");
            } else {
                Object invoke;
                try {
                    invoke = objArr.length == 0 ? c0054a.f204b.invoke(this.f208c, new Object[0]) : c0054a.f204b.invoke(this.f208c, objArr);
                } catch (Exception e) {
                    StringBuilder stringBuilder2 = new StringBuilder("Error trying to call delegated method ");
                    stringBuilder2.append(str);
                    stringBuilder2.append(". ");
                    stringBuilder2.append(e.getLocalizedMessage());
                    C0042g.Log(6, stringBuilder2.toString());
                    invoke = null;
                }
                return invoke;
            }
        }
        stringBuilder = new StringBuilder("No definition for method ");
        stringBuilder.append(str);
        str = " can be found";
        stringBuilder.append(str);
        C0042g.Log(6, stringBuilder.toString());
        return null;
    }

    /* renamed from: a */
    public final void m112a(String str, Class[] clsArr) {
        this.f206a.put(str, new C0054a(this, clsArr));
    }
}
