package com.example.shopinglist1.shopList;

import com.example.shopinglist1.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShopListRepository extends JpaRepository<ShopList, Long> {

    Optional<ShopList> findShopListByShopListId(long shopListId);
    List<ShopList> findShopListsByUser(User user);


    @Modifying
    @Query(value = "DELETE FROM ShopList sl WHERE sl.shopListId = :shopListId")
    void deleteShopListByShopListId(long shopListId);


}
