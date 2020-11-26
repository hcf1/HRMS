package com.example.hrms.main.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import com.example.hrms.common.RouteUtils
import com.example.hrms.main.view.dialog.AlertDialogEmployeeActivity
import com.example.hrms.entity.EmployeeEntity
import com.example.hrms.view.adapter.EmployeeAdapter

class CrudEmployeeFragment : CrudFragment(), GestureDetector.OnGestureListener {
    private lateinit var employeeAdapter: EmployeeAdapter
    private var query = "SELECT * FROM emp"

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = super.onCreateView(inflater, container, savedInstanceState)
        employeeAdapter = EmployeeAdapter()
        val gestureDetector = GestureDetector(context, this)
        recyclerView.setOnTouchListener { _: View, motionEvent: MotionEvent ->
            gestureDetector.onTouchEvent(motionEvent)
        }
        return rootView
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 110 && resultCode == 110) {
            if (data != null) {
                query = data.getStringExtra("query").toString()
            }
            presenter?.getAdapterByQuery(EmployeeEntity(), query)
        }
    }

    override fun setAdapterByQuery(list: MutableList<Any?>?) {
        super.setAdapterByQuery(list)
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

    override fun onShowPress(p0: MotionEvent?) {
        Log.d("hechangfei", "onShowPress")
    }

    override fun onSingleTapUp(p0: MotionEvent?): Boolean {
        Log.d("hechangfei", "onSingleTapUp")
        return false
    }

    override fun onDown(p0: MotionEvent?): Boolean {
        Log.d("hechangfei", "onDown")
        return false
    }

    override fun onFling(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
        Log.d("hechangfei", "onFling")
        return false
    }

    override fun onScroll(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
        Log.d("hechangfei", "onScroll")
        return false
    }

    override fun onLongPress(p0: MotionEvent?) {
        when (crudType) {
            CREATE -> {
                RouteUtils.gotoInsertEmployeeActivity(context)
            }
            READ -> {
                val intent = Intent(context, AlertDialogEmployeeActivity::class.java)
                intent.putExtra("tag", RouteUtils.RETRIEVE)
                this.startActivityForResult(intent, 110)
            }
            UPDATE -> {
                RouteUtils.gotoUpdateEmployeeActivity(context)
            }
            DELETE -> {
                RouteUtils.gotoDeleteEmployeeActivity(context)
            }
        }
        Log.d("hechangfei", "onLongPress")
    }

    companion object {
        fun newInstance(crudType: String) = CrudEmployeeFragment().apply {
            arguments = Bundle().apply {
                putString(CRUD_TYPE, crudType)
            }
        }
    }
}