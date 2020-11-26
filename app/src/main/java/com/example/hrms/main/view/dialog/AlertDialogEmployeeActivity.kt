package com.example.hrms.main.view.dialog

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.hrms.R
import com.example.hrms.common.RouteUtils
import com.example.hrms.presenter.Presenter
import com.example.hrms.view.Iview
import kotlinx.android.synthetic.main.activity_alert_dialog_employee.*

class AlertDialogEmployeeActivity() : AppCompatActivity(), Iview {
    private var query: String = ""
    lateinit var presenter: Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alert_dialog_employee)
        initView()
    }

    private fun initView() {
        presenter = Presenter(this)
        if (intent.extras != null) {
            when (intent.extras!!.getInt("tag")) {
                RouteUtils.RETRIEVE -> {
                    dnoItem.visibility = View.VISIBLE
                    this.title = "查询员工"
                }
                RouteUtils.UPDATE -> {
                    enoItem.visibility = View.VISIBLE
                    this.title = "修改员工信息"
                }
                RouteUtils.DELETE -> {
                    enoItem.visibility = View.VISIBLE
                    hint.visibility = View.VISIBLE
                    this.title = "删除员工"
                }
            }
        }
        positive.setOnClickListener {
            val eno = enoValue.text.toString()
            val name = nameValue.text.toString()
            val gender = genderValue.text.toString()
            val age = ageValue.text.toString()
            val addr = addrValue.text.toString()
            var dno=dnoValue.text.toString()
            if (intent.extras != null) {
                when (intent.extras!!.getInt("tag")) {
                    RouteUtils.RETRIEVE -> {
                        query = ""
//                        "SELECT emp.eno,emp.name,emp.gender,emp.age,emp.addr,emp.mail,emp.phonenum,dept.dname FROM emp,dept WHERE emp.eno=1"
                        query = "SELECT emp.eno,emp.name,emp.gender,emp.age,emp.addr,emp.mail,emp.phonenum,dept.dname FROM emp,dept WHERE "
                        if (eno != "") {
                            query += "ENO=$eno AND "
                        }
                        if (name != "") {
                            query += "NAME='$name' AND "
                        }
                        if (gender != "") {
                            query += "gender='$gender' and "
                        }
                        if (age != "") {
                            query += "age=$age and "
                        }
                        if (addr != "") {
                            query += "addr='$addr' and "
                        }
                        if (dno != "") {
                            query += "dept.dno=$dno"
                        } else {
                            query += "true"
                        }
                        intent.putExtra("query", query)
                        setResult(110, intent)
                        finish()
                    }
                    RouteUtils.INSERT -> {
                        query = "INSERT INTO emp (name,gender,age,addr) VALUES ('$name','$gender',$age,'$addr');"
                        presenter.updateDataBase(query)
                    }
                    RouteUtils.UPDATE -> {
                        query = "UPDATE emp SET name = '$name',gender='$gender',age=$age,addr='$addr' WHERE eno = '$eno'"
                        presenter.updateDataBase(query)
                    }
                    RouteUtils.DELETE -> {
//                        query="DELETE FROM emp WHERE ENO=$eno AND NAME='$name' AND gender='$gender' and age=$age and addr='$addr'"
                        query = "DELETE FROM emp WHERE "
                        if (eno != "") {
                            query += "ENO=$eno AND "
                        }
                        if (name != "") {
                            query += "NAME='$name' AND "
                        }
                        if (gender != "") {
                            query += "gender='$gender' and "
                        }
                        if (age != "") {
                            query += "age=$age and "
                        }
                        if (addr != "") {
                            query += "addr='$addr'"
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

    override fun setAdapterByQuery(list: MutableList<Any?>?) {
        TODO("Not yet implemented")
    }

    override fun setDataSuccess(isSuccess: Boolean?) {
        Log.d("hechangfei", "")
    }
}