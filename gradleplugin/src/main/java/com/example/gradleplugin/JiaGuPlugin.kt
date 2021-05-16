package com.example.gradleplugin

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * 插件的使用：
 * 1.插件上传仓库，可以是JCenter，也可以本地仓库
 * 2.引用插件
 * 根目录build.gradle引用classpath
 * 如：classpath 'com.example:JiaGu:1.0'
 * 对象Module build.gradle使用apply plugin: '[META-INF内properties文件名]'
 * 如：apply plugin: 'com.example.jiagu'
 */
class JiaGuPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        println("Hello JiaGuPlugin")

        //插件可以让用户配置JiaGuExt中的参数
        //name:JiaGu是用于在build.gradle中使用的名字来闭包配置信息
        val jiaGuExt: JiaGuExt = project.extensions.create("JiaGuExt", JiaGuExt::class.java)
        project.afterEvaluate() {
            println(jiaGuExt.userName)
            println(jiaGuExt.userPwd)

            //获得待加固的apk
            val appExtension = project.extensions.getByType(AppExtension::class.java)
            println(appExtension.compileSdkVersion)
            println(appExtension.buildToolsVersion)

            appExtension.applicationVariants.all() {
                it.outputs.all() { baseVariantOutput ->
                    val file = baseVariantOutput.outputFile
                    //这个file默认有debug与release， 如果配置来flavor就会有更多
                    val name = baseVariantOutput.name
                    project.tasks.create("jiaGu$name", JiaGuTask::class.java, file, jiaGuExt)
                }
            }
        }
    }
}

