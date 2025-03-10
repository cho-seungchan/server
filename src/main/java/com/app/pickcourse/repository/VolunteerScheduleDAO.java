package com.app.pickcourse.repository;

import com.app.pickcourse.domain.vo.VolunteerScheduleVO;
import com.app.pickcourse.mapper.VolunteerPrepareMapper;
import com.app.pickcourse.mapper.VolunteerScheduleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class VolunteerScheduleDAO {
    private final VolunteerScheduleMapper volunteerScheduleMapper;

    public void postAddCourse(String content, Long volunteerId) {
        volunteerScheduleMapper.postAddCourse(content, volunteerId);
    }
}
