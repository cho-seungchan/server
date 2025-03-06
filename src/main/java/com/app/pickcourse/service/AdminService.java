package com.app.pickcourse.service;

import com.app.pickcourse.domain.vo.AdminVO;
import com.app.pickcourse.domain.vo.MemberVO;
import com.app.pickcourse.exception.DuplicateException;
import com.app.pickcourse.mapper.AdminMapper;
import com.app.pickcourse.mapper.MemberMapper;
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

    private final AdminMapper adminMapper;
    private final MemberMapper memberMapper;

    public List<AdminVO> getManageAdminList(Pagination pagination, Search search) {
        log.info("count: {}", adminMapper.getCountAll(search));
        pagination.create(adminMapper.getCountAll(search));
        log.info("들어옴~!");
        return adminMapper.getManageAdminList(pagination, search);
    }

    public void postManageAdminList(AdminVO adminVO) throws DuplicateException {
        // 중복 체크
        if (adminMapper.isAdminAccount(adminVO.getAdminAccount()) > 0){
            throw new DuplicateException("adim account  "+adminVO.getAdminAccount()+"  already exist !!!");
        }

        adminMapper.postManageAdminList(adminVO);
    }

    public void deleteManageAdminList(String selectedIds) {
        // 삭제할 대상 id를 Long으로 변환
        List<Long> idList = Arrays.asList(selectedIds.split(",")).
                stream().map(Long::parseLong).collect(Collectors.toList());

        log.info("before for each ");
        idList.forEach(adminId -> {
            adminMapper.deleteManageAdminList(adminId);
            log.info("during for each ");
        });
        log.info("after for each ");
    }

    public List<MemberVO> getMemberList(Pagination pagination, Search search) {
        pagination.create(memberMapper.getCountAll(search));
        return memberMapper.getMemberList(pagination, search);
    }

//    public void patchMemberListPause(String selectedIds) {
//        List<Long> idList = Arrays.asList(selectedIds.split(",")).stream().
//                map(Long::parseLong).collect(Collectors.toList());
//
//        idList.forEach(memberId -> {
//            memberMapper.patchMemberListPause(memberId);
//        });
//    }
//
//    public void patchMemberListRestart(String selectedIds) {
//        List<Long> idList = Arrays.asList(selectedIds.split(",")).stream().
//                map(Long::parseLong).collect(Collectors.toList());
//
//        idList.forEach(memberId -> {
//            memberMapper.patchMemberListRestart(memberId);
//        });
//    }

    public void deleteMemberList(String selectedIds) {
        List<Long> idList = Arrays.asList(selectedIds.split(",")).stream().
                map(Long::parseLong).collect(Collectors.toList());

        idList.forEach(memberId -> {
            memberMapper.deleteMemberList(memberId);
        });

    }
}
