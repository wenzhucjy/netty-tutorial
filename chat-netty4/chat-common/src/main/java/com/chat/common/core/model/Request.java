package com.chat.common.core.model;

/**
 * description: 消息模块
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-24 18:23
 */
public class Request {

    /**
     * 模块号
     */
    private short  module;

    /**
     * 命令号
     */
    private short  cmd;

    /**
     * 数据
     */
    private byte[] data;

    /**
     * 设置
     * 
     * @param module 模块号
     * @param cmd 命令号
     * @param data 数据
     * @return Request
     */
    public static Request valueOf(short module, short cmd, byte[] data) {
        Request request = new Request();
        request.setModule(module);
        request.setCmd(cmd);
        request.setData(data);
        return request;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public short getModule() {
        return module;
    }

    public void setModule(short module) {
        this.module = module;
    }

    public short getCmd() {
        return cmd;
    }

    public void setCmd(short cmd) {
        this.cmd = cmd;
    }
}
