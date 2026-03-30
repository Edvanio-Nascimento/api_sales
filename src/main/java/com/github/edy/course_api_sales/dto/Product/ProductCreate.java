package com.github.edy.course_api_sales.dto.Product;

import com.github.edy.course_api_sales.model.ProductModel;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductCreate(

        @NotEmpty (message = "O SKU não pode ser nulo")
        @Size(min = 5, max = 30, message = "O SKU deve conter entre 5 e 30 caracteres")
        String sku,

        @NotEmpty(message = "O nome não pode ser nulo")
        @Size(min = 5, max = 100, message = "O nome deve conter entre 5 e 100 caracteres")
        String name,

        @NotEmpty(message = "A descrição não pode ser nulo")
        @Size(min = 5, message = "A descrição deve conter no mínimo 5 caracteres")
        String description,

        @NotNull(message = "O preço é obrigatório")
        @DecimalMin(value = "0.01", message = "O preço deve ser maior que zero")
        @Digits(integer = 17, fraction = 2)
         BigDecimal price

) {

    public ProductModel toEntity() {
        ProductModel model = new ProductModel();

        model.setSku(sku.toUpperCase());
        model.setName(name);
        model.setDescription(description);
        model.setPrice(price);


        return model;
    }
}
