package com.netty.pool;

import java.nio.channels.ServerSocketChannel;

/**
 * description: Boss接口
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-24 14:49
 */
public interface Boss {

    /**
     * 加入一个新的ServerSocket
     * 
     * @param serverChannel ServerSocketChannel
     */
    public void registerAcceptChannelTask(ServerSocketChannel serverChannel);
}
