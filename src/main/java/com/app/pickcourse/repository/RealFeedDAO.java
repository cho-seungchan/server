package com.app.pickcourse.repository;

import com.app.pickcourse.domain.vo.FeedVO;
import com.app.pickcourse.mapper.GeneralFeedMapper;
import com.app.pickcourse.mapper.RealFeedMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RealFeedDAO {
    private final RealFeedMapper realFeedMapper;

    public void postFeedWrite(Long id, Long loginId, Long planId) {
        realFeedMapper.postFeedWrite(id, loginId, planId);
    }
}
