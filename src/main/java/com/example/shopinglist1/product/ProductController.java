package com.example.shopinglist1.product;

import com.example.shopinglist1.payload.response.MessageResponse;
import com.example.shopinglist1.shopList.ShopList;
import com.example.shopinglist1.shopList.ShopListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/product/new")
    public ResponseEntity<MessageResponse> postProduct(@RequestBody Product newProduct){
        return productService.addProduct(newProduct);
    }

    @GetMapping(value ="/products/{shopListId}", produces = "application/json")
    public List<Product> getProducts(@PathVariable long shopListId){
        Optional<ShopList> shopList = shopListService.getShopListById(shopListId);
        return productService.getProductsByShopList(shopList.get());

    }


     @GetMapping(value ="/product/{productId}", produces = "application/json")
    public Optional<Product> getProduct(@PathVariable("productId") long productId){
        return productService.getProductById(productId);
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
