package com.example.hrms

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.hrms.activitys.Attendance
import com.example.hrms.activitys.Department
import com.example.hrms.activitys.Employee
import com.example.hrms.activitys.Salary
import com.example.hrms.common.RouteUtils
import com.example.hrms.model.JdbcManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var thread=Thread {
            JdbcManager.init()
        }
        thread?.let {
            it.priority=Thread.MAX_PRIORITY
            it.start()
        }
        employee.setOnClickListener(this)
        department.setOnClickListener(this)
        salary.setOnClickListener(this)
        attendance.setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        JdbcManager.onDestroy()
    }

    override fun onClick(p0: View?) {
        when (p0) {
            employee -> RouteUtils.gotoActivity(this, Employee::class.java)
            department -> RouteUtils.gotoActivity(this, Department::class.java)
            salary -> RouteUtils.gotoActivity(this, Salary::class.java)
            attendance -> RouteUtils.gotoActivity(this, Attendance::class.java)
        }
    }
}
