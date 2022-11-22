package com.example.shopinglist1.controller;

import com.example.shopinglist1.model.Category;
import com.example.shopinglist1.model.Product;
import com.example.shopinglist1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://listazakupowreact.alwaysdata.net")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/product/new")
    public Product addProduct(
            @RequestBody Product product){
        return productService.addProduct(product.getProductName(),product.getProductAmount());
    }
    @GetMapping("/products")
    public List<Product> getProducts(){
        return productService.getProducts();
    }

     @GetMapping("/product/{productId}")
    public Product getProduct(
            @PathVariable("productId") int productId){
        return productService.getProduct(productId);
    }


    @PutMapping("/product/status/{productId}")
    public Product updateProductStatus(
            @RequestBody Product product
            ){
        return productService.updateProductStatus(product.getProductId(),product.isProductStatus());

    }
    @PutMapping("/product/amount/{productId}")
    public void updateProductAmount(
            @PathVariable("productId") int productId,
            @RequestParam("productAmount") String productAmount
            ){
        productService.updateProductAmount(productId,productAmount);
    }

    @DeleteMapping("/products/{productId}")
    public boolean deleteProduct(@PathVariable("productId") int productId){
        return productService.deleteById(productId);

    }
}
