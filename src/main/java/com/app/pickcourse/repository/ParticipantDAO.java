package com.app.pickcourse.repository;

import com.app.pickcourse.domain.dto.RecentCourse;
import com.app.pickcourse.mapper.ParticipantMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ParticipantDAO {

    private final ParticipantMapper participantMapper;

    public int getNormalCourseParticipationCount(Long memberId) {
        return participantMapper.getNormalCourseParticipationCount(memberId);
    }

    public int getVolunteerCourseParticipationCount(Long memberId) {
        return participantMapper.getVolunteerCourseParticipationCount(memberId);
    }

    public RecentCourse getRecentCourse(Long memberId) {
        return participantMapper.getRecentCourse(memberId);
    }

}
