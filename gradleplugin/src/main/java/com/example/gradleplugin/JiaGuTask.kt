package com.example.gradleplugin

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.io.File

/**
 * Created by zhengwei on 2021/4/17.
 */
class JiaGuTask : DefaultTask() {
    //group会让对应应用plugin的Module Gradle有一个分组：jiagu
    init {
        group = "jiagu"
    }

    private lateinit var apk: File
    private lateinit var jiaGuExt: JiaGuExt

    @TaskAction
    fun doJiaGu(){
        //通过注解来找到对应的fun
//        Runtime.getRuntime().exec("javac -v")
        project.exec() {
            it.commandLine("java", "-version")

            //执行其他任务
            it.commandLine("")
        }
    }
}