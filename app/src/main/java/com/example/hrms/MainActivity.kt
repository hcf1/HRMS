package com.example.hrms

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hrms.activitys.Attendance
import com.example.hrms.activitys.Department
import com.example.hrms.activitys.Employee
import com.example.hrms.activitys.Salary
import com.example.hrms.common.RouteUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        employee.setOnClickListener {
            when (it) {
                employee -> RouteUtils.gotoActivity(this, Employee::class.java)
                department -> RouteUtils.gotoActivity(this, Department::class.java)
                salary -> RouteUtils.gotoActivity(this, Salary::class.java)
                attendance -> RouteUtils.gotoActivity(this, Attendance::class.java)
            }
        }
    }
}
