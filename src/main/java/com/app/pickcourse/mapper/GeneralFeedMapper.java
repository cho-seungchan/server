package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.vo.FeedVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GeneralFeedMapper {

    void postFeedWrite(@Param("feedId") Long feedId, @Param("memberId") Long memberId);
}
