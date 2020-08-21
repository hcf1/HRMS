package com.example.hrms.metaapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.hrms.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.button
import kotlinx.android.synthetic.main.activity_show.*
import okhttp3.*
import java.io.IOException


class ShowActivity : AppCompatActivity() {
    var recyclerAdapter = ShowAdapter()
    var pictureEntity = PictureEntity()
    val gson = Gson()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)
        button.setOnClickListener {
            val q = edit_text.text.toString()
            getData(q, 0, 10)
        }
    }

    private fun getData(q: String, sn: Int, pn: Int) {//sn是其实偏移，pn是返回item数量
        val url = "https://image.so.com/j?q=$q&sn=$sn&pn=$pn"
        val okHttpClient = OkHttpClient()
        val request = Request.Builder()
                .url(url)
                .get()
                .build()
        val call = okHttpClient.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {
                refreshRecyclerView(response)
            }
        })
    }

    private fun refreshRecyclerView(response: Response) {
        if (response == null || response.code != 200) {
            return
        }
        pictureEntity = gson.fromJson(response.body?.string(), PictureEntity::class.java)
        /**
         * 在数据库查询之后返回了一个新的list，notifyDataSetChanged()这个方法会去检查原先接收到的地址上面检查数据变化，
         * 但是从数据库查询的结果是返回了一个新的list，并不是对原list的更改。
         * 所以对原list指向的地址发送更改的时候该方法是无效的。
         * */
        this.runOnUiThread {
            if (recyclerAdapter.list != null) {
                recyclerAdapter.list!!.clear()
                recyclerAdapter.list!!.addAll(pictureEntity.list)
                recyclerAdapter.notifyDataSetChanged()
            } else {
                recyclerAdapter.list = pictureEntity.list
            }
            recyclerView.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);//错位布局
            recyclerView.adapter = recyclerAdapter
        }
    }
}
