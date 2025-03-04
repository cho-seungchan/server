package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.FeedListDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RealFeedMapper {

    void postFeedWrite(@Param("feedId") Long feedId, @Param("memberId") Long memberId, @Param("planId") Long planId);

    List<FeedListDto> getFeedList();
}
