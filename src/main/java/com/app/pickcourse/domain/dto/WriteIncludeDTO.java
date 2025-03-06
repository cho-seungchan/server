package com.app.pickcourse.domain.dto;

import com.app.pickcourse.domain.vo.WriteIncludeVO;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter @Setter @ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class WriteIncludeDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private List<WriteIncludeVO> includeContent;
    private Long planId;
}
