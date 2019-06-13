package bitter.jnibridge;

import com.vuforia.PIXEL_FORMAT;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JNIBridge {

    /* renamed from: bitter.jnibridge.JNIBridge$a */
    private static class C0000a implements InvocationHandler {
        /* renamed from: a */
        private Object f0a = new Object[0];
        /* renamed from: b */
        private long f1b;
        /* renamed from: c */
        private Constructor f2c;

        public C0000a(long r4) {
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
            r3 = this;
            r3.<init>();
            r0 = 0;
            r1 = new java.lang.Object[r0];
            r3.f0a = r1;
            r3.f1b = r4;
            r4 = 0;
            r5 = java.lang.invoke.MethodHandles.Lookup.class;	 Catch:{ NoClassDefFoundError -> 0x0028, NoSuchMethodException -> 0x0025 }
            r1 = 2;	 Catch:{ NoClassDefFoundError -> 0x0028, NoSuchMethodException -> 0x0025 }
            r1 = new java.lang.Class[r1];	 Catch:{ NoClassDefFoundError -> 0x0028, NoSuchMethodException -> 0x0025 }
            r2 = java.lang.Class.class;	 Catch:{ NoClassDefFoundError -> 0x0028, NoSuchMethodException -> 0x0025 }
            r1[r0] = r2;	 Catch:{ NoClassDefFoundError -> 0x0028, NoSuchMethodException -> 0x0025 }
            r0 = java.lang.Integer.TYPE;	 Catch:{ NoClassDefFoundError -> 0x0028, NoSuchMethodException -> 0x0025 }
            r2 = 1;	 Catch:{ NoClassDefFoundError -> 0x0028, NoSuchMethodException -> 0x0025 }
            r1[r2] = r0;	 Catch:{ NoClassDefFoundError -> 0x0028, NoSuchMethodException -> 0x0025 }
            r5 = r5.getDeclaredConstructor(r1);	 Catch:{ NoClassDefFoundError -> 0x0028, NoSuchMethodException -> 0x0025 }
            r3.f2c = r5;	 Catch:{ NoClassDefFoundError -> 0x0028, NoSuchMethodException -> 0x0025 }
            r5 = r3.f2c;	 Catch:{ NoClassDefFoundError -> 0x0028, NoSuchMethodException -> 0x0025 }
            r5.setAccessible(r2);	 Catch:{ NoClassDefFoundError -> 0x0028, NoSuchMethodException -> 0x0025 }
            return;
        L_0x0025:
            r3.f2c = r4;
            return;
        L_0x0028:
            r3.f2c = r4;
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: bitter.jnibridge.JNIBridge.a.<init>(long):void");
        }

        /* renamed from: a */
        private Object m0a(Object obj, Method method, Object[] objArr) {
            if (objArr == null) {
                objArr = new Object[0];
            }
            Class declaringClass = method.getDeclaringClass();
            return ((Lookup) this.f2c.newInstance(new Object[]{declaringClass, Integer.valueOf(2)})).in(declaringClass).unreflectSpecial(method, declaringClass).bindTo(obj).invokeWithArguments(objArr);
        }

        /* renamed from: a */
        public final void m1a() {
            synchronized (this.f0a) {
                this.f1b = 0;
            }
        }

        public final void finalize() {
            synchronized (this.f0a) {
                if (this.f1b == 0) {
                    return;
                }
                JNIBridge.delete(this.f1b);
            }
        }

        public final Object invoke(Object obj, Method method, Object[] objArr) {
            synchronized (this.f0a) {
                if (this.f1b == 0) {
                    return null;
                }
                try {
                    Object invoke = JNIBridge.invoke(this.f1b, method.getDeclaringClass(), method, objArr);
                    return invoke;
                } catch (NoSuchMethodError e) {
                    if (this.f2c == null) {
                        System.err.println("JNIBridge error: Java interface default methods are only supported since Android Oreo");
                        throw e;
                    } else if ((method.getModifiers() & PIXEL_FORMAT.YUYV) == 0) {
                        return m0a(obj, method, objArr);
                    } else {
                        throw e;
                    }
                }
            }
        }
    }

    static native void delete(long j);

    static void disableInterfaceProxy(Object obj) {
        if (obj != null) {
            ((C0000a) Proxy.getInvocationHandler(obj)).m1a();
        }
    }

    static native Object invoke(long j, Class cls, Method method, Object[] objArr);

    static Object newInterfaceProxy(long j, Class[] clsArr) {
        return Proxy.newProxyInstance(JNIBridge.class.getClassLoader(), clsArr, new C0000a(j));
    }
}
