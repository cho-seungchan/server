package com.app.pickcourse.repository;

import com.app.pickcourse.domain.dto.ReplyDetailDTO;
import com.app.pickcourse.domain.vo.FeedVO;
import com.app.pickcourse.mapper.FeedMapper;
import com.app.pickcourse.mapper.GeneralFeedMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class GeneralFeedDAO {
    private final GeneralFeedMapper generalFeedMapper;


    public void postFeedWrite(Long loginId, FeedVO feedVO) {
        generalFeedMapper.postFeedWrite(feedVO.getId(), loginId);
    }
}
