package com.example.shopinglist1.product;

import com.example.shopinglist1.shopList.ShopList;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    boolean addProduct(Product product);
    Optional<Product> getProductById(long productId);
    public List<Product> getProductsByShopList(ShopList shopList);
}
