package com.app.pickcourse.repository;

import com.app.pickcourse.mapper.GeneralReplyMapper;
import com.app.pickcourse.mapper.RealReplyMapper;
import com.app.pickcourse.mapper.TogetherReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RealReplyDAO {
    private final RealReplyMapper realReplyMapper;

    public void deleteReplyList(Long id) {
        realReplyMapper.deleteReplyList(id);
    }
}
