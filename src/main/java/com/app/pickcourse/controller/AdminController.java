package com.app.pickcourse.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    // 관리자 메인 페이지
    @GetMapping("/admin")
    public String getAdmin(Model model) {
        return "/admin/admin";
    }

    // 회원 관리 :: 회원 정지, 추방
    @GetMapping("/memberlist")
    public String getMemberList(Model model) {
        return "/admin/memberlist";
    }

    @GetMapping("/userlist")
    public String getUserList(Model model) {
        return "/admin/userlist";
    }

    @GetMapping("/courseedit")
    public String getCourseEdit(Model model) {
        return "/admin/courseedit";
    }

    // 관리자 관리 :: 관리자 등록, 추방
    @GetMapping("/manageadmin")
    public String getManageAdmin(Model model) {
        return "/admin/manageadmin";
    }

    // 코스 등록 화면
    @GetMapping("/addcourse")
    public String getAddCourse(Model model) {
        return "/admin/addcourse";
    }

    // 추천 코스 목록
    @GetMapping("/courselist")
    public String getCourseList(Model model) {
        return "/admin/courselist";
    }

    // 추천 코스 조회
    @GetMapping("/coursedetail")
    public String getCourseDetail(Model model) {
        return "/admin/coursedetail";
    }

    // 추천 코스 수정 화면
    @GetMapping("/editcourse")
    public String getEditCourse(Model model) {
        return "/admin/editcourse";
    }

    // 공지사항 등록
    @GetMapping("/addnotice")
    public String getAddNotice(Model model) {
        return "/admin/addnotice";
    }

    // 공지사항 수정 삭제
    @GetMapping("/managenotice")
    public String getAnnouncement(Model model) {
        return "/admin/managenotice";
    }

    // 신고 관리 :: 피드 신고, 댓글 신고
    @GetMapping("/report-list")
    public String getReportList(Model model) {
        return "/admin/report";
    }

}
