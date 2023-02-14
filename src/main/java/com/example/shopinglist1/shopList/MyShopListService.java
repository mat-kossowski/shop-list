package com.example.shopinglist1.shopList;

import com.example.shopinglist1.payload.response.MessageResponse;
import com.example.shopinglist1.product.ProductRepository;
import com.example.shopinglist1.user.User;
import com.example.shopinglist1.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MyShopListService implements ShopListService {
    private ProductRepository productRepository;
    private ShopListRepository shopListRepository;
    private UserService userService;

    @Autowired
    public MyShopListService(ProductRepository productRepository, ShopListRepository shopListRepository, UserService userService) {
        this.productRepository = productRepository;
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
        ShopList shopList = new ShopList(listName,true, user);
        shopListRepository.save(shopList);
        return ResponseEntity.ok(new MessageResponse("Add new Shop List!"));
    }

    @Override
    public List<ShopList> getShopListsUser(String userName) {
        User user = userService.getUserByUserName(userName).get();

        return shopListRepository.findShopListsByUser(user);
    }

    @Override
    public boolean getStatusSortShopList(long shopListId, String userName) {
        Optional<ShopList> shopList = shopListRepository.findShopListByShopListId(shopListId);
        if (shopList.isPresent()) {
           Optional<ShopList> updateShopList = getShopListById(shopListId, userName);
           return updateShopList.get().isAlphabetically();
        }
        return false;
    }

    @Override
    public boolean updateShopListSort(long shopListId, String userName) {
        Optional<ShopList> shopList = shopListRepository.findShopListByShopListId(shopListId);
        if (shopList.isPresent()) {
           Optional<ShopList> updateShopList = getShopListById(shopListId, userName);
           updateShopList.get().setAlphabetically(!updateShopList.get().isAlphabetically());
           shopListRepository.save(updateShopList.get());
           return true;
        }
        return false;
    }

    @Override
    public boolean deleteShopList(long shopListId) {
        Optional<ShopList> shopList = shopListRepository.findShopListByShopListId(shopListId);
        if(shopList.isPresent()){
            productRepository.deleteProductsByShopListId(shopList.get().getShopListId());
            shopListRepository.deleteShopListByShopListId(shopListId);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateShopListName(ShopList updateShopList, String userName) {
        Optional<ShopList> shopList = shopListRepository.findShopListByShopListId(updateShopList.getShopListId());
        if(shopList.isPresent()){
            ShopList updatingShopList = getShopListById(shopList.get().getShopListId(), userName).get();
            updatingShopList.setListName(updateShopList.getListName());
            shopListRepository.save(updatingShopList);
            return true;
        }

        return false;
    }


}
