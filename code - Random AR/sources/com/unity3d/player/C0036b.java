package com.unity3d.player;

import android.os.Build.VERSION;
import java.net.InetAddress;
import java.net.Socket;
import java.security.cert.X509Certificate;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/* renamed from: com.unity3d.player.b */
public final class C0036b extends SSLSocketFactory {
    /* renamed from: c */
    private static volatile SSLSocketFactory f159c;
    /* renamed from: d */
    private static volatile X509TrustManager f160d;
    /* renamed from: e */
    private static final Object f161e = new Object[0];
    /* renamed from: f */
    private static final Object f162f = new Object[0];
    /* renamed from: g */
    private static final boolean f163g;
    /* renamed from: a */
    private final SSLSocketFactory f164a;
    /* renamed from: b */
    private final C0034a f165b = null;

    /* renamed from: com.unity3d.player.b$a */
    class C0034a implements HandshakeCompletedListener {
        public final void handshakeCompleted(javax.net.ssl.HandshakeCompletedEvent r1) {
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
            r0 = this;
            r1 = r1.getSession();
            r1.getCipherSuite();
            r1.getProtocol();
            r1 = r1.getPeerPrincipal();	 Catch:{ SSLPeerUnverifiedException -> 0x0011 }
            r1.getName();	 Catch:{ SSLPeerUnverifiedException -> 0x0011 }
        L_0x0011:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.b.a.handshakeCompleted(javax.net.ssl.HandshakeCompletedEvent):void");
        }
    }

    /* renamed from: com.unity3d.player.b$b */
    public static abstract class C0035b implements X509TrustManager {
        /* renamed from: a */
        protected X509TrustManager f158a = C0036b.m80c();

        public final void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
            this.f158a.checkClientTrusted(x509CertificateArr, str);
        }

        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
            this.f158a.checkServerTrusted(x509CertificateArr, str);
        }

        public final X509Certificate[] getAcceptedIssuers() {
            return this.f158a.getAcceptedIssuers();
        }
    }

    static {
        boolean z = false;
        if (VERSION.SDK_INT >= 16 && VERSION.SDK_INT < 20) {
            z = true;
        }
        f163g = z;
    }

    private C0036b(C0035b[] c0035bArr) {
        SSLContext instance = SSLContext.getInstance("TLS");
        instance.init(null, c0035bArr, null);
        this.f164a = instance.getSocketFactory();
    }

    /* renamed from: a */
    private Socket m76a(Socket socket) {
        if (socket != null && (socket instanceof SSLSocket)) {
            if (f163g) {
                SSLSocket sSLSocket = (SSLSocket) socket;
                sSLSocket.setEnabledProtocols(sSLSocket.getSupportedProtocols());
            }
            if (this.f165b != null) {
                ((SSLSocket) socket).addHandshakeCompletedListener(this.f165b);
            }
        }
        return socket;
    }

    /* renamed from: a */
    public static SSLSocketFactory m77a(C0035b c0035b) {
        if (c0035b == null) {
            try {
                return C0036b.m79b();
            } catch (Exception e) {
                StringBuilder stringBuilder = new StringBuilder("CustomSSLSocketFactory: Failed to create SSLSocketFactory (");
                stringBuilder.append(e.getMessage());
                stringBuilder.append(")");
                C0042g.Log(5, stringBuilder.toString());
                return null;
            }
        }
        return new C0036b(new C0035b[]{c0035b});
    }

    /* renamed from: b */
    private static SSLSocketFactory m79b() {
        synchronized (f161e) {
            if (f159c != null) {
                SSLSocketFactory sSLSocketFactory = f159c;
                return sSLSocketFactory;
            }
            sSLSocketFactory = new C0036b(null);
            f159c = sSLSocketFactory;
            return sSLSocketFactory;
        }
    }

    /* renamed from: c */
    private static X509TrustManager m80c() {
        synchronized (f162f) {
            if (f160d != null) {
                return f160d;
            }
            try {
                TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                instance.init(null);
                for (TrustManager trustManager : instance.getTrustManagers()) {
                    if (trustManager instanceof X509TrustManager) {
                        X509TrustManager x509TrustManager = (X509TrustManager) trustManager;
                        f160d = x509TrustManager;
                        return x509TrustManager;
                    }
                }
            } catch (Exception e) {
                StringBuilder stringBuilder = new StringBuilder("CustomSSLSocketFactory: Failed to find X509TrustManager (");
                stringBuilder.append(e.getMessage());
                stringBuilder.append(")");
                C0042g.Log(5, stringBuilder.toString());
            }
        }
        return null;
    }

    public final Socket createSocket() {
        return m76a(this.f164a.createSocket());
    }

    public final Socket createSocket(String str, int i) {
        return m76a(this.f164a.createSocket(str, i));
    }

    public final Socket createSocket(String str, int i, InetAddress inetAddress, int i2) {
        return m76a(this.f164a.createSocket(str, i, inetAddress, i2));
    }

    public final Socket createSocket(InetAddress inetAddress, int i) {
        return m76a(this.f164a.createSocket(inetAddress, i));
    }

    public final Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) {
        return m76a(this.f164a.createSocket(inetAddress, i, inetAddress2, i2));
    }

    public final Socket createSocket(Socket socket, String str, int i, boolean z) {
        return m76a(this.f164a.createSocket(socket, str, i, z));
    }

    public final String[] getDefaultCipherSuites() {
        return this.f164a.getDefaultCipherSuites();
    }

    public final String[] getSupportedCipherSuites() {
        return this.f164a.getSupportedCipherSuites();
    }
}
