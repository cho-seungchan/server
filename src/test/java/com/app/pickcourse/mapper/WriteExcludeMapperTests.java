package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.vo.WriteExcludeVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class WriteExcludeMapperTests {
    @Autowired
    private WriteExcludeMapper mapper;

    @Test
    public void testInsert() {
        WriteExcludeVO exclude = new WriteExcludeVO();

        exclude.setExcludeContent("내용2");
        exclude.setPlanId(1L);

        mapper.insert(exclude);
    }
}
