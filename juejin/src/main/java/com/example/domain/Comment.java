package com.example.domain;

import cn.hutool.core.date.DateTime;

// 评论类:若不存在父评论，parent_id就为0

public class Comment {
    // 评论ID
    private int comment_id;
    // 用户ID
    private int user_id;
    // 用户名
    private String username;
    // 评论者头像
    private String avatar_url;
    // 文章ID
    private int article_id;
    // 父评论ID
    private int parent_id;
    // 点赞数
    private int like_count;
    // 子评论数
    private int child_count;
    // 内容
    private String content;
    // 创建时间
    private String created_at;

    public Comment() {
    }

    public Comment(int comment_id, int user_id, String username, String avatar_url, int article_id, int parent_id,
                   int like_count, int child_count, String content, String created_at) {
        this.comment_id = comment_id;
        this.user_id = user_id;
        this.username = username;
        this.avatar_url = avatar_url;
        this.article_id = article_id;
        this.parent_id = parent_id;
        this.like_count = like_count;
        this.child_count = child_count;
        this.content = content;
        this.created_at = created_at;
    }

    /**
     * 获取
     *
     * @return comment_id
     */
    public int getComment_id() {
        return comment_id;
    }

    /**
     * 设置
     *
     * @param comment_id
     */
    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
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
     * @return avatar_url
     */
    public String getAvatar_url() {
        return avatar_url;
    }

    /**
     * 设置
     *
     * @param avatar_url
     */
    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
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
     * @return parent_id
     */
    public int getParent_id() {
        return parent_id;
    }

    /**
     * 设置
     *
     * @param parent_id
     */
    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
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
     * @return child_count
     */
    public int getChild_count() {
        return child_count;
    }

    /**
     * 设置
     *
     * @param child_count
     */
    public void setChild_count(int child_count) {
        this.child_count = child_count;
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
        return "Comment{comment_id = " + comment_id + ", user_id = " + user_id + ", username = " + username + ", avatar_url = " + avatar_url + ", article_id = " + article_id + ", parent_id = " + parent_id + ", like_count = " + like_count + ", child_count = " + child_count + ", content = " + content + ", created_at = " + created_at + "}";
    }
}
