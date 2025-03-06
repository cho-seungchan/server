package com.app.pickcourse.domain.dto;

import com.app.pickcourse.domain.vo.FeedVO;
import com.app.pickcourse.domain.vo.FileVO;
import com.app.pickcourse.domain.vo.FeedTagVO;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class FeedDTO {
    private Long id;
    private String feedContent;
    private String createDate;
    private String updateDate;
    private int    realStar;  // 리얼 후기 별점
    private List<FileVO> files;
    private List<FeedTagVO> feedTags;

    public FeedVO toFeedVO() {
        FeedVO feedVO = new FeedVO();
        feedVO.setId(id);
        feedVO.setFeedContent(feedContent);
        return feedVO;
    }
}
