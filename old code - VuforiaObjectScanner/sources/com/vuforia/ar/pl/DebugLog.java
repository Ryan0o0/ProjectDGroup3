package com.vuforia.ar.pl;

import android.util.Log;

public class DebugLog {
    private static final String LOGTAG = "AR";

    public static final void LOGE(String str, String str2) {
        if (str.length() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(": ");
            stringBuilder.append(str2);
            str2 = stringBuilder.toString();
        }
        Log.e(LOGTAG, str2);
    }

    public static final void LOGW(String str, String str2) {
        if (str.length() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(": ");
            stringBuilder.append(str2);
            str2 = stringBuilder.toString();
        }
        Log.w(LOGTAG, str2);
    }

    public static final void LOGD(String str, String str2) {
        if (str.length() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(": ");
            stringBuilder.append(str2);
            str2 = stringBuilder.toString();
        }
        Log.d(LOGTAG, str2);
    }

    public static final void LOGI(String str, String str2) {
        if (str.length() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(": ");
            stringBuilder.append(str2);
            str2 = stringBuilder.toString();
        }
        Log.i(LOGTAG, str2);
    }
}
