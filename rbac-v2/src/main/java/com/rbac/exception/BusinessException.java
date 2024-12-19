package com.rbac.exception;
public class BusinessException extends Exception {
    private int code;
    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }
    public int getCode() {
        return code;
    }
}