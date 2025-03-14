package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.vo.ReportVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ReplyReportMapperTests {
    @Autowired
    private ReplyReportMapper mapper;
    @Autowired
    private ReportMapper reportMapper;

    @Test
    public void saveReplyReport() {
        ReportVO reportVO = new ReportVO();
        reportMapper.saveReport(reportVO);

        mapper.saveReplyReport(reportVO.getId(), 4l, 44l);
    }


}
