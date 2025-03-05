package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.FeedDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FeedMapper {

    void postFeedWrite(FeedDTO feedDTO);

    FeedDTO getFeedModify(@Param("id") Long id);
}
