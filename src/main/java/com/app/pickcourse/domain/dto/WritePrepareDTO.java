package com.app.pickcourse.domain.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class WritePrepareDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String[] prepareContent;
    private Long planId;
}
