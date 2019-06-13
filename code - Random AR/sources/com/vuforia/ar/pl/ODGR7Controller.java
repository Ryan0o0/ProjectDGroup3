package com.vuforia.ar.pl;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.osterhoutgroup.api.ext.ExtendDisplay;
import java.util.ArrayList;
import java.util.List;

public class ODGR7Controller {
    private static final String MODULENAME = "ODGR7Controller";
    private boolean stereoEnabled = false;

    public ODGR7Controller() throws ClassNotFoundException {
        Class.forName("com.osterhoutgroup.api.ext.ExtendDisplay");
    }

    private void logMetrics(String str, Activity activity, Window window) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ExtendDisplay.getDisplayMetrics(activity, window, displayMetrics);
        activity = displayMetrics.widthPixels;
        window = displayMetrics.heightPixels;
        String str2 = MODULENAME;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(" display metrics ");
        stringBuilder.append(activity);
        stringBuilder.append(" x ");
        stringBuilder.append(window);
        DebugLog.LOGD(str2, stringBuilder.toString());
    }

    public boolean getStereo() {
        return this.stereoEnabled;
    }

    public boolean setStereo(final boolean z) {
        Activity activityFromNative = SystemTools.getActivityFromNative();
        if (activityFromNative != null) {
            final Window window = activityFromNative.getWindow();
            if (window != null) {
                if (findSurfaceViews(window).size() != 0) {
                    activityFromNative.runOnUiThread(new Runnable() {
                        public void run() {
                            ExtendDisplay.extendWindow(window, z);
                            if (ODGR7Controller.this.setStereoSurfaces(window, z) == 0 && z) {
                                ExtendDisplay.extendWindow(window, false);
                                ODGR7Controller.this.stereoEnabled = false;
                                return;
                            }
                            ODGR7Controller.this.stereoEnabled = z;
                        }
                    });
                    return true;
                } else if (z) {
                    DebugLog.LOGE(MODULENAME, "ODG Display control: Cannot change to extended display mode, there are no SurfaceViews created.");
                }
            }
        }
        return false;
    }

    private int setStereoSurfaces(Window window, boolean z) {
        int i = 0;
        for (SurfaceView surfaceView : findSurfaceViews(window)) {
            if (surfaceView.getHolder().getSurface().isValid()) {
                ExtendDisplay.extendSurface(surfaceView, z);
                i++;
            }
        }
        return i;
    }

    private List<SurfaceView> findSurfaceViews(Window window) {
        List<SurfaceView> arrayList = new ArrayList();
        findSurfaceViews((ViewGroup) window.getDecorView(), arrayList);
        return arrayList;
    }

    private void findSurfaceViews(ViewGroup viewGroup, List<SurfaceView> list) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof SurfaceView) {
                list.add((SurfaceView) childAt);
            } else if (childAt instanceof ViewGroup) {
                findSurfaceViews((ViewGroup) childAt, list);
            }
        }
    }
}
