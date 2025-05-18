package com.software_enginnering.demo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class PaymentRequestDTO {
    private String impUid;
    private String merchantUid;
    private Long orderId;
}
