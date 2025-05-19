package com.software_enginnering.demo.controller;

import com.software_enginnering.demo.dto.OrderRequestDTO;
import com.software_enginnering.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/paymentOrder")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequestDTO dto) {
        try {
            orderService.createOrder(dto);
            return ResponseEntity.ok("주문이 정상적으로 완료되었습니다.");
        } catch (Exception e) {
            log.error("주문 저장 중 오류 발생", e);
            return ResponseEntity.status(500).body("주문 저장 중 오류 발생");
        }
    }
}
