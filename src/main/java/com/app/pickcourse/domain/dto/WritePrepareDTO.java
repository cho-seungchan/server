package com.app.pickcourse.domain.dto;

import com.app.pickcourse.domain.vo.WritePrepareVO;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class WritePrepareDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private List<WritePrepareVO> prepareContent;
    private Long planId;

}
