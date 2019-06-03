package com.vuforia.captureapp;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class SquareImageGridView extends ImageView {
    public SquareImageGridView(Context context) {
        super(context);
    }

    public SquareImageGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SquareImageGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
    }
}
