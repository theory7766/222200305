package com.example.service.impl;


import cn.hutool.core.codec.Base64Decoder;
import cn.hutool.core.codec.Base64Encoder;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.asymmetric.SignAlgorithm;
import cn.hutool.crypto.digest.DigestUtil;
import com.example.controller.Code;
import com.example.controller.Result;
import com.example.dao.UserDao;
import com.example.domain.User;
import com.example.exception.BusinessException;
import com.example.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;


    // 用户注册
    @Override
    public boolean register(@RequestBody User user) {
        User newUser = userDao.getByName(user.getUsername());
        if (newUser!=null) {
            throw new BusinessException(Code.USER_REGISTER_ERROR, "the username already exists");
        }else if (user.getPassword().length() <= 6){
            throw new BusinessException(Code.USER_REGISTER_ERROR,
                        "the length of password must bigger than 6");
        }else {
            // 使用bcript加密
            String newPassword = DigestUtil.bcrypt(user.getPassword());
            System.out.println(newPassword);
            user.setPassword(newPassword);
            userDao.saveUser(user);
            return true;
        }

    }
    // 用户登录
    @Override
    public String login(@RequestBody User user) {
        User matchUser = userDao.getByName(user.getUsername());
        if (matchUser == null){
            throw new BusinessException(Code.USER_LOGIN_ERROR,"username error!");
        }
        // 校验密码
        if (!DigestUtil.bcryptCheck(user.getPassword(),matchUser.getPassword())){
            throw new BusinessException(Code.USER_LOGIN_ERROR,"password error!");
        }else {
           // 非首次认证
           String token = userDao.getToken(user.getUsername());
            if (token == null) {
//                System.out.println("进行首次token制作保存：");
                Sign sign = SecureUtil.sign(SignAlgorithm.MD5withRSA);
                // 1.将签名数据转换为字节数组
                byte[] data = user.getUsername().getBytes();
                // 2.将字节数组放到sign对象的sign方法中
                byte[] signed = sign.sign(data);
                // 3.将签名通过base64转码解析为字符串格式
                token = Base64Encoder.encode(signed);
                // 4.保存到数据库中
                userDao.saveToken(user.getUsername(), token);
            }
            return token;
        }
    }
}
