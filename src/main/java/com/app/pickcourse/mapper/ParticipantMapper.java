package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.ParticipantDTO;
import com.app.pickcourse.domain.vo.ParticipantVO;
import com.app.pickcourse.domain.dto.RecentCourse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ParticipantMapper {
//    추가
    public void insert(ParticipantVO participantVO);
//    플랜ID로 조회
    public List<ParticipantDTO> selectByPlanId(Long planId);

    public int getNormalCourseParticipationCount(@Param("memberId") Long memberId);

    public int getVolunteerCourseParticipationCount(@Param("memberId") Long memberId);

    public RecentCourse getRecentCourse(@Param("memberId") Long memberId);


}
