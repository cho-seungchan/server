package com.app.pickcourse.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter @Setter @ToString
public class CourseListDTO {
    private Long      id;
    private Character courseType;
    private Character courseIsVolunteer;
    private String    courseName;
    private String    courseDistance;
    private String    courseSchedule;
    private String    courseTheme;
    private String    courseContent;
    private String    courseFilePath;
    private String    courseFileSize;
    private String    courseFileName;
    private Long      adminId;
    private String    adminName;
    private String    createdDate;
    private String    updatedDate;
}
