package com.app.pickcourse.domain.vo;

import lombok.*;

@NoArgsConstructor
@Getter @Setter @ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class VolunteerPrepareVO {
    @EqualsAndHashCode.Include
    private Long   id;

    private String volunteerPrepareContent;
    private Long   courseId;
    private String createdDate;
    private String updatedDate;
}