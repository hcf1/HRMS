package com.example.hrms.common

import com.example.hrms.main.login.model.UserInfo

/**kotlin单例模式
 * 这是一个全局共享类
 */
class Crafter private constructor() {
    companion object {
        val instance: Crafter by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            Crafter()
        }
    }
    var currentUser: UserInfo = UserInfo()
}