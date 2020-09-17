package com.study.controller;

import com.study.bean.UserBean;
import com.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Controller
@Path("users")
@Singleton
public class UserController {

    @Inject
    private UserService userService;

    @GET
    @Path(("hello"))
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello world";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UserBean getUser() {
        return userService.getUser();
    }

}
