package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.FeedListDTO;
import com.app.pickcourse.domain.dto.RealFeedDTO;
import com.app.pickcourse.domain.dto.RealDTO;
import com.app.pickcourse.domain.dto.ReviewDTO;
import com.app.pickcourse.domain.vo.FeedVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface RealFeedMapper {

    // postReviewWrite(리얼후기 작성)
    void postFeedWrite(@Param("id") Long id, @Param("memberId") Long memberId, @Param("planId") Long planId);

    Optional<RealDTO> getRealModify(Long id);

    void deleteRealModify(Long id);

    List<FeedListDTO> getFeedList();


    Optional<ReviewDTO> getReviewModify(Long id);

//    플랜후기작성
    public void insertReview(FeedVO feedVO, RealFeedDTO realFeedDTO);

    List<FeedListDTO> getMyFeedList(Long memberId);

}
