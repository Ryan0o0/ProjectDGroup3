package com.vuforia.ar.pl;

import android.content.ComponentName;
import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.vuforia.eyewear.Calibration.service.ICalibrationProfileService;
import com.vuforia.eyewear.Calibration.service.ICalibrationProfileService.Stub;
import java.nio.charset.Charset;

public class CalibrationProfileServiceConnection {
    private static final ComponentName CPS_COMPONENT_NAME = new ComponentName("com.vuforia.eyewear.Calibration", "com.vuforia.eyewear.Calibration.service.CalibrationProfileService");
    private static final String SUBTAG = "CalibrationProfileServiceConn";
    private VuforiaServiceConnection mConnection = new VuforiaServiceConnection();

    public boolean bind(Context context) {
        if (context != null) {
            return this.mConnection.bindService(context, CPS_COMPONENT_NAME);
        }
        DebugLog.LOGD(SUBTAG, "Activity is null");
        return null;
    }

    public boolean unbind(Context context) {
        if (context != null) {
            return this.mConnection.unbindService(context);
        }
        DebugLog.LOGD(SUBTAG, "Activity is null");
        return null;
    }

    public ICalibrationProfileService getCalibrationProfileClient() {
        IBinder awaitService = this.mConnection.awaitService();
        if (awaitService != null) {
            return Stub.asInterface(awaitService);
        }
        DebugLog.LOGD(SUBTAG, "getCalibrationProfileClient IBinder is null; returning null");
        return null;
    }

    int getMaxProfileCount() {
        try {
            return getCalibrationProfileClient().getMaxProfileCount();
        } catch (RemoteException e) {
            String str = SUBTAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("getMaxProfileCount; Remote Exception");
            stringBuilder.append(e.getCause());
            DebugLog.LOGD(str, stringBuilder.toString());
            return 0;
        }
    }

    int getUsedProfileCount() {
        try {
            return getCalibrationProfileClient().getUsedProfileCount();
        } catch (RemoteException e) {
            String str = SUBTAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("getUsedProfileCount; Remote Exception");
            stringBuilder.append(e.getCause());
            DebugLog.LOGD(str, stringBuilder.toString());
            return 0;
        }
    }

    boolean isProfileUsed(int i) {
        try {
            return getCalibrationProfileClient().isProfileUsed(i);
        } catch (int i2) {
            String str = SUBTAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("isProfileUsed; Remote Exception");
            stringBuilder.append(i2.getCause());
            DebugLog.LOGD(str, stringBuilder.toString());
            return false;
        }
    }

    int getActiveProfile() {
        try {
            return getCalibrationProfileClient().getActiveProfile();
        } catch (RemoteException e) {
            String str = SUBTAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("getActiveProfile; Remote Exception");
            stringBuilder.append(e.getCause());
            DebugLog.LOGD(str, stringBuilder.toString());
            return 0;
        }
    }

    boolean setActiveProfile(int i) {
        try {
            return getCalibrationProfileClient().setActiveProfile(i);
        } catch (int i2) {
            String str = SUBTAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("setActiveProfile; Remote Exception");
            stringBuilder.append(i2.getCause());
            DebugLog.LOGD(str, stringBuilder.toString());
            return false;
        }
    }

    byte[] getProfileName(int i) {
        try {
            return getCalibrationProfileClient().getProfileName(i).getBytes(Charset.forName("UTF-16LE"));
        } catch (int i2) {
            String str = SUBTAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("getProfileName; Remote Exception");
            stringBuilder.append(i2.getCause());
            DebugLog.LOGD(str, stringBuilder.toString());
            return 0;
        }
    }

    boolean setProfileName(int i, byte[] bArr) {
        try {
            return getCalibrationProfileClient().setProfileName(i, new String(bArr, Charset.forName("UTF-16LE")));
        } catch (int i2) {
            bArr = SUBTAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("setProfileName; Remote Exception");
            stringBuilder.append(i2.getCause());
            DebugLog.LOGD(bArr, stringBuilder.toString());
            return false;
        }
    }

    float[] getCameraToEyePose(int i, int i2) {
        try {
            return getCalibrationProfileClient().getCameraToEyePose(i, i2);
        } catch (int i3) {
            i2 = SUBTAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("getCameraToEyePose; Remote Exception");
            stringBuilder.append(i3.getCause());
            DebugLog.LOGD(i2, stringBuilder.toString());
            return 0;
        }
    }

    float[] getEyeProjection(int i, int i2) {
        try {
            return getCalibrationProfileClient().getEyeProjection(i, i2);
        } catch (int i3) {
            i2 = SUBTAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("getEyeProjection; Remote Exception");
            stringBuilder.append(i3.getCause());
            DebugLog.LOGD(i2, stringBuilder.toString());
            return 0;
        }
    }

    boolean setCameraToEyePose(int i, int i2, float[] fArr) {
        try {
            return getCalibrationProfileClient().setCameraToEyePose(i, i2, fArr);
        } catch (int i3) {
            i2 = SUBTAG;
            fArr = new StringBuilder();
            fArr.append("setCameraToEyePose; Remote Exception");
            fArr.append(i3.getCause());
            DebugLog.LOGD(i2, fArr.toString());
            return false;
        }
    }

    boolean setEyeProjection(int i, int i2, float[] fArr) {
        try {
            return getCalibrationProfileClient().setEyeProjection(i, i2, fArr);
        } catch (int i3) {
            i2 = SUBTAG;
            fArr = new StringBuilder();
            fArr.append("setEyeProjection; Remote Exception");
            fArr.append(i3.getCause());
            DebugLog.LOGD(i2, fArr.toString());
            return false;
        }
    }

    boolean clearProfile(int i) {
        try {
            return getCalibrationProfileClient().clearProfile(i);
        } catch (int i2) {
            String str = SUBTAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("clearProfile; Remote Exception");
            stringBuilder.append(i2.getCause());
            DebugLog.LOGD(str, stringBuilder.toString());
            return false;
        }
    }
}
