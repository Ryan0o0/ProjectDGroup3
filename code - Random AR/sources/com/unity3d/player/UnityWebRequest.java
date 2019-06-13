package com.unity3d.player;

import com.unity3d.player.C0036b.C0035b;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLKeyException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;

class UnityWebRequest implements Runnable {
    /* renamed from: k */
    private static final HostnameVerifier f108k = new C00261();
    /* renamed from: a */
    private long f109a;
    /* renamed from: b */
    private String f110b;
    /* renamed from: c */
    private String f111c;
    /* renamed from: d */
    private Map f112d;
    /* renamed from: e */
    private boolean f113e;
    /* renamed from: f */
    private int f114f;
    /* renamed from: g */
    private long f115g;
    /* renamed from: h */
    private long f116h;
    /* renamed from: i */
    private boolean f117i;
    /* renamed from: j */
    private boolean f118j;

    /* renamed from: com.unity3d.player.UnityWebRequest$1 */
    static class C00261 implements HostnameVerifier {
        C00261() {
        }

        public final boolean verify(String str, SSLSession sSLSession) {
            return true;
        }
    }

    /* renamed from: com.unity3d.player.UnityWebRequest$2 */
    class C00792 extends C0035b {
        /* renamed from: b */
        final /* synthetic */ UnityWebRequest f323b;

        C00792(UnityWebRequest unityWebRequest) {
            this.f323b = unityWebRequest;
        }

        public final void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
            byte[] encoded = (x509CertificateArr == null || x509CertificateArr.length <= 0) ? new byte[0] : x509CertificateArr[0].getEncoded();
            if (!this.f323b.validateCertificateCallback(encoded)) {
                throw new CertificateException();
            }
        }
    }

    static {
        if (CookieHandler.getDefault() == null) {
            CookieHandler.setDefault(new CookieManager());
        }
    }

    UnityWebRequest(long j, String str, Map map, String str2, boolean z, int i) {
        this.f109a = j;
        this.f110b = str2;
        this.f111c = str;
        this.f112d = map;
        this.f113e = z;
        this.f114f = i;
    }

    static void clearCookieCache(java.lang.String r3, java.lang.String r4) {
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
        r0 = java.net.CookieHandler.getDefault();
        if (r0 == 0) goto L_0x003a;
    L_0x0006:
        r1 = r0 instanceof java.net.CookieManager;
        if (r1 != 0) goto L_0x000b;
    L_0x000a:
        goto L_0x003a;
    L_0x000b:
        r0 = (java.net.CookieManager) r0;
        r0 = r0.getCookieStore();
        if (r0 != 0) goto L_0x0014;
    L_0x0013:
        return;
    L_0x0014:
        if (r3 != 0) goto L_0x001a;
    L_0x0016:
        r0.removeAll();
        return;
    L_0x001a:
        r1 = new java.net.URI;	 Catch:{ URISyntaxException -> 0x003a }
        r2 = 0;	 Catch:{ URISyntaxException -> 0x003a }
        r1.<init>(r2, r3, r4, r2);	 Catch:{ URISyntaxException -> 0x003a }
        r3 = r0.get(r1);	 Catch:{ URISyntaxException -> 0x003a }
        if (r3 == 0) goto L_0x003a;	 Catch:{ URISyntaxException -> 0x003a }
    L_0x0026:
        r3 = r3.iterator();	 Catch:{ URISyntaxException -> 0x003a }
    L_0x002a:
        r4 = r3.hasNext();	 Catch:{ URISyntaxException -> 0x003a }
        if (r4 == 0) goto L_0x003a;	 Catch:{ URISyntaxException -> 0x003a }
    L_0x0030:
        r4 = r3.next();	 Catch:{ URISyntaxException -> 0x003a }
        r4 = (java.net.HttpCookie) r4;	 Catch:{ URISyntaxException -> 0x003a }
        r0.remove(r1, r4);	 Catch:{ URISyntaxException -> 0x003a }
        goto L_0x002a;
    L_0x003a:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.UnityWebRequest.clearCookieCache(java.lang.String, java.lang.String):void");
    }

    private static native void contentLengthCallback(long j, int i);

    private static native boolean downloadCallback(long j, ByteBuffer byteBuffer, int i);

    private static native void errorCallback(long j, int i, String str);

    private boolean hasTimedOut() {
        return this.f114f > 0 && System.currentTimeMillis() - this.f115g >= ((long) this.f114f);
    }

    private static native void headerCallback(long j, String str, String str2);

    private static native void responseCodeCallback(long j, int i);

    private void runSafe() {
        this.f115g = System.currentTimeMillis();
        try {
            URL url = new URL(this.f110b);
            URLConnection openConnection = url.openConnection();
            openConnection.setConnectTimeout(this.f114f);
            openConnection.setReadTimeout(this.f114f);
            InputStream inputStream = null;
            if (openConnection instanceof HttpsURLConnection) {
                C0035b c00792;
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) openConnection;
                if (this.f113e) {
                    c00792 = new C00792(this);
                    httpsURLConnection.setHostnameVerifier(f108k);
                } else {
                    c00792 = null;
                }
                SSLSocketFactory a = C0036b.m77a(c00792);
                if (a != null) {
                    httpsURLConnection.setSSLSocketFactory(a);
                }
            }
            if (!url.getProtocol().equalsIgnoreCase("file") || url.getHost().isEmpty()) {
                boolean z = openConnection instanceof HttpURLConnection;
                if (z) {
                    try {
                        HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
                        httpURLConnection.setRequestMethod(this.f111c);
                        httpURLConnection.setInstanceFollowRedirects(false);
                        if (this.f116h > 0) {
                            if (this.f118j) {
                                httpURLConnection.setChunkedStreamingMode(0);
                            } else {
                                httpURLConnection.setFixedLengthStreamingMode((int) this.f116h);
                            }
                            if (this.f117i) {
                                httpURLConnection.addRequestProperty("Expect", "100-continue");
                            }
                        }
                    } catch (ProtocolException e) {
                        badProtocolCallback(e.toString());
                        return;
                    }
                }
                if (this.f112d != null) {
                    for (Entry entry : this.f112d.entrySet()) {
                        openConnection.addRequestProperty((String) entry.getKey(), (String) entry.getValue());
                    }
                }
                ByteBuffer allocateDirect = ByteBuffer.allocateDirect(131072);
                if (uploadCallback(null) > 0) {
                    openConnection.setDoOutput(true);
                    try {
                        OutputStream outputStream = openConnection.getOutputStream();
                        while (true) {
                            int uploadCallback = uploadCallback(allocateDirect);
                            if (uploadCallback <= 0) {
                                break;
                            } else if (hasTimedOut()) {
                                outputStream.close();
                                errorCallback(this.f109a, 14, "WebRequest timed out.");
                                return;
                            } else {
                                outputStream.write(allocateDirect.array(), allocateDirect.arrayOffset(), uploadCallback);
                            }
                        }
                    } catch (Exception e2) {
                        errorCallback(e2.toString());
                        return;
                    }
                }
                if (z) {
                    try {
                        responseCodeCallback(((HttpURLConnection) openConnection).getResponseCode());
                    } catch (UnknownHostException e3) {
                        unknownHostCallback(e3.toString());
                        return;
                    } catch (SSLException e4) {
                        sslCannotConnectCallback(e4);
                        return;
                    } catch (SocketTimeoutException e5) {
                        errorCallback(this.f109a, 14, e5.toString());
                        return;
                    } catch (IOException e6) {
                        errorCallback(e6.toString());
                        return;
                    }
                }
                Map headerFields = openConnection.getHeaderFields();
                headerCallback(headerFields);
                if ((headerFields == null || !headerFields.containsKey("content-length")) && openConnection.getContentLength() != -1) {
                    headerCallback("content-length", String.valueOf(openConnection.getContentLength()));
                }
                if ((headerFields == null || !headerFields.containsKey("content-type")) && openConnection.getContentType() != null) {
                    headerCallback("content-type", openConnection.getContentType());
                }
                if (headerFields != null && headerFields.containsKey("Set-Cookie") && CookieHandler.getDefault() != null && (CookieHandler.getDefault() instanceof CookieManager)) {
                    CookieStore cookieStore = ((CookieManager) CookieHandler.getDefault()).getCookieStore();
                    for (String parse : (List) headerFields.get("Set-Cookie")) {
                        try {
                            HttpCookie httpCookie = (HttpCookie) HttpCookie.parse(parse).get(0);
                            if (!(httpCookie.getPath() == null || httpCookie.getPath().equals(""))) {
                                if (httpCookie.getDomain() == null || httpCookie.getDomain().equals(url.getHost())) {
                                    URI uri = new URI(url.getProtocol(), url.getHost(), httpCookie.getPath(), null);
                                    httpCookie.setDomain(url.getHost());
                                    cookieStore.add(uri, httpCookie);
                                }
                            }
                        } catch (URISyntaxException e7) {
                            StringBuilder stringBuilder = new StringBuilder("UnityWebRequest: error constructing URI: ");
                            stringBuilder.append(e7.getMessage());
                            C0042g.Log(6, stringBuilder.toString());
                        }
                    }
                }
                contentLengthCallback(openConnection.getContentLength());
                try {
                    if (openConnection instanceof HttpURLConnection) {
                        HttpURLConnection httpURLConnection2 = (HttpURLConnection) openConnection;
                        responseCodeCallback(httpURLConnection2.getResponseCode());
                        inputStream = httpURLConnection2.getErrorStream();
                    }
                    if (inputStream == null) {
                        inputStream = openConnection.getInputStream();
                    }
                    ReadableByteChannel newChannel = Channels.newChannel(inputStream);
                    while (true) {
                        int read = newChannel.read(allocateDirect);
                        if (read != -1) {
                            if (!hasTimedOut()) {
                                if (!downloadCallback(allocateDirect, read)) {
                                    break;
                                }
                                allocateDirect.clear();
                            } else {
                                newChannel.close();
                                errorCallback(this.f109a, 14, "WebRequest timed out.");
                                return;
                            }
                        }
                        break;
                    }
                    newChannel.close();
                    return;
                } catch (UnknownHostException e32) {
                    unknownHostCallback(e32.toString());
                    return;
                } catch (SSLException e42) {
                    sslCannotConnectCallback(e42);
                    return;
                } catch (SocketTimeoutException e52) {
                    errorCallback(this.f109a, 14, e52.toString());
                    return;
                } catch (IOException e62) {
                    errorCallback(this.f109a, 12, e62.toString());
                    return;
                } catch (Exception e22) {
                    errorCallback(e22.toString());
                    return;
                }
            }
            malformattedUrlCallback("file:// must use an absolute path");
        } catch (MalformedURLException e8) {
            malformattedUrlCallback(e8.toString());
        } catch (IOException e622) {
            errorCallback(e622.toString());
        }
    }

    private static native int uploadCallback(long j, ByteBuffer byteBuffer);

    private static native boolean validateCertificateCallback(long j, byte[] bArr);

    protected void badProtocolCallback(String str) {
        errorCallback(this.f109a, 4, str);
    }

    protected void contentLengthCallback(int i) {
        contentLengthCallback(this.f109a, i);
    }

    protected boolean downloadCallback(ByteBuffer byteBuffer, int i) {
        return downloadCallback(this.f109a, byteBuffer, i);
    }

    protected void errorCallback(String str) {
        errorCallback(this.f109a, 2, str);
    }

    protected void headerCallback(String str, String str2) {
        headerCallback(this.f109a, str, str2);
    }

    protected void headerCallback(Map map) {
        if (map == null) {
            return;
        }
        if (map.size() != 0) {
            for (Entry entry : map.entrySet()) {
                String str = (String) entry.getKey();
                if (str == null) {
                    str = "Status";
                }
                for (String headerCallback : (List) entry.getValue()) {
                    headerCallback(str, headerCallback);
                }
            }
        }
    }

    protected void malformattedUrlCallback(String str) {
        errorCallback(this.f109a, 5, str);
    }

    protected void responseCodeCallback(int i) {
        responseCodeCallback(this.f109a, i);
    }

    public void run() {
        try {
            runSafe();
        } catch (Exception e) {
            errorCallback(e.toString());
        }
    }

    void setupTransferSettings(long j, boolean z, boolean z2) {
        this.f116h = j;
        this.f117i = z;
        this.f118j = z2;
    }

    protected void sslCannotConnectCallback(SSLException sSLException) {
        int i;
        String sSLException2 = sSLException.toString();
        Throwable cause;
        while (cause != null) {
            if (!(cause instanceof SSLKeyException)) {
                if (!(cause instanceof SSLPeerUnverifiedException)) {
                    if (!(cause instanceof CertPathValidatorException)) {
                        cause = cause.getCause();
                    }
                }
                i = 25;
                break;
            }
            i = 23;
            break;
        }
        i = 16;
        errorCallback(this.f109a, i, sSLException2);
    }

    protected void unknownHostCallback(String str) {
        errorCallback(this.f109a, 7, str);
    }

    protected int uploadCallback(ByteBuffer byteBuffer) {
        return uploadCallback(this.f109a, byteBuffer);
    }

    protected boolean validateCertificateCallback(byte[] bArr) {
        return validateCertificateCallback(this.f109a, bArr);
    }
}
