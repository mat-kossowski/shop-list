package com.example.shopinglist1.product;

import com.example.shopinglist1.shopList.ShopList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue
    private Long productId;
    private String productName;
    private String productAmount;
    private boolean productStatus;

    @Enumerated
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "shopList_id")
    private ShopList shopList;


//    public Product(String productName, String productAmount) {
//        this.productName = productName;
//        this.productAmount = productAmount;
//        this.category = category;
//        this.productStatus = false;
//    }
public enum Category {
    NABIAL,
    MIESO,
    WARZYWA,
    CHEMIA,
    INNE
}

    public Product(String productName, String productAmount, boolean productStatus, Category category, ShopList shopList) {
        this.productName = productName;
        this.productAmount = productAmount;
        this.productStatus = productStatus;
        this.category = category;
        this.shopList = shopList;
    }
}
