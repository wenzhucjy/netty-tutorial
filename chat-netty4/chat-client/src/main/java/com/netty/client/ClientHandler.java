package com.netty.client;

import com.chat.common.core.model.Response;
import com.netty.client.scanner.Invoker;
import com.netty.client.scanner.InvokerHolder;
import com.netty.client.swing.SwingClient;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * description: 消息接收处理类
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-28 20:33
 */
public class ClientHandler extends SimpleChannelInboundHandler<Response> {

    /**
     * 界面
     */
    private SwingClient swingClient;

    public ClientHandler(SwingClient swingClient) {
        this.swingClient = swingClient;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Response response) throws Exception {
        handlerResponse(response);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }


    /**
     * 消息处理
     *
     * @param response {@link Response}
     */
    private void handlerResponse(Response response) {
        //获取命令执行器
        Invoker invoker = InvokerHolder.getInvoker(response.getModule(), response.getCmd());
        if (invoker != null) {
            try {
                invoker.invoke(response.getStateCode(), response.getData());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            //找不到执行器
            System.out.println(String.format("module:%s  cmd:%s 找不到命令执行器", response.getModule(), response.getCmd()));
        }
    }

    /**
     * 断开链接
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        swingClient.getTips().setText("与服务器断开连接~~~");
    }
}
