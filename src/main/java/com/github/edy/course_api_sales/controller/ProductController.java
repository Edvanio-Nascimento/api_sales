package com.github.edy.course_api_sales.controller;

import com.github.edy.course_api_sales.dto.Product.*;
import com.github.edy.course_api_sales.model.ProductModel;
import com.github.edy.course_api_sales.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductSummary> save(@RequestBody @Valid ProductCreate create){
        ProductSummary summary = productService.saveProduct(create);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{sku}")
                .buildAndExpand(summary.sku())
                .toUri();

        return ResponseEntity.created(location).body(summary);
    }

    @GetMapping
    public ResponseEntity<List<ProductSummary>> getAll(){
        return ResponseEntity.ok(productService.findAllProducts());
    }

    @GetMapping("/{sku}")
    public ResponseEntity<ProductDetails> get(@PathVariable String sku){
        return ResponseEntity.ok(productService.findProductBySku(sku));
    }

    @GetMapping("/auditing/{sku}")
    public ResponseEntity<ProductAuditing>  getAuditing(@PathVariable String sku){
        return ResponseEntity.ok(productService.auditingProduct(sku));
    }

    @PutMapping("/{sku}")
    public ResponseEntity<ProductSummary> update(@PathVariable String sku, @RequestBody @Valid ProductUpdate update){
        return ResponseEntity.ok(productService.updateProduct(sku, update));
    }

    @DeleteMapping("/{sku}")
    public ResponseEntity<Void> delete(@PathVariable String sku){
        productService.deleteProduct(sku);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/noActive")
    public ResponseEntity<List<ProductSummary>> noActiveProduct(){
        return ResponseEntity.ok(productService.getProductNoActive());
    }
}
