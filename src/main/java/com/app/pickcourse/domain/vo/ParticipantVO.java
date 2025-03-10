package com.app.pickcourse.domain.vo;

import lombok.*;

@NoArgsConstructor
@Getter @Setter @ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ParticipantVO {
    @EqualsAndHashCode.Include
    private Long id;
    private int memberId;
    private int planId;
    private String createdDate;
    private String updatedDate;
}
