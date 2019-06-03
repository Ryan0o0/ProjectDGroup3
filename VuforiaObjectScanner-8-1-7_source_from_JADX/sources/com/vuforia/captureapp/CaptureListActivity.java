package com.vuforia.captureapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.vuforia.PIXEL_FORMAT;
import com.vuforia.captureapp.captureactivity.CaptureActivity;
import com.vuforia.captureapp.model.CaptureInformation;
import com.vuforia.captureapp.model.CaptureListMode;
import com.vuforia.captureapp.model.CaptureModelFragment;
import com.vuforia.captureapp.model.CaptureModelFragment.ShareCallback;
import java.util.ArrayList;
import java.util.List;

public class CaptureListActivity extends Activity implements ShareCallback {
    public static int MIN_MB_TO_ENABLE_SCAN = 200;
    private static final String TAG_FRAGMENT = "tag_fragment_capture_model";
    private Button _buttonOk;
    private LinearLayout _buttonsView;
    private CaptureModelFragment _fragment;
    private GridAdapter _gridAdapter;
    private GridView _gridView;

    /* renamed from: com.vuforia.captureapp.CaptureListActivity$1 */
    class C00201 implements OnItemClickListener {
        C00201() {
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            switch (CaptureListActivity.this._fragment.getMode()) {
                case MODE_SHARE:
                    CaptureListActivity.this._fragment.resetSelection();
                    CaptureListActivity.this._gridAdapter.onItemClick(view, i);
                    CaptureListActivity.this._gridView.invalidateViews();
                    return;
                case MODE_DELETE:
                    CaptureListActivity.this._gridAdapter.onItemClick(view, i);
                    CaptureListActivity.this._gridView.invalidateViews();
                    return;
                case MODE_NORMAL:
                    adapterView = new Intent(CaptureListActivity.this, CaptureDetailActivity.class);
                    adapterView.putExtra(Constants.INTENT_ITEM, CaptureListActivity.this._fragment.getCaptureInformationItem(i));
                    CaptureListActivity.this.startActivityForResult(adapterView, 1);
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: com.vuforia.captureapp.CaptureListActivity$2 */
    class C00222 implements OnClickListener {

        /* renamed from: com.vuforia.captureapp.CaptureListActivity$2$1 */
        class C00211 implements DialogInterface.OnClickListener {
            C00211() {
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == -1) {
                    deleteCaptures(CaptureListActivity.this._fragment.getSelections());
                    CaptureListActivity.this.enterMode(CaptureListMode.MODE_NORMAL);
                    CaptureListActivity.this._gridView.invalidateViews();
                    CaptureListActivity.this._gridAdapter.refresh();
                    if (CaptureListActivity.this._gridAdapter.getCount() > null) {
                        CaptureListActivity.this.findViewById(C0026R.id.empty_text).setVisibility(4);
                    } else {
                        CaptureListActivity.this.findViewById(C0026R.id.empty_text).setVisibility(0);
                    }
                }
            }

            private void deleteCaptures(List<String> list) {
                for (String str : list) {
                    Util.deleteFileRecursive(Util.getCaptureMetadataDirectory(str));
                    Util.getOdFile(str).delete();
                }
            }
        }

        C00222() {
        }

        public void onClick(View view) {
            view = CaptureListActivity.this._fragment.nbSelections();
            Builder builder = new Builder(CaptureListActivity.this);
            if (view > null) {
                DialogInterface.OnClickListener c00211 = new C00211();
                if (CaptureListActivity.this._fragment.getMode() == CaptureListMode.MODE_DELETE) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("\nAre you sure you want to delete ");
                    stringBuilder.append(view);
                    stringBuilder.append(" scan");
                    stringBuilder.append(view > 1 ? "s" : BuildConfig.FLAVOR);
                    stringBuilder.append("?\n");
                    ((TextView) builder.setMessage(stringBuilder.toString()).setPositiveButton("Yes", c00211).setNegativeButton("No", c00211).setTitle("Delete").show().findViewById(16908299)).setGravity(3);
                    return;
                }
                CaptureListActivity.this._fragment.share();
                CaptureListActivity.this.enterMode(CaptureListMode.MODE_NORMAL);
                return;
            }
            builder.setMessage("\nSelect at least 1 scan to continue\n").setPositiveButton("OK", null).setTitle(CaptureListActivity.this._fragment.getMode() == CaptureListMode.MODE_SHARE ? "Share" : "Delete").show();
        }
    }

    /* renamed from: com.vuforia.captureapp.CaptureListActivity$3 */
    class C00233 implements OnClickListener {
        C00233() {
        }

        public void onClick(View view) {
            CaptureListActivity.this._fragment.resetSelection();
            CaptureListActivity.this.enterMode(CaptureListMode.MODE_NORMAL);
            CaptureListActivity.this._gridView.invalidateViews();
        }
    }

    /* renamed from: com.vuforia.captureapp.CaptureListActivity$4 */
    class C00244 implements DialogInterface.OnClickListener {
        C00244() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
        }
    }

    class GridAdapter extends BaseAdapter {
        private final Context _context;
        private CaptureModelFragment _fragment;

        public long getItemId(int i) {
            return 0;
        }

        public GridAdapter(Context context, CaptureModelFragment captureModelFragment) {
            this._context = context;
            this._fragment = captureModelFragment;
        }

        public void refresh() {
            this._fragment.refresh();
            notifyDataSetChanged();
        }

        public void onItemClick(View view, int i) {
            this._fragment.getCaptureInformationItem(i).toggleSelected();
        }

        public int getCount() {
            return this._fragment.getCount();
        }

        public Object getItem(int i) {
            return this._fragment.getCaptureInformationItem(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = ((Activity) this._context).getLayoutInflater().inflate(C0026R.layout.capture_grid_item, viewGroup, false);
            }
            CaptureInformation captureInformation = (CaptureInformation) getItem(i);
            viewGroup = view.findViewById(C0026R.id.mainView);
            View findViewById = view.findViewById(C0026R.id.greyOutView);
            ImageView imageView = (ImageView) findViewById.findViewById(C0026R.id.greyOutImageView);
            viewGroup.setVisibility(0);
            ImageView imageView2 = (ImageView) viewGroup.findViewById(C0026R.id.item_capture_image);
            TextView textView = (TextView) viewGroup.findViewById(C0026R.id.item_capture_name);
            TextView textView2 = (TextView) viewGroup.findViewById(C0026R.id.item_capture_lastModified);
            imageView2.setImageBitmap(BitmapFactory.decodeFile(captureInformation.getImagePath()));
            textView.setText(captureInformation.getItemName());
            textView2.setText(captureInformation.getFormatedLastModified());
            view.invalidate();
            if (this._fragment.getMode() == CaptureListMode.MODE_NORMAL) {
                findViewById.setVisibility(4);
            } else {
                findViewById.setVisibility(0);
                LayoutParams layoutParams = new FrameLayout.LayoutParams(imageView2.getWidth(), imageView2.getHeight());
                layoutParams.gravity = 17;
                findViewById.setLayoutParams(layoutParams);
                if (captureInformation.isSelected() != 0) {
                    findViewById.setBackgroundColor(CaptureListActivity.this.getResources().getColor(C0026R.color.background_item_selected));
                    imageView.setImageResource(C0026R.drawable.selection_icon_selected);
                } else {
                    findViewById.setBackgroundColor(CaptureListActivity.this.getResources().getColor(C0026R.color.background_item_not_selected));
                    imageView.setImageResource(C0026R.drawable.selection_icon_not_selected);
                }
                findViewById.invalidate();
            }
            return view;
        }
    }

    public void onShareDone() {
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setFlags(PIXEL_FORMAT.YUYV, PIXEL_FORMAT.YUYV);
        setContentView(C0026R.layout.activity_capture_list);
        bundle = getFragmentManager();
        this._fragment = (CaptureModelFragment) bundle.findFragmentByTag(TAG_FRAGMENT);
        if (this._fragment == null) {
            this._fragment = new CaptureModelFragment();
            bundle.beginTransaction().add(this._fragment, TAG_FRAGMENT).commit();
        }
        this._gridView = (GridView) findViewById(C0026R.id.gridView_capture);
        this._buttonsView = (LinearLayout) findViewById(C0026R.id.bottom);
        this._buttonOk = (Button) this._buttonsView.findViewById(C0026R.id.BtnOk);
        Button button = (Button) this._buttonsView.findViewById(C0026R.id.BtnCancel);
        this._gridAdapter = new GridAdapter(this, this._fragment);
        this._gridView.setAdapter(this._gridAdapter);
        this._buttonsView.setVisibility(8);
        this._gridView.setOnItemClickListener(new C00201());
        this._buttonOk.setOnClickListener(new C00222());
        button.setOnClickListener(new C00233());
        this._gridView.invalidateViews();
        enterMode(this._fragment.getMode());
    }

    public void onBackPressed() {
        if (this._fragment.getMode() != CaptureListMode.MODE_NORMAL) {
            this._fragment.resetSelection();
            enterMode(CaptureListMode.MODE_NORMAL);
            this._gridView.invalidateViews();
            return;
        }
        super.onBackPressed();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        if (this._gridAdapter.getCount() > 0) {
            getMenuInflater().inflate(C0026R.menu.capture_list, menu);
        } else {
            getMenuInflater().inflate(C0026R.menu.capture_list_empty, menu);
        }
        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        if (this._gridAdapter.getCount() > 0) {
            getMenuInflater().inflate(C0026R.menu.capture_list, menu);
        } else {
            getMenuInflater().inflate(C0026R.menu.capture_list_empty, menu);
        }
        switch (this._fragment.getMode()) {
            case MODE_SHARE:
            case MODE_DELETE:
                return null;
            default:
                return super.onPrepareOptionsMenu(menu);
        }
    }

    @SuppressLint({"NewApi"})
    public long freeDiskSpace() {
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        if (VERSION.SDK_INT >= 18) {
            return statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
        }
        return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case C0026R.id.action_about:
                startActivity(new Intent(this, AboutScreenActivity.class));
                return true;
            case C0026R.id.action_delete:
                enterMode(CaptureListMode.MODE_DELETE);
                return true;
            case C0026R.id.action_new:
                if (freeDiskSpace() / 1048576 >= ((long) MIN_MB_TO_ENABLE_SCAN)) {
                    menuItem = new Intent(this, CaptureActivity.class);
                    menuItem.putExtra(Constants.INTENT_ACTION, 100);
                    menuItem.putExtra(Constants.INTENT_CAPTURE_NAME, BuildConfig.FLAVOR);
                    menuItem.putExtra(Constants.INTENT_CAPTURE_NB_POINTS, 0);
                    menuItem.putStringArrayListExtra(Constants.INTENT_CAPTURE_LIST, (ArrayList) this._fragment.getModelNames());
                    startActivityForResult(menuItem, 0);
                } else {
                    menuItem = new Builder(this);
                    ((TextView) menuItem.setMessage("\nNot enough storage space to scan\nClear space and try again\n").setPositiveButton("OK", new C00244()).setTitle("Insufficient Memory").show().findViewById(16908299)).setGravity(3);
                }
                return true;
            case C0026R.id.action_share:
                enterMode(CaptureListMode.MODE_SHARE);
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    private void enterMode(CaptureListMode captureListMode) {
        switch (captureListMode) {
            case MODE_SHARE:
                setTitle(C0026R.string.title_share);
                this._buttonOk.setText(C0026R.string.title_share);
                this._buttonsView.setVisibility(0);
                break;
            case MODE_DELETE:
                setTitle(C0026R.string.title_delete);
                this._buttonOk.setText(C0026R.string.title_delete);
                this._buttonsView.setVisibility(0);
                break;
            case MODE_NORMAL:
                setTitle(C0026R.string.title_activity_capture_list);
                this._buttonOk.setText(C0026R.string.title_ok);
                this._buttonsView.setVisibility(8);
                break;
            default:
                break;
        }
        this._fragment.setMode(captureListMode);
        invalidateOptionsMenu();
        if (this._gridAdapter.getCount() > null) {
            findViewById(C0026R.id.empty_text).setVisibility(4);
        } else {
            findViewById(C0026R.id.empty_text).setVisibility(0);
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 0) {
            if (i == 1) {
                enterMode(CaptureListMode.MODE_NORMAL);
                this._gridAdapter.refresh();
                this._gridView.invalidateViews();
            }
        }
        if (this._gridAdapter.getCount() > 0) {
            findViewById(C0026R.id.empty_text).setVisibility(4);
        } else {
            findViewById(C0026R.id.empty_text).setVisibility(0);
        }
    }

    protected void onResume() {
        String markerCatureName = Util.getMarkerCatureName();
        super.onResume();
        if (markerCatureName != null) {
            this._gridAdapter.refresh();
            Intent intent = new Intent(this, CaptureDetailActivity.class);
            intent.putExtra(Constants.INTENT_ITEM, this._fragment.getCaptureInformationItem(markerCatureName));
            startActivityForResult(intent, 1);
            return;
        }
        this._gridAdapter.refresh();
        this._gridView.invalidateViews();
        if (this._gridAdapter.getCount() > 0) {
            findViewById(C0026R.id.empty_text).setVisibility(4);
        } else {
            findViewById(C0026R.id.empty_text).setVisibility(0);
        }
    }
}
