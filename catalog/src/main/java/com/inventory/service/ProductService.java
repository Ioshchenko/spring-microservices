package com.inventory.service;

import com.inventory.model.Product;
import com.inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private List<Product> products;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private KafkaTemplate<String, List<String>> kafkaTemplate;
    @Resource(name = "topic")
    private String topic;


    @PostConstruct
    public void initProducts() {
        products = productRepository.getProducts();
    }

    public Product getProductById(String productId) {
        kafkaTemplate.send(topic, Arrays.asList(productId));

        return products.stream()
                .filter(p -> p.getUniq_id().equals(productId))
                .findFirst()
                .orElse(new Product());
    }

    public List<Product> searchProducts(String sku) {
        List<Product> products = this.products.stream()
                .filter(p -> p.getSku().equals(sku))
                .collect(Collectors.toList());

        List<String> ids = products.stream()
                .map(Product::getUniq_id)
                .collect(Collectors.toList());
        kafkaTemplate.send(topic, ids);
        return products;
    }
}
