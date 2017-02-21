package com.netty.server;

import com.netty.codc.RequestDecoder;
import com.netty.codc.ResponseEncoder;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.timeout.IdleStateHandler;
import org.jboss.netty.util.HashedWheelTimer;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * description: Netty 3 服务端
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-24 12:16
 */
public class Server {

    public static void main(String[] args) {
        // 服务类
        ServerBootstrap bootstrap = new ServerBootstrap();

        // boss线程监听端口，worker线程负责数据读写
        ExecutorService boss = Executors.newCachedThreadPool();
        ExecutorService worker = Executors.newCachedThreadPool();

        // 设置 NioSocket 工厂
        bootstrap.setFactory(new NioServerSocketChannelFactory(boss, worker));

        // 心跳时间设置
        final HashedWheelTimer hashedWheelTimer = new HashedWheelTimer();
        // 设置管道的工厂
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {

            @Override
            public ChannelPipeline getPipeline() throws Exception {

                ChannelPipeline pipeline = Channels.pipeline();
                // 读闲置时间，写闲置时间，读写闲置时间
                pipeline.addLast("idle", new IdleStateHandler(hashedWheelTimer, 5, 5, 10));
                pipeline.addLast("decoder", new RequestDecoder());
                pipeline.addLast("encoder", new ResponseEncoder());
                pipeline.addLast("serverHandler", new ServerHandler());
                return pipeline;
            }
        });
        // TCP相关设置
        bootstrap.setOption("backlog", 1024);
        bootstrap.setOption("tcpNoDelay", true);
        bootstrap.setOption("keepAlive", true);
        bootstrap.bind(new InetSocketAddress(10101));

        System.out.println("start!!!");

    }

}
