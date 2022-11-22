package com.example.shopinglist1.shopList;

import com.example.shopinglist1.product.Product;
import com.example.shopinglist1.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table()
public class ShopList {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private long shopListId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private User user;

    @ManyToMany
    @JsonIgnore
    private Product product;
}
