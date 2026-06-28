package com.rbac.v2.utils;
import com.rbac.v2.exception.BusinessException;
public class UnifiedResponseStructureFormatterUtils {
    private UnifiedResponseStructureFormatterUtils() {

    }
    public static UnifiedResponseStructure format(Integer code, String message, Object data) {
        return new UnifiedResponseStructure(code, message, data);
    }
    public static UnifiedResponseStructure format(BusinessException exc) {
        return new UnifiedResponseStructure(exc.getCode(), exc.getMessage(), null);
    }
}