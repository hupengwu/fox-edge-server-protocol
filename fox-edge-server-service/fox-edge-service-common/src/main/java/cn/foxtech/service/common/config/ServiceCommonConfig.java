/* ----------------------------------------------------------------------------
 * Copyright (c) Guangzhou Fox-Tech Co., Ltd. 2020-2024. All rights reserved.
 * --------------------------------------------------------------------------- */

package cn.foxtech.service.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 告知Spring框架去扫描其他包中的Component
 */
@Configuration
@ComponentScan(basePackages = {//
        "cn.foxtech.common.entity.service",//
        "cn.foxtech.common.entity.manager",//
        "cn.foxtech.utils.common.utils.redis.*",//
        "cn.foxtech.common.status"//
})
@MapperScan("cn.foxtech.common.entity.service")
public class ServiceCommonConfig {
}
