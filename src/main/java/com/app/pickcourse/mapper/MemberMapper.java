package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface MemberMapper {
    //회원가입
    public void insert(MemberVO memberVO);

    //회원조회 & 중복검사
    public MemberVO selectByMemberEmail(String email);

    // 로그인
    public MemberVO selectByMemberEmailAndPassword(@Param("memberEmail") String email,
                                                   @Param("memberPassword") String password);
    // 정보수정
    public void update(MemberVO memberVO);

    // 회원 탈퇴
    public void delete(Long id);

}
