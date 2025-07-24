package com.bank.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.entity.ProductEntity;
import com.bank.mapper.ProductMapper;
import com.bank.model.ProductDTO;
import com.bank.service.IProductService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final IProductService productService;

    private final ProductMapper productMapper;

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDto) {
        // return ResponseEntity.ok(
        // productMapper.toDto(
        // productService.createProduct(productMapper.toEntity(productDto))));
        var savedEntity = productService.createProduct(productMapper.toEntity(productDto));
        return ResponseEntity.ok(productMapper.toDto(savedEntity));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        // return ResponseEntity.ok(
        // productMapper.toDto(productService.getProductById(id)));
        ProductEntity productEntity = productService.getProductById(id);
        return ResponseEntity.ok(productMapper.toDto(productEntity));
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        // return ResponseEntity.ok(
        // productMapper.toDtoList(productService.getAllProducts()));
        var entities = productService.getAllProducts();
        return ResponseEntity.ok(productMapper.toDtoList(entities));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDto) {
        // return ResponseEntity.ok(
        // productMapper.toDto(
        // productService.updateProduct(id, productMapper.toEntity(productDto))));
        var updatedEntity = productService.updateProduct(id, productMapper.toEntity(productDto));
        return ResponseEntity.ok(productMapper.toDto(updatedEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
