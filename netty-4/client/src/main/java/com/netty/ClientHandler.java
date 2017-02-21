package com.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * description: 客户端消息处理
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-24 15:54
 */
public class ClientHandler extends SimpleChannelInboundHandler<String> { // 或者继承 ChannelHandlerAdapter

    // netty5 变更为 messageReceived 方法
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("客户端收到消息:" + msg);
        // *** Client 端 不能执行异步关闭，否则服务器端响应的数据接收不到 ***
         ctx.channel().writeAndFlush(Unpooled.copiedBuffer("Hi Server$_".getBytes()));
    }
}
