package com.birdaaron.mydialog.listener;

import android.content.Context;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.AbsListView;
import android.widget.FrameLayout;

import com.birdaaron.mydialog.HeightAnimation;

public class ExpandedTouchListener implements View.OnTouchListener
{
    private AbsListView absListView;
    private View contentContainer;
    private int displayHeight;
    private int defaultHeight;
    private GestureDetector gestureDetector;
    private int gravity;
    private float y;
    private boolean full;
    private boolean touchUp;
    private boolean scrollUp;
    private FrameLayout.LayoutParams params;

    public ExpandedTouchListener(Context context, AbsListView absListView, View container, int gravity,
                                 int displayHeight, int defaultHeight)
    {
        this.absListView = absListView;
        this.contentContainer = container;
        this.gravity = gravity;
        this.displayHeight = displayHeight;
        this.defaultHeight = defaultHeight;
        this.params = (FrameLayout.LayoutParams) container.getLayoutParams();

        gestureDetector = new GestureDetector(context,new GestureDetector.SimpleOnGestureListener()
        {
            @Override
            public boolean onSingleTapUp(MotionEvent e)
            {
                return true;
            }
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY)
            {
                scrollUp = distanceY > 0;
                return false;
            }
        });
    }
    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        if(gestureDetector.onTouchEvent(event))
            return false;

        if(!(!scrollUp && isListAtTop()) && full)
            return false;

        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                y = event.getRawY();
                return true;
            case MotionEvent.ACTION_MOVE:
                onTouchMove(v,event);
                break;
            case MotionEvent.ACTION_UP:
                onTouchUp(v,event);
                break;
        }
        return true;
    }
    private void onTouchMove(View view, MotionEvent event)
    {
        if(y == -1)
            y = event.getRawY();
        float delta = y - event.getRawY();
        touchUp = delta>0;
        if(gravity == Gravity.TOP)
            delta = -delta;
        y = event.getRawY();

        int newHeight = params.height + (int)delta;
        if(newHeight > displayHeight)
            newHeight = displayHeight;
        if(newHeight<defaultHeight)
            newHeight = defaultHeight;
        params.height = newHeight;
        contentContainer.setLayoutParams(params);

        full = params.height == displayHeight;
    }
    private void onTouchUp(View view,MotionEvent event)
    {
        y = -1;
        if(!touchUp&&params.height>defaultHeight*2)
        {
            animateContent(displayHeight, new SimpleAnimationListener()
            {
                @Override
                public void onAnimationEnd(Animation animation) {
                    full = true;
                }

            });
            return;
        }
        if(!touchUp&&params.height>defaultHeight)
        {
            animateContent(defaultHeight,new SimpleAnimationListener());
            return;
        }
        if(touchUp&&params.height>defaultHeight+50)
        {
            animateContent(displayHeight,new SimpleAnimationListener(){
                @Override
                public void onAnimationEnd(Animation animation) {
                    full = true;
                }
            });
            return;
        }
        if(touchUp&&params.height<=defaultHeight+50)
        {
            animateContent(defaultHeight,new SimpleAnimationListener());
            return;
        }
    }
    private boolean isListAtTop()
    {
        return absListView.getChildCount()==0||
                absListView.getChildAt(0).getTop()==absListView.getPaddingTop();//
    }
    private void animateContent(int to, Animation.AnimationListener listener)
    {
        HeightAnimation animation = new HeightAnimation(contentContainer, contentContainer.getHeight(), to);
        animation.setAnimationListener(listener);
        animation.setDuration(200);
        contentContainer.startAnimation(animation);
    }
}
