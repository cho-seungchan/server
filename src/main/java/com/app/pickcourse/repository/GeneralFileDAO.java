package com.app.pickcourse.repository;

import com.app.pickcourse.mapper.FileMapper;
import com.app.pickcourse.mapper.GeneralFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class GeneralFileDAO {
    private final GeneralFileMapper generalFileMapper;

    public void postFeedWrite(Long id, Long feedId) {
        generalFileMapper.postFeedWrite(id, feedId);
    }
}
