package com.app.pickcourse.service;

import com.app.pickcourse.domain.dto.FeedDTO;
import com.app.pickcourse.domain.vo.FeedVO;
import com.app.pickcourse.domain.dto.ReplyListDTO;
import com.app.pickcourse.domain.vo.ReplyVO;
import com.app.pickcourse.domain.vo.ReportIdVO;
import com.app.pickcourse.domain.vo.ReportVO;
import com.app.pickcourse.mapper.GeneralFeedMapper;
import com.app.pickcourse.mapper.GeneralFileMapper;
import com.app.pickcourse.repository.*;
import com.app.pickcourse.util.PaginationOnePage;
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
    private final FeedDAO feedDAO;
    private final GeneralFeedDAO generalFeedDAO;
    private final TogetherFeedDAO togetherFeedDAO;
    private final TagDAO tagDAO;
    private final FileDAO fileDAO;
    private final GeneralFileDAO generalFileDAO;
    private final TogetherFileDAO togetherFileDAO;

    public List<ReplyListDTO> getReplyList(Long loginId, Long feedId, PaginationOnePage pagination) {
        pagination.create(replyDAO.getCountAll(feedId));
        log.info("replyDAO.getCountAll(feedId) "+replyDAO.getCountAll(feedId));

        List<ReplyListDTO> list = replyDAO.getReplyList(feedId, pagination);

        // 로그인 회원과 작성자가 같으면 '삭제' 다르면 '신고'
        list.forEach( reply -> {
            if ( reply.getMemberId() == loginId){
                reply.setReplyAction("삭제");
            } else {
                reply.setReplyAction("신고");
            }

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

    public void postReportReplyList(ReportVO reportVO, Long loginId) {
        ReportIdVO reportId = new ReportIdVO();
        reportDAO.postReportReplyList(reportId); // 슈퍼키 가져오기
        replyReportDAO.postReportReplyList(reportId.getId(), reportVO.getId(), reportVO.getReportedReason(), loginId);

    }

    public void postReplyList(ReplyVO replyVO, Long loginId) {
        // 댓글 슈퍼키 입력
        replyDAO.postReplyList(replyVO);

        // 피드 종류 알아보기
        String typeOfFeed = replyDAO.selectTypeOfFeed(replyVO.getFeedId());

        // 피드 종류별 댓글 입력
        replyVO.setMemberId(loginId);
        if (typeOfFeed.equals("GENERAL FEED")) {
            generalReplyDAO.postReplyList(replyVO);
        } else if (typeOfFeed.equals("REAL FEED")) {
            realReplyDAO.postReplyList(replyVO);
        } else if (typeOfFeed.equals("TOGETHER FEED")) {
            togetherReplyDAO.postReplyList(replyVO);
        }

    }

    public List<ReplyListDTO> getMyReplyList(long loginId, PaginationOnePage pagination) {
        pagination.create(replyDAO.getMyCountAll(loginId));

        List<ReplyListDTO> list = replyDAO.getMyReplyList(loginId, pagination);

        // 삭제 버튼 생성
        list.forEach( reply -> {
                reply.setReplyAction("삭제");
        });

        return list;
    }

    public void postFeedWrite(Long loginId, FeedDTO feedDTO) {
        // 피드 슈퍼키 입력
        feedDAO.postFeedWrite(feedDTO);
        // 피드 타입에 따라 제네럴, 투게더 피드 입력
        if (feedDTO.getFeedType().equals("GENERAL")) {
            generalFeedDAO.postFeedWrite(loginId, feedDTO.toFeedVO());
        } else if (feedDTO.getFeedType().equals("TOGETHER")) {
            togetherFeedDAO.postFeedWrite(loginId, feedDTO.toFeedVO());
        }

        // 태크 입력
        if (feedDTO.getTags().size() != 0 || feedDTO.getTags() != null) {
            feedDTO.getTags().forEach( tagContent -> {
                tagDAO.postFeedWrite(tagContent, feedDTO.getId());
            });
        }

        // 파일 입력
        if (feedDTO.getFiles().size() != 0 || feedDTO.getFiles() != null) {
            feedDTO.getFiles().forEach( file -> {

                fileDAO.postFeedWrite(file); // 슈퍼키 입력

                // 피드 타입에 따라 제네럴, 투게더 파일 입력
                if (feedDTO.getFeedType().equals("GENERAL")) {
                    generalFileDAO.postFeedWrite(file.getId(), feedDTO.getId());
                } else if (feedDTO.getFeedType().equals("TOGETHER")) {
                    togetherFileDAO.postFeedWrite(file.getId(), feedDTO.getId());
                }
            });
        }
    }
}
