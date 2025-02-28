package com.app.mainPage;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main-page/*")
public class MainPageController {
    @GetMapping("mainPage")
    public void mainPage(){}

    @GetMapping("notice")
    public void notice(){}

}
