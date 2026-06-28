package com.rbac.v2.handler;
import com.rbac.v2.exception.BusinessException;
import com.rbac.v2.exception.ServerInternalErrorException;
import com.rbac.v2.utils.UnifiedResponseStructure;
import com.rbac.v2.utils.UnifiedResponseStructureFormatterUtils;
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