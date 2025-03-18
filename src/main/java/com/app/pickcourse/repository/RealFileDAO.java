package com.app.pickcourse.repository;

import com.app.pickcourse.mapper.GeneralFileMapper;
import com.app.pickcourse.mapper.RealFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RealFileDAO {
    private final RealFileMapper realFileMapper;

    public void postFeedWrite(Long id, Long feedId) {
        realFileMapper.postFeedWrite(id, feedId);
    }
}
