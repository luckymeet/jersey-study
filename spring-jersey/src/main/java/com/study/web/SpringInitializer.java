package com.study.web;

import com.study.config.RestApplication;
import com.sun.net.httpserver.HttpServer;
import org.glassfish.jersey.servlet.ServletContainer;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServlet;


/**
 * @author yuminjun yuminjun@lexiangbao.com
 * @version 1.00
 * @date 2020/9/16 10:15
 * @record <pre>
 * version  author      date      desc
 * -------------------------------------------------
 * 1.00     yuminjun    2020/9/16   新建
 * -------------------------------------------------
 * </pre>
 */
public class SpringInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.addListener(SpringLifecycleListener.class);
        HttpServlet servletContainer = new ServletContainer(new RestApplication());
        ServletRegistration.Dynamic ser = servletContext.addServlet("servletContainer", servletContainer);
        ser.addMapping("/rest/*");
    }
}
