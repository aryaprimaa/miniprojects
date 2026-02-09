package com.example.orderservice.inversion;

import com.example.orderservice.dto.OrderRequestDTO;
import com.example.orderservice.dto.RevenueByCategoryDTO;

import java.util.List;

public interface IOrderService {
    void createOrder(OrderRequestDTO request);
    double calculateTotalOrder(Long orderId);
    List<RevenueByCategoryDTO> getRevenueByCategory();
}
