package com.birdaaron.mydialog.holder;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.birdaaron.mydialog.R;

import java.util.List;

import androidx.annotation.NonNull;

public class ListHolder implements HolderWithAdapter
{
    private int backgroundResourceId;
    private FrameLayout header;
    private FrameLayout footer;
    private ListView contentContainer;
    @Override
    public void setAdpater(@NonNull BaseAdapter adpater)
    {
        contentContainer.setAdapter(adpater);
    }

    @Override
    public int getItemTotalHeight()
    {
        int totalHeight = 0;
        BaseAdapter adapter = (BaseAdapter)contentContainer.getAdapter();
        for(int i = 0;i<adapter.getCount();i++)
        {
            View mView = adapter.getView(i,null,contentContainer);
            mView.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                          View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            totalHeight += mView.getMeasuredHeight();
        }
        return totalHeight;
    }
    public int getCount()
    {
        return contentContainer.getAdapter().getCount();
    }
    @Override
    public void setHeader(View header,boolean isFixed)
    {
        if(isFixed)
            this.header.addView(header);
        else
            contentContainer.addHeaderView(header);
    }

    @Override
    public void setFooter(View footer,boolean isFixed)
    {
        if(isFixed)
            this.footer.addView(footer);
        else
            contentContainer.addHeaderView(footer);

    }

    @Override
    public void setBackground(int colorResource)
    {
        this.backgroundResourceId = colorResource;
    }

    @Override
    public View getView(@NonNull LayoutInflater inflater, ViewGroup parent)
    {
        View view = inflater.inflate(R.layout.holder_list,parent,false);
        header = view.findViewById(R.id.holder_list_header);
        footer = view.findViewById(R.id.holder_list_footer);
        view.setBackgroundResource(backgroundResourceId);
        contentContainer = view.findViewById(R.id.holder_list_container);
        return view;
    }

    @Override
    public void setPadding(int left, int top, int right, int bottom)
    {
        contentContainer.setPadding(left,top,right,bottom);
    }

    @Override
    public View getInflatedView() {
        return contentContainer;
    }


}
