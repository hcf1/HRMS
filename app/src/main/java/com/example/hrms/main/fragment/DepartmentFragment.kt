package com.example.hrms.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.hrms.main.entity.FragmentManager
import com.google.android.material.tabs.TabLayoutMediator

class DepartmentFragment : BaseFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        FragmentManager.initFragment(FragmentManager.DEPARTMENT)
        val rootView=super.onCreateView(inflater, container, savedInstanceState)
        viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return FragmentManager.getFragments(FragmentManager.DEPARTMENT)!!.size
            }

            override fun createFragment(position: Int): Fragment {
                return FragmentManager.getFragment(FragmentManager.DEPARTMENT,position)!!
            }
        }
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = FragmentManager.getFragmentNames(FragmentManager.DEPARTMENT)?.get(position).toString()
            tab.setIcon(FragmentManager.getPagerViewTabs(FragmentManager.DEPARTMENT)!![position])
        }.attach()
        return rootView
    }
}