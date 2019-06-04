package com.inventory.service;

import com.inventory.model.ProductStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private ConcurrentHashMap<String, ProductStatus> products = new ConcurrentHashMap<>();

    public List<ProductStatus> getProductStatus(List<String> productIds) {
        return productIds.stream()
                .map(id -> products.computeIfAbsent(id, k -> new ProductStatus(k, new Random().nextBoolean())))
                .collect(Collectors.toList());
    }
}
