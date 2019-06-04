package com.productapi.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductStatus {
    private String uniq_id;
    private boolean available;

}
