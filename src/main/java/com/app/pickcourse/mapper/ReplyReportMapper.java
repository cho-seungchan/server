package com.app.pickcourse.mapper;

import org.apache.ibatis.annotations.Param;

public interface ReplyReportMapper {

    void saveReplyReport(@Param("id") Long id, @Param("reportedId") Long replyId, @Param("memberId") Long memberId);
}
