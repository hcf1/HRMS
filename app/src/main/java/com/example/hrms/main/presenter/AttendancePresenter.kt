package com.example.hrms.main.presenter

import com.example.hrms.main.entity.AttendanceEntity
import com.example.hrms.main.view.AttendanceView
import com.example.hrms.model.JdbcManager
import rx.android.schedulers.AndroidSchedulers
import rx.subscriptions.CompositeSubscription


class AttendancePresenter(private val attendanceView: AttendanceView) {
    private val compositeSubscription: CompositeSubscription? = CompositeSubscription()
    fun <T : Any> queryLeaveStatus(t: T, query: String?) {
        val subscription = JdbcManager.query(query)
                .observeOn(AndroidSchedulers.mainThread()) //在主线程中操作UI
                .doOnNext { resultSet ->
                    if (t::class.java.name == AttendanceEntity::class.java.name && resultSet != null && resultSet.last()) {//获取最新的请销假记录
                        val attendanceEntity = AttendanceEntity()
                        attendanceEntity.apply {
                            name = resultSet.getString(1)
                            isAttendance = resultSet.getInt(2)==1
                            isLeave = resultSet.getInt(3)==1
                            leaveReason = resultSet.getString(4)
                            datetime = resultSet.getString(5)
                            if (isLeave!!){
                                attendanceStatus="请假中：$leaveReason"
                            }
                            else if (isAttendance!!){
                                attendanceStatus="出勤中。。。"
                            }
                        }
                        attendanceView.attendanceStatus(attendanceEntity,true,"查询成功！")
                    } else {
                        attendanceView.attendanceStatus(null,false,"查询失败，请检查网络和用户id！")
                    }

                }.subscribe()
        compositeSubscription!!.add(subscription)
    }

    fun setLeaveStatus(query: String?) {
        val subscription = JdbcManager.executeUpdate(query)
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnNext { defaultEntity ->
                    if (defaultEntity.affectedRows > 0) {
                        attendanceView.attendanceStatus(null,true,"设置成功！")
                    }
                    else{
                        attendanceView.attendanceStatus(null,false,"设置失败，请检查网络和用户id！")
                    }
                }.subscribe()
        compositeSubscription!!.add(subscription)
    }

    fun onDestroy() {
        compositeSubscription?.clear()
    }
}