package com.example.Team2BE.Menu.dto.response;

import com.example.Team2BE.Menu.domain.Menu;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MenuResponse {
    private String message;
    private List<Menu> menuList;

    public MenuResponse(String message, List<Menu> menuList) {
        this.message = message;
        this.menuList = menuList;
    }

    public MenuResponse(String message) {
        this.message = message;
        this.menuList = new ArrayList<>();
    }

    // getters and setters
}

