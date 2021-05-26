package com.example.hiltdemo

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Created by zhengwei on 2021/5/23.
 * 1.Application必须注解@HiltAndroidApp, 触发Hilt的代码生成操作
 * 2.将依赖项注入Android类@AndroidEntryPoint
 */
private const val TAG: String = "Dagger"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var user: User
    @Inject
    lateinit var httpObject: HttpObject
    @Inject
    lateinit var httpObject2: HttpObject

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.btn_print_user_log)
        val textView = findViewById<TextView>(R.id.tv_show_msg)
        button.setOnClickListener {
            user.name = "zhengwei"
            user.age = 30
            textView.text = user.toString()
        }

        Log.i(TAG, "httpObject.hashCode: ${httpObject.hashCode()}")
        Log.i(TAG, "httpObject2.hashCode: ${httpObject2.hashCode()}")

    }
}