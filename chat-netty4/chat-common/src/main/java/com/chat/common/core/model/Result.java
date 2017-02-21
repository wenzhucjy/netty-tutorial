package com.chat.common.core.model;

import com.chat.common.core.serial.Serializer;

/**
 * description: 封装响应对象
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-24 18:25
 */
public class Result<T extends Serializer> {

    /**
     * 结果码
     */
    private int resultCode;

    /**
     * 结果内容
     */
    private T   content;

    public static <T extends Serializer> Result<T> SUCCESS(T content) {
        Result<T> result = new Result<>();
        result.resultCode = ResultCode.SUCCESS;
        result.content = content;
        return result;
    }

    public static <T extends Serializer> Result<T> SUCCESS() {
        Result<T> result = new Result<>();
        result.resultCode = ResultCode.SUCCESS;
        return result;
    }

    public static <T extends Serializer> Result<T> ERROR(int resultCode) {
        Result<T> result = new Result<>();
        result.resultCode = resultCode;
        return result;
    }

    public static <T extends Serializer> Result<T> valueOf(int resultCode, T content) {
        Result<T> result = new Result<>();
        result.resultCode = resultCode;
        result.content = content;
        return result;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public boolean isSuccess() {
        return this.resultCode == ResultCode.SUCCESS;
    }

}
