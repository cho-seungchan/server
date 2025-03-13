package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.ReportListDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class ReportMapperTests {
    @Autowired
    private ReportMapper mapper;

    @Test
    public void saveFeedReport() {
        List<ReportListDTO> list = mapper.getReportList();
        list.forEach(System.out::println);
    }


}
