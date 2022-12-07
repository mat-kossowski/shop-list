package com.example.shopinglist1.secutiry.services;

import com.example.shopinglist1.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
@Data
public class UserDetailsImpl implements UserDetails {

     private Long id;
    private String email;
    private String userName;
    private User.Role role;

    @JsonIgnore
    private String password;


    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String email, String userName, User.Role role, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.role = role;
        this.password = password;
        this.authorities = authorities;
    }

     public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>(Collections.singleton(
                new SimpleGrantedAuthority(user.getRole().toString())));

        return new UserDetailsImpl(
                user.getUserId(),
                user.getEmail(),
                user.getUserName(),
                user.getRole(),
                user.getPassword(),
                authorities
        );
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


     @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    UserDetailsImpl user = (UserDetailsImpl) o;
    return Objects.equals(id, user.id);
  }
}
