package com.example.controller;

import com.example.domain.User;
import com.example.exception.BusinessException;
import com.example.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    // 用户注册
    @PostMapping("/register/")
    public Result userRegister(@RequestBody User user){
//        System.out.println(user);
        userService.register(user);
        return Result.success();
    }
    // 用户登录
    @PostMapping("/login/")
    public LoginResult userLogin(@RequestBody User user){
        String token = userService.login(user);
        return new LoginResult(Code.SUCCESS,"success",user.getUsername(),token);
    }
}