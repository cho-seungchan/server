package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.vo.FeedVO;
import com.app.pickcourse.domain.vo.FileVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper {

    void postFeedWrite(FileVO fileVO);
}
