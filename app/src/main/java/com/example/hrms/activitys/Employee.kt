package com.example.hrms.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.hrms.R
import com.example.hrms.common.RouteUtils
import com.example.hrms.employee.AlertDialogEmployeeActivity
import com.example.hrms.entity.EmployeeEntity
import com.example.hrms.presenter.Presenter
import com.example.hrms.view.Iview
import com.example.hrms.view.adapter.EmployeeAdapter
import kotlinx.android.synthetic.main.activity_employee.*

class Employee : AppCompatActivity(), Iview {
    private val query = "SELECT * FROM emp"
    lateinit var presenter: Presenter
    lateinit var employeeAdapter: EmployeeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee)
        initView()
        initData()
    }

    private fun initData() {
    }

    private fun initView() {
        presenter = Presenter(this)
        employeeAdapter = EmployeeAdapter()
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);//错位布局
        c.setOnClickListener {//增
            RouteUtils.gotoActivity(this, AlertDialogEmployeeActivity::class.java)
        }
        r.setOnClickListener {//查
            presenter.getAdapterbyQuery(EmployeeEntity(), query)
        }
        u.setOnClickListener {//改

        }
        d.setOnClickListener {//删

        }
    }

    override fun setAdapterbyQuery(list: MutableList<Any?>?) {
        /**
         * 在数据库查询之后返回了一个新的list，notifyDataSetChanged()这个方法会去检查原先接收到的地址上面检查数据变化，
         * 但是从数据库查询的结果是返回了一个新的list，并不是对原list的更改。
         * 所以对原list指向的地址发送更改的时候该方法是无效的。
         * */
        if (employeeAdapter.list != null) {
            employeeAdapter.list.clear()
            employeeAdapter.list.addAll(list as List<EmployeeEntity>)
            employeeAdapter.notifyDataSetChanged()
        } else {
            recyclerView.adapter = employeeAdapter
            employeeAdapter.list = list as List<EmployeeEntity>
        }
    }

    override fun setDataSuccess(isSuccess: Boolean?) {

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}