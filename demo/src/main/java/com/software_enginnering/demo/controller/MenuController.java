package com.software_enginnering.demo.controller;

import com.software_enginnering.demo.dto.FindByAllMenu;
import com.software_enginnering.demo.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MenuController {
    private final MenuService menuService;

    @GetMapping("/findByAllMenu")
    public ResponseEntity<List<FindByAllMenu>> findByAllMenu() {
        return menuService.findByAllMenus();
    }
}
