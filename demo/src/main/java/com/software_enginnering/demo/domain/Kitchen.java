package com.software_enginnering.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Kitchen {
    @Id @GeneratedValue
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    private KitchenStatus kitchenStatus;

    public Kitchen(Order order) {
        this.order = order;
        this.kitchenStatus = KitchenStatus.Waiting;
    }

    public void updateStatus() {
        this.kitchenStatus = KitchenStatus.Ready;
    }
}
