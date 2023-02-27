package com.example.shopinglist1.shopList;

import com.example.shopinglist1.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ShopListRepository extends JpaRepository<ShopList, Long> {

    @Modifying
    @Query(value = "SELECT shop_list_id, alphabetically, list_name\n" +
            "FROM shop_list as s\n" +
            "    INNER JOIN  users_shop_lists as u\n" +
            "ON s.shop_list_id = u.shops_shop_list_id\n" +
            "WHERE u.shops_user_id =  :userId", nativeQuery = true)
    List<ShopList> findShopListsByUser(long userId);

    Optional<ShopList> findShopListByShopListId(long shopListId);


 @Modifying
    @Query(value = "DELETE FROM ShopList sl WHERE sl.shopListId = :shopListId")
    void deleteShopListByShopListId(long shopListId);

}

