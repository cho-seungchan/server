package com.app.pickcourse.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/feed")
@RequiredArgsConstructor
@Slf4j
public class FeedController {

    @GetMapping("/list")
    public String getFeedList(Model model) {
        return "/feeds/list";
    }

    @GetMapping("/write")
    public String getFeedWrite(Model model) {
        return "/feeds/write";
    }

    @PostMapping("/write")
    public String postFeedWrite(Model model) {
        return "/feeds/write";
    }

    @GetMapping("/modify")
    public String getFeedModify(Model model) {
        return "/feeds/modify";
    }

    @PostMapping("/modify")
    public String postFeedModify(Model model) {
        return "/feeds/modify";
    }

    @GetMapping("/modify-list")
    public String getFeedModifyList(Model model) {
        return "/feeds/modifylist";
    }

    @GetMapping("/reply")
    public String getReplyList(Model model) {
        return "/feeds/reply";
    }

    @PostMapping("/reply")
    public String postReplyList(Model model) {
        return "/feeds/reply";
    }

    @GetMapping("/review-write")
    public String ReviewWrite(Model model) {
        return "/feeds/reviewwrite";
    }

    @PostMapping("/review-write")
    public String postReviewWrite(Model model) {
        return "/feeds/reviewwrite";
    }

    @GetMapping("/review-list")
    public String getReviewList(Model model) {
        return "/feeds/reviewlist";
    }

    @GetMapping("/review-modify")
    public String ReviewModify(Model model) {
        return "/feeds/reviewmodify";
    }

    @GetMapping("/tourlist")
    public String TourList(Model model) {
        return "/feeds/tourlist";
    }

    @GetMapping("/viewlist")
    public String ViewList(Model model) {
        return "/feeds/viewlist";
    }

    @GetMapping("/mobile")
    public String getMobile(Model model) {
        return "/feeds/mobile";
    }

}
