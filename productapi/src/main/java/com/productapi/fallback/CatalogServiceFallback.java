package com.productapi.fallback;

import com.productapi.model.Product;
import com.productapi.service.CatalogService;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class CatalogServiceFallback implements CatalogService {
    @Override
    public Product getProductById(String id) {
        return new Product();
    }

    @Override
    public List<Product> searchProducts(String sku) {
        return Collections.emptyList();
    }
}
