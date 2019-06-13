package com.vuforia.captureapp.model;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.os.Bundle;

public class WaitDialogFragment extends DialogFragment {
    private String _message;
    private String _title;

    public void info(String str, String str2) {
        this._title = str;
        this._message = str2;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public Dialog onCreateDialog(Bundle bundle) {
        bundle = new ProgressDialog(getActivity());
        bundle.setTitle(this._title);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");
        stringBuilder.append(this._message);
        stringBuilder.append("\n\n\n\n\n");
        bundle.setMessage(stringBuilder.toString());
        bundle.setIndeterminate(true);
        bundle.setCancelable(true);
        return bundle;
    }
}
