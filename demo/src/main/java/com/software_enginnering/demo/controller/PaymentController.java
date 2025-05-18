package com.software_enginnering.demo.controller;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import com.software_enginnering.demo.dto.PaymentRequestDTO;
import com.software_enginnering.demo.service.OrderService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class PaymentController {
    private OrderService orderService;
    private IamportClient iamportClient;

    @Value("${iamport.api-key}")
    private String apiKey;

    @Value("${iamport.api-secretkey}")
    private String apiSecretKey;

    @PostConstruct
    public void init() {
        this.iamportClient = new IamportClient(apiKey, apiSecretKey);
    }

    @PostMapping("/order/payment")
    public ResponseEntity<?> paymentComplete(@RequestBody PaymentRequestDTO dto) {
        try {
            IamportResponse<Payment> response = iamportClient.paymentByImpUid(dto.getImpUid());

            if(response.getResponse() == null) {
                return ResponseEntity.badRequest().body("결제 정보가 존재하지 않습니다.");
            }

            Payment payment = response.getResponse();
            if(!payment.getStatus().equals("paid")) {
                return ResponseEntity.badRequest().body("결제가 완료되지 않았습니다.");
            }

            orderService.createOrderFromPayment(dto.getOrderId(), payment.getAmount().intValue());

            return ResponseEntity.ok("주문이 정상적으로 생성되었습니다.");
        } catch (Exception e) {
            log.error("결제 검증 실패", e);
            return ResponseEntity.status(500).body("결제 검증 중 오류가 발생했습니다.");
        }
    }
}
