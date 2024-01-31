package com.example.dao;

import com.example.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
public interface UserDao {
    // 插入数据
    @Insert("insert into users (username,password) values (#{username},#{password})")
    public int saveUser(User user);
    // 向username_token表插入数据
    @Insert("insert into users_token (username,token) values (#{username},#{token})")
    public int saveToken(String username,String token);
    // 按名字和密码查询数据(登录）
    @Select("select * from users where username = #{username}")
    public User getByName(String username);
    // 按名字查询token
    @Select("select token from users_token where username = #{username}")
    public String getToken(String username);
}
