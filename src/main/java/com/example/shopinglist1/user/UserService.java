package com.example.shopinglist1.user;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> getUserByUserName(String userName);
    boolean addUser(User user);

}
