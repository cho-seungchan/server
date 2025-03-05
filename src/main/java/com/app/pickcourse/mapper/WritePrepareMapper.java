package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.vo.WritePrepareVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WritePrepareMapper {
    public void insert(WritePrepareVO writePrepareVO);
}
