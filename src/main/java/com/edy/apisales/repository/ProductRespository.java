package com.edy.apisales.repository;

import com.edy.apisales.model.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRespository extends JpaRepository<Product, UUID> {

    Optional<Product> findBySku(String sku);

    boolean existsBySku(@NotBlank(message = "SKU é necessário")
                        @Size(max = 30, message = "SKU deve conter no máximo 30 caracteres")
                        String sku);
}
