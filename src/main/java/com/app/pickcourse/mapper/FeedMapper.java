package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.FeedDTO;
import com.app.pickcourse.domain.dto.FeedListDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedMapper {

    void postFeedWrite(FeedDTO feedDTO);

    FeedDTO getFeedModify(Long id);

    void PostFeedModify(FeedDTO feedDTO);

    List<FeedListDTO> getFeedList();

    List<FeedListDTO> getFeedModifyList(Long id);

    void deleteFeedModifyList(Long id);
}
