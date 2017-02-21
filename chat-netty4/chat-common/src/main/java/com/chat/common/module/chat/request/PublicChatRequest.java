package com.chat.common.module.chat.request;

import com.chat.common.core.serial.Serializer;

/**
 * description: 广播消息
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-24 18:39
 */
public class PublicChatRequest extends Serializer {

    /**
     * 内容
     */
    private String context;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @Override
    protected void read() {
        this.context = readString();
    }

    @Override
    protected void write() {
        writeString(context);
    }
}
