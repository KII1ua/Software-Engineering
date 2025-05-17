package com.software_enginnering.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Payment {
    @Id @GeneratedValue
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    private int totalPrice;

    public Payment(Order order, int totalPrice) {
        this.order = order;
        this.totalPrice = totalPrice;
    }


}
