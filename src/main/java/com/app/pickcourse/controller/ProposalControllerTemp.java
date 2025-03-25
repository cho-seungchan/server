package com.app.pickcourse.controller;

import com.app.pickcourse.domain.dto.*;
import com.app.pickcourse.repository.QuestionDAO;
import com.app.pickcourse.service.AnswerService;
import com.app.pickcourse.service.CourseService;
import com.app.pickcourse.service.PlanService;
import com.app.pickcourse.util.Pagination;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/proposal")
@RequiredArgsConstructor
@Slf4j
public class ProposalControllerTemp {
    private final PlanService planService;
    private final HttpSession session;
    private final AnswerService answerService;
    private final CourseService courseService;

    @GetMapping("/eco")
    public String getEco(Model model) {

        EcoDetailDTO ecoDetail = new EcoDetailDTO();
        model.addAttribute("ecoDetail", ecoDetail);

        return "/proposal/eco";
    }

}
