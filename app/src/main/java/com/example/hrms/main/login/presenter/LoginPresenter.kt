package com.example.hrms.main.login.presenter

import android.app.Activity
import com.example.hrms.common.Crafter
import com.example.hrms.main.login.view.LoginView
import com.example.hrms.main.login.view.SignUpView
import com.example.hrms.model.JdbcManager
import com.example.hrms.main.login.model.UserInfo
import rx.android.schedulers.AndroidSchedulers
import rx.subscriptions.CompositeSubscription

class LoginPresenter(private val loginView: LoginView, private val signUpView: SignUpView,private val acticity:Activity) {
    private val compositeSubscription: CompositeSubscription? = CompositeSubscription()
    fun <T : Any> login(t: T, query: String?) {
        val subscription = JdbcManager.query(query)
                .observeOn(AndroidSchedulers.mainThread()) //在主线程中操作UI
                .doOnNext { resultSet ->
                    if (t::class.java.name == UserInfo::class.java.name && resultSet != null && resultSet.next()) {//最开始默认指向第0行，数据从第1行开始取
                        Crafter.instance.currentUser.apply {
                            id = resultSet.getInt(1)
                            name = resultSet.getString(2)
                        }
                        loginView.showLoginStatus("欢迎您，${Crafter.instance.currentUser.name}", true)
                    } else {
                        loginView.showLoginStatus("登录失败，请检查用户id和密码是否正确！", false)
                    }

                }.subscribe()
        compositeSubscription!!.add(subscription)
    }

    fun signUp(query: String?) {
        val subscription = JdbcManager.executeLogin(query)
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnNext { resultSet ->
                    if (resultSet != null && resultSet.next()) {
                        Crafter.instance.currentUser.id = resultSet.getInt(1)
                        login(UserInfo(), "select emp.eno,emp.`name` from emp where emp.eno=${Crafter.instance.currentUser.id}")
                        acticity.runOnUiThread {
                            signUpView.showSignUpStatus("注册成功，自动跳转中...", true)
                        }
                    } else {
                        signUpView.showSignUpStatus("注册失败，请重试！", false)
                    }
                }.subscribe()
        compositeSubscription!!.add(subscription)
    }

    fun onDestroy() {
        compositeSubscription?.clear()
    }
}