package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.FeedListDTO;
import com.app.pickcourse.domain.dto.ReviewDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RealFeedMapper {

    // postReviewWrite(리얼후기 작성)
    void postFeedWrite(@Param("feedId") Long feedId, @Param("memberId") Long memberId, @Param("planId") Long planId);

    List<FeedListDTO> getFeedList();

    List<FeedListDTO> getFeedModifyList(Long id);

    List<ReviewDTO>  getReviewList(Long memberId);

    ReviewDTO getReviewModify(Long id);
}
