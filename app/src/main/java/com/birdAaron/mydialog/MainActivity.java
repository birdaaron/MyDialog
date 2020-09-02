package com.birdAaron.mydialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.Scroller;

import com.birdaaron.mydialog.MyDialog;
import com.birdaaron.mydialog.holder.ListHolder;
import com.birdaaron.mydialog.holder.ViewHolder;

import java.util.List;

public class MainActivity extends AppCompatActivity
{

    private Button confirmButton;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        confirmButton = findViewById(R.id.example_confirm);
        final com.birdAaron.mydialog.adapter.ListAdapter adapter = new com.birdAaron.mydialog.adapter.ListAdapter(this,3);
        confirmButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                MyDialog myDialog = MyDialog.newDialog(MainActivity.this)
                        .setContentHolder(new ListHolder())
                        .setAdapter(adapter)
                        .create();
                myDialog.show();
            }
        });


    }
}
