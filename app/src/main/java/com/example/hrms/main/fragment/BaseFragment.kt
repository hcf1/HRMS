package com.example.hrms.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.hrms.R
import com.google.android.material.tabs.TabLayout
import kotlin.math.abs

open class BaseFragment : Fragment() {
    protected lateinit var viewPager: ViewPager2
    protected lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    /**设置viewPager的切换动画*/
    private val mAnimator = ViewPager2.PageTransformer { page, position ->
        val absPos = abs(position)
        page.apply {
            val scale = if (absPos > 1) 0F else 1 - absPos
            scaleX = scale
            scaleY = scale
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_base, container, false)
        initView(rootView)
        return rootView
    }

    private fun initView(rootView: View) {
        tabLayout = rootView.findViewById(R.id.ViewPagerTab)
        viewPager = rootView.findViewById(R.id.viewPager)
        viewPager.setPageTransformer(mAnimator)
        viewPager.adapter = null
    }
}