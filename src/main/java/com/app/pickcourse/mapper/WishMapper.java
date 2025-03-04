package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.vo.WishVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WishMapper {
//    찜한목록에 추가
    public void insert(WishVO wishVO);

//    찜한 목록
    public WishVO selectByMemberId(Long memberId);

//    삭제
    public void delete(Long memberId);


}
