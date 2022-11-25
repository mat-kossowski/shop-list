package com.example.shopinglist1;

import com.example.shopinglist1.user.User;
import com.example.shopinglist1.user.UserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Start {

    public Start(UserRepository userRepository, PasswordEncoder passwordEncoder){

//        User user = new User();
//        user.setUserName("Mateusz");
//        user.setEmail("mateusz@com.pl");
//        user.setRole("ROLE_ADMIN");
//        user.setPassword(passwordEncoder.encode("Mateusz123"));
//        userRepository.save(user);
    }
}
