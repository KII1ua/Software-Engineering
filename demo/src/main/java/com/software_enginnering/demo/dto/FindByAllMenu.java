package com.software_enginnering.demo.dto;

import com.software_enginnering.demo.domain.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindByAllMenu {
    private Long id;
    private String menuName;
    private int price;

    public FindByAllMenu(Menu menu) {
        this.id = menu.getId();
        this.menuName = menu.getName();
        this.price = menu.getPrice();
    }
}
