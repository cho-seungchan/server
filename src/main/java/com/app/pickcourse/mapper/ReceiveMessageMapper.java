package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.ReceiveMessageDTO;
import com.app.pickcourse.domain.dto.SendMessageDTO;
import com.app.pickcourse.domain.vo.ReceiveMessageVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReceiveMessageMapper {

    // 받은 메시지 저장
    void insertReceiveMessage(ReceiveMessageVO receiveMessageVO);

    // 받은 메시지 조회
    List<ReceiveMessageDTO> selectReceiveMessagesByReceiverId(Long receiverId);

    // 받은 메시지 삭제
    void deleteReceiveMessageById(Long messageId);

    // 받은 메시지 개수 조회 (페이징을 위해 추가)
    int countByReceiverId(Long receiverId);

    // 페이징을 적용한 받은 메시지 조회
    List<ReceiveMessageDTO> findByReceiverIdWithPagination(
            Long receiverId,
            int startRow,
            int rowCount
    );


}
