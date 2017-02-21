package com.serial.core;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

import java.nio.ByteOrder;

/**
 * description: buffer 工厂
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-24 16:53
 */
public class BufferFactory {

    public static ByteOrder BYTE_ORDER = ByteOrder.BIG_ENDIAN;

    /**
     * 获取一个buffer
     *
     * @return  ChannelBuffer
     */
    public static ChannelBuffer getBuffer() {
        return ChannelBuffers.dynamicBuffer();
    }

    /**
     * 将数据写入buffer
     * 
     * @param bytes byte[]
     * @return ChannelBuffer
     */
    public static ChannelBuffer getBuffer(byte[] bytes) {
        return ChannelBuffers.copiedBuffer(bytes);
    }

}
