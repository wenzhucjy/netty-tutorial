package com.netty.module.player.service;

import com.chat.common.core.exception.ErrorCodeException;
import com.chat.common.core.model.ResultCode;
import com.chat.common.core.session.Session;
import com.chat.common.core.session.SessionManager;
import com.chat.common.module.player.response.PlayerResponse;
import com.netty.module.player.dao.PlayerDao;
import com.netty.module.player.dao.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * description: 玩家服务
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-28 16:49
 */
@Component
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerDao playerDao;

    /**
     * 登录注册用户
     *
     * @param session {@link Session}
     * @param playerName 玩家名称
     * @param passWord 玩家密码
     * @return PlayerResponse
     */
    @Override
    public PlayerResponse registerAndLogin(Session session, String playerName, String passWord) {
        Player existPlayer = playerDao.getPlayerByName(playerName);
        // 玩家名已被占用
        if (existPlayer != null) {
            throw new ErrorCodeException(ResultCode.PLAYER_EXIST);
        }
        // 创建新帐号
        Player player = new Player();
        player.setPlayerName(playerName);
        player.setPassWord(passWord);
        playerDao.createPlayer(player);

        // 登录
        return login(session, playerName, passWord);
    }

    /**
     * 登录
     *
     * @param session {@link Session}
     * @param playerName 玩家名称
     * @param passWord 玩家密码
     * @return PlayerResponse
     */
    @Override
    public PlayerResponse login(Session session, String playerName, String passWord) {
        // 判断当前会话是否已登录
        if (session.getAttachment() != null) {
            throw new ErrorCodeException(ResultCode.HAS_LOGIN);
        }
        // 玩家不存在
        Player player = playerDao.getPlayerByName(playerName);
        if (player == null) {
            throw new ErrorCodeException(ResultCode.PLAYER_NO_EXIST);
        }
        // 密码错误
        if (!player.getPassWord().equals(passWord)) {
            throw new ErrorCodeException(ResultCode.PASSWARD_ERROR);
        }

        long playerId = player.getPlayerId();
        // 判断玩家是否其他地方登录过
        boolean onlinePlayer = SessionManager.isOnlinePlayer(playerId);
        if (onlinePlayer) {
            Session oldSession = SessionManager.removeSession(playerId);
            oldSession.removeAttachment();
            // 踢玩家下线
            oldSession.close();
        }

        // 加入在线玩家会话
        if (SessionManager.putSession(player.getPlayerId(), session)) {
            session.setAttachment(player);
        } else {
            throw new ErrorCodeException(ResultCode.LOGIN_FAIL);
        }

        // 创建 Response 传输对象返回
        PlayerResponse response = new PlayerResponse();
        response.setPlayerId(playerId);
        response.setPlayerName(playerName);
        response.setLevel(player.getLevel());
        response.setExp(player.getExp());
        return response;
    }
}
