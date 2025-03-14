package com.app.pickcourse.domain.dto;

import com.app.pickcourse.domain.vo.QuestionVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter @ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class QuestionDTO {
    @EqualsAndHashCode.Include
    Long id;
    String content;
    Long memberId;
    Long planId;
    String createDate;

    public QuestionVO toVO() {
        QuestionVO questionVO = new QuestionVO();

        questionVO.setId(id);
        questionVO.setContent(content);
        questionVO.setMemberId(memberId);
        questionVO.setPlanId(planId);
        questionVO.setCreateDate(createDate);

        return questionVO;
    }
}
