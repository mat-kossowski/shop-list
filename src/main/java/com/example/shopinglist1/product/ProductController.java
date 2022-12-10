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

//    @PostMapping()
//    public ResponseEntity<MessageResponse> postProduct(@RequestBody Product newProduct) {
//        return productService.addProduct(newProduct);
//    }

    @GetMapping(value = "/{shopListId}", produces = "application/json")
    public List<Product> getProducts(@PathVariable long shopListId) {
        Optional<ShopList> shopList = shopListService.getShopListById(shopListId);
        return productService.getProductsByShopList(shopList.get());

    }


    @GetMapping(value = "/{shopListId}/{productId}", produces = "application/json")
    public Optional<Product> getProduct(@PathVariable("productId") Long productId,
                                        @PathVariable("shopListId") Long shopListId) {
        return productService.getProductById(productId);
    }

    @PostMapping(value = "/{shopListId}/newProduct", produces = "application/json")
    public ResponseEntity<MessageResponse> addProduct(
            @RequestBody Product product, @PathVariable Long shopListId) {

        return productService.addProduct(product, shopListId);
    }


//    @PutMapping("/product/status/{productId}")
//    public Product updateProductStatus(
//            @RequestBody Product product
//            ){
//        return productService.updateProductStatus(Math.toIntExact(product.getProductId()),product.isProductStatus());
//
//    }
//    @PutMapping("/product/amount/{productId}")
//    public void updateProductAmount(
//            @PathVariable("productId") long productId,
//            @RequestParam("productAmount") String productAmount
//            ){
//        productService.updateProductAmount(productId,productAmount);
//    }

//    @DeleteMapping("/products/{productId}")
//    public boolean deleteProduct(@PathVariable("productId") long productId){
//        return productService.deleteById(productId);
//
//    }
}
