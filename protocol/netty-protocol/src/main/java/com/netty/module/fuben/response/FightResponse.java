package com.netty.module.fuben.response;

import com.netty.serial.Serializer;

/**
 * description: 副本Reponse对象
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-24 17:16
 */
public class FightResponse extends Serializer {

    /**
     * 获取金币
     */
    private int gold;

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    @Override
    protected void read() {
        this.gold = readInt();
    }

    @Override
    protected void write() {
        writeInt(gold);
    }
}
