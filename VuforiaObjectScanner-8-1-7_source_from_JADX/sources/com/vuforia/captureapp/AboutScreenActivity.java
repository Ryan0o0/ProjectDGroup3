package com.vuforia.captureapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class AboutScreenActivity extends Activity {
    private static final String LOGTAG = "AboutScreenActivity";

    private class AboutWebViewClient extends WebViewClient {
        private AboutWebViewClient() {
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            AboutScreenActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
            return true;
        }
    }

    protected void onCreate(android.os.Bundle r5) {
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
	at jadx.api.JadxDecompiler$$Lambda$8/1630521067.run(Unknown Source)
*/
        /*
        r4 = this;
        super.onCreate(r5);
        r5 = r4.getWindow();
        r0 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r5.setFlags(r0, r0);
        r5 = r4.getActionBar();
        r0 = 1;
        r5.setDisplayHomeAsUpEnabled(r0);
        r5 = 2131099648; // 0x7f060000 float:1.7811655E38 double:1.052903124E-314;
        r4.setContentView(r5);
        r5 = 2131034156; // 0x7f05002c float:1.7678822E38 double:1.0528707666E-314;
        r5 = r4.findViewById(r5);
        r5 = (android.webkit.WebView) r5;
        r0 = "";
        r1 = r4.getAssets();	 Catch:{ IOException -> 0x004f }
        r2 = "about.html";	 Catch:{ IOException -> 0x004f }
        r1 = r1.open(r2);	 Catch:{ IOException -> 0x004f }
        r2 = new java.io.BufferedReader;	 Catch:{ IOException -> 0x004f }
        r3 = new java.io.InputStreamReader;	 Catch:{ IOException -> 0x004f }
        r3.<init>(r1);	 Catch:{ IOException -> 0x004f }
        r2.<init>(r3);	 Catch:{ IOException -> 0x004f }
    L_0x0038:
        r1 = r2.readLine();	 Catch:{ IOException -> 0x004f }
        if (r1 == 0) goto L_0x0056;	 Catch:{ IOException -> 0x004f }
    L_0x003e:
        r3 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x004f }
        r3.<init>();	 Catch:{ IOException -> 0x004f }
        r3.append(r0);	 Catch:{ IOException -> 0x004f }
        r3.append(r1);	 Catch:{ IOException -> 0x004f }
        r1 = r3.toString();	 Catch:{ IOException -> 0x004f }
        r0 = r1;
        goto L_0x0038;
    L_0x004f:
        r1 = "AboutScreenActivity";
        r2 = "About html loading failed";
        android.util.Log.e(r1, r2);
    L_0x0056:
        r1 = "text/html";
        r2 = "UTF-8";
        r5.loadData(r0, r1, r2);
        r0 = new com.vuforia.captureapp.AboutScreenActivity$AboutWebViewClient;
        r1 = 0;
        r0.<init>();
        r5.setWebViewClient(r0);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.captureapp.AboutScreenActivity.onCreate(android.os.Bundle):void");
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        finish();
        return true;
    }
}
