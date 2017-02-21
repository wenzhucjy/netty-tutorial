package com.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * description: 客户端实现解决粘包的问题
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-29 16:12
 */
public class LineBasedFrameDecoderClient {

    private static final String CR = System.getProperty("line.separator");

    public void connection(String host, int port) throws InterruptedException {
        // 配置NIO线程组
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap boot = new Bootstrap();
        boot.group(group).channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY,true).handler(new ChannelInitializer<SocketChannel>() {
                 @Override
                 protected void initChannel(SocketChannel socketChannel) throws Exception {
                     socketChannel.pipeline().addLast(new LineBasedFrameDecoder(2048));
                     socketChannel.pipeline().addLast(new StringEncoder(CharsetUtil.UTF_8));
                     socketChannel.pipeline().addLast(new StringDecoder(CharsetUtil.UTF_8));
                     socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024), new DecoderClientHandler());
                 }
             });

        // 发起异步链接操作
        ChannelFuture channelFuture = boot.connect(host, port).sync();

        // 等待客户端链路关闭
        channelFuture.channel().closeFuture().sync();

        group.shutdownGracefully();

    }

    /**
     * 处理响应结果
     */
    private class DecoderClientHandler extends SimpleChannelInboundHandler {


        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            //给服务器发送消息
            //           ByteBuf msg  = Unpooled.copiedBuffer("QUERY TIME ORDER\\n".getBytes());
//            ctx.writeAndFlush(msg);
            //下面的操作会出现TCP粘包的情况
            for (int i = 0; i < 100; i++) {
                ByteBuf msg  = Unpooled.copiedBuffer(("QUERY TIME ORDER"+CR).getBytes());
                ctx.writeAndFlush(msg);
            }

        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            //接受服务器返回的消息
            String content = (String) msg;
            System.out.println("return msg:"+content);
        }

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
            // 接受服务器返回的消息
            String content = (String) msg;
            System.out.println("return msg:" + content);

            // 给服务器发送消息
            // ByteBuf msg = Unpooled.copiedBuffer("QUERY TIME ORDER\\n".getBytes());
            // ctx.writeAndFlush(msg);
            // 下面的操作会出现TCP粘包的情况
            for (int i = 0; i < 100; i++) {
                ByteBuf result = Unpooled.copiedBuffer(("QUERY TIME ORDER" + CR).getBytes());
                ctx.writeAndFlush(result);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new LineBasedFrameDecoderClient().connection("127.0.0.1", 9999);
    }
}
