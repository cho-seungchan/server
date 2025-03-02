package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.CourseDTO;
import com.app.pickcourse.domain.vo.VolunteerIncludeVO;
import com.app.pickcourse.domain.vo.VolunteerIncludeVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class VolunteerIncludeMapperTests {
    @Autowired
    private VolunteerIncludeMapper mapper;

    @Test
    public void postAddCourse() {
        VolunteerIncludeVO volunteerInclude = new VolunteerIncludeVO();
        volunteerInclude.setVolunteerIncludeContent("meal");
        volunteerInclude.setCourseId(2l);
        mapper.postAddCourse(volunteerInclude);
    }

    @Test
    public void getCourseDetail() {
        List<VolunteerIncludeVO> volunteerInclude = mapper.getCourseDetail(2l);
        volunteerInclude.forEach(System.out::println);

        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setVolunteerIncludes(volunteerInclude);
        log.info(courseDTO.toString());
    }

    @Test
    public void deleteEditCourse() {
        mapper.deleteEditCourse(2l);
    }
}
