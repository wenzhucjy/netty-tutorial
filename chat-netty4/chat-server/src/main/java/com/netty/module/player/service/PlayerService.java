package com.netty.module.player.service;

import com.chat.common.core.session.Session;
import com.chat.common.module.player.response.PlayerResponse;

/**
 * description: 玩家服务
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-28 16:47
 */
public interface PlayerService {

    /**
     * 登录注册用户
     * 
     * @param session {@link Session}
     * @param playerName 玩家名称
     * @param passWord 玩家密码
     * @return PlayerResponse
     */
    public PlayerResponse registerAndLogin(Session session, String playerName, String passWord);

    /**
     * 登录
     * 
     * @param session {@link Session}
     * @param playerName 玩家名称
     * @param passWord 玩家密码
     * @return PlayerResponse
     */
    public PlayerResponse login(Session session, String playerName, String passWord);

}
