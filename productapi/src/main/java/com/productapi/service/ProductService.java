package com.productapi.service;

import com.productapi.model.Product;
import com.productapi.model.ProductStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private CatalogService catalogService;

    @Autowired
    private InventoryService inventoryService;

    public List<Product> getProducts(String uniqId, String sku) {
        Set<Product> products = new HashSet<>(catalogService.searchProducts(sku));
        Product product = catalogService.getProductById(uniqId);
        if (product != null) {
            products.add(product);
        }

        Map<String, Boolean> productStatus = getProductStatus(products);

         return products.stream()
                .filter(p -> productStatus.getOrDefault(p.getUniq_id(), false))
                .collect(Collectors.toList());

    }

    private Map<String, Boolean> getProductStatus(Set<Product> products) {
        List<String> ids = products.stream()
                .map(Product::getUniq_id)
                .collect(Collectors.toList());

        return inventoryService.getStatus(ids).stream()
                .collect(Collectors.toMap(ProductStatus::getUniq_id, ProductStatus::isAvailable));
    }
}