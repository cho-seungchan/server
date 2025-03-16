package com.app.pickcourse.repository;

import com.app.pickcourse.domain.dto.ReplyListDTO;
import com.app.pickcourse.domain.dto.ReportDetailDTO;
import com.app.pickcourse.mapper.GeneralReplyMapper;
import com.app.pickcourse.mapper.ReplyMapper;
import com.app.pickcourse.util.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GeneralReplyDAO {
    private final GeneralReplyMapper generalReplyMapper;

    public void deleteReplyList(Long id) {
        generalReplyMapper.deleteReplyList(id);
    }
}
