package com.chat.common.core.session;

import io.netty.channel.Channel;
import io.netty.util.AttributeKey;

/**
 * description: 会话接口实现，采用 netty4
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-25 11:25
 */
public class SessionImpl implements Session {

    /**
     * 绑定对象key
     */
    public static AttributeKey<Object> ATTACHMENT_KEY = AttributeKey.valueOf("ATTACHMENT_KEY");

    /**
     * 实际会话对象
     */
    private Channel                    channel;

    public SessionImpl(Channel channel){
        this.channel = channel;
    }

    @Override
    public Object getAttachment() {
        return channel.attr(ATTACHMENT_KEY).get();
    }

    @Override
    public void setAttachment(Object attachment) {
        channel.attr(ATTACHMENT_KEY).set(attachment);
    }

    @Override
    public void removeAttachment() {
        //channel.attr(ATTACHMENT_KEY).remove();
        channel.attr(ATTACHMENT_KEY).set(null);
    }

    @Override
    public void write(Object message) {
        channel.writeAndFlush(message);
    }

    @Override
    public boolean isConnected() {
        return channel.isActive();
    }

    @Override
    public void close() {
        channel.close();
    }

}
