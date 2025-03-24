package com.app.pickcourse.service;

import com.app.pickcourse.domain.dto.ParticipantDTO;
import com.app.pickcourse.domain.vo.ParticipantVO;
import com.app.pickcourse.repository.ParticipantDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ParticipantService {

    private final ParticipantDAO participantDAO;

    public int getVolunteerCourseParticipationCount(Long memberId) {
        return participantDAO.getVolunteerCourseParticipationCount(memberId);
    }

//    참가자 추가
    public void insertParticipant (ParticipantDTO participantDTO) {
        ParticipantVO participantVO = participantDTO.toVO();
        participantDAO.save(participantVO);
    }

}
