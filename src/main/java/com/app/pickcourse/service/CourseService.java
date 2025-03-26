package com.app.pickcourse.service;

import com.app.pickcourse.domain.dto.CourseSelectDTO;
import com.app.pickcourse.domain.vo.FileVO;
import com.app.pickcourse.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class CourseService {
    private final CourseDAO courseDAO;
    private final PathDAO pathDAO;
    private final PlanDAO planDAO;
    private final MemberDAO memberDAO;
    private final RealFeedDAO realFeedDAO;
    private final TagDAO tagDAO;
    private final RealFileDAO realFileDAO;

    //    추천코스 조회
    public CourseSelectDTO findCourseById(Long courseId) {
        CourseSelectDTO course = new CourseSelectDTO();

        course = courseDAO.findCourseViewById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        course.setPaths(pathDAO.getCourseDetail(courseId));
        course.setPlans(planDAO.findByCourseId(courseId));

        course.getPlans().forEach((plan) -> {
            plan.setMemberNickname(memberDAO.findById(plan.getMemberId()).orElseThrow().getMemberNickname());
            plan.setFeedList(realFeedDAO.findFeedListByPlanId(plan.getId()));
            plan.getFeedList().forEach((feed) -> {
                    feed.setTags(tagDAO.getFeedModify(feed.getId()));
                    List<FileVO> fileList = null;
                    fileList = realFileDAO.getRealModify(feed.getId());
                    feed.setFiles(fileList);
            });
        });

        return course;
    }

}
