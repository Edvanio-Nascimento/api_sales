package com.github.edy.course_api_sales.repository;

import com.github.edy.course_api_sales.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductModel, UUID> {

    Optional<ProductModel> findById(UUID id);

    boolean existsById(UUID id);
}
