package com.birdAaron.mydialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.birdAaron.mydialog.R;

public class DialogAdapter extends BaseAdapter
{
    private int count = 0;
    private Context context;
    private boolean isGrid = false;
    public DialogAdapter(Context context, int count,boolean isGrid)
    {
        this.context = context;
        this.count = count;
        this.isGrid = isGrid;
    }
    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        int layoutResource;
        if(isGrid)
            layoutResource = R.layout.item_grid;
        else
            layoutResource = R.layout.item_list;
        View view = LayoutInflater.from(context).inflate(layoutResource,parent,false);
        TextView textView = view.findViewById(R.id.item_list_text);
        textView.setText("item"+position);
        return view;
    }
}
