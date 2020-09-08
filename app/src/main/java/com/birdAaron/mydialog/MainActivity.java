package com.birdAaron.mydialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.birdaaron.mydialog.MyDialog;
import com.birdaaron.mydialog.holder.GridHolder;
import com.birdaaron.mydialog.holder.Holder;
import com.birdaaron.mydialog.holder.ListHolder;
import com.birdaaron.mydialog.holder.ViewHolder;

public class MainActivity extends AppCompatActivity
{

    private Button confirmButton;
    private RadioGroup holderGroup;
    private RadioGroup gravityGroup;
    private CheckBox showHeader,fixedHeader,showFooter,fixedFooter,expanded;
    private EditText count,gridColumn;
    private Holder holder;
    private int gravity;
    private boolean isHeaderFixed,isFooterFixed,isExpanded,isGrid;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        confirmButton.setOnClickListener(onClickListener);

    }
    private void initView()
    {
        confirmButton = findViewById(R.id.example_confirm);
        holderGroup = findViewById(R.id.sample_holder_group);
        gravityGroup = findViewById(R.id.sample_position_group);
        showHeader = findViewById(R.id.sample_checkBox_show_header);
        fixedHeader = findViewById(R.id.sample_checkBox_fixed_header);
        showFooter = findViewById(R.id.sample_checkBox_show_footer);
        fixedFooter = findViewById(R.id.sample_checkBox_fixed_footer);
        expanded = findViewById(R.id.sample_checkBox_expanded);
        count = findViewById(R.id.sample_editText_count);
        gridColumn = findViewById(R.id.sample_editText_column);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            switch (holderGroup.getCheckedRadioButtonId())
            {
                case R.id.sample_holder_list:
                    holder = new ListHolder();
                    isGrid = false;
                    break;
                case R.id.sample_holder_grid:
                    holder = new GridHolder(Integer.parseInt(gridColumn.getText().toString()));
                    isGrid = true;
                    break;
                default:
                    holder = new ViewHolder(R.layout.view_holder);
                    break;
            }

            switch (gravityGroup.getCheckedRadioButtonId())
            {
                case R.id.sample_position_bottom:
                    gravity = Gravity.BOTTOM;
                    break;
                case R.id.sample_position_top:
                    gravity = Gravity.TOP;
                    break;
                default:
                    gravity = Gravity.CENTER;
                    break;
            }
            View header = LayoutInflater.from(MainActivity.this).inflate(R.layout.header,null);
            View footer = LayoutInflater.from(MainActivity.this).inflate(R.layout.footer,null); ;
            if(!showHeader.isChecked())
                header = null;
            if(!showFooter.isChecked())
                footer = null;
            if(fixedHeader.isChecked())
                isHeaderFixed = true;
            else
                isHeaderFixed = false;
            if(fixedFooter.isChecked())
                isFooterFixed = true;
            else
                isFooterFixed = true;
            if(expanded.isChecked())
                isExpanded = true;
            else
                isExpanded = false;
            final DialogAdapter adapter = new DialogAdapter(MainActivity.this,
                    Integer.parseInt(count.getText().toString()),isGrid);

            MyDialog myDialog = MyDialog.newDialog(MainActivity.this)
                    .setContentHolder(holder)
                    .setHeader(header,isHeaderFixed)
                    .setFooter(footer,isFooterFixed)
                    .setExpanded(isExpanded)
                    //.setMargin(10,10,10,10)
                    .setGravity(gravity)
                    .setAdapter(adapter)
                    .create();
            myDialog.show();
        }
    };


}
