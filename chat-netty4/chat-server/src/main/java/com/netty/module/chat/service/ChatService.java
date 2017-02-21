package com.netty.module.chat.service;

/**
 * description: 聊天服务
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-28 17:42
 */
public interface ChatService {

    /**
     * 群发消息
     * 
     * @param playerId 玩家id
     * @param content 消息内容
     */
    public void publicChat(long playerId, String content);

    /**
     * 私聊
     * 
     * @param playerId 玩家id
     * @param targetPlayerId 目标玩家id
     * @param content 消息内容
     */
    public void privateChat(long playerId, long targetPlayerId, String content);

}
