package com.example.controller.result;

public class Base {
    private String code;
    private String msg;

    public static Base success() {
        return new Base(Code.SUCCESS, "success");
    }

    public static Base loginFailure() {
        return new Base(Code.ERROR, "username or password error");
    }

    public Base(String code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    /**
     * 获取
     *
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置
     *
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取
     *
     * @return msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 设置
     *
     * @param msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
