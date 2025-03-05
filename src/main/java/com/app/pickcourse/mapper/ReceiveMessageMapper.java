package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.ReceiveMessageDTO;
import com.app.pickcourse.domain.dto.SendMessageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReceiveMessageMapper {

    // 받은 쪽지 저장
    public void insertReceiveMessage(ReceiveMessageDTO receiveMessageDTO);

    // 받은 쪽지 조회
    public List<ReceiveMessageDTO> selectMessageByReceiverId(Long receiverId);

    // 받은 쪽지 삭제
    public void deleteReceivedMessage(Long messageId);

}
