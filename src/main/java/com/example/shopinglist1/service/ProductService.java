package com.example.shopinglist1.service;

import com.example.shopinglist1.model.Category;
import com.example.shopinglist1.model.Product;
import com.example.shopinglist1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(String productName, String productAmount) {
        return productRepository.save(new Product(productName, productAmount));
    }

    public List<Product> getProducts() {
        return productRepository.findAll(Sort.by(Sort.Direction.ASC, "productStatus", "productName"));
    }

    public Product getProduct(int productId) {
        return productRepository.findById(productId).get();
    }


    public Optional<Product> getProductById(int productId) {
        return productRepository.findById(productId);
    }

    public Product updateProductStatus(int productId, boolean productStatus) {
        if (getProductById(productId).isPresent()) {
            Product updateProduct = getProductById(productId).get();
            updateProduct.setProductStatus(productStatus);
            productRepository.save(updateProduct);
            return updateProduct;
        }
        return null;
    }

    public void updateProductAmount(int productId, String productAmount) {
        if (getProductById(productId).isPresent()) {
            Product updateProduct = getProductById(productId).get();
            updateProduct.setProductAmount(productAmount);
            productRepository.save(updateProduct);

        }
    }

    public boolean deleteById(int productId) {
        if (getProductById(productId).isPresent()) {
            productRepository.deleteById(productId);
            return true;
        }
        return false;
    }


}
