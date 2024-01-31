package com.example.controller;

import com.example.exception.BusinessException;
import com.example.exception.SystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectExceptionAdvice {
    @ExceptionHandler(SystemException.class)
    public Result doSystemException(SystemException ex){
        // 记录日志

        // 发送消息给运维

        //发送邮件给开发人员,ex对象
        return new Result(ex.getCode(),ex.getMessage());
    }
    @ExceptionHandler(BusinessException.class)
    public Result doSystemException(BusinessException ex){
        return new Result(ex.getCode(),ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result doException(Exception ex){
        // 记录日志
        // 发送消息给运维
        //发送邮件给开发人员
        ex.printStackTrace();
        return new Result(Code.SYSTEM_UNKNOWN_ERROR,
                "The system is busy, please try again later!");
    }
}
