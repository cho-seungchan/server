package com.app.admin.dto;

import com.app.admin.VO.VolunteerExcludeVO;
import com.app.admin.VO.VolunteerVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@ToString
public class CourseDTO {
    public class CourseDAO {
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
        private Long   adminId;
        private String courseDate;
        private String courseAddDate;
        private String createdDate;
        private String updatedDate;
        private VolunteerVO volunteer;
        private List<VolunteerExcludeVO> exclude;
        private List<VolunteerExcludeVO> include;
        private List<VolunteerExcludeVO> prepare;
    }
}
