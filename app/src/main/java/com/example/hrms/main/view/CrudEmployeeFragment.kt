package com.example.hrms.main.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hrms.R
import com.example.hrms.common.RouteUtils
import com.example.hrms.employee.AlertDialogEmployeeActivity
import com.example.hrms.entity.EmployeeEntity
import com.example.hrms.view.adapter.EmployeeAdapter

class CrudEmployeeFragment(crud: String, functionModel: String) : CrudFragment(crud, functionModel) {
    private lateinit var employeeAdapter: EmployeeAdapter
    private var query = "SELECT * FROM emp"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = super.onCreateView(inflater, container, savedInstanceState)
        employeeAdapter = EmployeeAdapter()
        setExecuteButtonListener()
        return rootView
    }

    override fun setExecuteButtonListener() {
        super.setExecuteButtonListener()
        executeItem.setOnClickListener {
            when (crud.toString()) {
                CREATE -> {
                    RouteUtils.gotoInsertEmployeeActivity(context)
                }
                READ -> {
                    val intent = Intent(context, AlertDialogEmployeeActivity::class.java)
                    intent.putExtra("tag", RouteUtils.RETRIEVER)
                    this.startActivityForResult(intent, 110)
                }
                UPDATE -> {
                    RouteUtils.gotoUpdateEmployeeActivity(context)
                }
                DELETE -> {
                    RouteUtils.gotoDeleteEmployeeActivity(context)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 110 && resultCode == 110) {
            if (data != null) {
                query = data.getStringExtra("query").toString()
            }
            presenter?.getAdapterbyQuery(EmployeeEntity(), query)
        }
    }

    override fun setAdapterbyQuery(list: MutableList<Any?>?) {
        super.setAdapterbyQuery(list)
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

}