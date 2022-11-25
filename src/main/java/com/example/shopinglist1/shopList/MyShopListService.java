package com.example.shopinglist1.shopList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyShopListService implements ShopListService {

    private ShopListRepository shopListRepository;

    @Autowired
    public MyShopListService(ShopListRepository shopListRepository) {
        this.shopListRepository = shopListRepository;
    }

    public Optional<ShopList> getShopListById(long id){
        return shopListRepository.findShopListByShopListId(id);
    }
}
