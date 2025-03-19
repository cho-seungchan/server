package com.app.pickcourse.repository;

import com.app.pickcourse.domain.vo.ReceiveMessageFileVO;
import com.app.pickcourse.domain.vo.SendMessageFileVO;
import com.app.pickcourse.mapper.ReceiveMessageFileMapper;
import com.app.pickcourse.mapper.SendMessageFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReceiveMessageFileDAO {
    private final ReceiveMessageFileMapper receiveMessageFileMapper;

//    추가하기
    public void saveMessageFile(ReceiveMessageFileVO receiveMessageFileVO) {
        receiveMessageFileMapper.insert(receiveMessageFileVO);
    }
}
