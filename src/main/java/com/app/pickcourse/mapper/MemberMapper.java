package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.vo.AdminVO;
import com.app.pickcourse.domain.vo.Criteria;
import com.app.pickcourse.domain.vo.MemberVO;
import com.app.pickcourse.domain.vo.Search;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberMapper {

    // 전체 회원 수
    int getCountAll(@Param("search") Search search);

    // 회원 목록 조회
    List<MemberVO> getMemberList(@Param("criteria") Criteria criteria, @Param("search") Search search);

    // 회원 정지
    void patchMemberList(@Param("id") Long id);

    // 회원 추장
    void deleteMemberList(@Param("id") Long id);
}
