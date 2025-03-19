package com.app.pickcourse.repository;

import com.app.pickcourse.domain.dto.ReplyDetailDTO;
import com.app.pickcourse.domain.vo.FeedVO;
import com.app.pickcourse.mapper.FeedMapper;
import com.app.pickcourse.mapper.TagMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TagDAO {
    private final TagMapper tagMapper;

    public void postFeedWrite(String tagContent, Long feedId) {
        tagMapper.postFeedWrite(tagContent, feedId);
    }
}
