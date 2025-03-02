package com.app.pickcourse.domain.vo;

import lombok.*;

@NoArgsConstructor
@Getter @Setter @ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class VolunteerIncludeVO {
    @EqualsAndHashCode.Include
    private Long   id;

    private String volunteerIncludeContent;
    private Long   courseId;
    private String createdDate;
    private String updatedDate;
}