package com.birdaaron.mydialog.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

public interface Holder
{
    void setHeader(View header);
    void setFooter(View footer);
    void setBackground(int colorResource);
    View getView(@NonNull LayoutInflater inflater, ViewGroup parent);
    void setPadding(int left , int top, int right, int bottom);
}
