package com.chat.common.module.chat.request;

import com.chat.common.core.serial.Serializer;

/**
 * description: 私聊
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-24 18:38
 */
public class PrivateChatRequest extends Serializer {

    /**
     * 要向哪个会话发消息
     */
    private long   targetPlayerId;

    /**
     * 内容
     */
    private String context;

    public long getTargetPlayerId() {
        return targetPlayerId;
    }

    public void setTargetPlayerId(long targetPlayerId) {
        this.targetPlayerId = targetPlayerId;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @Override
    protected void read() {
        this.targetPlayerId = readLong();
        this.context = readString();
    }

    @Override
    protected void write() {
        writeLong(targetPlayerId);
        writeString(context);
    }
}
