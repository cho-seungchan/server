package com.app.pickcourse.admin.VO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CourseVO {
        private Long id;
        private String courseName;
        private String courseType;
        private String courseDistance;
        private String courseSchedule;
        private String courseTheme;
        private String courseContents;
        private String courseFilePath;
        private String courseFileSize;
        private String courseFileName;
        private String volunteerStatus;
        private Long   adminId;
        private String courseDate;
        private String courseAddDate;
        private String createdDate;
        private String updatedDate;
}
