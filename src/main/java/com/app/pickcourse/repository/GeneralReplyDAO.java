package com.app.pickcourse.repository;

import com.app.pickcourse.mapper.GeneralReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class GeneralReplyDAO {
    private final GeneralReplyMapper generalReplyMapper;

    public void deleteReplyList(Long id) {
        generalReplyMapper.deleteReplyList(id);
    }
}
