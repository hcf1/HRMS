package com.example.hrms.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hrms.R;
import com.example.hrms.entity.DepartmentEntity;

import java.util.List;

public class DepartmentAdapter extends RecyclerView.Adapter<DepartmentViewHolder> {
    public List<DepartmentEntity> list;
    @NonNull
    @Override
    public DepartmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DepartmentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_department_detail_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DepartmentViewHolder holder, int position) {
        DepartmentEntity departmentEntity = list.get(position);
        holder.name.setText(departmentEntity.name);
        holder.time.setText("部门创建时间");
        holder.timeValue.setText(departmentEntity.time);
        holder.eno.setText("主管职工号");
        holder.enoValue.setText(String.valueOf(departmentEntity.eno));
        holder.dno.setText("部门编号");
        holder.dnoValue.setText(String.valueOf(departmentEntity.dno));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
