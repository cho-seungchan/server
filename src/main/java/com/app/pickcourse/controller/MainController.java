package com.app.pickcourse.controller;

import com.app.pickcourse.domain.dto.MainDTO;
import com.app.pickcourse.service.MainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {
    private final MainService mainService;

    @GetMapping("/")
    public String mainPage(Model model){
        MainDTO mainDTO = mainService.getCourses();
        model.addAttribute("mainDTO", mainDTO);
        return "index";
    }
}
