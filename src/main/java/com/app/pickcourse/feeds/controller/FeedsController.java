package com.app.pickcourse.feeds.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/feeds")
@RequiredArgsConstructor
@Slf4j
public class FeedsController {

    @GetMapping("/list")
    public String getList(Model model) {
        return "/feeds/list";
    }

    @GetMapping("/mobile")
    public String getMobile(Model model) {
        return "/feeds/mobile";
    }

    @GetMapping("/modify")
    public String Modify(Model model) {
        return "/feeds/modify";
    }

    @GetMapping("/modifylist")
    public String ModifyList(Model model) {
        return "/feeds/modifylist";
    }

    @GetMapping("/reply")
    public String Reply(Model model) {
        return "/feeds/reply";
    }

    @GetMapping("/review")
    public String Review(Model model) {
        return "/feeds/review";
    }

    @GetMapping("/reviewlist")
    public String ReviewList(Model model) {
        return "/feeds/reviewlist";
    }

    @GetMapping("/reviewmodify")
    public String ReviewModify(Model model) {
        return "/feeds/reviewmodify";
    }

    @GetMapping("/reviewwrite")
    public String ReviewWrite(Model model) {
        return "/feeds/reviewwrite";
    }

    @GetMapping("/tourlist")
    public String TourList(Model model) {
        return "/feeds/tourlist";
    }

    @GetMapping("/viewlist")
    public String ViewList(Model model) {
        return "/feeds/viewlist";
    }

    @GetMapping("/write")
    public String Write(Model model) {
        return "/feeds/write";
    }

}
