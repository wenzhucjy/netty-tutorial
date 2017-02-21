package com.chat.common.core.session;

/**
 * description: 会话接口
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-25 11:23
 */
public interface Session {

    /**
     * 会话绑定对象
     * 
     * @return Object
     */
    Object getAttachment();

    /**
     * 设置绑定对象
     */
    void setAttachment(Object attachment);

    /**
     * 移除绑定对象
     */
    void removeAttachment();

    /**
     * 向会话中写入消息
     * 
     * @param message Object
     */
    void write(Object message);

    /**
     * 判断会话是否在连接中
     * 
     * @return boolean
     */
    boolean isConnected();

    /**
     * 关闭
     */
    void close();
}
