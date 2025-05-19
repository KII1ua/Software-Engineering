package com.software_enginnering.demo.service;

import com.software_enginnering.demo.domain.Kitchen;
import com.software_enginnering.demo.domain.Menu;
import com.software_enginnering.demo.domain.Order;
import com.software_enginnering.demo.dto.OrderRequestDTO;
import com.software_enginnering.demo.repository.KitchenRepository;
import com.software_enginnering.demo.repository.MenuRepository;
import com.software_enginnering.demo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final MenuRepository menuRepository;
    private final OrderRepository orderRepository;
    private final KitchenRepository kitchenRepository;

    @Transactional
    public void createOrder(OrderRequestDTO dto) {
        Menu menu = menuRepository.findById(dto.getMenuId())
                .orElseThrow(() -> new RuntimeException("메뉴가 존재하지 않습니다."));

        int totalPrice = menu.getPrice() * dto.getQuantity();

        Order order = new Order(menu, dto.getAmount(), totalPrice);

        orderRepository.save(order);

        Kitchen kitchenOrder = new Kitchen(order);

        kitchenRepository.save(kitchenOrder);
    }
}
