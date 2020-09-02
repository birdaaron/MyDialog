package com.birdAaron.mydialog.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.birdAaron.mydialog.R;

public class ListAdapter extends BaseAdapter
{
    private int count = 0;
    private Context context;
    public ListAdapter(Context context,int count)
    {
        this.context = context;
        this.count = count;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list,parent,false);
        TextView textView = view.findViewById(R.id.item_list_text);
        textView.setText("item"+position);
        return view;
    }
}
