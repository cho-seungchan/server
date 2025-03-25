package com.app.pickcourse.controller;

import com.app.pickcourse.domain.dto.*;
import com.app.pickcourse.repository.QuestionDAO;
import com.app.pickcourse.service.*;
import com.app.pickcourse.util.Pagination;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final CourseService courseService;
    private final ParticipantService participantService;
    private final PayService payService;
    private final MemberService memberService;
    private final RealFeedService realFeedService;

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
        MemberDTO loginMember = (MemberDTO) session.getAttribute("member");

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
        MemberDTO loginUser = (MemberDTO) session.getAttribute("member");
        if (loginUser == null) {
            return "redirect:/login/login";
        }else {
        return "/proposal/modifylist";
        }
    }

    @GetMapping("/pay")
    public String getPay(Model model, Long planId) {
        MemberDTO loginMember = (MemberDTO) session.getAttribute("member");

        if(loginMember == null) {
            return "redirect:/login/login";
        }
        PlanDTO planDTO = planService.getPlanById(planId).orElseThrow();
        planDTO.setMemberId(loginMember.getId());

        model.addAttribute("planDTO", planDTO);

        return "/proposal/pay";
    }

    @PostMapping("/addKakaoPay")
    public void addKakaoPay(@RequestBody PayDTO payDTO) {
        log.info("들어옴1");

        log.info(payDTO.toString());

        payService.addPay(payDTO);
    }

    @PutMapping("/updatePoint")
    public void update(@RequestBody MemberDTO memberDTO) {
        log.info(memberDTO.toString());
        memberService.updatePoint(memberDTO); }

    @PostMapping("/insertParticipant")
    public void insertParticipant(@RequestBody ParticipantDTO participantDTO) {
        log.info("memberId = {}", participantDTO.getMemberId());
        log.info("planId = {}", participantDTO.getPlanId());

        participantService.insertParticipant(participantDTO);
    }

    @GetMapping("/read")
    public String getRead(Model model, @RequestParam Long id) {
        MemberDTO loginUser = (MemberDTO) session.getAttribute("member");

        if(loginUser == null) {
            return "redirect:/login/login";
        }

        PlanDetailDTO planDetailDTO = planService.getPlanDetailById(id);
        planDetailDTO.setMember(loginUser.toVO());
        planDetailDTO.setFeedList(realFeedService.getRealFeedList(id));

        model.addAttribute("planDetailDTO", planDetailDTO);

        return "/proposal/read";
    }

    @GetMapping("/reviewlist")
    public String getReviewList(Model model, Long planId) {
        FeedListByPlanIdDTO feedListByPlanIdDTO = new FeedListByPlanIdDTO();

        feedListByPlanIdDTO.setPlanId(planId);
        feedListByPlanIdDTO.setFeedLists(realFeedService.getRealFeedList(planId));
        model.addAttribute("lists", feedListByPlanIdDTO);

        return "/proposal/reviewlist";
    }

    @GetMapping("/reviewlist/{planId}")
    @ResponseBody
    public PlanByFeedListDTO getReviewList(Pagination pagination, @PathVariable Long planId) {
        return realFeedService.getFeedPagination(pagination, planId);
    }

    @GetMapping("/reviewread")
    public String getReviewRead(Model model) {
        return "/proposal/reviewread";
    }

    @GetMapping("/viewlist")
    public String getVeiwList(Model model, Long courseId) {
        MemberDTO loginUser = (MemberDTO) session.getAttribute("member");


        CourseSelectDTO course = courseService.findCourseById(16L);
        model.addAttribute("course", course);
        model.addAttribute("loginMember", loginUser);

        return "/proposal/viewlist";
    }
    @PostMapping("/viewlist")
    public RedirectView check(Long courseId, RedirectAttributes redirectAttributes) {
        MemberDTO loginUser = (MemberDTO) session.getAttribute("member");
        if(loginUser == null) {
            return new RedirectView("/login/login");
        }
        redirectAttributes.addFlashAttribute("courseId", courseId);

        return new RedirectView("/proposal/write");
    }

    @GetMapping("/write")
    public String getWrite(PlanDTO planDTO, @ModelAttribute("courseId") Long courseId) {
        MemberDTO loginMember = (MemberDTO)session.getAttribute("member");
        if(loginMember == null) {
            return "redirect:/login/login";
        }
        planDTO.setCourseId(courseId);
        return "/proposal/write";
    }

    @PostMapping("/write")
    public RedirectView writePlan(PlanDTO planDTO) {
        MemberDTO loginMember = (MemberDTO)session.getAttribute("member");
        if(loginMember == null) {
            return new RedirectView("/login/login");
        }

        planDTO.setMemberId(loginMember.getId());
        planService.writePlan(planDTO);

        return new RedirectView("/proposal/modifylist");
    }

    @GetMapping("/modifylists")
    @ResponseBody
    public MyPLanListDTO getMyPlan(Pagination pagination) {
        MemberDTO loginMember = (MemberDTO) session.getAttribute("member");

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

    @GetMapping("/getAnswerLists/{questionId}")
    @ResponseBody
    public AnswerDTO getAnswerLists(@PathVariable Long questionId) {
        return answerService.getAnswerList(questionId);
    }

    @PostMapping("/modifyUpdate")
    public RedirectView modifyPlan(PlanDTO planDTO, RedirectAttributes redirectAttributes) {
        MemberDTO loginMember = (MemberDTO) session.getAttribute("member");

        planDTO.setMemberId(loginMember.getId());
        redirectAttributes.addFlashAttribute("planId", planDTO.getId());


        planService.updatePlan(planDTO);

        return new RedirectView("/proposal/modifylist");
    }

    @PutMapping("/update")
    public void updatePlan(@RequestBody PlanDTO planDTO) {
        planService.updatePlan(planDTO);
    }

    @GetMapping("/delete")
    public RedirectView deletePlan(Long id) {
        planService.deletePlan(id);
        return new RedirectView("/proposal/modifylist");
    }


}
