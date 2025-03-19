package com.app.pickcourse.controller;

import com.app.pickcourse.domain.dto.*;
import com.app.pickcourse.domain.vo.AnswerVO;
import com.app.pickcourse.domain.vo.MemberVO;
import com.app.pickcourse.repository.QuestionDAO;
import com.app.pickcourse.service.AnswerService;
import com.app.pickcourse.service.PlanService;
import com.app.pickcourse.util.Pagination;
import com.app.pickcourse.util.QuestionPagination;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.runtime.Runtime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/proposal")
@RequiredArgsConstructor
@Slf4j
public class ProposalController {
    private final PlanService planService;
    private final HttpSession session;
    private final QuestionDAO questionDAO;
    private final AnswerService answerService;

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
    public String getModify(Model model, Long id) {
        MemberVO loginMember = (MemberVO) session.getAttribute("member");

        if(loginMember == null) {
            return "redirect:/login/login";
        }else{
            PlanDTO planDTO = planService.getPlanById(id).orElseThrow(()->new RuntimeException());

            model.addAttribute("planDTO", planDTO);
            model.addAttribute("loginMember", loginMember);

            return "/proposal/modify";
        }
    }

    @GetMapping("/modifylist")
    public String getModifyList(Model model) {
        MemberVO loginUser = (MemberVO) session.getAttribute("member");
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
        MemberVO loginUser = (MemberVO) session.getAttribute("member");

        PlanDetailDTO planDetailDTO = planService.getPlanDetailById(id);
        planDetailDTO.setMember(loginUser);

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
        MemberVO loginMember = (MemberVO)session.getAttribute("member");

        log.info("planDTO: {}", planDTO.toString());
        planDTO.setMemberId(loginMember.getId());
        planDTO.setCourseId(21L);
        planService.writePlan(planDTO);

        return new RedirectView("/proposal/modifylist");
    }

    @GetMapping("/modifylists")
    @ResponseBody
    public MyPLanListDTO getMyPlan(Pagination pagination) {
        MemberVO loginMember = (MemberVO) session.getAttribute("member");

        Long id = loginMember.getId();
        return planService.getMyPlanList(pagination, id);
    }

    @PostMapping("/writeQuestion")
    public void writeQuestion(@RequestBody QuestionDTO questionDTO) {
        planService.writeQuestion(questionDTO);
    }

    @GetMapping("/read/{planId}")
    @ResponseBody
    public QuestionListDTO getLists(@PathVariable Long planId) {
        return planService.findQuestionLists(planId);
    }

    @PostMapping("/writeAnswer")
    public void writeAnswer(@RequestBody AnswerDTO answerDTO) {
        answerService.answerAdd(answerDTO);
    }

    @GetMapping("/getAnswerLists/{planId}/{questionId}")
    @ResponseBody
    public AnswerDTO getAnswerLists(@PathVariable Long planId, @PathVariable Long questionId) {
        return answerService.getAnswerList(planId, questionId);
    }

    @PostMapping("/modifyUpdate")
    public RedirectView modifyPlan(PlanDTO planDTO, RedirectAttributes redirectAttributes) {
        MemberVO loginMember = (MemberVO) session.getAttribute("member");

        planDTO.setMemberId(loginMember.getId());
        redirectAttributes.addFlashAttribute("planId", planDTO.getId());

        log.info("planDTO: {}", planDTO);

        planService.updatePlan(planDTO);

        return new RedirectView("/proposal/modifylist");
    }

    @PutMapping("/update")
    public void updatePlan(@RequestBody PlanDTO planDTO) {
        planService.updatePlan(planDTO);
    }


}
