package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.ReplyDTO;
import com.app.pickcourse.domain.vo.FeedVO;
import com.app.pickcourse.domain.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class RealReplyMapperTests {
    @Autowired
    private RealReplyMapper mapper;
    @Autowired
    private ReplyMapper replyMapper;

    @Test
    public void postReplyListTest() {
        ReplyDTO replyDTO = new ReplyDTO();
        replyDTO.setReplyContent("아름다운 밤이에요test");
        replyMapper.postReplyList(replyDTO);

        MemberVO memberVO = new MemberVO();
        memberVO.setId(1l);
        FeedVO feedVO = new FeedVO();
        feedVO.setId(7l);
        mapper.postReplyList(replyDTO.getId(),memberVO.getId(),feedVO.getId());
    }

    @Test
    public void getReplyListTest() {
        List<ReplyDTO> replyList = mapper.getReplyList(7l);
        replyList.forEach(System.out::println);
    }
}
