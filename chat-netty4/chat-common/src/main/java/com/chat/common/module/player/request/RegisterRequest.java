package com.chat.common.module.player.request;

import com.chat.common.core.serial.Serializer;

/**
 * description: 注册请求
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-24 18:42
 */
public class RegisterRequest extends Serializer {

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

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPassWord() {
        return passWord;
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
