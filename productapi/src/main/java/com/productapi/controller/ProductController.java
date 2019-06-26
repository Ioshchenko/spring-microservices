package com.productapi.controller;

import com.productapi.model.Product;
import com.productapi.model.ProductStatistics;
import com.productapi.service.ProductService;
import com.productapi.service.ProductStatisticsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductStatisticsService productStatisticsService;

    @GetMapping("/products")
    public List<Product> getAvailableProducts(@RequestParam(required = false) String uniqId,
                                              @RequestParam(required = false) String sku) {
        log.info("Get product by id:" + uniqId + " sku:" + sku);
        productStatisticsService.increment(uniqId);
        return productService.getProducts(uniqId, sku);
    }

    @GetMapping("/stat")
    public List<ProductStatistics> getStatistics() {
        return productStatisticsService.getProductStatistics();
    }
}
