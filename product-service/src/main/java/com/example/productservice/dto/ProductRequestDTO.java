package com.example.productservice.dto;

import lombok.Data;

@Data
public class ProductRequestDTO {
    private String name;
    private String category;
    private Double price;
}
