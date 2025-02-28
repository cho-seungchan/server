package com.app.admin.controller;

import com.app.admin.dto.CourseDTO;
import com.app.admin.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {
    private final CourseService courseService;

    @GetMapping("/admin")
    public String getAdmin(Model model) {
        return "/admin/admin";
    }

    @GetMapping("/userlist")
    public String getUserList(Model model) {
        return "/admin/userlist";
    }

    @GetMapping("/addcourse")
    public String getAddCourse(Model model) {
        return "/admin/addcourse";
    }


    @GetMapping("/coursewrite")
    public String getCourseWrite(Model model) {
        model.addAttribute("courseDTO", new CourseDTO());
        return "/admin/coursewrite";
    }

    @PostMapping("/coursewrite")
    public String postCourseWrite(@ModelAttribute("coursetDTO") CourseDTO courseDTO, RedirectAttributes redirectAttributes) {
        courseService.postCourseWrite(courseDTO);  // 관리자 아이디 처리 추가
        return "redirect:/admin/coursewrite";   // 성공시 리스트로 이동할 수 있도록 추가할 것.
    }

}
