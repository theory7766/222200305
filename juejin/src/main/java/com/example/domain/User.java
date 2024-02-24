package com.example.domain;

import cn.hutool.core.date.DateTime;

public class User  {
    private String username;
    private String password;

    // 用户id
    private int user_id;
    // 头像
    private String avatar_url;
    // 注册时间
    private DateTime created_at;

    private String token;


    public User(String username,String password){
        this.username = username;
        this.password = password;
        this.user_id = 0;
        this.avatar_url = null;
        this.created_at = DateTime.of(0);
    }

    public User(String username, String password, int user_id, String avatar_url, DateTime created_at,String token) {
        this.username = username;
        this.password = password;
        this.user_id = user_id;
        this.avatar_url = avatar_url;
        this.created_at = created_at;
        this.token = token;
    }


    public User() {
    }

    /**
     * 获取
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取
     * @return user_id
     */
    public int getUser_id() {
        return user_id;
    }

    /**
     * 设置
     * @param user_id
     */
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    /**
     * 获取
     * @return avatar_url
     */
    public String getAvatar_url() {
        return avatar_url;
    }

    /**
     * 设置
     * @param avatar_url
     */
    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    /**
     * 获取
     * @return created_at
     */
    public DateTime getCreated_at() {
        return created_at;
    }

    /**
     * 设置
     * @param created_at
     */
    public void setCreated_at(DateTime created_at) {
        this.created_at = created_at;
    }

    /**
     * 获取
     * @return token
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置
     * @param token
     */
    public void setToken(String token) {
        this.token = token;
    }

    public String toString() {
        return "User{username = " + username + ", password = " + password + ", user_id = " + user_id + ", avatar_url = " + avatar_url + ", created_at = " + created_at + ", token = " + token + "}";
    }
}
