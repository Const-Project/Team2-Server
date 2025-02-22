package com.example.Team2BE.Admin.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminResponse {

    private String memberId;
    private String menu;
    private Long cost;

    public AdminResponse(String memberId, String menu, Long cost) {
        this.memberId = memberId;
        this.menu = menu;
        this.cost = cost;
    }
}
