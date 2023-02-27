package com.example.shopinglist1.shopList;

import com.example.shopinglist1.product.Product;
import com.example.shopinglist1.user.User;
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
public class ShopList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long shopListId;

    private String listName;
    private boolean alphabetically;
    @JsonIgnore
    @ManyToMany(
            fetch = FetchType.EAGER,
            mappedBy = "shopLists")

    private Set<User> users = new HashSet<>();

    @JsonIgnore
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
             orphanRemoval = true,
            mappedBy = "shopList"
    )
    private List<Product> products = new ArrayList<>();


    public ShopList(String listName, boolean alphabetically) {
        this.listName = listName;
        this.alphabetically = alphabetically;
    }


    public void setAlphabetically(boolean alphabetically) {
        this.alphabetically = alphabetically;
    }

    public boolean isAlphabetically() {
        return alphabetically;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ShopList shopList = (ShopList) o;
        return shopListId != null && Objects.equals(shopListId, shopList.shopListId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
