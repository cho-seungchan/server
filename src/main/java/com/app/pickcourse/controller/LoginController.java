package com.app.pickcourse.controller;

import com.app.pickcourse.domain.dto.MemberDTO;
import com.app.pickcourse.domain.vo.AdminVO;
import com.app.pickcourse.domain.vo.MemberVO;
import com.app.pickcourse.service.AdminService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String login(@ModelAttribute MemberDTO memberDTO, RedirectAttributes redirectAttributes) {
        memberDTO.setMemberEmail(memberDTO.getMemberEmail());
        memberDTO.setMemberPassword(memberDTO.getMemberPassword());

        Optional<MemberDTO> optionalMember = memberService.login(memberDTO);

        if (optionalMember.isEmpty() ||
                optionalMember.get().getMemberPassword() == null ||
                !optionalMember.get().getMemberPassword().equals(memberDTO.getMemberPassword())) {

            redirectAttributes.addFlashAttribute("error", "이메일 또는 비밀번호가 올바르지 않습니다.");
            return "redirect:/login/login";
        }

        MemberDTO member = optionalMember.get();
        session.setAttribute("memberStatus", "email");
        session.setAttribute("member", member);

        return "redirect:/";
    }


    @GetMapping("updatePasswordLogin")
    public void updatePasswordLogin(@ModelAttribute MemberDTO memberDTO) {}


}
