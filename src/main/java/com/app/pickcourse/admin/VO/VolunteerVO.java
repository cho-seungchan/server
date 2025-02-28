package com.app.pickcourse.admin.VO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class VolunteerVO {
    private int id;
    private String volunteerStartDate;
    private String volunteerEndDate;
    private String volunteerDeadline;
    private String volunteerContents;
    private int courseId;
}
