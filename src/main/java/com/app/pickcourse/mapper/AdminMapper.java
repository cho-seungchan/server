package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.vo.AdminVO;
import com.app.pickcourse.util.Pagination;
import com.app.pickcourse.util.Search;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminMapper {

    // 전체 관리자 수
    int getCountAll(Search search);

    // 관리자 목록 조회
    List<AdminVO> getManageAdminList(@Param("pagination") Pagination pagination, @Param("search") Search search);

    // 관리자 등록
    void postManageAdmin(AdminVO admin);

    // 관리자 추방
    void deleteManageAdmin(Long id);
}
