package com.birdaaron.mydialog;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
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
    }
    public static MyDialogBuilder newDialog(@NonNull Context context)
    {
        return new MyDialogBuilder(context);
    }
    public void show()
    {
        decorView.addView(rootView);
    }
    private void initContent(LayoutInflater inflater,View header, View footer, Holder holder)
    {
        View content = holder.getView(inflater,rootView);
        /**
        View content = inflater.inflate(R.layout.holder_view,rootView,false);
        content.setBackgroundResource(android.R.color.white);
        FrameLayout fl = content.findViewById(R.id.holder_view_container);
        View flContent = inflater.inflate(R.layout.test,rootView,false);
         fl.addView(flContent);
         **/

        if(header!=null)
            holder.setHeader(header);
        if(footer!=null)
            holder.setFooter(footer);
        contentContainer.addView(content);
    }

}
