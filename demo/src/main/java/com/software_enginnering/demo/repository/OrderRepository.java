package com.software_enginnering.demo.repository;

import com.software_enginnering.demo.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
