package com.software_enginnering.demo.service;

import com.software_enginnering.demo.domain.Menu;
import com.software_enginnering.demo.domain.Order;
import com.software_enginnering.demo.dto.OrderRequestDTO;
import com.software_enginnering.demo.repository.MenuRepository;
import com.software_enginnering.demo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final MenuRepository menuRepository;
    private OrderRepository orderRepository;

    @Transactional
    public Order createOrderFromPayment(Long orderId, int amount) {
        Map<Long, Integer> quantityMap = new HashMap<>();

        int totalPrice = amount;

        List<Menu> menuItems = menuRepository.findAllById(List.of(orderId));

        menuItems.forEach(menu -> quantityMap.put(menu.getId(), 1));

        Order order = new Order(menuItems, totalPrice, quantityMap);

        return orderRepository.save(order);
    }

    @Transactional
    public ResponseEntity<Order> createOrder(OrderRequestDTO dto) {
        Map<Long, Integer> quantityMap = new HashMap<>();
        AtomicInteger totalPrice = new AtomicInteger(0);

        List<Menu> menuItems = dto.getOrders()
                .stream()
                .map(order -> {
                    Menu menu = menuRepository.findById(order.getMenuId())
                            .orElseThrow(() -> new IllegalArgumentException("메뉴가 존재하지 않습니다."));
                    quantityMap.put(order.getMenuId(), order.getQuantity());

                    int totalpr = menu.getPrice() * order.getQuantity();
                    totalPrice.addAndGet(totalpr);

                    return menu;
                })
                .collect(Collectors.toList());

        Order order = new Order(menuItems, totalPrice.get(), quantityMap);

        orderRepository.save(order);

        return ResponseEntity.ok(order);
    }
}
