package com.example.dao;

import com.example.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
public interface UserDao {
    // 插入数据
    @Insert("insert into users (username,password,avatar_url,created_at) " +
            "values (#{username},#{password},#{avatar_url},#{created_at})")
    public int saveUser(User user);
    // 更新token数据
    @Update("update users set token = #{token} where username = #{username}")
    public int setTokenByUsername(String username,String token);
    // 按名字查询数据(登录）
    @Select("select * from users where username = #{username}")
    public User getByName(String username);
    // 按名字查询token
    @Select("select token from users where username = #{username}")
    public String getToken(String username);
    // 按user_id查询用户信息
    @Select("select * form users where user_id = #{user_id}")
    public User getUserInformationByUserId(int user_id);
    // 修改user_id相应的username
    @Update("update users set username = #{username} where user_id = #{user_id}")
    public int setUsernameByUserId(String username,int user_id);
    // 修改user_id相应的password
    @Update("update users set password = #{password} where user_id = #{user_id}")
    public int setPasswordByUserId(String password, int user_id);
    // 修改user_id相应的avatar_url
    @Update("update users set avatar_url = #{avatar_url} where user_id = #{user_id}")
    public int setAvatarUrlByUserId(String avatar_url, int user_id);
}
