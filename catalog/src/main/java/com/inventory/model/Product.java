package com.inventory.model;


import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class Product {
    @CsvBindByName
    private String uniq_id;
    @CsvBindByName
    private String sku;
    @CsvBindByName
    private String name_title;
    @CsvBindByName
    private String description;
    @CsvBindByName
    private String list_price;
    @CsvBindByName
    private String sale_price;
    @CsvBindByName
    private String category;
    @CsvBindByName
    private String category_tree;
    @CsvBindByName
    private String average_product_rating;
    @CsvBindByName
    private String product_url;
    @CsvBindByName
    private String product_image_urls;
    @CsvBindByName
    private String brand;
    @CsvBindByName
    private String total_number_reviews;
    @CsvBindByName
    private String reviews;

}
