package com.netty.module.player.handler;

import com.chat.common.core.annotations.SocketCommand;
import com.chat.common.core.annotations.SocketModule;
import com.chat.common.core.model.Result;
import com.chat.common.core.session.Session;
import com.chat.common.module.ModuleId;
import com.chat.common.module.player.PlayerCmd;
import com.chat.common.module.player.request.LoginRequest;
import com.chat.common.module.player.request.RegisterRequest;
import com.chat.common.module.player.response.PlayerResponse;

/**
 * description: 玩家模块
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-28 16:25
 */
@SocketModule(module = ModuleId.PLAYER)
public interface PlayerHandler {

    /**
     * 创建并登录帐号
     * 
     * @param session {@link Session}
     * @param data {@link RegisterRequest}
     * @return Result<PlayerResponse>
     */
    @SocketCommand(cmd = PlayerCmd.REGISTER_AND_LOGIN)
    public Result<PlayerResponse> registerAndLogin(Session session, byte[] data);

    /**
     * 登录帐号
     * 
     * @param session {@link Session}
     * @param data {@link LoginRequest}
     * @return Result<PlayerResponse>
     */
    @SocketCommand(cmd = PlayerCmd.LOGIN)
    public Result<PlayerResponse> login(Session session, byte[] data);

}
