package com.app.pickcourse.controller;


import com.app.pickcourse.domain.dto.ReceiveMessageDTO;
import com.app.pickcourse.domain.dto.SendMessageDTO;
import com.app.pickcourse.domain.vo.MemberVO;
import com.app.pickcourse.domain.vo.SendMessageVO;
import com.app.pickcourse.repository.MemberDAO;
import com.app.pickcourse.repository.ReceiveMessageDAO;
import com.app.pickcourse.repository.SendMessageDAO;
import com.app.pickcourse.service.MessageService;
import com.app.pickcourse.util.Pagination;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    @GetMapping("heart")
    public void getHeart(){}

//    @GetMapping("messageList_Send")
//    public String getSentMessage(Model model, HttpSession session) {
//        MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
//
//        if (loginUser == null) {
//            return "redirect:/login/login";
//        }
//
//        try {
//            Long senderId = loginUser.getId();
//            List<SendMessageDTO> sentMessages = sendMessageDAO.findBySenderId(senderId);
//            model.addAttribute("sentMessages", sentMessages);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return "my-page/messageList_Send";
//    }

    @GetMapping("messageList_Send")
    public String getSentMessages(
            @RequestParam(value = "page", defaultValue = "1") int page,
            Model model,
            HttpSession session) {

        MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

        if (loginUser == null) {
            return "redirect:/login/login";
        }

        Long senderId = loginUser.getId();

        // 전체 메시지 개수 조회
        int totalMessages = messageService.getSendMessageCount(senderId);

        // 페이지네이션 객체 생성
        Pagination pagination = new Pagination();
        pagination.setPage(page);
        pagination.create(totalMessages);

        // 보낸 메시지 리스트 가져오기
        List<SendMessageDTO> sentMessages = messageService.findSendMessages(senderId, page, 5);

        model.addAttribute("sentMessages", sentMessages);
        model.addAttribute("pagination", pagination);

        return "my-page/messageList_Send";
    }



    @GetMapping("messageListReceive")
    public String getReceivedMessages(
            @RequestParam(value = "page", defaultValue = "1") int page,
            Model model,
            HttpSession session) {

        MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

        if (loginUser == null) {
            return "redirect:/login/login";
        }

        Long receiverId = loginUser.getId();

        // 전체 메시지 개수 조회
        int totalMessages = messageService.getReceiveMessageCount(receiverId);

        // 페이지네이션 객체 생성
        Pagination pagination = new Pagination();
        pagination.setPage(page);
        pagination.create(totalMessages);

        // 받은 메시지 리스트 가져오기 (페이징 적용)
        List<ReceiveMessageDTO> receivedMessages = messageService.findReceiveMessages(receiverId, page, 5);

        model.addAttribute("receivedMessages", receivedMessages);
        model.addAttribute("pagination", pagination);

        return "my-page/messageListReceive";
    }



    @GetMapping("messageWrite")
    public String getMessageWrite(Model model) {
        model.addAttribute("sendMessageDTO", new SendMessageDTO());
        return "my-page/messageWrite";
    }

    @PostMapping("messageWrite")
    public String sendMessage(@ModelAttribute SendMessageDTO sendMessageDTO, HttpSession session) {
        // 로그인된 사용자 정보 가져오기
        MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

        // 로그인된 사용자가 없으면 에러 페이지로 리다이렉트
        if (loginUser == null) {
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
        sendMessageDTO.setSenderId(loginUser.getId());

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
