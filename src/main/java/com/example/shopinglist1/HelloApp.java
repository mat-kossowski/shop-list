package com.example.shopinglist1;


import com.example.shopinglist1.user.User;
import com.example.shopinglist1.user.UserService;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class HelloApp {

    private UserService userService;

    @GetMapping("/hello")
    public String hello(@CurrentSecurityContext(expression="authentication?.name")
                    String username){

        return "hello  " + username;
    }
    @GetMapping("/hello/list")
    public String helloUser(@CurrentSecurityContext(expression="authentication?.name")
                    String username) {
        System.out.println(username);
        Optional<User> user = userService.getUserByUserName(username);
        long id = user.get().getUserId();
        return "hello  " + id;
    }


}
