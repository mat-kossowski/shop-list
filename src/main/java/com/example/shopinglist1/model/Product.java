package com.example.shopinglist1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue
    private int productId;
    private String productName;
    private String productAmount;
    private boolean productStatus;
//    @Enumerated
//    private Category category;


    public Product(String productName, String productAmount) {
        this.productName = productName;
        this.productAmount = productAmount;
//        this.category = category;
        this.productStatus = false;
    }


}
