package com.app.pickcourse.service;

import com.app.pickcourse.domain.dto.RealFeedDTO;
import com.app.pickcourse.domain.vo.FeedVO;
import com.app.pickcourse.repository.FeedDAO;
import jakarta.jws.Oneway;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class RealFeedServiceTests {
    @Autowired
    private RealFeedService realFeedService;
    @Autowired
    private FeedDAO feedDAO;

    @Test
    public void testWriteReview() {
        FeedVO feedVO = new FeedVO();
        RealFeedDTO realFeedDTO = new RealFeedDTO();

        feedVO.setFeedContent("후기테스트1");
        feedDAO.save(feedVO);

        log.info(feedVO.getId().toString());

        realFeedDTO.setId(feedVO.getId());

        log.info(realFeedDTO.getId().toString());
        realFeedDTO.setPlanId(113L);
        realFeedDTO.setMemberId(1L);


        realFeedService.writeReview(feedVO, realFeedDTO);
    }
}
