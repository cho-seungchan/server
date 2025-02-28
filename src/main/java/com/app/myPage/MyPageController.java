package com.app.myPage;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/my-page/*")
public class MyPageController {
    @GetMapping("changePassword")
    public void changePassword(){}
    @GetMapping("heart")
    public void heart(){}
    @GetMapping("messageList_Send")
    public void messageListSend(){}
    @GetMapping("messageListReceive")
    public void messageListReceive(){}
    @GetMapping("messageWrite")
    public void messageWrite(){}
    @GetMapping("myCourse")
    public void myCourse(){}
    @GetMapping("myMain")
    public void myMain(){}
    @GetMapping("myPageModify")
    public void myPageModify(){}
    @GetMapping("myReply")
    public void myReply(){}
    @GetMapping("payContent")
    public void payContent(){}
    @GetMapping("recruit")
    public void recuit(){}
    @GetMapping("userQuit")
    public void userQuit(){}

}
