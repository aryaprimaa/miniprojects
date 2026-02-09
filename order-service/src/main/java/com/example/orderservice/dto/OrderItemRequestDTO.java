package com.example.orderservice.dto;

import lombok.Data;

@Data
public class OrderItemRequestDTO {
    private Long productId;
    private Integer quantity;
    private Double price;
}
