package com.app.pickcourse.controller;


import com.app.pickcourse.domain.dto.*;
import com.app.pickcourse.domain.vo.MemberVO;
import com.app.pickcourse.domain.vo.SendMessageVO;
import com.app.pickcourse.repository.MemberDAO;
import com.app.pickcourse.repository.ReceiveMessageDAO;
import com.app.pickcourse.repository.SendMessageDAO;
import com.app.pickcourse.service.MemberService;
import com.app.pickcourse.service.MessageService;
import com.app.pickcourse.util.Pagination;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/my-page/*")
@RequiredArgsConstructor
@Slf4j
public class MyPageController {
    private final MemberDAO memberDAO;
    private final MessageService messageService;
    private final SendMessageDAO sendMessageDAO;
    private final ReceiveMessageDAO receiveMessageDAO;
    private final MemberService memberService;
    private final MemberVO memberVO;
    private final HttpSession session;

    @GetMapping("changePassword")
    public String getChangePassword(){
        MemberDTO member = (MemberDTO) session.getAttribute("member");
        if (member == null) {
            return "redirect:/login/login";
        }
        return "my-page/changePassword";
    }
    @PostMapping("/changePassword")
    public String changePassword(@RequestParam String oldPassword,
                                 @RequestParam String newPassword,
                                 @RequestParam String newPasswordConfirm,
                                 RedirectAttributes redirectAttributes) {
        MemberDTO member = (MemberDTO) session.getAttribute("member");
        if (member == null) {
            return "redirect:/login/login";
        }

        boolean isCorrectPassword = memberService.checkPassword(member.getId(), oldPassword);
        if (!isCorrectPassword) {
            redirectAttributes.addFlashAttribute("error", "기존 비밀번호가 올바르지 않습니다.");
            return "redirect:/my-page/changePassword";
        }

        if (!newPassword.equals(newPasswordConfirm)) {
            redirectAttributes.addFlashAttribute("error", "새 비밀번호가 일치하지 않습니다.");
            return "redirect:/my-page/changePassword";
        }

        memberService.updatePassword(member.getId(), newPassword);
        session.invalidate();

        redirectAttributes.addFlashAttribute("success", "비밀번호 변경이 완료되었습니다. 새로운 비밀번호로 다시 로그인해주세요.");

        return "redirect:/login/login";
    }


    @GetMapping("heart")
    public void getHeart(){}

    @GetMapping("/messageList_Send")
    public String goToSendListPage(HttpSession session) {
        MemberDTO member = (MemberDTO) session.getAttribute("member");

        if (member == null) {
            return "redirect:/login/login";
        }

        return "my-page/messageList_Send";
    }

    @GetMapping("/messageList_Sends")
    @ResponseBody
    public SendPaginationDTO getSendMessages(Pagination pagination, HttpSession session, HttpServletResponse response)  throws IOException {
        MemberDTO member = (MemberDTO) session.getAttribute("member");
        if (member == null) {
            response.sendRedirect("/login/login");
            return null;
        }

        Long senderId = member.getId();

        return messageService.getSendList(senderId, pagination);
    }




    @GetMapping("/messageListReceive")
    public String goToReceiveListPage(HttpSession session) {
        MemberDTO member = (MemberDTO) session.getAttribute("member");

        if (member == null) {
            return "redirect:/login/login";
        }

        return "my-page/messageListReceive";
    }

    @GetMapping("/messageListReceives")
    @ResponseBody
    public ReceivePaginationDTO getReceivedMessages(Pagination pagination, HttpSession session, HttpServletResponse response)  throws IOException {
        MemberDTO member = (MemberDTO) session.getAttribute("member");
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
        MemberDTO member = (MemberDTO) session.getAttribute("member");

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
    public String getMyMain() {
        MemberDTO member = (MemberDTO) session.getAttribute("member");

        if (member == null) {
            return "redirect:/login/login";
        }

        return "my-page/myMain";
    }

    @GetMapping("/myPageModify")
    public String getMyPageModify(Model model) {
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");

        if (memberDTO == null) {
            return "redirect:/login/login";
        }

        model.addAttribute("memberDTO", memberDTO);
        return "my-page/myPageModify";
    }

    @PostMapping("/myPageModify")
    public String updateProfile(@ModelAttribute MemberDTO memberDTO, Model model, RedirectAttributes redirectAttributes) {

        MemberDTO currentMember = (MemberDTO) session.getAttribute("member");
        String memberNickname = currentMember.getMemberNickname();

        String newNickname = memberDTO.getMemberNickname();

        if (!memberNickname.equals(newNickname)) {
            boolean isDuplicate = memberService.checkNicknameDuplicate(newNickname);
            if (isDuplicate) {
                model.addAttribute("nicknameError", "이미 사용 중인 닉네임입니다.");
                return "my-page/myPageModify";
            }
        }
        String birth = memberDTO.getMemberBirth();
        if (!birth.matches("^(19|20)\\d{2}(\\.?(0[1-9]|1[0-2])\\.?(0[1-9]|[12][0-9]|3[01]))$")) {
            model.addAttribute("birthError", "생년월일은 YYYY.MM.DD 또는 YYYYMMDD 형식으로 입력해주세요.");
            return "my-page/myPageModify";
        }
        memberService.update(memberDTO);
        redirectAttributes.addFlashAttribute("successMessage", "회원 정보가 성공적으로 수정되었습니다.");
        return "redirect:/my-page/myPageModify";
    }






    @GetMapping("myReply")
    public void getMyReply(){}
    @GetMapping("payContent")
    public void getPayContent(){}
    @GetMapping("recruit")
    public void getRecuit(){}

    @GetMapping("userQuit")
    public String getUserQuitForm(){
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");

        if (memberDTO == null) {
            return "redirect:/login/login";
        }
        return "my-page/userQuit";
    }
    @PostMapping("userQuit")
    public String getUserQuit() {

        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");


        if (memberDTO == null) {
            return "redirect:/login/login";
        }

        Long id = memberDTO.getId();
        log.info(id.toString());
        memberService.delete(id);
        session.invalidate();

        return "redirect:/";
    }

}
