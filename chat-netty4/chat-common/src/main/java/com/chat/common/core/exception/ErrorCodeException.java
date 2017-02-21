package com.chat.common.core.exception;

/**
 * description: 错误码携带异常
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-24 18:26
 */
public class ErrorCodeException extends RuntimeException {

    private static final long serialVersionUID = 3413569479098922562L;

    /**
     * 错误代码
     */
    private final int         errorCode;

    public int getErrorCode() {
        return errorCode;
    }

    /**
     * 构造方法
     *
     * @param errorCode 错误代码
     */
    public ErrorCodeException(int errorCode){
        this.errorCode = errorCode;
    }
}
