package com.example.Team2BE.Member;

import com.example.Team2BE.Member.service.MemberService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final MemberService memberService;

    public DataLoader(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public void run(String... args) throws Exception {
        memberService.createMember("hallo", "hallo", "오유준");
    }
}
