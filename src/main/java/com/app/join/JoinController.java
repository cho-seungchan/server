package com.app.join;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/join/*")
@Slf4j
public class JoinController {
    @GetMapping("join")
    public void join(){}

    @GetMapping("join_check")
    public void joinCheck(){}

    @GetMapping("join_email")
    public void joinEmail(){}
}

