package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.CourseDTO;
import com.app.pickcourse.domain.vo.MemberVO;
import com.app.pickcourse.domain.vo.PlanVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@Slf4j
public class PlanMapperTests {
    @Autowired
    private PlanMapper planMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private CourseMapper courseMapper;

//    계획 추가
    @Test
    public void testInsert() {
        MemberVO member = new MemberVO();

        member.setMemberEmail("test1@test.com");
        member.setMemberPassword("1234");

        Optional<MemberVO> loginMember = memberMapper.selectByMemberEmailAndMemberPassword(member);
        member = loginMember.orElse(new MemberVO());

        CourseDTO readCourse = courseMapper.getCourseDetail(2L);

        PlanVO planVO = new PlanVO();

        planVO.setPlanName("제목3");
        planVO.setPlanStartDate("2025-03-04");
        planVO.setPlanEndDate("2025-03-10");
        planVO.setPlanDeadline("2025-03-20");
        planVO.setPlanMaxPersonnel(10);
        planVO.setPlanMinPersonnel(1);
        planVO.setPlanPrice(25000);
        planVO.setPlanStartAddress("주소3");
        planVO.setPlanFileName("파일이름3");
        planVO.setPlanFileSize("파일크기3");
        planVO.setPlanFilePath("파일경로3");
        planVO.setMemberId(member.getId());
        planVO.setCourseId(readCourse.getId());
        planVO.setPlanContent("내용3");

        planMapper.insert(planVO);
    }
}
