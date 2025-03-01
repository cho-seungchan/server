package com.app.pickcourse.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login/*")
@Slf4j
public class LoginController {
    @GetMapping("login")
    public void getLogin(){}

    @GetMapping("findPassword")
    public void getFindPassword(){}

}
