package com.github.edy.course_api_sales.dto.Product;

import com.github.edy.course_api_sales.model.ProductModel;

import java.math.BigDecimal;

public record ProductDetails(

        String sku,
        String name,
        String description,
        BigDecimal price,
        Integer stock

) {

    public static ProductDetails fromEntity(ProductModel model) {
        return new ProductDetails(
                model.getSku(),
                model.getName(),
                model.getDescription(),
                model.getPrice(),
                model.getStock()
        );
    }
}
