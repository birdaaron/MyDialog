package com.birdaaron.mydialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

import com.birdaaron.mydialog.holder.Holder;

public class MyDialogBuilder
{
    private Holder holder;
    private View header;
    private View footer;
    private int backgroundResource = android.R.color.white;
    private Context context;
    public MyDialogBuilder(Context context)
    {
        this.context = context;
    }

    public MyDialog create()
    {
        holder.setBackground(backgroundResource);
        return new MyDialog(this);
    }
    public MyDialogBuilder setContentHolder(Holder holder)
    {
        this.holder = holder;
        return this;
    }
    public MyDialogBuilder setHeader(View header)
    {
        this.header = header;
        return this;
    }
    public MyDialogBuilder setFooter(View footer)
    {
        this.footer = footer;
        return this;
    }
    public MyDialogBuilder setBackground(int resource)
    {
        this.backgroundResource = resource;
        return this;
    }

    public Holder getHolder() {
        return holder;
    }

    public View getHeader() {
        return header;
    }

    public View getFooter() {
        return footer;
    }

    public int getBackgroundResource() {
        return backgroundResource;
    }

    public Context getContext() {
        return context;
    }
}
