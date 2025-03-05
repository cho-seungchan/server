package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.FeedDTO;
import com.app.pickcourse.domain.dto.FeedListDTO;
import com.app.pickcourse.domain.vo.ReplyVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReplyMapper {

    List<ReplyDTO> getReplyList();
}
