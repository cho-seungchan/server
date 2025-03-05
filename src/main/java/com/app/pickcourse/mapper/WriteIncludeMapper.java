package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.vo.WriteIncludeVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WriteIncludeMapper {
    public void insert(WriteIncludeVO writeIncludeVO);
}
