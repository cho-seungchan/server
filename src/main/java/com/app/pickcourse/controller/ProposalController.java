package com.app.pickcourse.controller;

import com.app.pickcourse.domain.dto.MyPLanListDTO;
import com.app.pickcourse.domain.dto.PlanDTO;
import com.app.pickcourse.domain.dto.PlanDetailDTO;
import com.app.pickcourse.domain.dto.QuestionDTO;
import com.app.pickcourse.domain.vo.MemberVO;
import com.app.pickcourse.service.PlanService;
import com.app.pickcourse.util.Pagination;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/proposal")
@RequiredArgsConstructor
@Slf4j
public class ProposalController {
    private final PlanService planService;
    private final HttpSession session;

    @GetMapping("/eco")
    public String getEco(Model model) {
        return "/proposal/eco";
    }

    @GetMapping("/list")
    public String getList(Model model) {
        return "/proposal/list";
    }

    @GetMapping("/mobile")
    public String getMobile(Model model) {
        return "/proposal/mobile";
    }

    @GetMapping("/modify")
    public String getModify(Model model) {
        return "/proposal/modify";
    }

    @GetMapping("/modifylist")
    public String getModifyList(Model model) {
        MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/login/login";
        }else {
        return "/proposal/modifylist";
        }
    }

    @GetMapping("/pay")
    public String getPay(Model model) {
        return "/proposal/pay";
    }

    @GetMapping("/read")
    public String getRead(Model model, Long id) {
        MemberVO loginUser = (MemberVO) session.getAttribute("getMember");

        log.info("id = {}", id);
        PlanDetailDTO planDetailDTO = planService.getPlanDetailById(id);
        planDetailDTO.setMember(loginUser);
        log.info("Read plan: {}", planDetailDTO);

        model.addAttribute("planDetailDTO", planDetailDTO);

        return "/proposal/read";
    }

    @GetMapping("/reviewlist")
    public String getReviewList(Model model) {
        return "/proposal/reviewlist";
    }

    @GetMapping("/reviewread")
    public String getReviewRead(Model model) {
        return "/proposal/reviewread";
    }

    @GetMapping("/viewlist")
    public String getVeiwList(Model model) {
        return "/proposal/viewlist";
    }

    @GetMapping("/write")
    public String getWrite(PlanDTO planDTO) {
        return "/proposal/write";
    }

    @PostMapping("/write")
    public RedirectView writePlan(PlanDTO planDTO) {
        MemberVO loginMember = (MemberVO)session.getAttribute("getMember");

        log.info("planDTO: {}", planDTO.toString());
        planDTO.setMemberId(loginMember.getId());
        planDTO.setCourseId(21L);
        planService.writePlan(planDTO);

        return new RedirectView("/proposal/modifylist");
    }

    @GetMapping("/modifylists")
    @ResponseBody
    public MyPLanListDTO getMyPlan(Pagination pagination) {
        MemberVO loginMember = (MemberVO) session.getAttribute("loginUser");

        Long id = loginMember.getId();
        return planService.getMyPlanList(pagination, id);
    }

    @PostMapping("/writeQuestion")
    public void writeQuestion(@RequestBody QuestionDTO questionDTO) {
        planService.writeQuestion(questionDTO);
    }

}
