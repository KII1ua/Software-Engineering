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

    @ManyToMany
    @JoinTable(
            name = "order_menu",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_id")
    )
    private List<Menu> menuItems = new ArrayList<>();           // 어떤 주문이 여러 메뉴가 있을 수 있기 때문에 조인 테이블 생성

    @ElementCollection
    @CollectionTable(name = "order_quantities", joinColumns = @JoinColumn(name = "order_id"))
    @MapKeyColumn(name = "menu_id")
    private Map<Long, Integer> quantities;          // 메뉴 Id, 개수 매핑하기 위함

    private LocalDateTime orderTime;

    private int totalPrice;

    @Enumerated
    private OrderStatus status;

    public Order(List<Menu> menuItems, int totalPrice, Map<Long, Integer> quantities) {
        this.menuItems = menuItems;
        this.orderTime = LocalDateTime.now();
        this.totalPrice = totalPrice;
        this.status = OrderStatus.PAID;
        this.quantities = quantities;
    }

    public void completeOrder() {
        this.status = OrderStatus.Completed;
    }
}
