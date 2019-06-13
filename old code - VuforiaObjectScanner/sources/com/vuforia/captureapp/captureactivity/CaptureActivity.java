package com.vuforia.captureapp.captureactivity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.vuforia.PIXEL_FORMAT;
import com.vuforia.Vuforia;
import com.vuforia.captureapp.BuildConfig;
import com.vuforia.captureapp.C0026R;
import com.vuforia.captureapp.Constants;
import com.vuforia.captureapp.Util;
import com.vuforia.captureapp.model.PersitenceModelFragment;
import com.vuforia.captureapp.model.PersitenceModelFragment.PersistenceCallback;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Vector;

public class CaptureActivity extends Activity implements PersistenceCallback {
    private static final int APPSTATUS_CAMERA_RUNNING = 7;
    private static final int APPSTATUS_CAMERA_STOPPED = 6;
    private static final int APPSTATUS_INITED = 5;
    private static final int APPSTATUS_INIT_APP = 0;
    private static final int APPSTATUS_INIT_APP_AR = 3;
    private static final int APPSTATUS_INIT_QCAR = 1;
    private static final int APPSTATUS_INIT_TRACKER = 2;
    private static final int APPSTATUS_LOAD_TRACKER = 4;
    private static final int APPSTATUS_UNINITED = -1;
    private static final int CAPTURE_IMAGE_TARGETS = 100;
    private static final int CAPTURE_NB_IMAGE_POINTS = 110;
    private static final int CAPTURE_NO_IMAGE_TARGETS = 101;
    private static final int CAPTURE_SAVED = 102;
    private static final int FOCUS_MODE_CONTINUOUS_AUTO = 1;
    private static final int FOCUS_MODE_INFINITY = 2;
    private static final int FOCUS_MODE_NORMAL = 0;
    static final int HIDE_LOADING_DIALOG = 0;
    private static final int INVALID_SCREEN_ROTATION = -1;
    private static final String NATIVE_LIB_SAMPLE = "CaptureApp";
    private static final String NATIVE_LIB_VUFORIA = "Vuforia";
    public static final int RESULT_NO_CAPTURE = 101;
    public static final int RESULT_WITH_CAPTURE = 102;
    static final int SHOW_LOADING_DIALOG = 1;
    private static final String TAG_FRAGMENT = "tag_fragment_save_model";
    private static final String sVuforiaKey = "AXVOQk7/////AAAAAcGhB4JJd0nlh/B8dBcXPkBsKxZLhiU8LF0WSWZakGP6TC5Cdxg7UidNeEd4INThOFfFOcEv45sDbOazRPt6FQEbclNYYf4sgTqw0Yd9oAfdz1s/qW3SH9fbzj5CkRGsUSQXS+/amTCN9iXw1Eppa1/YrgBFTHHYvTz9QbnRv8ivQMSX8tdIkb0vXX3KrEGA4vnwPSfKBI6Eygr7Xz0FZasntcreYqxi2xrtDuu7LObdAdfEHsO7L4jKrrfZLvuzl2+O4L6mUMqnfQyNJUmUTJueS017e+ZNCvPvMaWy7HqtPHSOYzRAVxcIBI7aHsdff3aWEblYTGrZgYkSbWNAuJXgEPUfHkATBX8JU+CCW5sU";
    private Bitmap _firstFrameBitmap;
    private PersitenceModelFragment _fragment;
    private Handler loadingDialogHandler = new LoadingDialogHandler(this);
    private LinearLayout mAlignmentOverlay;
    private int mAppStatus = -1;
    private BuilderNewSequenceTask mBuilderNewSequenceTask;
    private ImageView mButtonClose;
    private ImageView mButtonSave;
    private ImageView mButtonScanDisabled;
    private ImageView mButtonScanPaused;
    private ImageView mButtonScanning;
    List<String> mCaptureNameList;
    private ImageView mCoverageImage;
    private LinearLayout mCoverageLayout;
    private String mCurrentCaptureName = BuildConfig.FLAVOR;
    private int mCurrentJniState = -1;
    private int mCurrentMode = 100;
    private String mCurrentRecordingName = null;
    MenuItem mDataSetMenuItem = null;
    private int[] mDomeFacets = new int[49];
    private float mFileSize = 0.0f;
    private GestureDetector mGestureDetector;
    private CaptureGLView mGlView;
    private InitQCARTask mInitQCARTask;
    boolean mIsLegoDataSetActive = false;
    private boolean mIsRecording = false;
    private int mLastScreenRotation = -1;
    private LoadTrackerTask mLoadTrackerTask;
    private View mLoadingDialogContainer;
    private TextView mMemoryConsumption;
    private int mNbPoints = 0;
    private int mNbRecordingClips = 0;
    private TextView mPointsCount;
    private int mQCARFlags = 0;
    private CaptureGLRenderer mRenderer;
    private int mScreenHeight = 0;
    private int mScreenWidth = 0;
    private boolean mShowDome = true;
    private Object mShutdownLock = new Object();
    private Vector<Texture> mTextures;
    private RelativeLayout mUILayout;

    /* renamed from: com.vuforia.captureapp.captureactivity.CaptureActivity$2 */
    class C00282 implements OnClickListener {
        C00282() {
        }

        public void onClick(View view) {
            CaptureActivity.this.mShowDome = CaptureActivity.this.mShowDome ^ 1;
            CaptureActivity.this.cycleAugmentationMode(CaptureActivity.this.mShowDome);
            if (CaptureActivity.this.mShowDome != null) {
                CaptureActivity.this.mCoverageImage.setImageResource(C0026R.drawable.checkbox_checked);
            } else {
                CaptureActivity.this.mCoverageImage.setImageResource(C0026R.drawable.checkbox_unchecked);
            }
        }
    }

    /* renamed from: com.vuforia.captureapp.captureactivity.CaptureActivity$3 */
    class C00303 implements OnClickListener {

        /* renamed from: com.vuforia.captureapp.captureactivity.CaptureActivity$3$1 */
        class C00291 implements DialogInterface.OnClickListener {
            C00291() {
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == -1) {
                    CaptureActivity.this.builderCloseSequenceUI();
                    CaptureActivity.this.endActivity(0);
                }
            }
        }

        C00303() {
        }

        public void onClick(View view) {
            if (CaptureActivity.this.mNbRecordingClips == null) {
                CaptureActivity.this.builderCloseSequenceUI();
                CaptureActivity.this.endActivity(null);
                return;
            }
            CaptureActivity.this.builderStopClipUI();
            CaptureActivity.this.mIsRecording = false;
            view = new C00291();
            ((TextView) new Builder(CaptureActivity.this).setMessage("\nData from this scan will be lost. Continue?\n").setPositiveButton("Yes", view).setNegativeButton("No", view).setTitle("Discard scan").show().findViewById(16908299)).setGravity(3);
        }
    }

    /* renamed from: com.vuforia.captureapp.captureactivity.CaptureActivity$4 */
    class C00314 implements OnClickListener {
        C00314() {
        }

        public void onClick(View view) {
            CaptureActivity.this.builderStartClipUI();
            CaptureActivity.this.mButtonScanning.setVisibility(0);
            CaptureActivity.this.mButtonScanPaused.setVisibility(4);
            CaptureActivity.this.mIsRecording = true;
            CaptureActivity.this.mNbRecordingClips = CaptureActivity.this.mNbRecordingClips + 1;
            CaptureActivity.this.setRecordingUIStateRecording();
            CaptureActivity.this.onRecordingStarted(true);
        }
    }

    /* renamed from: com.vuforia.captureapp.captureactivity.CaptureActivity$5 */
    class C00325 implements OnClickListener {
        C00325() {
        }

        public void onClick(View view) {
            CaptureActivity.this.builderStopClipUI();
            CaptureActivity.this.mIsRecording = false;
            CaptureActivity.this.setUIStateRecordingPaused();
        }
    }

    /* renamed from: com.vuforia.captureapp.captureactivity.CaptureActivity$6 */
    class C00396 implements OnClickListener {

        /* renamed from: com.vuforia.captureapp.captureactivity.CaptureActivity$6$2 */
        class C00342 implements OnDismissListener {
            C00342() {
            }

            public void onDismiss(DialogInterface dialogInterface) {
                CaptureActivity.this.mUILayout.setVisibility(0);
                CaptureActivity.this.mUILayout.invalidate();
                CaptureActivity.this.mUILayout.requestLayout();
            }
        }

        /* renamed from: com.vuforia.captureapp.captureactivity.CaptureActivity$6$3 */
        class C00353 implements OnCancelListener {
            C00353() {
            }

            public void onCancel(DialogInterface dialogInterface) {
                CaptureActivity.this.mUILayout.setVisibility(0);
                CaptureActivity.this.mUILayout.invalidate();
                CaptureActivity.this.mUILayout.requestLayout();
            }
        }

        /* renamed from: com.vuforia.captureapp.captureactivity.CaptureActivity$6$4 */
        class C00364 implements DialogInterface.OnClickListener {
            C00364() {
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        }

        C00396() {
        }

        @TargetApi(17)
        public void onClick(View view) {
            if (CaptureActivity.this.mCurrentMode == 100) {
                CaptureActivity.this.mCurrentRecordingName = null;
                view = new Builder(CaptureActivity.this);
                View inflate = LayoutInflater.from(CaptureActivity.this).inflate(C0026R.layout.text_edit_layout, null, false);
                final EditText editText = (EditText) inflate.findViewById(C0026R.id.text_edit_view);
                final TextView textView = (TextView) inflate.findViewById(C0026R.id.text_edit_error_message);
                editText.setInputType(524289);
                editText.setImeActionLabel("OK", 66);
                editText.setImeOptions(6);
                editText.setOnEditorActionListener(new OnEditorActionListener() {
                    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                        textView = null;
                        if (i == 6 || i == 66) {
                            ((InputMethodManager) CaptureActivity.this.getSystemService("input_method")).hideSoftInputFromWindow(editText.getWindowToken(), 0);
                            i = editText.getText().toString();
                            if (i.length() > 64) {
                                textView.setText(CaptureActivity.this.getResources().getString(C0026R.string.edit_text_error_max_length));
                                keyEvent = null;
                            } else {
                                keyEvent = true;
                            }
                            if (i == BuildConfig.FLAVOR || i.length() == 0) {
                                textView.setText(CaptureActivity.this.getResources().getString(C0026R.string.edit_text_error_character));
                                keyEvent = null;
                            }
                            KeyEvent keyEvent2 = keyEvent;
                            for (keyEvent = null; keyEvent < i.length(); keyEvent++) {
                                char charAt = i.charAt(keyEvent);
                                if ((charAt < 'a' || charAt > 'z') && ((charAt < 'A' || charAt > 'Z') && ((charAt < '0' || charAt > '9') && charAt != '_'))) {
                                    textView.setText(CaptureActivity.this.getResources().getString(C0026R.string.edit_text_error_character));
                                    keyEvent2 = null;
                                }
                            }
                            if (CaptureActivity.this.mCaptureNameList.contains(i) != null) {
                                textView.setText(CaptureActivity.this.getResources().getString(C0026R.string.edit_text_error_duplicate_name));
                            } else {
                                textView = keyEvent2;
                            }
                            if (textView != null) {
                                CaptureActivity.this.mCurrentRecordingName = i;
                                CaptureActivity.this._fragment.save(i);
                            }
                        }
                        return textView;
                    }
                });
                view.setOnDismissListener(new C00342());
                view.setOnCancelListener(new C00353());
                view.setView(inflate);
                view.setTitle("Name");
                view.setNegativeButton("Cancel", new C00364());
                view.setPositiveButton("Ok", null);
                view = view.show();
                view.getButton(-1).setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        view = editText.getText().toString();
                        if (view.length() > 64) {
                            textView.setText(CaptureActivity.this.getResources().getString(C0026R.string.edit_text_error_max_length));
                            return;
                        }
                        if (view != BuildConfig.FLAVOR) {
                            if (view.length() != 0) {
                                for (int i = 0; i < view.length(); i++) {
                                    char charAt = view.charAt(i);
                                    if ((charAt < 'a' || charAt > 'z') && ((charAt < 'A' || charAt > 'Z') && ((charAt < '0' || charAt > '9') && charAt != '_'))) {
                                        textView.setText(CaptureActivity.this.getResources().getString(C0026R.string.edit_text_error_character));
                                        return;
                                    }
                                }
                                if (CaptureActivity.this.mCaptureNameList.contains(view)) {
                                    textView.setText(CaptureActivity.this.getResources().getString(C0026R.string.edit_text_error_duplicate_name));
                                    return;
                                }
                                CaptureActivity.this.mCurrentRecordingName = view;
                                CaptureActivity.this._fragment.save(view);
                                view.dismiss();
                                return;
                            }
                        }
                        textView.setText(CaptureActivity.this.getResources().getString(C0026R.string.edit_text_error_character));
                    }
                });
                ((InputMethodManager) CaptureActivity.this.getSystemService("input_method")).showSoftInput(editText, 1);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        ((InputMethodManager) CaptureActivity.this.getSystemService("input_method")).showSoftInput(editText, 1);
                    }
                }, 500);
                CaptureActivity.this.mUILayout.setVisibility(4);
            } else if (CaptureActivity.this.mCurrentMode == 300) {
                CaptureActivity.this.mCurrentRecordingName = CaptureActivity.this.mCurrentCaptureName;
                CaptureActivity.this._fragment.save(CaptureActivity.this.mCurrentCaptureName);
            }
        }
    }

    /* renamed from: com.vuforia.captureapp.captureactivity.CaptureActivity$7 */
    class C00407 implements Runnable {
        C00407() {
        }

        public void run() {
            CaptureActivity.this.mButtonClose.setVisibility(0);
            CaptureActivity.this.mButtonScanning.setVisibility(4);
            CaptureActivity.this.mButtonScanPaused.setVisibility(4);
            CaptureActivity.this.mButtonScanDisabled.setVisibility(0);
            CaptureActivity.this.mButtonSave.setVisibility(4);
            CaptureActivity.this.mPointsCount.setVisibility(4);
            CaptureActivity.this.mCoverageLayout.setVisibility(4);
            if (CaptureActivity.this.mCurrentMode == 100) {
                CaptureActivity.this.mAlignmentOverlay.setVisibility(0);
            }
        }
    }

    /* renamed from: com.vuforia.captureapp.captureactivity.CaptureActivity$8 */
    class C00418 implements Runnable {
        C00418() {
        }

        public void run() {
            CaptureActivity.this.mButtonClose.setVisibility(0);
            CaptureActivity.this.mButtonScanning.setVisibility(4);
            CaptureActivity.this.mButtonScanPaused.setVisibility(0);
            CaptureActivity.this.mButtonScanDisabled.setVisibility(4);
            CaptureActivity.this.mButtonSave.setVisibility(4);
            CaptureActivity.this.mAlignmentOverlay.setVisibility(4);
            if (CaptureActivity.this.mCurrentMode == Constants.INTENT_ACTION_CONTINUE) {
                CaptureActivity.this.mPointsCount.setVisibility(0);
                CaptureActivity.this.mCoverageLayout.setVisibility(0);
            }
        }
    }

    /* renamed from: com.vuforia.captureapp.captureactivity.CaptureActivity$9 */
    class C00429 implements Runnable {
        C00429() {
        }

        public void run() {
            CaptureActivity.this.mPointsCount.setVisibility(0);
            CaptureActivity.this.mCoverageLayout.setVisibility(0);
            CaptureActivity.this.mButtonClose.setVisibility(4);
            CaptureActivity.this.mButtonScanning.setVisibility(0);
            CaptureActivity.this.mButtonScanPaused.setVisibility(4);
            CaptureActivity.this.mButtonScanDisabled.setVisibility(4);
            CaptureActivity.this.mButtonSave.setVisibility(4);
            CaptureActivity.this.mAlignmentOverlay.setVisibility(4);
        }
    }

    private class BuilderNewSequenceTask extends AsyncTask<Void, Integer, Boolean> {
        private BuilderNewSequenceTask() {
        }

        protected Boolean doInBackground(Void... voidArr) {
            Boolean valueOf;
            synchronized (CaptureActivity.this.mShutdownLock) {
                valueOf = Boolean.valueOf(CaptureActivity.this.onQCARCameraRunning() > 0);
            }
            return valueOf;
        }

        protected void onPostExecute(Boolean bool) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("BuilderNewSequenceTask::onPostExecute: execution ");
            stringBuilder.append(bool.booleanValue() ? "successful" : "failed");
            DebugLog.LOGD(stringBuilder.toString());
            bool.booleanValue();
        }
    }

    private class GestureListener extends SimpleOnGestureListener {
        private final Handler autofocusHandler;

        /* renamed from: com.vuforia.captureapp.captureactivity.CaptureActivity$GestureListener$1 */
        class C00431 implements Runnable {
            public void run() {
            }

            C00431() {
            }
        }

        public boolean onDown(MotionEvent motionEvent) {
            return true;
        }

        private GestureListener() {
            this.autofocusHandler = new Handler();
        }

        public boolean onSingleTapUp(MotionEvent motionEvent) {
            CaptureActivity.this.autofocus();
            this.autofocusHandler.postDelayed(new C00431(), 1000);
            return true;
        }
    }

    private class InitQCARTask extends AsyncTask<Void, Integer, Boolean> {
        private int mProgressValue;

        /* renamed from: com.vuforia.captureapp.captureactivity.CaptureActivity$InitQCARTask$1 */
        class C00441 implements DialogInterface.OnClickListener {
            C00441() {
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                System.exit(1);
            }
        }

        protected void onProgressUpdate(Integer... numArr) {
        }

        private InitQCARTask() {
            this.mProgressValue = -1;
        }

        protected Boolean doInBackground(Void... voidArr) {
            Boolean valueOf;
            synchronized (CaptureActivity.this.mShutdownLock) {
                boolean z;
                Vuforia.setInitParameters(CaptureActivity.this, CaptureActivity.this.mQCARFlags, CaptureActivity.sVuforiaKey);
                do {
                    this.mProgressValue = Vuforia.init();
                    z = true;
                    publishProgress(new Integer[]{Integer.valueOf(this.mProgressValue)});
                    if (isCancelled() || this.mProgressValue < 0) {
                    }
                } while (this.mProgressValue < 100);
                if (this.mProgressValue <= 0) {
                    z = false;
                }
                valueOf = Boolean.valueOf(z);
            }
            return valueOf;
        }

        protected void onPostExecute(Boolean bool) {
            if (bool.booleanValue() != null) {
                DebugLog.LOGD("InitQCARTask::onPostExecute: QCAR initialization successful");
                CaptureActivity.this.updateApplicationStatus(2);
                return;
            }
            bool = new Builder(CaptureActivity.this).create();
            bool.setButton(-1, "Close", new C00441());
            CharSequence access$400 = CaptureActivity.this.getInitializationErrorString(this.mProgressValue);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("InitQCARTask::onPostExecute: ");
            stringBuilder.append(access$400);
            stringBuilder.append(" Exiting.");
            DebugLog.LOGE(stringBuilder.toString());
            bool.setMessage(access$400);
            bool.setCancelable(false);
            bool.setCanceledOnTouchOutside(false);
            bool.show();
        }
    }

    private class LoadTrackerTask extends AsyncTask<Void, Integer, Boolean> {

        /* renamed from: com.vuforia.captureapp.captureactivity.CaptureActivity$LoadTrackerTask$1 */
        class C00451 implements DialogInterface.OnClickListener {
            C00451() {
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                System.exit(1);
            }
        }

        private LoadTrackerTask() {
        }

        protected Boolean doInBackground(Void... voidArr) {
            Boolean valueOf;
            synchronized (CaptureActivity.this.mShutdownLock) {
                valueOf = Boolean.valueOf(CaptureActivity.this.loadTrackerData() > 0);
            }
            return valueOf;
        }

        protected void onPostExecute(Boolean bool) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("LoadTrackerTask::onPostExecute: execution ");
            stringBuilder.append(bool.booleanValue() ? "successful" : "failed");
            DebugLog.LOGD(stringBuilder.toString());
            if (bool.booleanValue() != null) {
                CaptureActivity.this.mIsLegoDataSetActive = true;
                CaptureActivity.this.updateApplicationStatus(5);
                return;
            }
            bool = new Builder(CaptureActivity.this).create();
            bool.setButton(-1, "Close", new C00451());
            bool.setMessage("Failed to load tracker data.");
            bool.show();
        }
    }

    static class LoadingDialogHandler extends Handler {
        private final WeakReference<CaptureActivity> mCaptureApp;

        LoadingDialogHandler(CaptureActivity captureActivity) {
            this.mCaptureApp = new WeakReference(captureActivity);
        }

        public void handleMessage(Message message) {
            CaptureActivity captureActivity = (CaptureActivity) this.mCaptureApp.get();
            if (captureActivity != null) {
                if (message.what == 1) {
                    captureActivity.mLoadingDialogContainer.setVisibility(0);
                } else if (message.what == null) {
                    captureActivity.mLoadingDialogContainer.setVisibility(8);
                }
            }
        }
    }

    private native boolean activateFlash(boolean z);

    private native boolean autofocus();

    private native boolean builderCloseSequenceUI();

    private native boolean builderNewSequenceUI();

    private native boolean builderOpenSequenceUI(String str);

    private native boolean builderSaveSequenceUI(String str);

    private native boolean builderStartClipUI();

    private native boolean builderStopClipUI();

    private native void cycleAugmentationMode(boolean z);

    private native void deinitApplicationNative();

    private int getInitializationFlags() {
        return 1;
    }

    private native void initApplicationNative(int i, int i2);

    private native void setActivityPortraitMode(boolean z);

    private native boolean setFocusMode(int i);

    private native void startCamera();

    private native void stopCamera();

    public native void deinitTracker();

    public native void destroyTrackerData();

    public native int getOpenGlEsVersionNative();

    public native int initTracker();

    public native int loadTrackerData();

    public native void nativeTouchEvent(int i, int i2, float f, float f2);

    public native int onQCARCameraRunning();

    public native void onQCARInitializedNative();

    public native void onRecordingStarted(boolean z);

    public native void setVisualizationMode(int i);

    static {
        loadLibrary(NATIVE_LIB_VUFORIA);
        loadLibrary(NATIVE_LIB_SAMPLE);
    }

    private String getInitializationErrorString(int i) {
        if (i == -2) {
            return getString(C0026R.string.INIT_ERROR_DEVICE_NOT_SUPPORTED);
        }
        if (i == -3) {
            return getString(C0026R.string.INIT_ERROR_NO_CAMERA_ACCESS);
        }
        if (i == -4) {
            return getString(C0026R.string.INIT_LICENSE_ERROR_MISSING_KEY);
        }
        if (i == -5) {
            return getString(C0026R.string.INIT_LICENSE_ERROR_INVALID_KEY);
        }
        if (i == -7) {
            return getString(C0026R.string.INIT_LICENSE_ERROR_NO_NETWORK_TRANSIENT);
        }
        if (i == -6) {
            return getString(C0026R.string.INIT_LICENSE_ERROR_NO_NETWORK_PERMANENT);
        }
        if (i == -8) {
            return getString(C0026R.string.INIT_LICENSE_ERROR_CANCELED_KEY);
        }
        return getString(C0026R.string.INIT_LICENSE_ERROR_UNKNOWN_ERROR);
    }

    private void storeScreenDimensions() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.mScreenWidth = displayMetrics.widthPixels;
        this.mScreenHeight = displayMetrics.heightPixels;
    }

    protected void onCreate(Bundle bundle) {
        DebugLog.LOGD("CaptureApp::onCreate");
        super.onCreate(bundle);
        this.mCurrentMode = 100;
        this.mCurrentCaptureName = null;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.mCurrentMode = extras.getInt(Constants.INTENT_ACTION, 100);
            this.mCurrentCaptureName = extras.getString(Constants.INTENT_CAPTURE_NAME);
            this.mCaptureNameList = extras.getStringArrayList(Constants.INTENT_CAPTURE_LIST);
            this.mNbPoints = extras.getInt(Constants.INTENT_CAPTURE_NB_POINTS);
        }
        if (this.mCurrentMode == 300) {
            this.mCurrentRecordingName = this.mCurrentCaptureName;
        }
        bundle = getFragmentManager();
        this._fragment = (PersitenceModelFragment) bundle.findFragmentByTag(TAG_FRAGMENT);
        if (this._fragment == null) {
            this._fragment = new PersitenceModelFragment();
            bundle.beginTransaction().add(this._fragment, TAG_FRAGMENT).commit();
        }
        this.mCurrentJniState = -1;
        this.mTextures = new Vector();
        loadTextures();
        requestWindowFeature(1);
        getWindow().setFlags(PIXEL_FORMAT.YUYV, PIXEL_FORMAT.YUYV);
        this.mQCARFlags = getInitializationFlags();
        this.mGestureDetector = new GestureDetector(this, new GestureListener());
        updateApplicationStatus(null);
    }

    private void loadTextures() {
        this.mTextures.add(Texture.loadTextureFromApk("TestTexture.png", getAssets()));
    }

    protected void onResume() {
        DebugLog.LOGD("CaptureApp::onResume");
        super.onResume();
        Vuforia.onResume();
        if (this.mAppStatus == 6) {
            updateApplicationStatus(7);
        }
        if (this.mGlView != null) {
            this.mGlView.setVisibility(0);
            this.mGlView.onResume();
        }
        if (this.mIsRecording) {
            this.mIsRecording = false;
            setUIStateRecordingPaused();
        }
    }

    private void updateActivityOrientation() {
        boolean z = false;
        switch (getResources().getConfiguration().orientation) {
            case 1:
                z = true;
                break;
            case 2:
                break;
            default:
                break;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Activity is in ");
        stringBuilder.append(z ? "PORTRAIT" : "LANDSCAPE");
        DebugLog.LOGI(stringBuilder.toString());
        setActivityPortraitMode(z);
    }

    public void updateRenderView() {
        int rotation = getWindowManager().getDefaultDisplay().getRotation();
        if (rotation != this.mLastScreenRotation && Vuforia.isInitialized() && this.mAppStatus == 7) {
            DebugLog.LOGD("CaptureApp::updateRenderView");
            storeScreenDimensions();
            this.mRenderer.updateRendering(this.mScreenWidth, this.mScreenHeight);
            this.mLastScreenRotation = rotation;
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        DebugLog.LOGD("CaptureApp::onConfigurationChanged");
        super.onConfigurationChanged(configuration);
    }

    protected void onPause() {
        DebugLog.LOGD("CaptureApp::onPause");
        super.onPause();
        if (this.mGlView != null) {
            this.mGlView.setVisibility(4);
            this.mGlView.onPause();
        }
        if (this.mAppStatus == 7) {
            updateApplicationStatus(6);
        }
        Vuforia.onPause();
    }

    protected void onDestroy() {
        super.onDestroy();
        if (!(this.mInitQCARTask == null || this.mInitQCARTask.getStatus() == Status.FINISHED)) {
            this.mInitQCARTask.cancel(true);
            this.mInitQCARTask = null;
        }
        if (!(this.mLoadTrackerTask == null || this.mLoadTrackerTask.getStatus() == Status.FINISHED)) {
            this.mLoadTrackerTask.cancel(true);
            this.mLoadTrackerTask = null;
        }
        if (!(this.mBuilderNewSequenceTask == null || this.mBuilderNewSequenceTask.getStatus() == Status.FINISHED)) {
            this.mBuilderNewSequenceTask.cancel(true);
            this.mBuilderNewSequenceTask = null;
        }
        synchronized (this.mShutdownLock) {
            deinitApplicationNative();
            this.mTextures.clear();
            this.mTextures = null;
            destroyTrackerData();
            deinitTracker();
            Vuforia.deinit();
        }
        System.gc();
    }

    public int getTextureCount() {
        return this.mTextures.size();
    }

    public Texture getTexture(int i) {
        return (Texture) this.mTextures.elementAt(i);
    }

    public void changeOfCaptureState(int i, final int i2) {
        if (this.mCurrentJniState != i || i2 > 0) {
            this.mCurrentJniState = i;
            if (i != CAPTURE_NB_IMAGE_POINTS) {
                switch (i) {
                    case 100:
                        setRecordingUIReadyToRecord();
                        return;
                    case 101:
                        setRecordingUIStateInitial();
                        return;
                    case 102:
                        setCaptureSavedState();
                        return;
                    default:
                        return;
                }
            } else if (this.mPointsCount != 0) {
                if (i2 > 0) {
                    this.mNbPoints = i2;
                }
                runOnUiThread(new Runnable() {
                    public void run() {
                        CaptureActivity.this.mPointsCount.setText(String.format("Points %d", new Object[]{Integer.valueOf(i2)}));
                    }
                });
            }
        }
    }

    public void saveDomePanelsState(int[] iArr) {
        for (int i = 0; i < this.mDomeFacets.length; i++) {
            String str = TAG_FRAGMENT;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(BuildConfig.FLAVOR);
            stringBuilder.append(iArr[i]);
            Log.d(str, stringBuilder.toString());
            this.mDomeFacets[i] = iArr[i];
        }
    }

    private void setCaptureSavedState() {
        if (this.mNbRecordingClips == 0) {
            builderCloseSequenceUI();
            return;
        }
        builderStopClipUI();
        builderCloseSequenceUI();
    }

    private synchronized void updateApplicationStatus(int r6) {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1630521067.run(Unknown Source)
*/
        /*
        r5 = this;
        monitor-enter(r5);
        r0 = r5.mAppStatus;	 Catch:{ all -> 0x011e }
        if (r0 != r6) goto L_0x0007;
    L_0x0005:
        monitor-exit(r5);
        return;
    L_0x0007:
        r5.mAppStatus = r6;	 Catch:{ all -> 0x011e }
        r6 = r5.mAppStatus;	 Catch:{ all -> 0x011e }
        r0 = -1;	 Catch:{ all -> 0x011e }
        r1 = 1;	 Catch:{ all -> 0x011e }
        r2 = 0;	 Catch:{ all -> 0x011e }
        r3 = 0;	 Catch:{ all -> 0x011e }
        switch(r6) {
            case 0: goto L_0x00d8;
            case 1: goto L_0x00c3;
            case 2: goto L_0x00b8;
            case 3: goto L_0x00b0;
            case 4: goto L_0x009b;
            case 5: goto L_0x0077;
            case 6: goto L_0x0072;
            case 7: goto L_0x0016;
            default: goto L_0x0012;
        };	 Catch:{ all -> 0x011e }
    L_0x0012:
        r6 = new java.lang.RuntimeException;	 Catch:{ all -> 0x011e }
        goto L_0x0118;	 Catch:{ all -> 0x011e }
    L_0x0016:
        r5.startCamera();	 Catch:{ all -> 0x011e }
        r6 = r5.loadingDialogHandler;	 Catch:{ all -> 0x011e }
        r6.sendEmptyMessage(r3);	 Catch:{ all -> 0x011e }
        r6 = r5.mUILayout;	 Catch:{ all -> 0x011e }
        r6.setBackgroundColor(r3);	 Catch:{ all -> 0x011e }
        r6 = r5.setFocusMode(r1);	 Catch:{ all -> 0x011e }
        if (r6 != 0) goto L_0x002c;	 Catch:{ all -> 0x011e }
    L_0x0029:
        r5.setFocusMode(r3);	 Catch:{ all -> 0x011e }
    L_0x002c:
        r6 = r5.mCurrentMode;	 Catch:{ all -> 0x011e }
        r0 = 100;	 Catch:{ all -> 0x011e }
        if (r6 != r0) goto L_0x0053;	 Catch:{ all -> 0x011e }
    L_0x0032:
        r6 = r5.mNbRecordingClips;	 Catch:{ all -> 0x011e }
        if (r6 != 0) goto L_0x0053;	 Catch:{ all -> 0x011e }
    L_0x0036:
        r6 = r5.mBuilderNewSequenceTask;	 Catch:{ all -> 0x011e }
        if (r6 != 0) goto L_0x004e;
    L_0x003a:
        r6 = new com.vuforia.captureapp.captureactivity.CaptureActivity$BuilderNewSequenceTask;	 Catch:{ Exception -> 0x0049 }
        r6.<init>();	 Catch:{ Exception -> 0x0049 }
        r5.mBuilderNewSequenceTask = r6;	 Catch:{ Exception -> 0x0049 }
        r6 = r5.mBuilderNewSequenceTask;	 Catch:{ Exception -> 0x0049 }
        r0 = new java.lang.Void[r3];	 Catch:{ Exception -> 0x0049 }
        r6.execute(r0);	 Catch:{ Exception -> 0x0049 }
        goto L_0x004e;
    L_0x0049:
        r6 = "New Sequence Task failed";	 Catch:{ all -> 0x011e }
        com.vuforia.captureapp.captureactivity.DebugLog.LOGE(r6);	 Catch:{ all -> 0x011e }
    L_0x004e:
        r5.setRecordingUIStateInitial();	 Catch:{ all -> 0x011e }
        goto L_0x0116;	 Catch:{ all -> 0x011e }
    L_0x0053:
        r6 = r5.mCurrentMode;	 Catch:{ all -> 0x011e }
        r0 = 300; // 0x12c float:4.2E-43 double:1.48E-321;	 Catch:{ all -> 0x011e }
        if (r6 == r0) goto L_0x0069;	 Catch:{ all -> 0x011e }
    L_0x0059:
        r6 = r5.mCurrentMode;	 Catch:{ all -> 0x011e }
        r0 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;	 Catch:{ all -> 0x011e }
        if (r6 != r0) goto L_0x0060;	 Catch:{ all -> 0x011e }
    L_0x005f:
        goto L_0x0069;	 Catch:{ all -> 0x011e }
    L_0x0060:
        r6 = r5.mNbRecordingClips;	 Catch:{ all -> 0x011e }
        if (r6 != 0) goto L_0x0116;	 Catch:{ all -> 0x011e }
    L_0x0064:
        r5.setRecordingUIStateInitial();	 Catch:{ all -> 0x011e }
        goto L_0x0116;	 Catch:{ all -> 0x011e }
    L_0x0069:
        r6 = r5._fragment;	 Catch:{ all -> 0x011e }
        r0 = r5.mCurrentCaptureName;	 Catch:{ all -> 0x011e }
        r6.load(r0);	 Catch:{ all -> 0x011e }
        goto L_0x0116;	 Catch:{ all -> 0x011e }
    L_0x0072:
        r5.stopCamera();	 Catch:{ all -> 0x011e }
        goto L_0x0116;	 Catch:{ all -> 0x011e }
    L_0x0077:
        java.lang.System.gc();	 Catch:{ all -> 0x011e }
        r6 = r5.mCurrentMode;	 Catch:{ all -> 0x011e }
        r5.setVisualizationMode(r6);	 Catch:{ all -> 0x011e }
        r5.onQCARInitializedNative();	 Catch:{ all -> 0x011e }
        r6 = r5.mRenderer;	 Catch:{ all -> 0x011e }
        r6.mIsActive = r1;	 Catch:{ all -> 0x011e }
        r6 = r5.mGlView;	 Catch:{ all -> 0x011e }
        r1 = new android.view.ViewGroup$LayoutParams;	 Catch:{ all -> 0x011e }
        r1.<init>(r0, r0);	 Catch:{ all -> 0x011e }
        r5.addContentView(r6, r1);	 Catch:{ all -> 0x011e }
        r6 = r5.mUILayout;	 Catch:{ all -> 0x011e }
        r6.bringToFront();	 Catch:{ all -> 0x011e }
        r6 = 7;	 Catch:{ all -> 0x011e }
        r5.updateApplicationStatus(r6);	 Catch:{ all -> 0x011e }
        goto L_0x0116;
    L_0x009b:
        r6 = new com.vuforia.captureapp.captureactivity.CaptureActivity$LoadTrackerTask;	 Catch:{ Exception -> 0x00aa }
        r6.<init>();	 Catch:{ Exception -> 0x00aa }
        r5.mLoadTrackerTask = r6;	 Catch:{ Exception -> 0x00aa }
        r6 = r5.mLoadTrackerTask;	 Catch:{ Exception -> 0x00aa }
        r0 = new java.lang.Void[r3];	 Catch:{ Exception -> 0x00aa }
        r6.execute(r0);	 Catch:{ Exception -> 0x00aa }
        goto L_0x0116;
    L_0x00aa:
        r6 = "Loading tracking data set failed";	 Catch:{ all -> 0x011e }
        com.vuforia.captureapp.captureactivity.DebugLog.LOGE(r6);	 Catch:{ all -> 0x011e }
        goto L_0x0116;	 Catch:{ all -> 0x011e }
    L_0x00b0:
        r5.initApplicationAR();	 Catch:{ all -> 0x011e }
        r6 = 4;	 Catch:{ all -> 0x011e }
        r5.updateApplicationStatus(r6);	 Catch:{ all -> 0x011e }
        goto L_0x0116;	 Catch:{ all -> 0x011e }
    L_0x00b8:
        r6 = r5.initTracker();	 Catch:{ all -> 0x011e }
        if (r6 <= 0) goto L_0x0116;	 Catch:{ all -> 0x011e }
    L_0x00be:
        r6 = 3;	 Catch:{ all -> 0x011e }
        r5.updateApplicationStatus(r6);	 Catch:{ all -> 0x011e }
        goto L_0x0116;
    L_0x00c3:
        r6 = new com.vuforia.captureapp.captureactivity.CaptureActivity$InitQCARTask;	 Catch:{ Exception -> 0x00d2 }
        r6.<init>();	 Catch:{ Exception -> 0x00d2 }
        r5.mInitQCARTask = r6;	 Catch:{ Exception -> 0x00d2 }
        r6 = r5.mInitQCARTask;	 Catch:{ Exception -> 0x00d2 }
        r0 = new java.lang.Void[r3];	 Catch:{ Exception -> 0x00d2 }
        r6.execute(r0);	 Catch:{ Exception -> 0x00d2 }
        goto L_0x0116;
    L_0x00d2:
        r6 = "Initializing QCAR SDK failed";	 Catch:{ all -> 0x011e }
        com.vuforia.captureapp.captureactivity.DebugLog.LOGE(r6);	 Catch:{ all -> 0x011e }
        goto L_0x0116;	 Catch:{ all -> 0x011e }
    L_0x00d8:
        r6 = android.view.LayoutInflater.from(r5);	 Catch:{ all -> 0x011e }
        r4 = 2131099652; // 0x7f060004 float:1.7811663E38 double:1.052903126E-314;	 Catch:{ all -> 0x011e }
        r6 = r6.inflate(r4, r2, r3);	 Catch:{ all -> 0x011e }
        r6 = (android.widget.RelativeLayout) r6;	 Catch:{ all -> 0x011e }
        r5.mUILayout = r6;	 Catch:{ all -> 0x011e }
        r6 = r5.mUILayout;	 Catch:{ all -> 0x011e }
        r6.setVisibility(r3);	 Catch:{ all -> 0x011e }
        r6 = r5.mUILayout;	 Catch:{ all -> 0x011e }
        r2 = -16777216; // 0xffffffffff000000 float:-1.7014118E38 double:NaN;	 Catch:{ all -> 0x011e }
        r6.setBackgroundColor(r2);	 Catch:{ all -> 0x011e }
        r5.prepareRecordingUIState();	 Catch:{ all -> 0x011e }
        r6 = r5.mUILayout;	 Catch:{ all -> 0x011e }
        r2 = 2131034146; // 0x7f050022 float:1.7678801E38 double:1.0528707617E-314;	 Catch:{ all -> 0x011e }
        r6 = r6.findViewById(r2);	 Catch:{ all -> 0x011e }
        r5.mLoadingDialogContainer = r6;	 Catch:{ all -> 0x011e }
        r6 = r5.loadingDialogHandler;	 Catch:{ all -> 0x011e }
        r6.sendEmptyMessage(r1);	 Catch:{ all -> 0x011e }
        r6 = r5.mUILayout;	 Catch:{ all -> 0x011e }
        r2 = new android.view.ViewGroup$LayoutParams;	 Catch:{ all -> 0x011e }
        r2.<init>(r0, r0);	 Catch:{ all -> 0x011e }
        r5.addContentView(r6, r2);	 Catch:{ all -> 0x011e }
        r5.initApplication();	 Catch:{ all -> 0x011e }
        r5.updateApplicationStatus(r1);	 Catch:{ all -> 0x011e }
    L_0x0116:
        monitor-exit(r5);
        return;
    L_0x0118:
        r0 = "Invalid application state";	 Catch:{ all -> 0x011e }
        r6.<init>(r0);	 Catch:{ all -> 0x011e }
        throw r6;	 Catch:{ all -> 0x011e }
    L_0x011e:
        r6 = move-exception;
        monitor-exit(r5);
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.captureapp.captureactivity.CaptureActivity.updateApplicationStatus(int):void");
    }

    private void initApplication() {
        setRequestedOrientation(0);
        updateActivityOrientation();
        storeScreenDimensions();
        getWindow().setFlags(PIXEL_FORMAT.NV12, PIXEL_FORMAT.NV12);
    }

    private void initApplicationAR() {
        initApplicationNative(this.mScreenWidth, this.mScreenHeight);
        boolean requiresAlpha = Vuforia.requiresAlpha();
        this.mGlView = new CaptureGLView(this);
        this.mGlView.init(this.mQCARFlags, requiresAlpha, 16, 0);
        this.mRenderer = new CaptureGLRenderer();
        this.mRenderer.mActivity = this;
        this.mGlView.setRenderer(this.mRenderer);
        this.mGlView.setPreserveEGLContextOnPause(true);
    }

    public static boolean loadLibrary(java.lang.String r2) {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1630521067.run(Unknown Source)
*/
        /*
        java.lang.System.loadLibrary(r2);	 Catch:{ UnsatisfiedLinkError -> 0x0038, SecurityException -> 0x001e }
        r0 = new java.lang.StringBuilder;	 Catch:{ UnsatisfiedLinkError -> 0x0038, SecurityException -> 0x001e }
        r0.<init>();	 Catch:{ UnsatisfiedLinkError -> 0x0038, SecurityException -> 0x001e }
        r1 = "Native library lib";	 Catch:{ UnsatisfiedLinkError -> 0x0038, SecurityException -> 0x001e }
        r0.append(r1);	 Catch:{ UnsatisfiedLinkError -> 0x0038, SecurityException -> 0x001e }
        r0.append(r2);	 Catch:{ UnsatisfiedLinkError -> 0x0038, SecurityException -> 0x001e }
        r1 = ".so loaded";	 Catch:{ UnsatisfiedLinkError -> 0x0038, SecurityException -> 0x001e }
        r0.append(r1);	 Catch:{ UnsatisfiedLinkError -> 0x0038, SecurityException -> 0x001e }
        r0 = r0.toString();	 Catch:{ UnsatisfiedLinkError -> 0x0038, SecurityException -> 0x001e }
        com.vuforia.captureapp.captureactivity.DebugLog.LOGI(r0);	 Catch:{ UnsatisfiedLinkError -> 0x0038, SecurityException -> 0x001e }
        r2 = 1;
        return r2;
    L_0x001e:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "The library lib";
        r0.append(r1);
        r0.append(r2);
        r2 = ".so was not allowed to be loaded";
        r0.append(r2);
        r2 = r0.toString();
        com.vuforia.captureapp.captureactivity.DebugLog.LOGE(r2);
        goto L_0x0051;
    L_0x0038:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "The library lib";
        r0.append(r1);
        r0.append(r2);
        r2 = ".so could not be loaded";
        r0.append(r2);
        r2 = r0.toString();
        com.vuforia.captureapp.captureactivity.DebugLog.LOGE(r2);
    L_0x0051:
        r2 = 0;
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vuforia.captureapp.captureactivity.CaptureActivity.loadLibrary(java.lang.String):boolean");
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        int i = (65280 & actionMasked) >> 8;
        switch (actionMasked) {
            case 0:
            case 5:
                actionMasked = 0;
                break;
            case 1:
            case 6:
                actionMasked = 2;
                break;
            case 2:
                actionMasked = 1;
                break;
            case 3:
                actionMasked = 3;
                break;
            default:
                actionMasked = -1;
                break;
        }
        if (i == 0) {
            nativeTouchEvent(actionMasked, motionEvent.getPointerId(i), motionEvent.getX(i), motionEvent.getY(i));
        }
        return this.mGestureDetector.onTouchEvent(motionEvent);
    }

    private float getMemoryConsumption() {
        MemoryInfo memoryInfo = new MemoryInfo();
        ((ActivityManager) getSystemService("activity")).getMemoryInfo(memoryInfo);
        return (((float) memoryInfo.availMem) / (((float) memoryInfo.totalMem) * 1.0f)) * 100.0f;
    }

    private void prepareRecordingUIState() {
        this.mAlignmentOverlay = (LinearLayout) this.mUILayout.findViewById(C0026R.id.alignment_overlay);
        this.mButtonClose = (ImageView) this.mUILayout.findViewById(C0026R.id.button_close);
        this.mButtonScanning = (ImageView) this.mUILayout.findViewById(C0026R.id.button_scanning);
        this.mButtonScanPaused = (ImageView) this.mUILayout.findViewById(C0026R.id.button_scan_ready_to_record);
        this.mButtonScanDisabled = (ImageView) this.mUILayout.findViewById(C0026R.id.button_scan_disabled);
        this.mButtonSave = (ImageView) this.mUILayout.findViewById(C0026R.id.button_save);
        this.mCoverageLayout = (LinearLayout) this.mUILayout.findViewById(C0026R.id.coverage_layout);
        this.mCoverageImage = (ImageView) this.mUILayout.findViewById(C0026R.id.coverage_image);
        this.mPointsCount = (TextView) this.mUILayout.findViewById(C0026R.id.points_count);
        this.mMemoryConsumption = (TextView) this.mUILayout.findViewById(C0026R.id.memory_consumption);
        this.mIsRecording = false;
        this.mNbRecordingClips = 0;
        this.mCoverageLayout.setOnClickListener(new C00282());
        this.mButtonClose.setOnClickListener(new C00303());
        this.mButtonScanPaused.setOnClickListener(new C00314());
        this.mButtonScanning.setOnClickListener(new C00325());
        this.mButtonSave.setOnClickListener(new C00396());
    }

    private void setRecordingUIStateInitial() {
        runOnUiThread(new C00407());
    }

    private void setRecordingUIReadyToRecord() {
        runOnUiThread(new C00418());
    }

    private void setRecordingUIStateRecording() {
        runOnUiThread(new C00429());
    }

    private void setUIStateRecordingPaused() {
        runOnUiThread(new Runnable() {
            public void run() {
                CaptureActivity.this.mPointsCount.setVisibility(0);
                CaptureActivity.this.mCoverageLayout.setVisibility(0);
                CaptureActivity.this.mButtonClose.setVisibility(0);
                CaptureActivity.this.mButtonScanning.setVisibility(4);
                CaptureActivity.this.mButtonScanPaused.setVisibility(0);
                CaptureActivity.this.mButtonScanDisabled.setVisibility(4);
                CaptureActivity.this.mAlignmentOverlay.setVisibility(4);
                CaptureActivity.this.mButtonSave.setVisibility(0);
            }
        });
    }

    private void setUIStateTest() {
        runOnUiThread(new Runnable() {
            public void run() {
                CaptureActivity.this.mButtonClose.setVisibility(0);
                CaptureActivity.this.mButtonScanning.setVisibility(4);
                CaptureActivity.this.mButtonScanPaused.setVisibility(4);
                CaptureActivity.this.mButtonScanDisabled.setVisibility(4);
                CaptureActivity.this.mButtonSave.setVisibility(4);
                CaptureActivity.this.mPointsCount.setVisibility(4);
                CaptureActivity.this.mCoverageLayout.setVisibility(4);
                CaptureActivity.this.mAlignmentOverlay.setVisibility(4);
            }
        });
    }

    private void endActivity(String str) {
        Intent intent = new Intent();
        if ((str == null || str.length() <= 0) && this.mCurrentMode != Constants.INTENT_ACTION_CONTINUE) {
            setResult(101, intent);
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("/sdcard/VuforiaObjectScanner/ObjectReco/");
            stringBuilder.append(this.mCurrentRecordingName);
            stringBuilder.append(".od");
            File file = new File(stringBuilder.toString());
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String str2 = TAG_FRAGMENT;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("file");
            stringBuilder2.append(this.mCurrentRecordingName);
            stringBuilder2.append(" ,Size = ");
            stringBuilder2.append(file.length());
            Log.d(str2, stringBuilder2.toString());
            this.mFileSize = ((float) file.length()) / 1048576.0f;
            intent.putExtra(Constants.INTENT_CAPTURE_NAME, str);
            intent.putExtra("nbPoints", this.mNbPoints);
            intent.putExtra("mFileSize", this.mFileSize);
            intent.putExtra("mDomeFacets", this.mDomeFacets);
            if (str != null) {
                Util.prepareMetadataDirectory(str, this.mNbPoints, this.mFileSize, this.mDomeFacets);
            }
            setResult(102, intent);
        }
        finish();
        System.exit(null);
    }

    public void onBackPressed() {
        if (this.mNbRecordingClips == 0) {
            super.onBackPressed();
            System.exit(0);
            return;
        }
        if (this.mIsRecording) {
            builderStopClipUI();
            this.mIsRecording = false;
            setUIStateRecordingPaused();
        }
        DialogInterface.OnClickListener anonymousClass12 = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == -1) {
                    CaptureActivity.this.endActivity(0);
                }
            }
        };
        ((TextView) new Builder(this).setMessage("\nData from this scan will be lost. Continue?\n").setPositiveButton("Yes", anonymousClass12).setNegativeButton("No", anonymousClass12).setTitle("Discard scan").show().findViewById(16908299)).setGravity(3);
    }

    public void storeCameraImage(byte[] bArr, int i, int i2) {
        try {
            YuvImage yuvImage = new YuvImage(bArr, 17, i, i2, null);
            String path = Environment.getExternalStorageDirectory().getPath();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(path);
            stringBuilder.append("/VuforiaObjectScanner/capture.jpg");
            File file = new File(stringBuilder.toString());
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            OutputStream fileOutputStream = new FileOutputStream(file);
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream(bArr.length);
            yuvImage.compressToJpeg(new Rect(0, 0, i - 1, i2 - 1), 90, byteArrayOutputStream);
            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.size());
            Matrix matrix = new Matrix();
            if (getResources().getConfiguration().orientation == 1) {
                matrix.postRotate(1119092736);
            }
            Bitmap.createBitmap(decodeByteArray, 0, 0, decodeByteArray.getWidth(), decodeByteArray.getHeight(), matrix, true).compress(CompressFormat.JPEG, 70, fileOutputStream);
            fileOutputStream.close();
        } catch (byte[] bArr2) {
            bArr2.printStackTrace();
        }
    }

    public void storeCameraImageWithAugmentation(byte[] bArr, int i, int i2) {
        try {
            Buffer allocateDirect = ByteBuffer.allocateDirect((i2 * i) * 4);
            allocateDirect.put(bArr);
            allocateDirect.rewind();
            Bitmap createBitmap = Bitmap.createBitmap(i, i2, Config.ARGB_8888);
            createBitmap.copyPixelsFromBuffer(allocateDirect);
            bArr = Environment.getExternalStorageDirectory().getPath();
            i2 = new StringBuilder();
            i2.append(bArr);
            i2.append("/VuforiaObjectScanner/capture.jpg");
            i = new File(i2.toString());
            if (i.getParentFile().exists() == null) {
                i.getParentFile().mkdirs();
            }
            bArr = new FileOutputStream(i);
            Matrix matrix = new Matrix();
            if (getResources().getConfiguration().orientation == 1) {
                matrix.postRotate(1119092736);
            }
            matrix.postScale(1065353216, -1082130432);
            Bitmap.createBitmap(createBitmap, 0, 0, createBitmap.getWidth(), createBitmap.getHeight(), matrix, true).compress(CompressFormat.JPEG, 70, bArr);
            bArr.close();
        } catch (byte[] bArr2) {
            bArr2.printStackTrace();
        }
    }

    public boolean doSave(String str) {
        return builderSaveSequenceUI(str);
    }

    public void onSaveDone() {
        endActivity(this.mCurrentRecordingName);
    }

    public boolean doLoad(String str) {
        return builderOpenSequenceUI(str);
    }

    public void onLoadDone() {
        if (this.mCurrentMode == Constants.INTENT_ACTION_CONTINUE) {
            if (this.mPointsCount != null && this.mNbPoints > 0) {
                final int i = this.mNbPoints;
                runOnUiThread(new Runnable() {
                    public void run() {
                        CaptureActivity.this.mPointsCount.setText(String.format("Points %d", new Object[]{Integer.valueOf(i)}));
                    }
                });
            }
            setRecordingUIReadyToRecord();
        } else if (this.mCurrentMode == Constants.INTENT_ACTION_TEST) {
            setUIStateTest();
        }
    }
}
