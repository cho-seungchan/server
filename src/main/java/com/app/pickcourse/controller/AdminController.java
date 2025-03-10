// 2025.02.24 조승찬
package com.app.pickcourse.controller;


import com.app.pickcourse.domain.dto.CourseDTO;
import com.app.pickcourse.domain.dto.CourseListDTO;
import com.app.pickcourse.domain.vo.AdminVO;
import com.app.pickcourse.domain.vo.MemberVO;
import com.app.pickcourse.exception.DuplicateException;
import com.app.pickcourse.service.AdminService;
import com.app.pickcourse.util.Pagination;
import com.app.pickcourse.util.Search;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // 회원 관리 목록 조회
    @GetMapping("/member-list")
    public String getMemberList(Pagination pagination, Search search, Model model) {

        List<MemberVO> members = adminService.getMemberList(pagination, search);
        model.addAttribute("members", members);
        return "/admin/member-list";
    }

    // 회원 정지
    @PostMapping("/member-list-pause")
    public String patchMemberListpause(@RequestParam("selectedIds") String selectedIds,
                                       @RequestParam(value = "page", required = false) String page,
                                       @RequestParam(value = "type", required = false) String type,
                                       @RequestParam(value = "keyWord", required = false) String keyWord,
                                       @RequestParam(value = "isAct", required = false) String isAct,
                                       RedirectAttributes redirectAttributes) {
        adminService.patchMemberListPause(selectedIds);
        return "redirect:/admin/member-list?page=" +page+"&type="+type+"&keyWord="+keyWord+"&isAct="+isAct;
    }

    // 회원 정지 해제
    @PostMapping("/member-list-restart")
    public String patchMemberListRestart(@RequestParam("selectedIds") String selectedIds,
                                               @RequestParam(value = "page", required = false) String page,
                                               @RequestParam(value = "type", required = false) String type,
                                               @RequestParam(value = "keyWord", required = false) String keyWord,
                                               @RequestParam(value = "isAct", required = false) String isAct,
                                               RedirectAttributes redirectAttributes) {
        adminService.patchMemberListRestart(selectedIds);
        return "redirect:/admin/member-list?page=" +page+"&type="+type+"&keyWord="+keyWord+"&isAct="+isAct;
    }

    // 회원 추방
    @PostMapping("/member-list-delete")
    public String deleteMemberList(@RequestParam("selectedIds") String selectedIds,
                                   @RequestParam(value = "page", required = false) String page,
                                   @RequestParam(value = "type", required = false) String type,
                                   @RequestParam(value = "keyWord", required = false) String keyWord,
                                   @RequestParam(value = "isAct", required = false) String isAct,
                                   RedirectAttributes redirectAttributes) {
        adminService.deleteMemberList(selectedIds);
        return "redirect:/admin/member-list?page=" +page+"&type="+type+"&keyWord="+keyWord+"&isAct="+isAct;
    }

    // 관리자 관리 화면 :: 목록 조회
    @GetMapping("/manage-admin-list")
    public String getManageAdminList(Pagination pagination, Search search, Model model) {
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
                                        @RequestParam(value = "page", required = false) String page,
                                        @RequestParam(value = "type", required = false) String type,
                                        @RequestParam(value = "keyWord", required = false) String keyWord,
                                        RedirectAttributes redirectAttributes) {
        adminService.deleteManageAdminList(selectedIds);
        return "redirect:/admin/manage-admin-list?page=" + page + "&type=" + type + "&keyWord=" + keyWord;
    }

    // 코스 작성 화면
    // <a href="/admin/add-course.html" > html에서 직접 이동시키려면
    // src/main/resources/static/admin 에 html 파일이 존재해야 함
    @GetMapping("/add-course")
    public String getAddCourse(Model model) {
        return "/admin/add-course";
    }

    // 신규 코스 작성
    @PostMapping("/add-course")
    public String postAddCourse(CourseDTO courseDTO, Model model) {
        adminService.postAddCourse(courseDTO);
        return "redirect:/admin/add-course";
    }

    // 추천 코스 목록 (api)
    @GetMapping("/course-list")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getCourseList(Pagination pagination, Search search) {
        List<CourseListDTO> list = adminService.getCourseList(pagination,search);
        list.forEach(System.out::println);

        Map<String, Object> response = new HashMap<String, Object>();
        response.put("courses", list);
        response.put("pagination", pagination);
        response.put("search", search);
        return ResponseEntity.ok(response);
    }

    // 추천코스 목록에서 A ~ D 코스 혹은 봉사 코스로 등록
    @PatchMapping("/course-list")
    @ResponseBody
    public ResponseEntity<String> patchCourseList(@RequestBody Map<String, String> reqData) {
        // 받은 데이터 확인 (디버깅용)
        System.out.println("Course Request: " + reqData.get("courseId")+" "+reqData.get("courseType"));

        adminService.patchCourseList(reqData.get("courseId"),reqData.get("courseType").trim());
        // 처리 결과 반환
        return ResponseEntity.ok("Course list fetched successfully!");
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
