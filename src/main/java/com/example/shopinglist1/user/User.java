package com.example.shopinglist1.user;

import com.example.shopinglist1.shopList.ShopList;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;


import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@ToString
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
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "user"
    )
    private List<ShopList> shopLists = new ArrayList<>();

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
