package com.edy.apisales.service;

import com.edy.apisales.dto.product.ProductCreate;
import com.edy.apisales.dto.product.ProductSummary;
import com.edy.apisales.exception.DuplicateResourceException;
import com.edy.apisales.model.Product;
import com.edy.apisales.repository.ProductRespository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private final ProductRespository respository;

    public ProductService(ProductRespository respository) {
        this.respository = respository;
    }

    @Transactional(readOnly = true)
    public ProductSummary createProduct(ProductCreate create) {
        try {
            Product product = create.toEntity();
            Product savedProduct = respository.save(product);

            return ProductSummary.fromEntiy(savedProduct);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateResourceException("SKU já existente");
        }
    }
}
