package com.sunriseframework.nds.config;

import org.opencv.core.Core;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.net.URL;

/**
 * @Title 配置类
 * @Description Spring框架配置类
 * @author chenhao
 * @version 1.0 2021-09-18
 */
@SuppressWarnings("unused")
@Configuration
// 开发
@PropertySource("classpath:NdsServiceApp.properties")
//@PropertySource("${user.dir}/NdsServiceApp.properties")

// 测试及生产-不启用配置中心
//@PropertySource("classpath:conf/NdsServiceApp.properties")

// 测试及生产-启用配置中心
//@PropertySources(value={
//		@PropertySource("classpath:conf/NdsServiceApp.properties"),
//		@PropertySource("url:${nds.service.config}")
//})
public class SpringConfig {

}
