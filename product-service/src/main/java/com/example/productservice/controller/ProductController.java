package com.example.productservice.controller;

import com.example.productservice.dto.ProductRequestDTO;
import com.example.productservice.dto.ProductResponseDTO;
import com.example.productservice.inversion.IProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final IProductService service;

    public ProductController(IProductService service) {
        this.service = service;
    }

    @PostMapping
    public ProductResponseDTO create(@RequestBody ProductRequestDTO request) {
        return service.create(request);
    }

    @GetMapping
    public List<ProductResponseDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ProductResponseDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }
}
