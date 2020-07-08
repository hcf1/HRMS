package com.example.hrms.view.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hrms.R;

public class DepartmentViewHolder extends RecyclerView.ViewHolder {
    public TextView name;
    public TextView eno;
    public TextView enoValue;
    public TextView time;
    public TextView timeValue;
    public TextView dno;
    public TextView dnoValue;
    public DepartmentViewHolder(@NonNull View itemView) {
        super(itemView);
        name=itemView.findViewById(R.id.name);
        time=itemView.findViewById(R.id.time);
        timeValue=itemView.findViewById(R.id.timeValue);
        eno=itemView.findViewById(R.id.eno);
        enoValue=itemView.findViewById(R.id.enoValue);
        dno=itemView.findViewById(R.id.dno);
        dnoValue=itemView.findViewById(R.id.dnoValue);
    }
}
