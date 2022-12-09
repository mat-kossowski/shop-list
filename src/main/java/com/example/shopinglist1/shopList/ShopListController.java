package com.example.shopinglist1.shopList;

import com.example.shopinglist1.payload.response.MessageResponse;
import com.example.shopinglist1.user.User;
import com.example.shopinglist1.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/newlist")
    public ResponseEntity<MessageResponse> newList(@CurrentSecurityContext(expression = "authentication")
                                                   Authentication authentication, @RequestBody ShopList shopList) {
        String name = authentication.getName();
        System.out.println(name);
        System.out.println(shopList);
        return shopListService.addShopList(shopList.getListName(), name);
    }
}
