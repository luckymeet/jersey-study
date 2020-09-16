package com.study.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuminjun yuminjun@lexiangbao.com
 * @version 1.00
 * @date 2020/9/15 16:41
 * @record <pre>
 * version  author      date      desc
 * -------------------------------------------------
 * 1.00     yuminjun    2020/9/15   新建
 * -------------------------------------------------
 * </pre>
 */
@Configuration
@ComponentScan(value = {"com.study.controller", "com.study.service"})
public class AppConfig {
}
