package com.software_enginnering.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Menu {         // 메뉴 ex) 아메리카노, 카푸치노 등
    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;

    public Menu(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
