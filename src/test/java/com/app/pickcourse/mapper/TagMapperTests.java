package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.FeedDTO;
import com.app.pickcourse.domain.vo.TagVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@Slf4j
public class TagMapperTests {
    @Autowired
    TagMapper mapper;
    @Autowired
    FeedMapper feedMapper;

    @Test
    public void postFeedWrite() {
        TagVO tagVO = new TagVO();
        tagVO.settagContent("가벼운");
        tagVO.setFeedId(47l);
        mapper.postFeedWrite(tagVO);
    }

    @Test
    public void getFeedModify(){
        Optional<FeedDTO> feedDTO = feedMapper.getFeedModify(51l);
        FeedDTO feed = feedDTO.orElseThrow(() -> new RuntimeException("FeedDTO not found"));
        feed.setFeedTags(mapper.getFeedList(feed.getId()));
        log.info("feedDTO:{}",feed);
    }

    @Test
    public void postFeedModify(){
        mapper.postModifyFeed(42l);
    }
}
