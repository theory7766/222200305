package com.example.controller;

import com.example.domain.Task;
import java.util.List;

public class Result {
    private Integer code;
    private String msg;

    public static Result success(){
        return new Result(Code.SUCCESS,"success");
    }

    public static Result loginFailure(){
        return new Result(Code.USER_LOGIN_ERROR, "username or password error");
    }

    public Result(Integer code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    /**
     * 获取
     * @return code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * 设置
     * @param code
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * 获取
     * @return msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 设置
     * @param msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
