package com.netty.client.module.player.handler;

import com.chat.common.core.model.ResultCode;
import com.chat.common.module.player.response.PlayerResponse;
import com.netty.client.swing.ResultCodeTip;
import com.netty.client.swing.SwingClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * description: 玩家模块
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-28 20:45
 */
@Component
public class PlayerHandlerImpl implements PlayerHandler {

    @Autowired
    private SwingClient   swingClient;

    @Autowired
    private ResultCodeTip resultCodeTip;

    @Override
    public void registerAndLogin(int resultCode, byte[] data) {
        if (resultCode == ResultCode.SUCCESS) {
            PlayerResponse playerResponse = new PlayerResponse();
            playerResponse.readFromBytes(data);

            swingClient.setPlayerResponse(playerResponse);
            swingClient.getTips().setText("注册登录成功");
        } else {
            swingClient.getTips().setText(resultCodeTip.getTipContent(resultCode));
        }
    }

    @Override
    public void login(int resultCode, byte[] data) {
        if (resultCode == ResultCode.SUCCESS) {
            PlayerResponse playerResponse = new PlayerResponse();
            playerResponse.readFromBytes(data);

            swingClient.setPlayerResponse(playerResponse);
            swingClient.getTips().setText("登录成功");
        } else {
            swingClient.getTips().setText(resultCodeTip.getTipContent(resultCode));
        }
    }
}
