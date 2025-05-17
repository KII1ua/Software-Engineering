package com.software_enginnering.demo.config;

import com.software_enginnering.demo.domain.Menu;
import com.software_enginnering.demo.repository.MenuRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MenuInitializer implements ApplicationRunner {
    private final MenuRepository menuRepository;

    public MenuInitializer(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(menuRepository.count() == 0) {
            List<Menu> menus = List.of(
                    new Menu("아메리카노", 3000),
                    new Menu("꿀아메리카노", 3300),
                    new Menu("콜드브루", 3500),
                    new Menu("카푸치노", 3500),
                    new Menu("카페라떼", 4000),
                    new Menu("바닐라라떼", 4500)
            );
            menuRepository.saveAll(menus);
        }
    }
}
