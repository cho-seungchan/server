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
        log.info("mmemberList size: {}", memberList.size());
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
}
