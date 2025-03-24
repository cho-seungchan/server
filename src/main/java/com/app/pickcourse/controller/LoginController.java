package com.app.pickcourse.controller;

import com.app.pickcourse.domain.dto.MemberDTO;
import com.app.pickcourse.domain.vo.MemberVO;
import com.app.pickcourse.service.EmailService;
import com.app.pickcourse.service.MemberService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    private final HttpServletRequest request;
    private final EmailService emailService;

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

        String redirectUrl = (String) session.getAttribute("redirectAfterLogin");
        if (redirectUrl != null) {
            session.removeAttribute("redirectAfterLogin");
            return "redirect:" + redirectUrl;
        }

        return "redirect:/";
    }


    @GetMapping("updatePasswordLogin")
    public void updatePasswordLogin(@ModelAttribute MemberDTO memberDTO) {}

//    비밀번호 변경을 위해 이메일 입력하는 페이지
    @GetMapping("find-password")
    public String showPasswordForm(){
        return "login/findPassword";
    }

//    입력한 이메일 주소로 이메일 발송
    @PostMapping("find-password")
    public String sendPasswordEmail(String email, RedirectAttributes redirectAttributes, HttpServletResponse response) throws MessagingException {
        Optional<MemberDTO> foundMember = memberService.getMember(email);
        if (foundMember.isEmpty()) {
            // 이메일로 회원 조회 실패 시
            redirectAttributes.addFlashAttribute("error", "해당 이메일로 회원을 찾을 수 없습니다.");
            return "redirect:/login/find-password";
        }
        // 이메일로 회원 조회 성공 시
        emailService.sendPasswordEmail(email, response);
        redirectAttributes.addFlashAttribute("success", "입력한 이메일로 비밀번호 찾기 링크를 발송했습니다.");
        return "redirect:/login/find-password";
    }

//    이메일로 받은 토큰 대조 후 일치하면 비밀번호 변경 페이지로
    @GetMapping("confirm")
    public String confirm(@CookieValue(name = "token", required = false) String token,
                                 @RequestParam String code,
                                 RedirectAttributes redirectAttributes, HttpServletResponse response, HttpSession session) {
        // 토큰이 만료되었을 때(유효기간이 지났을 때)
        if (token == null || token.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "초대 링크가 만료되었습니다.");
            return "redirect:/login/find-password";
        } 
        // 발급받은 토큰이 동일하다면 쿠키 초기화
        if(token.equals(code)) {
            Cookie cookie = new Cookie("token", "");
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);

            // 메일을 발송할 때 세션에 저장되어있던 이메일 가져오기
            String memberEmail = (String)session.getAttribute("email");
            // 가져온 이메일로 회원 전체 정보 조회 후 저장
            Optional<MemberDTO> foundMember = memberService.getMember(memberEmail);

            // 이메일로 조회되는 회원이 없을 경우
            if (foundMember.isEmpty()) {
                log.info("조회된 회원 없음.");
                redirectAttributes.addFlashAttribute("message", "조회된 회원이 없습니다. 다시 시도해주세요");
                return "redirect:/login/find-password";
                // 회원이 조회되는 경우 세션에서 이메일은 지우고 전체 회원 정보 저장
            } else {
                MemberDTO member = foundMember.get();
                session.removeAttribute("email");
                session.setAttribute("member", member);
                log.info("세션에 저장된 멤버정보: {}", session.getAttribute("member"));
                session.setAttribute("message", "토큰 일치. 재설정할 비밀번호를 입력해주세요.");
                log.info("세션에 저장된 메시지: {}", session.getAttribute("message"));
                return "redirect:/login/change-password";
            }
        }
        return "redirect:/login/change-password";
    }

    @GetMapping("change-password")
    public String changePassword(Model model, HttpSession session) {
        String message = (String) session.getAttribute("message");
        log.info("받은 메시지: " + message);
        if (message != null) {
            model.addAttribute("message", message);
            session.removeAttribute("message");  // 메시지 삭제
        }
        return "login/changeLostPassword";
    }
}
