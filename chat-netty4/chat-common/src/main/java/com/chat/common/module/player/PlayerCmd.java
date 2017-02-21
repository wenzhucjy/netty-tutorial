package com.chat.common.module.player;

/**
 * description: 玩家模块
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-24 18:43
 */
public interface PlayerCmd {

    /**
     * 创建并登录帐号
     */
    short REGISTER_AND_LOGIN = 1;

    /**
     * 登录帐号
     */
    short LOGIN              = 2;
}
