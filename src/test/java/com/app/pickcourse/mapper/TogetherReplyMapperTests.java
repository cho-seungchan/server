package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.ReplyListDTO;
import com.app.pickcourse.domain.vo.FeedVO;
import com.app.pickcourse.domain.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class TogetherReplyMapperTests {
    @Autowired
    private TogetherReplyMapper mapper;
    @Autowired
    private ReplyMapper replyMapper;

    @Test
    public void postReplyListTest() {
        ReplyListDTO replyListDTO = new ReplyListDTO();
        replyListDTO.setReplyContent("아름다운 밤이에요test");
        replyMapper.postReplyList(replyListDTO);

        MemberVO memberVO = new MemberVO();
        memberVO.setId(1l);
        FeedVO feedVO = new FeedVO();
        feedVO.setId(4l);
        mapper.postReplyList(replyListDTO.getId(),memberVO.getId(),feedVO.getId());
    }

    @Test
    public void getReplyListTest() {
        List<ReplyListDTO> replyList = mapper.getReplyList(4l);
        replyList.forEach(System.out::println);
    }
}
