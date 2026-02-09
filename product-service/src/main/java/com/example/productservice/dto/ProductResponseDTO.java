package com.example.productservice.dto;

import lombok.Data;

@Data
public class ProductResponseDTO {
    private Long id;
    private String name;
    private String category;
    private Double price;
}
