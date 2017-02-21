package com.netty.client.scanner;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * description: 命令执行器调度者
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-28 15:50
 */
public class InvokerHolder {

    /**
     * 命令调用器
     */
    private static Map<Short, Map<Short, Invoker>> invokers = new HashMap<>();

    /**
     * 添加命令调用
     *
     * @param module 模块号
     * @param cmd 命令号
     * @param invoker 命令执行器
     */
    public static void addInvoke(short module, short cmd, Invoker invoker) {
        // 通过模块号获取命令执行器
        Map<Short, Invoker> map = invokers.get(module);
        if (!Optional.ofNullable(map).isPresent()) {
            map = new HashMap<>();
            invokers.put(module, map);
        }
        map.put(cmd, invoker);
    }

    /**
     * 获取命令调用
     *
     * @param module 模块号
     * @param cmd 命令号
     * @return Invoker
     */
    public static Invoker getInvoker(short module, short cmd) {
        Map<Short, Invoker> map = invokers.get(module);
        return Optional.ofNullable(map).isPresent() ? map.get(cmd) : null;
    }

}
