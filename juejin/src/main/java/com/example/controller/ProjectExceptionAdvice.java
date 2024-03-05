package com.example.controller;

import com.example.controller.result.Base;
import com.example.controller.result.Code;
import com.example.controller.result.QueryRequest;
import com.example.exception.BusinessException;
import com.example.exception.SystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectExceptionAdvice {
    @ExceptionHandler(SystemException.class)
    public QueryRequest doSystemException(SystemException ex){
        Base warning =  new Base(ex.getCode(),ex.getMessage());
        return new QueryRequest(warning);
    }
    @ExceptionHandler(BusinessException.class)
    public QueryRequest doSystemException(BusinessException ex){

        Base warning =  new Base(ex.getCode(),ex.getMessage());
        return new QueryRequest(warning);
    }

    @ExceptionHandler(Exception.class)
    public QueryRequest doException(Exception ex){
        // 记录日志
        // 发送消息给运维
        //发送邮件给开发人员
        ex.printStackTrace();
        Base warning = new Base(Code.ERROR,
                "The system is busy, please try again later!");
        return new QueryRequest(warning);
    }
}
