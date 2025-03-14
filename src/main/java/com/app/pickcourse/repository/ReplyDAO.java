package com.app.pickcourse.repository;

import com.app.pickcourse.domain.dto.ReportDetailDTO;
import com.app.pickcourse.mapper.FeedMapper;
import com.app.pickcourse.mapper.ReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReplyDAO {
    private final ReplyMapper replyMapper;

    public ReportDetailDTO getReportDetail(Long id) {
        return replyMapper.getReportDetail(id).orElseThrow(() -> new RuntimeException("Report detail with ID " + id + " not found"));
    }
}
