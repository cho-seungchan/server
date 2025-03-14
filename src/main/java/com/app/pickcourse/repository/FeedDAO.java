package com.app.pickcourse.repository;

import com.app.pickcourse.domain.dto.FeedDTO;
import com.app.pickcourse.domain.vo.FeedVO;
import com.app.pickcourse.mapper.FeedMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class FeedDAO {
    private final FeedMapper feedMapper;

//    추가
    public void save(FeedVO feedVO) {
        feedMapper.postFeedWrite(feedVO);
    }

}
