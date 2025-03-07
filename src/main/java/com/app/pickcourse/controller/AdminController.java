package com.app.pickcourse.controller;


import com.app.pickcourse.domain.vo.AdminVO;
import com.app.pickcourse.domain.vo.MemberVO;
import com.app.pickcourse.exception.DuplicateException;
import com.app.pickcourse.service.AdminService;
import com.app.pickcourse.util.Pagination;
import com.app.pickcourse.util.Search;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {


    private final AdminService adminService;

    // 관리자 메인 페이지
    @GetMapping("/admin")
    public String getAdmin(Model model) {
        return "/admin/admin";
    }

//    // 회원 관리 목록 조회
//    @GetMapping("/member-list")
//    public String getMemberList(Pagination pagination, Search search, Model model) {
//
//        List<MemberVO> members = adminService.getMemberList(pagination, search);
//        model.addAttribute("members", members);
//        return "/admin/member-list";
//    }
//
//    // 회원 정지
//    @PostMapping("/member-list-pause")
//    public String patchMemberListpause(@RequestParam("selectedIds") String selectedIds,
//                                       @RequestParam("page") String page,
//                                       @RequestParam("type") String type,
//                                       @RequestParam("keyWord") String keyWord,
//                                       @RequestParam("isAct") String isAct,
//                                       RedirectAttributes redirectAttributes) {
//        adminService.patchMemberListPause(selectedIds);
//        return "redirect:/admin/member-list?page=" +page+"&type="+type+"&keyWord="+keyWord+"&isAct="+isAct;
//    }
//
//    // 회원 정지 해제
//    @PostMapping("/member-list-restart")
//    public RedirectView patchMemberListRestart(@RequestParam("selectedIds") String selectedIds,
//                                               @RequestParam("page") String page,
//                                               @RequestParam("type") String type,
//                                               @RequestParam("keyWord") String keyWord,
//                                               @RequestParam("isAct") String isAct,
//                                               RedirectAttributes redirectAttributes) {
//        adminService.patchMemberListRestart(selectedIds);
//        return new RedirectView("redirect:/admin/member-list?page=" +page+"&type="+type+"&keyWord="+keyWord+"&isAct="+isAct);
//    }
//
//    // 회원 추방
//    @PostMapping("/member-list-delete")
//    public String deleteMemberList(@RequestParam("selectedIds") String selectedIds,
//                                   @RequestParam("page") String page,
//                                   @RequestParam("type") String type,
//                                   @RequestParam("keyWord") String keyWord,
//                                   @RequestParam("isAct") String isAct,
//                                   RedirectAttributes redirectAttributes) {
//        adminService.deleteMemberList(selectedIds);
//        return "redirect:/admin/member-list?page=" +page+"&type="+type+"&keyWord="+keyWord+"&isAct="+isAct;
//    }

    // 관리자 관리 화면 :: 목록 조회
    @GetMapping("/manage-admin-list")
    public String getManageAdminList(Pagination pagination, Search search, Model model) {
        log.info(search.toString());
        List<AdminVO> admins = adminService.getManageAdminList(pagination, search);
        model.addAttribute("admins", admins);
        model.addAttribute("admin", new AdminVO());
        return "/admin/manage-admin-list";
    }

    // 관리자 관리 화면 :: 등록
    @PostMapping("/manage-admin-list")
    public String postManageAdminList(AdminVO adminVO, RedirectAttributes redirectAttributes) {
        try {
            adminService.postManageAdminList(adminVO);
        } catch (DuplicateException e){
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/manage-admin-list";
    }

    // 관리자 관리 화면 :: 삭제
    @PostMapping("/manage-admin-list-delete")
    public String deleteManageAdminList(@RequestParam("selectedIds") String selectedIds,
                                        @RequestParam("page") String page,
                                        @RequestParam("type") String type,
                                        @RequestParam("keyWord") String keyWord,
                                        RedirectAttributes redirectAttributes) {
        adminService.deleteManageAdminList(selectedIds);
        return "redirect:/admin/manage-admin-list?page=" + page + "&type=" + type + "&keyWord=" + keyWord;
    }

    // 코스 등록 화면
    @GetMapping("/add-course")
    public String getAddCourse(Model model) {
        return "/admin/add-course";
    }

    // 코스 등록
    @PostMapping("/add-course")
    public String saveAddCourse(Model model) {
        return "/admin/add-course";
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
