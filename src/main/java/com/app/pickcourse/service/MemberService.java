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
    public void kakaoJoin(MemberDTO memberDTO){
        memberDAO.kakaoSave(memberDTO.toVO());
    }

    public void join(MemberDTO memberDTO){
        memberDAO.save(memberDTO.toVO());
    }

    //    이메일로 회원 조회
    public Optional<MemberVO> getMember(String memberEmail) {
        return memberDAO.findByMemberEmail(memberEmail);
    }

    public void update(MemberDTO memberDTO){
        memberDAO.set(memberDTO.toVO());
    }

    // 회원 삭제
    public void delete(MemberDTO memberDTO) {
        memberDAO.delete(memberDTO.toVO());
    }

    // 이메일 로그인
    public Optional<MemberVO> login(MemberVO memberVO) {
        return memberDAO.findByMemberEmailAndPassword(memberVO);
    }



}
