package com.app.pickcourse.service;

import com.app.pickcourse.domain.dto.ReceiveMessageDTO;
import com.app.pickcourse.domain.dto.SendMessageDTO;
import com.app.pickcourse.domain.vo.MessageVO;
import com.app.pickcourse.domain.vo.ReceiveMessageVO;
import com.app.pickcourse.domain.vo.SendMessageVO;
import com.app.pickcourse.mapper.MessageMapper;
import com.app.pickcourse.mapper.ReceiveMessageMapper;
import com.app.pickcourse.mapper.SendMessageMapper;
import com.app.pickcourse.repository.MemberDAO;
import com.app.pickcourse.repository.MessageDAO;
import com.app.pickcourse.repository.ReceiveMessageDAO;
import com.app.pickcourse.repository.SendMessageDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class MessageService {

    private final MessageDAO messageDAO; // 슈퍼키 DAO
    private final SendMessageDAO sendMessageDAO; // 보낸 메시지 DAO
    private final ReceiveMessageDAO receiveMessageDAO; // 받은 메시지 DAO
    private final MemberDAO memberDAO;

    // 메세지 보내기
    public void sendMessage(SendMessageDTO sendMessageDTO) {
        // 메시지 내용 저장 (슈퍼키)
        MessageVO messageVO = new MessageVO();
        messageVO.setContent(sendMessageDTO.getContent());
        messageDAO.save(messageVO); // DAO 사용

        // 보낸 메시지 테이블에 저장
        SendMessageVO sendMessageVO = sendMessageDTO.toVO(); // DTO -> VO 변환
        sendMessageVO.setId(messageVO.getId()); // 슈퍼키 설정
        sendMessageDAO.save(sendMessageVO); // DAO 사용

        // 받은 메시지 테이블에도 저장
        ReceiveMessageVO receiveMessageVO = new ReceiveMessageVO();
        receiveMessageVO.setId(messageVO.getId()); // 슈퍼키 설정
        receiveMessageVO.setSenderId(sendMessageDTO.getSenderId()); // 보낸 사람 ID
        receiveMessageVO.setReceiverId(sendMessageDTO.getReceiverId()); // 받는 사람 ID
        receiveMessageVO.setContent(sendMessageDTO.getContent()); // 메시지 내용
        receiveMessageDAO.save(receiveMessageVO); // DAO 사용
    }

    // 받은 메세지 조회
    public List<ReceiveMessageDTO> findReceiveMessageByReceiverId(Long receiverId) {
        return receiveMessageDAO.findByReceiverId(receiverId);
    }

    // 보낸 메세지 조회
    public List<SendMessageDTO> findSendMessageBySenderId(Long senderId) {
        return sendMessageDAO.findBySenderId(senderId);
    }

    // 받은 메세지 삭제
    public void deleteReceiveMessageById(Long id) {
        receiveMessageDAO.delete(id);
    }

    // 보낸 메세지 삭제
    public void deleteSendMessageById(Long id) {
        sendMessageDAO.delete(id);
    }

    // 슈퍼키 메세지 삭제
    public void deleteMessageById(Long id) {
        messageDAO.delete(id);
    }


    // ID로 이메일을 찾고 이메일로 메시지 전송
    public void sendMessageByEmail(SendMessageDTO sendMessageDTO) {
        Long receiverId = memberDAO.findIdByEmail(sendMessageDTO.getReceiverEmail())
                .orElseThrow(() -> new RuntimeException("해당 이메일을 가진 사용자가 없습니다."));

        sendMessageDTO.setReceiverId(receiverId);

        sendMessage(sendMessageDTO);
    }
}
