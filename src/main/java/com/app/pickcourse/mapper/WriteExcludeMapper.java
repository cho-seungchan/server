package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.vo.WriteExcludeVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WriteExcludeMapper {
    public void insert(WriteExcludeVO writeExcludeVO);
}
