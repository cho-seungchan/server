package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.ReplyDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RealReplyMapper {

    void postReplyList(@Param("replyId") Long replyId, @Param("memberId") Long memberId, @Param("feedId") Long feedId);

    List<ReplyDTO> getReplyList(Long feedId);
}
