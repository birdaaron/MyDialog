package com.birdaaron.mydialog;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.birdaaron.mydialog.holder.Holder;

import androidx.annotation.NonNull;

public class MyDialog
{
    private ViewGroup decorView;
    private ViewGroup rootView;
    private MyDialogBuilder builder;
    private ViewGroup contentContainer;
    public MyDialog(MyDialogBuilder builder)
    {
        this.builder = builder;
        Activity activity = (Activity)builder.getContext();
        decorView =activity.getWindow().getDecorView().findViewById(android.R.id.content);
        LayoutInflater inflater = LayoutInflater.from(builder.getContext());
        rootView = (ViewGroup)inflater.inflate(R.layout.dialog,decorView,false);
        contentContainer = rootView.findViewById(R.id.dialog_contentContainer);
        initContent(inflater,
                    builder.getHeader(),
                    builder.getFooter(),
                    builder.getHolder());
        initCanceling(builder.getHolder());
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
    private void initContent(LayoutInflater inflater,View header, View footer, Holder holder)
    {
        View content = holder.getView(inflater,rootView);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        );
        content.setLayoutParams(params);
        if(header!=null)
            holder.setHeader(header);
        if(footer!=null)
            holder.setFooter(footer);
        contentContainer.addView(content);
    }
    public void initCanceling(Holder holder)
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
}
