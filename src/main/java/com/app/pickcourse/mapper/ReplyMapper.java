package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.FeedDTO;
import com.app.pickcourse.domain.dto.FeedListDTO;
import com.app.pickcourse.domain.dto.ReplyDTO;
import com.app.pickcourse.domain.dto.ReportDetailDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ReplyMapper {

    void postReplyList(ReplyDTO replyDTO);

    Optional<ReportDetailDTO> getReportDetail(Long id);
}
