package com.software_enginnering.demo.service;

import com.software_enginnering.demo.domain.Kitchen;
import com.software_enginnering.demo.domain.KitchenStatus;
import com.software_enginnering.demo.repository.KitchenRepository;
import com.software_enginnering.demo.repository.MenuRepository;
import com.software_enginnering.demo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class KitchenService {
    private final OrderRepository orderRepository;
    private final KitchenRepository kitchenRepository;


    @Scheduled(fixedRate = 60000)
    public void updateKitchenStatus() {
        List<Kitchen> kitchenList = kitchenRepository.findAll();

        for(Kitchen kitchen : kitchenList) {
            LocalDateTime createdTime = kitchen.getOrder().getOrderTime();

            if(createdTime.plusMinutes(5).isBefore(LocalDateTime.now())) {
                kitchen.updateStatus();
                kitchen.getOrder().completeOrder();
                kitchenRepository.save(kitchen);            // 주방에서 받은 주문 상태 업데이트 -> 조리됨
                orderRepository.save(kitchen.getOrder());   // 실제 주문 상태 업데이트 -> 준비 완료
            }
        }
    }
}
