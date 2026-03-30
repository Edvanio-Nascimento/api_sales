package com.github.edy.course_api_sales.dto.Product;

import com.github.edy.course_api_sales.model.ProductModel;

import java.time.LocalDateTime;

public record ProductAuditing(

        String sku,
        String name,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

) {

    public static ProductAuditing fromEntity(ProductModel model) {
        return new ProductAuditing(
                model.getSku(),
                model.getName(),
                model.getCreatedAt(),
                model.getUpdatedAt()
        );
    }
}
