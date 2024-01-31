package com.example.controller;

import com.example.domain.Task;

import java.util.List;

public class Data {
    private List<Task> item;
    private Integer total;

    public Data(List<Task> item, Integer total) {
        this.item = item;
        this.total = total;
    }

    /**
     * 获取
     * @return item
     */
    public List<Task> getItem() {
        return item;
    }

    /**
     * 设置
     * @param item
     */
    public void setItem(List<Task> item) {
        this.item = item;
    }

    /**
     * 获取
     * @return total
     */
    public int getTotal() {
        return total;
    }

    /**
     * 设置
     * @param total
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    public String toString() {
        return "Data{item = " + item + ", total = " + total + "}";
    }
}
