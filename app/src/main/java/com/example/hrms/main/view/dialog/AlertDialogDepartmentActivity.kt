package com.example.hrms.main.view.dialog

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.hrms.R
import com.example.hrms.common.RouteUtils
import com.example.hrms.presenter.Presenter
import com.example.hrms.view.Iview
import kotlinx.android.synthetic.main.activity_alert_dialog_department.*
import java.util.*

class AlertDialogDepartmentActivity : AppCompatActivity(), Iview {
    private var query: String = ""
    lateinit var presenter: Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alert_dialog_department)
        initView()
    }

    private fun initView() {
        presenter = Presenter(this)
        if (intent.extras != null) {
            when (intent.extras!!.getInt("tag")) {
                RouteUtils.UPDATE -> {
                    this.title = "修改部门信息"
                    enoItem.visibility = View.VISIBLE//部门编号修改框
                    timeItem.visibility = View.VISIBLE//时间修改框
                }
                RouteUtils.DELETE -> {
                    enoItem.visibility = View.VISIBLE//部门编号修改框
                    timeItem.visibility = View.VISIBLE//时间修改框
                    hint.visibility = View.VISIBLE
                    this.title = "撤销部门"
                }
            }
        }
        positive.setOnClickListener {
            val eno = enoValue.text.toString()//主管员工号
            val name = nameValue.text.toString()//部门名称
            val dno = dnoValue.text.toString()//部门编号
            var time = getDate()//创建时间
            if (intent.extras != null) {
                when (intent.extras!!.getInt("tag")) {
                    RouteUtils.INSERT -> {
                        query = "INSERT INTO dept (dname,eno,dtime) VALUES ('$name','$eno','$time');"
                        presenter.updateDataBase(query)
                    }
                    RouteUtils.UPDATE -> {
                        query = "UPDATE dept SET dname = '$name',eno='$eno',dtime='$time' WHERE dno = '$dno'"
                        presenter.updateDataBase(query)
                    }
                    RouteUtils.DELETE -> {
                        time=timeValue.text.toString()
                        query = "DELETE FROM dept WHERE "
                        if (dno != "") {
                            query += "dno=$dno AND "
                        }
                        if (name != "") {
                            query += "dname='$name' AND "
                        }
                        if (eno != "") {
                            query += "eno='$eno' and "
                        }
                        if (time != "") {
                            query += "dtime=$time and "
                        } else {
                            query += "true"
                        }
                        presenter.updateDataBase(query)
                    }
                }
            }
            finish()
        }
        negative.setOnClickListener {
            finish()
        }
    }

    private fun getDate(): String {
        val utilDate = Date() //util.Date
        var date=java.sql.Date(utilDate.time)
        return date.toString()
    }

    override fun setAdapterByQuery(list: MutableList<Any?>?) {
        TODO("Not yet implemented")
    }

    override fun setDataSuccess(isSuccess: Boolean?) {
        Log.d("hechangfei", "")
    }
}