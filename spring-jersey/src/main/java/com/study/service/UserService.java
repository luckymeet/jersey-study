package com.study.service;

import com.study.bean.UserBean;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public UserBean getUser() {
        return new UserBean(1, "王富豪", 26);
    }

}
