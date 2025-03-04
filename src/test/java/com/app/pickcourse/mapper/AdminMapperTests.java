package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.vo.AdminVO;
import com.app.pickcourse.domain.vo.Criteria;
import com.app.pickcourse.domain.vo.NoticeVO;
import com.app.pickcourse.domain.vo.Search;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class AdminMapperTests {
    @Autowired
    private AdminMapper mapper;

    @Test
    public void getManageListNotice() {
        Search search = new Search();
        search.setType("a");
        search.setKeyWord("KANG");
        Criteria criteria = new Criteria(0, mapper.getCountAll(search));
        List<AdminVO> notices = mapper.getManageAdminList(criteria, search);
        log.info(criteria.toString());
        notices.forEach(System.out::println);
        log.info("notices size :: {} ", notices.size());
    }

    @Test
    public void postManageAdmin() {
        AdminVO admin = new AdminVO();
        admin.setAdminAccount("hard");
        admin.setAdminPassword("1234");
        admin.setAdminName("cho");
        admin.setAdminPhone("011");
        admin.setAdminEmail("daum");
        mapper.postManageAdmin(admin);
    }

    @Test
    public void deleteManageAdmin() {
        mapper.deleteManageAdmin(3l);
    }
}
