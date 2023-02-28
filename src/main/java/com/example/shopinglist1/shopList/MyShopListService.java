package com.example.shopinglist1.shopList;

import com.example.shopinglist1.payload.response.MessageResponse;
import com.example.shopinglist1.product.ProductRepository;
import com.example.shopinglist1.user.User;
import com.example.shopinglist1.user.UserRepository;
import com.example.shopinglist1.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class MyShopListService implements ShopListService {
    private ProductRepository productRepository;
    private ShopListRepository shopListRepository;
    private UserService userService;
    private UserRepository userRepository;



    @Autowired

    public MyShopListService(ProductRepository productRepository, ShopListRepository shopListRepository, UserService userService, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.shopListRepository = shopListRepository;
        this.userService = userService;
        this.userRepository = userRepository;

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
        Set<ShopList> shopLists = new HashSet<>();
        User user = userService.getUserByUserName(userName).get();
        ShopList shopList = new ShopList(listName, true);

        user.addShopList(shopList);
        shopListRepository.save(shopList);

        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("Add new Shop List!"));
    }

    @Override
    public ResponseEntity<MessageResponse> entrustingList(long shopListId, String userName) {
        System.out.println("111111111111111111111111111111111111111111111111111111111111111111111");
        Optional<User> user = userService.getUserByUserName(userName);
        if (user.isEmpty()) {
             System.out.println("2222222222222222222222222222222222222222222222222222222222222222222222222222222");
            return ResponseEntity.ok(new MessageResponse("User no exist"));
        } else {
            Optional<ShopList> shopList = shopListRepository.findShopListByShopListId(shopListId);
            if (shopList.isPresent()) {
                 System.out.println("444444444444444444444444444444444444444444444444444444444444444444444444");
                user.get().addShopList(shopList.get());
                shopListRepository.save(shopList.get());
                userRepository.save(user.get());
                return ResponseEntity.ok(new MessageResponse("Add user new Shop List!"));
            }else {
                 System.out.println("555555555555555555555555555555555555555555555555555555555555555555555555555555555");
                return ResponseEntity.ok(new MessageResponse("User no exist"));
            }
        }
    }

    @Override
    public List<ShopList> getShopListsUser(String userName) {
        User user = userService.getUserByUserName(userName).get();
        System.out.println("PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP");

        return shopListRepository.findShopListsByUser(user.getUserId());
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
        if (shopList.isPresent()) {
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
