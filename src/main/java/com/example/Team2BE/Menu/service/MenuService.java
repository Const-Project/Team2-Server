package com.example.Team2BE.Menu.service;


import com.example.Team2BE.Member.domain.Member;
import com.example.Team2BE.Menu.domain.Menu;
import com.example.Team2BE.Menu.domain.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Transactional
    public void updateMenuCost(String menuName, Long newCost)
    {
        Optional<Menu> tmpMenu = menuRepository
                .findAll()
                .stream()
                .filter(menu -> menu.getMenu().equals(menuName))
                .findAny();

        if(tmpMenu.isPresent())
        {
            Menu m = tmpMenu.get();
            m.setCost(newCost);
        }
    }

    @Transactional
    public void createMenu(String menuName, Long cost)
    {
        Menu menu = new Menu(menuName, cost);
        menuRepository.save(menu);
    }

    @Transactional
    public boolean existsByMenuName(String menuName) {
        // 메뉴 이름으로 데이터베이스에서 메뉴를 찾는 로직을 작성
        return menuRepository.existsByMenu(menuName);  // 예시, 실제로 DB에서 확인하는 방법에 맞게 구현
    }


    @Transactional
    public List<Menu> getAllMenu()
    {
        List<Menu> allMenu = menuRepository.findAll();
        return allMenu;
    }
}
