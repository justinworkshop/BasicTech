package com.example.tps

import com.tencent.opentelemetry.common.Attributes
import com.tencent.opentelemetry.core.TpsTelemetrySdk


/**
 * Created by zhengwei on 2021/7/9.
 */
class TpsManager {

    fun init() {
        val tenantID = "default" // 天机阁的租户ID
        val fraction = 0.001 // 采样率，除了染色功能还可以指定采样比例
        // 获取SDK对象，并设置租户ID
        val telemetrySdk = TpsTelemetrySdk.getTpsTelemetrySdk().setTenantID(tenantID).withDefaultExporter()
        // 设置属性标签，这里可以自定义一些通用的属性，如名称，当前系统的某项环境信息
        telemetrySdk.withResource(Attributes.newBuilder().setAttribute("xxx.namespace", "Production").build())
        // 设置采样器，这里已经配置了默认天机阁的染色采样实现
        telemetrySdk.withSampler(fraction)
        // 所有信息配置完成，调用初始化即可
        telemetrySdk.init()
    }
}