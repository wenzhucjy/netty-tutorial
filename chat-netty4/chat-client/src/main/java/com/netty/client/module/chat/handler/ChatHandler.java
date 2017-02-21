package com.netty.client.module.chat.handler;

import com.chat.common.core.annotations.SocketCommand;
import com.chat.common.core.annotations.SocketModule;
import com.chat.common.core.model.ResultCode;
import com.chat.common.module.ModuleId;
import com.chat.common.module.chat.ChatCmd;
import com.chat.common.module.chat.response.ChatResponse;

/**
 * description: 聊天
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-28 20:07
 */
@SocketModule(module = ModuleId.CHAT)
public interface ChatHandler {

    /**
     * 发送广播消息回包
     * 
     * @param resultCode {@link ResultCode}
     * @param data {@link ChatResponse}
     */
    @SocketCommand(cmd = ChatCmd.PUBLIC_CHAT)
    public void publicChat(int resultCode, byte[] data);

    /**
     * 发送私人消息回包
     * 
     * @param resultCode {@link ResultCode}
     * @param data {@link ChatResponse}
     */
    @SocketCommand(cmd = ChatCmd.PRIVATE_CHAT)
    public void privateChat(int resultCode, byte[] data);

    /**
     * 收到推送聊天信息
     * 
     * @param resultCode {@link ResultCode}
     * @param data {@link ChatResponse}
     */
    @SocketCommand(cmd = ChatCmd.PUSH_CHAT)
    public void receiveMessage(int resultCode, byte[] data);
}
