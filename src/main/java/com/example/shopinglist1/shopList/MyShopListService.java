package com.example.shopinglist1.shopList;

import com.example.shopinglist1.payload.response.MessageResponse;
import com.example.shopinglist1.user.User;
import com.example.shopinglist1.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MyShopListService implements ShopListService {

    private ShopListRepository shopListRepository;
    private UserService userService;

    @Autowired
    public MyShopListService(ShopListRepository shopListRepository, UserService userService) {
        this.shopListRepository = shopListRepository;
        this.userService = userService;
    }

    public Optional<ShopList> getShopListById(Long shopListId, String userName) {
        User user = userService.getUserByUserName(userName).get();
        if (user.getShopLists().stream().anyMatch(shopList -> shopList.getShopListId() == shopListId)) {
            return shopListRepository.findShopListByShopListId(shopListId);
        }
        return Optional.empty();

    }

    @Override
    public ResponseEntity<MessageResponse> addShopList(String listName, String userName) {
        User user = userService.getUserByUserName(userName).get();
        ShopList shopList = new ShopList(listName, user);
        shopListRepository.save(shopList);
        return ResponseEntity.ok(new MessageResponse("Add new Shop List!"));
    }

    @Override
    public List<ShopList> getShopListsUser(String userName) {
        User user = userService.getUserByUserName(userName).get();

        return shopListRepository.findShopListsByUser(user);
    }


}
