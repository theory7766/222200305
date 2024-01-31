package com.example.domain;

public class Task {
    // 标题
    private String title;
    // 内容
    private String content;
    // 开始时间
    private String start_time;
    // 结束时间
    private String end_time;
    // 完成状态
    private String status;

    public Task(){}
    public Task(String title, String content, String start_time, String end_time, String status) {
        this.title = title;
        this.content = content;
        this.start_time = start_time;
        this.end_time = end_time;
        this.status = status;
    }

    /**
     * 获取
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取
     * @return start_time
     */
    public String getStart_time() {
        return start_time;
    }

    /**
     * 设置
     * @param start_time
     */
    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    /**
     * 获取
     * @return end_time
     */
    public String getEnd_time() {
        return end_time;
    }

    /**
     * 设置
     * @param end_time
     */
    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    /**
     * 获取
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public String toString() {
        return "Tasks{title = " + title + ", content = " + content + ", start_time = " + start_time + ", end_time = " + end_time + ", status = " + status + "}";
    }
}
