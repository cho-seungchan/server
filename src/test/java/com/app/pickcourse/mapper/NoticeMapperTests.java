package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.vo.Pagination;
import com.app.pickcourse.domain.vo.NoticeVO;
import com.app.pickcourse.domain.vo.Search;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.input.LineSeparatorDetector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class NoticeMapperTests {
    @Autowired
    private NoticeMapper mapper;

    @Test
    public void postAddNotice() {
        NoticeVO notice = new NoticeVO();
        notice.setNoticeName("공지사항2222");
        notice.setNoticeContent("이것은 공지사항2222");
        notice.setAdminId(6l);
        mapper.postAddNotice(notice);
    }

    @Test
    public void getManageListNotice() {
        Search search = new Search();
        search.setType("w");
        search.setKeyWord("HHH");
        Pagination pagination = new Pagination(0, mapper.getCountAll(search));
        List<NoticeVO> notices = mapper.getManageNoticeList(pagination, search);
        log.info(pagination.toString());
        notices.forEach(System.out::println);
        log.info("notices size :: {} ", notices.size());
    }

    @Test
    public void getManageNotice() {
        NoticeVO notice = mapper.getManageNotice(7l);
        log.info(notice.toString());
    }

    @Test
    public void patchManageNotice() {
        NoticeVO notice = new NoticeVO();
        notice.setNoticeContent("이것은 공지사항3333");
        notice.setId(3l);
        mapper.patchManageNotice(notice);
    }

    @Test
    public void deleteManageNotice() {
        mapper.deleteManageNotice(6l);
    }
}
