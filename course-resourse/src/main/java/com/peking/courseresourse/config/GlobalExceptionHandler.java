package com.peking.courseresourse.config;

import exception.RException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import utils.R;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RException.class)
    public R handleResultException(RException e) {
        System.out.println("执行了....");
        return R.error(e.getMsg());
    }
}
