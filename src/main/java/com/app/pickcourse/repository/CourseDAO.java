package com.app.pickcourse.repository;

import com.app.pickcourse.domain.dto.CourseDTO;
import com.app.pickcourse.domain.dto.CourseListDTO;
import com.app.pickcourse.mapper.CourseMapper;
import com.app.pickcourse.util.Pagination;
import com.app.pickcourse.util.Search;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CourseDAO {
    private final CourseMapper courseMapper;

    public int getCountAll(Search search) {
        return courseMapper.getCountAll(search);
    };

    public void postAddCourse(CourseDTO courseDTO) {
        courseMapper.postAddCourse(courseDTO);
    }

    public List<CourseListDTO> getCourseList(Pagination pagination, Search search) {
        return courseMapper.getCourseList(pagination,search);
    }

    public void patchCourseListExpire(String courseType) {
        courseMapper.patchCourseListExpire(courseType);
    }

    public void patchCourseListRegist(Long id, String courseType) {
        courseMapper.patchCourseListRegist(id, courseType);
    }
}
