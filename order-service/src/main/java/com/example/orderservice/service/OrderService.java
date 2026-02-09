package com.example.orderservice.service;

import com.example.orderservice.dto.OrderItemRequestDTO;
import com.example.orderservice.dto.OrderRequestDTO;
import com.example.orderservice.dto.RevenueByCategoryDTO;
import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.OrderItem;
import com.example.orderservice.inversion.IOrderService;
import com.example.orderservice.repository.ReportRepository;
import org.springframework.stereotype.Service;
import com.example.orderservice.repository.OrderItemRepository;
import com.example.orderservice.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository itemRepository;
    private final ReportRepository reportRepository;

    public OrderService(OrderRepository orderRepository, OrderItemRepository itemRepository, ReportRepository reportRepository) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.reportRepository = reportRepository;

    }

    @Override
    public void createOrder(OrderRequestDTO request) {
        Order order = new Order();
        order.setStatus("CREATED");
        order.setCreatedAt(LocalDateTime.now());

        Order savedOrder = orderRepository.save(order);

        for (OrderItemRequestDTO itemDto : request.getItems()) {
            OrderItem item = new OrderItem();
            item.setOrderId(savedOrder.getId());
            item.setProductId(itemDto.getProductId());
            item.setQuantity(itemDto.getQuantity());
            item.setPrice(itemDto.getPrice());
            itemRepository.save(item);
        }
    }

    @Override
    public double calculateTotalOrder(Long orderId) {
        List<OrderItem> items = itemRepository.findByOrderId(orderId);

        return items.stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();
    }

    @Override
    public List<RevenueByCategoryDTO> getRevenueByCategory() {
        return reportRepository.getRevenueByCategory();
    }
}
