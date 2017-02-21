package com.chat.common.core.session;

import com.chat.common.core.model.Response;
import com.chat.common.core.serial.Serializer;
import com.google.protobuf.GeneratedMessage;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * description: 会话管理者
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-25 11:29
 */
public class SessionManager {

    /**
     * 在线会话
     */
    private static final ConcurrentHashMap<Long, Session> onlineSessions = new ConcurrentHashMap<>();

    /**
     * 加入会话
     * 
     * @param playerId 玩家id
     * @param session 会话
     * @return boolean
     */
    public static boolean putSession(long playerId, Session session) {
        return !onlineSessions.containsKey(playerId) && onlineSessions.putIfAbsent(playerId, session) == null;
    }

    /**
     * 移除在线会话
     * 
     * @param playerId 玩家id
     */
    public static Session removeSession(long playerId) {
        return onlineSessions.remove(playerId);
    }

    /**
     * 发送消息[自定义协议]
     *
     * @param playerId 玩家id
     * @param module 模块号
     * @param cmd 命令号
     * @param message 消息
     * @param <T> 继承 Serializer 的对象
     */
    public static <T extends Serializer> void sendMessage(long playerId, short module, short cmd, T message) {
        Session session = onlineSessions.get(playerId);
        if (session != null && session.isConnected()) {
            Response response = new Response(module, cmd, message.getBytes());
            session.write(response);
        }
    }

    /**
     * 发送消息[protoBuf协议]
     *
     * @param playerId 玩家id
     * @param module 模块号
     * @param cmd 命令号
     * @param message 消息
     * @param <T> 继承 GeneratedMessage 的对象
     */
    public static <T extends GeneratedMessage> void sendMessage(long playerId, short module, short cmd, T message) {
        Session session = onlineSessions.get(playerId);
        if (session != null && session.isConnected()) {
            Response response = new Response(module, cmd, message.toByteArray());
            session.write(response);
        }
    }

    /**
     * 是否在线
     * 
     * @param playerId 玩家id
     * @return boolean
     */
    public static boolean isOnlinePlayer(long playerId) {
        return onlineSessions.containsKey(playerId);
    }

    /**
     * 获取所有在线玩家
     * 
     * @return Set<Long>
     */
    public static Set<Long> getOnlinePlayers() {
        return Collections.unmodifiableSet(onlineSessions.keySet());
    }
}
