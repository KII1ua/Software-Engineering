package com.software_enginnering.demo.service;

import com.software_enginnering.demo.domain.Kitchen;
import com.software_enginnering.demo.domain.KitchenStatus;
import com.software_enginnering.demo.domain.Menu;
import com.software_enginnering.demo.domain.Order;
import com.software_enginnering.demo.dto.OrderRequestDTO;
import com.software_enginnering.demo.repository.KitchenRepository;
import com.software_enginnering.demo.repository.MenuRepository;
import com.software_enginnering.demo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final MenuRepository menuRepository;
    private final OrderRepository orderRepository;
    private final KitchenRepository kitchenRepository;

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
    public void createOrder(OrderRequestDTO dto) {
        log.info("🚀 [OrderService] 주문 생성 시작");
        log.info("👉 전달된 DTO: {}", dto);

        Menu menu = menuRepository.findById(dto.getMenuId())
                .orElseThrow(() -> new RuntimeException("메뉴가 존재하지 않습니다."));

        log.info("메뉴 조회 성공: {}", menu.getName());

        Map<Long, Integer> quantityMap = new HashMap<>();
        quantityMap.put(dto.getMenuId(), dto.getQuantity());

        log.info("👉 수량 정보: {}", quantityMap);

        Order order = new Order(List.of(menu), dto.getAmount(), quantityMap);

        orderRepository.save(order);
        log.info("주문 생성 완료, DB에 저장됨");

        Kitchen kitchenOrder = new Kitchen(order);
        kitchenRepository.save(kitchenOrder);
    }
}
