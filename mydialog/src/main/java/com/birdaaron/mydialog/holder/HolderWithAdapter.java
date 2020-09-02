package com.birdaaron.mydialog.holder;

import android.widget.BaseAdapter;

import androidx.annotation.NonNull;

public interface HolderWithAdapter extends Holder
{
    void setAdpater(@NonNull BaseAdapter adpater);
}
