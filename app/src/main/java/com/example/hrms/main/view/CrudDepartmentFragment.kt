package com.example.hrms.main.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.example.hrms.common.RouteUtils
import com.example.hrms.entity.DepartmentEntity
import com.example.hrms.view.adapter.DepartmentAdapter

class CrudDepartmentFragment(crud: String, functionModel: String) : CrudFragment(crud, functionModel) {
    private val query = "SELECT * FROM dept"
    private lateinit var departmentAdapter: DepartmentAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = super.onCreateView(inflater, container, savedInstanceState)
        departmentAdapter = DepartmentAdapter()
        setExecuteButtonListener()
        return rootView
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun setExecuteButtonListener() {
        super.setExecuteButtonListener()
        recyclerView.setOnTouchListener { _: View, motionEvent: MotionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_UP) {
                when (crud.toString()) {
                    CREATE -> {
                        RouteUtils.gotoInsertDepartmentActivity(context)
                    }
                    READ -> {
                        presenter?.getAdapterbyQuery(DepartmentEntity(), query)
                    }
                    UPDATE -> {
                        RouteUtils.gotoUpdateDepartmentActivity(context)
                    }
                    DELETE -> {
                        RouteUtils.gotoDeleteDepartmentActivity(context)
                    }
                }
            }
            false
        }
    }

    override fun setAdapterbyQuery(list: MutableList<Any?>?) {
        /**
         * 在数据库查询之后返回了一个新的list，notifyDataSetChanged()这个方法会去检查原先接收到的地址上面检查数据变化，
         * 但是从数据库查询的结果是返回了一个新的list，并不是对原list的更改。
         * 所以对原list指向的地址发送更改的时候该方法是无效的。
         * */
        if (departmentAdapter.list != null) {
            departmentAdapter.list.clear()
            departmentAdapter.list.addAll(list as List<DepartmentEntity>)
            departmentAdapter.notifyDataSetChanged()
        } else {
            recyclerView.adapter = departmentAdapter
            departmentAdapter.list = list as List<DepartmentEntity>
        }
    }
}