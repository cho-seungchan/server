package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.CourseDTO;
import com.app.pickcourse.domain.dto.CourseListDTO;
import com.app.pickcourse.domain.vo.CourseVO;
import com.app.pickcourse.domain.vo.Criteria;
import com.app.pickcourse.domain.vo.Search;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CourseMapper {

    // 페이지 처리를 위한 전체 화면 갯수
    int getCountAll(@Param("search") Search search);

    // 추천코스 등록
    void postAddCourse(CourseVO courseVO);

    // 추천코스 조회
    CourseDTO getCourseDetail(@Param("id") Long id);

    // 추천코스 목록 조회
    List<CourseListDTO> getCourseList(@Param("criteria") Criteria criteria, @Param("search") Search search);

    // 추천코스 타입 정하기 :: 추천화면 목록 조회 화면에서 수행
    void patchCourseList(@Param("id") Long id, @Param("courseType") String courseType);

    // 추천코스 수정
    void patchEditCourse(CourseVO courseVO);
}
