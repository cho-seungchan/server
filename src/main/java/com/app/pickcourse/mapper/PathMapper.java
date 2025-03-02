package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.vo.PathVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PathMapper {

    // 추천 코스 등록, 추천 코스 수정
    void postAddCourse(PathVO pathVO);

    // 추천 코스 목록
    List<PathVO> getCourseDetail(@Param("courseId") Long courseId);

    // 추천 코스 수정
    void deleteEditCourse(@Param("courseId") Long courseId);
}
