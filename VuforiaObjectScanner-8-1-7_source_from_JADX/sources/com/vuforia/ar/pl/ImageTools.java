package com.vuforia.ar.pl;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Rect;
import android.graphics.YuvImage;
import java.io.ByteArrayOutputStream;

public class ImageTools {
    private static final int CAMERA_IMAGE_FORMAT_LUM = 268439809;
    private static final int CAMERA_IMAGE_FORMAT_NV12 = 268439815;
    private static final int CAMERA_IMAGE_FORMAT_NV21 = 268439817;
    private static final int CAMERA_IMAGE_FORMAT_RGB565 = 268439810;
    private static final String MODULENAME = "ImageTools";

    public static byte[] encodeImage(byte[] bArr, int i, int i2, int i3, int i4, int i5) {
        if (bArr == null) {
            return null;
        }
        int i6 = 0;
        if (i3 == CAMERA_IMAGE_FORMAT_NV21) {
            YuvImage yuvImage = new YuvImage(bArr, 17, i, i2, null);
            bArr = new ByteArrayOutputStream();
            if (yuvImage.compressToJpeg(new Rect(0, 0, i, i2), i5, bArr) != 0) {
                return bArr.toByteArray();
            }
            return null;
        } else if (i3 != CAMERA_IMAGE_FORMAT_LUM) {
            return null;
        } else {
            i3 = i * i2;
            int[] iArr = new int[i3];
            while (i6 < i3) {
                iArr[i6] = (bArr[i6] << 24) | 16777215;
                i6++;
            }
            bArr = Bitmap.createBitmap(iArr, 0, i, i, i2, Config.ARGB_8888);
            i = new ByteArrayOutputStream();
            if (bArr.compress(CompressFormat.JPEG, i5, i) != null) {
                return i.toByteArray();
            }
            return null;
        }
    }
}
