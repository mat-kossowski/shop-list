package com.example.shopinglist1.user;

import com.example.shopinglist1.shopList.ShopList;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private long userId;
    private String userName;
    private String email;
    @JsonIgnore
    private String password;

    @JsonIgnore
    @OneToMany
    private List<ShopList> shopLists = new ArrayList<>();
}
