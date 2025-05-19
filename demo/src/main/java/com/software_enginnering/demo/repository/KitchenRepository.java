package com.software_enginnering.demo.repository;

import com.software_enginnering.demo.domain.Kitchen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KitchenRepository extends JpaRepository<Kitchen, Long> {
}
