package com.app.pickcourse.repository;

import com.app.pickcourse.domain.vo.MemberVO;
import com.app.pickcourse.mapper.MemberMapper;
import com.app.pickcourse.util.Pagination;
import com.app.pickcourse.util.Search;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberDAO {
    private final MemberMapper memberMapper;


    //    전체 건수
    public int getCountAll(Search search) {
        return memberMapper.getCountAll(search);
    }

    //    활동회원 전체 건수
    public int getCountAllActY(Search search) {
        return memberMapper.getCountAll(search);
    }

    //    활동정지 회원 전체 건수
    public int getCountAllActN(Search search) {
        return memberMapper.getCountAll(search);
    }

    //    전체 멤버 조회
    public List<MemberVO> getMemberList(Pagination pagination, Search search) {
        return memberMapper.getMemberList(pagination, search);
    }
    //    활동회원 전체 멤버 조회
    public List<MemberVO> getMemberListActY(Pagination pagination, Search search) {
        return memberMapper.getMemberListActY(pagination, search);
    }
    //    활동정지 회원 전체 멤버 조회
    public List<MemberVO> getMemberListActN(Pagination pagination, Search search) {
        return memberMapper.getMemberListActN(pagination, search);
    }

    //    추가
    public void kakaoSave(MemberVO memberVO){
        memberMapper.kakaoInsert(memberVO);
    }

    public void save(MemberVO memberVO){
        memberMapper.insert(memberVO);
    }
//    수정
    public void set(MemberVO memberVO){
        memberMapper.update(memberVO);
    }
//    삭제
    public void delete(MemberVO memberVO){
        memberMapper.delete(memberVO.getId());
    }

    //    조회(이메일)
    public Optional<MemberVO> findByMemberEmail(String memberEmail){
        return memberMapper.selectByMemberEmail(memberEmail);
    }

    // 이메일로 아이디 조회
    public Optional<Long> findIdByEmail(String email) {
        return memberMapper.selectIdByEmail(email);
    }

//    로그인
    public Optional<MemberVO> findByMemberEmailAndPassword(MemberVO memberVO){
        return memberMapper.selectByMemberEmailAndMemberPassword(memberVO);
    }

//    관리자 :: 회원 정지
    public void patchMemberListPause(Long memberId) {
        memberMapper.patchMemberListPause(memberId);
    }

//    관리자 :: 회원 정지 해제
    public void patchMemberListRestart(Long memberId) {
        memberMapper.patchMemberListRestart(memberId);
    }

//    관리자 :: 회원 추방
    public void deleteMemberList(Long memberId) {
        memberMapper.deleteMemberList(memberId);
    }

}
