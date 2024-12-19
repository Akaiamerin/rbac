package com.rbac.exception;
import org.springframework.http.HttpStatus;
public class ServerInternalErrorException extends BusinessException {
    public ServerInternalErrorException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }
}