package com.app.pickcourse.controller;


import com.app.pickcourse.domain.dto.*;
import com.app.pickcourse.domain.vo.MemberVO;
import com.app.pickcourse.domain.vo.SendMessageVO;
import com.app.pickcourse.repository.MemberDAO;
import com.app.pickcourse.repository.ReceiveMessageDAO;
import com.app.pickcourse.repository.SendMessageDAO;
import com.app.pickcourse.service.MessageService;
import com.app.pickcourse.util.Pagination;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/my-page/*")
public class MyPageController {
    private final MemberDAO memberDAO;
    private final MessageService messageService;
    private final SendMessageDAO sendMessageDAO;
    private final ReceiveMessageDAO receiveMessageDAO;

    public MyPageController(MemberDAO memberDAO, MessageService messageService, SendMessageDAO sendMessageDAO, ReceiveMessageDAO receiveMessageDAO) {
        this.memberDAO = memberDAO;
        this.messageService = messageService;
        this.sendMessageDAO = sendMessageDAO;
        this.receiveMessageDAO = receiveMessageDAO;
    }

    @GetMapping("changePassword")
    public void getChangePassword(){}
    @PostMapping("changePassword")
    public String changePassword(@RequestParam("memberPassword") String memberPassword) {
        return changePassword(memberPassword);
    }

    @GetMapping("heart")
    public void getHeart(){}

    @GetMapping("/messageList_Send")
    public String goToSendListPage(HttpSession session) {
        MemberVO member = (MemberVO) session.getAttribute("member");

        if (member == null) {
            return "redirect:/login/login";
        }

        return "my-page/messageList_Send";
    }

    @GetMapping("/messageList_Sends")
    @ResponseBody
    public SendPaginationDTO getSendMessages(Pagination pagination, HttpSession session, HttpServletResponse response)  throws IOException {
        MemberVO member = (MemberVO) session.getAttribute("member");
        if (member == null) {
            response.sendRedirect("/login/login");
            return null;
        }

        Long senderId = member.getId();

        return messageService.getSendList(senderId, pagination);
    }




    @GetMapping("/messageListReceive")
    public String goToReceiveListPage(HttpSession session) {
        MemberVO member = (MemberVO) session.getAttribute("member");

        if (member == null) {
            return "redirect:/login/login";
        }

        return "my-page/messageListReceive";
    }

    @GetMapping("/messageListReceives")
    @ResponseBody
    public ReceivePaginationDTO getReceivedMessages(Pagination pagination, HttpSession session, HttpServletResponse response)  throws IOException {
        MemberVO member = (MemberVO) session.getAttribute("member");
        if (member == null) {
            response.sendRedirect("/login/login");
            return null;
        }

        Long receiverId = member.getId();

        return messageService.getReceiveList(receiverId, pagination);
    }


    @GetMapping("messageWrite")
    public String getMessageWrite(Model model) {
        model.addAttribute("sendMessageDTO", new SendMessageDTO());
        return "my-page/messageWrite";
    }

    @PostMapping("messageWrite")
    public String sendMessage(@ModelAttribute SendMessageDTO sendMessageDTO, HttpSession session) {
        // 로그인된 사용자 정보 가져오기
        MemberVO member = (MemberVO) session.getAttribute("member");

        // 로그인된 사용자가 없으면 에러 페이지로 리다이렉트
        if (member == null) {
            return "redirect:/login/login";
        }

        // 받은 사람 이메일이 비어있는 경우
        if (sendMessageDTO.getReceiverEmail() == null || sendMessageDTO.getReceiverEmail().isEmpty()) {
            return "redirect:/error-page";
        }

        // 받은 사람 이메일로 ID 조회
        Optional<Long> receiverId = memberDAO.findIdByEmail(sendMessageDTO.getReceiverEmail());
        if (receiverId.isEmpty()) {
            return "redirect:/error-page";
        }

        // receiverId 설정
        sendMessageDTO.setReceiverId(receiverId.get());
        // senderId를 세션에서 가져온 로그인된 사용자 ID로 설정
        sendMessageDTO.setSenderId(member.getId());

        // 메시지 전송
        messageService.sendMessage(sendMessageDTO);

        // 전송 성공 후 리스트로 이동
        return "redirect:/my-page/messageList_Send";
    }


    @GetMapping("myCourse")
    public void getMyCourse(){}
    @GetMapping("myMain")
    public void getMyMain(){}

    @GetMapping("myPageModify")
    public String getMyPageModify(HttpSession session, Model model) {
        Object member = session.getAttribute("member");

        if (member == null) {
            return "redirect:/login/login";
        }

        MemberVO memberVO = (MemberVO) member;
        MemberDTO memberDTO = MemberDTO.fromVO(memberVO);

        model.addAttribute("memberDTO", memberDTO);

        return "my-page/myPageModify";
    }



    @GetMapping("myReply")
    public void getMyReply(){}
    @GetMapping("payContent")
    public void getPayContent(){}
    @GetMapping("recruit")
    public void getRecuit(){}
    @GetMapping("userQuit")
    public void getUserQuit(){}

}
