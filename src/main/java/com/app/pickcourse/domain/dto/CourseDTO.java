package com.app.pickcourse.domain.dto;

import com.app.pickcourse.domain.vo.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@Getter @Setter @ToString
public class CourseDTO {
    CourseVO                 course;
    VolunteerVO              volunteer;
    List<PathVO>             paths;
    List<VolunteerExcludeVO> volunteerExcludes;
    List<VolunteerIncludeVO> volunteerIncludes;
    List<VolunteerPrepareVO> volunteerPrepares;
}
