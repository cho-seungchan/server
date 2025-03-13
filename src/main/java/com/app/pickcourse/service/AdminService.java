package com.app.pickcourse.service;

import com.app.pickcourse.domain.dto.CourseDTO;
import com.app.pickcourse.domain.dto.CourseListDTO;
import com.app.pickcourse.domain.vo.AdminVO;
import com.app.pickcourse.domain.vo.MemberVO;
import com.app.pickcourse.domain.vo.PathVO;
import com.app.pickcourse.exception.DuplicateException;
import com.app.pickcourse.repository.*;
import com.app.pickcourse.util.Pagination;
import com.app.pickcourse.util.Search;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
//@Transactional(rollbackFor = Exception.class)
@Slf4j
public class AdminService {

    private final AdminDAO adminDAO;
    private final MemberDAO memberDAO;
    private final CourseDAO courseDAO;
    private final VolunteerDAO volunteerDAO;
    private final PathDAO pathDAO;
    private final VolunteerExcludeDAO volunteerExcludeDAO;
    private final VolunteerIncludeDAO volunteerIncludeDAO;
    private final VolunteerPrepareDAO volunteerPrepareDAO;
    private final VolunteerScheduleDAO volunteerScheduleDAO;

    public List<AdminVO> getManageAdminList(Pagination pagination, Search search) {
        pagination.create(adminDAO.getCountAll(search));
        return adminDAO.getManageAdminList(pagination, search);
    }

    public void postManageAdminList(AdminVO adminVO) throws DuplicateException {
        // 중복 체크
        if (adminDAO.isAdminAccount(adminVO.getAdminAccount()) > 0){
            throw new DuplicateException("adim account  "+adminVO.getAdminAccount()+"  already exist !!!");
        }

        adminDAO.postManageAdminList(adminVO);
    }

    public void deleteManageAdminList(String selectedIds) {
        // 삭제할 대상 id를 Long으로 변환
        List<Long> idList = Arrays.asList(selectedIds.split(",")).
                stream().map(Long::parseLong).collect(Collectors.toList());

        idList.forEach(adminId -> {
            adminDAO.deleteManageAdminList(adminId);
        });
    }

    public List<MemberVO> getMemberList(Pagination pagination, Search search) {
        pagination.create(memberDAO.getCountAll(search));
        return memberDAO.getMemberList(pagination, search);
    }

    public void patchMemberListPause(String selectedIds) {
        List<Long> idList = Arrays.asList(selectedIds.split(",")).stream().
                map(Long::parseLong).collect(Collectors.toList());

        idList.forEach(memberId -> {
            memberDAO.patchMemberListPause(memberId);
        });
    }

    public void patchMemberListRestart(String selectedIds) {
        List<Long> idList = Arrays.asList(selectedIds.split(",")).stream().
                map(Long::parseLong).collect(Collectors.toList());


        idList.forEach(memberId -> {
            memberDAO.patchMemberListRestart(memberId);
        });
    }

    public void deleteMemberList(String selectedIds) {
        List<Long> idList = Arrays.asList(selectedIds.split(",")).stream().
                map(Long::parseLong).collect(Collectors.toList());

        idList.forEach(memberId -> {
            memberDAO.deleteMemberList(memberId);
        });

    }

    public void postAddCourse(CourseDTO courseDTO) {

        // 코스정보 입력
        courseDTO.setAdminId(11l);
        courseDAO.postAddCourse(courseDTO);
        log.info("postAddCourse service : {}",courseDTO.toString());

        if (courseDTO.getCourseIsVolunteer().equals('Y')){

            // 봉사코스 정보 입력
            volunteerDAO.postAddCourse(courseDTO.toVolunteerVO());

            // 일일 계획 입력
            if (courseDTO.getScheduleContents() != null){
                courseDTO.getScheduleContents().forEach(scheduleContent -> {
                    volunteerScheduleDAO.postAddCourse(scheduleContent, courseDTO.getId());
                });
            }
        }

        // 경로 입력
        courseDTO.getPaths().forEach(path -> {
            path.setCourseId(courseDTO.getId());
            pathDAO.postAddCourse(path);
        });

        // 불포함 사항 입력
        if (courseDTO.getExcludeContents() != null){
            courseDTO.getExcludeContents().forEach(excludeContent -> {
                volunteerExcludeDAO.postAddCourse(excludeContent, courseDTO.getId());
            });
        }

        // 포함 사항 입력
        if (courseDTO.getIncludeContents() != null){
            courseDTO.getIncludeContents().forEach(includeContent -> {
                volunteerIncludeDAO.postAddCourse(includeContent, courseDTO.getId());
            });
        }

        // 준비물 입력
        if (courseDTO.getPrepareContents() != null){
            courseDTO.getPrepareContents().forEach(prepareContent -> {
                volunteerPrepareDAO.postAddCourse(prepareContent, courseDTO.getId());
            });
        }

    }

    public List<CourseListDTO> getCourseList(Pagination pagination, Search search) {
        pagination.create(courseDAO.getCountAll(search));
        return courseDAO.getCourseList(pagination,search);
    }

    public void patchCourseList(String courseId, String courseType) {
        courseDAO.patchCourseListExpire(courseType);
        courseDAO.patchCourseListRegist(Long.parseLong(courseId), courseType);
    }

    public CourseDTO getCourseDetail(Long id) {
        CourseDTO courseDTO = courseDAO.getCourseDetail(id);
        courseDTO.setPaths(pathDAO.getCourseDetail(id));
        courseDTO.setExcludeContents(volunteerExcludeDAO.getCourseDetail(id));
        courseDTO.setIncludeContents(volunteerIncludeDAO.getCourseDetail(id));
        courseDTO.setPrepareContents(volunteerPrepareDAO.getCourseDetail(id));
        courseDTO.setScheduleContents(volunteerScheduleDAO.getCourseDetail(id));
        return courseDTO;
    }

    public CourseDTO getCourseTypeDetail(String courseType) {
        CourseDTO courseDTO = courseDAO.getCourseTypeDetail(courseType);
        courseDTO.setPaths(pathDAO.getCourseDetail(courseDTO.getId()));
        courseDTO.setExcludeContents(volunteerExcludeDAO.getCourseDetail(courseDTO.getId()));
        courseDTO.setIncludeContents(volunteerIncludeDAO.getCourseDetail(courseDTO.getId()));
        courseDTO.setPrepareContents(volunteerPrepareDAO.getCourseDetail(courseDTO.getId()));
        courseDTO.setScheduleContents(volunteerScheduleDAO.getCourseDetail(courseDTO.getId()));

        return courseDTO;
    }

    public void putCourseDetail(CourseDTO courseDTO) {

        courseDAO.putCourseDetail(courseDTO.toCourseVO());
        volunteerDAO.putCourseDetail(courseDTO.toVolunteerVO());
        pathDAO.deleteCourseDetail(courseDTO.getId());
        if (courseDTO.getPaths() != null){
            courseDTO.getPaths().forEach(path -> {
                path.setCourseId(courseDTO.getId());
                pathDAO.postAddCourse(path);
            });
        }

        volunteerExcludeDAO.deleteCourseDetail(courseDTO.getId());
        if (courseDTO.getExcludeContents() != null){
            courseDTO.getExcludeContents().forEach(content -> {
                volunteerExcludeDAO.postAddCourse(content, courseDTO.getId());
            });
        }
        volunteerIncludeDAO.deleteCourseDetail(courseDTO.getId());
        if (courseDTO.getIncludeContents() != null){
            courseDTO.getIncludeContents().forEach(content -> {
                volunteerIncludeDAO.postAddCourse(content, courseDTO.getId());
            });
        }

        volunteerPrepareDAO.deleteCourseDetail(courseDTO.getId());
        if (courseDTO.getPrepareContents() != null){
            courseDTO.getPrepareContents().forEach(content -> {
                volunteerPrepareDAO.postAddCourse(content, courseDTO.getId());
            });
        }

        volunteerScheduleDAO.deleteCourseDetail(courseDTO.getId());
        if (courseDTO.getScheduleContents() != null){
            courseDTO.getScheduleContents().forEach(content -> {
                log.info("schedult insert "+courseDTO.getId()+" "+content);
                volunteerScheduleDAO.postAddCourse(content, courseDTO.getId());
            });
        }

    }
}
