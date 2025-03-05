package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.vo.MessageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MessageMapper {

    // 메시지 저장 (TBL_MESSAGE)
    void insertMessage(MessageVO messageVO);

    // 메시지 조회 (TBL_MESSAGE)
    MessageVO selectMessageById(Long messageId);
}