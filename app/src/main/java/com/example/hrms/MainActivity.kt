package com.example.hrms

import android.os.Bundle
import android.view.View
import com.example.hrms.base.BaseMainActivity
import com.example.hrms.main.MainTabHost
import com.example.hrms.main.fragment.AttendanceFragment
import com.example.hrms.main.fragment.DepartmentFragment
import com.example.hrms.main.fragment.EmployeeFragment
import com.example.hrms.main.fragment.SalaryFragment
import com.example.hrms.main.listener.OnCheckedChangedListener
import com.example.hrms.model.JdbcManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseMainActivity(), View.OnClickListener, OnCheckedChangedListener {
    private lateinit var mainTabHost: MainTabHost
    private var transaction = supportFragmentManager.beginTransaction()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var thread = Thread {
            JdbcManager.init()
        }
        thread?.let {
            it.priority = Thread.MAX_PRIORITY
            it.start()
        }
        mainTabHost = main_tab
        mainTabHost.setOnCheckedChangeListener(this)
        transaction.apply {
            replace(R.id.fragmentContainer, EmployeeFragment.newInstance())
        }
        transaction.commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        JdbcManager.onDestroy()
    }

    override fun onClick(selectFunction: View?) {

    }

    override fun onCheckedChange(position: Int) {
        transaction = supportFragmentManager.beginTransaction()
        when (position) {
            1 -> {
                transaction.apply {
                    replace(R.id.fragmentContainer, EmployeeFragment.newInstance())
                }
                transaction.commit()
            }
            2 -> {
                transaction.apply {
                    replace(R.id.fragmentContainer, DepartmentFragment())
                }
                transaction.commit()
            }
            3 -> {
                transaction.apply {
                    replace(R.id.fragmentContainer, SalaryFragment())
                }
                transaction.commit()
            }
            4 -> {
                transaction.apply {
                    replace(R.id.fragmentContainer, AttendanceFragment())
                }
                transaction.commit()
            }
        }
    }
}
