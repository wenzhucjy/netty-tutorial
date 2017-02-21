package com.chat.common.core.serial;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocator;

import java.nio.ByteOrder;


/**
 * description: buffer 工厂类
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-24 18:10
 */
public class BufferFactory {

    public static ByteOrder BYTE_ORDER   = ByteOrder.BIG_ENDIAN;

    private static ByteBufAllocator bufAllocator = PooledByteBufAllocator.DEFAULT;

    /**
     * 获取一个buffer
     *
     * @return
     */
    public static ByteBuf getBuffer() {
        ByteBuf buffer = bufAllocator.heapBuffer();
        buffer = buffer.order(BYTE_ORDER);
        return buffer;
    }

    /**
     * 将数据写入buffer
     * 
     * @param bytes byte[]
     * @return  ByteBuf
     */
    public static ByteBuf getBuffer(byte[] bytes) {
        ByteBuf buffer = bufAllocator.heapBuffer();
        buffer = buffer.order(BYTE_ORDER);
        buffer.writeBytes(bytes);
        return buffer;
    }

}
