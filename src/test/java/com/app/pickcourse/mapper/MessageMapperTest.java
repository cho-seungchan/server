package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.ReceiveMessageDTO;
import com.app.pickcourse.domain.dto.SendMessageDTO;
import com.app.pickcourse.domain.vo.MessageVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class MessageMapperTest {

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private ReceiveMessageMapper receiveMapper;

    @Autowired
    private SendMessageMapper sendMapper;

    @Test
    public void testSelectReceivedMessages() {
        Long receiverId = 3L; // 받은 사람 ID (테스트 계정)

        // 받은 쪽지 조회
        List<ReceiveMessageDTO> receivedMessages = receiveMapper.selectMessageByReceiverId(receiverId);

        // 로그 출력
        if (!receivedMessages.isEmpty()) {
            log.info("받은 쪽지 조회 성공! 총 {}개의 메시지", receivedMessages.size());
            for (ReceiveMessageDTO message : receivedMessages) {
                log.info("메시지 ID: {}, 보낸 사람: {}, 내용: {}",
                        message.getMessageId(), message.getSenderNickname(), message.getContent());
            }
        } else {
            log.info("받은 쪽지가 없습니다.");
        }
    }

//  메세지 보내기
    @Test
    public void testSendMessage() {
        //메시지 보내기
        SendMessageDTO sendMessageDTO = new SendMessageDTO();
        sendMessageDTO.setSenderId(1L);
        sendMessageDTO.setSenderEmail("2ndtest@2ndtest.com");
        sendMessageDTO.setSenderNickname("홍길동");
        sendMessageDTO.setReceiverId(3L);
        sendMessageDTO.setReceiverEmail("test@test.com");
        sendMessageDTO.setReceiverNickname("Test");
        sendMessageDTO.setContent("안녕못해요");

        //TBL_MESSAGE(슈퍼키)에 먼저 메시지 저장
        MessageVO messageVO = new MessageVO();
        messageVO.setContent(sendMessageDTO.getContent());
        messageMapper.insertMessage(messageVO);

        //생성된 messageId 설정
        sendMessageDTO.setMessageId(messageVO.getMessageId());

        //보낸 쪽지함에 저장 (TBL_SEND_MESSAGE)
        sendMapper.insertSendMessage(sendMessageDTO);

        //받은 쪽지함에도 저장 (TBL_RECEIVE_MESSAGE)
        ReceiveMessageDTO receiveMessageDTO = new ReceiveMessageDTO();
        receiveMessageDTO.setMessageId(sendMessageDTO.getMessageId());
        receiveMessageDTO.setSenderId(sendMessageDTO.getSenderId());
        receiveMessageDTO.setSenderEmail(sendMessageDTO.getSenderEmail());
        receiveMessageDTO.setSenderNickname(sendMessageDTO.getSenderNickname());
        receiveMessageDTO.setReceiverId(sendMessageDTO.getReceiverId());
        receiveMessageDTO.setReceiverEmail(sendMessageDTO.getReceiverEmail());
        receiveMessageDTO.setReceiverNickname(sendMessageDTO.getReceiverNickname());
        receiveMessageDTO.setContent(sendMessageDTO.getContent());

        receiveMapper.insertReceiveMessage(receiveMessageDTO);

        //로그 출력
        log.info("보낸 쪽지 저장 완료! ID: {}", sendMessageDTO.getMessageId());
        log.info("받은 쪽지 저장 완료! ID: {}", receiveMessageDTO.getMessageId());
    }

    @Test
    public void testReceiveMessageDelete() {

    }

}
