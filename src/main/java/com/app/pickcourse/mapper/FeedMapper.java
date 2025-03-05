package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.FeedDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FeedMapper {

    void postFeedWrite(FeedDTO feedDTO);
}
