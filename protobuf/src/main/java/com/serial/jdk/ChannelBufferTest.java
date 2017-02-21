package com.serial.jdk;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

import java.util.Arrays;

/**
 * description: netty 的 ChannelBuffer 序列化测试
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-24 16:49
 */
public class ChannelBufferTest {

    public static void main(String[] args) {

        ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
        buffer.writeInt(101);
        buffer.writeDouble(80.1);

        byte[] bytes = new byte[buffer.writerIndex()];
        buffer.readBytes(bytes);

        System.out.println(Arrays.toString(bytes));

        // ================================================
        ChannelBuffer wrappedBuffer = ChannelBuffers.wrappedBuffer(bytes);
        System.out.println(wrappedBuffer.readInt());
        System.out.println(wrappedBuffer.readDouble());

    }
}
