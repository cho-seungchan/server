package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.vo.WritePrepareVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class WritePrepareMapperTests {
    @Autowired
    private WritePrepareMapper mapper;

    @Test
    public void testInsert() {
        WritePrepareVO writePrepareVO = new WritePrepareVO();

        writePrepareVO.setPlanId(1L);
        writePrepareVO.setPrepareContent("테스트제목2");

        mapper.insert(writePrepareVO);
    }
}
