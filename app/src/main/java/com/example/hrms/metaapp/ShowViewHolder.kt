package com.example.hrms.metaapp

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hrms.R
import com.facebook.drawee.view.SimpleDraweeView

class ShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var title: TextView =itemView.findViewById(R.id.title)
    var thumb: SimpleDraweeView = itemView.findViewById(R.id.thumb)
}