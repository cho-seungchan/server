package com.app.pickcourse.domain.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter @ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class WriteExcludeDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String[] excludeContent;
    private Long planId;
}
