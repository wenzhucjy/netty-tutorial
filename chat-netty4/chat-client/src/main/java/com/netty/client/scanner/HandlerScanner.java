package com.netty.client.scanner;

import com.chat.common.core.annotations.SocketCommand;
import com.chat.common.core.annotations.SocketModule;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * description: handler 扫描器
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-28 17:15
 */
@Component
public class HandlerScanner implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> clazz = bean.getClass();
        // 获取bean的注解接口
        Class<?>[] interfaces = clazz.getInterfaces();

        if (null != interfaces && interfaces.length > 0) {
            for (Class<?> iFace : interfaces) {
                // 判断是否为 handler 接口
                SocketModule socketModule = iFace.getAnnotation(SocketModule.class);
                if (null == socketModule) {
                    continue;
                }
                // 找出模块号和命令方法
                Method[] methods = iFace.getMethods();
                if (null != methods && methods.length > 0) {
                    for (Method method : methods) {
                        SocketCommand socketCommand = method.getAnnotation(SocketCommand.class);

                        if (null == socketCommand) {
                            continue;
                        }
                        // 模块号
                        short module = socketModule.module();
                        // 命令号
                        short cmd = socketCommand.cmd();

                        Invoker invoker = InvokerHolder.getInvoker(module, cmd);
                        if (null == invoker) {
                            InvokerHolder.addInvoke(module, cmd, Invoker.valueOf(method, bean));
                        } else {
                            System.out.println("重复命令:"+"module:"+module +" "+"cmd：" + cmd);
                        }
                    }
                }
            }
        }

        return bean;
    }
}
