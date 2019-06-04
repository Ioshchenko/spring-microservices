package com.inventory.controller;

import com.inventory.model.ProductStatus;
import com.inventory.service.ProductService;
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

    @GetMapping("/status")
    public List<ProductStatus> getStatus(@RequestParam List<String> ids) {
        log.info("Get status: " + ids);
        return productService.getProductStatus(ids);
    }


}
