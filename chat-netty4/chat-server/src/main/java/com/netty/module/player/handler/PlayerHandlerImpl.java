package com.netty.module.player.handler;

import com.chat.common.core.exception.ErrorCodeException;
import com.chat.common.core.model.Result;
import com.chat.common.core.model.ResultCode;
import com.chat.common.core.session.Session;
import com.chat.common.module.player.request.LoginRequest;
import com.chat.common.module.player.request.RegisterRequest;
import com.chat.common.module.player.response.PlayerResponse;
import com.netty.module.player.service.PlayerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * description: 玩家模块
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-28 16:33
 */
@Component
public class PlayerHandlerImpl implements PlayerHandler {

    @Autowired
    private PlayerService playerService;

    /**
     * 创建并登录帐号
     *
     * @param session {@link Session}
     * @param data {@link RegisterRequest}
     * @return Result<PlayerResponse>
     */
    @Override
    public Result<PlayerResponse> registerAndLogin(Session session, byte[] data) {
        try {
            // 反序列化
            RegisterRequest registerRequest = new RegisterRequest();
            registerRequest.readFromBytes(data);
            // 判断参数是否为空
            String passWord = registerRequest.getPassWord();
            String playerName = registerRequest.getPlayerName();
            if (StringUtils.isEmpty(playerName) || StringUtils.isEmpty(passWord)) {
                return Result.ERROR(ResultCode.PLAYERNAME_NULL);
            }
            // 执行业务
            PlayerResponse response = playerService.registerAndLogin(session, playerName, passWord);
            return Result.SUCCESS(response);
        } catch (ErrorCodeException e) {
            return Result.ERROR(e.getErrorCode());
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.UNKOWN_EXCEPTION);
        }
    }

    /**
     * 登录帐号
     *
     * @param session {@link Session}
     * @param data {@link LoginRequest}
     * @return Result<PlayerResponse>
     */
    @Override
    public Result<PlayerResponse> login(Session session, byte[] data) {
        try {
            // 反序列化
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.readFromBytes(data);
            // 参数判空
            String playerName = loginRequest.getPlayerName();
            String passWord = loginRequest.getPassWord();
            if (StringUtils.isEmpty(playerName) || StringUtils.isEmpty(passWord)) {
                return Result.ERROR(ResultCode.PLAYERNAME_NULL);
            }
            // 执行业务
            PlayerResponse response = playerService.login(session, playerName, passWord);
            return Result.SUCCESS(response);
        } catch (ErrorCodeException e) {
            return Result.ERROR(e.getErrorCode());
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.UNKOWN_EXCEPTION);
        }
    }
}
