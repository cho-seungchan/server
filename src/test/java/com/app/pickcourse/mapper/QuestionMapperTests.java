package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.vo.QuestionVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class QuestionMapperTests {
    @Autowired
    QuestionMapper questionMapper;

    @Test
    public void testInsertQuestion() {
        QuestionVO questionVO = new QuestionVO();

        questionVO.setContent("테스트1");
        questionVO.setMemberId(1L);
        questionVO.setPlanId(113L);

        questionMapper.insertQuestion(questionVO);
    }
}
