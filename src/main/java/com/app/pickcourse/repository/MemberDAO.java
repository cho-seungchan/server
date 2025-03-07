package com.app.pickcourse.repository;

import com.app.pickcourse.domain.vo.MemberVO;
import com.app.pickcourse.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberDAO {
    private final MemberMapper memberMapper;

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

//    로그인
    public Optional<MemberVO> findByMemberEmailAndPassword(MemberVO memberVO){
        return memberMapper.selectByMemberEmailAndMemberPassword(memberVO);
    }
}
