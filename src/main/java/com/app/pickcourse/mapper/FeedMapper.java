package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.vo.FeedVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FeedMapper {

    void postFeedWrite(FeedVO feedVO);
}
