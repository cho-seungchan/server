package com.app.pickcourse.repository;

import com.app.pickcourse.domain.vo.FeedVO;
import com.app.pickcourse.mapper.GeneralFeedMapper;
import com.app.pickcourse.mapper.TogetherFeedMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TogetherFeedDAO {
    private final TogetherFeedMapper togetherFeedMapper;

    public void postFeedWrite(Long loginId, FeedVO feedVO) {
        togetherFeedMapper.postFeedWrite(feedVO.getId(), loginId);
    }
}
