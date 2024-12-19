package com.rbac.exception;
import org.springframework.http.HttpStatus;
public class NotFoundException extends BusinessException {
    public NotFoundException() {
        super(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase());
    }
}