package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.ReportDTO;
import com.app.pickcourse.domain.vo.ReportVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReportMapper {

    void saveReport(ReportVO reportVO);

    List<ReportVO> getReportList();

}
