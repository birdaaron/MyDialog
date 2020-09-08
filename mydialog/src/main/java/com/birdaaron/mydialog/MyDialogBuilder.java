package com.birdaaron.mydialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ListView;

import com.birdaaron.mydialog.holder.GridHolder;
import com.birdaaron.mydialog.holder.Holder;
import com.birdaaron.mydialog.holder.ListHolder;
import com.birdaaron.mydialog.holder.ViewHolder;

import java.util.Arrays;
import java.util.List;

public class MyDialogBuilder {
    private static final int INVALID = -1;
    private int[] margin = new int[4];
    private int[] padding = new int[4];
    private Holder holder;
    private BaseAdapter adapter;
    private View header;
    private View footer;
    private int backgroundResource = android.R.color.white;
    private int gravity = Gravity.CENTER;
    private Context context;

    public MyDialogBuilder(Context context) {
        this.context = context;
        Arrays.fill(margin, INVALID);
    }

    public MyDialog create() {
        holder.setBackground(backgroundResource);
        return new MyDialog(this);
    }

    public MyDialogBuilder setContentHolder(Holder holder) {
        this.holder = holder;
        return this;
    }

    public MyDialogBuilder setHeader(View header) {
        this.header = header;
        return this;
    }

    public MyDialogBuilder setFooter(View footer) {
        this.footer = footer;
        return this;
    }

    public MyDialogBuilder setBackground(int resource) {
        this.backgroundResource = resource;
        return this;
    }

    public MyDialogBuilder setAdapter(BaseAdapter adapter) {
        this.adapter = adapter;
        return this;
    }

    public MyDialogBuilder setGravity(int gravity) {
        this.gravity = gravity;
        return this;
    }

    public MyDialogBuilder setMargin(int left, int top, int right, int bottom) {
        margin[0] = left;
        margin[1] = top;
        margin[2] = right;
        margin[3] = bottom;
        return this;
    }

    public MyDialogBuilder setPadding(int left, int top, int right, int bottom) {
        padding[0] = left;
        padding[1] = top;
        padding[2] = right;
        padding[3] = bottom;
        return this;
    }

    public int[] getMargin() {
        int minimumMargin = context.getResources().getDimensionPixelSize(R.dimen.default_center_margin);
        for (int i = 0; i < 4; i++)
            margin[i] = initMargin(gravity, margin[i], minimumMargin);
        return margin;
    }

    private int initMargin(int gravity, int margin, int minimumMargin) {
        switch (gravity) {
            case Gravity.CENTER:
                return (margin == INVALID) ? minimumMargin : margin;
            default:
                return (margin == INVALID) ? 0 : margin;
        }

    }
    private int initMaximunmHeight(int maximumHeight)
    {
        if(gravity==Gravity.CENTER)
        {
            int centerMargin = context.getResources().getDimensionPixelSize(R.dimen.default_center_margin);
            maximumHeight+=(2*centerMargin);
        }
        if(header!=null)
        {
            header.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            maximumHeight+=header.getMeasuredHeight();
        }
        if(footer!=null)
        {
            footer.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            maximumHeight+=footer.getMeasuredHeight();
         }
        return maximumHeight;
    }
    public int getExpandedMaximumHeight()
    {
        int maximumHeight = 0;
        if(holder instanceof ListHolder)
        {
            ListHolder listHolder = (ListHolder)holder;
            int count = listHolder.getCount();
            if(count>2)
            {
                maximumHeight = listHolder.getItemTotalHeight();
                maximumHeight = initMaximunmHeight(maximumHeight);
            }
            else
                maximumHeight = getDefaultHeight();
            if(maximumHeight>getDisplayHeight())
                maximumHeight = getDisplayHeight();
        }
        if(holder instanceof GridHolder)
        {

            GridHolder gridHolder = (GridHolder)holder;
            int row = gridHolder.getRowNum();
            Log.d("getExpandedMaximumHeight: ", String.valueOf(row));
            if(row > 1)
            {
                maximumHeight = gridHolder.getItemTotalHeight();
                maximumHeight = initMaximunmHeight(maximumHeight);
            }
            else
                maximumHeight = getDefaultHeight();
            if(maximumHeight>getDisplayHeight())
                maximumHeight = getDisplayHeight();
        }
        return maximumHeight;
    }
    public int getDefaultHeight()
    {
        int displayHeight = getDisplayHeight();//
        //displayHeight = holder.getInflatedView().getHeight();
        return (displayHeight * 2) / 5;
    }

    public int getDisplayHeight()
    {
        Activity activity = (Activity) context;
        Display display = activity.getWindowManager().getDefaultDisplay();
        return display.getHeight() - getStatusBarHeight(activity);
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
    public int[] getPadding() {
        return padding;
    }

    public BaseAdapter getAdapter() {
        return adapter;
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

    public int getGravity() {
        return gravity;
    }
}
