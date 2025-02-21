package com.example.Team2BE.Menu.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String menu;

    @Column
    private Long cost;

    public Menu(String menu, Long cost) {
        this.menu = menu;
        this.cost = cost;
    }
}
