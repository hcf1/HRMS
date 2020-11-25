package com.example.hrms.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.hrms.R
import com.example.hrms.presenter.Presenter
import com.example.hrms.view.Iview

const val CRUD_TYPE = "CRUD_TYPE"

open class CrudFragment : Fragment(), Iview {
    companion object {
        const val CREATE = "添加"
        const val READ = "查询"
        const val UPDATE = "更新"
        const val DELETE = "删除"
    }

    protected var presenter: Presenter? = null
    protected lateinit var recyclerView: RecyclerView
    protected var crudType: String? = null
    private var linearLayout: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            crudType = it.getString(CRUD_TYPE)
        }
    }

    open fun initView() {
        presenter = Presenter(this)
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);//错位布局
    }

    private fun initData() {
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_crud, container, false)
        recyclerView = rootView.findViewById(R.id.recyclerView)
        linearLayout = rootView.findViewById(R.id.linearLayout)
        initView()
        initData()
        return rootView
    }

    override fun setAdapterByQuery(list: MutableList<Any?>?) {

    }

    override fun setDataSuccess(isSuccess: Boolean?) {

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
    }
}