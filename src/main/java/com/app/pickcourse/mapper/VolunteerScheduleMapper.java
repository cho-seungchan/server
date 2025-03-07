package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.vo.VolunteerExcludeVO;
import com.app.pickcourse.domain.vo.VolunteerScheduleVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VolunteerScheduleMapper {

    // 추천 코스 등록, 추천 코스 수정 :: 봉사코스 일 경우
    void postAddCourse(VolunteerScheduleVO volunteerScheduleVO);

}
