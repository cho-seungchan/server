package com.app.introduce.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/introduce/*")
@RequiredArgsConstructor
@Slf4j
public class IntroduceController {
    @GetMapping("/introduce-main")
    public void getCustomerService() {}
}
