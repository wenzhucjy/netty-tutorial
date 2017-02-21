package com.netty.module.fuben.request;

import com.netty.serial.Serializer;

/**
 * description: 副本Request对象
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-24 17:16
 */
public class FightRequest extends Serializer {

    /**
     * 副本id
     */
    private int fubenId;

    /**
     * 次数
     */
    private int count;

    public int getFubenId() {
        return fubenId;
    }

    public void setFubenId(int fubenId) {
        this.fubenId = fubenId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    protected void read() {
        this.fubenId = readInt();
        this.count = readInt();
    }

    @Override
    protected void write() {
        writeInt(fubenId);
        writeInt(count);
    }

}
