package com.vuforia.captureapp;

import android.os.Environment;
import com.vuforia.PIXEL_FORMAT;
import com.vuforia.captureapp.model.CaptureInformation;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class Util {
    public static String getAppDataRootDirectory() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Environment.getExternalStorageDirectory().getPath());
        stringBuilder.append("/VuforiaObjectScanner");
        return stringBuilder.toString();
    }

    public static String getAppDataMetadataDirectory() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getAppDataRootDirectory());
        stringBuilder.append("/metadata");
        return stringBuilder.toString();
    }

    public static String getAppDataCaptureDirectory() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getAppDataRootDirectory());
        stringBuilder.append("/ObjectReco");
        return stringBuilder.toString();
    }

    public static File getCaptureMetadataDirectory(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getAppDataMetadataDirectory());
        stringBuilder.append("/");
        stringBuilder.append(str);
        return new File(stringBuilder.toString());
    }

    public static File getOdFile(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getAppDataCaptureDirectory());
        stringBuilder.append("/");
        stringBuilder.append(str);
        stringBuilder.append(".od");
        return new File(stringBuilder.toString());
    }

    public static File getCaptureShareDirectory() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getAppDataRootDirectory());
        stringBuilder.append("/share");
        return new File(stringBuilder.toString());
    }

    public static void deleteFileRecursive(File file) {
        if (file.isDirectory()) {
            for (File deleteFileRecursive : file.listFiles()) {
                deleteFileRecursive(deleteFileRecursive);
            }
        }
        file.delete();
    }

    public static Date prepareMetadataDirectory(String str, int i, float f, int[] iArr) {
        String path = Environment.getExternalStorageDirectory().getPath();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(path);
        stringBuilder.append("/VuforiaObjectScanner/capture.jpg");
        File file = new File(stringBuilder.toString());
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(path);
        stringBuilder2.append("/VuforiaObjectScanner/current.txt");
        File file2 = new File(stringBuilder2.toString());
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append(path);
        stringBuilder3.append("/VuforiaObjectScanner/metadata/");
        stringBuilder3.append(str);
        File file3 = new File(stringBuilder3.toString());
        if (!file3.exists()) {
            file3.mkdirs();
        }
        File file4 = new File(file3, "info.properties");
        try {
            File file5 = new File(file3, "capture.jpg");
            if (!file5.exists()) {
                copy(file, file5);
            }
            Properties properties = new Properties();
            if (file4.exists()) {
                properties.load(new FileInputStream(file4));
            }
            if (i > 0) {
                stringBuilder3 = new StringBuilder();
                stringBuilder3.append(BuildConfig.FLAVOR);
                stringBuilder3.append(i);
                properties.setProperty("nbPoints", stringBuilder3.toString());
            }
            if (f > 0) {
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(BuildConfig.FLAVOR);
                stringBuilder2.append(f);
                properties.setProperty("mFileSize", stringBuilder2.toString());
            }
            if (iArr.length > 0) {
                int i2 = 0;
                for (int i3 : iArr) {
                    if (i3 > 0) {
                        i2++;
                    }
                }
                if (i2 > 0) {
                    for (i = 0; i < iArr.length; i++) {
                        f = new StringBuilder();
                        f.append("facet");
                        f.append(i);
                        f = f.toString();
                        stringBuilder2 = new StringBuilder();
                        stringBuilder2.append(BuildConfig.FLAVOR);
                        stringBuilder2.append(iArr[i]);
                        properties.setProperty(f, stringBuilder2.toString());
                    }
                }
            }
            i = new FileOutputStream(file4);
            properties.store(i, "ObjectReco");
            i.flush();
            i.close();
            i = new FileOutputStream(file2);
            f = new Properties();
            f.setProperty(Constants.INTENT_CAPTURE_NAME, str);
            f.store(i, "marker");
            i.flush();
            i.close();
        } catch (String str2) {
            str2.printStackTrace();
        }
        return new Date(file4.lastModified());
    }

    public static String getMarkerCatureName() {
        try {
            String path = Environment.getExternalStorageDirectory().getPath();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(path);
            stringBuilder.append("/VuforiaObjectScanner/current.txt");
            File file = new File(stringBuilder.toString());
            if (!file.exists()) {
                return null;
            }
            InputStream fileInputStream = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(fileInputStream);
            fileInputStream.close();
            file.delete();
            return properties.getProperty(Constants.INTENT_CAPTURE_NAME);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void copy(File file, File file2) throws IOException {
        InputStream fileInputStream = new FileInputStream(file);
        file = new FileOutputStream(file2);
        file2 = new byte[PIXEL_FORMAT.YUYV];
        while (true) {
            int read = fileInputStream.read(file2);
            if (read > 0) {
                file.write(file2, 0, read);
            } else {
                fileInputStream.close();
                file.close();
                return;
            }
        }
    }

    public static CaptureInformation getCaptureInformation(String str) {
        str = getCaptureMetadataDirectory(str);
        Properties properties = new Properties();
        File file = new File(str, "info.properties");
        if (file.exists()) {
            try {
                properties.load(new FileInputStream(file));
                return new CaptureInformation(new File(str, "capture.jpg").getAbsolutePath(), str.getName(), new Date(file.lastModified()), properties);
            } catch (String str2) {
                str2.printStackTrace();
            }
        }
        return null;
    }
}
