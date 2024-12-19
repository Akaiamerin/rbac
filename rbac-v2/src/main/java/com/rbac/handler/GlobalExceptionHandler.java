package com.rbac.handler;
import com.rbac.exception.BusinessException;
import com.rbac.exception.ServerInternalErrorException;
import com.rbac.utils.FormatUtils;
import com.rbac.utils.ResponseResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ResponseResult handleBusinessException(BusinessException exc) {
        return FormatUtils.formatResponseResult(exc);
    }
    @ExceptionHandler(Exception.class)
    public ResponseResult handleException(Exception exc) {
        return FormatUtils.formatResponseResult(new ServerInternalErrorException());
    }
}