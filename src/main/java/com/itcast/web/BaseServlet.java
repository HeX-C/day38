package com.itcast.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author : qiangshengchen
 * @date : 上午 11:44 28/8/2020
 */
@WebServlet("/BaseServlet")
public class BaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //处理编码
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        try {
            //1. 获得method请求参数的值【说白了就是方法名】
            String methodStr =  request.getParameter("method");
            //2.获得字节码对象
            Class clazz  = this.getClass();
            //3.根据方法名反射获得Method
            Method method = clazz.getMethod(methodStr,HttpServletRequest.class,HttpServletResponse.class);
            //4.调用
            method.invoke(this,request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
