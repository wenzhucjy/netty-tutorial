package com.netty.hearbeat;

import com.netty.serial.marshalling.MarshallingCodeCFactory;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * description: 心跳检测
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-12-02 16:16
 */
public class HeartBeatClient {

    public static void main(String[] args) {

        EventLoopGroup group = new NioEventLoopGroup();
        // 服务类
        Bootstrap b = new Bootstrap();

        try {
            // 设置线程池
            b.group(group);
            // 设置socket工厂、
            b.channel(NioSocketChannel.class);

            // 设置管道
            b.handler(new ChannelInitializer<Channel>() {

                @Override
                protected void initChannel(Channel ch) throws Exception {
                    ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
                    ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
                    ch.pipeline().addLast(new ClientHeartBeatHandler());
                }
            });

            ChannelFuture cf = b.connect("127.0.0.1", 8765).sync();
            cf.channel().closeFuture().sync();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}
