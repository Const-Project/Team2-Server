package com.example.Team2BE.Admin.service;

import com.example.Team2BE.Admin.domain.Admin;
import com.example.Team2BE.Admin.domain.repository.AdminRepository;
import com.example.Team2BE.Member.domain.Member;
import com.example.Team2BE.Member.domain.repository.MemberRepository;
import com.example.Team2BE.Order.domain.Order;
import com.example.Team2BE.Order.domain.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MemberRepository memberRepository;

    // 전체 주문 내역
    @Transactional
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        List<Member> members = memberRepository.findAll();
        for (Member member : members) {
            List<Order> allByMember = orderRepository.findAllByMember(member);
            orders.addAll(allByMember);
        }
        return orders;
    }

    // 매출 확인
    @Transactional
    public Double getTotalSales() {
        List<Order> orders = getAllOrders();
        Double totalSales = 0.0;
        for (Order order : orders) {
            totalSales += order.getCost();
        }
        return totalSales;
    }

    // 회원 강퇴
    @Transactional
    public void deleteMember(String memberId) {
        Optional<Member> member = memberRepository.findByMemberId(memberId);
        if (member.isPresent()) {
            Member m = member.get();
            memberRepository.deleteByMemberId(m.getMemberId());
        }
    }

    // 회원 목록
    @Transactional
    public List<Member> getAllMembers() {
        List<Member> members = memberRepository.findAll();
        return members;
    }

}
