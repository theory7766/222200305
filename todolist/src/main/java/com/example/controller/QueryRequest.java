package com.example.controller;

import com.example.domain.Task;

import java.util.List;

public class QueryRequest {
    private Integer code;
    private String msg;
    private Data data;
    public QueryRequest(Integer code, String msg, List<Task> item, Integer total) {
        this.data = new Data(item,total);
        this.code = code;
        this.msg = msg;
    }

    /**
     * 获取
     * @return data
     */
    public Data getData() {
        return data;
    }

    /**
     * 设置
     * @param data
     */
    public void setData(Data data) {
        this.data = data;
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
