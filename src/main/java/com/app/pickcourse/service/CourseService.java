package com.app.pickcourse.service;

import com.app.pickcourse.domain.dto.CourseDTO;
import com.app.pickcourse.domain.dto.CourseSelectDTO;
import com.app.pickcourse.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class CourseService {
    private final CourseDAO courseDAO;
    private final PathDAO pathDAO;
    private final PlanDAO planDAO;
    private final MemberDAO memberDAO;
    private final VolunteerExcludeDAO volunteerExcludeDAO;
    private final VolunteerIncludeDAO volunteerIncludeDAO;
    private final VolunteerPrepareDAO volunteerPrepareDAO;
    private final VolunteerScheduleDAO volunteerScheduleDAO;
    private final VolunteerParticipantDAO volunteerParticipantDAO;

    //    추천코스 조회
    public CourseSelectDTO findCourseById(Long courseId) {
        CourseSelectDTO course = new CourseSelectDTO();

        course = courseDAO.findCourseViewById(courseId).orElseThrow(()->new RuntimeException("Course not found"));
        course.setPaths(pathDAO.getCourseDetail(courseId));
        course.setPlans(planDAO.findByCourseId(courseId));

        course.getPlans().forEach(plan -> {plan.setMemberNickname(
                memberDAO.findById(plan.getMemberId()).orElseThrow().getMemberNickname());});

        return course;
    }

    // 봉사코스 정보 가져오기
    public CourseDTO getEco(Long id) {
        CourseDTO course = courseDAO.getEco(id);
        course.setPaths(pathDAO.getCourseDetail(id));
        course.setExcludeContents(volunteerExcludeDAO.getCourseDetail(id));
        course.setIncludeContents(volunteerIncludeDAO.getCourseDetail(id));
        course.setPrepareContents(volunteerPrepareDAO.getCourseDetail(id));
        course.setScheduleContents(volunteerScheduleDAO.getCourseDetail(id));

        return course;
    }

    public String postEcoparticipant(Long courseId, Long memberId) {
        // 중복 체크
        if (volunteerParticipantDAO.checkDuplicate(courseId, memberId) > 0){
            return " 이미 참가 신청되어 있습니다. ";
        }

        volunteerParticipantDAO.postEcoparticipant(courseId, memberId);
        return "참가 신청이 정상적으로 처리 되었습니다.";
    }
}
