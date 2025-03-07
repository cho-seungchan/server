package com.app.pickcourse.repository;

import com.app.pickcourse.mapper.VolunteerExcludeMapper;
import com.app.pickcourse.mapper.VolunteerIncludeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class VolunteerIncludeDAO {
    private final VolunteerIncludeMapper volunteerIncludeMapper;

    public void postAddCourse(String includeContent, Long id) {
        volunteerIncludeMapper.postAddCourse(includeContent,id);
    }
}
