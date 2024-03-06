package com.example.controller.result;

public class LoginResult<T> {
    private Base base;
    private String token;

    public LoginResult(String code, String msg, String token) {
        this.base = new Base(code, msg);
        this.token = token;
    }

    /**
     * 获取
     *
     * @return base
     */
    public Base getBase() {
        return base;
    }

    /**
     * 设置
     *
     * @param base
     */
    public void setBase(Base base) {
        this.base = base;
    }

    /**
     * 获取
     *
     * @return token
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置
     *
     * @param token
     */
    public void setToken(String token) {
        this.token = token;
    }

    public String toString() {
        return "LoginResult{base = " + base + ", token = " + token + "}";
    }
}
