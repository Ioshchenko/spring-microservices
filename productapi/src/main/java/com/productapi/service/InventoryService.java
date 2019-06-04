package com.productapi.service;

import com.productapi.fallback.InventoryServiceFallback;
import com.productapi.model.ProductStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "inventory-service", fallback = InventoryServiceFallback.class)
public interface InventoryService {

    @RequestMapping(method = RequestMethod.GET, value = "/status")
    List<ProductStatus> getStatus(@RequestParam List<String> ids);
}
