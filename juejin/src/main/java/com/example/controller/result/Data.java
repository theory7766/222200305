package com.example.controller.result;

import java.util.List;

public class Data<T> {
    private Object task;
    private List<T> items;
    private Integer total;

    public Data(Object task){
        this.task = task;
        this.items = null;
        this.total = null;
    }

    public Data(List<T> items, Integer total) {
        this.items = items;
        this.total = total;
        this.task = null;
    }

    public Data() {
    }

    public Data(List<T> items, Integer total, Object task) {
        this.items = items;
        this.total = total;
        this.task = task;
    }

    /**
     * 获取
     * @return items
     */
    public List<T> getItems() {
        return items;
    }

    /**
     * 设置
     * @param items
     */
    public void setItems(List<T> items) {
        this.items = items;
    }

    /**
     * 获取
     * @return total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * 设置
     * @param total
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * 获取
     * @return task
     */
    public Object getTask() {
        return task;
    }

    /**
     * 设置
     * @param task
     */
    public void setTask(Object task) {
        this.task = task;
    }

    public String toString() {
        return "Data{items = " + items + ", total = " + total + ", task = " + task + "}";
    }
}
