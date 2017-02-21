package com.netty;

import com.chat.common.core.codec.RequestDecoder;
import com.chat.common.core.codec.ResponseEncoder;

import io.netty.channel.ChannelFuture;
import org.springframework.stereotype.Component;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * description: 服务端
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-25 10:50
 */
@Component
public class Server {

    /**
     * 启动
     */
    public void start() {
        // 服务类
        ServerBootstrap bootstrap = new ServerBootstrap();

        // boss worker线程池
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        // 有序的请求处理线程池，消息串行化
        final EventLoopGroup busyGroup = new NioEventLoopGroup();
        try {
            // 设置循环线程组事例
            bootstrap.group(bossGroup, workerGroup);

            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {

                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new RequestDecoder());
                    ch.pipeline().addLast(new ResponseEncoder());
                    // ch.pipeline().addLast(new ServerHandler());
                    ch.pipeline().addLast(busyGroup, new ServerHandler());// ServerHandler 与 RequestDecoder 不同线程
                }
            });
            // 设定套接字参数
            // backlog表示为此套接字排队的最大连接数
            bootstrap.option(ChannelOption.SO_BACKLOG, 2048);// 链接缓冲池队列大小
            bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);// 维持链接的活跃，清除死链接
            bootstrap.childOption(ChannelOption.TCP_NODELAY, true);// 关闭延迟发送

            // 绑定端口
            ChannelFuture future = bootstrap.bind(12121).sync();

            System.out.println("start!!!");
            // 等待服务器端口监听关闭
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 是否线程资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
