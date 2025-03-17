package com.app.pickcourse.repository;

import com.app.pickcourse.domain.dto.ReplyListDTO;
import com.app.pickcourse.domain.dto.ReplyDetailDTO;
import com.app.pickcourse.mapper.ReplyMapper;
import com.app.pickcourse.util.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReplyDAO {
    private final ReplyMapper replyMapper;

    public ReplyDetailDTO getReportDetail(Long id) {
        return replyMapper.getReportDetail(id).orElseThrow(() -> new RuntimeException("Report detail with ID " + id + " not found"));
    }

    public int getCountAll(Long feedId) {
        return replyMapper.getCountAll(feedId);
    }

    public List<ReplyListDTO> getReplyList(Long feedId, Pagination pagination) {
        return replyMapper.getReplyList(feedId, pagination);
    }

    public void deleteReplyList(Long id) {
        replyMapper.deleteReplyList(id);
    }
}
