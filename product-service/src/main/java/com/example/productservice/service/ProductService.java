package com.example.productservice.service;

import com.example.productservice.dto.ProductRequestDTO;
import com.example.productservice.dto.ProductResponseDTO;
import com.example.productservice.entity.Product;
import com.example.productservice.inversion.IProductService;
import com.example.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProductResponseDTO create(ProductRequestDTO request) {
        Product p = new Product();
        p.setName(request.getName());
        p.setCategory(request.getCategory());
        p.setPrice(request.getPrice());

        Product saved = repository.save(p);
        return toResponse(saved);
    }

    @Override
    public List<ProductResponseDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDTO findById(Long id) {
        Product p = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return toResponse(p);
    }

    private ProductResponseDTO toResponse(Product p) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(p.getId());
        dto.setName(p.getName());
        dto.setCategory(p.getCategory());
        dto.setPrice(p.getPrice());
        return dto;
    }
}
