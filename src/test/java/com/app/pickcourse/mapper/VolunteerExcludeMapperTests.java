package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.CourseDTO;
import com.app.pickcourse.domain.vo.VolunteerExcludeVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class VolunteerExcludeMapperTests {
    @Autowired
    private VolunteerExcludeMapper mapper;

    @Test
    public void postAddCourse() {
        VolunteerExcludeVO volunteerExclude = new VolunteerExcludeVO();

        volunteerExclude.setVolunteerExcludeContent("money");
        volunteerExclude.setCourseId(2l);
        mapper.postAddCourse(volunteerExclude);

    }

    @Test
    public void getCourseDetail() {
        List<VolunteerExcludeVO> volunteerExclude = mapper.getCourseDetail(2l);
        volunteerExclude.forEach(System.out::println);

        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setVolunteerExcludes(volunteerExclude);
        log.info(courseDTO.toString());
    }

    @Test
    public void deleteEditCourse() {
        mapper.deleteEditCourse(2l);
    }
}
