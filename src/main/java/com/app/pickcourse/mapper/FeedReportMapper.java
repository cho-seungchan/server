package com.app.pickcourse.mapper;

import org.apache.ibatis.annotations.Param;

public interface FeedReportMapper {

    void saveFeedReport(@Param("id") Long id, @Param("reportedId") Long feedId, @Param("memberId") Long memberId);
}
