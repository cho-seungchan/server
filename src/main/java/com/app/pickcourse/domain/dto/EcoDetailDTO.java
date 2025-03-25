package com.app.pickcourse.domain.dto;

import com.app.pickcourse.domain.vo.MemberVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
public class EcoDetailDTO {
    private CourseDTO course;
    private List<FeedDTO> feeds;
}
