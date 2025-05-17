package com.software_enginnering.demo.domain;

import jakarta.persistence.*;
import jdk.jfr.Unsigned;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "orders")
public class Order {            // 주문 상태 -> 어떤 메뉴를 선택했고 현재 어떤 상태인지
    @Id @GeneratedValue
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "order_menu",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_id")
    )
    private List<Menu> menuItems = new ArrayList<>();

    private LocalDateTime orderTime;

    @Enumerated
    private OrderStatus status;

    public Order(List<Menu> menuItems) {
        this.menuItems = menuItems;
        this.orderTime = LocalDateTime.now();
        this.status = OrderStatus.PAID;
    }

    public void completeOrder() {
        this.status = OrderStatus.Completed;
    }
}
