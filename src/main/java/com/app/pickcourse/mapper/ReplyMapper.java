package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.ReplyListDTO;
import com.app.pickcourse.domain.dto.ReportDetailDTO;
import com.app.pickcourse.util.Pagination;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ReplyMapper {

    int getCountAll(Long feedId);

    void postReplyList(ReplyListDTO replyListDTO);

    Optional<ReportDetailDTO> getReportDetail(Long id);

    List<ReplyListDTO> getReplyList(@Param("feedId") Long feedId, @Param("pagination") Pagination pagination);

    void deleteReplyList(Long id);
}
