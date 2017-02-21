package com.chat.common.module.chat;

/**
 * description: 聊天模块
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-24 18:41
 */
public interface ChatCmd {

    /**
     * 广播消息
     */
    short PUBLIC_CHAT  = 1;

    /**
     * 私人消息
     */
    short PRIVATE_CHAT = 2;

    /**
     * 消息推送命令
     */
    short PUSH_CHAT    = 101;
}
