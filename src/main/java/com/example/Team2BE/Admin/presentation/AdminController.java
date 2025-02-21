package com.example.Team2BE.Admin.presentation;

import com.example.Team2BE.Admin.domain.Admin;
import com.example.Team2BE.Admin.service.AdminService;
import com.example.Team2BE.Member.domain.Member;
import com.example.Team2BE.Order.domain.Order;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/order/list")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = adminService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/order/list/sum")
    public ResponseEntity<Double> getTotalSales() {
        Double totalSales = adminService.getTotalSales();
        return ResponseEntity.ok(totalSales);
    }

    @DeleteMapping("/{memberId}/delete")
    public ResponseEntity<Void> deleteMember(@PathVariable Long memberId) {
        adminService.deleteMember(memberId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/member_list")
    public ResponseEntity<List<Member>> getAllMembers() {
        List<Member> members = adminService.getAllMembers();
        return ResponseEntity.ok(members);
    }
}
