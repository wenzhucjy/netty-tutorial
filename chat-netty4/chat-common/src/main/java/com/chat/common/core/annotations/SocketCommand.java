package com.chat.common.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * description: 请求命令注解接口
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-24 18:01
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SocketCommand {

    /**
     * 请求的命令号
     * 
     * @return 命令号
     */
    short cmd();
}
