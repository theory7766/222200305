package com.example.controller.result;

import java.util.List;

public class QueryRequest<T> {
    private Base base;
    private Data<T> data;
    public QueryRequest(Base base,List<T> item, Integer total){
        this.base = base;
        this.data = new Data(item,total);
    }
    public QueryRequest(Base base,List<T> item, Integer total,Object task){
        this.base = base;
        this.data = new Data(item,total,task);
    }
    public QueryRequest(Base base,Object task){
        this.base = base;
        this.data = new Data(task);
    }


    /**
     * 获取
     * @return base
     */
    public Base getBase() {
        return base;
    }

    /**
     * 设置
     * @param base
     */
    public void setBase(Base base) {
        this.base = base;
    }

    /**
     * 获取
     * @return data
     */
    public Data<T> getData() {
        return data;
    }

    /**
     * 设置
     * @param data
     */
    public void setData(Data<T> data) {
        this.data = data;
    }



    public String toString() {
        return "QueryRequest{base = " + base + ", data = " + data + "}";
    }
}
