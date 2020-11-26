package com.example.hrms.main.view

import com.example.hrms.main.entity.AttendanceEntity

interface AttendanceView {
    fun attendanceStatus(attendanceEntity: AttendanceEntity?,success:Boolean,extraMessage:String?)
}