package com.itcast.mm.controller;

import com.itcast.mm.framework.Controller;
import com.itcast.mm.framework.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : qiangshengchen
 * @date : 下午 5:21 28/8/2020
 */
@Controller
public class MyController {
    @RequestMapping("/user/register")
    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().write("注册");
    }

    @RequestMapping("/user/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().write("登陆");
    }

}
