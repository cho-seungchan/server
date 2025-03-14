package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.vo.QuestionVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {
//    질문 등록
    public void insertQuestion(QuestionVO questionVO);
}
