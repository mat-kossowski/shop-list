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

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        String units;
        Optional<ShopList> shopList = shopListRepository.findShopListByShopListId(shopListId);
        if (newProduct.getProductUnit() == null) {
            units = null;
        } else {
            units = newProduct.getProductUnit();
        }
        Product product = new Product(
                newProduct.getProductName(),
                newProduct.getProductAmount(),
                units,
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
    public List<Product> getProductsByShopListOrderByName(ShopList shopList) {
        List<Product> products = productRepository.findProductsByShopListOrderByProductName(shopList);

        return products;
    }

    @Override
    public List<Product> getProductsByShopListOrderByNameAndCategory(ShopList shopList) {
        List<Product> products = productRepository.findProductsByShopListOrderByProductName(shopList);

        return products.stream()
                .sorted(Comparator.comparing(Product::getCategory))
                .collect(Collectors.toList());
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

    public boolean updateProduct(Product updateProduct) {
        Optional<Product> product = productRepository.findProductByProductId(updateProduct.getProductId());
        if (product.isPresent()) {
            Product updatingProduct = getProductById(product.get().getProductId()).get();
            updatingProduct.setProductName(updateProduct.getProductName());
            updatingProduct.setProductUnit(updateProduct.getProductUnit());
            updatingProduct.setProductAmount(updateProduct.getProductAmount());
            productRepository.save(updatingProduct);
            return true;
        }
        return false;
    }


}
