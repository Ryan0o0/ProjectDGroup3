package com.vuforia.captureapp.model;

import android.app.Activity;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import com.vuforia.captureapp.captureactivity.DebugLog;

public class PersitenceModelFragment extends Fragment {
    private static final String TAG_FRAGMENT = "tag_fragment_wait_save_dialog";
    private PersistenceCallback _persistenceCallback;

    private class ModelLoadTask extends AsyncTask<String, Integer, Boolean> {
        private ModelLoadTask() {
        }

        protected Boolean doInBackground(String... strArr) {
            PersitenceModelFragment.this._persistenceCallback.doLoad(strArr[0]);
            return Boolean.valueOf(1);
        }

        protected void onPostExecute(Boolean bool) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ModelLoadTask::onPostExecute: execution ");
            stringBuilder.append(bool.booleanValue() != null ? "successful" : "failed");
            DebugLog.LOGD(stringBuilder.toString());
            PersitenceModelFragment.this.onLoadDone();
        }
    }

    private class ModelSaveTask extends AsyncTask<String, Integer, Boolean> {
        private ModelSaveTask() {
        }

        protected Boolean doInBackground(String... strArr) {
            PersitenceModelFragment.this._persistenceCallback.doSave(strArr[0]);
            return Boolean.valueOf(1);
        }

        protected void onPostExecute(Boolean bool) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ModelSaveTask::onPostExecute: execution ");
            stringBuilder.append(bool.booleanValue() != null ? "successful" : "failed");
            DebugLog.LOGD(stringBuilder.toString());
            PersitenceModelFragment.this.onSaveDone();
        }
    }

    public interface PersistenceCallback {
        boolean doLoad(String str);

        boolean doSave(String str);

        void onLoadDone();

        void onSaveDone();
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this._persistenceCallback = (PersistenceCallback) activity;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
    }

    public void onDetach() {
        super.onDetach();
        this._persistenceCallback = null;
    }

    public void save(java.lang.String r5) {
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
        r0 = new com.vuforia.captureapp.model.WaitDialogFragment;	 Catch:{ Exception -> 0x0028 }
        r0.<init>();	 Catch:{ Exception -> 0x0028 }
        r1 = r4.getResources();	 Catch:{ Exception -> 0x0028 }
        r2 = 2131230762; // 0x7f08002a float:1.8077586E38 double:1.052967903E-314;	 Catch:{ Exception -> 0x0028 }
        r1 = r1.getString(r2);	 Catch:{ Exception -> 0x0028 }
        r2 = r4.getResources();	 Catch:{ Exception -> 0x0028 }
        r3 = 2131230761; // 0x7f080029 float:1.8077584E38 double:1.0529679024E-314;	 Catch:{ Exception -> 0x0028 }
        r2 = r2.getString(r3);	 Catch:{ Exception -> 0x0028 }
        r0.info(r1, r2);	 Catch:{ Exception -> 0x0028 }
        r1 = r4.getFragmentManager();	 Catch:{ Exception -> 0x0028 }
        r2 = "tag_fragment_wait_save_dialog";	 Catch:{ Exception -> 0x0028 }
        r0.show(r1, r2);	 Catch:{ Exception -> 0x0028 }
        goto L_0x002d;
    L_0x0028:
        r0 = "Waiting for save dialog could not be shown";
        com.vuforia.captureapp.captureactivity.DebugLog.LOGW(r0);
    L_0x002d:
        r0 = new com.vuforia.captureapp.model.PersitenceModelFragment$ModelSaveTask;	 Catch:{ Exception -> 0x003d }
        r1 = 0;	 Catch:{ Exception -> 0x003d }
        r0.<init>();	 Catch:{ Exception -> 0x003d }
        r1 = 1;	 Catch:{ Exception -> 0x003d }
        r1 = new java.lang.String[r1];	 Catch:{ Exception -> 0x003d }
        r2 = 0;	 Catch:{ Exception -> 0x003d }
        r1[r2] = r5;	 Catch:{ Exception -> 0x003d }
        r0.execute(r1);	 Catch:{ Exception -> 0x003d }
        goto L_0x0042;
    L_0x003d:
        r5 = "Saving model failed";
        com.vuforia.captureapp.captureactivity.DebugLog.LOGE(r5);
    L_0x0042:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.captureapp.model.PersitenceModelFragment.save(java.lang.String):void");
    }

    public void load(java.lang.String r5) {
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
        r0 = new com.vuforia.captureapp.model.WaitDialogFragment;	 Catch:{ Exception -> 0x0028 }
        r0.<init>();	 Catch:{ Exception -> 0x0028 }
        r1 = r4.getResources();	 Catch:{ Exception -> 0x0028 }
        r2 = 2131230760; // 0x7f080028 float:1.8077582E38 double:1.052967902E-314;	 Catch:{ Exception -> 0x0028 }
        r1 = r1.getString(r2);	 Catch:{ Exception -> 0x0028 }
        r2 = r4.getResources();	 Catch:{ Exception -> 0x0028 }
        r3 = 2131230759; // 0x7f080027 float:1.807758E38 double:1.0529679014E-314;	 Catch:{ Exception -> 0x0028 }
        r2 = r2.getString(r3);	 Catch:{ Exception -> 0x0028 }
        r0.info(r1, r2);	 Catch:{ Exception -> 0x0028 }
        r1 = r4.getFragmentManager();	 Catch:{ Exception -> 0x0028 }
        r2 = "tag_fragment_wait_save_dialog";	 Catch:{ Exception -> 0x0028 }
        r0.show(r1, r2);	 Catch:{ Exception -> 0x0028 }
        goto L_0x002d;
    L_0x0028:
        r0 = "Waiting for load dialog could not be shown";
        com.vuforia.captureapp.captureactivity.DebugLog.LOGW(r0);
    L_0x002d:
        r0 = new com.vuforia.captureapp.model.PersitenceModelFragment$ModelLoadTask;	 Catch:{ Exception -> 0x003d }
        r1 = 0;	 Catch:{ Exception -> 0x003d }
        r0.<init>();	 Catch:{ Exception -> 0x003d }
        r1 = 1;	 Catch:{ Exception -> 0x003d }
        r1 = new java.lang.String[r1];	 Catch:{ Exception -> 0x003d }
        r2 = 0;	 Catch:{ Exception -> 0x003d }
        r1[r2] = r5;	 Catch:{ Exception -> 0x003d }
        r0.execute(r1);	 Catch:{ Exception -> 0x003d }
        goto L_0x0042;
    L_0x003d:
        r5 = "Loading model failed";
        com.vuforia.captureapp.captureactivity.DebugLog.LOGE(r5);
    L_0x0042:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.captureapp.model.PersitenceModelFragment.load(java.lang.String):void");
    }

    public void onSaveDone() {
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
        r2 = this;
        r0 = r2.getFragmentManager();	 Catch:{ Exception -> 0x0017 }
        r1 = "tag_fragment_wait_save_dialog";	 Catch:{ Exception -> 0x0017 }
        r0 = r0.findFragmentByTag(r1);	 Catch:{ Exception -> 0x0017 }
        r0 = (com.vuforia.captureapp.model.WaitDialogFragment) r0;	 Catch:{ Exception -> 0x0017 }
        if (r0 == 0) goto L_0x0011;	 Catch:{ Exception -> 0x0017 }
    L_0x000e:
        r0.dismissAllowingStateLoss();	 Catch:{ Exception -> 0x0017 }
    L_0x0011:
        r0 = r2._persistenceCallback;	 Catch:{ Exception -> 0x0017 }
        r0.onSaveDone();	 Catch:{ Exception -> 0x0017 }
        goto L_0x001c;
    L_0x0017:
        r0 = "Save dialog could not be dismissed";
        com.vuforia.captureapp.captureactivity.DebugLog.LOGW(r0);
    L_0x001c:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.captureapp.model.PersitenceModelFragment.onSaveDone():void");
    }

    public void onLoadDone() {
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
        r2 = this;
        r0 = r2.getFragmentManager();	 Catch:{ Exception -> 0x0017 }
        r1 = "tag_fragment_wait_save_dialog";	 Catch:{ Exception -> 0x0017 }
        r0 = r0.findFragmentByTag(r1);	 Catch:{ Exception -> 0x0017 }
        r0 = (com.vuforia.captureapp.model.WaitDialogFragment) r0;	 Catch:{ Exception -> 0x0017 }
        if (r0 == 0) goto L_0x0011;	 Catch:{ Exception -> 0x0017 }
    L_0x000e:
        r0.dismissAllowingStateLoss();	 Catch:{ Exception -> 0x0017 }
    L_0x0011:
        r0 = r2._persistenceCallback;	 Catch:{ Exception -> 0x0017 }
        r0.onLoadDone();	 Catch:{ Exception -> 0x0017 }
        goto L_0x001c;
    L_0x0017:
        r0 = "Load dialog could not be dismissed";
        com.vuforia.captureapp.captureactivity.DebugLog.LOGW(r0);
    L_0x001c:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.captureapp.model.PersitenceModelFragment.onLoadDone():void");
    }
}
