package com.chat.common.module.player.request;

import com.chat.common.core.serial.Serializer;

/**
 * description: 登陆请求
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-24 18:42
 */
public class LoginRequest extends Serializer {

    /**
     * 用户名
     */
    private String playerName;

    /**
     * 密码
     */
    private String passWord;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    protected void read() {
        this.playerName = readString();
        this.passWord = readString();
    }

    @Override
    protected void write() {
        writeString(playerName);
        writeString(passWord);
    }
}
