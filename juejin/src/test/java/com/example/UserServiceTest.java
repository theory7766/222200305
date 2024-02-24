package com.example;

import com.example.service.UserService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.domain.User;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@MapperScan("com.example.dao")
public class UserServiceTest {
    @Autowired
    private UserService userService;
//    @Test
//    public void testRegister(){
//        User user = new User("lax", "sjkdnajnksj");
//        boolean flag = userService.register(user);
//        System.out.println(flag);
//    }
//
    @Test
    public void testLogin(){
        User user = new User("lax", "sjkdnajnksj");
        String token = userService.login(user);
        System.out.println(token);
    }

}
