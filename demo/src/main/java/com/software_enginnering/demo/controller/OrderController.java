package com.software_enginnering.demo.controller;

import com.software_enginnering.demo.domain.Order;
import com.software_enginnering.demo.dto.OrderRequestDTO;
import com.software_enginnering.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderController {
    private final OrderService orderService;

    public ResponseEntity<Order> createOrder(OrderRequestDTO dto) {
        return orderService.createOrder(dto);
    }
}
