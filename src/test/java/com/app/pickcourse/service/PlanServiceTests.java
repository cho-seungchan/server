package com.app.pickcourse.service;

import com.app.pickcourse.domain.dto.*;
import com.app.pickcourse.domain.vo.*;
import com.app.pickcourse.mapper.PlanMapper;
import com.app.pickcourse.repository.ScheduleDAO;
import com.app.pickcourse.repository.WriteExcludeDAO;
import com.app.pickcourse.repository.WriteIncludeDAO;
import com.app.pickcourse.repository.WritePrepareDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
public class PlanServiceTests {
    @Autowired
    private PlanService planService;
    @Autowired
    private WriteExcludeDAO writeExcludeDAO;
    @Autowired
    private WriteIncludeDAO writeIncludeDAO;
    @Autowired
    private WritePrepareDAO writePrepareDAO;
    @Autowired
    private ScheduleDAO scheduleDAO;

    @Test
    public void testWritePlan() {
        PlanDTO planDTO = new PlanDTO();
        List<WriteExcludeDTO> test = new ArrayList<>();
        List<WriteIncludeDTO> test1 = new ArrayList<>();
        List<WritePrepareDTO> test2 = new ArrayList<>();
        List<ScheduleDTO> test3 = new ArrayList<>();
        WriteExcludeDTO writeExcludeVO = new WriteExcludeDTO();
        WriteExcludeDTO writeExcludeVO1 = new WriteExcludeDTO();
        WriteIncludeDTO writeIncludeVO = new WriteIncludeDTO();
        WriteIncludeDTO writeIncludeVO1 = new WriteIncludeDTO();
        WritePrepareDTO writePrepareVO = new WritePrepareDTO();
        WritePrepareDTO writePrepareVO1 = new WritePrepareDTO();
        ScheduleDTO scheduleVO = new ScheduleDTO();
        ScheduleDTO scheduleVO1 = new ScheduleDTO();

        planDTO.setPlanName("서비스테스트2");
        planDTO.setPlanStartDate("2025-03-04");
        planDTO.setPlanEndDate("2025-03-10");
        planDTO.setPlanDeadline("2025-03-20");
        planDTO.setPlanMaxPersonnel(10);
        planDTO.setPlanMinPersonnel(1);
        planDTO.setPlanPrice(25000);
        planDTO.setPlanStartAddress("서비스테스트2");
        planDTO.setPlanFileName("서비스테스트2");
        planDTO.setPlanFileSize("서비스테스트2");
        planDTO.setPlanFilePath("서비스테스트2");
        planDTO.setMemberId(1L);
        planDTO.setCourseId(1L);
        planDTO.setPlanContent("서비스테스트2");

        writeExcludeVO.setExcludeContent("test1");
        test.add(writeExcludeVO);

        writeExcludeVO1.setExcludeContent("test2");
        test.add(writeExcludeVO1);

        writeIncludeVO.setIncludeContent("test3");
        test1.add(writeIncludeVO);

        writeIncludeVO1.setIncludeContent("test4");
        test1.add(writeIncludeVO1);

        writePrepareVO.setPrepareContent("test5");
        test2.add(writePrepareVO);

        writePrepareVO1.setPrepareContent("test6");
        test2.add(writePrepareVO1);

        scheduleVO.setScheduleContent("test7");
        test3.add(scheduleVO);

        scheduleVO1.setScheduleContent("test8");
        test3.add(scheduleVO1);

        planDTO.setExcludeContents(test);
        planDTO.setIncludeContents(test1);
        planDTO.setPrepareContents(test2);
        planDTO.setScheduleContents(test3);

        planService.writePlan(planDTO);

        log.info(planDTO.toString());
    }
}
