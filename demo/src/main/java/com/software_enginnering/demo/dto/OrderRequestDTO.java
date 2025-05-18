package com.software_enginnering.demo.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderRequestDTO {
    private List<MenuOrderRequest> orders;

    @Getter
    @Setter
    public static class MenuOrderRequest {
        private Long menuId;
        private int quantity;
    }
}
