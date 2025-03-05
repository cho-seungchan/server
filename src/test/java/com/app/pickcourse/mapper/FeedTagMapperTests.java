package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.FeedListDto;
import com.app.pickcourse.domain.vo.FeedTagVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class FeedTagMapperTests {
    @Autowired
    FeedTagMapper mapper;
    @Autowired
    FeedMapper feedMapper;

    @Test
    public void postFeedWrite() {
        FeedTagVO tagVO = new FeedTagVO();
        tagVO.setFeedTagContent("가벼운");
        tagVO.setFeedId(47l);
        mapper.postFeedWrite(tagVO);
    }

}
