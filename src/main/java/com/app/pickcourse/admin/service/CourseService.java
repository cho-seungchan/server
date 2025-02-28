package com.app.pickcourse.admin.service;

import com.app.pickcourse.admin.dao.CourseDAO;
import com.app.pickcourse.admin.dto.CourseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseService {
    private final CourseDAO courseDAO;

    public void postCourseWrite(CourseDTO courseDTO) {

        courseDAO.postCourseWrite(courseDTO);

//        if (courseDTO.get)
//        courseDAO.postVolunteerWrite(courseDTO);
//        courseDAO.postExcludeWrite(courseDTO);
//        courseDAO.postIncludeWrite(courseDTO);
//        courseDAO.postPrepareWrite(courseDTO);
    }
}
