package com.vuforia.ar.pl;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Environment;

public class FileTools {
    private static final int FILE_PATHTYPEINDEX_ABSOLUTE = -1;
    private static final int FILE_PATHTYPEINDEX_ANDROID_ASSETS = 0;
    private static final int FILE_PATHTYPEINDEX_ANDROID_DATALOCAL = 4;
    private static final int FILE_PATHTYPEINDEX_ANDROID_MEDIASTORAGE = 3;
    private static final int FILE_PATHTYPEINDEX_ANDROID_PRIVATEAPPCACHE = 2;
    private static final int FILE_PATHTYPEINDEX_ANDROID_PRIVATEAPPSTORAGE = 1;
    private static final String MODULENAME = "FileTools";

    public static String getAbsolutePath(int i, String str) {
        switch (i) {
            case 1:
                i = SystemTools.getActivityFromNative();
                if (i != 0) {
                    i = i.getFilesDir();
                    if (i != 0) {
                        i = i.getAbsolutePath();
                        break;
                    }
                    SystemTools.setSystemErrorCode(6);
                    return null;
                }
                SystemTools.setSystemErrorCode(6);
                return null;
            case 2:
                i = SystemTools.getActivityFromNative();
                if (i != 0) {
                    i = i.getCacheDir();
                    if (i != 0) {
                        i = i.getAbsolutePath();
                        break;
                    }
                    SystemTools.setSystemErrorCode(6);
                    return null;
                }
                SystemTools.setSystemErrorCode(6);
                return null;
            case 3:
                i = Environment.getExternalStorageDirectory();
                if (i != 0) {
                    i = i.getAbsolutePath();
                    break;
                }
                SystemTools.setSystemErrorCode(6);
                return null;
            default:
                SystemTools.setSystemErrorCode(6);
                return null;
        }
        if (!(i == 0 || str == null)) {
            StringBuilder stringBuilder;
            if (i.length() > 0 && i.charAt(i.length() - 1) != '/') {
                stringBuilder = new StringBuilder();
                stringBuilder.append(i);
                stringBuilder.append("/");
                i = stringBuilder.toString();
            }
            stringBuilder = new StringBuilder();
            stringBuilder.append(i);
            stringBuilder.append(str);
            i = stringBuilder.toString();
        }
        return i;
    }

    public static boolean mediastorage_isAvailable() {
        String externalStorageState = Environment.getExternalStorageState();
        if (!"mounted".equals(externalStorageState)) {
            if (!"mounted_ro".equals(externalStorageState)) {
                return false;
            }
        }
        return true;
    }

    public static AssetManager get_assetmanager() {
        Activity activityFromNative = SystemTools.getActivityFromNative();
        if (activityFromNative != null) {
            return activityFromNative.getAssets();
        }
        SystemTools.setSystemErrorCode(6);
        return null;
    }
}
