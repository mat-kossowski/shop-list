package com.example.shopinglist1.product;

import com.example.shopinglist1.product.Product;
import com.example.shopinglist1.shopList.ShopList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findProductByProductId(Long productId);

    List<Product> findProductsByShopList(ShopList shopList);

@Modifying
    @Query(value = "DELETE FROM Product p WHERE p.productId = :productId")
    void deleteProductByProductId(Long productId);

}
