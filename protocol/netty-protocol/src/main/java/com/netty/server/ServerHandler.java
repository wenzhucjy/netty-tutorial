package com.netty.server;

import com.netty.model.Request;
import com.netty.model.Response;
import com.netty.model.StateCode;
import com.netty.module.fuben.request.FightRequest;
import com.netty.module.fuben.response.FightResponse;

import org.jboss.netty.channel.*;
import org.jboss.netty.handler.timeout.IdleState;
import org.jboss.netty.handler.timeout.IdleStateEvent;

/**
 * description: 消息接收处理类
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-24 14:42
 */
public class ServerHandler extends SimpleChannelHandler {

    /**
     * 接收消息
     */
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {

        Request message = (Request)e.getMessage();

        if(message.getModule() == 1){

            if(message.getCmd() == 1){

                FightRequest fightRequest = new FightRequest();
                fightRequest.readFromBytes(message.getData());

                System.out.println("fubenId:" +fightRequest.getFubenId() + "   " + "count:" + fightRequest.getCount());

                //回写数据
                FightResponse fightResponse = new FightResponse();
                fightResponse.setGold(9999);

                Response response = new Response();
                response.setModule((short) 1);
                response.setCmd((short) 1);
                response.setStateCode(StateCode.SUCCESS);
                response.setData(fightResponse.getBytes());
                ctx.getChannel().write(response);
            }else if(message.getCmd() == 2){

            }

        }else if (message.getModule() == 1){


        }
    }

    @Override
    public void handleUpstream(final ChannelHandlerContext ctx, ChannelEvent e) throws Exception {
        if (e instanceof IdleStateEvent) {
            if(((IdleStateEvent)e).getState() == IdleState.ALL_IDLE){
                System.out.println("踢玩家下线");
                //关闭会话,踢玩家下线
                ChannelFuture write = ctx.getChannel().write("time out, you will close");
                write.addListener(new ChannelFutureListener() {

                    @Override
                    public void operationComplete(ChannelFuture future) throws Exception {
                        ctx.getChannel().close();
                    }
                });
            }
        } else {
            super.handleUpstream(ctx, e);
        }
    }

    /**
     * 捕获异常
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        System.out.println("exceptionCaught");
        super.exceptionCaught(ctx, e);
    }

    /**
     * 新连接
     */
    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelConnected");
        super.channelConnected(ctx, e);
    }

    /**
     * 必须是链接已经建立，关闭通道的时候才会触发
     */
    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelDisconnected");
        super.channelDisconnected(ctx, e);
    }

    /**
     * channel关闭的时候触发
     */
    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelClosed");
        super.channelClosed(ctx, e);
    }
}
