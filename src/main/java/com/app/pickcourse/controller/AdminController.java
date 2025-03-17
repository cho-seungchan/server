// 2025.02.24 조승찬
package com.app.pickcourse.controller;


import com.app.pickcourse.domain.dto.*;
import com.app.pickcourse.domain.vo.AdminVO;
import com.app.pickcourse.domain.vo.MemberVO;
import com.app.pickcourse.domain.vo.NoticeVO;
import com.app.pickcourse.service.AdminService;
import com.app.pickcourse.util.Pagination;
import com.app.pickcourse.util.Search;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    // 회원 관리 목록 조회 25.03.03 조승찬
    @GetMapping("/member-list")
    public String getMemberList(@RequestParam(value = "isAct", required = false) Character isAct,
                                Pagination pagination, Search search, Model model) {


        List<MemberVO> members = adminService.getMemberList(isAct, pagination, search);
        model.addAttribute("members", members);

        log.info("회원 목록 "+isAct+" "+pagination.toString()+" "+search.toString());
        return "/admin/member-list";
    }

    // 회원 정지 25.03.03 조승찬
    @PostMapping("/member-list-pause")
    public String patchMemberListpause(@RequestParam("selectedIds") String selectedIds,
                                       @RequestParam(value = "page", required = false) String page,
                                       @RequestParam(value = "type", required = false) String type,
                                       @RequestParam(value = "keyWord", required = false) String keyWord,
                                       @RequestParam(value = "isAct", required = false) String isAct,
                                       RedirectAttributes redirectAttributes) {
        log.info("회원정지 "+selectedIds+" "+page+" "+type+" "+keyWord+" "+isAct);
        adminService.patchMemberListPause(selectedIds);
        return "redirect:/admin/member-list?page=" +page+"&type="+type+"&keyWord="+keyWord+"&isAct="+isAct;
    }

    // 회원 정지 해제 25.03.03 조승찬
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

    // 회원 추방 25.03.03 조승찬
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

    // 관리자 관리 화면 :: 목록 조회 25.03.03 조승찬
    @GetMapping("/manage-admin-list")
    public String getManageAdminList(Pagination pagination, Search search, Model model) {

        List<AdminVO> admins = adminService.getManageAdminList(pagination, search);
        model.addAttribute("admins", admins);
        model.addAttribute("admin", new AdminVO());

        log.info(pagination.toString());
        return "/admin/manage-admin-list";
    }

    // 관리자 관리 화면 :: 등록 25.03.03 조승찬
    @PostMapping("/manage-admin-list")
    public String postManageAdminList(AdminVO adminVO, RedirectAttributes redirectAttributes) {
        try {
            adminService.postManageAdminList(adminVO);
        } catch (com.app.pickcourse.exception.DuplicateException e){
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/manage-admin-list";
    }

    // 관리자 관리 화면 :: 삭제  25.03.03 조승찬
    @PostMapping("/manage-admin-list-delete")
    public String deleteManageAdminList(@RequestParam("selectedIds") String selectedIds,
                                        @RequestParam(value = "page", required = false) String page,
                                        @RequestParam(value = "type", required = false) String type,
                                        @RequestParam(value = "keyWord", required = false) String keyWord,
                                        RedirectAttributes redirectAttributes) {
        adminService.deleteManageAdminList(selectedIds);
        return "redirect:/admin/manage-admin-list?page=" + page + "&type=" + type + "&keyWord=" + keyWord;
    }

    // 신규 코스 조회 화면 25.03.07 조승찬
    @GetMapping("/add-course")
    public String getAddCourse(Model model) {return "/admin/add-course";}

    // 신규 코스 작성  25.03.07 조승찬
    @PostMapping("/add-course")
    public String postAddCourse(CourseDTO courseDTO, Model model) {
        log.info(courseDTO.toString());
        adminService.postAddCourse(courseDTO);
        return "redirect:/admin/add-course";
    }

    // 추천 코스 목록 (api) 25.03.08 조승찬
    @GetMapping("/course-list")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getCourseList(Pagination pagination, Search search) {
        log.info("course list  "+pagination.toString()+" "+search.toString());
        List<CourseListDTO> list = adminService.getCourseList(pagination,search);
        list.forEach(System.out::println);

        Map<String, Object> response = new HashMap<String, Object>();
        response.put("courses", list);
        response.put("pagination", pagination);
        response.put("search", search);
        return ResponseEntity.ok(response);
    }

    // 추천코스 목록에서 A ~ D 코스 혹은 봉사 코스로 등록 25.03.09 조승찬
    @PutMapping("/course-list")
    @ResponseBody
    public ResponseEntity<String> patchCourseList(@RequestBody Map<String, String> reqData) {
        // 받은 데이터 확인 (디버깅용)
        System.out.println("Course Request: " + reqData.get("courseId")+" "+reqData.get("courseType"));

        adminService.patchCourseList(reqData.get("courseId"),reqData.get("courseType").trim());
        // 처리 결과 반환
        return ResponseEntity.ok("Course list fetched successfully!");
    }

    // 추천 코스 조회   25.03.10 조승찬
    @GetMapping("/course-detail/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getCourseDetail(@PathVariable("id") Long id) {
        CourseDTO courseDTO = adminService.getCourseDetail(id);
        System.out.println(courseDTO.toString());
        Map<String,Object> response = new HashMap<String, Object>();
        response.put("course", courseDTO);
        return ResponseEntity.ok(response);
    }

    // 타입별 추천 코스 조회  25.03.12 조승찬
    @GetMapping("/course-type-detail/{courseType}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getCourseTypeDetail(@PathVariable("courseType") String courseType) {
        CourseDTO courseDTO = adminService.getCourseTypeDetail(courseType);
        System.out.println(courseDTO.toString());
        Map<String,Object> response = new HashMap<String, Object>();
        response.put("course", courseDTO);
        return ResponseEntity.ok(response);
    }

    // 추천 코스 수정  25.03.13 조승찬
    @PutMapping("/course-detail")
    @ResponseBody
    public void putCourseDetail(@RequestBody CourseDTO courseDTO) {
//        log.info("putCourseDetail"+courseDTO.toString());
        adminService.putCourseDetail(courseDTO);
    }

    // 추천 코스 삭제  25.03.13 조승찬
    @DeleteMapping("/course-detail/{id}")
    @ResponseBody
    public void deleteCourseDetail(@PathVariable Long id){
        log.info("deleteCourseDetail  "+id);
        adminService.deleteCourseDetail(id);
    }

    // 신고 관리 :: 피드 신고, 댓글 신고 25.03.14 조승찬
    @GetMapping("/report-list")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getReportList(Pagination pagination, Search search) {
        List<ReportListDTO> list = adminService.getReportList(pagination, search);

        Map<String, Object> response = new HashMap<>();
        response.put("report", list);
        response.put("pagination", pagination);
        response.put("search", search);

        log.info("pagination "+pagination.toString()+"search "+search.toString());
        list.forEach(System.out::println);

        return ResponseEntity.ok(response);
    }

    // 신고된 대상 상세 조회 25.03.14 조승찬
    @GetMapping("/report-detail/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getReportDetail(@PathVariable Long id,
                                                               @RequestParam String source) {
        ReplyDetailDTO reply = adminService.getReportDetail(id, source);

        Map<String,Object> response = new HashMap<>();
        response.put("reply", reply);

        log.info(reply.toString());

        return ResponseEntity.ok(response);
    };

    // 공지 사항 목록  2025.03.15 조승찬
    @GetMapping("/notice-list")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getNoticeList(Pagination pagination, Search search) {

        log.info(" getNoticeList 들어옴 ");

        List<NoticeListDTO> list = adminService.getNoticeList(pagination, search);

        list.forEach(System.out::println);

        Map<String,Object> response = new HashMap<>();
        response.put("notice", list);
        response.put("pagination", pagination);
        response.put("search", search);

        return ResponseEntity.ok(response);
    }

    // 공지사항 상세 조회  25.03.15 조승찬
    @GetMapping("/notice-detail/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getNoticeDetail(@PathVariable Long id) {

        NoticeVO notice = adminService.getNoticeDetail(id);

        Map<String,Object> response = new HashMap<>();
        response.put("notice", notice);
        return ResponseEntity.ok(response);
    }

    // 공지사항 등록  25.03.15 조승찬
    @PostMapping("/notice-detail")
    @ResponseBody
    public void postNoticeDetail(@RequestBody NoticeVO notice) {
        log.info("postNoticeDetail  "+notice.toString());

        adminService.postNoticeDetail(notice);
    }

    // 공지사항 수정  25.03.15 조승찬
    @PutMapping("/notice-detail")
    @ResponseBody
    public void putNoticeDetail(@RequestBody NoticeVO notice) {

        adminService.putNoticeDetail(notice);
    }

    // 공지사항 삭제 25.03.15 조승찬
    @DeleteMapping("/notice-detail/{id}")
    @ResponseBody
    public void deleteNoticeDetail(@PathVariable Long id) {
        log.info("deleteNoticeDetail  "+id);

        adminService.deleteNoticeDetail(id);
    }

}
