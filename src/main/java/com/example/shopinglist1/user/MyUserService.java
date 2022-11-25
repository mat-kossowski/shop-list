package com.example.shopinglist1.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MyUserService implements UserService {

    private UserRepository userRepository;

    @Autowired
    public MyUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Optional<User> getUserByUserName(String userName) {
        return userRepository.findUserByUserName(userName);
    }

    @Override
    public boolean addUser(User user) {
        userRepository.save(user);
        return true;
    }




}
