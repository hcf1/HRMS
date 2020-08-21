package com.example.hrms.metaapp

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hrms.R
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.interfaces.DraweeController


class ShowAdapter : RecyclerView.Adapter<ShowViewHolder>() {
   var list: ArrayList<ListImage>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
        return ShowViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.show_recycler_view_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        holder.title.text = Uri.decode(list?.get(position)?.title)
        val uri: Uri = Uri.parse(list?.get(position)?.thumb)
        val controller: DraweeController = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .build()
        holder.thumb?.let{
            it.controller = controller
//            it.aspectRatio=1f//设置宽高比
        }
    }

    override fun getItemCount(): Int {
        return list!!.size
    }
}