package com.example.shopinglist1.product;

import com.example.shopinglist1.payload.response.MessageResponse;
import com.example.shopinglist1.shopList.ShopList;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface ProductService {

    ResponseEntity<MessageResponse> addProduct(Product product);
    Optional<Product> getProductById(long productId);
    public List<Product> getProductsByShopList(ShopList shopList);
}
