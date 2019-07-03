package com.productapi.service;

import com.productapi.model.ProductStatistics;
import com.productapi.repository.ProductStatisticsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProductStatisticsService {
    @Autowired
    private ProductStatisticsRepository productStatisticsRepository;

    public void increment(String productId) {
        ProductStatistics productStatistics = productStatisticsRepository.findById(productId)
                .orElse(new ProductStatistics(productId, 0));
        productStatistics.setCount(productStatistics.getCount() + 1);
        productStatisticsRepository.save(productStatistics);

        log.info("Incremented product: " + productStatistics);
    }

    public List<ProductStatistics> getProductStatistics() {
        return productStatisticsRepository.findAll();
    }
}
