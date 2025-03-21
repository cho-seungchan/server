// 2025.02.24 조승찬
package com.app.pickcourse.controller;

import com.app.pickcourse.domain.dto.*;
import com.app.pickcourse.domain.vo.ReplyVO;
import com.app.pickcourse.domain.vo.ReportVO;
import com.app.pickcourse.service.FeedsService;
import com.app.pickcourse.util.PaginationOnePage;
import jakarta.servlet.http.HttpServletRequest;
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
    private final FeedsService feedsService;

    // 댓글 목록 조회 :: 25.03.16 조승찬
    @GetMapping("/reply-list/{feedId}")
    public String getReplyList(@PathVariable Long feedId, PaginationOnePage pagination, Model model) {

        List<ReplyListDTO> replys = feedsService.getReplyList(1l, feedId, pagination);  // 로그인수정
        log.info("pagination  "+pagination.toString());
        model.addAttribute("replys", replys);  // 댓글 목록
        model.addAttribute("replyAction", new ReplyActionDTO()); // 입력될 댓글을 받아올 객체
        model.addAttribute("pagination", pagination);
        return "/feeds/reply-list";
    }

    // 댓글 목록 추가 조회 레스트컨트롤러 방식:: 25.03.16 조승찬
    @GetMapping("/reply-list/api/{feedId}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getReplyListApi(@PathVariable Long feedId, PaginationOnePage pagination) {

        List<ReplyListDTO> replys = feedsService.getReplyList(1l, feedId, pagination);  //로그인수정
        Map<String, Object> response = new HashMap<>();
        response.put("replys", replys);
        response.put("pagination", pagination);
        return ResponseEntity.ok(response);

    }

    // 댓글 삭제 레스트컨트롤러 방식 => 컨트롤러 방식으로 redirect :: 25.03.16 조승찬
    @DeleteMapping("/reply-list/{id}")
    @ResponseBody
    public Map<String, Object> deleteReplyList(@PathVariable Long id,
                                               @RequestParam Long feedId) {

        feedsService.deleteReplyList(id);
        Map<String, Object> response = new HashMap<>();
        response.put("redirectUrl", "/feeds/reply-list/" + feedId + "?page=" + 1); // 1페이지부터 조회
        return response;
    }

    // 댓글 신고 25.03.16 조승찬
    @PostMapping("/reply-list/report")
    @ResponseBody
    public void postReportReplyList(@RequestBody ReportVO reportVO) {
        log.info("postReportReplyList  들어옴");

        feedsService.postReportReplyList(reportVO, 1l);  // 로그인수정
    }

    // 댓글 등록  25.03.17 조승찬
    @PostMapping("/reply-list")
    @ResponseBody
    public Map<String, Object> postReplyList(@RequestBody ReplyVO replyVO) {
        log.info("Map<String, Object> postReplyList에 들어옴 "+replyVO.toString());
        feedsService.postReplyList(replyVO, 1l);   // 로그인수정

        Map<String, Object> response = new HashMap<>();
        // 댓글 등록 후 첫 페이지로 이동 :: 등록 확인을 위해
        response.put("redirectUrl", "/feeds/reply-list/" + replyVO.getFeedId() + "?page=" + 1);

        return response;
    }

    // 나의 댓글 목록 조회 :: 25.03.17 조승찬
    @GetMapping("/my/reply-list")
    public String getMyReplyList(PaginationOnePage pagination, Model model) {

        List<ReplyListDTO> replys = feedsService.getMyReplyList(1l, pagination);  //로그인수정
        model.addAttribute("replys", replys);  // 댓글 목록
        model.addAttribute("replyAction", new ReplyActionDTO()); // 입력될 댓글을 받아올 객체
        model.addAttribute("pagination", pagination);

        return "/feeds/my-reply-list";
    }

    // 나의 댓글 목록 추가 조회 레스트컨트롤러 방식:: 25.03.17 조승찬
    @GetMapping("/my/reply-list/api")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getMyReplyListApi(PaginationOnePage pagination) {

        List<ReplyListDTO> replys = feedsService.getMyReplyList(1l, pagination); // 로그인수정
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
    public String getFeedWrite(Model model) {
        FeedDTO feedDTO = new FeedDTO();
        model.addAttribute("feedDTO", feedDTO);
        return "/feeds/feed-write";
    }

    // 피드 작성 25.03.18 조승찬
    @PostMapping("/feed-write")
    public String postFeedWrite(FeedDTO feedDTO) {

        feedsService.postFeedWrite(1l, feedDTO); //로그인수정

        // 조회 타입 정하기
        String listType = feedDTO.getFeedType().equals("TOGETHER") ? "TOGETHER" : "ALL";

        return "redirect:/feeds/feed-list?listType=" + listType;
    }

    // 피드 수정용 조회 25.03.19 조승찬
    @GetMapping("/feed-modify")
    public String getFeedModify(@RequestParam("id") Long id, @RequestParam("feedType") String feedType, Model model) {

        FeedDTO feedDTO = feedsService.getFeedModify(id, feedType);
        feedDTO.setId(id);
        feedDTO.setFeedType(feedType);
        model.addAttribute("feedDTO", feedDTO);

        return "/feeds/feed-modify";
    }

    // 피드 수정 25.03.19 조승찬
    @PostMapping("/feed-modify")
    public String postFeedModify(FeedDTO feedDTO) {

        feedsService.postFeedModify(feedDTO); //로그인수정

        String listType = feedDTO.getFeedType().equals("TOGETHER") ? "TOGETHER" : "ALL";
        return "redirect:/feeds/my/feed-list?listType=" + listType;
    }

    // 피드 삭제 25.03.19 조승찬
    @PostMapping("/feed-delete")
    public String deleteFeedModify(@RequestParam("id") Long id, @RequestParam("feedType") String feedType) {

        log.info("deleteFeedModify 들어옴 ::  "+id+" "+feedType);

        feedsService.deleteFeedModify(id, feedType);

        String listType = feedType.equals("TOGETHER") ? "TOGETHER" : "ALL";
        return "redirect:/feeds/my/feed-list?listType=" + listType;
    }

    // 리얼 후기 작성 25.03.18 조승찬
    @GetMapping("/real-write")
    public String ReviewWrite(@RequestParam("planId") Long planId, Model model) {
        RealDTO realDTO = new RealDTO();
        realDTO.setPlanId(planId);
        model.addAttribute("realDTO", realDTO);
        return "/feeds/real-write";
    }

    // 리얼 후기 작성 25.03.18 조승찬
    @PostMapping("/real-write")
    public String postRealWrite(RealDTO realDTO) {
        log.info(realDTO.toString());

        feedsService.postRealWrite(2l, realDTO); //로그인수정

        return "redirect:/feeds/feed-list?listType=REAL";
    }

    // 리얼 후기 수정용 조회 25.03.19 조승찬
    @GetMapping("/real-modify")
    public String getRealModify(@RequestParam("id") Long id, Model model) {

        RealDTO realDTO = feedsService.getRealModify(id);
        realDTO.setId(id);
        model.addAttribute("realDTO", realDTO);

        return "/feeds/real-modify";
    }

    // 리얼 후기 수정 25.03.19 조승찬
    @PostMapping("/real-modify")
    public String postRealModify(RealDTO realDTO) {

        feedsService.postRealModify(realDTO);

        return "redirect:/feeds/my/feed-list?listType=REAL";
    }

    // 리얼 후기 삭제 25.03.19 조승찬
    @PostMapping("/real-delete")
    public String deleteFeedModify(@RequestParam("id") Long id) {

        feedsService.deleteRealModify(id);

        return "redirect:/feeds/my/feed-list?listType=REAL";
    }

    // 피드 신고 25.03.20 조승찬
    @PostMapping("/feed-list/report")
    @ResponseBody
    public void postReportFeedList(@RequestBody ReportVO reportVO) {

        feedsService.postReportFeedList(reportVO, 1l);  // 로그인수정
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
    public String getFeedModifyList(@RequestParam("listType") String listType, Model model) {
        List<FeedListDTO> feedListDTO = feedsService.getMyFeedList(1l, listType); //  로그인수정
        model.addAttribute("feedListDTO", feedListDTO);
        model.addAttribute("listType", listType);
        return "/feeds/my-feed-list";
    }

    // 나의 여행 목록 25.03.21 조승찬
    @GetMapping("/tour-list")
    public String getTourList(Model model) {

        List<TourListDTO> tourListDTO = feedsService.getTourList(1l); // 로그인 수정
        model.addAttribute("tourListDTO", tourListDTO);

        return "/feeds/tour-list";
    }

    @GetMapping("/review-list")
    public String getReviewList(Model model) {
        return "/feeds/reviewlist";
    }

}
