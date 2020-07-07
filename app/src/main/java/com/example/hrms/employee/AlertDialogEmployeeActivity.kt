package com.example.hrms.employee

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.hrms.R
import com.example.hrms.presenter.Presenter
import com.example.hrms.view.Iview
import kotlinx.android.synthetic.main.activity_alert_dialog_employee.*

class AlertDialogEmployeeActivity (): AppCompatActivity() ,Iview{
    private var query="INSERT INTO emp (name,gender,age,addr) VALUES ('998i','male',30,'北京');"
    lateinit var presenter:Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alert_dialog_employee)
        initView()
    }

    private fun initView() {
        presenter = Presenter(this)
        positive.setOnClickListener {
            val name=nameValue.text.toString()
            val gender=genderValue.text.toString()
            val age=ageValue.text.toString()
            val addr=addrValue.text.toString()
            query= "INSERT INTO emp (name,gender,age,addr) VALUES ('$name','$gender',$age,'$addr');"
            presenter.insertInToDataBase(query)
            finish()
        }
        negative.setOnClickListener{
            finish()
        }
    }

    override fun setAdapterbyQuery(list: MutableList<Any?>?) {
        TODO("Not yet implemented")
    }

    override fun setDataSuccess(isSuccess: Boolean?) {
        Log.d("hechangfei","")
    }
}