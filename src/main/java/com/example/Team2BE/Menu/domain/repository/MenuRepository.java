package com.example.Team2BE.Menu.domain.repository;

import com.example.Team2BE.Menu.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long>{

    boolean existsByMenu(String menu);
}
