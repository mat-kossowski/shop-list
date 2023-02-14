package com.example.shopinglist1.product;

import com.example.shopinglist1.payload.response.MessageResponse;
import com.example.shopinglist1.shopList.ShopList;
import com.example.shopinglist1.shopList.ShopListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
public class ProductController {

    private ProductService productService;
    private ShopListService shopListService;


    @Autowired
    public ProductController(ProductService productService, ShopListService shopListService) {
        this.productService = productService;
        this.shopListService = shopListService;
    }



    @GetMapping(value = "/category/{shopListId}", produces = "application/json")
    public List<Product> getProductsCategory(@PathVariable long shopListId, @CurrentSecurityContext(expression = "authentication")
    Authentication authentication) {
        String name = authentication.getName();
        Optional<ShopList> shopList = shopListService.getShopListById(shopListId, name);
//        List<Product> products = productService.getProductsByShopListOrderByNameAndCategory(shopList.get());
//        System.out.println(products);
        return productService.getProductsByShopListOrderByNameAndCategory(shopList.get());

    }
    @GetMapping(value = "/alphabet/{shopListId}", produces = "application/json")
    public List<Product> getProductsAlphabet(@PathVariable long shopListId, @CurrentSecurityContext(expression = "authentication")
    Authentication authentication) {
        String name = authentication.getName();
        Optional<ShopList> shopList = shopListService.getShopListById(shopListId, name);
//        List<Product> products = productService.getProductsByShopListOrderByName(shopList.get());
//        System.out.println(products);
        return productService.getProductsByShopListOrderByName(shopList.get());

    }


    @GetMapping(value = "/{shopListId}/{productId}", produces = "application/json")
    public Optional<Product> getProduct(@PathVariable("productId") long productId,
                                        @PathVariable("shopListId") long shopListId) {
        return productService.getProductById(productId);
    }

    @PostMapping(value = "/{shopListId}", produces = "application/json")
    public ResponseEntity<MessageResponse> addProduct(
            @RequestBody Product product, @PathVariable long shopListId) {
        System.out.println(product);
        return productService.addProduct(product, shopListId);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Product> deleteProduct (@PathVariable("productId") Long productId) {
        System.out.println(productId);
        productService.deleteProduct(productId);

         return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


    @PutMapping("/status/{productId}")
    public ResponseEntity<Product> updateProductStatus(@PathVariable("productId") Long productId){
        System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT");
        System.out.println(productId);
        productService.updateProductStatus(productId);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PutMapping(value = "/update", produces = "application/json")
    public ResponseEntity<MessageResponse> updateProduct(
            @RequestBody Product product) {
        System.out.println(product);

        productService.updateProduct(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }
//    @PutMapping("/product/amount/{productId}")
//    public void updateProductAmount(
//            @PathVariable("productId") long productId,
//            @RequestParam("productAmount") String productAmount
//            ){
//        productService.updateProductAmount(productId,productAmount);
//    }


}
