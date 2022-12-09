package com.example.shopinglist1.shopList;

import com.example.shopinglist1.payload.response.MessageResponse;
import com.example.shopinglist1.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public interface ShopListService {

    Optional<ShopList> getShopListById(long id);

    ResponseEntity<MessageResponse> addShopList(String name, String userName);


}
