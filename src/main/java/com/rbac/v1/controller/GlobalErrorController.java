package com.rbac.v1.controller;
import com.rbac.v1.exception.NotFoundException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class GlobalErrorController implements ErrorController {
    @RequestMapping("/error")
    public void error() throws NotFoundException {
        throw new NotFoundException();
    }
}