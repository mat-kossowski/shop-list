package com.example.shopinglist1.product;

import com.example.shopinglist1.shopList.ShopList;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table()
public class Product {
    @Id
    @GeneratedValue
    private Long productId;
    private String productName;
    private Long productAmount;

    private String productUnit;
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
    CHEMIA,
    MIESO,
    NABIAL,
    WARZYWA,
    INNE
}



    public Product(String productName, Long productAmount,String productUnit, boolean productStatus, Category category, ShopList shopList) {
        this.productName = productName;
        this.productAmount = productAmount;
        this.productUnit = null;
        this.productStatus = false;
        this.category = category;
        this.shopList = shopList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Product product = (Product) o;
        return productId != null && Objects.equals(productId, product.productId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
