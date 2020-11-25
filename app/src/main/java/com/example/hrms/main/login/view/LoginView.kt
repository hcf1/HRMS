package com.example.hrms.main.login.view

import com.example.hrms.model.UserInfo

interface LoginView {
    fun showLoginStatus(statusMessage: String?, success:Boolean)
}