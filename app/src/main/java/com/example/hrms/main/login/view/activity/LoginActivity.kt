package com.example.hrms.main.login.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hrms.MainActivity
import com.example.hrms.R
import com.example.hrms.main.login.presenter.LoginPresenter
import com.example.hrms.main.login.view.LoginView
import com.example.hrms.main.login.view.SignUpView
import com.example.hrms.model.JdbcManager
import com.example.hrms.model.UserInfo
import com.facebook.drawee.backends.pipeline.Fresco
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), SignUpView, LoginView {
    private var presenter: LoginPresenter = LoginPresenter(this, this,this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initFresco()
        initJdbc()
        initView()
    }

    private fun initView() {
        loginOrSignUpRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == login.id) {
                name.isEnabled = false
                id.isEnabled = true
            } else {
                name.isEnabled = true
                id.isEnabled = false
            }
        }
        nextStepButton.setOnClickListener {
            if (login.isChecked) {
                presenter.login(UserInfo(), "select emp.eno,emp.`name` from emp where emp.eno=${id.text} and emp.password='${password.text}'")
            } else if (sign_up.isChecked) {
                presenter.signUp("INSERT INTO emp (name,password) VALUES ('${name.text}','${password.text}')")
            }
        }
    }

    private fun initJdbc() {
        val thread = Thread {
            JdbcManager.initConnection()
        }
        thread.let {
            it.priority = Thread.MAX_PRIORITY
            it.start()
        }
    }

    private fun initFresco() {
        Fresco.initialize(this)
    }

    /**注冊*/
    override fun showSignUpStatus(statusMessage: String?, success: Boolean) {
        if (statusMessage != null) {
            val toast= Toast.makeText(applicationContext, statusMessage, Toast.LENGTH_LONG)
            toast.setText(statusMessage)
            toast.show()
        }
    }

    /**登录*/
    override fun showLoginStatus(statusMessage: String?, success: Boolean) {
        if (statusMessage != null) {
           val toast= Toast.makeText(applicationContext, statusMessage, Toast.LENGTH_LONG)
            toast.setText(statusMessage)
            toast.show()
        }
        if (success) {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}