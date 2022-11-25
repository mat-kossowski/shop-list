package com.example.shopinglist1.shopList;

import java.util.Optional;

public interface ShopListService {

    Optional<ShopList> getShopListById(long id);
}
