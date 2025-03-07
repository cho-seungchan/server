package com.app.pickcourse.service;

import com.app.pickcourse.domain.dto.ReceiveMessageDTO;
import com.app.pickcourse.domain.dto.SendMessageDTO;
import com.app.pickcourse.domain.vo.MessageVO;
import com.app.pickcourse.domain.vo.ReceiveMessageVO;
import com.app.pickcourse.domain.vo.SendMessageVO;
import com.app.pickcourse.repository.MessageDAO;
import com.app.pickcourse.repository.ReceiveMessageDAO;
import com.app.pickcourse.repository.SendMessageDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class MessageServiceTests {

    @Autowired
    private MessageService messageService;

    @Autowired
    private SendMessageDAO sendMessageDAO;

    @Autowired
    private ReceiveMessageDAO receiveMessageDAO;

    @Autowired
    private MessageDAO messageDAO;

    private SendMessageDTO sendMessageDTO;
    private ReceiveMessageDTO receiveMessageDTO;

    @Test
    public void testSendMessage() {

        SendMessageDTO sendMessageDTO = new SendMessageDTO();
        sendMessageDTO.setSenderId(1L);
        sendMessageDTO.setSenderEmail("test@test.com");
        sendMessageDTO.setSenderNickname("Test");
        sendMessageDTO.setReceiverId(2L);
        sendMessageDTO.setReceiverEmail("dao@test.com");
        sendMessageDTO.setReceiverNickname("다오수정테스트");
        sendMessageDTO.setContent("서비스 테스트 메시지입니다.123");

        messageService.sendMessage(sendMessageDTO);

        List<SendMessageVO> sentMessages = sendMessageDAO.findBySenderId(sendMessageDTO.getSenderId());
        log.info("보낸 메시지 조회 성공: {}", sentMessages);

        List<ReceiveMessageDTO> receivedMessages = receiveMessageDAO.findByReceiverId(sendMessageDTO.getReceiverId());
        log.info("받은 메시지 조회 성공: {}", receivedMessages);

        MessageVO message = messageDAO.findById(sentMessages.get(0).getId());
        log.info("슈퍼키 메시지 조회 성공: {}", message);
    }

    @Test
    public void selectReceiveMessageByReceiverId() {
        List<ReceiveMessageDTO> receiveMessages = receiveMessageDAO.findByReceiverId(2L);

        if (!receiveMessages.isEmpty()) {
            log.info("총 {}개의 받은 메시지 조회 성공!", receiveMessages.size());
            for (ReceiveMessageDTO message : receiveMessages) {
                log.info("메시지 ID: {}, 보낸 사람 ID: {}, 내용: {}",
                        message.getId(), message.getSenderId(), message.getContent());
            }
        } else {
            log.info("받은 메시지가 없습니다.");
        }
    }

    @Test
    public void selectSendMessageBySenderId() {
        List<SendMessageVO> sendMessages = sendMessageDAO.findBySenderId(1L);

        if (!sendMessages.isEmpty()) {
            log.info("총 {}개의 보낸 메시지 조회 성공!", sendMessages.size());
            for (SendMessageVO message : sendMessages) {
                log.info("메시지 ID: {}, 받은 사람 ID: {}, 내용: {}",
                        message.getId(), message.getReceiverId(), message.getContent());
            }
        } else {
            log.info("보낸 메시지가 없습니다.");
        }
    }

//    보낸메세지 삭제
    @Test
    public void deleteSendMessageById() {
        Long id = 21L;

        messageService.deleteSendMessageById(id);

        List<SendMessageVO> leftMessages = sendMessageDAO.findBySenderId(1L);
        log.info("남은 보낸 메시지: {}", leftMessages);
    }

//    받은메세지 삭제
    @Test
    public void deleteReceiveMessageById() {
        Long id = 21L;
        messageService.deleteReceiveMessageById(id);

        List<ReceiveMessageDTO> leftMessages = receiveMessageDAO.findByReceiverId(1L);
        log.info("남은 받은 메세지: {}", leftMessages);

    }

//    슈퍼키 메세지 삭제
    @Test
    public void deleteMessageById() {
        Long id = 21L;
        messageService.deleteMessageById(id);

        List<MessageVO> leftMessages = messageDAO.findAll();
        log.info("남은 메세지: {}", leftMessages);
    }


}






