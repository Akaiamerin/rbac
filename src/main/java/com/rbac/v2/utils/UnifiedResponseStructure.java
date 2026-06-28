package com.rbac.v2.utils;
import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude
public class UnifiedResponseStructure {
    public int code;
    public String message;
    public Object data;
    private UnifiedResponseStructure() {

    }
    public UnifiedResponseStructure(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}