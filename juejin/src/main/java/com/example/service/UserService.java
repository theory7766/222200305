package com.example.service;

import com.example.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public interface UserService {

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    public boolean register(User user);

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    public String login(User user);

    /**
     * 获取用户信息
     *
     * @param user_id
     * @return
     */
    public HashMap<String, String> getUserInformation(int user_id);

    /**
     * 修改user_id相应的username
     *
     * @param user_id
     * @param username
     * @return
     */
    public boolean updateUsernameByUserId(int user_id, String username);

    /**
     * 修改user_id相应的password
     *
     * @param user_id
     * @param password
     * @return
     */

    public boolean updatePasswordByUserId(int user_id, String password);

    /**
     * 修改user_id相应的avatar_url
     *
     * @param user_id
     * @param avatar_url
     * @return
     */

    public boolean updateAvatarUrlByUserId(int user_id, String avatar_url);

}
