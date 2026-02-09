package com.example.orderservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDTO {
    private List<OrderItemRequestDTO> items;
    public List<OrderItemRequestDTO> getItems() {
        return items;
    }
    public void setItems(List<OrderItemRequestDTO> items) {
        this.items = items;
    }
}
