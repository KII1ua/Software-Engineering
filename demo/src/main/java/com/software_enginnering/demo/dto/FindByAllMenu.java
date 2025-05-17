package com.software_enginnering.demo.dto;

import com.software_enginnering.demo.domain.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindByAllMenu {
    private String menuName;
    private int price;

    public FindByAllMenu(Menu menu) {
        this.menuName = menu.getName();
        this.price = menu.getPrice();
    }
}
