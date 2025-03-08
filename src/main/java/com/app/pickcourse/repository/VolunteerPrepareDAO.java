package com.app.pickcourse.repository;

import com.app.pickcourse.mapper.VolunteerPrepareMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class VolunteerPrepareDAO {
    private final VolunteerPrepareMapper volunteerPrepareMapper;

    public void postAddCourse(String content, Long volunteerId) {
        volunteerPrepareMapper.postAddCourse(content, volunteerId);
    }
}
