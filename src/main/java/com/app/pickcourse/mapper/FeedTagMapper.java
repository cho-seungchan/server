package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.FeedDTO;
import com.app.pickcourse.domain.vo.FeedTagVO;
import com.app.pickcourse.domain.vo.FeedVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FeedTagMapper {

    void postFeedWrite(FeedTagVO tagVO);

    List<FeedTagVO> getFeedList(Long feedId);

    // 태그 전체 삭제 :: 후 전체 삽입 => void postFeedWrite(FeedTagVO tagVO)
    void postModifyFeed(Long feedId);
}
