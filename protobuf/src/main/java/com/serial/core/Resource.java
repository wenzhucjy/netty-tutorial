package com.serial.core;

/**
 * description: 玩家金币
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-24 16:59
 */
public class Resource extends Serializer {

    private int gold; // 金币

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
