package com.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * description: Netty 4 客户端
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-24 15:51
 */
public class Client {

    public static void main(String[] args) {

        // 服务类
        Bootstrap bootstrap = new Bootstrap();

        // worker
        EventLoopGroup worker = new NioEventLoopGroup();

        try {
            // 设置线程池
            bootstrap.group(worker);

            // 设置socket工厂、
            bootstrap.channel(NioSocketChannel.class);

            // 设置管道
            bootstrap.handler(new ChannelInitializer<Channel>() {

                @Override
                protected void initChannel(Channel ch) throws Exception {
                    ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, Unpooled.copiedBuffer("$_".getBytes())));
                    ch.pipeline().addLast(new StringDecoder());
                    ch.pipeline().addLast(new StringEncoder());
                    ch.pipeline().addLast(new ClientHandler());
                }
            });
            // 发起异步连接操作
            ChannelFuture connect = bootstrap.connect("127.0.0.1", 10101).sync();

            ChannelFuture future = connect.channel()
                    .writeAndFlush(Unpooled.copiedBuffer("Hi Server$_".getBytes()));

            if (future.isSuccess()) {
                System.out.println("connect server  成功---------");
            }

            // BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            // while (true) {
            // System.out.println("请输入：");
            // String msg = bufferedReader.readLine();
            // connect.channel().writeAndFlush(msg);
            //
            // }

            // 等待客户端链路关闭
            //connect.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            worker.shutdownGracefully();
        }
    }
}
