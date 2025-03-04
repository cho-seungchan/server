package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.vo.Criteria;
import com.app.pickcourse.domain.vo.NoticeVO;
import com.app.pickcourse.domain.vo.Search;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NoticeMapper {
    // 페이지 처리를 위한 전체 공지사항 건수
    int getCountAll(@Param("search") Search search);

    // 공지사항 등록
    void postAddNotice(NoticeVO noticeVO);

    // 공지사항 목록
    List<NoticeVO> getManageNoticeList(@Param("criteria") Criteria criteria, @Param("search") Search search);

    // 공지사항 조회
    NoticeVO getManageNotice(@Param("id") Long id);

    // 공지사항 수정
    void patchManageNotice(NoticeVO noticeVO);

    // 공지사항 삭제
    void deleteManageNotice(@Param("id") Long id);

}
