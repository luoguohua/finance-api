package com.luoguohua.finance.common.exception;

/**
 * @Version 1.0
 * @Author: luoguohua
 * @Date: 2024/5/11 16:25
 * Content:
 */
public class BusinessException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    protected int code;
    protected String message;

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BusinessException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public BusinessException() {
    }
}
