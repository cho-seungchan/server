package com.app.pickcourse.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/proposal")
@RequiredArgsConstructor
@Slf4j
public class ProposalController {

    @GetMapping("/eco")
    public String getEco(Model model) {
        return "/proposal/eco";
    }

    @GetMapping("/list")
    public String getList(Model model) {
        return "/proposal/list";
    }

    @GetMapping("/mobile")
    public String getMobile(Model model) {
        return "/proposal/mobile";
    }

    @GetMapping("/modify")
    public String getModify(Model model) {
        return "/proposal/modify";
    }

    @GetMapping("/modifylist")
    public String getModifyList(Model model) {
        return "/proposal/modifylist";
    }

    @GetMapping("/pay")
    public String getPay(Model model) {
        return "/proposal/pay";
    }

    @GetMapping("/read")
    public String getRead(Model model) {
        return "/proposal/read";
    }

    @GetMapping("/reviewlist")
    public String getReviewList(Model model) {
        return "/proposal/reviewlist";
    }

    @GetMapping("/reviewread")
    public String getReviewRead(Model model) {
        return "/proposal/reviewread";
    }

    @GetMapping("/viewlist")
    public String getVeiwList(Model model) {
        return "/proposal/viewlist";
    }

    @GetMapping("/write")
    public String getWrite(Model model) {
        return "/proposal/write";
    }
}
