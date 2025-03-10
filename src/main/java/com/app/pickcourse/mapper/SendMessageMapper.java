package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.SendMessageDTO;
import com.app.pickcourse.domain.vo.SendMessageVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SendMessageMapper {

    // 보낸 메시지 저장
    void insertSendMessage(SendMessageVO sendMessageVO);

    // 보낸 메시지 조회
    SendMessageVO selectSendMessageById(Long messageId);

    // 보낸 메시지 목록 조회
    List<SendMessageDTO> selectSendMessagesBySenderId(Long senderId);

    // 보낸 메시지 삭제
    void deleteSendMessageById(Long messageId);
}
