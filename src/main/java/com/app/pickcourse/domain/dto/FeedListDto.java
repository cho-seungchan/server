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
public class FeedListDto {
    private Long id;
    private String feedContent;
    private String createDate;
    private String updateDate;
    private Long   memberId;
    private List<FileVO> files;
}
