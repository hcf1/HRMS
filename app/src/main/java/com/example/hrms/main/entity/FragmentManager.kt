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
                    crudEmployeeFragments = arrayListOf(CrudEmployeeFragment.newInstance("添加"),
                            CrudEmployeeFragment.newInstance("查询"),
                            CrudEmployeeFragment.newInstance("更新"),
                            CrudEmployeeFragment.newInstance("删除"))
                }
                DEPARTMENT -> {
                    crudDepartmentFragments = arrayListOf(CrudDepartmentFragment.newInstance("添加"),
                            CrudDepartmentFragment.newInstance("查询"),
                            CrudDepartmentFragment.newInstance("更新"),
                            CrudDepartmentFragment.newInstance("删除"))
                }
                SALARY -> {
                    crudSalaryFragments = arrayListOf(CrudSalaryFragment.newInstance("添加"),
                            CrudSalaryFragment.newInstance("查询"),
                            CrudSalaryFragment.newInstance("更新"),
                            CrudSalaryFragment.newInstance("删除"))
                }
                ATTENDANCE -> {
                    crudAttantenceFragments = arrayListOf(CrudAttantenceFragment.newInstance("添加"),
                            CrudAttantenceFragment.newInstance("查询"),
                            CrudAttantenceFragment.newInstance("更新"),
                            CrudAttantenceFragment.newInstance("删除"))
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