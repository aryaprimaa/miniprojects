package com.example.productservice.inversion;


import com.example.productservice.dto.ProductRequestDTO;
import com.example.productservice.dto.ProductResponseDTO;

import java.util.List;

public interface IProductService {
    ProductResponseDTO create(ProductRequestDTO request);
    List<ProductResponseDTO> findAll();
    ProductResponseDTO findById(Long id);
}
