package com.productapi.fallback;

import com.productapi.model.ProductStatus;
import com.productapi.service.InventoryService;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class InventoryServiceFallback implements InventoryService {
    @Override
    public List<ProductStatus> getStatus(List<String> ids) {
        return Collections.emptyList();
    }
}
