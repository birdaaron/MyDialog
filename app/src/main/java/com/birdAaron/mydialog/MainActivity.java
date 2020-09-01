package com.birdAaron.mydialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.birdaaron.mydialog.MyDialog;
import com.birdaaron.mydialog.holder.ViewHolder;

public class MainActivity extends AppCompatActivity
{

    private Button confirmButton;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        confirmButton = findViewById(R.id.example_confirm);
        confirmButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                MyDialog myDialog = MyDialog.newDialog(MainActivity.this)
                        .setContentHolder(new ViewHolder(R.layout.view_holder))
                        .create();
                myDialog.show();
            }
        });
    }
}
