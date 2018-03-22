package com.tt.study.demo.web.controller;

import com.tt.study.demo.common.MyException;
import com.tt.study.demo.common.RespInfo;
import com.tt.study.demo.entity.User;
import com.tt.study.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

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
    public Object index() throws SocketException {
        StringBuilder sb = new StringBuilder();
        sb.append("ip");
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()) {
            Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
            while (inetAddresses.hasMoreElements()) {
                InetAddress inetAddress = inetAddresses.nextElement();
                if (inetAddress != null && inetAddress instanceof Inet4Address) {
                    sb.append(":").append(inetAddress.getHostAddress());
                }
            }
        }
        return sb.toString();
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
    public Object login(User user, HttpSession session) {
        RespInfo<User> respInfo = userService.login(user);
        if (respInfo.isSuccess()) {
            session.setAttribute("user_session", respInfo.getData());
        }
        return respInfo;
    }


    @RequestMapping("/getUser")
    @ResponseBody
    public Object getUser(Long id) {
        return userService.getUser(id);
    }

    @RequestMapping("/getUserList")
    @ResponseBody
    public Object getUserList() {
        return userService.getUserList();
    }

}
