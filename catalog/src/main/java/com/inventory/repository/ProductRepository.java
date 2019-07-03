package com.inventory.repository;

import com.inventory.model.Product;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.List;

@Repository
@Slf4j
public class ProductRepository {

    public List<Product> getProducts() {
        try {
            InputStream inputStream = new ClassPathResource("products.csv").getInputStream();

            return new CsvToBeanBuilder<Product>(new InputStreamReader(inputStream))
                    .withType(Product.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build()
                    .parse();
        } catch (IOException e) {
            log.error("Problem load products from file", e);
            throw new IllegalArgumentException("Error load products");
        }

    }
}
