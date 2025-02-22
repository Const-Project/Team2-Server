package com.example.Team2BE.Admin.presentation;

import com.example.Team2BE.Admin.service.AdminService;
import com.example.Team2BE.Member.domain.Member;
import com.example.Team2BE.Order.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    // 모든 주문 조회
    @GetMapping("/order/list")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = adminService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    // 전체 수익 조회
    @GetMapping("/order/list/sum")
    public ResponseEntity<Double> getTotalSales() {
        Double totalSales = adminService.getTotalSales();
        return ResponseEntity.ok(totalSales);
    }

    // 특정 멤버 삭제
    @DeleteMapping("/{memberId}/delete")
    public ResponseEntity<Void> deleteMember(@PathVariable String memberId) {
        adminService.deleteMember(memberId);
        return ResponseEntity.ok().build();
    }

    // 모든 멤버 조회
    @GetMapping("/member_list")
    public ResponseEntity<List<Member>> getAllMembers() {
        List<Member> members = adminService.getAllMembers();
        return ResponseEntity.ok(members);
    }
}
