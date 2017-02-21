package com.netty.client.module.player.handler;

import com.chat.common.core.annotations.SocketCommand;
import com.chat.common.core.annotations.SocketModule;
import com.chat.common.core.model.ResultCode;
import com.chat.common.module.ModuleId;
import com.chat.common.module.player.PlayerCmd;

/**
 * description: 玩家模块
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-28 20:44
 */
@SocketModule(module = ModuleId.PLAYER)
public interface PlayerHandler {

    /**
     * 创建并登录帐号
     * 
     * @param resultCode {@link ResultCode}
     * @param data  byte[]
     */
    @SocketCommand(cmd = PlayerCmd.REGISTER_AND_LOGIN)
    public void registerAndLogin(int resultCode, byte[] data);

    /**
     * 创建并登录帐号
     * 
     * @param resultCode {@link ResultCode}
     * @param data  byte[]
     */
    @SocketCommand(cmd = PlayerCmd.LOGIN)
    public void login(int resultCode, byte[] data);
}
