package com.example.hrms.main.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.hrms.MainActivity
import com.example.hrms.R
import com.example.hrms.common.Crafter
import com.example.hrms.main.entity.AttendanceEntity
import com.example.hrms.main.presenter.AttendancePresenter
import com.example.hrms.main.view.AttendanceView
import com.example.hrms.presenter.Presenter
import kotlinx.android.synthetic.main.fragment_attendance.*
import java.util.*

open class AttendanceFragment : Fragment(),AttendanceView {
    private var presenter: AttendancePresenter= AttendancePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_attendance, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        titleMessage.text = "您好！${Crafter.instance.currentUser.name}，您的id是${Crafter.instance.currentUser.id}"
        loginOrSignUpRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                retrieve.id -> {
                    userId.isEnabled = true
                    leaveReason.setText("")
                    leaveReason.isEnabled = false
                }
                askForLeave.id -> {
                    userId.isEnabled = true
                    leaveReason.isEnabled = true
                }
                cancelLeave.id -> {
                    userId.isEnabled = true
                    leaveReason.setText("")
                    leaveReason.isEnabled = false
                }
            }
        }
        nextStepButton.setOnClickListener {
            when {
                retrieve.isChecked -> {
                    presenter.queryLeaveStatus(AttendanceEntity(), "select emp.`name`, attend.abool,attend.ahbool,attend.leave_reason,attend.adate\n" +
                            "from emp,attend\n" +
                            "where emp.eno=${userId.text} and emp.eno=attend.eno")
                }
                askForLeave.isChecked -> {
                    presenter.setLeaveStatus("insert into attend(eno,abool,ahbool,leave_reason,adate)\n" +
                            "values(${userId.text},0,1,'${leaveReason.text}','${getDate()}')")
                }
                cancelLeave.isChecked -> {
                    presenter.setLeaveStatus("update attend\n" +
                            "set attend.abool=1,attend.ahbool=0,attend.leave_reason=NULL\n" +
                            "where attend.eno=${userId.text}")
                }
            }
        }
    }

    override fun attendanceStatus(attendanceEntity: AttendanceEntity?, success: Boolean, extraMessage: String?) {
        if (extraMessage != null) {
            /**在主线程中支持ui操作*/
            retrieve.post {
                val toast= Toast.makeText(context, extraMessage, Toast.LENGTH_SHORT)
                toast.setText(extraMessage)
                toast.show()
            }
        }
        if (success&&attendanceEntity!=null) {
            resultName.apply {
                visibility=View.VISIBLE
                text="用户名：${attendanceEntity.name}"
            }
            resultStatus.apply {
                visibility=View.VISIBLE
                text=attendanceEntity.attendanceStatus
            }
            resultDatetime.apply {
                visibility=View.VISIBLE
                text=attendanceEntity.datetime
            }
        }
    }
    private fun getDate(): String {
        val utilDate = Date() //util.Date
        val date=java.sql.Date(utilDate.time)
        return date.toString()
    }
    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}