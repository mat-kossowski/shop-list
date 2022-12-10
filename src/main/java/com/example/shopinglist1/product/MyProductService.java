package com.example.shopinglist1.product;

import com.example.shopinglist1.payload.response.MessageResponse;
import com.example.shopinglist1.shopList.ShopList;
import com.example.shopinglist1.shopList.ShopListRepository;
import com.example.shopinglist1.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MyProductService implements ProductService {
    private ProductRepository productRepository;
    private UserService userService;
    private ShopListRepository shopListRepository;

    @Autowired
    public MyProductService(ProductRepository productRepository, UserService userService, ShopListRepository shopListRepository) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.shopListRepository = shopListRepository;
    }

    @Override
    public ResponseEntity<MessageResponse> addProduct(Product newProduct, Long shopListId) {

        Optional<ShopList> shopList = shopListRepository.findShopListByShopListId(shopListId);
        Product product = new Product(newProduct.getProductName(),
                newProduct.getProductAmount(),
                newProduct.isProductStatus(),
                newProduct.getCategory(),
                shopList.get()
        );

        productRepository.save(product);
        return ResponseEntity.ok(new MessageResponse("Product Add!"));
    }

    @Override
    public Optional<Product> getProductById(long productId) {
        return productRepository.findProductByProductId(productId);
    }

    @Override
    public List<Product> getProductsByShopList(ShopList shopList) {
        return productRepository.findProductsByShopList(shopList);
    }
//    public List<Product> getProducts() {
//        return productRepository.findAll(Sort.by(Sort.Direction.ASC, "productStatus", "productName"));
//    }
//
//    public Product getProduct(long productId) {
//        return productRepository.findById(productId).get();
//    }
//
//
//    public Optional<Product> getProductById(long productId) {
//        return productRepository.findById(productId);
//    }
//
//    public Product updateProductStatus(long productId, boolean productStatus) {
//        if (getProductById(productId).isPresent()) {
//            Product updateProduct = getProductById(productId).get();
//            updateProduct.setProductStatus(productStatus);
//            productRepository.save(updateProduct);
//            return updateProduct;
//        }
//        return null;
//    }
//
//    public void updateProductAmount(long productId, String productAmount) {
//        if (getProductById(productId).isPresent()) {
//            Product updateProduct = getProductById(productId).get();
//            updateProduct.setProductAmount(productAmount);
//            productRepository.save(updateProduct);
//
//        }
//    }
//
//    public boolean deleteById(long productId) {
//        if (getProductById(productId).isPresent()) {
//            productRepository.deleteById(productId);
//            return true;
//        }
//        return false;
//    }


}
