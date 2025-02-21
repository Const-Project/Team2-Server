package com.example.Team2BE.Menu.presentation;


import com.example.Team2BE.Menu.domain.Menu;
import com.example.Team2BE.Menu.dto.request.MenuRequest;
import com.example.Team2BE.Menu.dto.response.MenuResponse;
import com.example.Team2BE.Menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    // 메뉴 가격 변경
    @PostMapping("/cost/change")
    public ResponseEntity<String> updateMenuCostRequest(@RequestBody MenuRequest request) {
        if (request.getMenu() == null || request.getMenu().isEmpty()) {
            return ResponseEntity.badRequest().body("메뉴 이름을 입력해주세요.");
        }

        if (request.getCost() <= 0) {
            return ResponseEntity.badRequest().body("유효한 가격을 입력해주세요.");
        }

        try {
            // 메뉴가 존재하는지 확인
            if (!menuService.existsByMenuName(request.getMenu())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 메뉴입니다.");
            }

            // 가격 변경
            menuService.updateMenuCost(request.getMenu(), request.getCost());
            return ResponseEntity.ok("가격 변경이 성공적으로 완료되었습니다.");
        } catch (Exception e) {
            // 예외 처리
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("서버 오류가 발생했습니다. 다시 시도해 주세요.");
        }
    }



    // 메뉴 추가
    @PostMapping("/create")
    public ResponseEntity<String> createMenuRequest(@RequestBody MenuRequest request) {
        // 메뉴 이름이 없거나 빈 문자열인 경우
        if (request.getMenu() == null || request.getMenu().isEmpty()) {
            return ResponseEntity.badRequest().body("메뉴 이름을 입력해주세요.");
        }

        // 가격이 0 이하인 경우
        if (request.getCost() <= 0) {
            return ResponseEntity.badRequest().body("유효한 가격을 입력해주세요.");
        }

        // 이미 존재하는 메뉴인지 확인
        if (menuService.existsByMenuName(request.getMenu())) {
            return ResponseEntity.badRequest().body("이미 존재하는 메뉴입니다.");
        }

        // 메뉴 추가
        menuService.createMenu(request.getMenu(), request.getCost());
        return ResponseEntity.ok("메뉴가 성공적으로 추가되었습니다.");
    }

    // 모든 메뉴 조회
    @GetMapping(value = "/list")
    public ResponseEntity<MenuResponse> getAllMenuRequest() {
        try {
            List<Menu> menus = menuService.getAllMenu();

            if (menus.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MenuResponse("메뉴가 없습니다.", null));
            }

            MenuResponse menuList = new MenuResponse("메뉴 조회 성공", menus);
            return ResponseEntity.ok().body(menuList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MenuResponse("서버 오류가 발생했습니다.", null));
        }
    }
}
