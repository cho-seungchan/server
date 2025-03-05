package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.FeedListDto;
import com.app.pickcourse.domain.vo.FeedVO;
import com.app.pickcourse.domain.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class TogetherFeedMapperTests {
    @Autowired
    private TogetherFeedMapper mapper;
    @Autowired
    private FeedMapper feedMapper;

    @Test
    public void postFeedWrite(){
        FeedVO feedVO = new FeedVO();
        feedVO.setFeedContent("아름다운 밤이에요test");
        feedMapper.postFeedWrite(feedVO);

        MemberVO memberVO = new MemberVO();
        memberVO.setId(21l);
        mapper.postFeedWrite(feedVO.getId(),memberVO.getId());
    }

    @Test
    public void getFeedList(){
        List<FeedListDto> feedList = mapper.getFeedList();
        feedList.forEach(System.out::println);
    }
}
