package com.productapi.service;

import com.productapi.model.Product;
import com.productapi.fallback.CatalogServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "catalog-service", fallback = CatalogServiceFallback.class)
public interface CatalogService {

    @RequestMapping(method = RequestMethod.GET, value = "/product")
    Product getProductById(@RequestParam String id);

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    List<Product> searchProducts(@RequestParam String sku);
}
