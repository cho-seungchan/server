package com.app.pickcourse.repository;

import com.app.pickcourse.domain.dto.ReplyDetailDTO;
import com.app.pickcourse.mapper.FeedMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FeedDAO {
    private final FeedMapper feedMapper;

    public ReplyDetailDTO getReportDetail(Long id) {
        return feedMapper.getReportDetail(id).orElseThrow(() -> new RuntimeException("Report detail with ID " + id + " not found"));
    }
}
