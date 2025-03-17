package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.ReplyListDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GeneralReplyMapper {

    void postReplyList(@Param("replyId") Long replyId, @Param("memberId") Long memberId, @Param("feedId") Long feedId);

    List<ReplyListDTO> getReplyList(Long feedId);

    void deleteReplyList(Long id);
}
