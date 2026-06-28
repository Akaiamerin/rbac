package com.rbac.v1.handler;
import com.rbac.v1.exception.BusinessException;
import com.rbac.v1.exception.ServerInternalErrorException;
import com.rbac.v1.utils.UnifiedResponseStructure;
import com.rbac.v1.utils.UnifiedResponseStructureFormatterUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public UnifiedResponseStructure handleBusinessException(BusinessException exc) {
        return UnifiedResponseStructureFormatterUtils.format(exc);
    }
    @ExceptionHandler(Exception.class)
    public UnifiedResponseStructure handleException(Exception exc) {
        return UnifiedResponseStructureFormatterUtils.format(new ServerInternalErrorException());
    }
}