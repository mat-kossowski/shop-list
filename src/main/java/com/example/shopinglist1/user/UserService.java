package com.example.shopinglist1.user;

import com.example.shopinglist1.payload.request.LoginRequest;
import com.example.shopinglist1.payload.request.RegisterRequest;
import com.example.shopinglist1.payload.response.MessageResponse;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface UserService {

    Optional<User> getUserByUserName(String userName);
    boolean addUser(User user);

    boolean isUserExistsByEmail(RegisterRequest registerRequest);
     ResponseCookie authenticateUser(LoginRequest loginRequest);
    ResponseCookie logoutUser();


    ResponseEntity<MessageResponse> addNewUser(RegisterRequest registerRequest);

}
