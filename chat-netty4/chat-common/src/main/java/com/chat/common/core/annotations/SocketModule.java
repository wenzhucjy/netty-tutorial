package com.chat.common.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * description: 请求的模块注解接口
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-24 18:02
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SocketModule {

    /**
     * 请求的模块号
     * 
     * @return 模块号
     */
    short module();
}
