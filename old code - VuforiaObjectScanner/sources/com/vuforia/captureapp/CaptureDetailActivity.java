package com.vuforia.captureapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StatFs;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import com.vuforia.PIXEL_FORMAT;
import com.vuforia.captureapp.captureactivity.CaptureActivity;
import com.vuforia.captureapp.model.CaptureInformation;
import com.vuforia.captureapp.model.CaptureModelFragment;
import com.vuforia.captureapp.model.CaptureModelFragment.ShareCallback;
import java.io.File;

public class CaptureDetailActivity extends Activity implements ShareCallback {
    private static final String TAG_FRAGMENT = "tag_fragment_capture_model_detail";
    private CaptureInformation _captureInformation;
    private CaptureModelFragment _fragment;

    /* renamed from: com.vuforia.captureapp.CaptureDetailActivity$1 */
    class C00111 implements OnClickListener {
        C00111() {
        }

        public void onClick(View view) {
            view = new Intent(CaptureDetailActivity.this, CaptureActivity.class);
            view.putExtra(Constants.INTENT_ACTION, Constants.INTENT_ACTION_TEST);
            view.putExtra(Constants.INTENT_CAPTURE_NAME, CaptureDetailActivity.this._captureInformation.getItemName());
            view.putExtra(Constants.INTENT_CAPTURE_NB_POINTS, CaptureDetailActivity.this._captureInformation.getNbPoints());
            CaptureDetailActivity.this.startActivityForResult(view, 0);
        }
    }

    /* renamed from: com.vuforia.captureapp.CaptureDetailActivity$2 */
    class C00132 implements OnClickListener {

        /* renamed from: com.vuforia.captureapp.CaptureDetailActivity$2$1 */
        class C00121 implements DialogInterface.OnClickListener {
            C00121() {
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }

        C00132() {
        }

        public void onClick(View view) {
            if (CaptureDetailActivity.this.freeDiskSpace() / 1048576 >= ((long) CaptureListActivity.MIN_MB_TO_ENABLE_SCAN)) {
                view = new Intent(CaptureDetailActivity.this, CaptureActivity.class);
                view.putExtra(Constants.INTENT_ACTION, Constants.INTENT_ACTION_CONTINUE);
                view.putExtra(Constants.INTENT_CAPTURE_NAME, CaptureDetailActivity.this._captureInformation.getItemName());
                view.putExtra(Constants.INTENT_CAPTURE_NB_POINTS, CaptureDetailActivity.this._captureInformation.getNbPoints());
                CaptureDetailActivity.this.startActivityForResult(view, 0);
                return;
            }
            view = new Builder(CaptureDetailActivity.this);
            ((TextView) view.setMessage("\nNot enough storage space to scan\nClear space and try again\n").setPositiveButton("OK", new C00121()).setTitle("Insufficient Memory").show().findViewById(16908299)).setGravity(3);
        }
    }

    /* renamed from: com.vuforia.captureapp.CaptureDetailActivity$3 */
    class C00143 implements DialogInterface.OnClickListener {
        C00143() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.cancel();
        }
    }

    /* renamed from: com.vuforia.captureapp.CaptureDetailActivity$5 */
    class C00165 implements DialogInterface.OnClickListener {
        C00165() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.cancel();
        }
    }

    public void onShareDone() {
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this._captureInformation = (CaptureInformation) bundle.getSerializable("captureInformation");
        }
        getWindow().setFlags(PIXEL_FORMAT.YUYV, PIXEL_FORMAT.YUYV);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().setSoftInputMode(32);
        setContentView(C0026R.layout.activity_capture_detail);
        bundle = getFragmentManager();
        this._fragment = (CaptureModelFragment) bundle.findFragmentByTag(TAG_FRAGMENT);
        if (this._fragment == null) {
            this._fragment = new CaptureModelFragment();
            bundle.beginTransaction().add(this._fragment, TAG_FRAGMENT).commit();
        }
        bundle = getIntent().getExtras();
        if (!(bundle == null || bundle == null || this._captureInformation != null)) {
            this._captureInformation = (CaptureInformation) bundle.getSerializable(Constants.INTENT_ITEM);
        }
        setTitle(this._captureInformation.getItemName());
        ((ImageView) findViewById(C0026R.id.item_capture_image)).setImageBitmap(BitmapFactory.decodeFile(this._captureInformation.getImagePath()));
        ((TextView) findViewById(C0026R.id.capture_name)).setText(this._captureInformation.getItemName());
        TextView textView = (TextView) findViewById(C0026R.id.capture_points);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BuildConfig.FLAVOR);
        stringBuilder.append(this._captureInformation.getNbPoints());
        textView.setText(stringBuilder.toString());
        textView = (TextView) findViewById(C0026R.id.capture_file_size);
        stringBuilder = new StringBuilder();
        stringBuilder.append(BuildConfig.FLAVOR);
        stringBuilder.append(String.format("%.1f", new Object[]{Float.valueOf(this._captureInformation.getFileSize())}));
        stringBuilder.append(" MB");
        textView.setText(stringBuilder.toString());
        ((TextView) findViewById(C0026R.id.last_modified)).setText(this._captureInformation.getFormatedLastModified());
        setDomeVisualization(this._captureInformation.getFacets());
        LinearLayout linearLayout = (LinearLayout) findViewById(C0026R.id.bottom);
        ((Button) linearLayout.findViewById(C0026R.id.test_btn)).setOnClickListener(new C00111());
        ((Button) linearLayout.findViewById(C0026R.id.cont_scan_btn)).setOnClickListener(new C00132());
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putSerializable("captureInformation", this._captureInformation);
    }

    private void setDomeVisualization(int[] iArr) {
        ImageView imageView = (ImageView) findViewById(C0026R.id.item_capture_coverage);
        Bitmap decodeResource = BitmapFactory.decodeResource(getResources(), C0026R.drawable.coverage_empty);
        Bitmap copy = decodeResource.copy(Config.ARGB_8888, true);
        decodeResource.recycle();
        Canvas canvas = new Canvas(copy);
        Bitmap bitmap = null;
        for (int i = 0; i < iArr.length; i++) {
            if (iArr[i] > 0) {
                bitmap = BitmapFactory.decodeResource(getResources(), C0026R.drawable.ring_d_16 - i);
                canvas.drawBitmap(bitmap, 0.0f, 0.0f, null);
            }
        }
        if (bitmap != null) {
            bitmap.recycle();
        }
        imageView.setImageBitmap(copy);
        imageView.invalidate();
    }

    @SuppressLint({"NewApi"})
    public long freeDiskSpace() {
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        if (VERSION.SDK_INT >= 18) {
            return statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
        }
        return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(C0026R.menu.capture_detail, menu);
        return true;
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        configuration = ((LayoutInflater) getApplicationContext().getSystemService("layout_inflater")).inflate(C0026R.layout.activity_capture_detail, null);
        ViewGroup viewGroup = (ViewGroup) getWindow().getDecorView().findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(configuration);
        ((ImageView) findViewById(C0026R.id.item_capture_image)).setImageBitmap(BitmapFactory.decodeFile(this._captureInformation.getImagePath()));
        ((TextView) findViewById(C0026R.id.capture_name)).setText(this._captureInformation.getItemName());
        TextView textView = (TextView) findViewById(C0026R.id.capture_points);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BuildConfig.FLAVOR);
        stringBuilder.append(this._captureInformation.getNbPoints());
        textView.setText(stringBuilder.toString());
        textView = (TextView) findViewById(C0026R.id.capture_file_size);
        stringBuilder = new StringBuilder();
        stringBuilder.append(BuildConfig.FLAVOR);
        stringBuilder.append(this._captureInformation.getFileSize());
        stringBuilder.append(" MB");
        textView.setText(stringBuilder.toString());
        ((TextView) findViewById(C0026R.id.last_modified)).setText(this._captureInformation.getFormatedLastModified());
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        final String itemName = this._captureInformation.getItemName();
        switch (menuItem.getItemId()) {
            case 16908332:
                finish();
                return true;
            case C0026R.id.action_delete:
                menuItem = new Builder(this);
                menuItem.setTitle("Delete");
                menuItem.setMessage("\nAre you sure you want to delete?\n");
                menuItem.setNegativeButton("No", new C00143());
                menuItem.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Util.deleteFileRecursive(Util.getCaptureMetadataDirectory(itemName));
                        Util.getOdFile(itemName).delete();
                        CaptureDetailActivity.this.endActivityForRefresh();
                    }
                });
                ((TextView) menuItem.show().findViewById(16908299)).setGravity(3);
                return true;
            case C0026R.id.action_edit_name:
                menuItem = new Builder(this);
                View inflate = LayoutInflater.from(this).inflate(C0026R.layout.text_edit_layout, null, false);
                final EditText editText = (EditText) inflate.findViewById(C0026R.id.text_edit_view);
                TextView textView = (TextView) inflate.findViewById(C0026R.id.text_edit_error_message);
                editText.setText(this._captureInformation.getItemName());
                editText.setInputType(524289);
                editText.setImeActionLabel("OK", 66);
                editText.setImeOptions(6);
                menuItem.setView(inflate);
                menuItem.setTitle("Name");
                menuItem.setNegativeButton("Cancel", new C00165());
                menuItem.setPositiveButton("Ok", null);
                menuItem = menuItem.show();
                final EditText editText2 = editText;
                final TextView textView2 = textView;
                final String str = itemName;
                final MenuItem menuItem2 = menuItem;
                menuItem.getButton(-1).setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        view = editText2.getText().toString();
                        if (view.length() > 64) {
                            textView2.setText(CaptureDetailActivity.this.getResources().getString(C0026R.string.edit_text_error_max_length));
                        } else if (view.length() == 0) {
                            textView2.setText(CaptureDetailActivity.this.getResources().getString(C0026R.string.edit_text_error_character));
                        } else {
                            for (int i = 0; i < view.length(); i++) {
                                char charAt = view.charAt(i);
                                if ((charAt < 'a' || charAt > 'z') && ((charAt < 'A' || charAt > 'Z') && ((charAt < '0' || charAt > '9') && charAt != '_'))) {
                                    textView2.setText(CaptureDetailActivity.this.getResources().getString(C0026R.string.edit_text_error_character));
                                    return;
                                }
                            }
                            if (view != BuildConfig.FLAVOR) {
                                if (!CaptureDetailActivity.this._fragment.getModelNames().contains(view) || view.compareTo(str) == 0) {
                                    if (view.compareTo(str) != 0) {
                                        CaptureDetailActivity.this.renameTo(view);
                                    }
                                    menuItem2.dismiss();
                                    return;
                                }
                            }
                            textView2.setText(CaptureDetailActivity.this.getResources().getString(C0026R.string.edit_text_error_duplicate_name));
                        }
                    }
                });
                editText.setOnEditorActionListener(new OnEditorActionListener() {
                    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                        textView = null;
                        if (i == 6 || i == 0) {
                            ((InputMethodManager) CaptureDetailActivity.this.getSystemService("input_method")).hideSoftInputFromWindow(editText2.getWindowToken(), 0);
                            i = editText2.getText().toString();
                            if (i.length() > 64) {
                                textView2.setText(CaptureDetailActivity.this.getResources().getString(C0026R.string.edit_text_error_max_length));
                                keyEvent = null;
                            } else {
                                keyEvent = true;
                            }
                            KeyEvent keyEvent2 = keyEvent;
                            for (keyEvent = null; keyEvent < i.length(); keyEvent++) {
                                char charAt = i.charAt(keyEvent);
                                if ((charAt < 'a' || charAt > 'z') && ((charAt < 'A' || charAt > 'Z') && ((charAt < '0' || charAt > '9') && charAt != '_'))) {
                                    textView2.setText(CaptureDetailActivity.this.getResources().getString(C0026R.string.edit_text_error_character));
                                    keyEvent2 = null;
                                }
                            }
                            if (i == BuildConfig.FLAVOR || i.length() == null) {
                                textView2.setText(CaptureDetailActivity.this.getResources().getString(C0026R.string.edit_text_error_character));
                                keyEvent2 = null;
                            }
                            if (!(CaptureDetailActivity.this._fragment.getModelNames().contains(i) == null || i.compareTo(str) == null)) {
                                textView2.setText(CaptureDetailActivity.this.getResources().getString(C0026R.string.edit_text_error_duplicate_name));
                                keyEvent2 = null;
                            }
                            if (i.compareTo(str) == null) {
                                menuItem2.dismiss();
                            } else {
                                textView = keyEvent2;
                            }
                            if (textView != null) {
                                CaptureDetailActivity.this.renameTo(i);
                                menuItem2.dismiss();
                            }
                        }
                        return textView;
                    }
                });
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        ((InputMethodManager) CaptureDetailActivity.this.getSystemService("input_method")).showSoftInput(editText, 1);
                    }
                }, 500);
                return true;
            case C0026R.id.action_share:
                this._fragment.share(itemName);
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    private void renameTo(String str) {
        String str2 = "/sdcard/VuforiaObjectScanner/";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str2);
        stringBuilder.append("ObjectReco/");
        String stringBuilder2 = stringBuilder.toString();
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append(this._captureInformation.getItemName());
        stringBuilder3.append(".od");
        File file = new File(stringBuilder2, stringBuilder3.toString());
        stringBuilder3 = new StringBuilder();
        stringBuilder3.append(str2);
        stringBuilder3.append("ObjectReco/");
        String stringBuilder4 = stringBuilder3.toString();
        StringBuilder stringBuilder5 = new StringBuilder();
        stringBuilder5.append(str);
        stringBuilder5.append(".od");
        File file2 = new File(stringBuilder4, stringBuilder5.toString());
        stringBuilder5 = new StringBuilder();
        stringBuilder5.append(str2);
        stringBuilder5.append("metadata/");
        File file3 = new File(stringBuilder5.toString(), this._captureInformation.getItemName());
        StringBuilder stringBuilder6 = new StringBuilder();
        stringBuilder6.append(str2);
        stringBuilder6.append("metadata/");
        File file4 = new File(stringBuilder6.toString(), str);
        if (file2.exists() || file4.exists()) {
            Toast.makeText(this, "File or folder already exists", 1).show();
            return;
        }
        file.renameTo(file2);
        file3.renameTo(file4);
        CaptureInformation captureInformation = this._captureInformation;
        StringBuilder stringBuilder7 = new StringBuilder();
        stringBuilder7.append(file4.getAbsolutePath());
        stringBuilder7.append("/capture.jpg");
        captureInformation.updateName(str, stringBuilder7.toString());
        setTitle(this._captureInformation.getItemName());
        ((TextView) findViewById(C0026R.id.capture_name)).setText(this._captureInformation.getItemName());
        str = getIntent().getExtras();
        if (str != null) {
            str.putSerializable(Constants.INTENT_ITEM, this._captureInformation);
        }
    }

    private void endActivityForRefresh() {
        setResult(0, new Intent());
        finish();
    }

    protected void onResume() {
        String markerCatureName = Util.getMarkerCatureName();
        super.onResume();
        if (markerCatureName != null) {
            this._captureInformation = Util.getCaptureInformation(markerCatureName);
            if (this._captureInformation != null) {
                TextView textView = (TextView) findViewById(C0026R.id.capture_points);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(BuildConfig.FLAVOR);
                stringBuilder.append(this._captureInformation.getNbPoints());
                textView.setText(stringBuilder.toString());
                textView = (TextView) findViewById(C0026R.id.capture_file_size);
                stringBuilder = new StringBuilder();
                stringBuilder.append(BuildConfig.FLAVOR);
                stringBuilder.append(String.format("%.1f", new Object[]{Float.valueOf(this._captureInformation.getFileSize())}));
                stringBuilder.append(" MB");
                textView.setText(stringBuilder.toString());
                ((TextView) findViewById(C0026R.id.last_modified)).setText(this._captureInformation.getFormatedLastModified());
                setDomeVisualization(this._captureInformation.getFacets());
            }
        }
    }

    protected void onPause() {
        super.onPause();
    }
}
