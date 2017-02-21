package com.netty.pool;

import java.nio.channels.SocketChannel;

/**
 * description: Worker接口
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-24 14:50
 */
public interface Worker {

    /**
     * 加入一个新的客户端会话
     * 
     * @param channel SocketChannel
     */
    public void registerNewChannelTask(SocketChannel channel);

}
