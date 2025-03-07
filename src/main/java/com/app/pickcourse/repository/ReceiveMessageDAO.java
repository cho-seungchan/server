package com.app.pickcourse.repository;

import com.app.pickcourse.domain.dto.ReceiveMessageDTO;
import com.app.pickcourse.domain.vo.ReceiveMessageVO;
import com.app.pickcourse.mapper.ReceiveMessageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReceiveMessageDAO {
    private final ReceiveMessageMapper receiveMessageMapper;

    // 받은 메시지 저장
    public void save(ReceiveMessageVO receiveMessageVO) {
        receiveMessageMapper.insertReceiveMessage(receiveMessageVO);
    }

    // 받은 메시지 조회
    public List<ReceiveMessageDTO> findByReceiverId(Long receiverId) {
        return receiveMessageMapper.selectReceiveMessagesByReceiverId(receiverId);
    }

    // 받은 메시지 삭제
    public void delete(Long id) {
        receiveMessageMapper.deleteReceiveMessageById(id);
    }
}
