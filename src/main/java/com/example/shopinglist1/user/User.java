package com.example.shopinglist1.user;

import com.example.shopinglist1.shopList.ShopList;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;


import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long userId;
    private String userName;

    private String email;
    private Role role;
    @JsonIgnore
    private String password;
    @JsonIgnore
    @ManyToMany(
            cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "users_shop_lists",
            joinColumns = @JoinColumn(name = "shops_user_id"),
            inverseJoinColumns = @JoinColumn(name = "shops_shop_list_id")
    )
    private Set<ShopList> shopLists = new HashSet<>();


    public void addShopList(ShopList shopList) {
        this.shopLists.add(shopList);
        shopList.getUsers().add(this);
    }

    public void removeShopList(ShopList shopList) {
        this.shopLists.remove(shopList);
        shopList.getUsers().remove(this);
    }

    public Long getUserId() {
        return userId;
    }

    public enum Role {
        USER("ROLE_USER"),
        ADMIN("ROLE_ADMIN");
        private final String name;

        Role(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public User(String userName, String email, Role role, String password) {
        this.userName = userName;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return userId != null && Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


}
