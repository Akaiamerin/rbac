package com.rbac.utils;
import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude
public class ResponseResult {
    public int code;
    public String message;
    public Object data;
    private ResponseResult() {

    }
    public ResponseResult(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}