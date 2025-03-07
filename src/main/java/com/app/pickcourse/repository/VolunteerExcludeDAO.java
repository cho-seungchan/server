package com.app.pickcourse.repository;

import com.app.pickcourse.mapper.VolunteerExcludeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class VolunteerExcludeDAO {
    private final VolunteerExcludeMapper volunteerExcludeMapper;

    public void postAddCourse(String excludeContent, Long id) {
        volunteerExcludeMapper.postAddCourse(excludeContent, id);
    }
}
