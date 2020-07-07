package com.example.hrms.view.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.hrms.R

class EmployeeViewHolder(itemView: View) : ViewHolder(itemView) {
    public var name: TextView = itemView.findViewById(R.id.name)
    public var gender: TextView = itemView.findViewById(R.id.gender)
    public var genderValue: TextView = itemView.findViewById(R.id.genderValue)
    public var age: TextView = itemView.findViewById(R.id.age)
    public var ageValue: TextView = itemView.findViewById(R.id.ageValue)
    public var addr: TextView = itemView.findViewById(R.id.addr)
    public var addrValue: TextView = itemView.findViewById(R.id.addrValue)
}