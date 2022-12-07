package com.example.shopinglist1.payload.response;


import com.example.shopinglist1.user.User;
import lombok.Data;

@Data
public class UserInfoResponse {

    private Long id;
    private String userName;
    private User.Role role;

    public UserInfoResponse(Long id, String userName, User.Role role) {
        this.id = id;
        this.userName = userName;
        this.role = role;
    }
}
