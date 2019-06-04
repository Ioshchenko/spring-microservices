package com.inventory.service;

import com.inventory.model.Product;
import com.inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private List<Product> products;
    @Autowired
    private ProductRepository productRepository;

    @PostConstruct
    public void initProducts() {
        products = productRepository.getProducts();
    }

    public Product getProductById(String productId) {
        return products.stream()
                .filter(p -> p.getUniq_id().equals(productId))
                .findFirst()
                .orElse(new Product());
    }

    public List<Product> searchProducts(String sku) {
        return products.stream()
                .filter(p -> p.getSku().equals(sku))
                .collect(Collectors.toList());
    }
}
