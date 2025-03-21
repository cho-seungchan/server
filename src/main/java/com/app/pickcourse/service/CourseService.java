package com.app.pickcourse.service;

import com.app.pickcourse.domain.dto.CourseSelectDTO;
import com.app.pickcourse.repository.CourseDAO;
import com.app.pickcourse.repository.MemberDAO;
import com.app.pickcourse.repository.PathDAO;
import com.app.pickcourse.repository.PlanDAO;
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

}
