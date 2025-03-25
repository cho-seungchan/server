package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.vo.PayVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PayMapper {
//    결제 추가
    public void insertKakaoPay(PayVO payVO);
}
