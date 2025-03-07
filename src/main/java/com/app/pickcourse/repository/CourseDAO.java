package com.app.pickcourse.repository;

import com.app.pickcourse.domain.dto.CourseDTO;
import com.app.pickcourse.domain.vo.CourseVO;
import com.app.pickcourse.mapper.CourseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CourseDAO {
    private CourseMapper courseMapper;

    public void postAddCourse(CourseVO courseVO) {
        courseMapper.postAddCourse(courseVO);
    }
}
