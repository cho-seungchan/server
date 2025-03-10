package com.app.pickcourse.domain.vo;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class VolunteerScheduleVO {
    @EqualsAndHashCode.Include
    private Long id;
    private int scheduleDay;
    private String scheduleContent;
    private Long volunteerId;
}
