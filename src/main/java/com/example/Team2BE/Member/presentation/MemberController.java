package com.example.Team2BE.Member.presentation;


import com.example.Team2BE.Member.domain.Member;
import com.example.Team2BE.Member.dto.request.MemberRequest;
import com.example.Team2BE.Member.dto.response.MyPageResponse;
import com.example.Team2BE.Member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    // 회원가입
    @PostMapping("/create")
    public ResponseEntity<String> createMemberRequest(@RequestBody MemberRequest request) {
        // 1️⃣ 이미 존재하는 memberId인지 확인
        if (memberService.existsByMemberId(request.getMemberId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 존재하는 아이디입니다.");
        }

        // 2️⃣ 새로운 회원 생성
        memberService.createMember(request.getMemberId(), request.getPassword(), request.getName());
        return ResponseEntity.ok("회원가입 성공!");
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<MyPageResponse> loginMemberRequest(@RequestBody MemberRequest request, HttpSession session) {
        Member member = memberService.loginMember(request.getMemberId(), request.getPassword());
        if (member != null) {
            session.setAttribute("member", member);
            MyPageResponse myPageResponse = memberService.getMyPage(request.getMemberId());
            return ResponseEntity.ok().body(myPageResponse);  // 로그인 성공 시 MyPageResponse 반환
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MyPageResponse("로그인 실패", "잘못된 아이디 또는 비밀번호")); // 로그인 실패 시 BAD_REQUEST 응답
    }


    // 로그아웃
    @GetMapping("/logout")
    public ResponseEntity<String> logoutMember(HttpSession session) {
        session.invalidate();  // 세션 무효화
        return ResponseEntity.ok("로그아웃 성공");  // 200 OK 응답 반환
    }


    // 회원탈퇴
    @DeleteMapping("/{memberId}/delete")
    public ResponseEntity<Void> deleteMemberRequest(@PathVariable(name="memberId") String memberId, @RequestBody MemberRequest request)
    {
        boolean Deleted = memberService.deleteMember(memberId, request.getPassword());
        if(Deleted) return ResponseEntity.ok().build();
        else return ResponseEntity.status(401).build();
    }

    // 비밀번호 변경
    @PostMapping("/{memberId}/password")
    public ResponseEntity<Void> updatePasswordRequest(@RequestBody MemberRequest request)
    {
        memberService.updatePassword(request.getMemberId(), request.getPassword(), request.getNewPassword());
        return ResponseEntity.ok().build();
    }

    // 정보 보기
    @GetMapping("/{memberId}")
    public ResponseEntity<MyPageResponse> getMyPageRequest(@PathVariable(name="memberId") String memberId)
    {
        MyPageResponse myPageResponse = memberService.getMyPage(memberId);
        return ResponseEntity.ok().body(myPageResponse);
    }

}
