package com.edy.apisales.controller;

import com.edy.apisales.dto.product.ProductCreate;
import com.edy.apisales.dto.product.ProductSummary;
import com.edy.apisales.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProductSummary> createProduct(@RequestBody @Valid ProductCreate create) {
        ProductSummary summary = service.createProduct(create);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(summary.sku())
                .toUri();

        return ResponseEntity.created(uri).body(summary);
    }
}
