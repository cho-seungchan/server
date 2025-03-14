package com.app.pickcourse.service;

import com.app.pickcourse.domain.dto.ReceiveMessageDTO;
import com.app.pickcourse.domain.dto.ReceivePaginationDTO;
import com.app.pickcourse.domain.dto.SendMessageDTO;
import com.app.pickcourse.domain.dto.SendPaginationDTO;
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
import com.app.pickcourse.util.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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

//    // 받은 메시지 목록과 페이지네이션 데이터를 함께 반환하는 메서드
//    public Map<String, Object> getReceivedMessages(Long receiverId, int page) {
//        int totalMessages = getReceiveMessageCount(receiverId);
//
//        Pagination pagination = new Pagination();
//        pagination.setPage(page);
//        pagination.create(totalMessages);
//
//        List<ReceiveMessageDTO> receivedMessages = findReceiveMessages(receiverId, page, 5);
//
//        return Map.of(
//                "receivedMessages", receivedMessages,
//                "pagination", pagination
//        );
//    }

    // 받은 메시지 개수 조회
    public int getReceiveMessageCount(Long receiverId) {
        return receiveMessageDAO.countByReceiverId(receiverId);
    }

    // 받은 메시지 목록 조회 (페이지네이션 적용)
    public List<ReceiveMessageDTO> findReceiveMessages(Long receiverId, Pagination pagination) {
        return receiveMessageDAO.findByReceiverIdWithPagination(receiverId, pagination);
    }


//    // 받은 메시지 조회
//    public List<ReceiveMessageDTO> findReceiveMessages(Long receiverId, int page, int rowCount) {
//        int startRow = (page - 1) * rowCount;
//        return receiveMessageDAO.findByReceiverIdWithPagination(receiverId, startRow, rowCount);
//    }
//
//    // 받은 메시지 개수 조회
//    public int getReceiveMessageCount(Long receiverId) {
//        return receiveMessageDAO.countByReceiverId(receiverId);
//    }

    // 보낸 메시지 조회
    public List<SendMessageDTO> findSendMessages(Long senderId, int page, int rowCount) {
        int startRow = (page - 1) * rowCount;
        return sendMessageDAO.findBySenderIdWithPagination(senderId, startRow, rowCount);
    }

    // 보낸 메시지 개수 조회
    public int getSendMessageCount(Long senderId) {
        return sendMessageDAO.countBySenderId(senderId);
    }

//    페이지네이션 리스트
    public ReceivePaginationDTO getReceiveList(Long receiverId, Pagination pagination) {
        ReceivePaginationDTO receivePaginationDTO = new ReceivePaginationDTO();

        pagination.create(receiveMessageDAO.findTotalReceiveMessage(receiverId));
        receivePaginationDTO.setPagination(pagination);
        receivePaginationDTO.setReceiveMessages(receiveMessageDAO.findAllReceiveMessage(receiverId, pagination));
        return receivePaginationDTO;
    }

    public int getTotalReceiveMessage(Long receiverId) {
        return receiveMessageDAO.findTotalReceiveMessage(receiverId);
    }

    //    페이지네이션 리스트
    public SendPaginationDTO getSendList(Long senderId, Pagination pagination) {
        SendPaginationDTO sendPaginationDTO = new SendPaginationDTO();

        pagination.create(sendMessageDAO.findTotalSendMessage(senderId));
        sendPaginationDTO.setPagination(pagination);
        sendPaginationDTO.setSendMessages(sendMessageDAO.findAllSendMessage(senderId, pagination));
        return sendPaginationDTO;
    }

    public int getTotalSendMessage(Long senderId) {
        return sendMessageDAO.findTotalSendMessage(senderId);
    }
}
