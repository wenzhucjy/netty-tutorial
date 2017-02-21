package com.chat.common.core.codec;

import com.chat.common.core.model.Response;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * description: 响应数据包编码器
 *
 * <pre>
 * 数据包格式
 * +——----——+——-----——+——----——+——----——+——----——+——----——+
 * |  包头	|  模块号 |  命令号 | 状态码 |  长度  |   数据  |
 * +——----——+——-----——+——----——+——----——+——----——+——----——+
 * </pre>
 * 
 * 包头4字节 模块号2字节 命令号2字节 状态码4字节 长度4字节(数据部分占有字节数量)
 * 
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-24 18:34
 */
public class ResponseEncoder extends MessageToByteEncoder<Response> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Response response, ByteBuf buffer) throws Exception {

        System.out.println("返回请求:" + "module:" +response.getModule() +" cmd:" + response.getCmd() + " resultCode:" + response.getStateCode());

        //包头
        buffer.writeInt(ConstantValue.HEADER_FLAG);
        //module和cmd
        buffer.writeShort(response.getModule());
        buffer.writeShort(response.getCmd());
        //结果码
        buffer.writeInt(response.getStateCode());
        //长度
        int len = response.getData()==null? 0 : response.getData().length;
        if(len <= 0){
            buffer.writeInt(len);
        }else{
            buffer.writeInt(len);
            buffer.writeBytes(response.getData());
        }
    }
}
