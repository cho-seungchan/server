package com.app.admin.controller;

import com.app.admin.VO.CourseVO;
import com.app.admin.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/course")
@RequiredArgsConstructor
@Slf4j
public class CourseController {
    private final CourseService courseService;

    @GetMapping("/write")
    public String write(Model model) {
        model.addAttribute("courseVO", new CourseVO());
        return "/admin/course/write";
    }
}
