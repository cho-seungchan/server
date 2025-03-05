package com.app.pickcourse.domain.dto;

import com.app.pickcourse.domain.vo.FeedTagVO;
import com.app.pickcourse.domain.vo.FeedVO;
import com.app.pickcourse.domain.vo.FileVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class FeedListDTO {
    private Long id;
    private String feedContent;
    private String createDate;
    private String updateDate;
    private Long   memberId;
    private Long   planId;
    private List<FileVO> files;
    private List<FeedTagVO> feedTags;

    public FeedVO toFeedVO() {
        FeedVO feedVO = new FeedVO();
        feedVO.setId(id);
        feedVO.setFeedContent(feedContent);
        return feedVO;
    }
}
