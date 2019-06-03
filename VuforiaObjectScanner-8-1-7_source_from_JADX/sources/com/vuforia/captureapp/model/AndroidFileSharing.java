package com.vuforia.captureapp.model;

import android.net.Uri;
import android.os.AsyncTask;
import com.vuforia.captureapp.Util;
import com.vuforia.captureapp.captureactivity.DebugLog;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AndroidFileSharing {
    private ZipCompletionCallback _callback;
    private final List<String> _capturesToShare = new ArrayList();

    private class ModelZipTask extends AsyncTask<Void, Integer, Boolean> {
        ArrayList<Uri> imageUris;

        private ModelZipTask() {
            this.imageUris = new ArrayList();
        }

        protected Boolean doInBackground(Void... voidArr) {
            AndroidFileSharing.this.doZip(this.imageUris);
            return Boolean.valueOf(1);
        }

        protected void onPostExecute(Boolean bool) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("BuilderNewSequenceTask::onPostExecute: execution ");
            stringBuilder.append(bool.booleanValue() != null ? "successful" : "failed");
            DebugLog.LOGD(stringBuilder.toString());
            AndroidFileSharing.this._callback.onZipDone(this.imageUris);
        }
    }

    public interface ZipCompletionCallback {
        void onZipDone(ArrayList<Uri> arrayList);
    }

    public void addCaptureName(String str) {
        this._capturesToShare.add(str);
    }

    public void zip(com.vuforia.captureapp.model.AndroidFileSharing.ZipCompletionCallback r2) {
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
        r1 = this;
        r1._callback = r2;
        r2 = new com.vuforia.captureapp.model.AndroidFileSharing$ModelZipTask;	 Catch:{ Exception -> 0x000f }
        r0 = 0;	 Catch:{ Exception -> 0x000f }
        r2.<init>();	 Catch:{ Exception -> 0x000f }
        r0 = 0;	 Catch:{ Exception -> 0x000f }
        r0 = new java.lang.Void[r0];	 Catch:{ Exception -> 0x000f }
        r2.execute(r0);	 Catch:{ Exception -> 0x000f }
        goto L_0x0014;
    L_0x000f:
        r2 = "Loading tracking data set failed";
        com.vuforia.captureapp.captureactivity.DebugLog.LOGE(r2);
    L_0x0014:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.captureapp.model.AndroidFileSharing.zip(com.vuforia.captureapp.model.AndroidFileSharing$ZipCompletionCallback):void");
    }

    private void doZip(ArrayList<Uri> arrayList) {
        File captureShareDirectory = Util.getCaptureShareDirectory();
        Util.deleteFileRecursive(captureShareDirectory);
        captureShareDirectory.mkdirs();
        for (String odFile : this._capturesToShare) {
            arrayList.add(Uri.fromFile(Util.getOdFile(odFile)));
        }
    }
}
