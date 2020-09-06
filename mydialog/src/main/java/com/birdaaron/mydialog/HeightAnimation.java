package com.birdaaron.mydialog;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class HeightAnimation extends Animation
{
    private int originalHeight;
    private float perValue;
    private View view;

    public HeightAnimation(View view,int fromHeight, int toHeight)
    {
        this.view = view;
        this.originalHeight = fromHeight;
        this.perValue = toHeight - fromHeight;
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        view.getLayoutParams().height = (int) (originalHeight + perValue * interpolatedTime);
        view.requestLayout();
    }
}
