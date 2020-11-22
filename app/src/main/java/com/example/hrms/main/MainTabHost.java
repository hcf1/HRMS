package com.example.hrms.main;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import com.example.hrms.R;
import com.example.hrms.main.listener.OnCheckedChangedListener;

public class MainTabHost extends GridLayout {
    private LinearLayout tab_main_employee_item, tab_main_department_item, tab_main_salary_item, tab_main_attendance_item;
    private OnCheckedChangedListener mListener;

    public MainTabHost(Context context) {
        this(context, null);
    }

    public MainTabHost(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MainTabHost(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.layout_main_tab_host, this, true);
        this.tab_main_employee_item = findViewById(R.id.tab_main_employee_item);
        this.tab_main_department_item = findViewById(R.id.tab_main_department_item);
        this.tab_main_salary_item = findViewById(R.id.tab_main_salary_item);
        this.tab_main_attendance_item = findViewById(R.id.tab_main_attendance_item);
        tab_main_employee_item.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onCheckedChange(1);
                tab_main_employee_item.setBackgroundColor(Color.parseColor("#e9e2e5"));
                tab_main_department_item.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                tab_main_salary_item.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                tab_main_attendance_item.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            }
        });
        tab_main_department_item.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onCheckedChange(2);
                tab_main_employee_item.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                tab_main_department_item.setBackgroundColor(Color.parseColor("#e9e2e5"));
                tab_main_salary_item.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                tab_main_attendance_item.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            }
        });
        tab_main_salary_item.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onCheckedChange(3);
                tab_main_employee_item.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                tab_main_department_item.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                tab_main_salary_item.setBackgroundColor(Color.parseColor("#e9e2e5"));
                tab_main_attendance_item.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            }
        });
        tab_main_attendance_item.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onCheckedChange(4);
                tab_main_employee_item.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                tab_main_department_item.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                tab_main_salary_item.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                tab_main_attendance_item.setBackgroundColor(Color.parseColor("#e9e2e5"));
            }
        });
    }

    public void setOnCheckedChangeListener(OnCheckedChangedListener mListener) {
        this.mListener = mListener;
    }
}
