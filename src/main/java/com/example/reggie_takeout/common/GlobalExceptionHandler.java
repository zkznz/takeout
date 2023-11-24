package com.example.reggie_takeout.common;

import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;


public class GlobalExceptionHandler {
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String> exceptionHandler(SQLIntegrityConstraintViolationException ex){
        if(ex.getMessage().contains("Duplicate entry")){
            String [] split=ex.getMessage().split(" ");
            String msg=split[2]+"已存在";
            return R.error(msg);
        }
        return R.error("未知错误");
    }

    //处理自定义异常
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String> CustomExceptionHandler(CustomException ex){
        return R.error(ex.getMessage());
    }
}
