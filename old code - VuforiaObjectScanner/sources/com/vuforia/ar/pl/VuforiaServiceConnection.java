package com.vuforia.ar.pl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import java.util.concurrent.CountDownLatch;

public class VuforiaServiceConnection implements ServiceConnection {
    private static final String SUBTAG = "VuforiaConn";
    private IBinder mService;
    private CountDownLatch mServiceLatch = null;

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.mService = iBinder;
        this.mServiceLatch.countDown();
    }

    public void onServiceDisconnected(ComponentName componentName) {
        this.mService = null;
        this.mServiceLatch = null;
    }

    public boolean bindService(Context context, ComponentName componentName) {
        boolean bindService;
        this.mServiceLatch = new CountDownLatch(1);
        Intent intent = new Intent();
        intent.setComponent(componentName);
        try {
            bindService = context.bindService(intent, this, 1);
        } catch (SecurityException e) {
            context.unbindService(this);
            String str = SUBTAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Not permitted to bind to service: ");
            stringBuilder.append(componentName);
            stringBuilder.append(e);
            DebugLog.LOGD(str, stringBuilder.toString());
            bindService = false;
        }
        if (!bindService) {
            context.unbindService(this);
            context = SUBTAG;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Bind to service ");
            stringBuilder2.append(componentName);
            stringBuilder2.append(" failed");
            DebugLog.LOGD(context, stringBuilder2.toString());
            this.mServiceLatch = null;
        }
        return bindService;
    }

    public boolean unbindService(Context context) {
        if (this.mService != null) {
            context.unbindService(this);
            this.mService = null;
        }
        return true;
    }

    public IBinder awaitService() {
        if (this.mServiceLatch == null) {
            DebugLog.LOGE(SUBTAG, "ERROR: awaitService called before bind()");
            return null;
        }
        try {
            this.mServiceLatch.await();
            return this.mService;
        } catch (InterruptedException e) {
            String str = SUBTAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("bind failed to complete");
            stringBuilder.append(e);
            DebugLog.LOGD(str, stringBuilder.toString());
            this.mServiceLatch = null;
            return null;
        }
    }
}
