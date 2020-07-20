package com.example.hrms.main.entity

import androidx.fragment.app.Fragment
import com.example.hrms.R
import com.example.hrms.main.view.CrudAttantenceFragment
import com.example.hrms.main.view.CrudDepartmentFragment
import com.example.hrms.main.view.CrudEmployeeFragment
import com.example.hrms.main.view.CrudSalaryFragment

class FragmentManager {
    companion object {
        const val MAIN_FRAGMENT = 0
        const val EMPLOYEE = 1
        const val DEPARTMENT = 2
        const val SALARY = 3
        const val ATTENDANCE = 4

        private lateinit var crudEmployeeFragments: List<Fragment>
        private lateinit var crudDepartmentFragments: List<Fragment>
        private lateinit var crudSalaryFragments: List<Fragment>
        private lateinit var crudAttantenceFragments: List<Fragment>
        fun initFragment(fragmentId: Int) {
            when (fragmentId) {
                EMPLOYEE -> {
                    crudEmployeeFragments = arrayListOf(CrudEmployeeFragment("添加", "添加"),
                            CrudEmployeeFragment("查询", "查询"),
                            CrudEmployeeFragment("更新", "更新"),
                            CrudEmployeeFragment("删除", "删除"))
                }
                DEPARTMENT -> {
                    crudDepartmentFragments = arrayListOf(CrudDepartmentFragment("添加", "添加"),
                            CrudDepartmentFragment("查询", "查询"),
                            CrudDepartmentFragment("更新", "更新"),
                            CrudDepartmentFragment("删除", "删除"))
                }
                SALARY -> {
                    crudSalaryFragments = arrayListOf(CrudSalaryFragment("添加", "添加"),
                            CrudSalaryFragment("查询", "查询"),
                            CrudSalaryFragment("更新", "更新"),
                            CrudSalaryFragment("删除", "删除"))
                }
                ATTENDANCE -> {
                    crudAttantenceFragments = arrayListOf(CrudAttantenceFragment("添加", "添加"),
                            CrudAttantenceFragment("查询", "查询"),
                            CrudAttantenceFragment("更新", "更新"),
                            CrudAttantenceFragment("删除", "删除"))
                }
            }
        }

        fun getFragment(fragmentId: Int, position: Int): Fragment? {
            return when (fragmentId) {
                EMPLOYEE -> {
                    crudEmployeeFragments[position]
                }
                DEPARTMENT -> {
                    crudDepartmentFragments[position]
                }
                SALARY -> {
                    crudSalaryFragments[position]
                }
                ATTENDANCE -> {
                    crudAttantenceFragments[position]
                }
                else -> null
            }
        }

        fun getFragments(fragmentId: Int): List<Fragment>? {
            return when (fragmentId) {
                EMPLOYEE -> {
                    crudEmployeeFragments
                }
                DEPARTMENT -> {
                    crudDepartmentFragments
                }
                SALARY -> {
                    crudSalaryFragments
                }
                ATTENDANCE -> {
                    crudAttantenceFragments
                }
                else -> null
            }
        }

        fun getFragmentNames(fragmentId: Int): List<String>? {
            return when (fragmentId) {
                MAIN_FRAGMENT -> {
                    return arrayListOf("员工管理", "部门管理", "薪资管理", "出勤管理")
                }
                EMPLOYEE -> {
                    return arrayListOf("添加", "查询", "更新", "删除")
                }
                DEPARTMENT -> {
                    return arrayListOf("添加", "查询", "更新", "删除")
                }
                SALARY -> {
                    return arrayListOf("添加", "查询", "更新", "删除")
                }
                ATTENDANCE -> {
                    return arrayListOf("添加", "查询", "更新", "删除")
                }
                else -> null
            }
        }

        fun getPagerViewTabs(fragmentId: Int): List<Int>? {
            return when (fragmentId) {
                MAIN_FRAGMENT -> {
                    return arrayListOf()
                }
                EMPLOYEE -> {
                    return arrayListOf(R.mipmap.button_view_pager_add,
                            R.mipmap.button_view_pager_read,
                            R.mipmap.button_view_pager_update,
                            R.mipmap.button_view_pager_delete)
                }
                DEPARTMENT -> {
                    return arrayListOf(R.mipmap.button_view_pager_add,
                            R.mipmap.button_view_pager_read,
                            R.mipmap.button_view_pager_update,
                            R.mipmap.button_view_pager_delete)
                }
                SALARY -> {
                    return arrayListOf(R.mipmap.button_view_pager_add,
                            R.mipmap.button_view_pager_read,
                            R.mipmap.button_view_pager_update,
                            R.mipmap.button_view_pager_delete)
                }
                ATTENDANCE -> {
                    return arrayListOf(R.mipmap.button_view_pager_add,
                            R.mipmap.button_view_pager_read,
                            R.mipmap.button_view_pager_update,
                            R.mipmap.button_view_pager_delete)
                }
                else -> null
            }
        }
    }
}