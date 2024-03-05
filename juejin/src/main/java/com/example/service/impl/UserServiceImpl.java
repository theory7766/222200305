package com.example.service.impl;


import cn.hutool.core.codec.Base64Encoder;
import cn.hutool.core.date.DateTime;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.asymmetric.SignAlgorithm;
import cn.hutool.crypto.digest.DigestUtil;
import com.example.controller.result.Code;
import com.example.dao.UserDao;
import com.example.domain.User;
import com.example.exception.BusinessException;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.SimpleDateFormat;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;


    // 用户注册
    @Override
    public boolean register(@RequestBody User user) {
        User newUser = userDao.getByName(user.getUsername());
        if (newUser!=null) {
            throw new BusinessException(Code.ERROR, "the username already exists");
        }else if (user.getPassword().length() <= 6){
            throw new BusinessException(Code.ERROR,
                        "the length of password must bigger than 6");
        }else {
            // 使用bcript加密
            String newPassword = DigestUtil.bcrypt(user.getPassword());
//            System.out.println(newPassword);
            user.setPassword(newPassword);
           // 将数据保存进用户表users中
            DateTime created_at = DateTime.now();
            String mysqlFormat = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat sdf = new SimpleDateFormat(mysqlFormat);
            String formattedDateTime = sdf.format(created_at);
            user.setCreated_at(formattedDateTime);
            // 设置默认头像
            String avatar_url = "null";
            user.setAvatar_url(avatar_url);
            userDao.saveUser(user);
            return true;
        }

    }
    // 用户登录
    @Override
    public String login(@RequestBody User user) {
        User matchUser = userDao.getByName(user.getUsername());
        if (matchUser == null){
            throw new BusinessException(Code.ERROR,"username error!");
        }
        // 校验密码
        if (!DigestUtil.bcryptCheck(user.getPassword(),matchUser.getPassword())){
            throw new BusinessException(Code.ERROR,"password error!");
        }else {
           // 非首次认证
           String token = userDao.getToken(user.getUsername());
            if (token == null || token.compareTo("NULL") == 0) {
//                System.out.println("进行首次token制作保存：");
                Sign sign = SecureUtil.sign(SignAlgorithm.MD5withRSA);
                // 1.将签名数据转换为字节数组
                byte[] data = user.getUsername().getBytes();
                // 2.将字节数组放到sign对象的sign方法中
                byte[] signed = sign.sign(data);
                // 3.将签名通过base64转码解析为字符串格式
                token = Base64Encoder.encode(signed);
//                System.out.println(token);
                // 4.保存到数据库中
                userDao.setTokenByUsername(user.getUsername(), token);
            }
            return token;
        }
    }
    // 获取用户信息
    @Override
    public User getUserInformation(int user_id) {
        User user = userDao.getUserInformationByUserId(user_id);
        if (user == null){
            throw new BusinessException(Code.ERROR,"get information failed");
        } else {
            return user;
        }
    }

    @Override
    public boolean updateUsernameByUserId(int user_id, String username) {
        User user = userDao.getByName(username);
        if (user != null){
            throw new BusinessException(Code.ERROR, "the username already exists");
        } else {
            int set = userDao.setUsernameByUserId(username,user_id);
            if (set == 0){
                throw new BusinessException(Code.ERROR,"update username failed");
            } else {
                return true;
            }
        }
    }

    @Override
    public boolean updatePasswordByUserId(int user_id, String password) {
        if (password.length() <= 6){
            throw new BusinessException(Code.ERROR,
                    "the length of password must bigger than 6");
        } else {
            String newPassword = DigestUtil.bcrypt(password);
            int set = userDao.setPasswordByUserId(newPassword,user_id);
            if (set == 0){
                throw new BusinessException(Code.ERROR,"update password failed");
            } else {
                return true;
            }
        }
    }

    @Override
    public boolean updateAvatarUrlByUserId(int user_id, String avatar_url) {
        int set = userDao.setAvatarUrlByUserId(avatar_url,user_id);
        if (set == 0){
            throw new BusinessException(Code.ERROR,"update avatar_url failed");
        } else {
            return true;
        }
    }

}
