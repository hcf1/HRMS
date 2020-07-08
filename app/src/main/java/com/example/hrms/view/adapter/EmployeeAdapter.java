package com.example.hrms.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hrms.R;
import com.example.hrms.entity.EmployeeEntity;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeViewHolder> {
    public List<EmployeeEntity> list;

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EmployeeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_employee_detail_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        EmployeeEntity employeeEntity = list.get(position);
        holder.getName().setText(employeeEntity.name);
        holder.getGender().setText("性别");
        holder.getGenderValue().setText(employeeEntity.gender);
        holder.getAge().setText("年龄");
        holder.getAgeValue().setText(String.valueOf(employeeEntity.age));
        holder.getAddr().setText("籍贯");
        holder.getAddrValue().setText(employeeEntity.addr);
        holder.getMail().setText("邮箱");
        holder.getPhoneValue().setText(employeeEntity.mail);
        holder.getPhone().setText("电话");
        holder.getMailValue().setText(employeeEntity.phone);
        holder.getEno().setText("员工ID");
        holder.getEnoValue().setText(String.valueOf(employeeEntity.eno));
        holder.getDname().setText("部门");
        holder.getDnameValue().setText(String.valueOf(employeeEntity.dname));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
