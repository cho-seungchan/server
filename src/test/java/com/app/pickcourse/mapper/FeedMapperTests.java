package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.FeedDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class FeedMapperTests {
    @Autowired
    private FeedMapper mapper;

    @Test
    public void postFeedWrite(){
        FeedDTO feedDTO = new FeedDTO();
        feedDTO.setFeedContent("아름다운 밤이에요test");
        mapper.postFeedWrite(feedDTO);
    }

    @Test
    public void postFeedModify(){
        FeedDTO feedDTO = mapper.getFeedModify(51l);
        log.info("feedDTO:{}",feedDTO);
    }
}
