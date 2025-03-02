package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.vo.VolunteerIncludeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VolunteerIncludeMapper {

    // 추천 코스 등록, 추천 코스 수정 :: 봉사코스 일 경우
    void postAddCourse(VolunteerIncludeVO volunteerIncludeVO);

    // 추천 코스 목록
    List<VolunteerIncludeVO> getCourseDetail(@Param("courseId") Long courseId);

    // 추천 코스 수정 :: 모두 삭제 후 모두 등록
    void deleteEditCourse(@Param("courseId") Long courseId);
}
