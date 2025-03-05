package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.vo.PlanVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlanMapper {
    public void insert(PlanVO planVO);
}
