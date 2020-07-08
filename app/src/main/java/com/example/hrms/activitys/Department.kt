package com.example.hrms.activitys

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.hrms.R
import com.example.hrms.common.RouteUtils
import com.example.hrms.entity.DepartmentEntity
import com.example.hrms.presenter.Presenter
import com.example.hrms.view.Iview
import com.example.hrms.view.adapter.DepartmentAdapter
import kotlinx.android.synthetic.main.activity_department.*

class Department : AppCompatActivity() ,Iview{
    private val query = "SELECT * FROM dept"
    lateinit var presenter: Presenter
    private lateinit var DepartmentAdapter: DepartmentAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_department)
        initView()
        initData()
    }

    private fun initData() {
    }

    private fun initView() {
        presenter = Presenter(this)
        DepartmentAdapter =DepartmentAdapter()
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);//错位布局
        c.setOnClickListener {//增
            RouteUtils.gotoInsertDepartmentActivity(this)
        }
        r.setOnClickListener {//查
            presenter.getAdapterbyQuery(DepartmentEntity(), query)
        }
        u.setOnClickListener {//改
            RouteUtils.gotoUpdateDepartmentActivity(this)
        }
        d.setOnClickListener {//删
            RouteUtils.gotoDeleteDepartmentActivity(this)
        }
    }

    override fun setAdapterbyQuery(list: MutableList<Any?>?) {
        /**
         * 在数据库查询之后返回了一个新的list，notifyDataSetChanged()这个方法会去检查原先接收到的地址上面检查数据变化，
         * 但是从数据库查询的结果是返回了一个新的list，并不是对原list的更改。
         * 所以对原list指向的地址发送更改的时候该方法是无效的。
         * */
        if (DepartmentAdapter.list != null) {
            DepartmentAdapter.list.clear()
            DepartmentAdapter.list.addAll(list as List<DepartmentEntity>)
            DepartmentAdapter.notifyDataSetChanged()
        } else {
            recyclerView.adapter = DepartmentAdapter
            DepartmentAdapter.list = list as List<DepartmentEntity>
        }
    }

    override fun setDataSuccess(isSuccess: Boolean?) {

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}