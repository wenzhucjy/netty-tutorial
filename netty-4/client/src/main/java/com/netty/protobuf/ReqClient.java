package com.netty.protobuf;

import com.netty.serial.protobuf.PersonProbuf;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

/**
 * description:  netty 4 protobuf 客户端示例
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-12-07 14:23
 */
public class ReqClient {

    public void connect(String host,int port)throws Exception{
        // 配置服务端的NIO线程组
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            // Bootstrap 类，是启动NIO服务器的辅助启动类
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch)
                                throws Exception{
                            //
                            ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());

                            ch.pipeline().addLast(new ProtobufDecoder(PersonProbuf.Person.getDefaultInstance()));
                            // 对protobuf协议的的消息头上加上一个长度为32的整形字段，用于标志这个消息的长度
                            ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
                            ch.pipeline().addLast(new ProtobufEncoder());
                            ch.pipeline().addLast(new ReqClientHandler());

                        }
                    });

            // 发起异步连接操作
            ChannelFuture f= b.connect(host,port).sync();

            // 等待客户端链路关闭
            f.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[]args)throws Exception{
        int port = 8080;
        if(args!=null && args.length>0){
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException ex){
                ex.printStackTrace();
            }
        }
        new ReqClient().connect("127.0.0.1",port);
    }
}
