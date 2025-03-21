package com.app.pickcourse.service;

import com.app.pickcourse.domain.dto.MemberDTO;
import com.app.pickcourse.domain.vo.MemberVO;
import com.app.pickcourse.mapper.MemberMapper;
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
    private final MemberMapper memberMapper;

    //    회원가입
    public void kakaoJoin(MemberDTO memberDTO){
        memberDAO.kakaoSave(memberDTO.toVO());
    }

    public void join(MemberDTO memberDTO){
        memberDAO.save(memberDTO.toVO());
    }

    //    이메일로 회원 조회
    public Optional<MemberDTO> getMember(String memberEmail) {
        return memberDAO.findByMemberEmail(memberEmail);
    }

    public void update(MemberDTO memberDTO){
        Optional<MemberDTO> member = memberDAO.findById(memberDTO.getId());

        memberDTO.setMemberEmail(member.get().getMemberEmail());
        memberDTO.setMemberTell(member.get().getMemberTell());

        memberDAO.set(memberDTO.toVO());
    }

    // 회원 삭제
    public void delete(Long id) {memberDAO.delete(id);}

    // 이메일 로그인
    public Optional<MemberDTO> login(MemberDTO memberDTO) {
        return memberDAO.findByMemberEmailAndPassword(memberDTO);
    }

    // 닉네임 중복검사
    public Optional<MemberVO> getMemberByNickname(String nickname) {
        return memberMapper.findByNickname(nickname);
    }


    public boolean checkPassword(Long id, String oldPassword) {
        Optional<MemberDTO> optionalMember = memberMapper.selectById(id);

        if (optionalMember.isEmpty()) {
            return false; // 존재하지 않는 회원이면 false 반환
        }

        MemberDTO member = optionalMember.get();

        // 🚨 기존 비밀번호가 틀릴 경우, 예외를 던지지 않고 false 반환
        return member.getMemberPassword().equals(oldPassword);
    }
//    비밀번호 변경
    public void updatePassword(Long userId, String newPassword) {
        memberMapper.updatePassword(userId, newPassword);
    }

    public boolean checkNicknameDuplicate(String memberNickname) {
        return memberDAO.checkNicknameDuplicate(memberNickname);
    }

    public Optional<MemberDTO> findEmailByNickname(String memberNickname) {
        return memberDAO.findEmailByNickname(memberNickname);
    }
}
