package com.app.pickcourse.admin.VO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class VolunteerExcludeVO {
    private int id;
    private String excludeContent;
    private int volunteerId;
}
