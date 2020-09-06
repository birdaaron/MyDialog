package com.birdaaron.mydialog;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;

import com.birdaaron.mydialog.holder.Holder;
import com.birdaaron.mydialog.holder.HolderWithAdapter;
import com.birdaaron.mydialog.listener.ExpandedTouchListener;

import androidx.annotation.NonNull;

public class MyDialog
{
    private ViewGroup decorView;
    private ViewGroup rootView;
    private MyDialogBuilder builder;
    private FrameLayout contentContainer;
    public MyDialog(MyDialogBuilder builder)
    {
        this.builder = builder;
        Activity activity = (Activity)builder.getContext();
        decorView =activity.getWindow().getDecorView().findViewById(android.R.id.content);
        LayoutInflater inflater = LayoutInflater.from(builder.getContext());
        rootView = (ViewGroup)inflater.inflate(R.layout.dialog,decorView,false);
        contentContainer = rootView.findViewById(R.id.dialog_contentContainer);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(contentContainer.getLayoutParams());
        layoutParams.gravity = builder.getGravity();
        contentContainer.setLayoutParams(layoutParams);
        initContent(inflater,
                    builder.getHeader(),
                    builder.getFooter(),
                    builder.getHolder(),
                    builder.getAdapter(),
                    builder.getMargin(),
                    builder.getPadding());
        initCanceling();
        initExpandAnimation(activity,builder.getGravity(),builder.getHolder());
    }
    public static MyDialogBuilder newDialog(@NonNull Context context)
    {
        return new MyDialogBuilder(context);
    }
    public boolean isShowing()
    {

        return decorView.findViewById(R.id.dialog_outMost)!=null;
    }
    public void show()
    {
        if (isShowing())
            return;
        decorView.addView(rootView);
    }

    public void dismiss()
    {
        if (!isShowing())
            return;
        decorView.removeView(rootView);

    }
    private void initContent(LayoutInflater inflater, View header, View footer, Holder holder, BaseAdapter adapter,
                             int[] margin, int[] padding)
    {
        View content = holder.getView(inflater,rootView);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        );
        params.setMargins(margin[0],margin[1],margin[2],margin[3]);
        content.setLayoutParams(params);
        holder.setPadding(padding[0],padding[1],padding[2],padding[3]);
        content.setBackgroundResource(builder.getBackgroundResource());

        if(header!=null)
            holder.setHeader(header);
        if(footer!=null)
            holder.setFooter(footer);
        if(adapter!=null && holder instanceof HolderWithAdapter)
        {
            HolderWithAdapter hwa = (HolderWithAdapter)holder;
            hwa.setAdpater(adapter);
        }
        contentContainer.addView(content);

    }
    public void initCanceling()
    {
        rootView.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if(event.getAction()==MotionEvent.ACTION_DOWN)
                    dismiss();
                return false;
            }
        });

        rootView.requestFocus();

        rootView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction()==KeyEvent.ACTION_DOWN)
                {
                    if(keyCode==KeyEvent.KEYCODE_BACK)
                    {

                        dismiss();
                        return true;
                    }

                }
                return false;
            }
        });
    }
    private void initExpandAnimation(Activity activity,int gravity,Holder holder)
    {
        Display display = activity.getWindowManager().getDefaultDisplay();
        int displayHeight = display.getHeight() - getStatusBarHeight(activity);

        int defaultHeight = (displayHeight * 2) / 5;


        final View view = holder.getInflatedView();
        if (!(view instanceof AbsListView)) {
            return;
        }
        final AbsListView absListView = (AbsListView) view;

        view.setOnTouchListener(new ExpandedTouchListener(
                activity, absListView, contentContainer, gravity, displayHeight, defaultHeight
        ));
    }
    private int getStatusBarHeight(Context context)
    {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
