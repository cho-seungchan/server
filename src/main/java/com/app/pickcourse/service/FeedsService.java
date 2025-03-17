package com.app.pickcourse.service;

import com.app.pickcourse.domain.dto.ReplyListDTO;
import com.app.pickcourse.domain.vo.ReplyVO;
import com.app.pickcourse.domain.vo.ReportIdVO;
import com.app.pickcourse.domain.vo.ReportVO;
import com.app.pickcourse.repository.*;
import com.app.pickcourse.util.Pagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeedsService {
    private final ReplyDAO replyDAO;
    private final GeneralReplyDAO generalReplyDAO;
    private final TogetherReplyDAO togetherReplyDAO;
    private final RealReplyDAO realReplyDAO;
    private final ReportDAO reportDAO;
    private final ReplyReportDAO replyReportDAO;

    public List<ReplyListDTO> getReplyList(Long feedId, Pagination pagination) {
        pagination.create(replyDAO.getCountAll(feedId));
        log.info(pagination.toString());

        List<ReplyListDTO> list = replyDAO.getReplyList(feedId, pagination);

        // 로그인 회원과 작성자가 같으면 '삭제' 다르면 '신고'
        list.forEach( reply -> {
            if ( reply.getMemberId() == 2){  // 로그인수정
                reply.setReplyAction("삭제");
            } else {
                reply.setReplyAction("신고");
            }

            log.info("댓글 내용  "+reply.toString());
        });
        return list;
    }

    public void deleteReplyList(Long id) {
        replyDAO.deleteReplyList(id);
        generalReplyDAO.deleteReplyList(id);
        togetherReplyDAO.deleteReplyList(id);
        realReplyDAO.deleteReplyList(id);
        // 신고 내역 삭제 (한건의 댓글에 많은 신고)
        List<Long> reportIds = replyReportDAO.selectId(id);
        reportIds.forEach( reportId -> {
            reportDAO.deleteReplyList(reportId);
            replyReportDAO.deleteReplyList(reportId);
        });

    }

    public void postReportReplyList(ReportVO reportVO) {
        ReportIdVO reportId = new ReportIdVO();
        reportDAO.postReportReplyList(reportId); // 슈퍼키 가져오기
        replyReportDAO.postReportReplyList(reportId.getId(), reportVO.getId(), reportVO.getReportedReason(), 1l); // 로그인수정

    }
}
