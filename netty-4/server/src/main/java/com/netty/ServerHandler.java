package com.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * description:
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-24 15:40
 */
public class ServerHandler extends SimpleChannelInboundHandler<String> {

    // netty5 变更为 messageReceived 方法
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(msg);

        // 给客户端响应
        // ByteBuf result = Unpooled.copiedBuffer(msg.getBytes());
        // ctx.write(result);
        // ctx.writeAndFlush(result);

        //ctx.channel().writeAndFlush("hi");
        // Client写入数据结束后，异步关闭只能在Server端处理
        ctx.writeAndFlush(Unpooled.copiedBuffer("hi$_".getBytes()));
        //.addListener(ChannelFutureListener.CLOSE)
    }

    @Override
    public void userEventTriggered(final ChannelHandlerContext ctx, Object evt) throws Exception {

        if (evt instanceof IdleStateEvent) {

            IdleStateEvent event = (IdleStateEvent) evt;

            if (event.state().equals(IdleState.READER_IDLE)) {
                //未进行读操作
                System.out.println("READER_IDLE");
                // 超时关闭channel
                ctx.close();

            } else if (event.state().equals(IdleState.WRITER_IDLE)) {


            } else if (event.state().equals(IdleState.ALL_IDLE)) {
                //未进行读写
                System.out.println("ALL_IDLE");
                // 发送心跳消息

                // 清除超时会话
                ChannelFuture writeAndFlush = ctx.writeAndFlush("you will close");
                writeAndFlush.addListener(new ChannelFutureListener() {

                    @Override
                    public void operationComplete(ChannelFuture future) throws Exception {
                        ctx.channel().close();
                    }
                });
            }
        }else {
            super.userEventTriggered(ctx, evt);
        }

    }

    /**
     * 新客户端接入，channel处于活跃状态(已经连接到了远程主机).现在可以接受/发送数据了
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive");
    }

    /**
     * 客户端断开，channel处于非活跃状态，没有连接到远程主机
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelInactive");
    }

    /**
     * 异常
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // 当读操作完成后不刷新的话,客户端是收不到响应结果的
        ctx.flush();
    }
}
