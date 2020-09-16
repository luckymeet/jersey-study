package com.study;

import com.study.config.AppConfig;
import com.study.config.RestApplication;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.model.Resource;
import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.Servlet;
import java.util.Set;

public class Application {

    public static void main(String[] args) {
        Server server = new Server(8080);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.register(AppConfig.class);
        ac.refresh();

        RestApplication resourceConfig = new RestApplication();
        Servlet servletContainer = new ServletContainer(resourceConfig);
        context.addServlet(new ServletHolder(servletContainer), "/*");

        Set<Object> singletons = resourceConfig.getSingletons();
        System.out.println(singletons);

        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
