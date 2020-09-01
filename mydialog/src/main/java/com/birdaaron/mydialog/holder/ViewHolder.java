package com.birdaaron.mydialog.holder;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.birdaaron.mydialog.R;

import java.util.logging.Handler;

import androidx.annotation.NonNull;

public class ViewHolder implements Holder
{
    private int contentResourceId;
    private int backgroundResourceId;
    private FrameLayout header;
    private FrameLayout footer;
    public ViewHolder(int contentResourceId)
    {
        this.contentResourceId = contentResourceId;
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

        View view = inflater.inflate(R.layout.holder_view,parent,false);
        view.setBackgroundResource(backgroundResourceId);
        header = view.findViewById(R.id.holder_view_header);
        footer = view.findViewById(R.id.holder_view_footer);
        FrameLayout contentContainer = view.findViewById(R.id.holder_view_container);
        View content = inflater.inflate(contentResourceId,parent,false);//?parent

        contentContainer.addView(content);


        return view;
    }
}
