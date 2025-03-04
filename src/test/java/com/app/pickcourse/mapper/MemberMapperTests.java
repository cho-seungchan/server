package com.app.pickcourse.mapper;


import com.app.pickcourse.domain.vo.Criteria;
import com.app.pickcourse.domain.vo.MemberVO;
import com.app.pickcourse.domain.vo.Search;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;




@SpringBootTest
@Slf4j
public class MemberMapperTests {
    @Autowired

    MemberMapper mapper;

    @Test
    public void getMemberListTest() {
        Search search = new Search();
        search.setType("e");
        search.setKeyWord("3");
        Criteria criteria = new Criteria(0, mapper.getCountAll(search));
        List<MemberVO> memberList = mapper.getMemberList(criteria, search);
        memberList.forEach(System.out::println);
        log.info("memberList size: {}", memberList.size());
        log.info("criteria: {}", criteria);
    }

    @Test
    public void postMemberListTest() {
        mapper.patchMemberList(3l);
    }

    @Test
    public void deleteMemberListTest() {
        mapper.deleteMemberList(3l);
    }

    private MemberMapper memberMapper;

    @Test
    public void testInsert() {

        MemberVO memberVO = new MemberVO();

        memberVO.setMemberEmail("test@test.com");
        memberVO.setMemberPassword("1234");
        memberVO.setMemberNickname("Test");
        memberVO.setMemberTell("010-1234-5678");
        memberVO.setMemberBirth("1992-12-12");
        memberVO.setMemberGender("선택안함");
        mapper.insert(memberVO);
    }

    @Test
    public void testSelectCountByMemberEmail(){
        int status = mapper.selectCountByMemberEmail("test@naver.com");
        log.info("status:{}", status);
    }

    @Test
    public void testSelectByMemberEmailAndMemberPassword(){
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberEmail("test@test.com");
        memberVO.setMemberPassword("12345");
        MemberVO foundMember = mapper.selectByMemberEmailAndMemberPassword(memberVO).orElse(null);
        log.info(foundMember.toString());
    }

    @Test
    public void testUpdate() {
        MemberVO memberVO = new MemberVO();

        memberVO.setMemberNickname("홍길동");
        memberVO.setMemberTell("010-1111-1234");
        memberVO.setMemberBirth("1919-11-11");
        memberVO.setMemberGender("남자");
        memberVO.setId(1L);

        mapper.update(memberVO);
    }

    @Test
    public void testDelete() {
        mapper.delete(2L);
    }



}
