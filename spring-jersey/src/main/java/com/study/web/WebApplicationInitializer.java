package com.study.web;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * @author yuminjun yuminjun@lexiangbao.com
 * @version 1.00
 * @date 2020/9/16 10:12
 * @record <pre>
 * version  author      date      desc
 * -------------------------------------------------
 * 1.00     yuminjun    2020/9/16   新建
 * -------------------------------------------------
 * </pre>
 */
public interface WebApplicationInitializer {
    /**
     * @param var1
     * @throws ServletException
     */
    void onStartup(ServletContext var1) throws ServletException;
}
