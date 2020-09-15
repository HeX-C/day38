package com.itcast.mm.framework;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author : qiangshengchen
 * @date : 下午 2:35 28/8/2020
 */
@Target({ElementType.METHOD,ElementType.TYPE})
//运行期有效
@Retention(RetentionPolicy.RUNTIME)
//自定义注解
public @interface RequestMapping {
//    属性是属性名加一个（）
    String value();
}
