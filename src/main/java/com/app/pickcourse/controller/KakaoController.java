package com.app.pickcourse.controller;

import com.app.pickcourse.domain.dto.MemberDTO;
import com.app.pickcourse.domain.vo.MemberVO;
import com.app.pickcourse.service.KakaoService;
import com.app.pickcourse.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Controller
@Slf4j
@RequiredArgsConstructor
public class KakaoController {

    private final HttpSession session;
    private final KakaoService kakaoService;
    private final MemberService memberService;

    @GetMapping("/kakao/login")
    public RedirectView login(String code){
        String token = kakaoService.getKakaoAccessToken(code);
        Optional<MemberDTO> foundInfo = kakaoService.getKakaoInfo(token);
        log.info("foundInfo:{}", foundInfo);
        log.info("token:{}", token);
        MemberDTO memberDTO = foundInfo.orElseThrow(() -> {
            throw new RuntimeException();
        });

        Optional<MemberVO> foundMember = memberService.getMember(memberDTO.getMemberEmail());
        if(!foundMember.isPresent()){
            memberService.kakaoJoin(memberDTO);
        }

        session.setAttribute("member", memberDTO);
        return new RedirectView("/");
    }
}
