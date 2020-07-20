package com.example.hrms.base

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.hrms.R

open class BaseMainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_main)

    }

}