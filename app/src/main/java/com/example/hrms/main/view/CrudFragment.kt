package com.example.hrms.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.hrms.R
import com.example.hrms.presenter.Presenter
import com.example.hrms.view.Iview

open class CrudFragment(crud: String, functionModel: String) : Fragment(), Iview {
    companion object {
        const val CREATE = "添加"
        const val READ = "查询"
        const val UPDATE = "更新"
        const val DELETE = "删除"
    }

    protected var presenter: Presenter? = null
    protected lateinit var executeView: TextView
    protected lateinit var executeImage: ImageView
    protected lateinit var executeItem: LinearLayout
    protected lateinit var recyclerView: RecyclerView
    protected var crud: String? = null
    protected var functionModel: String? = null

    init {
        this.crud = crud
        this.functionModel = functionModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    open fun initView() {//如果父类中A调用B，子类调用父类A。// 子类一旦重写父类B方法，子类中调用父类A方法时，父类A方法将调用重写后的子类B方法，不再是自己的B方法
        presenter = Presenter(this)
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);//错位布局
    }

    open fun setExecuteButtonListener() {

    }

    private fun initData() {
        executeView.text = crud.toString()
        when (crud) {
            CREATE -> {
                executeImage.setImageResource(R.mipmap.button_view_pager_add)
            }
            READ -> {
                executeImage.setImageResource(R.mipmap.button_view_pager_read)
            }
            UPDATE -> {
                executeImage.setImageResource(R.mipmap.button_view_pager_update)
            }
            DELETE -> {
                executeImage.setImageResource(R.mipmap.button_view_pager_delete)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_crud, container, false)
        executeView = rootView.findViewById(R.id.executeView)
        executeImage = rootView.findViewById(R.id.executeImage)
        executeItem = rootView.findViewById(R.id.executeItem)
        recyclerView = rootView.findViewById(R.id.recyclerView)
        initView()
        initData()
        return rootView
    }

    override fun setAdapterbyQuery(list: MutableList<Any?>?) {

    }

    override fun setDataSuccess(isSuccess: Boolean?) {

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
    }
}