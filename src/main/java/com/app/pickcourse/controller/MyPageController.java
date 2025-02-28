package com.app.pickcourse.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/my-page/*")
public class MyPageController {
    @GetMapping("changePassword")
    public void getChangePassword(){}
    @GetMapping("heart")
    public void getHeart(){}
    @GetMapping("messageList_Send")
    public void getMessageListSend(){}
    @GetMapping("messageListReceive")
    public void getMessageListReceive(){}
    @GetMapping("messageWrite")
    public void getMessageWrite(){}
    @GetMapping("myCourse")
    public void getMyCourse(){}
    @GetMapping("myMain")
    public void getMyMain(){}
    @GetMapping("myPageModify")
    public void getMyPageModify(){}
    @GetMapping("myReply")
    public void getMyReply(){}
    @GetMapping("payContent")
    public void getPayContent(){}
    @GetMapping("recruit")
    public void getRecuit(){}
    @GetMapping("userQuit")
    public void getUserQuit(){}

}
