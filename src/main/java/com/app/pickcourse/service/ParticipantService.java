package com.app.pickcourse.service;

import com.app.pickcourse.domain.dto.RecentCourse;
import com.app.pickcourse.repository.ParticipantDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ParticipantService {

    private final ParticipantDAO participantDAO;

    public int getNormalCourseParticipationCount(Long memberId) {
        return participantDAO.getNormalCourseParticipationCount(memberId);
    }

    public int getVolunteerCourseParticipationCount(Long memberId) {
        return participantDAO.getVolunteerCourseParticipationCount(memberId);
    }

    public RecentCourse getRecentCourse(Long memberId) {
        return participantDAO.getRecentCourse(memberId);
    }

}
