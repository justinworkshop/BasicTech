package com.example.view

/**
 * Created by zhengwei on 2021/9/27.
 */
class Test {
    var anchor : Anchor? = null
    fun getUid(): String {
        android.util.Log.i("TestKt", ">> 为空怎么输出：" + anchor?.uid)
        return anchor?.uid?.toString() ?: "0"
    }
}