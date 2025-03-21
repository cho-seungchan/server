package com.app.pickcourse.repository;

import com.app.pickcourse.domain.dto.RecentCourse;
import com.app.pickcourse.mapper.ParticipantMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ParticipantDAO {

    private final ParticipantMapper participantMapper;

    public int getNormalCourseParticipationCount(Long memberId) {
        return participantMapper.getNormalCourseParticipationCount(memberId);
//    추가
    public void save(ParticipantVO participantVO) {
        participantMapper.insert(participantVO);
    }
//    조회
    public List<ParticipantDTO> findByPlanId(Long planId) {
        return participantMapper.selectByPlanId(planId);

    public int getVolunteerCourseParticipationCount(Long memberId) {
        return participantMapper.getVolunteerCourseParticipationCount(memberId);
    }

    public RecentCourse getRecentCourse(Long memberId) {
        return participantMapper.getRecentCourse(memberId);
    }

}
