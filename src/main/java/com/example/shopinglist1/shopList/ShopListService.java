package com.example.shopinglist1.shopList;

import com.example.shopinglist1.payload.request.RegisterRequest;
import com.example.shopinglist1.payload.response.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface ShopListService {
//
    Optional<ShopList> getShopListById(Long id, String userName);

    ResponseEntity<MessageResponse> addShopList(String name, String userName);

    List<ShopList> getShopListsUser(String userName);
    boolean getStatusSortShopList(long shopListId, String userName);
    boolean updateShopListSort(long shopListId, String userName);

    boolean deleteShopList(long shopListId);
    boolean updateShopListName(ShopList shopList, String userName);

    ResponseEntity<MessageResponse> entrustingList(long shopListId, String userName);
}
