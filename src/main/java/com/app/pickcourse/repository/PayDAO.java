package com.app.pickcourse.repository;

import com.app.pickcourse.domain.vo.PayVO;
import com.app.pickcourse.mapper.PayMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PayDAO {
    private final PayMapper payMapper;

    public void saveKakaoPay(PayVO payVO) {
        payMapper.insertKakaoPay(payVO);
    }
}
