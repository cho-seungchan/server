package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.SendMessageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SendMessageMapper {

    // 보낸 쪽지 저장
    public void insertSendMessage(SendMessageDTO sendMessageDTO);

    // 보낸 쪽지 조회
    public List<SendMessageDTO> selectMessageBySenderId(Long senderId);

    // 보낸 쪽지 삭제
    public void deleteSentMessage(Long messageId);
}
