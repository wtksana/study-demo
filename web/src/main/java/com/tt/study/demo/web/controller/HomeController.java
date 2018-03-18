package com.tt.study.demo.web.controller;

import com.tt.study.demo.common.MyException;
import com.tt.study.demo.common.RespInfo;
import com.tt.study.demo.entity.User;
import com.tt.study.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * date: 2018/3/14
 * author: wt
 */
@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    @ResponseBody
    public Object index() {
        return "Hello World";
    }

    @RequestMapping("/home")
    @ResponseBody
    public Object home() {
        return RespInfo.success("Welcome Home");
    }

    @RequestMapping("/exceptions")
    @ResponseBody
    public Object exceptions() throws Exception {
        throw new MyException("error");
    }

    @RequestMapping("/addUser")
    @ResponseBody
    public Object addUser(User user) {
        return userService.addUser(user);
    }


    @RequestMapping("/login")
    @ResponseBody
    public Object login(User user) {
        return userService.login(user);
    }
}
