package com.chat.common.core.codec;

import com.chat.common.core.model.Request;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * description: 消息数据包编码器
 *
 * <pre>
 * 数据包格式
 * +——----——+——-----——+——----——+——----——+——-----——+
 * |  包头	|  模块号 |  命令号 |   长度|   数据    |
 * +——----——+——-----——+——----——+——----——+——-----——+
 * </pre>
 *
 * 包头4字节 模块号2字节 命令号2字节 长度4字节(数据部分占有字节数量)
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-24 18:12
 */
public class RequestEncoder extends MessageToByteEncoder<Request> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Request message, ByteBuf buffer) throws Exception {

        //包头
        buffer.writeInt(ConstantValue.HEADER_FLAG);
        //module
        buffer.writeShort(message.getModule());
        //cmd
        buffer.writeShort(message.getCmd());
        //长度
        int len = message.getData()==null? 0 : message.getData().length;
        if(len <= 0){
            buffer.writeInt(len);
        }else{
            buffer.writeInt(len);
            buffer.writeBytes(message.getData());
        }
    }
}
