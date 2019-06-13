package com.vuforia;

public class ModelRecoSearchResult extends TargetSearchResult {
    private long swigCPtr;

    protected ModelRecoSearchResult(long j, boolean z) {
        super(VuforiaJNI.ModelRecoSearchResult_SWIGUpcast(j), z);
        this.swigCPtr = j;
    }

    protected static long getCPtr(ModelRecoSearchResult modelRecoSearchResult) {
        return modelRecoSearchResult == null ? 0 : modelRecoSearchResult.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    protected synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                VuforiaJNI.delete_ModelRecoSearchResult(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public boolean equals(Object obj) {
        if ((obj instanceof ModelRecoSearchResult) && ((ModelRecoSearchResult) obj).swigCPtr == this.swigCPtr) {
            return true;
        }
        return false;
    }

    public static Type getClassType() {
        return new Type(VuforiaJNI.ModelRecoSearchResult_getClassType(), true);
    }

    public float getConfidence() {
        return VuforiaJNI.ModelRecoSearchResult_getConfidence(this.swigCPtr, this);
    }

    public String getGuideViewName() {
        return VuforiaJNI.ModelRecoSearchResult_getGuideViewName(this.swigCPtr, this);
    }
}
