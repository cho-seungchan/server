package com.app.pickcourse.service;

import com.app.pickcourse.domain.vo.AdminVO;
import com.app.pickcourse.domain.vo.MemberVO;
import com.app.pickcourse.exception.DuplicateException;
import com.app.pickcourse.repository.AdminDAO;
import com.app.pickcourse.repository.MemberDAO;
import com.app.pickcourse.util.Pagination;
import com.app.pickcourse.util.Search;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {

    private final AdminDAO adminDAO;
    private final MemberDAO memberDAO;

    public List<AdminVO> getManageAdminList(Pagination pagination, Search search) {
        pagination.create(adminDAO.getCountAll(search));
        return adminDAO.getManageAdminList(pagination, search);
    }

    public void postManageAdminList(AdminVO adminVO) throws DuplicateException {
        // 중복 체크
        if (adminDAO.isAdminAccount(adminVO.getAdminAccount()) > 0){
            throw new DuplicateException("adim account  "+adminVO.getAdminAccount()+"  already exist !!!");
        }

        adminDAO.postManageAdminList(adminVO);
    }

    public void deleteManageAdminList(String selectedIds) {
        // 삭제할 대상 id를 Long으로 변환
        List<Long> idList = Arrays.asList(selectedIds.split(",")).
                stream().map(Long::parseLong).collect(Collectors.toList());

        idList.forEach(adminId -> {
            adminDAO.deleteManageAdminList(adminId);
        });
    }

    public List<MemberVO> getMemberList(Pagination pagination, Search search) {
        pagination.create(memberDAO.getCountAll(search));
        return memberDAO.getMemberList(pagination, search);
    }

    public void patchMemberListPause(String selectedIds) {
        List<Long> idList = Arrays.asList(selectedIds.split(",")).stream().
                map(Long::parseLong).collect(Collectors.toList());

        idList.forEach(memberId -> {
            memberDAO.patchMemberListPause(memberId);
        });
    }

    public void patchMemberListRestart(String selectedIds) {
        List<Long> idList = Arrays.asList(selectedIds.split(",")).stream().
                map(Long::parseLong).collect(Collectors.toList());


        idList.forEach(memberId -> {
            memberDAO.patchMemberListRestart(memberId);
        });
    }

    public void deleteMemberList(String selectedIds) {
        List<Long> idList = Arrays.asList(selectedIds.split(",")).stream().
                map(Long::parseLong).collect(Collectors.toList());

        idList.forEach(memberId -> {
            memberDAO.deleteMemberList(memberId);
        });

    }
}
