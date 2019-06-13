package com.vuforia.captureapp.model;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import com.vuforia.captureapp.C0026R;
import com.vuforia.captureapp.model.AndroidFileSharing.ZipCompletionCallback;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class CaptureModelFragment extends Fragment implements ZipCompletionCallback {
    private static final String TAG_FRAGMENT = "tag_fragment_wait_dialog";
    private List<CaptureInformation> _captures = getCaptureList();
    private CaptureListMode _mode = CaptureListMode.MODE_NORMAL;
    private ShareCallback _shareCallback;

    /* renamed from: com.vuforia.captureapp.model.CaptureModelFragment$1 */
    class C00481 implements FileFilter {
        C00481() {
        }

        public boolean accept(File file) {
            if (!file.isDirectory()) {
                return false;
            }
            File file2 = new File(file, "capture.jpg");
            if (file2.exists() == null || file2.isFile() == null) {
                return false;
            }
            return true;
        }
    }

    /* renamed from: com.vuforia.captureapp.model.CaptureModelFragment$2 */
    class C00492 implements Comparator<CaptureInformation> {
        C00492() {
        }

        public int compare(CaptureInformation captureInformation, CaptureInformation captureInformation2) {
            long lastModified = captureInformation2.getLastModified() - captureInformation.getLastModified();
            if (lastModified > 0) {
                return 1;
            }
            return lastModified < 0 ? -1 : null;
        }
    }

    public interface ShareCallback {
        void onShareDone();
    }

    public CaptureListMode getMode() {
        return this._mode;
    }

    public void setMode(CaptureListMode captureListMode) {
        this._mode = captureListMode;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this._shareCallback = (ShareCallback) activity;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
    }

    public void onDetach() {
        super.onDetach();
        this._shareCallback = null;
    }

    public void refresh() {
        this._captures.clear();
        this._captures.addAll(getCaptureList());
    }

    public int getCount() {
        return this._captures.size();
    }

    public int nbSelections() {
        int i = 0;
        for (CaptureInformation isSelected : this._captures) {
            if (isSelected.isSelected()) {
                i++;
            }
        }
        return i;
    }

    public List<String> getSelections() {
        List<String> arrayList = new ArrayList();
        for (CaptureInformation captureInformation : this._captures) {
            if (captureInformation.isSelected()) {
                arrayList.add(captureInformation.getItemName());
            }
        }
        return arrayList;
    }

    public CaptureInformation getCaptureInformationItem(int i) {
        return (CaptureInformation) this._captures.get(i);
    }

    public CaptureInformation getCaptureInformationItem(String str) {
        for (CaptureInformation captureInformation : this._captures) {
            if (captureInformation.getItemName().equals(str)) {
                return captureInformation;
            }
        }
        return (CaptureInformation) this._captures.get(0);
    }

    public void resetSelection() {
        for (CaptureInformation selected : this._captures) {
            selected.selected(false);
        }
    }

    public List<String> getModelNames() {
        List<String> arrayList = new ArrayList();
        for (CaptureInformation itemName : this._captures) {
            arrayList.add(itemName.getItemName());
        }
        return arrayList;
    }

    private List<CaptureInformation> getCaptureList() {
        List<CaptureInformation> arrayList = new ArrayList();
        String path = Environment.getExternalStorageDirectory().getPath();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(path);
        stringBuilder.append("/VuforiaObjectScanner/metadata/");
        File file = new File(stringBuilder.toString());
        if (file.exists() && file.isDirectory()) {
            for (File file2 : file.listFiles(new C00481())) {
                Properties properties = new Properties();
                File file3 = new File(file2, "info.properties");
                if (file3.exists()) {
                    try {
                        properties.load(new FileInputStream(file3));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                arrayList.add(new CaptureInformation(new File(file2, "capture.jpg").getAbsolutePath(), file2.getName(), new Date(file3.lastModified()), properties));
            }
            Collections.sort(arrayList, new C00492());
        }
        return arrayList;
    }

    public void share() {
        share(null);
    }

    public void share(String str) {
        WaitDialogFragment waitDialogFragment = new WaitDialogFragment();
        waitDialogFragment.info(getResources().getString(C0026R.string.wait_share_title), getResources().getString(C0026R.string.wait_share_message));
        waitDialogFragment.show(getFragmentManager(), TAG_FRAGMENT);
        AndroidFileSharing androidFileSharing = new AndroidFileSharing();
        if (str == null) {
            for (String addCaptureName : getSelections()) {
                androidFileSharing.addCaptureName(addCaptureName);
            }
        } else {
            androidFileSharing.addCaptureName(str);
        }
        androidFileSharing.zip(this);
    }

    public void onZipDone(ArrayList<Uri> arrayList) {
        resetSelection();
        ((WaitDialogFragment) getFragmentManager().findFragmentByTag(TAG_FRAGMENT)).dismiss();
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND_MULTIPLE");
        intent.putParcelableArrayListExtra("android.intent.extra.STREAM", arrayList);
        intent.setType("*/*");
        getActivity().startActivity(Intent.createChooser(intent, "Share via"));
        this._shareCallback.onShareDone();
    }
}
