package com.example.exception;

public class SystemException extends RuntimeException {
    private String code;


    public SystemException(String code, String message) {
        super(message);
        this.code = code;
    }

    public SystemException(String code, String message, Throwable cause) {
        super(message, cause);
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

    public String toString() {
        return "SystemException{code = " + code + "}";
    }
}
