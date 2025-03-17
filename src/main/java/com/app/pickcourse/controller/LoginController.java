package com.app.pickcourse.controller;

import com.app.pickcourse.domain.dto.MemberDTO;
import com.app.pickcourse.domain.vo.MemberVO;
import com.app.pickcourse.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/login/*")
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    private final MemberService memberService;
    private final MemberVO memberVO;
    private final HttpSession session;

    @GetMapping("login")
    public String goToLoginForm(Model model) {
        model.addAttribute("memberDTO", new MemberDTO());
        return "login/login";
    }

    @PostMapping("login")
    public String login(@ModelAttribute MemberDTO memberDTO) {

        MemberVO memberVO = new MemberVO();
        memberVO.setMemberEmail(memberDTO.getMemberEmail());
        memberVO.setMemberPassword(memberDTO.getMemberPassword());

        Optional<MemberVO> optionalMember = memberService.login(memberVO);

        if (optionalMember.isEmpty()) {
            return "redirect:/login/login";
        }

        MemberVO member = optionalMember.get();

        if (member.getMemberPassword() == null ||
                !member.getMemberPassword().equals(memberDTO.getMemberPassword())) {
            return "redirect:/login/login";
        }
        session.setAttribute("memberStatus", "email");
        session.setAttribute("member", member);
        return "redirect:/";
    }

    @GetMapping("adminLogin")
    public String goToAdminLoginForm(Model model) {
        return "login/adminLogin";
    }




}
