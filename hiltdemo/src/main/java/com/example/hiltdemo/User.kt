package com.example.hiltdemo

import javax.inject.Inject

/**
 * Created by zhengwei on 2021/5/23.
 */
class User @Inject constructor() {
    var name: String = ""
    var age: Int = 0
    override fun toString() = "User(name=$name age$age)"
}