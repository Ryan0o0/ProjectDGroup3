package com.vuforia;

public class State implements Cloneable {
    private Frame mFrame;
    private Object mFrameMutex;
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected State(long j, boolean z) {
        this.mFrame = null;
        this.mFrameMutex = new Object();
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(State state) {
        return state == null ? 0 : state.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    protected synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                VuforiaJNI.delete_State(this.swigCPtr);
            }
            synchronized (this.mFrameMutex) {
                if (this.mFrame != null) {
                    this.mFrame.delete();
                    this.mFrame = null;
                }
            }
            this.swigCPtr = 0;
        }
    }

    public State clone() {
        return new State(this);
    }

    public boolean equals(Object obj) {
        if ((obj instanceof State) && ((State) obj).swigCPtr == this.swigCPtr) {
            return true;
        }
        return false;
    }

    public State() {
        this(VuforiaJNI.new_State__SWIG_0(), true);
    }

    public State(State state) {
        this(VuforiaJNI.new_State__SWIG_1(getCPtr(state), state), true);
    }

    public Frame getFrame() {
        synchronized (this.mFrameMutex) {
            if (this.mFrame == null) {
                this.mFrame = new Frame(VuforiaJNI.State_getFrame(this.swigCPtr, this), true);
            }
        }
        return this.mFrame;
    }

    public CameraCalibration getCameraCalibration() {
        long State_getCameraCalibration = VuforiaJNI.State_getCameraCalibration(this.swigCPtr, this);
        if (State_getCameraCalibration == 0) {
            return null;
        }
        return new CameraCalibration(State_getCameraCalibration, false);
    }

    public Illumination getIllumination() {
        long State_getIllumination = VuforiaJNI.State_getIllumination(this.swigCPtr, this);
        if (State_getIllumination == 0) {
            return null;
        }
        return new Illumination(State_getIllumination, false);
    }

    public DeviceTrackableResult getDeviceTrackableResult() {
        long State_getDeviceTrackableResult = VuforiaJNI.State_getDeviceTrackableResult(this.swigCPtr, this);
        if (State_getDeviceTrackableResult == 0) {
            return null;
        }
        return new DeviceTrackableResult(State_getDeviceTrackableResult, false);
    }

    public TrackableResultList getTrackableResults() {
        return new TrackableResultList(VuforiaJNI.State_getTrackableResults(this.swigCPtr, this), true);
    }
}
