package com.app.admin.dao;

import com.app.admin.dto.CourseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class CourseDAO {
    public void postCourseWrite(CourseDTO courseDTO) {
      log.info(" courseDTO : {} ", courseDTO);
    }
}
