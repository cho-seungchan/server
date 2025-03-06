package com.app.pickcourse.repository;

import com.app.pickcourse.domain.vo.MemberVO;
import com.app.pickcourse.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberDAO {
    private final MemberMapper memberMapper;

    //    추가
    public void save(MemberVO memberVO){
        memberMapper.kakaoInsert(memberVO);
    }

    //    조회(이메일)
    public Optional<MemberVO> findByMemberEmail(String memberEmail){
        return memberMapper.selectByMemberEmail(memberEmail);
    }
}
