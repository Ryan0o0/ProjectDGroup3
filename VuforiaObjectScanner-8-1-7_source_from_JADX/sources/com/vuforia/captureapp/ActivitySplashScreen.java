package com.vuforia.captureapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.vuforia.PIXEL_FORMAT;

public class ActivitySplashScreen extends Activity {
    private static long SPLASH_MILLIS = 450;

    /* renamed from: com.vuforia.captureapp.ActivitySplashScreen$1 */
    class C00101 implements Runnable {
        C00101() {
        }

        public void run() {
            ActivitySplashScreen.this.startActivity(new Intent(ActivitySplashScreen.this, CaptureListActivity.class));
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setFlags(PIXEL_FORMAT.YUYV, PIXEL_FORMAT.YUYV);
        addContentView((RelativeLayout) LayoutInflater.from(this).inflate(C0026R.layout.activity_splash_screen, null, false), new LayoutParams(-1, -1));
        new Handler().postDelayed(new C00101(), SPLASH_MILLIS);
    }
}
