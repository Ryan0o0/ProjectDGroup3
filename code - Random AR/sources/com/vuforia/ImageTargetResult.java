package com.vuforia;

public class ImageTargetResult extends ObjectTargetResult {
    private long swigCPtr;

    protected ImageTargetResult(long j, boolean z) {
        super(VuforiaJNI.ImageTargetResult_SWIGUpcast(j), z);
        this.swigCPtr = j;
    }

    protected static long getCPtr(ImageTargetResult imageTargetResult) {
        return imageTargetResult == null ? 0 : imageTargetResult.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    protected synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                VuforiaJNI.delete_ImageTargetResult(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public boolean equals(Object obj) {
        if ((obj instanceof ImageTargetResult) && ((ImageTargetResult) obj).swigCPtr == this.swigCPtr) {
            return true;
        }
        return false;
    }

    public static Type getClassType() {
        return new Type(VuforiaJNI.ImageTargetResult_getClassType(), true);
    }

    public Trackable getTrackable() {
        return new ImageTarget(VuforiaJNI.ImageTargetResult_getTrackable(this.swigCPtr, this), false);
    }

    public VirtualButtonResult getVirtualButtonResult(String str) {
        long ImageTargetResult_getVirtualButtonResult = VuforiaJNI.ImageTargetResult_getVirtualButtonResult(this.swigCPtr, this, str);
        if (ImageTargetResult_getVirtualButtonResult == 0) {
            return null;
        }
        return new VirtualButtonResult(ImageTargetResult_getVirtualButtonResult, false);
    }

    public VirtualButtonResultList getVirtualButtonResults() {
        return new VirtualButtonResultList(VuforiaJNI.ImageTargetResult_getVirtualButtonResults(this.swigCPtr, this), true);
    }
}
