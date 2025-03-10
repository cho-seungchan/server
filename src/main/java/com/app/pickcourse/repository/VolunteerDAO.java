package com.app.pickcourse.repository;

import com.app.pickcourse.domain.vo.VolunteerVO;
import com.app.pickcourse.mapper.VolunteerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class VolunteerDAO {
    private final VolunteerMapper volunteerMapper;

    public void postAddCourse(VolunteerVO volunteerVO) {
        volunteerMapper.postAddCourse(volunteerVO);
    }
}
