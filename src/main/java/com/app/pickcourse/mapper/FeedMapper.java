package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.FeedDTO;
import com.app.pickcourse.domain.dto.FeedListDTO;
import com.app.pickcourse.domain.vo.FeedVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedMapper {

    void postFeedWrite(FeedVO feedVO);

    FeedDTO getFeedModify(Long id);

    void PostFeedModify(FeedVO feedVO);

    List<FeedListDTO> getFeedList();

    List<FeedListDTO> getFeedModifyList(Long id);

    void deleteFeedModifyList(Long id);
}
