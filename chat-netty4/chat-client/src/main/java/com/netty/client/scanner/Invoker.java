package com.netty.client.scanner;

import java.lang.reflect.Method;

/**
 * description: 命令执行器
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-28 15:23
 */
public class Invoker {

    /**
     * 方法
     */
    private Method method;
    /**
     * 目标对象
     */
    private Object target;

    public static Invoker valueOf(Method method, Object target) {
        Invoker invoker = new Invoker();
        invoker.setMethod(method);
        invoker.setTarget(target);
        return invoker;
    }

    /**
     * 执行
     *
     * @param paramValues Object...
     * @return Object
     */
    public Object invoke(Object... paramValues) {
        try {
            return method.invoke(target, paramValues);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }
}
