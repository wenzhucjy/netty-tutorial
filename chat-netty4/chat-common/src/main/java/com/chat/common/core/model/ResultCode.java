package com.chat.common.core.model;

/**
 * description: 响应状态码
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-24 18:24
 */
public interface ResultCode {

    /**
     * 成功
     */
    int SUCCESS          = 0;

    /**
     * 找不到命令
     */
    int NO_INVOKER       = 1;

    /**
     * 参数异常
     */
    int AGRUMENT_ERROR   = 2;

    /**
     * 未知异常
     */
    int UNKOWN_EXCEPTION = 3;

    /**
     * 玩家名或密码不能为空
     */
    int PLAYERNAME_NULL  = 4;

    /**
     * 玩家名已使用
     */
    int PLAYER_EXIST     = 5;

    /**
     * 玩家不存在
     */
    int PLAYER_NO_EXIST  = 6;

    /**
     * 密码错误
     */
    int PASSWARD_ERROR   = 7;

    /**
     * 您已登录
     */
    int HAS_LOGIN        = 8;

    /**
     * 登录失败
     */
    int LOGIN_FAIL       = 9;

    /**
     * 玩家不在线
     */
    int PLAYER_NO_ONLINE = 10;

    /**
     * 请先登录
     */
    int LOGIN_PLEASE     = 11;

    /**
     * 不能私聊自己
     */
    int CAN_CHAT_YOUSELF = 12;
}
