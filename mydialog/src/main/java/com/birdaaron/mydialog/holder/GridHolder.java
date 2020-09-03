package com.birdaaron.mydialog.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ListView;

import com.birdaaron.mydialog.R;

import androidx.annotation.NonNull;

public class GridHolder implements HolderWithAdapter
{
    private int backgroundResourceId;
    private FrameLayout header;
    private FrameLayout footer;
    private GridView contentContainer;
    private int columnNum = 0;
    public GridHolder(int columnNum)
    {
        this.columnNum = columnNum;
    }
    @Override
    public void setAdpater(@NonNull BaseAdapter adpater)
    {
        contentContainer.setAdapter(adpater);
    }

    @Override
    public void setHeader(View header)
    {
        this.header.addView(header);
    }

    @Override
    public void setFooter(View footer)
    {
        this.footer.addView(footer);
    }

    @Override
    public void setBackground(int colorResource)
    {
        this.backgroundResourceId = colorResource;
    }

    @Override
    public View getView(@NonNull LayoutInflater inflater, ViewGroup parent)
    {
        View view = inflater.inflate(R.layout.holder_grid,parent,false);
        header = view.findViewById(R.id.holder_grid_header);
        footer = view.findViewById(R.id.holder_grid_footer);
        view.setBackgroundResource(backgroundResourceId);
        contentContainer = view.findViewById(R.id.holder_grid_container);
        contentContainer.setNumColumns(columnNum);
        return view;
    }

    @Override
    public void setPadding(int left, int top, int right, int bottom)
    {
        contentContainer.setPadding(left,top,right,bottom);
    }


}
