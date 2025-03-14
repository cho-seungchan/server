package com.app.pickcourse.repository;

import com.app.pickcourse.domain.dto.ReportDetailDTO;
import com.app.pickcourse.domain.dto.ReportListDTO;
import com.app.pickcourse.mapper.FeedMapper;
import com.app.pickcourse.mapper.FeedReportMapper;
import com.app.pickcourse.util.Pagination;
import com.app.pickcourse.util.Search;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FeedDAO {
    private final FeedMapper feedMapper;

    public ReportDetailDTO getReportDetail(Long id) {
        return feedMapper.getReportDetail(id).orElseThrow(() -> new RuntimeException("Report detail with ID " + id + " not found"));
    }
}
