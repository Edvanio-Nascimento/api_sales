package com.github.edy.course_api_sales.dto.Product;

import com.github.edy.course_api_sales.model.ProductModel;

import java.math.BigDecimal;

public record ProductSummary(

        String sku,
        String name,
        BigDecimal price

) {

    public static ProductSummary fromEntity(ProductModel model) {
        return new ProductSummary(
                model.getSku(),
                model.getName(),
                model.getPrice()
        );
    }
}
