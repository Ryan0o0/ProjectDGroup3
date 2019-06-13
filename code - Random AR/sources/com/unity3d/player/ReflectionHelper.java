package com.unity3d.player;

import com.vuforia.PIXEL_FORMAT;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Iterator;

final class ReflectionHelper {
    protected static boolean LOG = false;
    protected static final boolean LOGV = false;
    /* renamed from: a */
    private static C0010a[] f41a = new C0010a[4096];
    /* renamed from: b */
    private static long f42b = 0;

    /* renamed from: com.unity3d.player.ReflectionHelper$a */
    private static class C0010a {
        /* renamed from: a */
        public volatile Member f36a;
        /* renamed from: b */
        private final Class f37b;
        /* renamed from: c */
        private final String f38c;
        /* renamed from: d */
        private final String f39d;
        /* renamed from: e */
        private final int f40e = (((((this.f37b.hashCode() + 527) * 31) + this.f38c.hashCode()) * 31) + this.f39d.hashCode());

        C0010a(Class cls, String str, String str2) {
            this.f37b = cls;
            this.f38c = str;
            this.f39d = str2;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof C0010a) {
                C0010a c0010a = (C0010a) obj;
                return this.f40e == c0010a.f40e && this.f39d.equals(c0010a.f39d) && this.f38c.equals(c0010a.f38c) && this.f37b.equals(c0010a.f37b);
            }
        }

        public final int hashCode() {
            return this.f40e;
        }
    }

    ReflectionHelper() {
    }

    /* renamed from: a */
    private static float m14a(java.lang.Class r1, java.lang.Class r2) {
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
        r0 = r1.equals(r2);
        if (r0 == 0) goto L_0x0009;
    L_0x0006:
        r1 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        return r1;
    L_0x0009:
        r0 = r1.isPrimitive();
        if (r0 != 0) goto L_0x0028;
    L_0x000f:
        r0 = r2.isPrimitive();
        if (r0 != 0) goto L_0x0028;
    L_0x0015:
        r0 = r1.asSubclass(r2);	 Catch:{ ClassCastException -> 0x001e }
        if (r0 == 0) goto L_0x001e;
    L_0x001b:
        r1 = 1056964608; // 0x3f000000 float:0.5 double:5.222099017E-315;
        return r1;
    L_0x001e:
        r1 = r2.asSubclass(r1);	 Catch:{ ClassCastException -> 0x0028 }
        if (r1 == 0) goto L_0x0028;
    L_0x0024:
        r1 = 1036831949; // 0x3dcccccd float:0.1 double:5.122630465E-315;
        return r1;
    L_0x0028:
        r1 = 0;
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.ReflectionHelper.a(java.lang.Class, java.lang.Class):float");
    }

    /* renamed from: a */
    private static float m15a(Class cls, Class[] clsArr, Class[] clsArr2) {
        if (clsArr2.length == 0) {
            return 0.1f;
        }
        int i = 0;
        if ((clsArr == null ? 0 : clsArr.length) + 1 != clsArr2.length) {
            return 0.0f;
        }
        float f = 1.0f;
        if (clsArr != null) {
            int i2 = 0;
            while (i < clsArr.length) {
                f *= m14a(clsArr[i], clsArr2[i2]);
                i++;
                i2++;
            }
        }
        return f * m14a(cls, clsArr2[clsArr2.length - 1]);
    }

    /* renamed from: a */
    private static java.lang.Class m17a(java.lang.String r3, int[] r4) {
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
    L_0x0000:
        r0 = 0;
        r1 = r4[r0];
        r2 = r3.length();
        if (r1 >= r2) goto L_0x00a8;
    L_0x0009:
        r1 = r4[r0];
        r2 = r1 + 1;
        r4[r0] = r2;
        r1 = r3.charAt(r1);
        r2 = 40;
        if (r1 == r2) goto L_0x0000;
    L_0x0017:
        r2 = 41;
        if (r1 == r2) goto L_0x0000;
    L_0x001b:
        r2 = 76;
        if (r1 != r2) goto L_0x0041;
    L_0x001f:
        r1 = 59;
        r2 = r4[r0];
        r1 = r3.indexOf(r1, r2);
        r2 = -1;
        if (r1 == r2) goto L_0x00a8;
    L_0x002a:
        r2 = r4[r0];
        r3 = r3.substring(r2, r1);
        r1 = r1 + 1;
        r4[r0] = r1;
        r4 = 47;
        r0 = 46;
        r3 = r3.replace(r4, r0);
        r3 = java.lang.Class.forName(r3);	 Catch:{ ClassNotFoundException -> 0x00a8 }
        return r3;
    L_0x0041:
        r2 = 90;
        if (r1 != r2) goto L_0x0048;
    L_0x0045:
        r3 = java.lang.Boolean.TYPE;
        return r3;
    L_0x0048:
        r2 = 73;
        if (r1 != r2) goto L_0x004f;
    L_0x004c:
        r3 = java.lang.Integer.TYPE;
        return r3;
    L_0x004f:
        r2 = 70;
        if (r1 != r2) goto L_0x0056;
    L_0x0053:
        r3 = java.lang.Float.TYPE;
        return r3;
    L_0x0056:
        r2 = 86;
        if (r1 != r2) goto L_0x005d;
    L_0x005a:
        r3 = java.lang.Void.TYPE;
        return r3;
    L_0x005d:
        r2 = 66;
        if (r1 != r2) goto L_0x0064;
    L_0x0061:
        r3 = java.lang.Byte.TYPE;
        return r3;
    L_0x0064:
        r2 = 67;
        if (r1 != r2) goto L_0x006b;
    L_0x0068:
        r3 = java.lang.Character.TYPE;
        return r3;
    L_0x006b:
        r2 = 83;
        if (r1 != r2) goto L_0x0072;
    L_0x006f:
        r3 = java.lang.Short.TYPE;
        return r3;
    L_0x0072:
        r2 = 74;
        if (r1 != r2) goto L_0x0079;
    L_0x0076:
        r3 = java.lang.Long.TYPE;
        return r3;
    L_0x0079:
        r2 = 68;
        if (r1 != r2) goto L_0x0080;
    L_0x007d:
        r3 = java.lang.Double.TYPE;
        return r3;
    L_0x0080:
        r2 = 91;
        if (r1 != r2) goto L_0x0091;
    L_0x0084:
        r3 = m17a(r3, r4);
        r3 = java.lang.reflect.Array.newInstance(r3, r0);
        r3 = r3.getClass();
        return r3;
    L_0x0091:
        r3 = 5;
        r4 = new java.lang.StringBuilder;
        r0 = "! parseType; ";
        r4.<init>(r0);
        r4.append(r1);
        r0 = " is not known!";
        r4.append(r0);
        r4 = r4.toString();
        com.unity3d.player.C0042g.Log(r3, r4);
    L_0x00a8:
        r3 = 0;
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.ReflectionHelper.a(java.lang.String, int[]):java.lang.Class");
    }

    /* renamed from: a */
    private static void m20a(C0010a c0010a, Member member) {
        c0010a.f36a = member;
        f41a[c0010a.hashCode() & (f41a.length - 1)] = c0010a;
    }

    /* renamed from: a */
    private static boolean m21a(C0010a c0010a) {
        C0010a c0010a2 = f41a[c0010a.hashCode() & (f41a.length - 1)];
        if (!c0010a.equals(c0010a2)) {
            return false;
        }
        c0010a.f36a = c0010a2.f36a;
        return true;
    }

    /* renamed from: a */
    private static Class[] m22a(String str) {
        int[] iArr = new int[1];
        int i = 0;
        iArr[0] = 0;
        ArrayList arrayList = new ArrayList();
        while (iArr[0] < str.length()) {
            Class a = m17a(str, iArr);
            if (a == null) {
                break;
            }
            arrayList.add(a);
        }
        Class[] clsArr = new Class[arrayList.size()];
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            int i2 = i + 1;
            clsArr[i] = (Class) it.next();
            i = i2;
        }
        return clsArr;
    }

    protected static void endUnityLaunch() {
        f42b++;
    }

    protected static Constructor getConstructorID(Class cls, String str) {
        Constructor constructor;
        C0010a c0010a = new C0010a(cls, "", str);
        if (m21a(c0010a)) {
            constructor = (Constructor) c0010a.f36a;
        } else {
            Class[] a = m22a(str);
            float f = 0.0f;
            Member member = null;
            for (Constructor constructor2 : cls.getConstructors()) {
                float a2 = m15a(Void.TYPE, constructor2.getParameterTypes(), a);
                if (a2 > f) {
                    if (a2 == 1.0f) {
                        member = constructor2;
                        break;
                    }
                    member = constructor2;
                    f = a2;
                }
            }
            m20a(c0010a, member);
            constructor = member;
        }
        if (constructor != null) {
            return constructor;
        }
        StringBuilder stringBuilder = new StringBuilder("<init>");
        stringBuilder.append(str);
        stringBuilder.append(" in class ");
        stringBuilder.append(cls.getName());
        throw new NoSuchMethodError(stringBuilder.toString());
    }

    protected static Field getFieldID(Class cls, String str, String str2, boolean z) {
        Field field;
        String str3 = str;
        String str4 = str2;
        boolean z2 = z;
        Class cls2 = cls;
        C0010a c0010a = new C0010a(cls2, str3, str4);
        if (m21a(c0010a)) {
            field = (Field) c0010a.f36a;
        } else {
            Class[] a = m22a(str2);
            float f = 0.0f;
            Member member = null;
            while (cls2 != null) {
                Member member2 = member;
                float f2 = f;
                for (Field field2 : cls2.getDeclaredFields()) {
                    if (z2 == Modifier.isStatic(field2.getModifiers()) && field2.getName().compareTo(str3) == 0) {
                        float a2 = m15a(field2.getType(), null, a);
                        if (a2 > f2) {
                            if (a2 == 1.0f) {
                                member = field2;
                                f = a2;
                                break;
                            }
                            member2 = field2;
                            f2 = a2;
                        } else {
                            continue;
                        }
                    }
                }
                f = f2;
                member = member2;
                if (f == 1.0f || cls2.isPrimitive() || cls2.isInterface() || cls2.equals(Object.class) || cls2.equals(Void.TYPE)) {
                    break;
                }
                cls2 = cls2.getSuperclass();
            }
            m20a(c0010a, member);
            field = member;
        }
        if (field != null) {
            return field;
        }
        Object[] objArr = new Object[4];
        objArr[0] = z2 ? "static" : "non-static";
        objArr[1] = str3;
        objArr[2] = str4;
        objArr[3] = cls2.getName();
        throw new NoSuchFieldError(String.format("no %s field with name='%s' signature='%s' in class L%s;", objArr));
    }

    protected static Method getMethodID(Class cls, String str, String str2, boolean z) {
        Method method;
        C0010a c0010a = new C0010a(cls, str, str2);
        if (m21a(c0010a)) {
            method = (Method) c0010a.f36a;
        } else {
            Class[] a = m22a(str2);
            float f = 0.0f;
            Member member = null;
            while (cls != null) {
                Member member2 = member;
                float f2 = f;
                for (Method method2 : cls.getDeclaredMethods()) {
                    if (z == Modifier.isStatic(method2.getModifiers()) && method2.getName().compareTo(str) == 0) {
                        float a2 = m15a(method2.getReturnType(), method2.getParameterTypes(), a);
                        if (a2 > f2) {
                            if (a2 == 1.0f) {
                                member = method2;
                                f = a2;
                                break;
                            }
                            member2 = method2;
                            f2 = a2;
                        } else {
                            continue;
                        }
                    }
                }
                f = f2;
                member = member2;
                if (f == 1.0f || cls.isPrimitive() || cls.isInterface() || cls.equals(Object.class) || cls.equals(Void.TYPE)) {
                    break;
                }
                cls = cls.getSuperclass();
            }
            m20a(c0010a, member);
            method = member;
        }
        if (method != null) {
            return method;
        }
        Object[] objArr = new Object[4];
        objArr[0] = z ? "static" : "non-static";
        objArr[1] = str;
        objArr[2] = str2;
        objArr[3] = cls.getName();
        throw new NoSuchMethodError(String.format("no %s method with name='%s' signature='%s' in class L%s;", objArr));
    }

    private static native void nativeProxyFinalize(int i);

    private static native Object nativeProxyInvoke(int i, String str, Object[] objArr);

    private static native void nativeProxyLogJNIInvokeException();

    protected static Object newProxyInstance(int i, Class cls) {
        return newProxyInstance(i, new Class[]{cls});
    }

    protected static Object newProxyInstance(final int i, final Class[] clsArr) {
        return Proxy.newProxyInstance(ReflectionHelper.class.getClassLoader(), clsArr, new InvocationHandler() {
            /* renamed from: c */
            private long f35c = ReflectionHelper.f42b;

            /* renamed from: a */
            private static java.lang.Object m13a(java.lang.Object r7, java.lang.reflect.Method r8, java.lang.Object[] r9) {
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
                r0 = 0;
                if (r9 != 0) goto L_0x0005;
            L_0x0003:
                r9 = new java.lang.Object[r0];	 Catch:{ NoClassDefFoundError -> 0x003f }
            L_0x0005:
                r1 = r8.getDeclaringClass();	 Catch:{ NoClassDefFoundError -> 0x003f }
                r2 = java.lang.invoke.MethodHandles.Lookup.class;	 Catch:{ NoClassDefFoundError -> 0x003f }
                r3 = 2;	 Catch:{ NoClassDefFoundError -> 0x003f }
                r4 = new java.lang.Class[r3];	 Catch:{ NoClassDefFoundError -> 0x003f }
                r5 = java.lang.Class.class;	 Catch:{ NoClassDefFoundError -> 0x003f }
                r4[r0] = r5;	 Catch:{ NoClassDefFoundError -> 0x003f }
                r5 = java.lang.Integer.TYPE;	 Catch:{ NoClassDefFoundError -> 0x003f }
                r6 = 1;	 Catch:{ NoClassDefFoundError -> 0x003f }
                r4[r6] = r5;	 Catch:{ NoClassDefFoundError -> 0x003f }
                r2 = r2.getDeclaredConstructor(r4);	 Catch:{ NoClassDefFoundError -> 0x003f }
                r2.setAccessible(r6);	 Catch:{ NoClassDefFoundError -> 0x003f }
                r4 = new java.lang.Object[r3];	 Catch:{ NoClassDefFoundError -> 0x003f }
                r4[r0] = r1;	 Catch:{ NoClassDefFoundError -> 0x003f }
                r3 = java.lang.Integer.valueOf(r3);	 Catch:{ NoClassDefFoundError -> 0x003f }
                r4[r6] = r3;	 Catch:{ NoClassDefFoundError -> 0x003f }
                r2 = r2.newInstance(r4);	 Catch:{ NoClassDefFoundError -> 0x003f }
                r2 = (java.lang.invoke.MethodHandles.Lookup) r2;	 Catch:{ NoClassDefFoundError -> 0x003f }
                r2 = r2.in(r1);	 Catch:{ NoClassDefFoundError -> 0x003f }
                r8 = r2.unreflectSpecial(r8, r1);	 Catch:{ NoClassDefFoundError -> 0x003f }
                r7 = r8.bindTo(r7);	 Catch:{ NoClassDefFoundError -> 0x003f }
                r7 = r7.invokeWithArguments(r9);	 Catch:{ NoClassDefFoundError -> 0x003f }
                return r7;
            L_0x003f:
                r7 = 6;
                r8 = "Java interface default methods are only supported since Android Oreo";
                r9 = new java.lang.Object[r0];
                r8 = java.lang.String.format(r8, r9);
                com.unity3d.player.C0042g.Log(r7, r8);
                com.unity3d.player.ReflectionHelper.nativeProxyLogJNIInvokeException();
                r7 = 0;
                return r7;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.ReflectionHelper.1.a(java.lang.Object, java.lang.reflect.Method, java.lang.Object[]):java.lang.Object");
            }

            protected final void finalize() {
                try {
                    if (this.f35c == ReflectionHelper.f42b) {
                        ReflectionHelper.nativeProxyFinalize(i);
                    }
                    super.finalize();
                } catch (Throwable th) {
                    super.finalize();
                }
            }

            public final Object invoke(Object obj, Method method, Object[] objArr) {
                if (this.f35c != ReflectionHelper.f42b) {
                    C0042g.Log(6, "Scripting proxy object was destroyed, because Unity player was unloaded.");
                    return null;
                }
                Object a = ReflectionHelper.nativeProxyInvoke(i, method.getName(), objArr);
                if (a == null) {
                    if ((method.getModifiers() & PIXEL_FORMAT.YUYV) == 0) {
                        return C00091.m13a(obj, method, objArr);
                    }
                    ReflectionHelper.nativeProxyLogJNIInvokeException();
                }
                return a;
            }
        });
    }
}
