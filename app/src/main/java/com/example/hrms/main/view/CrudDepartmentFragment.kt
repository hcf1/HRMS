package com.example.hrms.main.view

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import com.example.hrms.common.RouteUtils
import com.example.hrms.entity.DepartmentEntity
import com.example.hrms.view.adapter.DepartmentAdapter

class CrudDepartmentFragment(crud: String, functionModel: String) : CrudFragment(crud, functionModel), GestureDetector.OnGestureListener {
    private val query = "SELECT * FROM dept"
    private lateinit var departmentAdapter: DepartmentAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = super.onCreateView(inflater, container, savedInstanceState)
        departmentAdapter = DepartmentAdapter()
        val gestureDetector=GestureDetector(context,this)
        recyclerView.setOnTouchListener { view: View, motionEvent: MotionEvent ->
            gestureDetector.onTouchEvent(motionEvent)
        }
        linearLayout?.setBackgroundColor(Color.parseColor("#e5e5ff"))
        return rootView
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

    override fun onShowPress(p0: MotionEvent?) {
        Log.d("hechangfei","onShowPress")
    }

    override fun onSingleTapUp(p0: MotionEvent?): Boolean {
        Log.d("hechangfei","onSingleTapUp")
        return false
    }

    override fun onDown(p0: MotionEvent?): Boolean {
        Log.d("hechangfei","onDown")
        return false
    }

    override fun onFling(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
        Log.d("hechangfei","onFling")
        return false
    }

    override fun onScroll(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
        Log.d("hechangfei","onScroll")
        return false
    }

    override fun onLongPress(p0: MotionEvent?) {
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
}