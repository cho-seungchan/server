package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.FeedListDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GeneralFeedMapper {

    void postFeedWrite(@Param("feedId") Long feedId, @Param("memberId") Long memberId);

    List<FeedListDTO> getFeedList();
}
