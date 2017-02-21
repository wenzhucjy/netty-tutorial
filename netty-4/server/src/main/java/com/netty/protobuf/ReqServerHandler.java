package com.netty.protobuf;

import com.netty.serial.protobuf.PersonProbuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * description: ReqServerHandler
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-12-07 14:27
 */
public class ReqServerHandler extends SimpleChannelInboundHandler {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        channelRead(ctx, msg);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        PersonProbuf.Person people = (PersonProbuf.Person) msg;
        if ("Orange".equalsIgnoreCase(people.getName())) {
            System.out.println("accept client people:[" + people.toString() + "]");
            ctx.writeAndFlush(resp(people.getId()));
        }
    }

    private PersonProbuf.Person resp(long peopleID) {
        PersonProbuf.Person.Builder builder = PersonProbuf.Person.newBuilder();
        builder.setId(peopleID);
        builder.setName("karl");
        builder.setSex("boy");
        builder.setTel("110");
        return builder.build();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
