package com.app.pickcourse.repository;

import com.app.pickcourse.domain.vo.QuestionVO;
import com.app.pickcourse.mapper.QuestionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class QuestionDAO {
    private final QuestionMapper questionMapper;
//    댓글작성
    public void saveQuestion(QuestionVO questionVO) {
        questionMapper.insertQuestion(questionVO);
    }
}
