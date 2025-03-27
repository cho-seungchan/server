package com.app.pickcourse.controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/logout/*")
@Slf4j
public class LogoutController {

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        if (session.getAttribute("member") != null) {
            session.invalidate();
            log.info("일반 사용자 로그아웃 완료");
        } else {
            log.warn("로그아웃 실패: 로그인된 일반 사용자가 없음");
        }
        return "redirect:/login/login";
    }


    @PostMapping("/kakao")
    public String kakaoLogout(HttpSession session) {
        if (session.getAttribute("member") != null) {
            session.invalidate();
            log.info("카카오 사용자 로그아웃 완료");

            String kakaoLogoutUrl = "https://kauth.kakao.com/oauth/logout?"
<<<<<<< HEAD
                    + "client_id=" + "1f0eec6d921e399c7940b9689a7896e3"
                    + "&logout_redirect_uri=" + "http://52.78.155.144:10000/login/login";
=======
                    + "client_id=" + "fae1d8d22225221e075546a8f1f4ac4d"
                    + "&logout_redirect_uri=" + "http://localhost:10000/login/login";
>>>>>>> devel

            return "redirect:" + kakaoLogoutUrl;
        } else {
            log.warn("카카오 로그아웃 실패: 로그인된 카카오 사용자가 없음");
            return "redirect:/login/login";
        }
    }


}


