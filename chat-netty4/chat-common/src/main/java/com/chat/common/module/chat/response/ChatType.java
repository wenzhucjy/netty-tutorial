package com.chat.common.module.chat.response;

/**
 * description: 消息类型
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-24 18:40
 */
public interface ChatType {

    /**
     * 广播消息
     */
    public byte PUBLIC_CHAT  = 0;

    /**
     * 私聊消息
     */
    public byte PRIVATE_CHAT = 1;
}
