package com.app.pickcourse.service;

import com.app.pickcourse.domain.dto.MemberDTO;
import com.app.pickcourse.domain.vo.MemberVO;
import com.app.pickcourse.repository.MemberDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class MemberService {
    private final MemberDAO memberDAO;

    //    회원가입
    public void join(MemberDTO memberDTO){
        memberDAO.save(memberDTO.toVO());
    }

    //    이메일로 회원 조회
    public Optional<MemberVO> getMember(String memberEmail) {
        return memberDAO.findByMemberEmail(memberEmail);
    }
}
