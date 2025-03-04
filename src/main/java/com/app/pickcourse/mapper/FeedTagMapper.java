package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.vo.FeedTagVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FeedTagMapper {

    void postFeedWrite(FeedTagVO tagVO);
}
