package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.vo.FeedVO;
import com.app.pickcourse.domain.vo.FileVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FileMapper {

    void postFeedWrite(FileVO fileVO);

    // 슈퍼키만 삭제하면 자식들도 삭제
    void deleteModifyFeed(Long feedId);
}
