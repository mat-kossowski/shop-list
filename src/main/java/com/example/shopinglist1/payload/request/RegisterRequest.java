package com.example.shopinglist1.payload.request;

import com.example.shopinglist1.user.User;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Data
public class RegisterRequest {

    @NotBlank
    @Size(min = 3, max = 20)
    private String userName;


    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private User.Role role = User.Role.USER;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
}
