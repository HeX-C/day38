package com.itcast.mm.framework;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : qiangshengchen
 * @date : 下午 2:38 28/8/2020
 */

public class DispatcherServlet extends HttpServlet {

    private Map<String, MethodObject> map = new HashMap<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);  //不要删

        try {
//        2、获取注解值，比较
//        获取class
            List<Class<?>> classes = ClassScannerUtils.
                    getClasssFromPackage(config.getInitParameter("ScannerPackage"));

//         遍历class
            for (Class<?> aClass : classes) {

//         判断类是否被标记
                if (aClass.isAnnotationPresent(Controller.class)) {
                    Method[] methods = aClass.getMethods();

//         获取所有方法，遍历
                    for (Method method : methods) {
                        //        判断方法是否被注解
                        if (method.isAnnotationPresent(RequestMapping.class)) {

                            //        获取注解值
                            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                            String value = requestMapping.value();

                            //      给MethodObject赋值并存储至Map集合中
                            MethodObject methodObject = new MethodObject();
                            methodObject.setMethod(method);
                            methodObject.setObject(aClass.newInstance());

                            map.put(value, methodObject);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        处理编码
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
//        写总控制器逻辑

//        1、解析请求路径/user/login
        String requestURI = request.getRequestURI();//    /day38/user/login.do
        String contextPath = request.getContextPath();//    /day38

        String requestPath = requestURI.substring(contextPath.length()).
                replace(".do", "");//   /user/login

//        调用方法
        MethodObject methodObject = map.get(requestPath);
        if (methodObject != null) {
            try {
                methodObject.getMethod().invoke(methodObject.getObject(), request, response);
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            response.getWriter().write("您失败了" + request.getRequestURL());
        }
    }
}
