package com.netty.module.chat.handler;

import com.chat.common.core.annotations.SocketCommand;
import com.chat.common.core.annotations.SocketModule;
import com.chat.common.core.model.Result;
import com.chat.common.module.ModuleId;
import com.chat.common.module.chat.ChatCmd;
import com.chat.common.module.chat.request.PrivateChatRequest;
import com.chat.common.module.chat.request.PublicChatRequest;

/**
 * description: 聊天
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-28 17:55
 */
@SocketModule(module = ModuleId.CHAT)
public interface ChatHandler {

    /**
     * 广播消息
     * 
     * @param playerId 玩家id
     * @param data {@link PublicChatRequest}
     * @return {@link Result}
     */
    @SocketCommand(cmd = ChatCmd.PUBLIC_CHAT)
    public Result<?> publicChat(long playerId, byte[] data);

    /**
     * 私人消息
     * 
     * @param playerId 玩家id
     * @param data {@link PrivateChatRequest}
     * @return {@link Result}
     */
    @SocketCommand(cmd = ChatCmd.PRIVATE_CHAT)
    public Result<?> privateChat(long playerId, byte[] data);
}
