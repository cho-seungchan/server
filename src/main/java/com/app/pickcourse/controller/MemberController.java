package com.app.pickcourse.controller;

import com.app.pickcourse.domain.dto.MemberDTO;
import com.app.pickcourse.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/join/*")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("join")
    public void goToJoinForm(MemberDTO memberDTO){}

    @PostMapping("join")
    public void join(MemberDTO memberDTO){
        memberService.kakaoJoin(memberDTO);
    }

    @GetMapping("login")
    public void goToJoinForm(){}

    @GetMapping("join_check")
    public void getJoinCheck(){}

    @GetMapping("join_email")
    public void getJoinEmail(){}
}
