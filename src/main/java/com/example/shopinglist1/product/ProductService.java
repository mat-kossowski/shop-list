package com.example.shopinglist1.product;

import com.example.shopinglist1.payload.response.MessageResponse;
import com.example.shopinglist1.shopList.ShopList;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    ResponseEntity<MessageResponse> addProduct(Product product, Long shopListId);
    Optional<Product> getProductById(long productId);
    public List<Product> getProductsByShopListOrderByNameAndCategory(ShopList shopList);
    public List<Product> getProductsByShopListOrderByName(ShopList shopList);

    boolean deleteProduct(long productId);
    boolean  updateProductStatus(long productId);
    boolean updateProduct(Product updateProduct);
}
