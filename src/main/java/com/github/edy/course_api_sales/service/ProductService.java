package com.github.edy.course_api_sales.service;

import com.github.edy.course_api_sales.dto.Product.*;
import com.github.edy.course_api_sales.exception.DuplicateResourceException;
import com.github.edy.course_api_sales.model.ProductModel;
import com.github.edy.course_api_sales.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    private  final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public ProductSummary saveProduct(ProductCreate create) {
        ProductModel model = create.toEntity();

        if (productRepository.existsBySku(model.getSku())) {
            throw new DuplicateResourceException("SKU já existe");
        }

        ProductModel savedProduct = productRepository.save(model);

        return ProductSummary.fromEntity(savedProduct);
    }

    @Transactional(readOnly = true)
    public List<ProductSummary> findAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductSummary::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public ProductDetails findProductBySku(String sku) {
        ProductModel model = productRepository.findBySku(sku)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        return ProductDetails.fromEntity(model);
    }

    @Transactional(readOnly = true)
    public ProductAuditing auditingProduct(String sku) {
        ProductModel model = productRepository.findBySku(sku)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        return ProductAuditing.fromEntity(model);
    }

    @Transactional
    public ProductSummary updateProduct(String sku, ProductUpdate update) {
        ProductModel model = productRepository.findBySku(sku)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        if (update.description() != null) {
            model.setDescription(update.description());
        }

        if (update.price() != null) {
            model.setPrice(update.price());
        }

        return ProductSummary.fromEntity(model);
    }

    @Transactional
    public void deleteProduct(String sku) {
        ProductModel model = productRepository.findBySku(sku)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        model.setActive(false);
        productRepository.save(model);
    }

    @Transactional(readOnly = true)
    public List<ProductSummary> getProductNoActive() {
        List<ProductModel> inactiveProducts = productRepository.findAllInactive();

        return inactiveProducts.stream().map(ProductSummary::fromEntity).toList();

    }
}
