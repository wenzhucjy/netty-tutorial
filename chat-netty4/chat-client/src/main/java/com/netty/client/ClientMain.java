package com.netty.client;

import com.netty.client.swing.SwingClient;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * description: 客户端启动
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-28 20:47
 */
public class ClientMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        SwingClient swing = applicationContext.getBean(SwingClient.class);
        swing.setVisible(true);
    }
}
