package com.rbac.utils;
import com.rbac.exception.BusinessException;
public class FormatUtils {
    private FormatUtils() {

    }
    public static ResponseResult formatResponseResult(Integer code, String message, Object data) {
        return new ResponseResult(code, message, data);
    }
    public static ResponseResult formatResponseResult(BusinessException exc) {
        return new ResponseResult(exc.getCode(), exc.getMessage(), null);
    }
}