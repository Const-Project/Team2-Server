package com.example.Team2BE.Member.service;

import com.example.Team2BE.Member.domain.Member;
import com.example.Team2BE.Member.domain.repository.MemberRepository;
import com.example.Team2BE.Member.dto.response.MyPageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 회원 가입
    public boolean createMember(String memberId, String rawPassword, String name) {
        // 이미 존재하는 아이디인지 체크
        if (memberRepository.existsByMemberId(memberId)) {
            return false; // 이미 존재하는 회원이면 가입 불가
        }

        // 비밀번호 암호화 후 저장
        String encryptedPassword = passwordEncoder.encode(rawPassword);
        Member member = new Member(memberId, encryptedPassword, name);
        memberRepository.save(member);
        return true;
    }

    // memberId가 이미 존재하는지 확인하는 메서드
    public boolean existsByMemberId(String memberId) {
        return memberRepository.existsByMemberId(memberId);
    }

    @Transactional
    public Member loginMember(String memberId, String rawPassword)
    {
        Optional<Member> member = memberRepository.findByMemberId(memberId);
        if(member.isPresent()) {
            Member m = member.get();
            if (passwordEncoder.matches(rawPassword, m.getPassword()))
            {
                return m;
            }
        }
        return null;
    }

    // 회원탈퇴
    @Transactional
    public boolean deleteMember(String memberId, String password) {
        Optional<Member> member = memberRepository.findByMemberId(memberId);
        if (member.isPresent()) {
            memberRepository.delete(member.get());
            return true;
        }
        return false; // 삭제 실패 (존재하지 않는 회원)
    }

    // 비밀번호 변경 (암호화 적용)
    public boolean updatePassword(String memberId, String oldPassword, String newPassword) {
        Optional<Member> member = memberRepository.findByMemberId(memberId);
        if (member.isPresent()) {
            Member m = member.get();
            // 기존 비밀번호 검증
            if (passwordEncoder.matches(oldPassword, m.getPassword())) {
                m.setPassword(passwordEncoder.encode(newPassword)); // 새 비밀번호 암호화 후 저장
                memberRepository.save(m);
                return true;
            }
        }
        return false; // 비밀번호 변경 실패
    }

    // 정보 보기
    public MyPageResponse getMyPage(String memberId) {
        Optional<Member> member = memberRepository.findByMemberId(memberId);
        return member.map(MyPageResponse::from).orElse(null);
    }

}
