package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.ReceiveMessageFileDTO;
import com.app.pickcourse.domain.vo.ReceiveMessageFileVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReceiveMessageFileMapper {

    public void insert(ReceiveMessageFileVO receiveMessageFileVO);

}
