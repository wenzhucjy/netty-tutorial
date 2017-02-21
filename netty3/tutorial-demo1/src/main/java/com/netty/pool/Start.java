package com.netty.pool;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * description: 启动类
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-24 14:59
 */
public class Start {

    public static void main(String[] args) {

        // 初始化线程
        NioSelectorRunnablePool nioSelectorRunnablePool = new NioSelectorRunnablePool(Executors.newCachedThreadPool(),
                                                                                      Executors.newCachedThreadPool());

        // 获取服务类
        ServerBootstrap bootstrap = new ServerBootstrap(nioSelectorRunnablePool);

        // 绑定端口
        bootstrap.bind(new InetSocketAddress(10101));

        System.out.println("start");
    }

}
