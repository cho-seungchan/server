package com.app.pickcourse.mapper;

import com.app.PickcourseApplication;
import com.app.pickcourse.domain.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class MemberMapperTests {
    @Autowired
    private MemberMapper memberMapper;

    @Test
    public void testInsert() {

        MemberVO memberVO = new MemberVO();

        memberVO.setMemberEmail("2ndtest@2ndtest.com");
        memberVO.setMemberPassword("1234");
        memberVO.setMemberName("2ndtest");
        memberVO.setMemberPhone("010-1234-5678");
        memberVO.setMemberBirth("1992-12-12");
        memberVO.setMemberGender("선택안함");
        memberMapper.insert(memberVO);
    }

    @Test
    public void testSelectByMemberEmail() {
        MemberVO member =  memberMapper.selectByMemberEmail("test@test.com");
        log.info("조회된 회원 정보: {}", member);
    }

    @Test
    public void testSelectByMemberEmailAndPassword() {
        MemberVO member =  memberMapper.selectByMemberEmailAndPassword("test@test.com", "1234");
        log.info("조회된 회원 정보: {}", member);
    }

    @Test
    public void testUpdate() {
        MemberVO memberVO = new MemberVO();

        memberVO.setMemberName("홍길동");
        memberVO.setMemberPhone("test@test.co.kr");
        memberVO.setMemberBirth("1919-11-11");
        memberVO.setMemberGender("남자");
        memberVO.setId(1L);

        memberMapper.update(memberVO);
    }

    @Test
    public void testDelete() {
        memberMapper.delete(21L);
    }


}
