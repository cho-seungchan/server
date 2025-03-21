package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.RecentCourse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ParticipantMapper {

    public int getNormalCourseParticipationCount(@Param("memberId") Long memberId);

    public int getVolunteerCourseParticipationCount(@Param("memberId") Long memberId);

    public RecentCourse getRecentCourse(@Param("memberId") Long memberId);


}
