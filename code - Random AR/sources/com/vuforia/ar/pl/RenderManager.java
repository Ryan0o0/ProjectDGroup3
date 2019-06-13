package com.vuforia.ar.pl;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class RenderManager {
    private static final int AR_RENDERING_MODE_CONTINUOUS = 2;
    private static final int AR_RENDERING_MODE_DISABLED = 1;
    private static final int AR_RENDERING_MODE_UNKNOWN = 0;
    private static final int AR_RENDERING_MODE_WHENDIRTY = 3;
    private static final String MODULENAME = "RenderManager";
    private static int viewId;
    long delayMS = 0;
    ScheduledFuture<?> fixedFrameRateRunnerTask;
    long maxMS = 0;
    long minMS = 0;
    int renderMode;
    AtomicBoolean renderRequestServiced;
    ScheduledFuture<?> renderRequestWatcherTask;
    AtomicBoolean renderRequested;
    SurfaceManager surfaceManager;
    boolean synchronousMode;
    ScheduledThreadPoolExecutor timer;

    private final class FixedFrameRateRunner implements Runnable {
        private FixedFrameRateRunner() {
        }

        public void run() {
            if (!RenderManager.this.renderRequestServiced.getAndSet(false) && RenderManager.this.surfaceManager != null) {
                RenderManager.this.surfaceManager.requestRender();
                if (!RenderManager.this.synchronousMode && !RenderManager.this.renderRequestWatcherTask.isCancelled()) {
                    RenderManager.this.renderRequestWatcherTask.cancel(false);
                }
            }
        }
    }

    private final class RenderRequestWatcher implements Runnable {
        private RenderRequestWatcher() {
        }

        public void run() {
            if (RenderManager.this.renderRequested.compareAndSet(true, false) && RenderManager.this.surfaceManager != null) {
                RenderManager.this.surfaceManager.requestRender();
                RenderManager.this.renderRequestServiced.set(true);
                if (RenderManager.this.fixedFrameRateRunnerTask == null) {
                    RenderManager.this.fixedFrameRateRunnerTask = RenderManager.this.timer.scheduleAtFixedRate(new FixedFrameRateRunner(), 0, RenderManager.this.delayMS, TimeUnit.MILLISECONDS);
                }
            }
        }
    }

    public RenderManager(SurfaceManager surfaceManager) {
        this.surfaceManager = surfaceManager;
        this.renderMode = 2;
        this.timer = new ScheduledThreadPoolExecutor(1);
        this.synchronousMode = false;
        this.renderRequestServiced = new AtomicBoolean(false);
        this.renderRequested = new AtomicBoolean(false);
    }

    void startTimer() {
        if (this.timer.isShutdown()) {
            this.timer = new ScheduledThreadPoolExecutor(1);
        }
        if (!(this.fixedFrameRateRunnerTask == null || this.fixedFrameRateRunnerTask.isCancelled())) {
            this.fixedFrameRateRunnerTask.cancel(true);
        }
        if (!(this.renderRequestWatcherTask == null || this.renderRequestWatcherTask.isCancelled())) {
            this.renderRequestWatcherTask.cancel(true);
        }
        this.fixedFrameRateRunnerTask = null;
        this.renderRequestWatcherTask = null;
        this.renderRequestWatcherTask = this.timer.scheduleWithFixedDelay(new RenderRequestWatcher(), 0, this.delayMS < 4 ? 1 : this.delayMS / 4, TimeUnit.MILLISECONDS);
    }

    void shutdownTimer() {
        if (!this.timer.isShutdown()) {
            this.timer.shutdown();
        }
    }

    public boolean canSetRenderMode() {
        boolean retrieveGLSurfaceView = this.surfaceManager.retrieveGLSurfaceView();
        if (!retrieveGLSurfaceView) {
            DebugLog.LOGD(MODULENAME, "Could not retrieve a valid GLSurfaceView in view hierarchy, therefore cannot set any render mode");
        }
        return retrieveGLSurfaceView;
    }

    public int getRenderMode() {
        return this.renderMode;
    }

    public boolean setRenderMode(int i) {
        if (this.surfaceManager == null) {
            SystemTools.setSystemErrorCode(6);
            return false;
        }
        boolean enableRenderWhenDirty;
        this.surfaceManager.retrieveGLSurfaceView();
        switch (i) {
            case 1:
            case 3:
                enableRenderWhenDirty = this.surfaceManager.setEnableRenderWhenDirty(true);
                if (enableRenderWhenDirty) {
                    if (i != 1) {
                        if (i != this.renderMode || this.timer.isShutdown()) {
                            long j = this.synchronousMode ? this.minMS : this.maxMS;
                            if (j != 0) {
                                this.delayMS = j;
                                startTimer();
                                break;
                            }
                        }
                    }
                    shutdownTimer();
                    break;
                }
                break;
            case 2:
                enableRenderWhenDirty = this.surfaceManager.setEnableRenderWhenDirty(false);
                if (enableRenderWhenDirty) {
                    shutdownTimer();
                    break;
                }
                break;
            default:
                SystemTools.setSystemErrorCode(2);
                return false;
        }
        if (enableRenderWhenDirty) {
            this.renderMode = i;
        } else {
            SystemTools.setSystemErrorCode(6);
        }
        return enableRenderWhenDirty;
    }

    public boolean setRenderFpsLimits(boolean z, int i, int i2) {
        this.synchronousMode = z;
        if (i != 0) {
            if (i2 != 0) {
                long j;
                long j2 = 1;
                if (i > 1000) {
                    j = 1;
                } else {
                    j = 1000 / ((long) i);
                }
                this.minMS = j;
                if (i2 <= 1000) {
                    j2 = 1000 / ((long) i2);
                }
                this.maxMS = j2;
                if (this.renderMode) {
                    z = this.synchronousMode ? this.minMS : this.maxMS;
                    if (z != this.delayMS) {
                        this.delayMS = z;
                        startTimer();
                    }
                }
                return true;
            }
        }
        SystemTools.setSystemErrorCode(true);
        return false;
    }

    public boolean requestRender() {
        this.renderRequested.set(true);
        return true;
    }

    public View addOverlay(byte[] bArr, int i, int i2, float[] fArr, int[] iArr) {
        final Context activityFromNative = SystemTools.getActivityFromNative();
        if (activityFromNative == null) {
            DebugLog.LOGE(MODULENAME, "drawOverlay could not get access to an activity");
            return null;
        }
        final View drawOverlayView = new DrawOverlayView(activityFromNative, bArr, i, i2, fArr, iArr);
        activityFromNative.runOnUiThread(new Runnable() {
            public void run() {
                drawOverlayView.addOverlay(activityFromNative);
            }
        });
        return drawOverlayView;
    }

    public boolean removeOverlay(final View view) {
        final Activity activityFromNative = SystemTools.getActivityFromNative();
        if (activityFromNative == null || view == null) {
            return false;
        }
        activityFromNative.runOnUiThread(new Runnable() {
            public void run() {
                new DrawOverlayView(activityFromNative).removeOverlay(activityFromNative, view);
            }
        });
        return true;
    }
}
