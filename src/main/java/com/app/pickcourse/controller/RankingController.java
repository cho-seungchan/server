// 2025.02.24 조승찬
package com.app.pickcourse.controller;

import com.app.pickcourse.domain.dto.MemberDTO;
import com.app.pickcourse.domain.dto.RankingContainerDTO;
import com.app.pickcourse.service.RankingService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/ranking")
@RequiredArgsConstructor
@Slf4j
public class RankingController {
    private final RankingService rankingService;

    // 주간, 월간, 년간 랭킹  25.03.22  조승찬
    @GetMapping("/ranking")
    public String getRanking(@SessionAttribute(name = "member", required = false) MemberDTO member,
                             Model model) {

        Long memberId = null;
        if (member != null) {  // 찜 여부를 가져올 id
            memberId = member.getId();
        }

        RankingContainerDTO rankings = rankingService.getRanking(memberId);

        rankings.getWeekRanking().forEach(System.out::println);
        model.addAttribute("rankings", rankings);

        return "/ranking/ranking";
    }

    @PostMapping("/wish")
    @ResponseBody
    public void postWish(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                   @RequestParam Long planId){


        Long memberId = member.getId();
        rankingService.postWish(planId, memberId);
    };

    @PutMapping("/wish")
    @ResponseBody
    public void deleteWish(@SessionAttribute(name = "member", required = false) MemberDTO member,
                         @RequestParam Long planId){


        Long memberId = member.getId();
        rankingService.deleteWish(planId, memberId);
    };

}
