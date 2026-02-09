package com.example.orderservice.repository;

import com.example.orderservice.dto.RevenueByCategoryDTO;
import com.example.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Order, Long> {

    @Query(value = """
        SELECT p.category AS category,
               SUM(oi.quantity * oi.price) AS totalRevenue
        FROM orders o
        JOIN order_items oi ON o.id = oi.order_id
        JOIN products p ON p.id = oi.product_id
        WHERE o.status = 'COMPLETED'
        GROUP BY p.category
        ORDER BY totalRevenue DESC
    """, nativeQuery = true)
    List<RevenueByCategoryDTO> getRevenueByCategory();
}
