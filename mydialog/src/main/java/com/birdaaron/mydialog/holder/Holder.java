package com.birdaaron.mydialog.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

public interface Holder
{
    void setHeader(View header,boolean isFixed);
    void setFooter(View footer,boolean isFixed);
    void setBackground(int colorResource);
    View getView(@NonNull LayoutInflater inflater, ViewGroup parent);
    void setPadding(int left , int top, int right, int bottom);
    View getInflatedView();
}
