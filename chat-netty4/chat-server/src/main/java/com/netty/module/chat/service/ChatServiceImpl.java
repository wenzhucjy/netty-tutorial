package com.netty.module.chat.service;

import com.chat.common.core.exception.ErrorCodeException;
import com.chat.common.core.model.ResultCode;
import com.chat.common.core.session.SessionManager;
import com.chat.common.module.ModuleId;
import com.chat.common.module.chat.ChatCmd;
import com.chat.common.module.chat.response.ChatResponse;
import com.chat.common.module.chat.response.ChatType;
import com.netty.module.player.dao.PlayerDao;
import com.netty.module.player.dao.entity.Player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.function.Consumer;

/**
 * description: 聊天服务
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-28 17:43
 */
@Component
public class ChatServiceImpl implements ChatService {

    @Autowired
    private PlayerDao playerDao;

    /**
     * 群发消息
     *
     * @param playerId 玩家id
     * @param content 消息内容
     */
    @Override
    public void publicChat(long playerId, String content) {

        // 通过玩家id获取玩家信息
         Player player = this.playerDao.getPlayerById(playerId);
        // if (null == player) {
        // throw new ErrorCodeException(ResultCode.PLAYER_NO_EXIST);
        // }
        // 获取所有在线玩家
        Set<Long> onlinePlayers = SessionManager.getOnlinePlayers();

        // 创建消息对象
        ChatResponse response = new ChatResponse();
        response.setChatType(ChatType.PUBLIC_CHAT);
        response.setSendPlayerId(playerId);
        response.setSendPlayerName(player.getPlayerName());
        response.setMessage(content);

        // 循环发送消息
        onlinePlayers.forEach(new Consumer<Long>() {

            @Override
            public void accept(Long targetPlayerId) {
                SessionManager.sendMessage(targetPlayerId, ModuleId.CHAT, ChatCmd.PUSH_CHAT, response);
            }
        });
    }

    /**
     * 私聊
     *
     * @param playerId 玩家id
     * @param targetPlayerId 目标玩家id
     * @param content 消息内容
     */
    @Override
    public void privateChat(long playerId, long targetPlayerId, String content) {
        // 不能和自己私聊
        if (playerId == targetPlayerId) {
            throw new ErrorCodeException(ResultCode.CAN_CHAT_YOUSELF);
        }
        // 通过玩家id获取玩家信息
         Player player = this.playerDao.getPlayerById(playerId);
        // if (null == player) {
        // throw new ErrorCodeException(ResultCode.PLAYER_NO_EXIST);
        // }

        // 判断目标是否存在
        Player targetPlayer = playerDao.getPlayerById(targetPlayerId);
        if (targetPlayer == null) {
            throw new ErrorCodeException(ResultCode.PLAYER_NO_EXIST);
        }

        // 判断对方是否在线
        if (!SessionManager.isOnlinePlayer(targetPlayerId)) {
            throw new ErrorCodeException(ResultCode.PLAYER_NO_ONLINE);
        }
        // 创建消息对象
        ChatResponse chatResponse = new ChatResponse();
        chatResponse.setChatType(ChatType.PRIVATE_CHAT);
        chatResponse.setSendPlayerId(player.getPlayerId());
        chatResponse.setSendPlayerName(player.getPlayerName());
        chatResponse.setTargetPlayerName(targetPlayer.getPlayerName());
        chatResponse.setMessage(content);

        // 给目标对象发送消息
        SessionManager.sendMessage(targetPlayerId, ModuleId.CHAT, ChatCmd.PUSH_CHAT, chatResponse);
        // 给自己也回一个
        SessionManager.sendMessage(playerId, ModuleId.CHAT, ChatCmd.PUSH_CHAT, chatResponse);
    }
}
