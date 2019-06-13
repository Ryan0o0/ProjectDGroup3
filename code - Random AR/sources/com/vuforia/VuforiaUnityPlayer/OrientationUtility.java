package com.vuforia.VuforiaUnityPlayer;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.view.WindowManager;

public class OrientationUtility {
    static final int SCREEN_ORIENTATION_LANDSCAPELEFT = 3;
    static final int SCREEN_ORIENTATION_LANDSCAPERIGHT = 4;
    static final int SCREEN_ORIENTATION_PORTRAIT = 1;
    static final int SCREEN_ORIENTATION_PORTRAITUPSIDEDOWN = 2;
    static final int SCREEN_ORIENTATION_UNKNOWN = 0;

    public static int getSurfaceOrientation(Activity activity) {
        if (activity == null) {
            return -1;
        }
        Configuration configuration = activity.getResources().getConfiguration();
        activity = ((WindowManager) activity.getSystemService("window")).getDefaultDisplay();
        if (VERSION.SDK_INT >= 8) {
            activity = activity.getRotation();
        } else {
            activity = activity.getOrientation();
        }
        int i = 0;
        switch (configuration.orientation) {
            case 1:
            case 3:
                if (activity != null) {
                    if (activity != 3) {
                        i = 2;
                        break;
                    }
                }
                i = 1;
                break;
            case 2:
                if (activity != null) {
                    if (activity != 1) {
                        i = 4;
                        break;
                    }
                }
                i = 3;
                break;
            default:
                break;
        }
        return i;
    }
}
