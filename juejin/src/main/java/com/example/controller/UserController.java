package com.example.controller;

import com.example.controller.result.Base;
import com.example.controller.result.Code;
import com.example.controller.result.LoginResult;
import com.example.controller.result.QueryRequest;
import com.example.domain.Article;
import com.example.domain.User;
import com.example.service.ArticleService;
import com.example.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/juejin/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    // 用户注册
    @PostMapping("/register")
    public Base userRegister(@RequestBody User user){
//        System.out.println(user);
        userService.register(user);
        return Base.success();
    }
    // 用户登录
    @PostMapping("/login")
    public LoginResult userLogin(@RequestBody User user){
        String token = userService.login(user);
        return new LoginResult(Code.SUCCESS,"success",token);
    }
    // 获取用户信息
    @GetMapping("/information")
    public QueryRequest<Article> getUserInformation(HttpServletRequest request){
        int user_id = articleService.checkToken(request);
        User user = userService.getUserInformation(user_id);
        return new QueryRequest<>(Base.success(),user);
    }
    // 修改用户名
    @PutMapping("/setup/username")
    public QueryRequest updateUsername(HttpServletRequest request,@RequestParam String username){
        int user_id = articleService.checkToken(request);
        userService.updateUsernameByUserId(user_id,username);
        return new QueryRequest(Base.success());
    }
    // 修改密码
    @PutMapping("/setup/password")
    public QueryRequest updatePassword(HttpServletRequest request,@RequestParam String password){
        int user_id = articleService.checkToken(request);
        userService.updatePasswordByUserId(user_id,password);
        return new QueryRequest(Base.success());
    }
    // 修改头像
    @PutMapping("/setup/avatar_url")
    public QueryRequest updateAvatarUrl(HttpServletRequest request,@RequestParam String avatar_url){
        int user_id = articleService.checkToken(request);
        userService.updateAvatarUrlByUserId(user_id,avatar_url);
        return new QueryRequest(Base.success());
    }
}