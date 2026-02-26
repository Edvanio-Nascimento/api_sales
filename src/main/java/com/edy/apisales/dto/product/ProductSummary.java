package com.edy.apisales.dto.product;

import com.edy.apisales.model.Product;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductSummary(

        String name,
        String description,
        String sku,
        BigDecimal price
) {

    public static ProductSummary fromEntiy(Product product) {
        return new ProductSummary(
                product.getName(),
                product.getDescription(),
                product.getSku(),
                product.getPrice()
        );
    }
}
