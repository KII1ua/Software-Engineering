package com.software_enginnering.demo.service;


import com.software_enginnering.demo.domain.Menu;
import com.software_enginnering.demo.dto.FindByAllMenu;
import com.software_enginnering.demo.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuService {
    private final MenuRepository menuRepository;


    public ResponseEntity<List<FindByAllMenu>> findByAllMenus() {
        List<Menu> menus = menuRepository.findAll();

        List<FindByAllMenu> menuDTO = menus.stream()
                .map(menu -> new FindByAllMenu(menu.getId(), menu.getName(), menu.getPrice()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(menuDTO);
    }
}
