package com.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * description: netty4服务端
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-24 15:25
 */
public class Netty4Server {

    public static void main(String[] args) {

        // ServerBootstrap 类，是启动NIO服务器的辅助启动类
        ServerBootstrap bootstrap = new ServerBootstrap();

        // boss 和 worker
        EventLoopGroup boss = new NioEventLoopGroup(); // 用于处理服务器端接收客户端连接
        EventLoopGroup worker = new NioEventLoopGroup(); // 用于网络事件的读写处理
        try {

            // 设置线程池
            bootstrap.group(boss, worker);
            // 设置socket工厂
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.childHandler(new ChannelInitializer<Channel>() {

                @Override
                protected void initChannel(Channel ch) throws Exception {
                    // 心跳检测设置
                    // IdleStateHandler 用来检测客户端的读取超时的，第一个参数是指定读操作空闲秒数，第二个参数是指定写操作的空闲秒数，第三个参数是指定读写空闲秒数
                    // ch.pipeline().addLast(new IdleStateHandler(5, 5, 10));
                    //ch.pipeline().addLast("ping", new IdleStateHandler(25, 15, 10, TimeUnit.SECONDS));
                    // 特定的分隔符
                    ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, Unpooled.copiedBuffer("$_".getBytes())));
                    ch.pipeline().addLast(new StringDecoder());
                    ch.pipeline().addLast(new StringEncoder());
                    ch.pipeline().addLast(new ServerHandler());
                }
            });

            // 设置参数，TCP参数
            bootstrap.option(ChannelOption.SO_BACKLOG, 2048);// 链接缓冲池的大小
            bootstrap.option(ChannelOption.SO_RCVBUF, 32 * 1024);// 设置接收缓冲器大小
            bootstrap.option(ChannelOption.SO_SNDBUF, 32 * 1024);// 设置发送缓冲大小
            // 会周期性地探测peer是否还存活着，清除死连接，默认true
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            // 通过NoDelay禁用 Nagle,使消息立即发出去，不用等待到一定的数据量才发出去，关闭延迟发送
            bootstrap.option(ChannelOption.TCP_NODELAY, true);
            bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000);

            // 绑定端口,同步等待成功
            ChannelFuture future = bootstrap.bind(10101).addListener(new GenericFutureListener<Future<? super Void>>() {

                @Override
                public void operationComplete(Future<? super Void> future) throws Exception {
                    if (future.isSuccess()) {
                        System.out.println("[Server] Started Successed,  waiting for client connect...");
                    } else {
                        System.out.println("[Server] Started Failed");
                    }
                }
            });
            System.out.println("start");
            // 等待服务端监听端口关闭
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            System.out.println("e," + e.getMessage());
        } finally {
            // 释放线程池资源
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

}
