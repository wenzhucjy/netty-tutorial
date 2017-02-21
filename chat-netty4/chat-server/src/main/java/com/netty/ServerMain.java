package com.netty;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * description: 服务端测试类
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-28 19:04
 */
public class ServerMain {

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        Server server = applicationContext.getBean(Server.class);

        server.start();
    }

}
