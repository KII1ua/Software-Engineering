package com.software_enginnering.demo.repository;

import com.software_enginnering.demo.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {

}
