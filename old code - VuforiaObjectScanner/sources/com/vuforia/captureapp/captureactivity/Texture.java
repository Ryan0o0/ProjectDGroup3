package com.vuforia.captureapp.captureactivity;

import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import java.io.BufferedInputStream;

public class Texture {
    public int mChannels;
    public byte[] mData;
    public int mHeight;
    public int mWidth;

    public byte[] getData() {
        return this.mData;
    }

    public static Texture loadTextureFromApk(String str, AssetManager assetManager) {
        try {
            assetManager = BitmapFactory.decodeStream(new BufferedInputStream(assetManager.open(str, 3)));
            int[] iArr = new int[(assetManager.getWidth() * assetManager.getHeight())];
            assetManager.getPixels(iArr, 0, assetManager.getWidth(), 0, 0, assetManager.getWidth(), assetManager.getHeight());
            byte[] bArr = new byte[((assetManager.getWidth() * assetManager.getHeight()) * 4)];
            for (int i = 0; i < assetManager.getWidth() * assetManager.getHeight(); i++) {
                int i2 = iArr[i];
                int i3 = i * 4;
                bArr[i3] = (byte) (i2 >>> 16);
                bArr[i3 + 1] = (byte) (i2 >>> 8);
                bArr[i3 + 2] = (byte) i2;
                bArr[i3 + 3] = (byte) (i2 >>> 24);
            }
            Texture texture = new Texture();
            texture.mWidth = assetManager.getWidth();
            texture.mHeight = assetManager.getHeight();
            texture.mChannels = 4;
            texture.mData = bArr;
            return texture;
        } catch (AssetManager assetManager2) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Failed to log texture '");
            stringBuilder.append(str);
            stringBuilder.append("' from APK");
            DebugLog.LOGE(stringBuilder.toString());
            DebugLog.LOGI(assetManager2.getMessage());
            return null;
        }
    }
}
