package com.example.Team2BE.Admin.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminRequest {

    private String memberId;

    private String menu;

    private Long cost;
}
