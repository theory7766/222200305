package com.example.controller;

import com.example.controller.result.Base;
import com.example.controller.result.Code;
import com.example.exception.BusinessException;
import com.example.exception.SystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectExceptionAdvice {
    @ExceptionHandler(SystemException.class)
    public Base doSystemException(SystemException ex){
        // 记录日志

        // 发送消息给运维

        //发送邮件给开发人员,ex对象
        return new Base(ex.getCode(),ex.getMessage());
    }
    @ExceptionHandler(BusinessException.class)
    public Base doSystemException(BusinessException ex){
        return new Base(ex.getCode(),ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Base doException(Exception ex){
        // 记录日志
        // 发送消息给运维
        //发送邮件给开发人员
        ex.printStackTrace();
        return new Base(Code.ERROR,
                "The system is busy, please try again later!");
    }
}
