package com.example.shopinglist1.product;

import com.example.shopinglist1.payload.response.MessageResponse;
import com.example.shopinglist1.shopList.ShopList;
import com.example.shopinglist1.shopList.ShopListRepository;
import com.example.shopinglist1.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
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
        Product product = new Product(
                newProduct.getProductName(),
                newProduct.getProductAmount(),
                false,
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

    @Override
    public boolean deleteProduct(long productId) {
        Optional<Product> product = productRepository.findProductByProductId(productId);
        if (product.isPresent()) {
            productRepository.deleteProductByProductId(productId);
            return true;
        }
        return false;
    }

    @Override

    public boolean updateProductStatus(long productId) {
        Optional<Product> product = productRepository.findProductByProductId(productId);

        if (product.isPresent()) {
            Product updateProduct = getProductById(productId).get();
            updateProduct.setProductStatus(!updateProduct.isProductStatus());
            productRepository.save(updateProduct);
            return true;
        }
        return false;
    }
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


}
