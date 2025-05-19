package com.software_enginnering.demo.domain;

import jakarta.persistence.*;
import jdk.jfr.Unsigned;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "orders")
public class Order {            // 주문 상태 -> 어떤 메뉴를 선택했고 현재 어떤 상태인지
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    private int quantities;

    private LocalDateTime orderTime;

    private int totalPrice;

    @Enumerated
    private OrderStatus status;

    public Order(Menu menu, int totalPrice, int quantities) {
        this.menu = menu;
        this.totalPrice = totalPrice;
        this.quantities = quantities;
        this.orderTime = LocalDateTime.now();
        this.status = OrderStatus.PAID;
    }

    public void completeOrder() {
        this.status = OrderStatus.Completed;
    }
}
