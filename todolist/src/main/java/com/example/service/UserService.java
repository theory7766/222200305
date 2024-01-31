package com.example.service;

import com.example.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService{

    /**
     * 用户注册
     * @param  user
     * @return
     * */
    public boolean register(User user);

    /**
     * 用户登录
     * @param  user
     * @return
     * */
    public String login(User user);

}
