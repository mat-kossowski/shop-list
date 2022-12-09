package com.example.shopinglist1.shopList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShopListRepository extends JpaRepository<ShopList, Long> {

    Optional<ShopList> findShopListByShopListId(long shopListId);

}
