package com.app.pickcourse.controller;

import com.app.pickcourse.service.KakaoService;
import com.app.pickcourse.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
@RequiredArgsConstructor
public class KakaoController {

    private final HttpSession session;
    private final KakaoService kakaoService;
    private final MemberService memberService;

}
