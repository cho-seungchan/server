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
        // 신고 내역 삭제 (한건의 댓글에 많은 신고), tbl_report는 자동삭제 되지 않기 때문에 삭제 대상 id를 가져와야 함.
        // on delete cascade로 자동 삭제되므로 가장 우선적으로 실행
        List<Long> reportIds = replyReportDAO.selectId(id);

        replyDAO.deleteReplyList(id);
        generalReplyDAO.deleteReplyList(id);
        togetherReplyDAO.deleteReplyList(id);
        realReplyDAO.deleteReplyList(id);
        reportIds.forEach( reportId -> {
            log.info("reportId  "+reportId);
            replyReportDAO.deleteReplyList(reportId);   // on delete cascade로 자동 삭제되지만 한번 더 확인
            reportDAO.deleteReplyList(reportId);
        });

    }

    public void postReportReplyList(ReportVO reportVO) {
        ReportIdVO reportId = new ReportIdVO();
        reportDAO.postReportReplyList(reportId); // 슈퍼키 가져오기
        replyReportDAO.postReportReplyList(reportId.getId(), reportVO.getId(), reportVO.getReportedReason(), 1l); // 로그인수정

    }

    public void postReplyList(ReplyVO replyVO) {
        // 댓글 슈퍼키 입력
        replyDAO.postReplyList(replyVO);

        // 피드 종류 알아보기
        String typeOfFeed = replyDAO.selectTypeOfFeed(replyVO.getFeedId());

        // 피드 종류별 댓글 입력
        replyVO.setMemberId(1l);   // 로그인수정
        if (typeOfFeed.equals("GENERAL FEED")) {
            generalReplyDAO.postReplyList(replyVO);
        } else if (typeOfFeed.equals("REAL FEED")) {
            realReplyDAO.postReplyList(replyVO);
        } else if (typeOfFeed.equals("TOGETHER FEED")) {
            togetherReplyDAO.postReplyList(replyVO);
        }

    }

    public List<ReplyListDTO> getMyReplyList(long id, Pagination pagination) {
        pagination.create(replyDAO.getCountAll(id));

        List<ReplyListDTO> list = replyDAO.getReplyList(id, pagination);

        // 삭제 버튼 생성
        list.forEach( reply -> {
                reply.setReplyAction("삭제");
        });

        return list;
    }
}
