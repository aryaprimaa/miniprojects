package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderRequestDTO;
import com.example.orderservice.dto.RevenueByCategoryDTO;
import com.example.orderservice.inversion.IOrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class OrderController {

    private final IOrderService service;

    public OrderController(IOrderService service) {
        this.service = service;
    }

    @PostMapping
    public String create(@RequestBody OrderRequestDTO request) {
        service.createOrder(request);
        return "Order Created";
    }

    @GetMapping("/{id}/total")
    public double getTotal(@PathVariable Long id) {
        return service.calculateTotalOrder(id);
    }

    @GetMapping("/reports/revenue-by-category")
    public List<RevenueByCategoryDTO> revenueByCategory() {
        return service.getRevenueByCategory();
    }
}
