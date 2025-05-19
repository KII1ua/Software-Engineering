package com.software_enginnering.demo.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderRequestDTO {
    private String impUid;
    private String merchantUid;
    private int amount;
    private Long menuId;
    private int quantity;
}
