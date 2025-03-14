package com.app.pickcourse.repository;

import com.app.pickcourse.domain.dto.RealFeedDTO;
import com.app.pickcourse.domain.vo.FeedVO;
import com.app.pickcourse.mapper.RealFeedMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class RealFeedDAO {
    private final RealFeedMapper realFeedMapper;

//    여행후기작성
    public void saveReview(FeedVO feedVO, RealFeedDTO realFeedDTO) {
        realFeedMapper.insertReview(feedVO, realFeedDTO);
    }
}
