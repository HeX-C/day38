package com.itcast.mm.framework;

import java.lang.reflect.Method;

/**
 * @author : qiangshengchen
 * @date : 下午 5:25 28/8/2020
 */
public class MethodObject {
    private Method method;
    private Object object;

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
