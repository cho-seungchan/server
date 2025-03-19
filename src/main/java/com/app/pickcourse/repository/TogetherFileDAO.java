package com.app.pickcourse.repository;

import com.app.pickcourse.mapper.GeneralFileMapper;
import com.app.pickcourse.mapper.TogetherFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TogetherFileDAO {
    private final TogetherFileMapper togetherFileMapper;

    public void postFeedWrite(Long id, Long feedId) {
        togetherFileMapper.postFeedWrite(id, feedId);
    }
}
