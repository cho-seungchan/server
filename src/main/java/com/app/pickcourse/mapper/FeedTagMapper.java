package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.vo.FeedTagVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FeedTagMapper {

    void postFeedWrite(FeedTagVO tagVO);

    List<FeedTagVO> getFeedList(@Param("feedId") Long feedId);
}
