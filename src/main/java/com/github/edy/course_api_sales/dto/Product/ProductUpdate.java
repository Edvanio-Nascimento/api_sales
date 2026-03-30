package com.github.edy.course_api_sales.dto.Product;

import com.github.edy.course_api_sales.model.ProductModel;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductUpdate(

        @NotEmpty(message = "A descrição não pode ser nulo")
        @Size(min = 5, message = "A descrição deve conter no mínimo 5 caracteres")
        String description,

        @NotNull(message = "O preço é obrigatório")
        @DecimalMin(value = "0.01", message = "O preço deve ser maior que zero")
        @Digits(integer = 17, fraction = 2)
        BigDecimal price

) {

    public ProductUpdate toEntity(ProductModel model) {
        return  new ProductUpdate(
                model.getDescription(),
                model.getPrice()
        );
    }
}
