package com.example.shopinglist1.product;

import com.example.shopinglist1.product.Product;
import com.example.shopinglist1.shopList.ShopList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findProductByProductId(long productId);

    List<Product> findProductsByShopList(ShopList shopList);

}
