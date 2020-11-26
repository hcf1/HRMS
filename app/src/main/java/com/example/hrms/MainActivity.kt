package com.example.hrms

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.WindowManager
import com.example.hrms.base.BaseMainActivity
import com.example.hrms.main.MainTabHost
import com.example.hrms.main.fragment.AttendanceFragment
import com.example.hrms.main.fragment.DepartmentFragment
import com.example.hrms.main.fragment.EmployeeFragment
import com.example.hrms.main.fragment.SalaryFragment
import com.example.hrms.main.listener.OnCheckedChangedListener
import com.example.hrms.metaapp.ShowActivity
import com.example.hrms.model.JdbcManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseMainActivity(), OnCheckedChangedListener {
    private lateinit var mainTabHost: MainTabHost
    private var transaction = supportFragmentManager.beginTransaction()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.statusBarColor = Color.parseColor("#9999CC");
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)//元素不被键盘顶起
        initFragment()
        /**metaApp图片浏览代码遗留*/
        button.setOnClickListener {
            val intent = Intent(this, ShowActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initFragment() {
        mainTabHost = main_tab
        mainTabHost.setOnCheckedChangeListener(this)
        transaction.apply {
            replace(R.id.fragmentContainer, EmployeeFragment())
        }
        transaction.commit()
    }


    override fun onDestroy() {
        super.onDestroy()
        JdbcManager.onDestroy()
    }

    override fun onCheckedChange(position: Int) {
        transaction = supportFragmentManager.beginTransaction()
        when (position) {
            1 -> {
                transaction.apply {
                    replace(R.id.fragmentContainer, EmployeeFragment())
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
