package com.example.domain;

import cn.hutool.core.date.DateTime;

public class Article {
    // 文章ID
    private int article_id;
    // 用户ID
    private int user_id;
    // 用户名
    private String username;
    // 标题
    private String title;
    // 内容
    private String content;
    // 封面
    private String cover_url;
    // 浏览量
    private int visit_count;
    // 点赞数
    private int like_count;
    // 评论数
    private int comment_count;
    // 创建时间
    private String created_at;

    public Article() {
    }

    public Article(String title, String content, String cover_url) {
        this.title = title;
        this.content = content;
        this.cover_url = cover_url;
    }

    /**
     * 获取
     *
     * @return article_id
     */
    public int getArticle_id() {
        return article_id;
    }

    /**
     * 设置
     *
     * @param article_id
     */
    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    /**
     * 获取
     *
     * @return user_id
     */
    public int getUser_id() {
        return user_id;
    }

    /**
     * 设置
     *
     * @param user_id
     */
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    /**
     * 获取
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取
     *
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置
     *
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取
     *
     * @return cover_url
     */
    public String getCover_url() {
        return cover_url;
    }

    /**
     * 设置
     *
     * @param cover_url
     */
    public void setCover_url(String cover_url) {
        this.cover_url = cover_url;
    }

    /**
     * 获取
     *
     * @return visit_count
     */
    public int getVisit_count() {
        return visit_count;
    }

    /**
     * 设置
     *
     * @param visit_count
     */
    public void setVisit_count(int visit_count) {
        this.visit_count = visit_count;
    }

    /**
     * 获取
     *
     * @return like_count
     */
    public int getLike_count() {
        return like_count;
    }

    /**
     * 设置
     *
     * @param like_count
     */
    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    /**
     * 获取
     *
     * @return comment_count
     */
    public int getComment_count() {
        return comment_count;
    }

    /**
     * 设置
     *
     * @param comment_count
     */
    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    /**
     * 获取
     *
     * @return created_at
     */
    public String getCreated_at() {
        return created_at;
    }

    /**
     * 设置
     *
     * @param created_at
     */
    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String toString() {
        return "Article{article_id = " + article_id + ", user_id = " + user_id + ", username = " + username + ", title = " + title + ", content = " + content + ", cover_url = " + cover_url + ", visit_count = " + visit_count + ", like_count = " + like_count + ", comment_count = " + comment_count + ", created_at = " + created_at + "}";
    }
}
