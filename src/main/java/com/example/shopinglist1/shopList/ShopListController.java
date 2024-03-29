package com.example.shopinglist1.shopList;

import com.example.shopinglist1.payload.response.MessageResponse;
import com.example.shopinglist1.product.Product;
import com.example.shopinglist1.user.User;
import com.example.shopinglist1.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/shoplist")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
public class ShopListController {

    @Autowired
    private ShopListService shopListService;

    private UserService userService;

    @Autowired
    public ShopListController(ShopListService shopListService, UserService userService) {
        this.shopListService = shopListService;
        this.userService = userService;
    }


    @PostMapping()
    public ResponseEntity<MessageResponse> newList(@CurrentSecurityContext(expression = "authentication")
                                                   Authentication authentication, @RequestBody ShopList shopList) {
        String name = authentication.getName();
        return shopListService.addShopList(shopList.getListName(), name);
    }

    @GetMapping()
    public List<ShopList> getAllShowList(@CurrentSecurityContext(expression = "authentication")
                                         Authentication authentication) {
        String name = authentication.getName();
        return shopListService.getShopListsUser(name);
    }

    @GetMapping("/{shopListId}")
    public Optional<ShopList> getShopList(@PathVariable Long shopListId, @CurrentSecurityContext(expression = "authentication")
    Authentication authentication) {
        String name = authentication.getName();
        return shopListService.getShopListById(shopListId, name);
    }

    @PutMapping("/sort/{shopListId}")
    public ResponseEntity<ShopList> updateShopListSort(@PathVariable Long shopListId, @CurrentSecurityContext(expression = "authentication")
    Authentication authentication) {
        String name = authentication.getName();
        shopListService.updateShopListSort(shopListId, name);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/sort/{shopListId}")
    public boolean getStatusSort(@PathVariable Long shopListId, @CurrentSecurityContext(expression = "authentication")
    Authentication authentication) {
        String name = authentication.getName();
        return shopListService.getStatusSortShopList(shopListId, name);
    }

    @DeleteMapping("/{shopListId}")
    public ResponseEntity<ShopList> deleteShopList(@PathVariable("shopListId") Long shopListId) {
        System.out.println(shopListId);
        shopListService.deleteShopList(shopListId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping(value = "/update", produces = "application/json")
    public ResponseEntity<MessageResponse> updateShopListName(
            @RequestBody ShopList shopList, @CurrentSecurityContext(expression = "authentication")
    Authentication authentication) {
        String name = authentication.getName();
        shopListService.updateShopListName(shopList, name);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/entrusting/{shopListId}", produces = "application/json")
    public ResponseEntity<MessageResponse> entrustingShopList(
            @PathVariable("shopListId") Long shopListId,
            @RequestBody User user) {
        return shopListService.entrustingList(shopListId, user.getUserName());
    }


}
