// 2025.02.24 조승찬
package com.app.pickcourse.controller;

import com.app.pickcourse.domain.dto.*;
import com.app.pickcourse.domain.vo.ReplyVO;
import com.app.pickcourse.domain.vo.ReportVO;
import com.app.pickcourse.service.FeedsService;
import com.app.pickcourse.util.PaginationOnePage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/feeds")
@RequiredArgsConstructor
@Slf4j
public class FeedsController {
    private final HttpSession session;
    private final FeedsService feedsService;
    private final HttpServletRequest request;

    // 댓글 목록 조회 :: 25.03.16 조승찬
    @GetMapping("/reply-list/{feedId}")
    public String getReplyList(@SessionAttribute(name = "member", required = false) MemberDTO member,
                               @PathVariable Long feedId, PaginationOnePage pagination, Model model) {

        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        Long memberId = member.getId();

        // memberId는 댓글 "삭제", "신고" 를 구분하기 위해서 사용
        List<ReplyListDTO> replys = feedsService.getReplyList(memberId, feedId, pagination);
        log.info("pagination  "+pagination.toString());
        model.addAttribute("replys", replys);  // 댓글 목록
        model.addAttribute("replyAction", new ReplyActionDTO()); // 입력될 댓글을 받아올 객체
        model.addAttribute("pagination", pagination);
        return "/feeds/reply-list";
    }

    // 댓글 목록 추가 조회 레스트컨트롤러 방식:: 25.03.16 조승찬
    @GetMapping("/reply-list/api/{feedId}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getReplyListApi(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                                               @PathVariable Long feedId, PaginationOnePage pagination) {

        List<ReplyListDTO> replys = feedsService.getReplyList(member.getId(), feedId, pagination);
        Map<String, Object> response = new HashMap<>();
        response.put("replys", replys);
        response.put("pagination", pagination);
        return ResponseEntity.ok(response);

    }

    // 댓글 삭제 레스트컨트롤러 방식 => 컨트롤러 방식으로 redirect :: 25.03.16 조승찬
    @DeleteMapping("/reply-list/{id}")
    @ResponseBody
    public Map<String, Object> deleteReplyList(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                               @PathVariable Long id,
                                               @RequestParam Long feedId) {

        feedsService.deleteReplyList(id);
        Map<String, Object> response = new HashMap<>();
        response.put("redirectUrl", "/feeds/reply-list/" + feedId + "?page=" + 1); // 1페이지부터 조회
        return response;
    }

    // 댓글 신고 25.03.16 조승찬
    @PostMapping("/reply-list/report")
    @ResponseBody
    public void postReportReplyList(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                    @RequestBody ReportVO reportVO) {

        feedsService.postReportReplyList(reportVO, member.getId());
    }

    // 댓글 등록  25.03.17 조승찬
    @PostMapping("/reply-list")
    @ResponseBody
    public Map<String, Object> postReplyList(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                             @RequestBody ReplyVO replyVO) {

        feedsService.postReplyList(replyVO, member.getId());

        Map<String, Object> response = new HashMap<>();
        // 댓글 등록 후 첫 페이지로 이동 :: 등록 확인을 위해
        response.put("redirectUrl", "/feeds/reply-list/" + replyVO.getFeedId() + "?page=" + 1);

        return response;
    }

    // 나의 댓글 목록 조회 :: 25.03.17 조승찬
    @GetMapping("/my/reply-list")
    public String getMyReplyList(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                 PaginationOnePage pagination, Model model) {

        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        List<ReplyListDTO> replys = feedsService.getMyReplyList(member.getId(), pagination);
        model.addAttribute("replys", replys);  // 댓글 목록
        model.addAttribute("replyAction", new ReplyActionDTO()); // 입력될 댓글을 받아올 객체
        model.addAttribute("pagination", pagination);

        return "/feeds/my-reply-list";
    }

    // 나의 댓글 목록 추가 조회 레스트컨트롤러 방식:: 25.03.17 조승찬
    @GetMapping("/my/reply-list/api")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getMyReplyListApi(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                                                 PaginationOnePage pagination) {

        List<ReplyListDTO> replys = feedsService.getMyReplyList(member.getId(), pagination);
        Map<String, Object> response = new HashMap<>();
        response.put("replys", replys);
        response.put("pagination", pagination);
        return ResponseEntity.ok(response);

    }

    // 나의 댓글 삭제 레스트컨트롤러 방식 => 컨트롤러 방식으로 redirect :: 25.03.17 조승찬
    @DeleteMapping("/my/reply-list/{id}")
    @ResponseBody
    public Map<String, Object> deleteMyReplyList(@PathVariable Long id) {

        feedsService.deleteReplyList(id);
        Map<String, Object> response = new HashMap<>();
        response.put("redirectUrl", "/feeds/my/reply-list?page=" + 1); // 1페이지부터 조회
        return response;
    }

    // 피드 작성용 화면 랜딩 25.03.18 조승찬
    @GetMapping("/feed-write")
    public String getFeedWrite(@SessionAttribute(name = "member", required = false) MemberDTO member,
                               Model model) {

        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        FeedDTO feedDTO = new FeedDTO();
        model.addAttribute("feedDTO", feedDTO);
        return "/feeds/feed-write";
    }

    // 피드 작성 25.03.18 조승찬
    @PostMapping("/feed-write")
    public String postFeedWrite(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                FeedDTO feedDTO) {

        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        feedsService.postFeedWrite(member.getId(), feedDTO);

        // 조회 타입 정하기
        String listType = feedDTO.getFeedType().equals("TOGETHER") ? "TOGETHER" : "ALL";

        return "redirect:/feeds/feed-list?listType=" + listType;
    }

    // 피드 수정용 조회 25.03.19 조승찬
    @GetMapping("/feed-modify")
    public String getFeedModify(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                @RequestParam("id") Long id, @RequestParam("feedType") String feedType, Model model) {

        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        FeedDTO feedDTO = feedsService.getFeedModify(id, feedType);
        feedDTO.setId(id);
        feedDTO.setFeedType(feedType);
        model.addAttribute("feedDTO", feedDTO);

        return "/feeds/feed-modify";
    }

    // 피드 수정 25.03.19 조승찬
    @PostMapping("/feed-modify")
    public String postFeedModify(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                 FeedDTO feedDTO) {

        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        feedsService.postFeedModify(feedDTO);

        String listType = feedDTO.getFeedType().equals("TOGETHER") ? "TOGETHER" : "ALL";
        return "redirect:/feeds/my/feed-list?listType=" + listType;
    }

    // 피드 삭제 25.03.19 조승찬
    @PostMapping("/feed-delete")
    public String deleteFeedModify(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                   @RequestParam("id") Long id, @RequestParam("feedType") String feedType) {
        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        feedsService.deleteFeedModify(id, feedType);

        String listType = feedType.equals("TOGETHER") ? "TOGETHER" : "ALL";
        return "redirect:/feeds/my/feed-list?listType=" + listType;
    }

    // 리얼 후기 작성 25.03.18 조승찬
    @GetMapping("/real-write")
    public String ReviewWrite(@SessionAttribute(name = "member", required = false) MemberDTO member,
                              @RequestParam("planId") Long planId, Model model) {

        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        RealDTO realDTO = new RealDTO();
        realDTO.setPlanId(planId);
        model.addAttribute("realDTO", realDTO);
        return "/feeds/real-write";
    }

    // 리얼 후기 작성 25.03.18 조승찬
    @PostMapping("/real-write")
    public String postRealWrite(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                RealDTO realDTO) {
        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        feedsService.postRealWrite(member.getId(), realDTO);

        return "redirect:/feeds/feed-list?listType=REAL";
    }

    // 리얼 후기 수정용 조회 25.03.19 조승찬
    @GetMapping("/real-modify")
    public String getRealModify(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                @RequestParam("id") Long id, Model model) {
        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        RealDTO realDTO = feedsService.getRealModify(id);
        realDTO.setId(id);
        model.addAttribute("realDTO", realDTO);

        return "/feeds/real-modify";
    }

    // 리얼 후기 수정 25.03.19 조승찬
    @PostMapping("/real-modify")
    public String postRealModify(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                 RealDTO realDTO) {
        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        feedsService.postRealModify(realDTO);

        return "redirect:/feeds/my/feed-list?listType=REAL";
    }

    // 리얼 후기 삭제 25.03.19 조승찬
    @PostMapping("/real-delete")
    public String deleteFeedModify(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                   @RequestParam("id") Long id) {
        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        feedsService.deleteRealModify(id);

        return "redirect:/feeds/my/feed-list?listType=REAL";
    }

    // 피드 신고 25.03.20 조승찬
    @PostMapping("/feed-list/report")
    @ResponseBody
    public void postReportFeedList(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                   @RequestBody ReportVO reportVO) {

        feedsService.postReportFeedList(reportVO, member.getId());
    }


    // 피드 리스트  25.03.20 조승찬
    @GetMapping("/feed-list")
    public String getFeedList(@RequestParam("listType") String listType, Model model) {

        List<FeedListDTO> feedListDTO = feedsService.getFeedList(listType);
        model.addAttribute("feedListDTO", feedListDTO);
        model.addAttribute("listType", listType);
        return "/feeds/feed-list";
    }

    // 나의 피드 리스트 25.03.20 조승찬
    @GetMapping("/my/feed-list")
    public String getFeedModifyList(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                    @RequestParam("listType") String listType, Model model) {
        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        List<FeedListDTO> feedListDTO = feedsService.getMyFeedList(member.getId(), listType);
        model.addAttribute("feedListDTO", feedListDTO);
        model.addAttribute("listType", listType);
        return "/feeds/my-feed-list";
    }

    // 나의 여행 목록 25.03.21 조승찬
    @GetMapping("/tour-list")
    public String getTourList(@SessionAttribute(name = "member", required = false) MemberDTO member,
                              PaginationOnePage pagination, Model model) {
        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        List<TourListDTO> tourListDTO = feedsService.getTourList(member.getId(), pagination);
        model.addAttribute("tourListDTO", tourListDTO);
        model.addAttribute("pagination", pagination);

        log.info("컨트롤 "+pagination.toString());
        tourListDTO.forEach(System.out::println);

        return "/feeds/tour-list";
    }


    // 나의 여행 목록 추가 조회 레스트컨트롤러 방식:: 25.03.21 조승찬
    @GetMapping("/tour-list/api")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getTourListApi(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                                              PaginationOnePage pagination) {

        List<TourListDTO> tours = feedsService.getTourList(member.getId(), pagination);
        log.info("레스트 "+pagination.toString());
        tours.forEach(System.out::println);

        Map<String, Object> response = new HashMap<>();
        response.put("tours", tours);
        response.put("pagination", pagination);
        return ResponseEntity.ok(response);

    }

    @GetMapping("/review-list")
    public String getReviewList(Model model) {
        return "/feeds/reviewlist";
    }

}
