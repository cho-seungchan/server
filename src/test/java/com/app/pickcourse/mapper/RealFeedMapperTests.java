package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.FeedDTO;
import com.app.pickcourse.domain.dto.FeedListDTO;
import com.app.pickcourse.domain.dto.ReviewDTO;
import com.app.pickcourse.domain.vo.FeedVO;
import com.app.pickcourse.domain.vo.MemberVO;
import com.app.pickcourse.domain.vo.PlanVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class RealFeedMapperTests {
    @Autowired
    private RealFeedMapper mapper;
    @Autowired
    private FeedMapper feedMapper;

    @Test
    public void postFeedWrite(){
        FeedDTO feedDTO = new FeedDTO();
        feedDTO.setFeedContent("아름다운 밤이에요test1");
        feedMapper.postFeedWrite(feedDTO.toFeedVO());

        MemberVO memberVO = new MemberVO();
        memberVO.setId(1l);
        PlanVO planVO = new PlanVO();
        planVO.setId(113l);
        mapper.postFeedWrite(feedDTO.getId(),memberVO.getId(),planVO.getId());
    }

    @Test
    public void getFeedList(){
        List<FeedListDTO> feedList = mapper.getFeedList();
        feedList.forEach(System.out::println);
    }

//    @Test
//    public void getFeedModifyList(){
//        List<FeedListDTO> list = mapper.getFeedModifyList(47l);
//        list.forEach(System.out::println);
//    }
//
//    @Test
//    public void getReviewList(){
//        List<ReviewDTO> list = mapper.getReviewList(21l);
//        list.forEach(System.out::println);
//    }

    @Test
    public void getReviewModify(){
        Optional<ReviewDTO> reviewDTO = mapper.getReviewModify(46l);
        ReviewDTO review = reviewDTO.orElseThrow(() -> new RuntimeException("ReviewDTO not found"));
        log.info("reviewDTO:{}",review);
    }
}
