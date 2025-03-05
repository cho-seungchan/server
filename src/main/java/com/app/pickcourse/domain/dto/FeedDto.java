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
public class FeedDto {
    private Long id;
    private String feedContent;
    private String createDate;
    private String updateDate;
    private List<FileVO> files;
    private List<FeedTagVO> feedTags;
}
