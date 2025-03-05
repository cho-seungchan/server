package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.vo.WriteIncludeVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class WriteIncludeMapperTests {
    @Autowired
    private WriteIncludeMapper mapper;

    @Test
    public void testInsert() {
        WriteIncludeVO writeIncludeVO = new WriteIncludeVO();

        writeIncludeVO.setPlanId(1L);
        writeIncludeVO.setIncludeContent("포함내용2");

        mapper.insert(writeIncludeVO);
    }
}
